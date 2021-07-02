package com.korea.dbapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	@GetMapping("/")
	public String home() {
		
		return"index";
	}
}
