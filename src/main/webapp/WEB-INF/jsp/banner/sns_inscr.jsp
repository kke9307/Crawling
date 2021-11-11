<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/WEB-INF/tlds/jsoft.tld" prefix="jsoft"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

$(document).ready(function(){
	// 조회 버튼 클릭시
	$("#bt_s_date").datepicker({
			showOn:"both", 
			 dateFormat: 'yy-mm-dd',
			buttonImage: "/_admst/images/icon_calendar.png",
			onClose:function( selectedDate ) {                
		         $("#bt_e_date").datepicker( "option", "minDate", selectedDate );            
		      }    
	});
	$("#bt_e_date").datepicker({
		showOn:"both", 
		 dateFormat: 'yy-mm-dd',
		 buttonImage: "/_admst/images/icon_calendar.png",
		onClose:function( selectedDate ) {                
	         $( "#bt_s_date").datepicker( "option", "maxDate", selectedDate );            
	      }    
	});	
	
	$("a[id^=tab_]").on("click", function(){
		var lan = $(this).attr("id").replace("tab_", "");
		if(lan == 'kor'){
			$("#div_eng").hide();
			$("#div_kor").show();
			$("#tab_li_kor").addClass("on");
			$("#tab_li_eng").removeClass("on");
		}else{
			$("#div_eng").show();
			$("#div_kor").hide();
			$("#tab_li_eng").addClass("on");
			$("#tab_li_kor").removeClass("on");
		}
	});
	
	$("#btn_sns").on("click", function(){
		if(confirm("url 데이터를 추출하시겠습니까?")){
			if(!fnIsValidRequired(frm.sns_url, "URL")){return false;}
			else{fnDoCallBackAjax('<spring:message code="admin.context" />/banner/sns_data.json', $("#frm").serialize(), fnRltdKorSnsSuccess);}
		}else{
			return false;
		}
	});
	$("#eng_btn_sns").on("click", function(){
		if(confirm("url 데이터를 추출하시겠습니까?")){
			if(!fnIsValidRequired(frm.eng_sns_url, "URL")){return false;}
			else{fnDoCallBackAjax('<spring:message code="admin.context" />/banner/sns_data.json', $("#frm").serialize(), fnRltdEngSnsSuccess);}
		}else{
			return false;
		}
	});
	
	$("#btn_insert").on("click", function(){
		var frm = document.frm;
		if(!fnIsValidRequired(frm.sns_url, "배너명")){return false;}
		if ($("#site_open_gubun_2").is(":checked")) {
			if(!fnIsValidRequired(frm.sns_url, "링크 url")){
				$("#div_eng").hide();
				$("#div_kor").show();
				$("#tab_li_kor").addClass("on");
				$("#tab_li_eng").removeClass("on");
				return false;
			}
		}
		if ($("#site_open_gubun_4").is(":checked")) {
			if(!fnIsValidRequired(frm.eng_sns_url, "링크 url")){
				$("#div_eng").show();
				$("#div_kor").hide();
				$("#tab_li_eng").addClass("on");
				$("#tab_li_kor").removeClass("on");
				return false;
			} 
		}
		var sns_name = $("#sns_name").val();
  		var exp = /(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff]|[\u0023-\u0039]\ufe0f?\u20e3|\u3299|\u3297|\u303d|\u3030|\u24c2|\ud83c[\udd70-\udd71]|\ud83c[\udd7e-\udd7f]|\ud83c\udd8e|\ud83c[\udd91-\udd9a]|\ud83c[\udde6-\uddff]|\ud83c[\ude01-\ude02]|\ud83c\ude1a|\ud83c\ude2f|\ud83c[\ude32-\ude3a]|\ud83c[\ude50-\ude51]|\u203c|\u2049|[\u25aa-\u25ab]|\u25b6|\u25c0|[\u25fb-\u25fe]|\u00a9|\u00ae|\u2122|\u2139|\ud83c\udc04|[\u2600-\u26FF]|\u2b05|\u2b06|\u2b07|\u2b1b|\u2b1c|\u2b50|\u2b55|\u231a|\u231b|\u2328|\u23cf|[\u23e9-\u23f3]|[\u23f8-\u23fa]|\ud83c\udccf|\u2934|\u2935|[\u2190-\u21ff])/g; 
		if(sns_name.search(exp)){
			removeEmojis(sns_name);
		} 
		$("#frm").submit();
	});
}); 

