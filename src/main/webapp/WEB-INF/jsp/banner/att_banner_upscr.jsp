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
	
	$("#ebt_s_date").datepicker({
			showOn:"both", 
			 dateFormat: 'yy-mm-dd',
			buttonImage: "/_admst/images/icon_calendar.png",
			onClose:function( selectedDate ) {                
		         $("#ebt_e_date").datepicker( "option", "minDate", selectedDate );            
		      }    
	});
	
	$("#ebt_e_date").datepicker({
		showOn:"both", 
		 dateFormat: 'yy-mm-dd',
		 buttonImage: "/_admst/images/icon_calendar.png",
		onClose:function( selectedDate ) {                
	         $( "#ebt_s_date").datepicker( "option", "maxDate", selectedDate );            
	      }    
	});	
	
	$("#ebt_eng_s_date").datepicker({
		showOn:"both", 
		 dateFormat: 'yy-mm-dd',
		buttonImage: "/_admst/images/icon_calendar.png",
		onClose:function( selectedDate ) {                
	         $("#ebt_eng_e_date").datepicker( "option", "minDate", selectedDate );            
	      }    
	});
	
	$("#ebt_eng_e_date").datepicker({
		showOn:"both", 
		 dateFormat: 'yy-mm-dd',
		 buttonImage: "/_admst/images/icon_calendar.png",
		onClose:function( selectedDate ) {                
	         $( "#ebt_eng_s_date").datepicker( "option", "maxDate", selectedDate );            
	      }    
	});	
	
	$("#btn_insert").on("click", function(){
		var frm = document.frm;
		if(!fnIsValidRequired(frm.ebt_title, "????????????")){return false;}
		if(!fnIsValidRequired(frm.ebt_s_date, "????????? ?????????")){return false;}
		if(!fnIsValidRequired(frm.ebt_e_date, "????????? ?????????")){return false;}
		if(!fnIsValidRequired(frm.ebt_cont, "????????? ??????")){return false;}
		if(!fnIsValidRequired(frm.ebt_eng_name, "?????? ????????????")){return false;}
		if(!fnIsValidRequired(frm.ebt_eng_s_date, "?????? ????????? ?????????")){return false;}
		if(!fnIsValidRequired(frm.ebt_eng_e_date, "?????? ????????? ?????????")){return false;}
		if(!fnIsValidRequired(frm.ebt_eng_cont, "?????? ????????? ??????")){return false;}
		frm.action = "<spring:message code='admin.context' />/banner/att_banner_upscr.do";
		if(confirm('?????????????????????????')){
			$("#frm").submit();	
		}
	});
	
	<c:if test="${attBannerVo.gmet_idx == null}">
		$('input').prop('disabled', true);
		$('textarea').prop('disabled', true);
	</c:if>
	
	
	$("#btn_delete").click(function(){
		var frm = document.frm;
		frm.action = "<spring:message code='admin.context' />/banner/att_banner_delete.do";
		if(confirm('????????? ???????????????????')){
			$("#frm").submit();	
		}
	});
	
	
	$("#btn_clean").on("click", function(){
		$("#ebt_title").val('');
		$("#ebt_s_date").val('');
		$("#ebt_e_date").val('');
		$("#ebt_cont").html('');
		$("#ebt_eng_name").val('');
		$("#ebt_eng_ename").val('');
		$("#ebt_eng_s_date").val('');
		$("#ebt_eng_e_date").val('');
		$("#ebt_eng_cont").html('');
		$("#gci_kor_cname").html('&nbsp;');
		$('input').prop('disabled', true);
		$('textarea').prop('disabled', true);
	});
	
	$("#btn_popup").on("click", function(){
		$.smartPop.open({title: '????????? ????????? ????????????', width: '80%', height:400, url: '<spring:message code="admin.context" />/banner/popup/event_com_list.do', padding : 0, border : 2, log : false });
	});
	
}); 


function fn_cominput(param){
	$("#frm")[0].reset();
	$('input').prop('disabled', false);
	$('textarea').prop('disabled', false);
	$("#gci_kor_cname").html(param.gci_kor_cname);
	$("#ebt_title").val(param.gmet_name);
	$("#ebt_s_date").val(param.gmet_s_date);
	$("#ebt_e_date").val(param.gmet_e_date);
	$("#ebt_cont").val(param.gmet_cont);
	$("#gmet_idx").val(param.gmet_idx);
}


</script>

