package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.util.Script;

@Controller
public class PostController {

	private final PostRepository postrepository;
	private final HttpSession session;

	public PostController(PostRepository postrepository, HttpSession session) {
		this.postrepository = postrepository;
		this.session = session;
	}

	@GetMapping({ "/", "/post" })
	public String list(Model model) {// model = request

		model.addAttribute("postsEntity", postrepository.findAll());
		return "post/list"; // vViewResolver의 도움 + RequestDispatcher (request유지 기법)
	}

	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {

		Post postEntity = postrepository.findById(id).get();
		model.addAttribute("postEntity", postEntity);

		return "post/detail";
	}

	@PostMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id, Model model) {
		// 1. 권한 체크(post id를 통해 user id를 찾아서 session의 user id를 비교)
		// 2. {id}값으로 삭제

		// 1-1 세션에 userid찾기
		int userId = ((User) session.getAttribute("principal")).getId();
		// user오브젝트로 만들기위해서 괄호로 묶는 문법이있다.

		// 1-2 post의 user id찾기
		Post post = postrepository.findById(id).get();//이런건 어떻게 처리하지?
		if (userId == post.getUser().getId()) {
			postrepository.deleteById(id);
			return Script.href("/");
		} else {
			return Script.back("삭제 실패");
		}

	}
	@GetMapping("/post/saveForm")
	public String saveForm() {
		//1.인증 체크

		return"/post/saveForm";
	}
	@PostMapping("/post/{id}")
	public String savePost(@PathVariable int id, String title, String content){
	
		User user = (User)session.getAttribute("principal");
		
		if(user != null) {
		Post postEntity = postrepository.findById(id).get();
		postEntity.setTitle(title);
		postEntity.setContent(content);
		postEntity.setUser(user);
		postrepository.save(postEntity);
		
		session.setAttribute("principal", postEntity);
		return "";
		}
		return"";
	}
	
}
