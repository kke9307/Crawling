package com.gstar.app.model;

public class SnsNowDetailVo extends SnsNowVo {
	private Integer site_gubun;
	private String site_gubun_str;
	private String sns_url;
	private String img_url;
	
	/* 지스타 TV 카테고리 */
	private String replay_title;
	private String replay_writer;
	private String category;
	
	private SnsNowDetailVo engSnsNowDetailVo;
	
	public String getReplay_title() {
		return replay_title;
	}
	public void setReplay_title(String replay_title) {
		this.replay_title = replay_title;
	}
	public String getReplay_writer() {
		return replay_writer;
	}
	public void setReplay_writer(String replay_writer) {
		this.replay_writer = replay_writer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getSns_url() {
		return sns_url;
	}
	public void setSns_url(String sns_url) {
		this.sns_url = sns_url;
	}
	public SnsNowDetailVo getEngSnsNowDetailVo() {
		return engSnsNowDetailVo;
	}
	public void setEngSnsNowDetailVo(SnsNowDetailVo engSnsNowDetailVo) {
		this.engSnsNowDetailVo = engSnsNowDetailVo;
	}
	public Integer getSite_gubun() {
		return site_gubun;
	}
	public void setSite_gubun(Integer site_gubun) {
		this.site_gubun = site_gubun;
	}
	public String getSite_gubun_str() {
		return site_gubun_str;
	}
	public void setSite_gubun_str(String site_gubun_str) {
		this.site_gubun_str = site_gubun_str;
	}
}
