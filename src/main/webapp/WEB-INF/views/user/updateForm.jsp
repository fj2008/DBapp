<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/user/${principal.id}" method="post">
		<div class="form-group">
			<label for="username">username:</label> 
			<input value="${principal.username}" type="text" class="form-control" placeholder="Enter username" readonly="readonly"/>
		</div>
		<div class="form-group">
			<label for="password">password:</label>
			 <input value="${principal.password}" type="password" class="form-control" placeholder="Enter password"  name="password"   required="required"/>
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> 
			<input value="${principal.email}" type="email" class="form-control" placeholder="Enter Email address" readonly="readonly"/>
		</div>
		<input class="btn btn-info" type="button"onClick="goPopup();" value="주소찾기" />

		<div class="form-group">
			<label for="pwd">address:</label>
			 <input value="${principal.address}" type="text" class="form-control" placeholder="Enter address" id="address" name="address" readonly="readonly" />
		</div>

		<button type="submit" class="btn btn-primary">회원정보 수정</button>
	</form>
</div>
<script>
	function goPopup() {

		var pop = window.open("/juso", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}

	function jusoCallBack(roadFullAddr) {
		let addressEL = document.querySelector("#address");
		addressEL.value=roadFullAddr;
		console.log(addressEL)
	}
</script>


<%@ include file="../layout/footer.jsp"%>

