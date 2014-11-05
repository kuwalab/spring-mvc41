package com.example.spring.controller.c016;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c016")
public class C016Controller {
	@RequestMapping("/bookForm")
	public String bookForm() {
		return "c016/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C016Model c016Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c016/bookForm";
		}
		return "c016/bookRecv";
	}
}
