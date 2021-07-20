package com.korea.dbapp.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//@ControllerAdvice 를 붙여주면 모든 Exception이 이 어노테이션이 있는 클래스로 찾아온다.
@ControllerAdvice
@RestController
public class ExceptionController {

	//Exception이라는 예외가 발생하면 무조건 이 컨트롤러가 실행된다
	@ExceptionHandler(Exception.class)
	public String test1(Exception e) {
		return "에러발생:"+e.getMessage();
	}
}