function removeEmojis (string) {
	  var regex = /(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff]|[\u0023-\u0039]\ufe0f?\u20e3|\u3299|\u3297|\u303d|\u3030|\u24c2|\ud83c[\udd70-\udd71]|\ud83c[\udd7e-\udd7f]|\ud83c\udd8e|\ud83c[\udd91-\udd9a]|\ud83c[\udde6-\uddff]|\ud83c[\ude01-\ude02]|\ud83c\ude1a|\ud83c\ude2f|\ud83c[\ude32-\ude3a]|\ud83c[\ude50-\ude51]|\u203c|\u2049|[\u25aa-\u25ab]|\u25b6|\u25c0|[\u25fb-\u25fe]|\u00a9|\u00ae|\u2122|\u2139|\ud83c\udc04|[\u2600-\u26FF]|\u2b05|\u2b06|\u2b07|\u2b1b|\u2b1c|\u2b50|\u2b55|\u231a|\u231b|\u2328|\u23cf|[\u23e9-\u23f3]|[\u23f8-\u23fa]|\ud83c\udccf|\u2934|\u2935|[\u2190-\u21ff])/g;
	  string = string.replace(regex, '');
	  $("#sns_name").val(string);
}
function fnRltdKorSnsSuccess(data){
	if(data.result == 'success'){
		var snsVo = data.snsNowDetailVo;
		//국문
		$("#sns_name").val(snsVo.sns_name);
		$("#pImgPreview").attr("src", snsVo.img_url); 
		$("#img_url").val(snsVo.img_url); 
		$("#pImgPreview").attr("width", 200); 
		$("#pImgPreview").attr("alt", snsVo.sns_name); 
/* 		$("#mImgPreview").attr("src", snsVo.sns_url); 
		$("#mImgURL").val(snsVo.sns_url); 
		$("#mImgPreview").attr("width", 200); 
		$("#mImgPreview").attr("alt", snsVo.sns_name);  */
	}else{
		alert(data.resMsg);	
		$("#page").val("1");
	}
}
function fnRltdEngSnsSuccess(data){
	if(data.result == 'success'){
		var snsVo = data.snsNowDetailVo.engSnsNowDetailVo;
		//영문 
		$("#epImgPreview").attr("src", snsVo.img_url);
		$("#eng_img_url").val(snsVo.img_url); 
		$("#epImgPreview").attr("width", 200); 
		$("#epImgPreview").attr("alt", snsVo.sns_name); 
/* 		$("#emImgPreview").attr("src", snsVo.sns_url);
		$("#emImgURL").val(snsVo.sns_url); 
		$("#emImgPreview").attr("width", 200); 
		$("#emImgPreview").attr("alt", snsVo.sns_name);  */
		$("#epage").val("1");
	}else{
		alert(data.resMsg);	
		$("#page").val("1");
	}
}
</script>

<a href="#" class="lnb-control">메뉴컨트롤</a>
<div class="content-area"><!-- 우측 본문영역 -->
	<div class="breadcrumbs"><span class="home">HOME</span> &gt; 배너/팝업 &gt; <strong>SNS NOW 등록</strong></div>
	<h2>SNS NOW 등록</h2>
	<form name="frm" id="frm" action="<spring:message code="admin.context" />/banner/sns_inscr.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="page" id="page" value="${page}" />
	<h3 class="sub-title">기본 정보</h3>
	<div class="btn-area-right">
		<span class="warning">*표는 필수 입력 항목입니다.</span>
	</div>
		<div class="regist">
		<table>
			<tbody>
				<tr>
					<th style="width:120px"><span>*</span>노출여부</th>
					<td colspan="3">
						<input type="checkbox" name="site_open_gubun" id="site_open_gubun_2" value="2" checked="checked" />&nbsp;국문
						<input type="checkbox" name="site_open_gubun" id="site_open_gubun_4" value="4" />&nbsp;영문
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>배너명   ${snsNowDetailVo.sns_name}</th>
					<td colspan="3">
						<input type="text" name="sns_name" id="sns_name" value="" style="width:90%"/>
						
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<ul class="tab">
			<li class="on" id="tab_li_kor"><a href="#" id="tab_kor">국문</a> </li>
			<li id="tab_li_eng"><a href="#" id="tab_eng">영문</a> </li>
		</ul>
		<div id="div_kor" class="regist">
		<table>
			<tbody>
				<tr>
					<th style="width:120px"><span>*</span>배너이미지(PC)</th>
					<td colspan="3">
							<input type="file" name="img_url" id="img_url" value="" style="width:40%" />&nbsp;
							(이미지사이즈 : 2500*587 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>링크 URL</th>
					<td colspan="3">
						<input type="text" name="sns_url" id="sns_url" value="" style="width:50%" />
						<a href="#" class="button blue" id="btn_sns"><span>링크 데이터 추출</span></a>
					</td>
				</tr>				
			</tbody>
		</table>
		</div>
		<div id="div_eng" class="regist" style="display:none">
		<table >
			<tbody>
				<tr>
					<th style="width:120px"><span>*</span>배너이미지(PC)</th>
					<td colspan="3">
						<input type="text" name="eng_img_url" id="eng_img_url" style="width:50%"/><br><br>	
						<img src="" width="" alt="" id="epImgPreview">	
					</td>
				</tr>
<!-- 				<tr>
					<th style="width:120px"><span>*</span>배너이미지(MOBILE)</th>
					<td colspan="3">
						<input type="text" name="emImgURL" id="emImgURL" style="width:50%"/><br><br>
						<img src="" width="" alt="" id="emImgPreview">
					</td>
				</tr>			 -->	
				<tr>
					<th style="width:120px"><span>*</span>링크 URL</th>
					<td colspan="3">
						<input type="text" name="eng_sns_url" id="eng_sns_url" value="" style="width:50%" />
						<a href="#" class="button blue" id="eng_btn_sns"><span>링크 데이터 추출</span></a>
					</td>
				</tr>				
			</tbody>
		</table>
		</div>
	</form>
	<div class="btn-area">
		<a href="#" class="button blue" id="btn_insert"><span>저장</span></a>
		<a href="<spring:message code="admin.context" />/banner/sns_list.do" class="button big"><span>목록</span></a>
	</div>
</div><!-- //우측 본문영역 -->