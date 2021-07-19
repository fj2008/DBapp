<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<c:if test="${sessionScope.principal.id == postEntity.user.id}">
		<a href="/post/${postEntity.id}/updateForm" class="btn btn-warning">수정</a>
		<form action="/post/${postEntity.id}" onclick="deleteById(${postEntity.id})" style="display: inline-block">
			<button id="btn-delete" class="btn btn-danger" type="submit">삭제</button>
		</form>
	</c:if>
	<br /> <br />
	<div>
		<span> 글 번호 : "${postEntity.id}"</span> 작성자 : <span><i> "${postEntity.user.username}" </i></span>
	</div>
	<br />
	<div>
		<h3>"${postEntity.title}"</h3>
	</div>
	<hr />
	<div>
		<div>"${postEntity.content}"</div>
	</div>
	<hr />

	<div class="card">
		<form>
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
	<br />

	<div class="card">
		<div class="card-header">
			<b>댓글 리스트</b>
		</div>

		<ul id="reply-box" class="list-group">
			<c:forEach var="comment" items="${commentsEntity}">
				<li id="reply-${comment.id}" class="list-group-item d-flex justify-content-between">
					<div>${comment.text}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${comment.user.username} &nbsp;</div>
						<c:if test="${principal.id == comment.user.id}">
							<button class="badge" onclick="deleteComment(${comment.id})">삭제</button>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<br />
</div>
<script>

async function deleteById(postId) {

		event.preventDefault();//페이지가 새로고침이 되는것을 방지하는 함수

		let response = await
		fetch("/post/"+postId, {
			method : "delete"
		});

		let parseResponse = await
		response.text();//json()or text()
		//통신으로 받은데이터가 json이 아닌 문자열이라는뜻.
		console.log(parseResponse);

		if (parseResponse === "ok") {
			location.href = "/";// location는 화면전환을 해주는 객체
		} else {
			alert("삭제실패");
		}
	}


	async function deleteComment(commentId) {


		let response = await
		fetch("/comment/"+commentId, {
			method : "delete"
		});

		let parseResponse = await
		response.text();


		if (parseResponse === "ok") {
			//location.reload();//해당페이지 리턴
			let deleteEl =document.querySelector("reply-"+commentId);
			console.log(deleteEl);
		} else {
			alert("삭제실패");
		}
	}
</script>
<%@ include file="../layout/footer.jsp"%>
​
