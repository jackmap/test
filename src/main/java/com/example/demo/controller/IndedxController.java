package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndedxController {

	@GetMapping({"/login","/"})
	public String Index() {
		return "login";
	}
	
	
}
