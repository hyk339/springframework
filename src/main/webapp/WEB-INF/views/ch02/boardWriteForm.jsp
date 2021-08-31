<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		게시물 작성
	</div>
	<div class="card-body">
		<%-- 절대경로를 사용하고자 할때 1번 --%>
		<%-- <form method="post" action="<%=application.getContextPath()%>/ch02/boardwrite">--%>
		<%--아래 방식을 선호하는 개발자가 많다. 절대경로를 사용하고자 할때 2번--%>
		<form method="post" action="${pageContext.request.contextPath}/ch02/boardwrite">
		<%-- 상대경로를 사용하려면 아래와 같이 쓴다.--%>
		 <%-- <form method="post" action="boardwrite">--%>
		  <div class="form-group">
		    <label for="title">제목</label>
		    <input type="text" class="form-control" id="title">
		  </div>
		  <div class="form-group">
		    <label for="content">내용</label>
		    <textarea class="form-control" id="content"></textarea>
		  </div>
		  <button type="submit" class="btn btn-primary btn-sm">저장</button>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>