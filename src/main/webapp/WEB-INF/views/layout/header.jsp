<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  jstl을 사용할때 라이브러리가 있다고 해도
					톰캣에서 컴파일을 할때 jstl문법을 이해하지 못한다.
					그래서 jstl을 사용하겠다고 알려줘야 한것이다.  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>블로그</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">블로그</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${empty sessionScope.principal}">
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/post/saveForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보변경</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<br />