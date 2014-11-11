package com.example.spring.controller.c028;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class C028Controller {
	@RequestMapping("/c028/requestScope1")
	public String requestScope1(C028Model c028Model) {
		c028Model.setName("よくわかるSpring");
		c028Model.setPrice(2900);
		return "c028/requestScope1";
	}

	@RequestMapping("/c028/requestScope2")
	public String requestScope2() {
		return "c028/requestScope2";
	}
}
