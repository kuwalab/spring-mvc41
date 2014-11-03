package com.example.spring.controller.c011;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c011")
public class C011Controller {
	@RequestMapping("/checkType")
	public String checkType(@ModelAttribute C011Model c011Model,
			BindingResult errors) {
		return "c011/checkType";
	}
}
