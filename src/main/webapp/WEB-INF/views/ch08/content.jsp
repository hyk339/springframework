<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Session Support
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				세션 원리 : JSESSIONID 쿠키
			</div>
			<div class="card-body">
				<p>서버: 세션생성->JSESSION 쿠키 발생</p>
				<p>브라우져: JSESSIONID 쿠키 전송 -> SERVER가 세션 객체 찾음 -> 세션 이용</p>
				<a href="javascript:saveData()" class="btn btn-info btn-sm">세션에 데이터 저장</a>
				<a href="javascript:readData()" class="btn btn-info btn-sm">세션에 데이터 읽기</a>
			</div>
			<script>
				function saveData(){
					$.ajax({
						url:"saveData",
						data: {name:"홍길동"}
					})
					.done((data) => { //json이 파싱이 되어서 json객체로 data에 들어온다. 
						console.log(data)
					});
				}
				
				function readData(){
					$.ajax({
						url:"readData",
					})
					.done((data) => {  
						console.log(data); //{name:"홍길동"}
						console.log(data.name);
					});
				}
			</script>
		</div>
		
		<div class="card">
			<div class="card-header">
				form을 통한 login 처리		
			</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}">
					<a href="login" class="btn btn-info btn-sm">로그인 폼 요청</a>
				</c:if>
				<c:if test="${sessionMid != null}">
					<a href="logout" class="btn btn-info btn-sm">로그아웃</a>
				</c:if>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				AJAX를 통한 login 처리		
			</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}">
					<form>
			           <div class="input-group">
			              <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
			              <input id="mid" type="text" name="mid" class="form-control">
			           	  <span id="mid-error" class="error"></span>
			           </div>
			           <div class="input-group">
			              <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
			              <input id="mpassword" type="password" name="mpassword" class="form-control">
			           	  <span id="mpassword-error" class="error"></span>
			           </div>
			        </form>
			     </c:if>
				<div class="mt-2">
					<c:if test="${sessionMid == null}">
						<a href="javascript:login()" class="btn btn-info btn-sm">로그인</a>
					</c:if>
					<c:if test="${sessionMid != null}">
						<a href="javascript:logout()" class="btn btn-info btn-sm">로그아웃</a>
					</c:if>
				</div>
				<script>
					function login(){
						let mid = $("#mid").val();
						let mpassword = $("#mpassword").val();
						$.ajax({
							url: "loginAjax",
							data:{mid, mpassword}, //{mid:mid, mpassword:mpassword} 같을 경우 생략가능하다.
							method: "post"
						}).done((data) => {
							//data = {result: "success"} //로그인이 성공한경우
							//data = {result: "wrongMid"}
							//data = {result: "wrongMpassword"}
							
							const midError = $("#mid-error");
							const mpasswordError = $("#mpassword-error");
							midError.html("");
							mpasswordError.html("");
							
							
							if(data.result==="success"){
								//현재 페이지 전체를 다시 서버에서 받아오도록 함.
								window.location.reload();
								//windows.location.href = "content"; //위와 둘다 같다. //controller거치고 오는지 확인해보자.
							} else if(data.result==="wrongMid"){
								midError.html("아이디가 잘못됨");
							} else if(data.result==="wrongMpassword"){
								mpasswordError.html("패스워드가 잘못됨");
							}
						});
					}
					
					function logout(){
						$.ajax({
							url: "logoutAjax",
						}).done((data)=>{
							
						});
					}
				</script>
			</div>
		</div>
		
		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>