package com.gstar.app.web.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gstar.app.model.AttBannerVo;
import com.gstar.app.model.AttExBannerVo;
import com.gstar.app.model.BannerDetailVo;
import com.gstar.app.model.FileVo;
import com.gstar.app.model.GstarMhEventVo;
import com.gstar.app.model.LoginVo;
import com.gstar.app.model.SearchVo;
import com.gstar.app.model.SnsNowDetailVo;
import com.gstar.app.service.BannerService;
import com.gstar.app.service.FileMngService;
import com.gstar.app.util.Constants;
import com.gstar.app.util.FileMngUtil;
import com.gstar.app.util.PageHolder;
import com.gstar.app.web.CommonController;



@SessionAttributes({"bannerVo", "searchVo","snsNowVo","snsNowDetailVo"})
@RequestMapping("/admincenter/**")
@Controller
public class BannerAdminController extends CommonController {

	@Autowired
	private Properties commonProp;

	@Autowired
	private BannerService bannerService;	
	
	@Autowired
	private FileMngUtil fileUtil;	

	@Autowired
	private FileMngService fileMngService;	

	
	// 메뉴 번호
	private String menuDept = "1";

	
	@RequestMapping("/banner/banner_list.do")
	public String banner_list(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		LoginVo loginVo = (LoginVo) request.getSession().getAttribute(Constants.LOGIN_VO);
		// 파라미터 세팅
		SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
    	
		// 검색조건 추가
		/*String searchDel = ServletRequestUtils.getStringParameter(request, "searchDel", "N");
		searchVo.setSearchDel(searchDel);
		model.addAttribute("searchDel", searchDel);
		this.paramMap.put("searchDel",searchDel);*/
		
		searchVo.setSearchGubun(Constants.BANNER_TOP_POSITION);
		/*searchVo.setSearchSite(Constants.SITE_GUBUN_KOR);*/
		
		int totalCount = 0;
		int maxOrder = 0;
    	// 페이징에 추가 파라미터를 전달 한다.
    	List<BannerDetailVo> bannerList = bannerService.selectBannerList(searchVo); 
    	totalCount = bannerService.selectBannerListTotal(searchVo);
    	maxOrder = bannerService.selectBannerMaxOrder();
    	PageHolder pageholder = setPageNumRow(request, totalCount);
		
		
    	this.setParamToModeSearch(model, searchVo);
    	
    	
    	model.addAttribute("pageholder", pageholder);
    	model.addAttribute("totalCount", totalCount);
    	model.addAttribute("maxOrder", maxOrder);
		model.addAttribute("itemList", bannerList);

    	
		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 1);

		
		return "admincenter/banner/banner_list";
	}
	@RequestMapping("/banner/banner_bottom_list.do")
	public String banner_bottom_list(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		LoginVo loginVo = (LoginVo) request.getSession().getAttribute(Constants.LOGIN_VO);
		// 파라미터 세팅
		SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
    	
		// 검색조건 추가
		/*String searchDel = ServletRequestUtils.getStringParameter(request, "searchDel", "N");
		searchVo.setSearchDel(searchDel);
		model.addAttribute("searchDel", searchDel);
		this.paramMap.put("searchDel",searchDel);*/
		
		searchVo.setSearchGubun(Constants.BANNER_MIDDLE_POSITION);
		/*searchVo.setSearchSite(Constants.SITE_GUBUN_KOR);*/
		
		int totalCount = 0;
		int maxOrder = 0;
    	// 페이징에 추가 파라미터를 전달 한다.
    	List<BannerDetailVo> bannerList = bannerService.selectBannerList(searchVo); 
    	totalCount = bannerService.selectBannerListTotal(searchVo);
    	maxOrder = bannerService.selectBannerMaxOrder();
    	PageHolder pageholder = setPageNumRow(request, totalCount);
		
		
    	this.setParamToModeSearch(model, searchVo);
    	
    	
    	model.addAttribute("pageholder", pageholder);
    	model.addAttribute("totalCount", totalCount);
    	model.addAttribute("maxOrder", maxOrder);
		model.addAttribute("itemList", bannerList);

    	
		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 3);

		
		return "admincenter/banner/banner_bottom_list";
	}
	
	@RequestMapping("/banner/banner_sponser_list.do")
	public String banner_sponser_list(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		LoginVo loginVo = (LoginVo) request.getSession().getAttribute(Constants.LOGIN_VO);
		// 파라미터 세팅
		SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
    	
		// 검색조건 추가
		/*String searchDel = ServletRequestUtils.getStringParameter(request, "searchDel", "N");
		searchVo.setSearchDel(searchDel);
		model.addAttribute("searchDel", searchDel);
		this.paramMap.put("searchDel",searchDel);*/
		
		searchVo.setSearchGubun(Constants.BANNER_SPONSER_POSITION);
		/*searchVo.setSearchSite(Constants.SITE_GUBUN_KOR);*/
		
		int totalCount = 0;
		int maxOrder = 0;
    	// 페이징에 추가 파라미터를 전달 한다.
    	List<BannerDetailVo> bannerList = bannerService.selectBannerList(searchVo); 
    	totalCount = bannerService.selectBannerListTotal(searchVo);
    	maxOrder = bannerService.selectBannerMaxOrder();
    	PageHolder pageholder = setPageNumRow(request, totalCount);
		
		
    	this.setParamToModeSearch(model, searchVo);
    	
    	
    	model.addAttribute("pageholder", pageholder);
    	model.addAttribute("totalCount", totalCount);
    	model.addAttribute("maxOrder", maxOrder);
		model.addAttribute("itemList", bannerList);

    	
		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 7);

		
		return "admincenter/banner/banner_sponser_list";
	}
	
	@RequestMapping(value="/banner/banner_bottom_inscr.do", method = RequestMethod.GET)
	public String banner_bottom_inscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		this.setParamToModeSearch(model, searchVo);
		
		model.addAttribute("bannerVo", new BannerDetailVo());

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 3);
		
		return "admincenter/banner/banner_bottom_inscr";
	}
	@RequestMapping(value="/banner/banner_sponser_inscr.do", method = RequestMethod.GET)
	public String banner_sponser_inscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		this.setParamToModeSearch(model, searchVo);
		
		model.addAttribute("bannerVo", new BannerDetailVo());

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 7);
		
		return "admincenter/banner/banner_sponser_inscr";
	}
	@RequestMapping(value="/banner/banner_inscr.do", method = RequestMethod.GET)
	public String banner_inscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		this.setParamToModeSearch(model, searchVo);
		
		model.addAttribute("bannerVo", new BannerDetailVo());

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 1);
		
		return "admincenter/banner/banner_inscr";
	}
	
	@RequestMapping(value="/banner/banner_upscr.do", method = RequestMethod.GET)
	public String banner_upscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		
		int bt_idx = ServletRequestUtils.getIntParameter(request, "bt_idx", 0);
		
		BannerDetailVo bannerVo = bannerService.selectBanner(bt_idx);
		
		
		if((bannerVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			String atchFileId = bannerVo.getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			bannerVo.setpFileVo(fileVo);
			
			
			String atchMFileId = bannerVo.getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			bannerVo.setmFileVo(mFileVo);
		}else{
			bannerVo.setpFileVo(null);
			bannerVo.setmFileVo(null);
		}
		
		if((bannerVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			String atchFileId = bannerVo.getEngBannerDetailVo().getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			bannerVo.getEngBannerDetailVo().setpFileVo(fileVo);
			
			
			String atchMFileId = bannerVo.getEngBannerDetailVo().getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			bannerVo.getEngBannerDetailVo().setmFileVo(mFileVo);
		}else{
			bannerVo.setEngBannerDetailVo(new BannerDetailVo());
			bannerVo.getEngBannerDetailVo().setpFileVo(null);
			bannerVo.getEngBannerDetailVo().setmFileVo(null);
		}
		
		
		
		this.setParamToModeSearch(model, searchVo);
		
		
		model.addAttribute("bannerVo", bannerVo);

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 1);
		
		return "admincenter/banner/banner_upscr";
	}
	@RequestMapping(value="/banner/banner_bottom_upscr.do", method = RequestMethod.GET)
	public String banner_bottom_upscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		
		int bt_idx = ServletRequestUtils.getIntParameter(request, "bt_idx", 0);
		
		BannerDetailVo bannerVo = bannerService.selectBanner(bt_idx);
		
		
		if((bannerVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			String atchFileId = bannerVo.getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			bannerVo.setpFileVo(fileVo);
			
			
			String atchMFileId = bannerVo.getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			bannerVo.setmFileVo(mFileVo);
		}else{
			bannerVo.setpFileVo(null);
			bannerVo.setmFileVo(null);
		}
		
		if((bannerVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			String atchFileId = bannerVo.getEngBannerDetailVo().getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			bannerVo.getEngBannerDetailVo().setpFileVo(fileVo);
			
			
			String atchMFileId = bannerVo.getEngBannerDetailVo().getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			bannerVo.getEngBannerDetailVo().setmFileVo(mFileVo);
		}else{
			bannerVo.setEngBannerDetailVo(new BannerDetailVo());
			bannerVo.getEngBannerDetailVo().setpFileVo(null);
			bannerVo.getEngBannerDetailVo().setmFileVo(null);
		}
		
		
		
		this.setParamToModeSearch(model, searchVo);
		
		
		model.addAttribute("bannerVo", bannerVo);

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 3);
		
		return "admincenter/banner/banner_bottom_upscr";
	}
	
	
	@RequestMapping(value="/banner/banner_sponser_upscr.do", method = RequestMethod.GET)
	public String banner_sponser_upscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		
		int bt_idx = ServletRequestUtils.getIntParameter(request, "bt_idx", 0);
		
		BannerDetailVo bannerVo = bannerService.selectBanner(bt_idx);
		
		
		if((bannerVo.getSite_open_gubun() & Constants.SITE_GUBUN_KOR) > 0){
			String atchFileId = bannerVo.getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			bannerVo.setpFileVo(fileVo);
			
			
			String atchMFileId = bannerVo.getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			bannerVo.setmFileVo(mFileVo);
		}else{
			bannerVo.setpFileVo(null);
			bannerVo.setmFileVo(null);
		}
		
		if((bannerVo.getSite_open_gubun() & Constants.SITE_GUBUN_ENG) > 0){
			String atchFileId = bannerVo.getEngBannerDetailVo().getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			bannerVo.getEngBannerDetailVo().setpFileVo(fileVo);
			
			
			String atchMFileId = bannerVo.getEngBannerDetailVo().getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			bannerVo.getEngBannerDetailVo().setmFileVo(mFileVo);
		}else{
			bannerVo.setEngBannerDetailVo(new BannerDetailVo());
			bannerVo.getEngBannerDetailVo().setpFileVo(null);
			bannerVo.getEngBannerDetailVo().setmFileVo(null);
		}
		
		
		
		this.setParamToModeSearch(model, searchVo);
		
		
		model.addAttribute("bannerVo", bannerVo);

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 7);
		
		return "admincenter/banner/banner_sponser_upscr";
	}
	
	@RequestMapping(value="/banner/banner_inscr.do", method = RequestMethod.POST)
	public String banner_inscr_proc(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("bannerVo") BannerDetailVo bannerVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		logger.info("=================] Call news_inscr [=================");
		// 파라미터 세팅
		String ren_view = "common/message";
		try {
			// 등록자 아이디 
			LoginVo loginVo = (LoginVo)multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			
			if(loginVo == null){
	   			model.addAttribute("msg", "alterBack");
	       		model.addAttribute("altmsg", "잘못된 접근 입니다.");
			}else{
				int[] site_open_gubun_arr = ServletRequestUtils.getIntParameters(multiRequest, "site_open_gubun");
				
				int site_open_gubun = 0;
				for(int i = 0; i < site_open_gubun_arr.length; i++){
					site_open_gubun += site_open_gubun_arr[i];
				}
				bannerVo.setSite_open_gubun(site_open_gubun);
				/*bannerVo.setBt_position(Constants.BANNER_TOP_POSITION);*/
				bannerVo.setIn_mem_id(loginVo.getMem_id());
				MultipartFile file = null;
				List<FileVo> result = null;
				String atchFileId = "";
				List<MultipartFile> tmpAttFiles = null;
				Map<String, MultipartFile> tmpfiles = null;
		    	int chkcnt = 0;
		    	boolean chkFlag = true;

		    	if((site_open_gubun & Constants.SITE_GUBUN_KOR) > 0){
		    		// 국문 체크시
		    		bannerVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
		    	
			    	tmpAttFiles = multiRequest.getFiles("attPcFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
				    	if(chkcnt > 0){
				    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
				    		atchFileId = fileMngService.insertFileInfs(result);
				    		bannerVo.setP_atch_file_id(atchFileId);
				    	}
				    	
				    	
			    	}
			    	tmpAttFiles = multiRequest.getFiles("attMobileFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
				    	if(chkcnt > 0){
				    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
				    		atchFileId = fileMngService.insertFileInfs(result);
				    		bannerVo.setM_atch_file_id(atchFileId);
				    	}
			    	}
			    	
		    	}
		    	if((site_open_gubun & Constants.SITE_GUBUN_ENG) > 0){
		    		// 영문도 선택 했다면
		    		bannerVo.setEngBannerDetailVo(new BannerDetailVo());
		    		String btd_url = ServletRequestUtils.getStringParameter(multiRequest, "btd_eng_url", "");
		    		String btd_target = ServletRequestUtils.getStringParameter(multiRequest, "btd_eng_target", "");
		    		bannerVo.getEngBannerDetailVo().setBtd_url(btd_url);
		    		bannerVo.getEngBannerDetailVo().setSite_gubun(Constants.SITE_GUBUN_ENG);
		    		bannerVo.getEngBannerDetailVo().setBtd_target(btd_target);
		    		tmpAttFiles = multiRequest.getFiles("attEngPcFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
				    	if(chkcnt > 0){
				    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
				    		atchFileId = fileMngService.insertFileInfs(result);
				    		bannerVo.getEngBannerDetailVo().setP_atch_file_id(atchFileId);
				    	}
				    	
				    	bannerVo.getEngBannerDetailVo().setIn_mem_id(loginVo.getMem_id());
			    	}
			    	tmpAttFiles = multiRequest.getFiles("attEngMobileFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
				    	if(chkcnt > 0){
				    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
				    		atchFileId = fileMngService.insertFileInfs(result);
				    		bannerVo.getEngBannerDetailVo().setM_atch_file_id(atchFileId);
				    	}
			    	}
		    	}
		    	bannerService.insertBanner(bannerVo);
		    	
		    	model.addAttribute("msg", "alterloc");
		    	if(bannerVo.getBt_position().equals(Constants.BANNER_TOP_POSITION)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/banner_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_MIDDLE_POSITION)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/banner_bottom_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_SPONSER_POSITION)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/banner_sponser_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_GSTARTV_EXHIBITOR)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/gstar/gstar_exhibitor_banner_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_GSTARTV_GAME_CONTENTS)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/gstar/gstar_game_contents_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_GSTARTV_PARTNER_SPONSOR)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/gstar/gstar_partner_sponsor_list.do");
		    	}
	   			model.addAttribute("altmsg", "배너를 등록 하였습니다.");
			}
    	}catch (Exception e){
    		model.addAttribute("msg", "alterBack");
       		model.addAttribute("altmsg", "배너 등록에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}
       		return ren_view;			
	}
	@RequestMapping(value="/banner/banner_upscr.do", method = RequestMethod.POST)
	public String banner_upscr_proc(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("bannerVo") BannerDetailVo bannerVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		logger.info("=================] Call news_inscr [=================");
		// 파라미터 세팅
		String ren_view = "common/message";
		try {
			// 등록자 아이디 
			LoginVo loginVo = (LoginVo)multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			
			if(loginVo == null){
	   			model.addAttribute("msg", "alterBack");
	       		model.addAttribute("altmsg", "잘못된 접근 입니다.");
			}else{
				int[] site_open_gubun_arr = ServletRequestUtils.getIntParameters(multiRequest, "site_open_gubun");
				
				int site_open_gubun = 0;
				for(int i = 0; i < site_open_gubun_arr.length; i++){
					site_open_gubun += site_open_gubun_arr[i];
				}
				bannerVo.setSite_open_gubun(site_open_gubun);
				/*bannerVo.setBt_position(Constants.BANNER_TOP_POSITION);*/
				bannerVo.setIn_mem_id(loginVo.getMem_id());
				MultipartFile file = null;
				List<FileVo> result = null;
				String atchFileId = "";
				List<MultipartFile> tmpAttFiles = null;
				Map<String, MultipartFile> tmpfiles = null;
		    	int chkcnt = 0;
		    	boolean chkFlag = true;

		    	if((site_open_gubun & Constants.SITE_GUBUN_KOR) > 0){
		    		// 국문 체크시
		    		bannerVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
		    	
			    	tmpAttFiles = multiRequest.getFiles("attPcFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
				    	if(chkcnt > 0){
				    		
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getP_atch_file_id());
							List<FileVo> chkList = fileMngService.selectFileInfs(fvo);
							if(chkList.size() > 0){
								// 기존에 등록 된게 있으면
								
								// 단일 등록일 시는 무조건 삭제 후 등록 한다.
								fileMngService.deleteFileInfs(chkList);
					    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.setP_atch_file_id(atchFileId);
								
								//기존 파일 아이디에 추가
								/*int cnt = fileMngService.getMaxFileSN(fvo);
								List<FileVo> _result = fileUtil.parseFileInf(tmpfiles, "BN_",cnt, bannerVo.getP_atch_file_id(), commonProp.getProperty("bn.file.path"));
								fileMngService.updateFileInfs(_result);*/
							}else{
					    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.setP_atch_file_id(atchFileId);
							}
				    	}else{
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getP_atch_file_id());
							List<FileVo> fileVoList = fileMngService.selectFileInfs(fvo);
							if (fileVoList.size() == 0) {
								bannerVo.setP_atch_file_id(null);
							}
				    	}
			    	}
			    	tmpAttFiles = multiRequest.getFiles("attMobileFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
			    		if(chkcnt > 0){
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getM_atch_file_id());
							List<FileVo> chkList = fileMngService.selectFileInfs(fvo);
							if(chkList.size() > 0){
								// 기존에 등록 된게 있으면
								// 단일 등록일 시는 무조건 삭제 후 등록 한다.
								fileMngService.deleteFileInfs(chkList);
								result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.setM_atch_file_id(atchFileId);
								
								//기존 파일 아이디에 추가
								/*int cnt = fileMngService.getMaxFileSN(fvo);
								List<FileVo> _result = fileUtil.parseFileInf(tmpfiles, "BN_",cnt, bannerVo.getM_atch_file_id(), commonProp.getProperty("bn.file.path"));
								fileMngService.updateFileInfs(_result);*/
							}else{
					    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.setM_atch_file_id(atchFileId);
							}
				    	}else{
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getM_atch_file_id());
							List<FileVo> fileVoList = fileMngService.selectFileInfs(fvo);
							if (fileVoList.size() == 0) {
								bannerVo.setM_atch_file_id(null);
							}
				    	}
			    	}
		    	}
		    	if((site_open_gubun & Constants.SITE_GUBUN_ENG) > 0){
		    		// 영문도 선택 했다면
		    		
		    		String btd_url = ServletRequestUtils.getStringParameter(multiRequest, "btd_eng_url", "");
		    		String btd_target = ServletRequestUtils.getStringParameter(multiRequest, "btd_eng_target", "");
		    		bannerVo.getEngBannerDetailVo().setBtd_url(btd_url);
		    		bannerVo.getEngBannerDetailVo().setSite_gubun(Constants.SITE_GUBUN_ENG);
		    		bannerVo.getEngBannerDetailVo().setBtd_target(btd_target);
		    		tmpAttFiles = multiRequest.getFiles("attEngPcFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
			    		if(chkcnt > 0){
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getEngBannerDetailVo().getP_atch_file_id());
							List<FileVo> chkList = fileMngService.selectFileInfs(fvo);
							if(chkList.size() > 0){
								// 기존에 등록 된게 있으면
								// 단일 등록일 시는 무조건 삭제 후 등록 한다.
								
								fileMngService.deleteFileInfs(chkList);
								result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.getEngBannerDetailVo().setP_atch_file_id(atchFileId);
								
								//기존 파일 아이디에 추가
								/*int cnt = fileMngService.getMaxFileSN(fvo);
								List<FileVo> _result = fileUtil.parseFileInf(tmpfiles, "BN_",cnt, bannerVo.getEngBannerDetailVo().getP_atch_file_id(), commonProp.getProperty("bn.file.path"));
								fileMngService.updateFileInfs(_result);*/
							}else{
					    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.getEngBannerDetailVo().setP_atch_file_id(atchFileId);
							}
				    	}else{
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getEngBannerDetailVo().getP_atch_file_id());
							List<FileVo> fileVoList = fileMngService.selectFileInfs(fvo);
							if (fileVoList.size() == 0) {
								bannerVo.getEngBannerDetailVo().setP_atch_file_id(null);
							}
				    	}

				    	
				    	bannerVo.getEngBannerDetailVo().setIn_mem_id(loginVo.getMem_id());
			    	}
			    	tmpAttFiles = multiRequest.getFiles("attEngMobileFile");
			    	tmpfiles = new HashMap<String, MultipartFile>();
			    	// 앱파일 등록
			    	chkcnt = 0;
			    	chkFlag = true;
			    	for(int i = 0;i< tmpAttFiles.size();i++){
			    		file = tmpAttFiles.get(i);
			    		if(file != null)
			    		if(!file.isEmpty()){    		
			    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
				    			model.addAttribute("msg", "alterBack");
				    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
				    			chkFlag = false;	
				    		}
				    		tmpfiles.put(file.getName() + i, file);
				    		chkcnt ++;
			    		}
			    	}
			    	if(chkFlag){
			    		if(chkcnt > 0){
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getEngBannerDetailVo().getM_atch_file_id());
							List<FileVo> chkList = fileMngService.selectFileInfs(fvo);
							if(chkList.size() > 0){
								// 기존에 등록 된게 있으면
								// 단일 등록일 시는 무조건 삭제 후 등록 한다.
								fileMngService.deleteFileInfs(chkList);
								result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.getEngBannerDetailVo().setM_atch_file_id(atchFileId);
								
								//기존 파일 아이디에 추가
								/*int cnt = fileMngService.getMaxFileSN(fvo);
								List<FileVo> _result = fileUtil.parseFileInf(tmpfiles, "BN_",cnt, bannerVo.getEngBannerDetailVo().getM_atch_file_id(), commonProp.getProperty("bn.file.path"));
								fileMngService.updateFileInfs(_result);*/
							}else{
					    		result = fileUtil.parseFileInf(tmpfiles, "BN_", 0, "", commonProp.getProperty("bn.file.path"));
					    		atchFileId = fileMngService.insertFileInfs(result);
					    		bannerVo.getEngBannerDetailVo().setM_atch_file_id(atchFileId);
							}
				    	}else{
				    		FileVo fvo = new FileVo();
							fvo.setAtch_file_id(bannerVo.getEngBannerDetailVo().getM_atch_file_id());
							List<FileVo> fileVoList = fileMngService.selectFileInfs(fvo);
							if (fileVoList.size() == 0) {
								bannerVo.getEngBannerDetailVo().setM_atch_file_id(null);
							}
				    	}
			    	}
		    	}
		    	bannerService.updateBanner(bannerVo);
		    	
		    	model.addAttribute("msg", "alterloc");

		    	if(bannerVo.getBt_position().equals(Constants.BANNER_TOP_POSITION)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/banner_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_MIDDLE_POSITION)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/banner_bottom_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_SPONSER_POSITION)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/banner_sponser_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_GSTARTV_EXHIBITOR)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/gstar/gstar_exhibitor_banner_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_GSTARTV_GAME_CONTENTS)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/gstar/gstar_game_contents_list.do");
		    	}else if(bannerVo.getBt_position().equals(Constants.BANNER_GSTARTV_PARTNER_SPONSOR)){
		    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/gstar/gstar_partner_sponsor_list.do");
		    	}
	   			model.addAttribute("altmsg", "배너를 등록 하였습니다.");
			}
    	}catch (Exception e){
    		model.addAttribute("msg", "alterBack");
       		model.addAttribute("altmsg", "배너 등록에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}
       		return ren_view;			
	}
	
	@RequestMapping(value="/banner/banner_sort.json", method = RequestMethod.POST)
	public String banner_sort(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		// 파라미터 세팅
		try {
			// 삭제자 아이디 
			LoginVo loginVo = (LoginVo)request.getSession().getAttribute(Constants.LOGIN_VO);
			// 삭제할 뉴스 아이디
			int bt_idx = ServletRequestUtils.getIntParameter(request, "bt_idx", 0);
			String sort = ServletRequestUtils.getStringParameter(request, "sort", "");
			
			BannerDetailVo bannerDetailVo = bannerService.selectBanner(bt_idx);
			if(sort.equals("down")){
				bannerDetailVo.setBt_order(bannerDetailVo.getBt_order() + 1);
			}else{
				bannerDetailVo.setBt_order(bannerDetailVo.getBt_order() - 1);
			}
			bannerService.soartUpDate(bannerDetailVo, sort);
			
			model.addAttribute("result", "success");
			model.addAttribute("resMsg", "");
		}catch (Exception e){
			model.addAttribute("result", "fail");
			model.addAttribute("resMsg", "삭제에 실패 하였습니다.");
		}
		return "banner/banner_sort";
	}	
	@RequestMapping(value="/banner/banner_delete.json", method = RequestMethod.POST)
	public String banner_delete(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call news_delete [=================");
		// 파라미터 세팅
		try {
			// 삭제자 아이디 
			LoginVo loginVo = (LoginVo)request.getSession().getAttribute(Constants.ADMIN_VO);
			List<BannerDetailVo> delList = new ArrayList<BannerDetailVo>();
			String[] del_id_arr = ServletRequestUtils.getStringParameters(request, "del_id");
			for(int i = 0; i < del_id_arr.length; i ++){
				BannerDetailVo delItem = new BannerDetailVo();
			//	delItem.setBd_del_id(loginVo.getStaff_id());
				delItem.setBt_idx(Integer.parseInt(del_id_arr[i]));
				delList.add(delItem);
			}
			bannerService.deleteBanner(delList);
			
			
			model.addAttribute("result", "success");
			model.addAttribute("resMsg", "");
		}catch (Exception e){
    		model.addAttribute("msg", "alterBack");
       		model.addAttribute("altmsg", commonProp.getProperty("comm.delete.fail"));
		}
		logger.debug("=================] Call code.response.success [================= {}", commonProp.get("code.response.success"));
		return "common/message";
	}
	@RequestMapping("/banner/sns_list.do")
	public String sns_list(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		LoginVo loginVo = (LoginVo) request.getSession().getAttribute(Constants.LOGIN_VO);
		// 파라미터 세팅
		SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
    	
		// 검색조건 추가
		String searchDel = ServletRequestUtils.getStringParameter(request, "searchDel", "N");
		searchVo.setSearchDel(searchDel);
		model.addAttribute("searchDel", searchDel);
		this.paramMap.put("searchDel",searchDel);
		/*searchVo.setSearchSite(Constants.SITE_GUBUN_KOR);*/
		
		int totalCount = 0;
    	// 페이징에 추가 파라미터를 전달 한다.
    	List<SnsNowDetailVo> snsList = bannerService.selectSnsList(searchVo);
    	totalCount = bannerService.selectSnsListTotal(searchVo);
    	PageHolder pageholder = setPageNumRow(request, totalCount);
		
		
    	this.setParamToModeSearch(model, searchVo);
    	
    	
    	model.addAttribute("pageholder", pageholder);
    	model.addAttribute("totalCount", totalCount);
		model.addAttribute("itemList", snsList);

    	
		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 6);

		
		return "admincenter/banner/sns_list";
	}
	@RequestMapping(value="/banner/sns_data.json", method = RequestMethod.POST)
	public String sns_data(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		// 파라미터 세팅
		try {
			// 삭제자 아이디 
			LoginVo loginVo = (LoginVo)request.getSession().getAttribute(Constants.LOGIN_VO);
			String URL = ServletRequestUtils.getStringParameter(request, "sns_url", "");
			String ENG_URL = ServletRequestUtils.getStringParameter(request, "eng_sns_url", "");
			SnsNowDetailVo snsNowDetailVo = new SnsNowDetailVo();
			snsNowDetailVo.setSns_url(URL);
			snsNowDetailVo.setSns_url(ENG_URL);
			//국문
			Document doc = Jsoup.connect(URL).get();
			Elements title = doc.select("meta[property=\"og:title\"]");
			Elements image = doc.select("meta[property=\"og:image\"]");
			System.out.println("title = "+ title);
			System.out.println("image = "+ image);
			if(title.isEmpty() && image.isEmpty()) {
				System.out.println("isEmpty!");
				Elements iframe_src = doc.select("iframe[src]");
				System.out.println("iframe_src="+iframe_src);
				String new_url = "https://blog.naver.com" + iframe_src.attr("src");
				System.out.println("new_url="+new_url);
				doc = Jsoup.connect(new_url).get();
				title = doc.select("meta[property=\"og:title\"]");
				System.out.println("title="+title);
				image = doc.select("meta[property=\"og:image\"]");
				System.out.println("image="+image);
			}
			if(title.isEmpty()) {
				Document doc2 = Jsoup.connect(URL).get();
				snsNowDetailVo.setSns_name(doc2.title());
				snsNowDetailVo.setImg_url(image.attr("content"));
			}else {
				snsNowDetailVo.setSns_name(title.attr("content"));
				snsNowDetailVo.setImg_url(image.attr("content"));
			}
			
			//영문
			if(!ENG_URL.equals("")) {
				Document eng_doc = Jsoup.connect(ENG_URL).get();
				Elements eng_image = eng_doc.select("meta[property=\"og:image\"]");
				/* Elements description = doc.select("meta[property=\"og:description\"]"); */
				if(eng_image.isEmpty()) {
					Elements iframe_src = eng_doc.select("iframe[src]");
					String new_url = "https://blog.naver.com" + iframe_src.attr("src");
					eng_doc = Jsoup.connect(new_url).get();
					eng_image = eng_doc.select("meta[property=\"og:image\"]");
				}
				snsNowDetailVo.setEngSnsNowDetailVo(new SnsNowDetailVo());
				if(title.isEmpty()) {
					Document eng_doc2 = Jsoup.connect(ENG_URL).get();
					snsNowDetailVo.getEngSnsNowDetailVo().setSns_name(eng_doc2.title());
					snsNowDetailVo.getEngSnsNowDetailVo().setImg_url(eng_image.attr("content"));
				}else {
					snsNowDetailVo.getEngSnsNowDetailVo().setImg_url(eng_image.attr("content"));
				}
			}
			
			model.addAttribute("snsNowDetailVo",snsNowDetailVo);
			
			model.addAttribute("result", "success");
			model.addAttribute("resMsg", "");
		}catch (Exception e){
			model.addAttribute("result", "fail");
			model.addAttribute("resMsg", "url 데이터 추출에 실패 하였습니다.");
		}
		return "banner/sns_inscr";
	}	
	@RequestMapping(value="/banner/sns_inscr.do", method = RequestMethod.GET)
	public String sns_inscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		this.setParamToModeSearch(model, searchVo);
		
		model.addAttribute("snsNowDetailVo", new SnsNowDetailVo());

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 6);
		
		return "admincenter/banner/sns_inscr";
	}
	
	@RequestMapping(value="/banner/sns_inscr.do", method = RequestMethod.POST)
	public String sns_inscr_proc(final HttpServletRequest request,
    		@ModelAttribute("snsNowDetailVo") SnsNowDetailVo snsNowVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		logger.info("=================] Call news_inscr [=================");
		// 파라미터 세팅
		String ren_view = "common/message";
		try {
			// 등록자 아이디 
			LoginVo loginVo = (LoginVo)request.getSession().getAttribute(Constants.ADMIN_VO);
			
			if(loginVo == null){
	   			model.addAttribute("msg", "alterBack");
	       		model.addAttribute("altmsg", "잘못된 접근 입니다.");
			}else{
			
				int[] site_open_gubun_arr = ServletRequestUtils.getIntParameters(request, "site_open_gubun");
				
				int site_open_gubun = 0;
				for(int i = 0; i < site_open_gubun_arr.length; i++){
					site_open_gubun += site_open_gubun_arr[i];
				}
				snsNowVo.setSite_open_gubun(site_open_gubun);
				snsNowVo.setIn_mem_id(loginVo.getMem_id());

		    	if((site_open_gubun & Constants.SITE_GUBUN_KOR) > 0){
		    		// 국문 체크시
		    		String sns_url = ServletRequestUtils.getStringParameter(request, "sns_url", "");
		    		String img_url = ServletRequestUtils.getStringParameter(request, "img_url", "");
		    		snsNowVo.setSns_url(sns_url);
		    		snsNowVo.setImg_url(img_url);
		    		snsNowVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
		    	}
		    	if((site_open_gubun & Constants.SITE_GUBUN_ENG) > 0){
		    		// 영문도 선택 했다면
		    		snsNowVo.setEngSnsNowDetailVo(new SnsNowDetailVo());
		    		String sns_url = ServletRequestUtils.getStringParameter(request, "eng_sns_url", "");
		    		String img_url = ServletRequestUtils.getStringParameter(request, "eng_img_url", "");
		    		snsNowVo.getEngSnsNowDetailVo().setSns_url(sns_url);
		    		snsNowVo.getEngSnsNowDetailVo().setImg_url(img_url);
		    		snsNowVo.getEngSnsNowDetailVo().setSite_gubun(Constants.SITE_GUBUN_ENG);
		    	}
		    	bannerService.insertSnsNow(snsNowVo);
		    	
		    	model.addAttribute("msg", "alterloc");
		    	model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/sns_list.do");
	   			model.addAttribute("altmsg", "SNS NOW를 등록 하였습니다.");
			}
    	}catch (Exception e){
    		model.addAttribute("msg", "alterBack");
       		model.addAttribute("altmsg", "SNS NOW 등록에 실패 하였습니다.["+ e.toString() +"]");
		}
       		return ren_view;			
	}
	
	@RequestMapping(value="/banner/sns_upscr.do", method = RequestMethod.GET)
	public String sns_upscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call user_list [=================");
		// 파라미터 세팅
    	SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		this.setParamToModeSearch(model, searchVo);
		
		int sns_idx = ServletRequestUtils.getIntParameter(request, "sns_idx", 0);
		SnsNowDetailVo snsNowDetailVo = bannerService.selectSns(sns_idx);
		
		model.addAttribute("snsNowDetailVo", snsNowDetailVo);
		model.addAttribute("snsNowEngVo", snsNowDetailVo.getEngSnsNowDetailVo());

		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 6);
		
		return "admincenter/banner/sns_upscr";
	}
	
	@RequestMapping(value="/banner/sns_upscr.do", method = RequestMethod.POST)
	public String sns_upscr_proc(final MultipartHttpServletRequest multiRequest, HttpServletResponse response, Model model,
			@ModelAttribute("snsNowDetailVo")SnsNowDetailVo snsNowVo,
			BindingResult bindingResult, SessionStatus status)  throws Exception {
		logger.info("=================] Call sns_upscr [=================");
			String ren_view = "common/message";
		try {
			LoginVo loginVo = (LoginVo) multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			snsNowVo.setUp_mem_id(loginVo.getMem_id());
			int sns_idx = ServletRequestUtils.getIntParameter(multiRequest, "sns_idx", 0);
			int[] site_open_gubun_arr = ServletRequestUtils.getIntParameters(multiRequest, "site_open_gubun");
    		
			int site_open_gubun = 0;
			for(int i = 0; i < site_open_gubun_arr.length; i++){
				site_open_gubun += site_open_gubun_arr[i];
			}
			
	    	if((site_open_gubun & Constants.SITE_GUBUN_KOR) > 0){
	    		// 국문 체크시
	    		String sns_url = ServletRequestUtils.getStringParameter(multiRequest, "sns_url", "");
	    		String img_url = ServletRequestUtils.getStringParameter(multiRequest, "img_url", "");
	    		snsNowVo.setSns_url(sns_url);
	    		snsNowVo.setImg_url(img_url);
	    		snsNowVo.setSite_gubun(Constants.SITE_GUBUN_KOR);
	    	}
	    	if((site_open_gubun & Constants.SITE_GUBUN_ENG) > 0){
	    		// 영문도 선택 했다면
	    		snsNowVo.setEngSnsNowDetailVo(new SnsNowDetailVo());
	    		String sns_url = ServletRequestUtils.getStringParameter(multiRequest, "eng_sns_url", "");
	    		String img_url = ServletRequestUtils.getStringParameter(multiRequest, "eng_img_url", "");
	    		snsNowVo.getEngSnsNowDetailVo().setSns_url(sns_url);
	    		snsNowVo.getEngSnsNowDetailVo().setImg_url(img_url);
	    		snsNowVo.getEngSnsNowDetailVo().setSite_gubun(Constants.SITE_GUBUN_ENG);
	    	}
			
			snsNowVo.setSite_open_gubun(site_open_gubun);
			snsNowVo.setSns_idx(sns_idx);
			bannerService.updateSnsNow(snsNowVo);	
			model.addAttribute("msg", "alterloc");
    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/sns_upscr.do?sns_idx="+snsNowVo.getSns_idx());
			model.addAttribute("altmsg", "SNS NOW를 수정 하였습니다.");
		
		}catch (Exception e){
			model.addAttribute("msg", "alterBack");
			model.addAttribute("altmsg", "SNS NOW를 수정에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}
		return ren_view;	
	}
	
	@RequestMapping(value="/banner/sns_delete.json", method = RequestMethod.POST)
	public String sns_delete(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		logger.info("=================] Call news_delete [=================");
		// 파라미터 세팅
		try {
			// 삭제자 아이디 
			LoginVo loginVo = (LoginVo)request.getSession().getAttribute(Constants.ADMIN_VO);
			List<SnsNowDetailVo> delList = new ArrayList<SnsNowDetailVo>();
			String[] del_id_arr = ServletRequestUtils.getStringParameters(request, "del_id");
			for(int i = 0; i < del_id_arr.length; i ++){
				SnsNowDetailVo delItem = new SnsNowDetailVo();
			//	delItem.setBd_del_id(loginVo.getStaff_id());
				delItem.setSns_idx(Integer.parseInt(del_id_arr[i]));
				delList.add(delItem);
			}
			bannerService.deleteSnsNow(delList);
			
			
			model.addAttribute("result", "success");
			model.addAttribute("resMsg", "");
		}catch (Exception e){
    		model.addAttribute("msg", "alterBack");
       		model.addAttribute("altmsg", commonProp.getProperty("comm.delete.fail"));
		}
		logger.debug("=================] Call code.response.success [================= {}", commonProp.get("code.response.success"));
		return "common/message";
	}
	
	@RequestMapping(value="/banner/att_banner_upscr.do", method = RequestMethod.GET)
	public String att_banner_upscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		
		String ebt_position = ServletRequestUtils.getStringParameter(request, "ebt_position", "A");
		AttBannerVo attBannerVo = bannerService.selectAttBanner(ebt_position);
		
		model.addAttribute("attBannerVo", attBannerVo);
		
		model.addAttribute("ebt_position", ebt_position);
		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 2);
		return "admincenter/banner/att_banner_upscr";
	}
	
	@RequestMapping(value="/banner/att_banner_upscr.do", method = RequestMethod.POST)
	public String att_banner_upscr_proc(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("AttBannerVo") AttBannerVo attBannerVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		
		String ren_view = "common/message";
		
		try {
			LoginVo loginVo = (LoginVo) multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			attBannerVo.setUp_mem_id(loginVo.getMem_id());
			
			bannerService.insertAttBanner(attBannerVo);	
			model.addAttribute("msg", "alterloc");
    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/att_banner_upscr.do?ebt_position="+attBannerVo.getEbt_position());
			model.addAttribute("altmsg", "이벤트 배너를 등록 하였습니다.");
		
		}catch (Exception e){
			model.addAttribute("msg", "alterBack");
			model.addAttribute("altmsg", "이벤트 배너 등록에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}

		return ren_view;			
	}
	
	
	@RequestMapping(value="/banner/att_banner_delete.do", method = RequestMethod.POST)
	public String att_banner_delete(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("AttBannerVo") AttBannerVo attBannerVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		
		String ren_view = "common/message";
		
		try {
			LoginVo loginVo = (LoginVo) multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			attBannerVo.setUp_mem_id(loginVo.getMem_id());
			
			bannerService.deleteAttBanner(attBannerVo);	
			model.addAttribute("msg", "alterloc");
    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/att_banner_upscr.do?ebt_position="+attBannerVo.getEbt_position());
			model.addAttribute("altmsg", "이벤트 배너가 초기화되었습니다.");
		
		}catch (Exception e){
			model.addAttribute("msg", "alterBack");
			model.addAttribute("altmsg", "이벤트 배너 초기화에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}

		return ren_view;			
	}
	
	
	
	
	@RequestMapping(value="/banner/attex_banner_upscr.do", method = RequestMethod.GET)
	public String attex_banner_upscr(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		
		String eht_position = ServletRequestUtils.getStringParameter(request, "eht_position", "A");
		AttExBannerVo attExBannerVo = bannerService.selectAttExBanner(eht_position);
		if(attExBannerVo != null){
			String atchFileId = attExBannerVo.getP_atch_file_id();
			String min_img_path = commonProp.getProperty("bn.file.url.path");
			FileVo fvo = new FileVo();
			fvo.setAtch_file_id(atchFileId);
			fvo.setFile_sn("0");
			FileVo fileVo = fileMngService.selectFileInf(fvo);
			if(fileVo != null){
					fileVo.setFile_src(min_img_path + "/" + fileVo.getStre_file_nm());
			}
			attExBannerVo.setpFileVo(fileVo);
			
			
			String atchMFileId = attExBannerVo.getM_atch_file_id();
			FileVo mfvo = new FileVo();
			mfvo.setAtch_file_id(atchMFileId);
			mfvo.setFile_sn("0");
			FileVo mFileVo = fileMngService.selectFileInf(mfvo);
			if(mFileVo != null){
				mFileVo.setFile_src(min_img_path + "/" + mFileVo.getStre_file_nm());
			}
			attExBannerVo.setmFileVo(mFileVo);
		}
		model.addAttribute("attExBannerVo", attExBannerVo);
		
		model.addAttribute("eht_position", eht_position);
		model.addAttribute("menuDept", menuDept);
		model.addAttribute("menuSubDept", 4);
		return "admincenter/banner/attex_banner_upscr";
	}
	
	@RequestMapping(value="/banner/attex_banner_upscr.do", method = RequestMethod.POST)
	public String attex_banner_upscr_proc(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("AttExBannerVo") AttExBannerVo attExBannerVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		
		String ren_view = "common/message";
		
		try {
			LoginVo loginVo = (LoginVo) multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			attExBannerVo.setMem_id(loginVo.getMem_id());
			
			MultipartFile file = null;
			List<FileVo> result = null;
			String atchFileId = "";
			List<MultipartFile> tmpAttFiles = null;
			Map<String, MultipartFile> tmpfiles = null;
	    	int chkcnt = 0;
	    	boolean chkFlag = true;
	    	
	    	tmpAttFiles = multiRequest.getFiles("attPcFile");
	    	tmpfiles = new HashMap<String, MultipartFile>();
	    	// 앱파일 등록
	    	chkcnt = 0;
	    	chkFlag = true;
	    	for(int i = 0;i< tmpAttFiles.size();i++){
	    		file = tmpAttFiles.get(i);
	    		if(file != null)
	    		if(!file.isEmpty()){    		
	    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
		    			model.addAttribute("msg", "alterBack");
		    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
		    			chkFlag = false;	
		    		}
		    		tmpfiles.put(file.getName() + i, file);
		    		chkcnt ++;
	    		}
	    	}
	    	if(chkFlag){
		    	if(chkcnt > 0){
		    		
		    		FileVo fvo = new FileVo();
					fvo.setAtch_file_id(attExBannerVo.getP_atch_file_id());
					List<FileVo> chkList = fileMngService.selectFileInfs(fvo);
					if(chkList.size() > 0){
						// 기존에 등록 된게 있으면
						
						// 단일 등록일 시는 무조건 삭제 후 등록 한다.
						fileMngService.deleteFileInfs(chkList);
			    		result = fileUtil.parseFileInf(tmpfiles, "EXBN_", 0, "", commonProp.getProperty("bn.file.path"));
			    		atchFileId = fileMngService.insertFileInfs(result);
			    		attExBannerVo.setP_atch_file_id(atchFileId);
						
						//기존 파일 아이디에 추가
						/*int cnt = fileMngService.getMaxFileSN(fvo);
						List<FileVo> _result = fileUtil.parseFileInf(tmpfiles, "BN_",cnt, bannerVo.getP_atch_file_id(), commonProp.getProperty("bn.file.path"));
						fileMngService.updateFileInfs(_result);*/
					}else{
			    		result = fileUtil.parseFileInf(tmpfiles, "EXBN_", 0, "", commonProp.getProperty("bn.file.path"));
			    		atchFileId = fileMngService.insertFileInfs(result);
			    		attExBannerVo.setP_atch_file_id(atchFileId);
					}
		    	}else{
		    		FileVo fvo = new FileVo();
					fvo.setAtch_file_id(attExBannerVo.getP_atch_file_id());
					List<FileVo> fileVoList = fileMngService.selectFileInfs(fvo);
					if (fileVoList.size() == 0) {
						attExBannerVo.setP_atch_file_id(null);
					}
		    	}
	    	}
	    	
	    	tmpAttFiles = multiRequest.getFiles("attMobileFile");
	    	tmpfiles = new HashMap<String, MultipartFile>();
	    	// 앱파일 등록
	    	chkcnt = 0;
	    	chkFlag = true;
	    	for(int i = 0;i< tmpAttFiles.size();i++){
	    		file = tmpAttFiles.get(i);
	    		if(file != null)
	    		if(!file.isEmpty()){    		
	    			if(!fileUtil.chkFileExt(file, Constants.allowExtFileImg)){
		    			model.addAttribute("msg", "alterBack");
		    			model.addAttribute("altmsg", "이미지 파일만 가능합니다.");
		    			chkFlag = false;	
		    		}
		    		tmpfiles.put(file.getName() + i, file);
		    		chkcnt ++;
	    		}
	    	}
	    	if(chkFlag){
		    	if(chkcnt > 0){
		    		
		    		FileVo fvo = new FileVo();
					fvo.setAtch_file_id(attExBannerVo.getM_atch_file_id());
					List<FileVo> chkList = fileMngService.selectFileInfs(fvo);
					if(chkList.size() > 0){
						// 기존에 등록 된게 있으면
						
						// 단일 등록일 시는 무조건 삭제 후 등록 한다.
						fileMngService.deleteFileInfs(chkList);
			    		result = fileUtil.parseFileInf(tmpfiles, "EXBN_", 0, "", commonProp.getProperty("bn.file.path"));
			    		atchFileId = fileMngService.insertFileInfs(result);
			    		attExBannerVo.setM_atch_file_id(atchFileId);
						
						//기존 파일 아이디에 추가
						/*int cnt = fileMngService.getMaxFileSN(fvo);
						List<FileVo> _result = fileUtil.parseFileInf(tmpfiles, "BN_",cnt, bannerVo.getP_atch_file_id(), commonProp.getProperty("bn.file.path"));
						fileMngService.updateFileInfs(_result);*/
					}else{
			    		result = fileUtil.parseFileInf(tmpfiles, "EXBN_", 0, "", commonProp.getProperty("bn.file.path"));
			    		atchFileId = fileMngService.insertFileInfs(result);
			    		attExBannerVo.setM_atch_file_id(atchFileId);
					}
		    	}else{
		    		FileVo fvo = new FileVo();
					fvo.setAtch_file_id(attExBannerVo.getM_atch_file_id());
					List<FileVo> fileVoList = fileMngService.selectFileInfs(fvo);
					if (fileVoList.size() == 0) {
						attExBannerVo.setM_atch_file_id(null);
					}
		    	}
	    	}
			
			
			
			bannerService.insertExAttBanner(attExBannerVo);	
			
			model.addAttribute("msg", "alterloc");
    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/attex_banner_upscr.do?eht_position="+attExBannerVo.getEht_position());
 			model.addAttribute("altmsg", "Exhibitor 배너를 등록 하였습니다.");
		
		}catch (Exception e){
			model.addAttribute("msg", "alterBack");
			model.addAttribute("altmsg", "Exhibitor 배너 등록에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}

		return ren_view;			
	}
	
	
	
	@RequestMapping(value="/banner/attex_banner_delete.do", method = RequestMethod.POST)
	public String attex_banner_delete(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("AttExBannerVo") AttExBannerVo attExBannerVo,
    		BindingResult bindingResult, 
    		Model model,
            SessionStatus status)  throws Exception {
		
		String ren_view = "common/message";
		
		try {
			LoginVo loginVo = (LoginVo) multiRequest.getSession().getAttribute(Constants.ADMIN_VO);
			attExBannerVo.setMem_id(loginVo.getMem_id());
			
			bannerService.deleteExAttBanner(attExBannerVo);	
			
			model.addAttribute("msg", "alterloc");
    		model.addAttribute("locurl", Constants.ADMIN_CONTEXT + "/banner/attex_banner_upscr.do?eht_position="+attExBannerVo.getEht_position());
 			model.addAttribute("altmsg", "Exhibitor 배너를 초기화 하였습니다.");
		
		}catch (Exception e){
			model.addAttribute("msg", "alterBack");
			model.addAttribute("altmsg", "Exhibitor 배너 초기화에 실패 하였습니다.["+ e.toString() +"]");
		}finally{
			  status.setComplete();
		}

		return ren_view;			
	}
	
	
	@RequestMapping("/banner/popup/event_com_list.do")
	public String event_com_list(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		
		// 파라미터 세팅
		SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		String searchKind = ServletRequestUtils.getStringParameter(request, "searchKind", "");
    	searchVo.setSearchKind(searchKind);
		model.addAttribute("searchKind", searchKind);
		this.paramMap.put("searchKind",searchKind);
		
		int totalCount = 0;
		
    	// 페이징에 추가 파라미터를 전달 한다.
    	List<GstarMhEventVo> bannerList = bannerService.SelectEventComList(searchVo); 
    	totalCount = bannerService.SelectEventComListTotal(searchVo);
    	PageHolder pageholder = setPageNumRow(request, totalCount);
		
		
    	this.setParamToModeSearch(model, searchVo);
    	
    	
    	model.addAttribute("pageholder", pageholder);
    	model.addAttribute("totalCount", totalCount);
		model.addAttribute("itemList", bannerList);

		
		return "admincenter/banner/popup/event_com_list";
	}
	
	@RequestMapping("/banner/popup/ex_com_list.do")
	public String ex_com_list(HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {
		
		// 파라미터 세팅
		SearchVo searchVo = this.getSearch(request);
		this.getModel(request, model);
		
		String searchKind = ServletRequestUtils.getStringParameter(request, "searchKind", "C");
    	searchVo.setSearchKind(searchKind);
		model.addAttribute("searchKind", searchKind);
		this.paramMap.put("searchKind",searchKind);
		
		int totalCount = 0;
		
    	// 페이징에 추가 파라미터를 전달 한다.
    	List<GstarMhEventVo> bannerList = bannerService.SelectExComList(searchVo); 
    	totalCount = bannerService.SelectExComListTotal(searchVo);
    	PageHolder pageholder = setPageNumRow(request, totalCount);
		
		
    	this.setParamToModeSearch(model, searchVo);
    	
    	
    	model.addAttribute("pageholder", pageholder);
    	model.addAttribute("totalCount", totalCount);
		model.addAttribute("itemList", bannerList);

		
		return "admincenter/banner/popup/ex_com_list";
	}
	
	
	
}
