package com.example.spring.controller.c017;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c017")
public class C017Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c017/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C017Model c017Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c017/bookForm";
		}
		return "c017/bookRecv";
	}
}
