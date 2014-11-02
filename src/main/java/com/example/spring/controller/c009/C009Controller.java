package com.example.spring.controller.c009;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c009")
public class C009Controller {
	@RequestMapping("/entityForm")
	public String entityForm() {
		return "c009/entityForm";
	}

	@RequestMapping(value = "/entityRecv", method = RequestMethod.POST)
	public String entityRecv(HttpEntity<String> httpEntity, Model model) {
		model.addAttribute("body", httpEntity.getBody());
		return "c009/entityRecv";
	}
}
