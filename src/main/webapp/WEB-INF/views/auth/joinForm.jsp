<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/join" method="post">
		<div class="form-group">
			<label for="username">username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username"/>
		</div>
		<div class="form-group">
			<label for="password">password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password"/>
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter Email address" id="Email address" name="email"/>
		</div>
		<div class="form-group">
			<label for="pwd">address:</label> <input type="text" class="form-control" placeholder="Enter address" id="address" name="address"/>
		</div>
		
		<button type="submit" class="btn btn-primary">회원가입 하기</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>