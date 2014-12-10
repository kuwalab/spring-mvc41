package com.example.spring.controller.c043;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c043")
public class C043Controller {
	@RequestMapping("/label")
	public String label(Model model) {
		C043Model c043Model = new C043Model("123", "よく分かるSpring");
		model.addAttribute("c043Model", c043Model);
		return "c043/label";
	}
}
