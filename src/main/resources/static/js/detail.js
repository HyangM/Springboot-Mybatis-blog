
$('#post--delete--submit').on('click', function() {
	var id = $(this).attr('value');
	$.ajax({
		type : 'DELETE',
		url : '/post/delete/'+ id,
		dataType : 'json'
	}).done(function(r) {
		alert("글삭제 성공");
		location.href = '/';
	}).fail(function(r) {
		alert("글삭제 실패");
	});
});

$('#comment--save--submit').on('click', function() {
	var data = {
		userId: $('#userId').val(),
		postId: $('#postId').val(),
		content: $('#content').val()
	};
	
	$.ajax({
		type : 'POST',
		url : '/comment/write',
		data:JSON.stringify(data),
		contentType:'application/json; charset=utf-8',
		dataType : 'json'
	}).done(function(r) {
		if(r.status.statusCode = 200){
			alert("댓글쓰기 성공");
			makeCommentItem(r);
		}else{
			alert("댓글쓰기 실패");
		}
	}).fail(function(r) {
		alert("댓글쓰기 실패");
	});
});

	
function makeCommentItem(r){
	var comment_item = `<div id="comment--item--${r.id}">`;	
	comment_item += `<span class="comment--username">작성자: ${r.username} </span>`;		
	comment_item += `<span class="comment--content"> ${r.content} </span>`;	
	comment_item += `<button onclick="commentDelete(${r.id})">삭제</button>`;
	comment_item += `</div>`;		
	
	$('#comment--items').prepend(comment_item);
}

function commentDelete(commentId){
	
	$.ajax({
		type : 'DELETE',
		url : '/comment/delete/'+commentId,
		dataType : 'json'
	}).done(function(r) {
		if(r.statusCode = 200){
			alert("댓글삭제 성공");
			$('#comment--item--'+commentId).remove();
		}else{
			alert("댓글삭제 실패");
		}
	}).fail(function(r) {
		if(r.statusCode = 403){
			
		}else{
			alert("댓글삭제 실패");
		}
	});

}