<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		DTO 객체(Command Object)와 폼 연결
	</div>
	<div class="card-body">
		<form:form method="post" modelAttribute="member">
		  <div class="form-group">
		    <label for="mid">ID</label>
		    <form:input type="text" class="form-control" id="mid" path="mid"/>
		  </div>
		  <div class="form-group">
		    <label for="mname">Name</label>
		    <form:input type="text" class="form-control" id="mname" path="mname"/>
		  </div>
		  <div class="form-group">
		    <label for="mpassword">Password</label>
		    <form:password class="form-control" id="mpassword" path="mpassword"/>
		  </div>
		  <div class="form-group">
		    <label for="mnation">Nation</label>
		    <%-- 아래 국가에 value="한국" 이라고 주면 동적으로 변경이 불가하다. 그래서 만약 db에서 읽어온 값을 default로 setting할때를 알아보자.--%>
		    <%-- 아래 국가에 value="${defaultNation}" 이라고 주면 동적으로 변경 가능하긴다. --%>
		    <%-- 아래처럼 객체를 연결시켜주어사용할수있다. --%>
		    <form:input type="text" class="form-control" id="mnation" path="mnation"/>
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>