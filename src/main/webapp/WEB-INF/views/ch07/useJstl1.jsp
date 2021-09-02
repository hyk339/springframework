<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%-- 
	많이 사용되는 논리적인 판단, 반복문의 처리, 데이터베이스 등의 처리를 위한 표준 커스텀 태그가 바로 JSTL
	STL 을 사용하는 이유는 소스를 보기 쉽고 유지보수시 더 유리하기 때문
--%>
<div class="card m-2">
	<div class="card-header">
		JSTL을 이용한 반복 처리
	</div>
	<div class="card-body">
		<h6>[지정한 횟수만큼 반복]</h6>
		<table class="table table-striped">
			<thead>
				<tr>
					<td>No</td>
					<td>Title</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="1" end="5" step="1" var="i">
					<tr>
						<th scope="row">${i}</td>
						<td>제목${i}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h6 class="mt-3">[배열의 항목 수만큼]</h6>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">Title</th>
				</tr>
			</thead>
			<%--
			${status.index}    <!– 0부터의 순서 –>
			${status.count}    <!– 1부터의 순서 –>
			${status.first}    <!– 현재 루프가 처음인지 반환 제일 처음에 반복할때–>
			${status.last}     <!– 현재 루프가 마지막인지 반환 제일마지막에 반복할때–> 
			--%>
			<c:forEach var="lang" items="${langs}" varStatus="status">
				<%-- 
					items나 test부분은 반드시 el이 들어가야한다!!
					요즘 추세는 jsp안에는 java코드를 작성하지 말자다.
					java code를 jsp에 적을때 단점은
					jstl을 사용할때보다 많은 코드가 들어간다는점
					tag와 java코드가 섞여있다면 개발자만 손댈수있다.
					모든 것을 tagbase로 하면 읽기가 쉬워진다.
					jsp에 java코드 안쓰는것이 관례이다.
				 --%>
				<c:if test="${status.first}">
					<tbody>
				</c:if>
					<tr>
						<th scope="row">${status.count}</th>
						<td>${lang}</td>
					</tr>
				<c:if test="${status.last}">
					</tbody>
				</c:if>
			</c:forEach>
		</table>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>