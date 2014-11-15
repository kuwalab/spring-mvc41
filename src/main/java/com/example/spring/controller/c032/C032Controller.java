package com.example.spring.controller.c032;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class C032Controller {
	@RequestMapping("/c032/csvInit")
	public String csvInit() {
		return "c032/csvInit";
	}

	@RequestMapping("/c032/csvDown")
	public String csvDown4(Model model) {
		List<C032Model> c032ModelList = new ArrayList<>();

		C032Model c032Model = new C032Model();
		c032Model.setName("よくわかるSpring");
		c032Model.setPrice(3000);
		c032ModelList.add(c032Model);

		c032Model = new C032Model();
		c032Model.setName("よくわかるJava");
		c032Model.setPrice(2980);
		c032ModelList.add(c032Model);

		model.addAttribute("c032ModelList", c032ModelList);
		return "c032Download";
	}
}
