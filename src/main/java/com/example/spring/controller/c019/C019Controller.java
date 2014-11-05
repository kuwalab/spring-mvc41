package com.example.spring.controller.c019;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c019")
public class C019Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c019/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C019Model c019Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c019/bookForm";
		}
		return "c019/bookRecv";
	}
}
