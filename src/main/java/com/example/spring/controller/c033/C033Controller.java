package com.example.spring.controller.c033;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c033")
public class C033Controller {
	@RequestMapping("/input")
	public String input(Model model) {
		C033Model c033Model = new C033Model("よく分かるSpring<&>", 2000, 2500);
		model.addAttribute("c033Model", c033Model);
		return "c033/input";
	}
}