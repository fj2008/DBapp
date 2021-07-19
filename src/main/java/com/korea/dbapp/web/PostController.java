package com.korea.dbapp.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.comment.Comment;
import com.korea.dbapp.domain.comment.CommentRepository;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//lombok의 어노테이션
//final필드가 붙어있는 것들은 알아서 다 di(의존성주입)을 해주는것이다.
//di를 받고싶지 않다면 final을 안붙이면 되는것이다.

@Controller
public class PostController {

	private final PostRepository postrepository;
	private final HttpSession session;
	private final CommentRepository commentRepository;


	@GetMapping({ "/", "/post" })
	public String list(Model model) {// model = request

		model.addAttribute("postsEntity", postrepository.findAll());
		return "post/list"; // vViewResolver의 도움 + RequestDispatcher (request유지 기법)
	}

	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {

		Post postEntity = postrepository.findById(id).get();
		
		model.addAttribute("postEntity", postEntity);

		List<Comment> commentsEntity = commentRepository.mfindAllByPostId(id);
		model.addAttribute("commentsEntity",commentsEntity);
		
		return "post/detail";
	}

	@DeleteMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		// 1. 권한 체크(post id를 통해 user id를 찾아서 session의 user id를 비교)
		// 2. {id}값으로 삭제

		// 1-1 세션에 userid찾기
		int userId = ((User) session.getAttribute("principal")).getId();
		// user오브젝트로 만들기위해서 괄호로 묶는 문법이있다.

		// 1-2 post의 user id찾기
		Post post = postrepository.findById(id).get();// 이런건 어떻게 처리하지?
		if (userId == post.getUser().getId()) {
			postrepository.deleteById(id);
			return "ok";
		} else {
			return "fail";
			
		}

	}

	@GetMapping("/post/saveForm")
	public String saveForm() {
		// 1.인증 체크

		return "/post/saveForm";
	}

	@PostMapping("/post")
	public String save(Post post) {

		User principal = (User) session.getAttribute("principal");
		post.setUser(principal);
		// 이렇게 하면 로그인된 사용자의 세션에 값을 비교하는데
		// jpa가 알아서 user오브젝트에 id를 가져와서 insert를 해준다
		if(principal == null) {
			
			return"/auth/loginForm";
			
		}else {
			postrepository.save(post);
			return "/post/list";
		}
		
		
	}
	
	@GetMapping("/post/{id}/updateForm")
	public String update(@PathVariable int id, Model model) {
		
		User principal = (User) session.getAttribute("principal");
		int loginId = principal.getId();
		
		Post postEntity= postrepository.findById(id).get();
		int postOwnerId = postEntity.getUser().getId();
		
		if(loginId == postOwnerId) {
				model.addAttribute("postEntity",postEntity);
				
				return"post/updateForm";
		}else {
			return"/auth/loginForm";
		}
	}

	@PutMapping("/post/{id}")
	public @ResponseBody String updateSave(@PathVariable int id ,@RequestBody Post post) {
		

		Post postEntity = postrepository.findById(id).get();
		
			postEntity.setTitle(post.getTitle());
			postEntity.setContent(post.getContent());
			postrepository.save(postEntity);
			return "ok";
		
		
	}
	
}
