<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/WEB-INF/tlds/jsoft.tld" prefix="jsoft"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
$(document).ready(function(){
	
		// 조회 버튼 클릭시
		$("#searchSDate").datepicker({
				showOn:"both", 
				 dateFormat: 'yy-mm-dd',
				buttonImage: "/_admst/images/icon_calendar.png",
				onClose:function( selectedDate ) {                
			         $("#searchEDate").datepicker( "option", "minDate", selectedDate );            
			      }    
		});
		$("#searchEDate").datepicker({
			showOn:"both", 
			 dateFormat: 'yy-mm-dd',
			 buttonImage: "/_admst/images/icon_calendar.png",
			onClose:function( selectedDate ) {                
		         $( "#searchSDate").datepicker( "option", "maxDate", selectedDate );            
		      }    
		});	
		// 전체선택
		$('#allCheck').click(function(){
			if ($("#allCheck").is(":checked")) { 
				$('input:checkbox[name=del_id]').prop("checked", true); 
			} else {
				$('input:checkbox[name=del_id]').prop("checked", false); 
			} 
		}); 
		$('input:checkbox[name=del_id]').on("click", function(){
			var chk = true;
			$('input:checkbox[name=del_id]').each(function(){
				if (!$(this).is(":checked")) { 
					chk = false;
				}
			});
			$('#allCheck').prop("checked", chk);
		}); 	
		
		// 조회 버튼 클릭시
		$("#btnRightSearch").click(function(){
			$("#page").val("1");
			$("#frm").submit();
			
		});
		
		// 삭제 버튼 클릭시
		$("#btn_del").click(function(){
			var checkCnt = $("input[name=del_id]:checked").size();
			if(checkCnt == 0){
				alert("삭제할 배너를 하나이상 선택 해 주세요.");
				return false;
			}
			// 선택한 개시물을 삭제 한다.
			if(confirm("배너를 삭제 하시겠습니까?")){
				fnDoCallBackAjax('<spring:message code="admin.context" />/banner/banner_delete.json', $("#delfrm").serialize(), fnRltdDelSuccess);
			}else{
				return false;
			}
		});
		
		$("a[id^=btn_down_]").on("click", function(){
			var bt_idx = $(this).attr("id").replace("btn_down_", "");
			
			fnDoCallBackAjax('<spring:message code="admin.context" />/banner/banner_sort.json', "bt_idx=" + bt_idx+ "&sort=down", function(){
				$("#page").val("${page}");
				$("#frm").submit();
			});
			
		});
		$("a[id^=btn_up_]").on("click", function(){
			var bt_idx = $(this).attr("id").replace("btn_up_", "");
			
			fnDoCallBackAjax('<spring:message code="admin.context" />/banner/banner_sort.json', "bt_idx=" + bt_idx+ "&sort=up", function(){
				$("#page").val("${page}");
				$("#frm").submit();
			});
			
		});
}); 