<a href="#" class="lnb-control">???????????????</a>
<div class="content-area"><!-- ?????? ???????????? -->
	<div class="breadcrumbs"><span class="home">HOME</span> &gt; ??????/?????? &gt; <strong>????????? ????????? ?????? ??????</strong></div>
	<h2>????????? ????????? ?????? ??????</h2>
	<form name="frm" id="frm" action="<spring:message code="admin.context" />/banner/att_banner_upscr.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="gmet_idx" id="gmet_idx" value="${attBannerVo.gmet_idx}"/>
	<input type="hidden" name="ebt_position" id="ebt_position" value="${ebt_position}"/>
	<h3 class="sub-title">?????? ??????</h3>
		<ul class="tab">
			<li <c:if test="${ebt_position == 'A' || ebt_position == ''}">class="on"</c:if> id="tab_li_kor"><a href="?ebt_position=A" id="tab_kor">1?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'B'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=B" id="tab_eng">2?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'C'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=C" id="tab_eng">3?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'D'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=D" id="tab_eng">4?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'E'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=E" id="tab_eng">5?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'F'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=F" id="tab_eng">6?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'G'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=G" id="tab_eng">7?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'H'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=H" id="tab_eng">8?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'I'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=I" id="tab_eng">9?????? ??????</a></li>
			<li <c:if test="${ebt_position == 'J'}">class="on"</c:if> id="tab_li_eng"><a href="?ebt_position=J" id="tab_eng">10?????? ??????</a></li>
			
		</ul>
		<div class="regist">
			<table>
				<tbody>
				<tr>
					<th style="width:120px">????????????</th>
					<td colspan="3">??????/?????? ?????? ??????</td>
				</tr>
				</tbody>
			</table>
		</div>
		<h3 class="sub-title" style="margin-top:10px;">???????????????</h3>
		<div class="btn-area">
			<a href="javascript:;" class="button blue" id="btn_popup"><span>????????? ????????? ????????????</span></a>
			<a href="javascript:;" class="button red" id="btn_delete"><span>?????????</span></a>		
		</div>
		<div class="btn-area-right">
			<span class="warning">*?????? ?????? ?????? ???????????????.</span>
		</div>
		
		<div class="regist">
		<table>
			<tbody>
				<tr>
					<th colspan="4" height="48" style="text-align:center;"><strong>??????</strong></th>
				</tr>
				<tr>
					<th style="width:120px">????????? ?????????</th>
					<td colspan="3" id="gci_kor_cname">
							&nbsp;${attBannerVo.gci_kor_cname}
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????????</th>
					<td colspan="3">
						<input type="text" name="ebt_title" id="ebt_title" value="${attBannerVo.ebt_title}" data-text="????????????">
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????? ??????</th>
					<td colspan="3">
						<input type="text" name="ebt_s_date" id="ebt_s_date" class="date" value="${attBannerVo.ebt_s_date}" data-text="????????? ?????????">
						 ~ <input type="text" name="ebt_e_date" id="ebt_e_date" class="date" value="${attBannerVo.ebt_e_date}" data-text="????????? ?????????">
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????? ??????</th>
					<td colspan="3">
						<textarea name="ebt_cont"  id="ebt_cont" rows="10" style="width:90%" data-text="????????? ??????">${attBannerVo.ebt_cont}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		
		<div class="regist">
		<table>
			<tbody>
				<tr>
					<th colspan="4" height="48" style="text-align:center;"><strong>??????</strong></th>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????? ?????????</th>
					<td colspan="3" class="gct_name">
						<input type="text" name="ebt_eng_name" id="ebt_eng_name" value="${attBannerVo.ebt_eng_name}" data-text="?????? ????????? ?????????">
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????????</th>
					<td colspan="3">
						<input type="text" name="ebt_eng_ename" id="ebt_eng_ename" value="${attBannerVo.ebt_eng_ename}" data-text="?????? ????????????">
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????? ??????</th>
					<td colspan="3">
						<input type="text" name="ebt_eng_s_date" id="ebt_eng_s_date" class="date" value="${attBannerVo.ebt_eng_s_date}" data-text="?????? ????????? ?????????">
						 ~ <input type="text" name="ebt_eng_e_date" id="ebt_eng_e_date" class="date" value="${attBannerVo.ebt_eng_e_date}" data-text="?????? ????????? ?????????">
					</td>
				</tr>
				<tr>
					<th style="width:120px"><span>*</span>????????? ??????</th>
					<td colspan="3">
						<textarea name="ebt_eng_cont"  id="ebt_eng_cont" rows="10" style="width:90%" data-text="?????? ????????? ??????">${attBannerVo.ebt_eng_cont}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</form>
	<div class="btn-area">
		<a href="#" class="button blue" id="btn_insert"><span>??????</span></a>
	</div>
</div><!-- //?????? ???????????? -->