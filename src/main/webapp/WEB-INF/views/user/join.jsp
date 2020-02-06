<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">아이디</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username"maxlength="15">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password"maxlength="15"/>
		</div>
		<div class="form-group">
			<label for="email">이메일</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email"maxlength="30">
		</div>
	</form>
	
	<button id="join--submit" class="btn btn-primary">회원가입</button>
</div>
<script>
	$('#join--submit').on('click',function(){
		var data = {
			username:$('#username').val(),
			password:$('#password').val(),
			email:$('#email').val()
		};

		$.ajax({
			type:'POST',
			url:'/user/join',
			data:JSON.stringify(data),
			contentType:'application/json; charset=utf-8'
		}).done(function(r){
			if(r.statusCode == 200){
				alert('회원가입 성공');
				location.href='/user/login';
			}else{
				if(r.msg == '아이디중복'){
					alert('아이디가 중복되었습니다.');
				}
				alert('회원가입 실패');
			}
		}).fail(function(r){
			alert('회원가입 실패');
		});
	});
</script>
<%@include file="../include/footer.jsp"%>