function fnRltdDelSuccess(data){
	if(data.result == 'success'){
		alert("삭제하였습니다.");
		$("#page").val("1");
		$("#frm").submit();
	}else{
		alert(data.resMsg);	
		$("#page").val("1");
		$("#frm").submit();
	}
}
</script>
			<a href="#" class="lnb-control">메뉴컨트롤</a>
			<div class="content-area"><!-- 우측 본문영역 --> 
				<div class="breadcrumbs"><span class="home">HOME</span> &gt; 배너/팝업 &gt; <strong>스폰서 배너관리</strong></div>
				<h2>스폰서 배너관리</h2>
            	<form name="frm" id="frm" method="post" action="<spring:message code="admin.context" />/banner/banner_sponser_list.do">
            	<input type="hidden" name="page" id="page" value="${page}" />
            	<input type="hidden" name="searchGubun" id="searchGubun" value="T" />
            	<div class="sbox"><!-- 검색조건 -->
					<div class="clear">
						<div class="item itemF"  style="width:100%">
							<label>배너명</label>
							<input type="text" name="searchVal" id="searchVal" value="${searchVal}" style="width:40%">
						</div>
					</div>
					<div class="clear">
						<div class="item itemF"  style="width:50%">
							<label>노출위치</label>
							<select name="searchKind" id="searchKind">
                                <option value="">전체</option>
                                <option value="6" <c:if test="${searchKind == '6'}">selected="selected"</c:if>>국문/영문</option>
                                <option value="2" <c:if test="${searchKind == '2'}">selected="selected"</c:if>>국문</option>
                                <option value="4" <c:if test="${searchKind == '4'}">selected="selected"</c:if>>영문</option>
                            </select>
						</div>
						<div class="item itemF"  style="width:40%">
							<label>등록일자</label>
							<input type="text" name="searchSDate" id="searchSDate" class="date" value="${searchSDate}" readonly="readonly"> 
							~ <input type="text" name="searchEDate" id="searchEDate" class="date" value="${searchEDate}"  readonly="readonly">
							<a href="#" class="btn" id="btnToday"><span>오늘</span></a>
							<a href="#" class="btn" id="btnWeek"><span>일주일</span></a>
							<a href="#" class="btn" id="btnFifteen"><span>15일</span></a>
							<a href="#" class="btn" id="btnMonth"><span>한달</span></a>
							<a href="#" class="btn" id="btnDayAll"><span>기간지정</span></a>
							
						</div>
						<a href="#" class="button search" id="btnRightSearch"><span>검색</span></a>	
					</div>
				</div>
				</form>
				<div class="grid-area"><!-- 목록 -->
					<div class="num">Total  : <fmt:formatNumber value="${totalCount}" pattern="##,###" /></div>
					<div class="btn-area right">
						<a href="#"  id="btn_del" class="button warning"><span>선택삭제</span></a>
						<!-- <a href="#"  id="btn_sort" class="button txt-blue"><span>순서변경</span></a> -->
						<a href="<spring:message code="admin.context" />/banner/banner_sponser_inscr.do" class="button"><span>신규등록</span></a>
					</div>
					<form id="delfrm" name="delfrm">
					<div class="list">
						<table>
							<colgroup>
								<col style="width:70px" />
								<col style="width:70px" />
								<col style="width:*px" />	
								<col style="width:15%" />
								<%-- <col style="width:8%" /> --%>
								<col style="width:8%" />				
								<col style="width:8%" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="allCheck" id="allCheck" value=""></th>
									<th>번호</th>
									<th>배너명</th>
									<th>노출기간</th>
									<!-- <th>노출순서</th> -->
									<th>등록일자</th>
									<th>노출위치</th>
								</tr>
							</thead>
							<tbody>
 							<c:if test="${fn:length(itemList) == 0}">
							 	<tr>
							 		<td colspan="7">조회된 게시물이 없습니다.</td>
							 	</tr>
							 </c:if>							
							<c:set var="lno" value="0" />
							<c:forEach items="${itemList}" var="item" varStatus="status">
							<c:set var="lno">${lno + 1}</c:set>
								<tr>
									<td><input type="checkbox" name="del_id" id="del_id_${item.bt_idx}" value="${item.bt_idx}"></td>
   								    <td><fmt:formatNumber value="${totalCount - ((page - 1) * listSize + (lno - 1))}" pattern="##,###" /></td>
   								    <td class="left">
   								    	<a href="./banner_sponser_upscr.do?bt_idx=${item.bt_idx}&amp;${StrParam}">
   								    		${item.bt_name}
   								    	</a>
   								    </td>
   								    <td>
   								    	${item.bt_s_date} ~ ${item.bt_e_date}
   								    </td>
   								   <%--  <td>
   								    	<c:choose>
   								    		<c:when test="${item.bt_order == 1}">
   								    		<a href="#" id="btn_down_${item.bt_idx}">▼</a>
   								    		</c:when>
   								    		<c:when test="${item.bt_order == maxOrder}">
   								    		<a href="#" id="btn_up_${item.bt_idx}">▲</a>
   								    		</c:when>
   								    		<c:otherwise>
   								    		<a href="#" id="btn_up_${item.bt_idx}">▲</a><a href="#" id="btn_down_${item.bt_idx}">▼</a>
   								    		</c:otherwise>
   								    	</c:choose>
   								    </td> --%>
   								    <td>${fn:substring(item.inputdate, 0, 10)}</td>
   								    <td>${item.site_open_gubun_str}</td>
								</tr>
							</c:forEach>								
							 
							</tbody>
						</table>
					</div>
					</form>
					<jsoft:pageLinkAdmin name="pageholder"/>
				</div><!-- //목록 -->
			</div><!-- //우측 본문영역 -->