<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		게시물 목록
	</div>
	<div class="card-body">
      <table style="width:auto" class="table table-sm table-bordered">
         <tr>
            <th style="width:50px">번호</th>
            <th style="width:200px">제목</th>
            <th style="width:100px">글쓴이</th>
            <th style="width:100px">날짜</th>
         </tr>
         
         <c:forEach var="board" items="${boards}">
            <tr>
               <td>${board.bno}</td>
               <td><a href="detailBoard?bno=${board.bno}">${board.btitle}</a></td>
               <td>${board.mid}</td>
               <td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></td>
            </tr>
         </c:forEach>
      </table>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>