<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		DTO 객체의 필드값을 양식의 드롭다운리스트(select 태그)로 세팅
	</div>
	<div class="card-body">
		 	<%--
		<form>
		  <div class="form-group">
		    <label for="mtype">Type</label>
		    <select class="form-control" id="mtype" name="mtype">
		      <c:forEach var="type" items="${typeList}">
		      	<option value="${type}"
					<c:if test="${member.mtype == type}">selected</c:if>		      	
		      	>${type}</option>
		      </c:forEach>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form>
		--%>
		
		
	 <%--자동으로 post방식으로 현재 요청한 주소의 post방식으로 넘어간다.--%>
		<form:form method="post" action="form2" modelAttribute="member">
		  <div class="form-group">
		    <label for="mtype">Type</label>
			<form:select path="mtype" items="${typeList}" class="form-control"/>
		  </div>
		  <button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>