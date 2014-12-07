package com.example.spring.controller.c036;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c036")
public class C036Controller {
	@RequestMapping("/checkbox")
	public String checkbox(Model model) {
		C036Model c036Model = new C036Model();
		c036Model.setCheck(true);
		model.addAttribute("c036Model", c036Model);
		return "c036/checkbox";
	}

	@RequestMapping("/checkboxRecv")
	public String checkboxRecv(String check, Model model) {
		model.addAttribute("recvData", check);
		return "c036/checkboxRecv";
	}
}
