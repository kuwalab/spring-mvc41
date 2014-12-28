package com.example.spring.controller.c029;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c029")
public class C029Controller {
	@Autowired
	private C029Model c029Model;

	@RequestMapping("/sessionStart")
	public String sessionStart(Model model) {
		c029Model.setName("よくわかるHttpSession");
		c029Model.setPrice(980);
		model.addAttribute("c029Model", c029Model);
		return "c029/sessionScope";
	}

	@RequestMapping("/sessionScope")
	public String sessionScope(Model model) {
		model.addAttribute("c029Model", c029Model);
		return "c029/sessionScope";
	}
}
