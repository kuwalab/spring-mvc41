package com.example.spring.controller.c006;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c006")
public class C006Controller {
	@RequestMapping("/bodyForm")
	public String bodyForm() {
		return "c006/bodyForm";
	}

	@RequestMapping(value = "/bodyRecv", method = RequestMethod.POST)
	public String bodyRecv(@RequestBody String body, Model model) {
		model.addAttribute("body", body);
		return "c006/bodyRecv";
	}
}
