package com.example.spring.controller.c003;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/c003")
public class C003Controller {
	@RequestMapping(value = "/getParam")
	public String getParam(@RequestParam Optional<String> foo,
			@RequestParam Optional<Integer> bar, Model model) {
		model.addAttribute("modelFoo", foo.orElse("nullです"));
		model.addAttribute("modelBar", bar.orElse(-9999));
		return "c003/getParam";
	}
}
