<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		EL을 사용해서 Object 안에 데이터를 출력
	</div>
	<div class="card-body">
		<%--
			읽어올때는 member.name이라고 하면 해당 객체가 가지고 있는 getter를 호출한다.
			표현을 el에서는 이렇게 한다.
			그래서 반드시 getter가 있어야한다.
			
		 --%>
		<p>이름: ${member.name}</p>
		<p>나이: ${member.age}</p>
		<p>직업: ${member.job}</p>
		<p>도시: ${member.city.name}</p>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>