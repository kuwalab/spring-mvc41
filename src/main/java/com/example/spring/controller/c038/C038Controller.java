package com.example.spring.controller.c038;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/c038")
public class C038Controller {
	@RequestMapping("/radiobutton")
	public String radiobutton(Model model) {
		C038Model c038Model = new C038Model();
		c038Model.setTel("mobile");

		model.addAttribute("c038Model", c038Model);
		return "c038/radiobutton";
	}

	@RequestMapping("/radiobuttonRecv")
	public String radiobuttonRecv(@RequestParam String tel, Model model) {
		model.addAttribute("recvData", tel);
		return "c038/radiobuttonRecv";
	}
}
