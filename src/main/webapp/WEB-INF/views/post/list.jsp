<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/nav.jsp"%>

<div class="container">
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일자</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="post" items="${posts}">
			<tr>
				<td>${post.id}</td>
				<td><a href="/post/detail/${post.id}">${post.title}</a></td>
				<td>${post.content}</td>
				<td>${post.username}</td>
				<td><fmt:formatDate value="${post.createDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>
</div>

<%@include file="../include/footer.jsp"%>



