package com.example.spring.controller.c001;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class C001Controller {
	@RequestMapping(value = "/c001", method = RequestMethod.GET)
	public String index() {
		return "c001/index";
	}
}
