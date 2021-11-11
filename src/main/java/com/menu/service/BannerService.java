package com.gstar.app.service;

import java.util.List;

import com.gstar.app.model.AttBannerVo;
import com.gstar.app.model.AttExBannerVo;
import com.gstar.app.model.BannerDetailVo;
import com.gstar.app.model.GstarMhEventVo;
import com.gstar.app.model.SearchVo;
import com.gstar.app.model.SnsNowDetailVo;

public interface BannerService {
	List<BannerDetailVo> selectBannerList(SearchVo searchVo) throws Exception;
	List<BannerDetailVo> selectBannerListNoOrder(SearchVo searchVo) throws Exception;//등록일자 정렬
	List<BannerDetailVo> selectBannerListUser(SearchVo searchVo) throws Exception;
	
	int selectBannerListTotal(SearchVo searchVo) throws Exception;
	BannerDetailVo selectBanner(int bt_idx) throws Exception;
	void insertBanner(BannerDetailVo bannerDetailVo) throws Exception;
	void updateBanner(BannerDetailVo bannerDetailVo) throws Exception;
	void deleteBanner(List<BannerDetailVo> bannerDetailList) throws Exception;
	void soartUpDate(BannerDetailVo bannerDetailVo, String sortType) throws Exception;
	int selectBannerMaxOrder() throws Exception;
	
	/*참가사*/
	List<GstarMhEventVo> SelectEventComList(SearchVo searchVo) throws Exception;
	int SelectEventComListTotal(SearchVo searchVo) throws Exception;
	AttBannerVo selectAttBanner(String str) throws Exception;
	AttExBannerVo selectAttExBanner(String str) throws Exception;
	void insertAttBanner(AttBannerVo attBannerVo) throws Exception;
	void insertExAttBanner(AttExBannerVo attExBannerVo) throws Exception;
	void updateAttBanner(AttBannerVo attBannerVo) throws Exception;
	void updateAttExBanner(AttExBannerVo attExBannerVo) throws Exception;
	
	List<GstarMhEventVo> SelectExComList(SearchVo searchVo) throws Exception;
	int SelectExComListTotal(SearchVo searchVo) throws Exception;
	
	void deleteAttBanner(AttBannerVo attBannerVo) throws Exception;
	
	
	void deleteExAttBanner(AttExBannerVo attExBannerVo) throws Exception;
	
	/* SNS NOW */ 
	List<SnsNowDetailVo> selectSnsList(SearchVo searchVo) throws Exception;
	List<SnsNowDetailVo> selectSnsListUser(SearchVo searchVo) throws Exception;
	int selectSnsListTotal(SearchVo searchVo) throws Exception;
	SnsNowDetailVo selectSns(int sns_idx) throws Exception;
	SnsNowDetailVo selectSnsDetail(SnsNowDetailVo snsNowDetailVo) throws Exception;
	void insertSnsNow(SnsNowDetailVo snsNowDetailVo) throws Exception;
	void updateSnsNow(SnsNowDetailVo snsNowDetailVo) throws Exception;
	void deleteSnsNow(List<SnsNowDetailVo> snsNowDetailVo) throws Exception;

}

