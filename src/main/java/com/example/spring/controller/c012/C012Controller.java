package com.example.spring.controller.c012;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c012")
public class C012Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c012/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C012Model c012Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c012/bookForm";
		}
		return "c012/bookRecv";
	}
}
