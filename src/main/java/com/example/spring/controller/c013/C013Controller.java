package com.example.spring.controller.c013;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c013")
public class C013Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c013/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C013Model c013Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c013/bookForm";
		}
		return "c013/bookRecv";
	}
}
