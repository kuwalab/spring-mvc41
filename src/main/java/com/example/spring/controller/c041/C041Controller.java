package com.example.spring.controller.c041;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c041")
public class C041Controller {
	@RequestMapping("/option")
	public String option(Model model) {
		C041Model c041Model = new C041Model();
		c041Model.setSelectedIsbn("");
		model.addAttribute("c041Model", c041Model);
		return "c041/option";
	}
}
