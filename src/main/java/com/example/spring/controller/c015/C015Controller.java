package com.example.spring.controller.c015;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c015")
public class C015Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c015/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C015Model c015Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c015/bookForm";
		}
		return "c015/bookRecv";
	}
}
