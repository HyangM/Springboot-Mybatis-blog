<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">아이디</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username" >
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password"/>
		</div>
	</form>
	
	<button id="login--submit" class="btn btn-primary">로그인</button>
</div>

<script>
	$('#login--submit').on('click', function(){
		var data = {
			username: $('#username').val(),
			password: $('#password').val()
		}; 

		$.ajax({
			type:'POST',
			url:'/user/loginProc',
			data:data, //username=ssar&password=1234
			contentType:'application/x-www-form-urlencoded',
			dataType:'json'
		}).done(function(r){
			console.log(r);
			alert("로그인 성공");
			location.href='/';
		}).fail(function(r){
			console.log('statusCode : ' + r.statusCode);
			console.log(r);
			alert("로그인 실패");
		});
	});

</script>

<%@include file="../include/footer.jsp"%>



