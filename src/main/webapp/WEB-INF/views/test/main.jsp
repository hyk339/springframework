<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		<h1>main</h1>
	</div>
	<div class="card-body">
		<a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/test/makeid?id=hyk339&pass=1234&name=kim&age=28">a태그로 href 보내기</a>
		<form name="form1" id="form1" action="${pageContext.request.contextPath}/test/makeid" method="POST">
			<div class="form-group">
				<label for="id1">아이디</label>
				<input id="id1" name="id" type="text" class="form-control" value="hyk339"><br/>
			</div>
			<div class="form-group">
				<label for="pass1">패스워드</label>
				<input id="pass1" name="pass" type="password" class="form-control" value="1234"><br/>
			</div>
			<div class="form-group">
				<label for="name1">이름</label>
				<input id="name1" name="name" type="text" class="form-control" value="kim"><br/>
			</div>
			<div class="form-group">
				<label for="age1">나이</label>
				<input id="age1" name="age" type="text" class="form-control" value="28"><br/>
			</div>
			<div class="form-group">
				<label for="date1">나이</label>
				<input id="date1" name="date" type="date" class="form-control" value="2021-12-05"><br/>
			</div>
			<label class="btn btn-secondary active">
			  <input type="radio" name="radio1" checked value="true"> true
			</label>
			<label class="btn btn-secondary">
			  <input type="radio" name="radio1" value="false"> false
			</label>
			
			<button class="btn btn-sm btn-danger">제출하기</button>
			<input type="submit" value="제출하기" class="btn btn-sm btn-danger">
			<input type="image" src="${pageContext.request.contextPath}/resources/images/photo1.jpg" width="100px" height="50px">
		</form>
		<hr/>
		<button class="btn btn-sm btn-success" onclick="ajaxmethod1()">실행</button>
		<button class="btn btn-sm btn-success" onclick="ajaxput()">put실행</button>
		<button class="btn btn-sm btn-success" onclick="ajaxdelete()">delete실행</button>
	</div>
	<script>
	
		function Object1(id,pass,name,age,date,radio1){
			/* this.id1 = id;
			this.pass1 = pass;
			this.name1 = name;
			this.age1 = age; */
			this.method1 = function(){
				$.ajax({
					url:"makeid",
					method:"post",
					data:{id,pass,name,age,date,radio1}
				})
				.done(()=>{});
			};
		}
		
		class Object2 {
			constructor(id,pass,name,age,date,radio1){
				this.id = id;
				this.pass = pass;
				this.name = name;
				this.age = age;
				this.date = date;
				this.radio1 = radio1;
				/*
				this.id2 = id;
				this.pass2 = pass;
				this.name2 = name;
				this.age2 = age;
				*/
			}
			
			method1(){
				$.ajax({
					url:"makeid",
					method:"post",
					data:{
						id:this.id, 
						pass:this.pass,
						name:this.name, 
						age:this.age,
						date:this.date,
						radio1:this.radio1
						/*
		-----------------불가능-------------------
						id,
						pass,
						name,
						age
		-----------------------------------------
						*/
						/* 
						id:this.id2, 
						pass:this.pass2,
						name:this.name2, 
						age:this.age2 
						*/
						}
				})
				.done(()=>{});
			}
		}
		
		
		
		function ajaxmethod1(){
			const var1 = document.getElementById("id1").value;
		   	const var2 = document.querySelector("#pass1").value;
		   	const var3 = $("#name1").val();
		    const var4 = document.form1.age.value;
		    const var5 = document.form1.date.value;
		    const var6 = $("#form1 input[type=radio]:checked").val();
			const obj = new Object2(var1,var2,var3,var4,var5,var6);
			obj.method1();
		}
		
/* 		const obtest1 = new Object1("hyk339",1234,"kim",28);
		console.log(obtest1.id1);
		console.log(obtest1.pass1);
		console.log(obtest1.name1);
		console.log(obtest1.age1);
		const obtest2 = new Object2("hyk339",1234,"kim",28);
		console.log(obtest2.id);
		console.log(obtest2.pass);
		console.log(obtest2.name);
		console.log(obtest2.age); */
		
		function ajaxput(){
			$.ajax({
				url:"putmethod",
				method:"put"
			}).done(()=>{});
		}
		
		function ajaxdelete(){
			$.ajax({
				url:"deletemethod",
				method:"delete"
			}).done(()=>{});
		}
	</script>
	
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>