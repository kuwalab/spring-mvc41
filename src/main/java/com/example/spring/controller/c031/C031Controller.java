package com.example.spring.controller.c031;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class C031Controller {
	@RequestMapping("/c031/flashScope1")
	public String flashScope1(RedirectAttributes attrs, Model model) {
		attrs.addFlashAttribute("flash1", "flash1");
		model.addAttribute("request1", "request1");

		return "redirect:/c031/flashScope2";
	}

	@RequestMapping("/c031/flashScope2")
	public String flashScope2() {
		return "c031/flashScope";
	}
}
