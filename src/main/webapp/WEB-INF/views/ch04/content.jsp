<%@ page contentType="text/html; charset=UTF-8" %>
<%-- 아래 태그들 중에서 form이라고 접두사가 붙어있는 것 중 : 이름이 들어가있으면--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		유효성 검사
	</div>
	<div class="card-body">
	
		<div class="card m-2">
			<div class="card-header">
				POST 방식으로 요청
			</div>
			<div class="card-body">
				<form id="form0" method="post" action="method1" onsubmit="checkData(this)">
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param1</span></div>
						<input type="text" name="param1" class="form-control" value="">
						<span class="param1-error text-danger"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param2</span></div>
						<input type="text" name="param2" class="form-control" value="" >
						<span class="param2-error text-danger"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param3</span></div>
						<input type="text" name="param3" class="form-control" value="">
						<span class="param3-error text-danger"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param4</span></div>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-secondary active">
							  <input type="radio" name="param4" checked value="true"> true
							</label>
							<label class="btn btn-secondary">
							  <input type="radio" name="param4" value="false"> false
							</label>
						</div>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param5</span></div>
						<input type="date" name="param5" class="form-control" value="">
						<span class="param5-error text-danger"></span>
					</div>
					<input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
				</form>
			</div>
			<script>
				function checkData(form){
					//form의 제출 기능을 off
					event.preventDefault();
					
					//유효성 검사 결과 변수
					let checkResult = true;
					
					//입력 길이 체크
					let param1 = form.param1.value;
					const param1Error = document.querySelector("#form0 .param1-error");
					param1Error.innerHTML = "";
					if(param1 === ""){
						param1Error.innerHTML = "필수 입력 사항";
						checkResult = false;
						//input 요소가 아니면 name으로 찾을수 없다.
					} else{
						if(param1.length<8 || param1.length>15){
							param1Error.innerHTML = "8자 이상, 15자 이하로 입력";
							checkResult = false;
						};
					}
					
					
					//정규 표현식을 이용한 전화번호 형식 체크
					let param2 = form.param2.value;
					const param2Error = document.querySelector("#form0 .param2-error");
					param2Error.innerHTML = "";
					if(param2 === ""){
						param2Error.innerHTML = "필수 입력 사항";
						checkResult = false;
					}else{
						const pattern = /(010|011)-[0-9]{3,4}-[0-9]{4}/i;
						const result = pattern.test(param2);
						if(result === false){
							param2Error.innerHTML = "전화 번호 형식이 아님";
							checkResult = false;
						}
					}
					
					//정규 표현식을 이용한 이메일 형식 체크
					let param3 = form.param3.value;
					const param3Error = document.querySelector("#form0 .param3-error");
					param3Error.innerHTML = "";
					if(param3 === ""){
						param3Error.innerHTML = "필수 입력 사항";
						checkResult = false;
					}else{
						const pattern = /([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)/i;
						const result = pattern.test(param3);
						if(result === false){
							param3Error.innerHTML = "이메일 형식이 아님";
							checkResult = false;
						}
					}
					
					//날짜가 비었는지 체크
					let param5 = form.param5.value;
					console.log(param5);
					const param5Error = document.querySelector("#form0 .param5-error");
					param5Error.innerHTML = "";
					if(param5 === ""){
						param5Error.innerHTML = "필수 입력 사항";
						checkResult = false;
					}
					
					//서버로 제출할지 말지 결정
					if(checkResult){
						//아래가 자바스크립트로 제출기능을 수행한다.
						form.submit();
					}
					
					
					
					//const pattern = /([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)/i
					//const result = pattern.test("blueskii@naver");
					//console.log(result);
					//i는 대소문자 상관없다를 의미
					//const pattern = /(010|011)-[0-9]{3,4}-[0-9]{4}/i;
					//const result = pattern.test("011-12-1234");
					//console.log(result);
				}
			</script>
		</div>
	
		<div class="card m-2">
			<div class="card-header">
				AJAX로 요청
			</div>
			<div class="card-body">
				<form id="form1" name="form1">
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param1</span></div>
						<input type="text" id="param1" name="param1" class="form-control">
						<span class="param1-error text-danger"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param2</span></div>
						<input type="text" id="param2" name="param2" class="form-control">
						<span class="param2-error text-danger"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param3</span></div>
						<input type="text" id="param3" name="param3" class="form-control">
						<span class="param3-error text-danger"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param4</span></div>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-secondary active">
							  <input type="radio" id="radio1" name="param4" checked value="true"> true
							</label>
							<label class="btn btn-secondary">
							  <input type="radio" id="radio2" name="param4" value="false"> false
							</label>
						</div>
					</div>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">param5</span></div>
						<input type="date" id="param5" name="param5" class="form-control" value="2030-12-05">
						<span class="param5-error text-danger"></span>
					</div>
				</form>
				<div class="mt-2">
					<button class="btn btn-info btn-sm" onclick="requestPost()">POST 방식 요청</button>
				</div>
			</div>
			<script>
				function requestPost() {
					const param1 = $("#param1").val(); //주민번호: xxxxxx-xxxxxxx(6자리-7자리, 반드시 7자리의 첫자리는 1,2,3,4만 와야한다.)
					const param2 = $("#param2").val(); //년월일: 19680615 년월일이 나오게 (월에 31들어가지 않게)
					const param3 = $("#param3").val(); //패스워드 : 알파벳으로 시작, 최소 8자 최대 10자
					const param4 = $("#form1 input[name=param4]:checked").val();
					const param5 = $("#param5").val();
					
					
					let checkData = true;
	
					const param1Error = $("#form1 .param1-error");
					param1Error.html("");
					if(param1 === ""){
						param1Error.html("필수 입력 사항");
						checkData = false;
					} else{
						//숫자가 2자리가 온다.
						const pattern = /^\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4][0-9]{6}$/;
						const result = pattern.test(param1);
						if(result === false){
							param1Error.html("주민번호 형식이 아님");
							checkData = false;
						};
					}
					
					if(checkData){					
						$.ajax({
							url:"method1",
							method:"post",
							data: {
								param1:param1, 
								param2, 
								param3, 
								param4, 
								param5
							}
						})
						.done(() => {});
					}
				}
			</script>
		</div>
		
		<div class="card m-2">
			<div class="card-header">
				서버측 유효성 검사
			</div>
			<div class="card-body">
			
				<div class="card m-2">
					<div class="card-header">
						회원 가입 폼
					</div>
					<div class="card-body">
						<form method="post" action="join">
		                    <div class="input-group">
		                       <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
		                       <input type="text" name="mid" class="form-control" value="${joinForm.mid}" autocomplete="username">
		                       <%-- 
		                       		아래는 spring에서 제공해주는 태그이다.
		                       		Spring이 제공하는 form관련 tag중 하나.
		                       		이걸 사용하려면 라이브러리가 추가되어야한다.
		                       		만약 이 아래 form을 사용하지 않으면 에러메시지를 넣어서 form에 입력하도록 하는 것을 못한다.
		                       		그래서 들어가 있다.
		                        --%>
		                       <form:errors cssClass="text-danger" path="joinForm.mid"/>
		                    </div>
		                    <div class="input-group">
		                       <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
		                       <input type="password" name="mpassword" class="form-control" value="${joinForm.mpassword}" autocomplete="current-password">
		                       <form:errors cssClass="text-danger" path="joinForm.mpassword"/>
		                    </div>
		                    <div class="input-group">
		                       <div class="input-group-prepend"><span class="input-group-text">memail</span></div>
		                       <input type="text" name="memail" class="form-control" value="${joinForm.memail}">
		                       <form:errors cssClass="text-danger" path="joinForm.memail"/>
		                    </div>
		                    <div class="input-group">
		                       <div class="input-group-prepend"><span class="input-group-text">mtel</span></div>
		                       <input type="text" name="mtel" class="form-control" value="${joinForm.mtel}">
		                       <form:errors cssClass="text-danger" path="joinForm.mtel"/>
		                    </div>
		                    <input class="btn btn-info" type="submit" value="가입"/>
		                 </form>
		        	</div>
		        </div>
		        
		        <div class="card m-2">
					<div class="card-header">
						로그인폼
					</div>
					<div class="card-body">
						<form method="post" action="login">
		                     <div class="input-group">
		                        <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
		                        <input type="text" name="mid" class="form-control" value="${loginForm.mid}">
		                        <form:errors cssClass="error" path="loginForm.mid"/>
		                     </div>
		                     <div class="input-group">
		                        <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
		                        <input type="password" name="mpassword" class="form-control" value="${loginForm.mpassword}">
		                        <form:errors cssClass="error" path="loginForm.mpassword"/>
		                     </div>
		                     <input class="btn btn-info" type="submit" value="로그인"/>
		                </form>
		        	</div>
		        </div>
		        
		        
		        
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>