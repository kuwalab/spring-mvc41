package com.example.spring.controller.c035;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c035")
public class C035Controller {
	@RequestMapping("/hidden")
	public String password(Model model) {
		C035Model c035Model = new C035Model("よくわかるSpring", 2500);
		model.addAttribute("c035Model", c035Model);

		return "c035/hidden";
	}
}