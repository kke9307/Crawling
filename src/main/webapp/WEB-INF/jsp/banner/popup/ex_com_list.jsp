<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="/WEB-INF/tlds/jsoft.tld" prefix="jsoft"%>
<script>
$(document).ready(function(){
	$("#btnRightSearch").click(function(){
		$("#page").val("1");
		$("#frm").submit();
	})
});

function fn_cominput_pop(gft_idx_param ,gci_kor_cname){
 	var param= new Object();
	param.gft_idx = gft_idx_param;
	param.gci_kor_cname = gci_kor_cname;
	parent.fn_cominput(param);
	parent.$.smartPop.close();
 }
</script>
<div id="box_div">		
		<h3 class="sub-title">참가사 선택</h3>
		<form name="frm" id="frm" method="post" action="<spring:message code="admin.context" />/banner/popup/ex_com_list.do">
			<div class="sbox"><!-- 검색조건 -->
				<div class="clear">
					<div class="item itemF"  style="width:80%">
					<label>회사명</label>
						<input type="text" name="searchVal" id="searchVal" value="${searchVal}" style="width:40%">
					</div>
					<a href="#" class="button search" id="btnRightSearch"><span>검색</span></a>		
				</div>
			</div>
		</form>
		
		<div class="num">총  : <fmt:formatNumber value="${totalCount}" pattern="##,###" />건</div>
		<div class="list">
			<table>
				<colgroup>
					<col style="width:5%" />
					<col style="width:15%" />
					<col style="width:10%" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>참가사 회사명</th>
						<th>등록</th>
					</tr>
				</thead>
				<tbody>
				 <c:if test="${fn:length(itemList) == 0}">
				 	<tr>
						<td colspan="8">등록된 이벤트사가 없습니다.</td>
				 	</tr>
				 </c:if>
				<c:set var="lno" value="0" />
				<c:forEach items="${itemList}" var="item" varStatus="status">
				<c:set var="lno">${lno + 1}</c:set>
					<tr>
						<td><fmt:formatNumber value="${totalCount - ((page - 1) * listSize + (lno - 1))}" pattern="##,###" /></td>
					    <td>${item.gci_kor_cname}</td>
					    <td><a href="javascript:fn_cominput_pop('${item.gft_idx}','${item.gci_kor_cname}');" class="button" ><span>등록</span></a></td>
					</tr>							
				</c:forEach>
				</tbody>
			</table>
		</div>
		<jsoft:pageLinkAdmin name="pageholder"/>
		<div class="btn-area">
        	<a href="#" class="button point big" onclick="parent.$.smartPop.close();"><span>닫기</span></a>
       	</div>
</div>

