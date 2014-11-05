package com.example.spring.controller.c020;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c020")
public class C020Controller {
	@RequestMapping("/bookForm")
	public String bookForm(Model model) {
		model.addAttribute("c020Model", new C020Model());
		return "c020/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute C020Model c020Model,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "c020/bookForm";
		}
		return "c020/bookRecv";
	}
}
