package com.korea.dbapp.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BootStrapControllerTest {

	@GetMapping("/test/boot/navbar")
	public String navbar() {
		return "/test/navBarTest";
	}
	
}
