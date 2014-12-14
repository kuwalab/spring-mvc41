package com.example.spring.controller.c018;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c018")
public class C018Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c018/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Validated @ModelAttribute C018Model c018Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c018/bookForm";
		}
		return "c018/bookRecv";
	}
}
