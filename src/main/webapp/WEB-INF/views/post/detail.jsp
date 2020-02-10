<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">
	<div class="card">
		<div class="card-header">
			<h4 class="card-title">${post.title}</h4>
		</div>
		<div class="card-body">
			<p class="card-text">${post.content}</p>
		</div>
		<div class="card-footer">
			<c:if test="${post.userId eq sessionScope.principal.id}">
				<a href="/post/update/${post.id}" id="post--update--submit" class="btn btn-warning" type="button">수정</a>
				<button id="post--delete--submit" class="btn btn-danger" value="${post.id}">삭제</button>
			</c:if>
			<a href="/" class="btn btn-primary">목록</a>

		</div>
	</div>

	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-body">
				<input type="hidden" id="postId" value="${post.id}" /> <input type="hidden" id="userId" value="${sessionScope.principal.id}" />
				<textarea class="form-control" rows="2" id="content"></textarea>
			</div>
			<div class="card-footer">
				<button id="comment--save--submit" class="btn btn-primary">등록</button>
			</div>
		</div>
	</div>

	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h4 class="card-title">댓글 리스트</h4>
			</div>
			<div id="comment--items" class="card-body">
				
			</div>
		</div>
	</div>
</div>

<script src="/js/detail.js"></script>

<%@include file="../include/footer.jsp"%>



