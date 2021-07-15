<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="#" onsubmit="updatePost()">
		<div class="form-group">
			<label for="Title">Title:</label> 
			<input id="title" value="${postEntity.title}"type="text" class="form-control" placeholder="Enter Title"  name="title" required="required"/>
		</div>
		<div class="form-group">
			<textarea id="content" rows="10" class="form-control" name="content">
			${postEntity.content}
			</textarea>
			
		</div>
		<button type="submit" class="btn btn-primary">글쓰기 완료</button>
	</form>
</div>

<script>
	
	async function updatePost(){
		
		console.log(event);
		event.preventDefault();//submit 동작을 막는다.
		
		let title=document.querySelector("#title").value;
		let content=document.querySelector("#content").value;
		
		let updateDto ={
				title:title,
				content:content
		};
		let response =await fetch("/post/${postEntity.id}",{
			method:"put",
			body:JSON.stringify(updateDto),
			headers:{
				"Content-Type":"application/json; charset=utf-8"
			}
		});
		let parseResponse =await response.text();//json()or text()
		console.log(parseResponse);
		
		if(parseResponse ==="ok"){
			location.href="/post/${postEntity.id}";// location는 화면전환을 해주는 객체
		}
	}




      $('#content').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 500
      });
    </script>

<%@ include file="../layout/footer.jsp"%>