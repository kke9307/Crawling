package com.gstar.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gstar.app.model.AttBannerVo;
import com.gstar.app.model.AttExBannerVo;
import com.gstar.app.model.BannerDetailVo;
import com.gstar.app.model.GstarMhEventVo;
import com.gstar.app.model.SearchVo;
import com.gstar.app.model.SnsNowDetailVo;
import com.gstar.app.repository.BannerRepository;
import com.gstar.app.service.BannerService;
import com.gstar.app.util.Constants;

@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerRepository bannerRepository;

	@Override
	public List<BannerDetailVo> selectBannerList(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectBannerList(searchVo);
	}
	
	@Override
	public List<BannerDetailVo> selectBannerListNoOrder(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectBannerListNoOrder(searchVo);
	}
	
	@Override
	public List<BannerDetailVo> selectBannerListUser(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		
		searchVo.setSearchKind5("Y");
		List<BannerDetailVo> bannerList = bannerRepository.selectBannerList(searchVo);
		List<BannerDetailVo> newList = new ArrayList<BannerDetailVo>();
		for (BannerDetailVo item : bannerList) {
			if((item.getSite_open_gubun() & 2) > 0){
				BannerDetailVo searchBannerVo =new  BannerDetailVo();
				searchBannerVo.setBt_idx(item.getBt_idx());
				searchBannerVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
				item = bannerRepository.selectBannerDetail(searchBannerVo);
			}
			
			if((item.getSite_open_gubun() & 4) > 0){
				BannerDetailVo searchBannerVo =new  BannerDetailVo();
				searchBannerVo.setBt_idx(item.getBt_idx());
				searchBannerVo.setSite_gubun(Constants.SITE_GUBUN_ENG);
				item.setEngBannerDetailVo(bannerRepository.selectBannerDetail(searchBannerVo));
			}
			newList.add(item);
		}
		return newList;
	}

	@Override
	public int selectBannerListTotal(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectBannerListTotal(searchVo);
	}

	@Override
	public BannerDetailVo selectBanner(int bt_idx) throws Exception {
		// TODO Auto-generated method stub
		BannerDetailVo bannerDetailVo = bannerRepository.selectBanner(bt_idx);
		if((bannerDetailVo.getSite_open_gubun() & 2) > 0){
			bannerDetailVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
			bannerDetailVo = bannerRepository.selectBannerDetail(bannerDetailVo);
		}
		
		if((bannerDetailVo.getSite_open_gubun() & 4) > 0){
			bannerDetailVo.setSite_gubun(Constants.SITE_GUBUN_ENG);
			bannerDetailVo.setEngBannerDetailVo(bannerRepository.selectBannerDetail(bannerDetailVo));
			bannerDetailVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
		}
		return bannerDetailVo;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void insertBanner(BannerDetailVo bannerDetailVo) throws Exception {
		// TODO Auto-generated method stub
		// 기본 등록
		bannerRepository.insertBanner(bannerDetailVo);
		if((bannerDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			// 국문 등록
			bannerRepository.deleteBannerDetail(bannerDetailVo);
			bannerRepository.insertBannerDetail(bannerDetailVo);
		}
		if((bannerDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			// 영문 등록
			bannerDetailVo.getEngBannerDetailVo().setBt_idx(bannerDetailVo.getBt_idx());
			bannerRepository.deleteBannerDetail(bannerDetailVo.getEngBannerDetailVo());
			bannerRepository.insertBannerDetail(bannerDetailVo.getEngBannerDetailVo());
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void updateBanner(BannerDetailVo bannerDetailVo) throws Exception {
		// TODO Auto-generated method stub
		bannerRepository.updateBanner(bannerDetailVo);
		
		if((bannerDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			// 국문 등록
			bannerRepository.deleteBannerDetail(bannerDetailVo);
			bannerRepository.insertBannerDetail(bannerDetailVo);
		}else{
			BannerDetailVo deleteDetailVo = new BannerDetailVo();
			deleteDetailVo.setBt_idx(bannerDetailVo.getBt_idx());
			deleteDetailVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
			bannerRepository.deleteBannerDetail(deleteDetailVo);
		}
		if((bannerDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			// 영문 등록
			bannerDetailVo.getEngBannerDetailVo().setBt_idx(bannerDetailVo.getBt_idx());
			bannerRepository.deleteBannerDetail(bannerDetailVo.getEngBannerDetailVo());
			bannerRepository.insertBannerDetail(bannerDetailVo.getEngBannerDetailVo());
		}else{
			BannerDetailVo deleteDetailVo = new BannerDetailVo();
			deleteDetailVo.setBt_idx(bannerDetailVo.getBt_idx());
			deleteDetailVo.setSite_gubun(Constants.SITE_GUBUN_ENG);
			bannerRepository.deleteBannerDetail(deleteDetailVo);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void deleteBanner(List<BannerDetailVo> bannerDetailList) throws Exception {
		// TODO Auto-generated method stub
		BannerDetailVo bannerDetailVo = null;
		for(BannerDetailVo delItem : bannerDetailList){
			bannerDetailVo = bannerRepository.selectBanner(delItem.getBt_idx());  
			delItem.setSite_gubun(Constants.SITE_GUBUN_KOR);
			bannerRepository.deleteBannerDetail(delItem);
			delItem.setSite_gubun(Constants.SITE_GUBUN_ENG);
			bannerRepository.deleteBannerDetail(delItem);
			
			bannerRepository.deleteBanner(delItem);
		}
		SearchVo searchVo = new SearchVo();
		searchVo.setFirstIndex(0);
		searchVo.setPageSize(10000);
		searchVo.setSearchGubun(bannerDetailVo.getBt_position());
		List<BannerDetailVo> bannerList = bannerRepository.selectBannerList(searchVo);
		int order = 0;
		for(BannerDetailVo item: bannerList){
			order++;
			item.setBt_order(order);
			bannerRepository.soartUpdate(item);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void soartUpDate(BannerDetailVo bannerDetailVo, String sortType) throws Exception {
		// TODO Auto-generated method stub
		
		if(sortType.equals("down")){
			bannerRepository.updateBannerSortDown(bannerDetailVo);
		}else{
			bannerRepository.updateBannerSortUp(bannerDetailVo);
		}
		bannerRepository.updateBannerSort(bannerDetailVo);	
		
		bannerRepository.soartUpdate(bannerDetailVo);
		
		
		SearchVo searchVo = new SearchVo();
		searchVo.setFirstIndex(0);
		searchVo.setPageSize(10000);
		searchVo.setSearchGubun(bannerDetailVo.getBt_position());
		List<BannerDetailVo> bannerList = bannerRepository.selectBannerList(searchVo);
		int order = 0;
		for(BannerDetailVo item: bannerList){
			order++;
			item.setBt_order(order);
			bannerRepository.soartUpdate(item);
		}
		
		
		
	}
	
	@Override
	public int selectBannerMaxOrder() throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectBannerMaxOrder();
	}
	
	
	@Override
	public List<GstarMhEventVo> SelectEventComList(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.SelectEventComList(searchVo);
	}

	@Override
	public int SelectEventComListTotal(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.SelectEventComListTotal(searchVo);
	}
	
	@Override
	public List<GstarMhEventVo> SelectExComList(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.SelectExComList(searchVo);
	}

	@Override
	public int SelectExComListTotal(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.SelectExComListTotal(searchVo);
	}
	
	@Override
	public void deleteAttBanner(AttBannerVo attBannerVo) throws Exception {
		// TODO Auto-generated method stub
		bannerRepository.deleteAttBanner(attBannerVo);
	}
	
	
	
	@Override
	public AttBannerVo selectAttBanner(String str) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectAttBanner(str);
	}
	
	@Override
	public AttExBannerVo selectAttExBanner(String str) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectAttExBanner(str);
	}
	
	@Override
	public void insertAttBanner(AttBannerVo attBannerVo) throws Exception {
		// TODO Auto-generated method stub
		if(bannerRepository.selectAttBanner(attBannerVo.getEbt_position()) == null){
			bannerRepository.insertAttBanner(attBannerVo);
		}else{
			bannerRepository.updateAttBanner(attBannerVo);
		}
		
	}
	
	@Override
	public void insertExAttBanner(AttExBannerVo attExBannerVo) throws Exception {
		// TODO Auto-generated method stub
		if(bannerRepository.selectAttExBanner(attExBannerVo.getEht_position()) == null){
			bannerRepository.insertExAttBanner(attExBannerVo);
		}else{
			bannerRepository.updateAttExBanner(attExBannerVo);
		}
	}
	
	@Override
	public void updateAttBanner(AttBannerVo attBannerVo) throws Exception {
		// TODO Auto-generated method stub
		bannerRepository.updateAttBanner(attBannerVo);
	}
	
	@Override
	public void updateAttExBanner(AttExBannerVo attExBannerVo) throws Exception {
		// TODO Auto-generated method stub
		bannerRepository.updateAttExBanner(attExBannerVo);
	}

	@Override
	public void deleteExAttBanner(AttExBannerVo attExBannerVo) throws Exception {
		// TODO Auto-generated method stub
		bannerRepository.deleteExAttBanner(attExBannerVo);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void insertSnsNow(SnsNowDetailVo snsNowDetailVo) throws Exception {
		// TODO Auto-generated method stub
		// 기본 등록
		bannerRepository.insertSnsNow(snsNowDetailVo);
		if((snsNowDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			// 국문 등록
			bannerRepository.deleteSnsNowDetail(snsNowDetailVo);
			bannerRepository.insertSnsNowDetail(snsNowDetailVo);
		}
		if((snsNowDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			// 영문 등록
			snsNowDetailVo.getEngSnsNowDetailVo().setSns_idx(snsNowDetailVo.getSns_idx());
			bannerRepository.deleteSnsNowDetail(snsNowDetailVo.getEngSnsNowDetailVo());
			bannerRepository.insertSnsNowDetail(snsNowDetailVo.getEngSnsNowDetailVo());
		}
	}

	@Override
	public List<SnsNowDetailVo> selectSnsList(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectSnsList(searchVo);
	}

	@Override
	public int selectSnsListTotal(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectSnsListTotal(searchVo);
	}

	@Override
	public SnsNowDetailVo selectSns(int sns_idx) throws Exception {
		// TODO Auto-generated method stub
		SnsNowDetailVo snsNowVo = bannerRepository.selectSns(sns_idx);
		if((snsNowVo.getSite_open_gubun() & 2) > 0){
			snsNowVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
			snsNowVo = bannerRepository.selectSnsDetail(snsNowVo);
		}
		
		if((snsNowVo.getSite_open_gubun() & 4) > 0){
			snsNowVo.setSite_gubun(Constants.SITE_GUBUN_ENG);
			snsNowVo.setEngSnsNowDetailVo(bannerRepository.selectSnsDetail(snsNowVo));
			snsNowVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
		}
		return snsNowVo;
	}

	@Override
	public SnsNowDetailVo selectSnsDetail(SnsNowDetailVo snsNowDetailVo) throws Exception {
		// TODO Auto-generated method stub
		return bannerRepository.selectSnsDetail(snsNowDetailVo);
	}

	@Override
	public void updateSnsNow(SnsNowDetailVo snsNowDetailVo) throws Exception {
		// TODO Auto-generated method stub
		bannerRepository.updateSnsNow(snsNowDetailVo);
		
		if((snsNowDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			// 국문 등록
			snsNowDetailVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
			bannerRepository.deleteSnsNowDetail(snsNowDetailVo);
			bannerRepository.insertSnsNowDetail(snsNowDetailVo);
		}else{
			SnsNowDetailVo deleteDetailVo = new SnsNowDetailVo();
			deleteDetailVo.setSns_idx(snsNowDetailVo.getSns_idx());
			deleteDetailVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
			bannerRepository.deleteSnsNowDetail(deleteDetailVo);
		}
		if((snsNowDetailVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			// 영문 등록
			snsNowDetailVo.getEngSnsNowDetailVo().setSns_idx(snsNowDetailVo.getSns_idx());
			snsNowDetailVo.getEngSnsNowDetailVo().setSite_gubun(Constants.SITE_GUBUN_ENG);
			bannerRepository.deleteSnsNowDetail(snsNowDetailVo.getEngSnsNowDetailVo());
			bannerRepository.insertSnsNowDetail(snsNowDetailVo.getEngSnsNowDetailVo());
		}else{
			SnsNowDetailVo deleteDetailVo = new SnsNowDetailVo();
			deleteDetailVo.setSns_idx(snsNowDetailVo.getSns_idx());
			deleteDetailVo.setSite_gubun(Constants.SITE_GUBUN_ENG);
			bannerRepository.deleteSnsNowDetail(deleteDetailVo);
		}
	}

	@Override
	public void deleteSnsNow(List<SnsNowDetailVo> snsNowDetailVo) throws Exception {
		// TODO Auto-generated method stub
		for(SnsNowDetailVo snsNowVo : snsNowDetailVo) {
			bannerRepository.deleteSnsNowDetail(snsNowVo);
			bannerRepository.deleteSnsNow(snsNowVo);
		}
	}
	
	@Override
	public List<SnsNowDetailVo> selectSnsListUser(SearchVo searchVo) throws Exception {
		// TODO Auto-generated method stub
		searchVo.setSearchKind5("Y");
		List<SnsNowDetailVo> snsList = bannerRepository.selectSnsList(searchVo);
		List<SnsNowDetailVo> newList = new ArrayList<SnsNowDetailVo>();
		for (SnsNowDetailVo item : snsList) {
			if((item.getSite_open_gubun() & 2) > 0){
				SnsNowDetailVo searchSnsNowVo =new  SnsNowDetailVo();
				searchSnsNowVo.setSns_idx(item.getSns_idx());
				searchSnsNowVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
				item = bannerRepository.selectSnsDetail(searchSnsNowVo);
			}
			
			if((item.getSite_open_gubun() & 4) > 0){
				SnsNowDetailVo searchSnsNowVo =new  SnsNowDetailVo();
				searchSnsNowVo.setSns_idx(item.getSns_idx());
				searchSnsNowVo.setSite_gubun(Constants.SITE_GUBUN_ENG);
				item.setEngSnsNowDetailVo(bannerRepository.selectSnsDetail(searchSnsNowVo));
			}
			newList.add(item);
		}
		return newList;
	}
}
