package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;
import com.korea.dbapp.util.Script;

@Controller
public class UserController {
	private final UserRepository userRepository;
	private final HttpSession session;
	//의존성 주입
	
	public UserController(UserRepository userRepository, HttpSession session) {
		super();
		this.userRepository = userRepository;
		this.session = session;
	}

//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	

	//회원가입 페이지로 가는 함수
	
	@GetMapping("/auth/joinForm")//데이터를 리턴하는게아니니 restFullAPI를 지키지않아도됨
	public String joinForm(User user){
		//팁
		//인증에 관련된 주소(회원가입페이지,로그인페이지 등등)를 설계할때는 따로 분류해서 통일시킨다.
		//우리는 auth를 붙여본다
		//위와같이하면 나중에 필터를 설정할때 인증되지 않은 접근을 막기에 용이해진다.
		
		
		
		return"auth/joinForm";
	}
	
	//로그인페이지로 가는 함수
	@GetMapping("/auth/loginForm")
	public String loginForm(){

		
		return"auth/loginForm";
	}
	
	//회원가입을 한 후 로그인페이지로 redirect
	//새로운 문법
	//redirect:/경로    
	//페이지이동
	//redirect를 쓰는이유는 데이터를 가지고가기위함.
	@PostMapping("/auth/join")
	public String join(User user) {
		userRepository.save(user);
		return"redirect:/auth/loginForm";
	}
	//@Controller는 파일을 리턴한다 경고메시지는 데이터를 리턴하는 방식이다.그래서
	//@ResponseBody 를쓰면 이 컨트롤러는  데이터를 리턴하는 RestController가 된다.
	
	@PostMapping("/auth/login")
	public @ResponseBody String login(User user) {
	User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
	if(userEntity == null) {
		return Script.back("로그인 실패");
	}else {
		session.setAttribute("principal", userEntity);//principal: 인증주체 라는뜻
		return Script.href("/");
	 	}
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
