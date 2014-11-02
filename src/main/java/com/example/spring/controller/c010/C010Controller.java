package com.example.spring.controller.c010;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c010")
public class C010Controller {
	@RequestMapping("/modelForm")
	public String modelForm() {
		return "c010/modelForm";
	}

	@RequestMapping(value = "/modelRecv", method = RequestMethod.POST)
	public String modelRecv(@ModelAttribute C010Model c010Model) {
		return "c010/modelRecv";
	}
}
