package com.example.spring.controller.c014;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c014")
public class C014Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c014/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C014Model c013Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c014/bookForm";
		}
		return "c014/bookRecv";
	}
}
