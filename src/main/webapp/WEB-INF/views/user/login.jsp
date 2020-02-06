<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">아이디</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password"/>
		</div>
	</form>
	
	<button id="login--submit" class="btn btn-primary">로그인</button>
</div>

<%@include file="../include/footer.jsp"%>



