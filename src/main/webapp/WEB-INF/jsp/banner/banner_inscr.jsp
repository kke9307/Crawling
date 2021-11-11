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
	
	$("#btn_insert").on("click", function(){
		var frm = document.frm;
		var checkCnt = $("input[name=site_open_gubun]:checked").size();
		if(checkCnt == 0){
			alert("노출 여부를 한곳 이상 선택 해 주세요");
			return false;
		}
		if(!fnIsValidRequired(frm.bt_name, "배너명")){return false;}
		if(!fnIsValidRequired(frm.bt_s_date, "노출기간 시작일")){return false;}
		if(!fnIsValidRequired(frm.bt_e_date, "노출기간 종료일")){return false;}
		if ($("#site_open_gubun_2").is(":checked")) {
			if(!fnIsValidRequired(frm.attPcFile, "배너 이미지 pc")){
				$("#div_eng").hide();
				$("#div_kor").show();
				$("#tab_li_kor").addClass("on");
				$("#tab_li_eng").removeClass("on");
				return false;
			}
			if(!fnIsValidRequired(frm.attMobileFile, "배너 이미지 mobile")){
				$("#div_eng").hide();
				$("#div_kor").show();
				$("#tab_li_kor").addClass("on");
				$("#tab_li_eng").removeClass("on");
				return false;
			}
			if(!fnIsValidRequired(frm.btd_url, "링크 url")){
				$("#div_eng").hide();
				$("#div_kor").show();
				$("#tab_li_kor").addClass("on");
				$("#tab_li_eng").removeClass("on");
				return false;
			}
		}
		if ($("#site_open_gubun_4").is(":checked")) {
			if(!fnIsValidRequired(frm.attEngPcFile, "배너 이미지 pc")){
				$("#div_eng").show();
				$("#div_kor").hide();
				$("#tab_li_eng").addClass("on");
				$("#tab_li_kor").removeClass("on");
				return false;
			}
			if(!fnIsValidRequired(frm.attEngMobileFile, "배너 이미지 mobile")){
				$("#div_eng").show();
				$("#div_kor").hide();
				$("#tab_li_eng").addClass("on");
				$("#tab_li_kor").removeClass("on");
				return false;
			}
			if(!fnIsValidRequired(frm.btd_eng_url, "링크 url")){
				$("#div_eng").show();
				$("#div_kor").hide();
				$("#tab_li_eng").addClass("on");
				$("#tab_li_kor").removeClass("on");
				return false;
			}
		}
		$("#frm").submit();
	});
}); 
</script>

<a href="#" class="lnb-control">메뉴컨트롤</a>
<div class="content-area"><!-- 우측 본문영역 -->
	<div class="breadcrumbs"><span class="home">HOME</span> &gt; 배너/팝업 &gt; <strong>메인상단 배너 등록</strong></div>
	<h2>메인상단 배너 등록</h2>
	<form name="frm" id="frm" action="<spring:message code="admin.context" />/banner/banner_inscr.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="page" id="page" value="${page}" />
    <input type="hidden" name="bt_position" id="bt_position" value="T" />
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
					<th style="width:120px"><span>*</span>배너명</th>
					<td colspan="3">
							<input type="text" name="bt_name" id="bt_name" style="width:90%" />
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>노출기간</th>
					<td colspan="3">
							<input type="text" name="bt_s_date" id="bt_s_date" class="date" value="" readonly="readonly"> 
							~ <input type="text" name="bt_e_date" id="bt_e_date" class="date" value=""  readonly="readonly">
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
							<input type="file" name="attPcFile" id="attPcFile" value="" style="width:40%" />&nbsp;
							(이미지사이즈 : 2500*587 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>배너이미지(MOBILE)</th>
					<td colspan="3">
							<input type="file" name="attMobileFile" id="attMobileFile" value="" style="width:40%" />&nbsp;
							(이미지사이즈 : 2500*587 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)
					</td>
				</tr>				
				<tr>
					<th style="width:120px"><span>*</span>링크 URL</th>
					<td colspan="3">
							<input type="text" name="btd_url" id="btd_url" value="" style="width:90%" />
					</td>
				</tr>				
				<tr>
					<th style="width:120px"><span>*</span>타겟</th>
					<td colspan="3">
							<select name="btd_target" id="btd_target">
								<option value="N">새창</option>
								<option value="S">현재창</option>
							</select>
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
							<input type="file" name="attEngPcFile" id="attEngPcFile" value="" style="width:40%" />&nbsp;
							(이미지사이즈 : 2500*587 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)							
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>배너이미지(MOBILE)</th>
					<td colspan="3">
							<input type="file" name="attEngMobileFile" id="attEngMobileFile" value="" style="width:40%" />&nbsp;
							(이미지사이즈 : 2500*587 / 파일 형식 : jpg,jpge,png / 파일용량 : 3M 이하)
					</td>
				</tr>				
				<tr>
					<th style="width:120px"><span>*</span>링크 URL</th>
					<td colspan="3">
							<input type="text" name="btd_eng_url" id="btd_eng_url" value="" style="width:90%" />
					</td>
				</tr>				
				<tr>
					<th style="width:120px"><span>*</span>타겟</th>
					<td colspan="3">
							<select name="btd_eng_target" id="btd_eng_target">
								<option value="N">새창</option>
								<option value="S">현재창</option>
							</select>
					</td>
				</tr>				
			</tbody>
		</table>
		</div>		
	</form>
	<div class="btn-area">
		<a href="#" class="button blue" id="btn_insert"><span>저장</span></a>
		<a href="<spring:message code="admin.context" />/banner/banner_list.do" class="button big"><span>목록</span></a>
		
	</div>
</div><!-- //우측 본문영역 -->