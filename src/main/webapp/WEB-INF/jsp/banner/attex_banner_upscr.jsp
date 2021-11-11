<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/jsoft.tld" prefix="jsoft"%>
<%@ taglib uri="/WEB-INF/tlds/jsoftFunc.tld" prefix="jsftFunc"%>
<script type="text/javascript" src="/resources/plugins/smartPop/jquery.smartPop.js"></script>
<link rel="stylesheet" href="/resources/plugins/smartPop/jquery.smartPop.css" />

<script type="text/javascript">
$(document).ready(function(){
	
	$("#btn_insert").on("click", function(){
		
		var frm = document.frm;
		
		if($("#gft_idx").val() == ""){
			alert('참가사를 먼저 선택해주세요.');
			$("#btn_popup").click();
			return false;
		} 
		
		<c:if test='${attExBannerVo.p_atch_file_id eq null}'>
		if(!fnIsValidRequired(frm.attPcFile, "배너 이미지 pc")){
			return false;
		}
		</c:if>
		<c:if test='${attExBannerVo.m_atch_file_id eq null}'>
		if(!fnIsValidRequired(frm.attMobileFile, "배너 이미지 mobile")){
			return false;
		}
		</c:if>
		
		var frm = document.frm;
		frm.action = "<spring:message code="admin.context" />/banner/attex_banner_upscr.do";
		if(confirm('등록하시겠습니까?')){
			$("#frm").submit();	
		}
		
	});
	
	
	$("#btn_delete").on("click", function(){
		
		var frm = document.frm;
		
		
		frm.action = "<spring:message code="admin.context" />/banner/attex_banner_delete.do";
		if(confirm('초기화 하시겠습니까?')){
			$("#frm").submit();	
		}
		
	});
	
	$("#btn_popup").on("click", function(){
		$.smartPop.open({title: '참가사 선택', width: '45%', height:200, url: '<spring:message code="admin.context" />/banner/popup/ex_com_list.do', padding : 0, border : 2, log : false });
	});
	
	// 파일 삭제 버튼 클릭시
	$("a[id^=btn_file_del_]").click(function(){
		var del_id_arr = $(this).attr("id").replace("btn_file_del_", "").split('_');
		if(confirm("삭제 하시겠습니까?")){
			fnDoCallBackAjax("<c:url value='/file/file_delete.json'/>", "atch_file_id=" + del_id_arr[0] + "&file_sn=" + del_id_arr[1] , fnRltdFileDelSuccess);
		}else{
			return false;
		}
		return false;
	});
	
	
	
	// 삭제 버튼 클릭시
/* 	$("#btn_delete").click(function(){
		
		if(confirm("배너를 삭제 하시겠습니까?")){
			fnDoCallBackAjax('<spring:message code="admin.context" />/banner/banner_delete.json', "del_id=${bannerVo.bt_idx}", fnRltdDelSuccess);
		}else{
			return false;
		}
	}); */
	
	$("#attPcFile").live('change', function(){ 
		if($("#gft_idx").val() == ""){
			alert('참가사를 먼저 선택해주세요.');
			$("#btn_popup").click();
			return false;
		}
	});
	
}); 


function fn_cominput(param){
	$('textarea').prop('disabled', false);
	$("#gci_kor_cname").html(param.gci_kor_cname);
	$("#gft_idx").val(param.gft_idx);
}

function fnRltdFileDelSuccess(data){
	if(data.result == 'success'){
		alert("삭제하였습니다.");
		location.reload();
	}else{
		alert(data.resMsg);	
	}
}





</script>

