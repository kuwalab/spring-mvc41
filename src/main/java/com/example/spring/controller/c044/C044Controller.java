package com.example.spring.controller.c044;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class C044Controller {
	@RequestMapping(value = "/c044", method = RequestMethod.GET)
	public String index() {
		return "c044/index";
	}
}
