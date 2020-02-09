<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="post" items="${posts}">
			<tr>
				<td>${post.id}</td>
				<td><a href="/post/detail/${post.id}">${post.title}</a></td>
				<td><a href="/post/detail/${post.id}">${post.content}</a></td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>
</div>

<%@include file="../include/footer.jsp"%>



