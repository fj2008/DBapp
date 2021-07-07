package com.korea.dbapp.test;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.korea.dbapp.domain.user.Login;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;

import lombok.Delegate;
@RestController

public class UserApiControllerTest {

	private final UserRepository userRepository;
	public UserApiControllerTest(UserRepository userRepository, HttpSession session) {
		super();
		this.userRepository = userRepository;
		this.session = session;
	}

	private final HttpSession session;
	
	//의존성 주입
	

	
	@PostMapping("/test/user")
	public String save(User user) {
		userRepository.save(user);
		return"save ok";
	}
	
	@GetMapping("/test/user")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	//http://localhost:8000/user/2
	@GetMapping("/test/user/{id}")
	public User findById(@PathVariable int id) {
//		User userEntity = userRepository.findById(id).get();
//		System.out.println("1");
//		userEntity.getPosts().get(1).getId();
//		System.out.println("2");
		return userRepository.findById(id).get();
	}
	
	@GetMapping("/test/user/username/{username}")
	public User findByUsername(@PathVariable String username){
		return userRepository.mFindByUsername(username);
	}
	
	@PostMapping("/test/login")
	public String login(@RequestBody Login date) {
		
		User userEntity = userRepository.mLogin(date.getUsername(),date.getPassword());
		
		
		if(userEntity == null) {
			return "login fail";
		}else {
			
			return "login sucsses";
		}
		
	}
	@DeleteMapping("/test/user/{id}")
	public String deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		return"delete ok";
	}
	
	@PutMapping("/test/user/{id}")
	public String updateOne(@PathVariable int id, User user) {
		//update의 규칙 
				//1. id로 select 하기
		User userEntity = userRepository.findById(id).get();// Entity로 변수명을 지으면 db에서 데이터를 들고오는 객체라는것
				//2.받은 body 데이터로 수정하기
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		
		//3.save 하면된다. (이때 꼭 id값이 db에 존재해야 update 가 된다.)
		userRepository.save(userEntity);//수정할때쓰는 메서드 save의 특징은 id값이 들어오면 update하고
														  //없으면 insert된다.
		//위와같이 하지않으면 update하는과정에서 날라갈 수 있다.
		
		return"update ok";
		
	}
	
	@GetMapping("/user/{id}")
	public String updateForm() {
		//1.인증과 권한을 검사해야함때.
		//2.세션값을 사용하면됨.
		
		return"user/updateForm";
	}
	
	
}