<a href="#" class="lnb-control">메뉴컨트롤</a>
<div class="content-area"><!-- 우측 본문영역 -->
	<div class="breadcrumbs"><span class="home">HOME</span> &gt; 배너/팝업 &gt; <strong>EXHIBITOR 배너관리</strong></div>
	<h2>EXHIBITOR 배너관리</h2>
	<form name="frm" id="frm" action="<spring:message code="admin.context" />/banner/attex_banner_upscr.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="gft_idx" id="gft_idx" value="${attExBannerVo.gft_idx}"/>
	<input type="hidden" name="eht_position" id="eht_position" value="${eht_position}"/>
	<input type="hidden" name="p_atch_file_id" id="p_atch_file_id" value="${attExBannerVo.p_atch_file_id}"/>
	<input type="hidden" name="m_atch_file_id" id="m_atch_file_id" value="${attExBannerVo.m_atch_file_id}"/>
	<h3 class="sub-title">기본 정보</h3>
		<ul class="tab">
			<li <c:if test="${eht_position == 'A' || eht_position == ''}">class="on"</c:if> id="tab_li_kor"><a href="?eht_position=A" id="tab_kor">1번째 영역</a></li>
			<li <c:if test="${eht_position == 'B'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=B" id="tab_eng">2번째 영역</a></li>
			<li <c:if test="${eht_position == 'C'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=C" id="tab_eng">3번째 영역</a></li>
			
			<li <c:if test="${eht_position == '1'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=1" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '2'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=2" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '3'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=3" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '4'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=4" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '5'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=5" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '6'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=6" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '7'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=7" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '8'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=8" id="tab_eng">추가 영역</a></li>
			<li <c:if test="${eht_position == '9'}">class="on"</c:if> id="tab_li_eng"><a href="?eht_position=9" id="tab_eng">추가 영역</a></li>
		</ul> 
		<div class="regist">
			<table>
				<tbody>
				<tr>
					<th style="width:120px">노출여부</th>
					<td colspan="3">국문/영문 동시 노출</td>
				</tr>
				</tbody>
			</table>
		</div>
		<h3 class="sub-title" style="margin-top:10px;">이벤트정보</h3>
		<div class="btn-area">
			<a href="javascript:;" class="button blue" id="btn_popup"><span>참가사 불러오기</span></a>
			<a href="javascript:;" class="button red" id="btn_delete"><span>초기화</span></a>		
		</div>
		<div class="btn-area-right">
			<span class="warning">*표는 필수 입력 항목입니다.</span>
		</div>
		
		<div class="regist">
		<table>
			<tbody>
				<tr>
					<th colspan="4" height="48" style="text-align:center;"><strong>국문</strong></th>
				</tr>
				<tr>
					<th style="width:120px">참가사 회사명</th>
					<td colspan="3" id="gci_kor_cname">
							&nbsp;${attExBannerVo.gci_kor_cname}
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>배너이미지(PC)</th>
					<td colspan="3">
							<input type="file" name="attPcFile" id="attPcFile" value="" style="width:40%" />&nbsp;
							(이미지사이즈 : 394*606 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)<br/>
							
							<c:if test="${attExBannerVo.pFileVo != null}">
								<a href="${attExBannerVo.pFileVo.file_src}" target="_blank1">${attExBannerVo.pFileVo.orignl_file_nm}</a>&nbsp;[<a href="#" id="btn_file_del_${attExBannerVo.pFileVo.atch_file_id}_${attExBannerVo.pFileVo.file_sn}">삭제</a>]
							</c:if>
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>배너이미지(MOBILE)</th>
					<td colspan="3">
							<input type="file" name="attMobileFile" id="attMobileFile" value="" style="width:40%" onclick="chkFile();"/>&nbsp;
							(이미지사이즈 : 414*638 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)<br/>
							<c:if test="${attExBannerVo.mFileVo != null}">
								<a href="${attExBannerVo.mFileVo.file_src}" target="_blank2">${attExBannerVo.mFileVo.orignl_file_nm}</a>&nbsp;[<a href="#" id="btn_file_del_${attExBannerVo.mFileVo.atch_file_id}_${attExBannerVo.mFileVo.file_sn}">삭제</a>]
							</c:if>
					</td>
				</tr>					
			</tbody>
		</table>
		</div>
	</form>
	<div class="btn-area">
		<a href="#" class="button blue" id="btn_insert"><span>저장</span></a>
	</div>
</div><!-- //우측 본문영역 -->