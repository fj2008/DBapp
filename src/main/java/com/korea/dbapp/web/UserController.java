package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;
import com.korea.dbapp.util.Script;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserRepository userRepository;
	private final HttpSession session;
	//의존성 주입
	

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
	
	
		@GetMapping("/juso")
	public String jusoRequest() {
		return "juso/jusoPopup";
	}
		
		@PostMapping("/juso")
		public String jusoResponse(String roadFullAddr, String inputYn, Model model) {
			System.out.println("주소 : "+ roadFullAddr);
			model.addAttribute("roadFullAddr",roadFullAddr);
			model.addAttribute("inputYn",inputYn);
			return   "juso/jusoPopup";
		}
		
		
		@GetMapping("/user/updateForm")
		public String updateForm() {
			//1.인증과 권한을 검사해야함때.
			//2.세션값을 사용하면됨.
			
			return"user/updateForm";
		}
		
		@PostMapping("/user/{id}")//원래는 Put으로 해야한다. 나중에 자바스크립트로 Put요청하기!
		//wehere절에 걸리는상황에서 위와같은 주소를사용한다
		public String update(@PathVariable int id, String password, String address) {
			// 공통관심사
			User principal =(User)session.getAttribute("principal");
			
			
				if(id == principal.getId() && principal !=null) {//인증권한 ok
					User userEntity= userRepository.findById(id).get();
					userEntity.setPassword(password);
					userEntity.setAddress(address);
					userRepository.save(userEntity);
					session.setAttribute("principal", userEntity);
					return "redirect:/";
				}
					return "redirect:/auth/loginForm";
				
			}
			//위와같은 코드는 인증이나 권한이 필요한 모든 함수에서 꼭 체크돼야하는 사항이다.(공통관심사)
			//목적을 이루기위해서 해야되는 부가적인 것들.
			//그래서 나오는 스프링에서의 개념이 바로 AOP이다.
			//쉽게말해서 컨트롤러마다 비서를 배치하는것이다.
			//AOP공통관심사를 분리시키는것
			
		
		
}
