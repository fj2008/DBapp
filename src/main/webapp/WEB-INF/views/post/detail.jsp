<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<c:if test="${sessionScope.principal.id == postEntity.user.id}">
		<a href="/post/${postEntity.id}/updateForm" class="btn btn-warning">수정</a>
		
		<form style="display: inline-block" onsubmit="deletePost(${postEntity.id})">
			<button id="btn-delete" class="btn btn-danger" type="submit">삭제</button>
		</form>
		
	</c:if>

	<br />
	<br />
	<div>
		<span>글 번호 : ${postEntity.id}</span> 작성자 : <span><i>${postEntity.user.username} </i></span>
	</div>
	<br />
	<div>
		<h3>${postEntity.title}</h3>
	</div>
	<hr />
	<div>
		<div>${postEntity.content}</div>
	</div>
	<hr />

	<div class="card">
		<form onsubmit="saveComment(${postEntity.id})">
			<div class="card-body">
				<textarea id="reply-text" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button class="btn btn-primary">등록</button>
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

<script src="/js/detail.js"></script>

<%@ include file="../layout/footer.jsp"%>


