package com.example.spring.controller.c034;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c034")
public class C034Controller {
	@RequestMapping("/password")
	public String password(Model model) {
		C034Model c034Model = new C034Model();
		;
		c034Model.setPassword("password");
		model.addAttribute("c034Model", c034Model);
		return "c034/password";
	}
}