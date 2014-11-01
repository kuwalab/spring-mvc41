package com.example.spring.controller.c002;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/c002")
public class C002Controller {
	@RequestMapping(value = "/getParam")
	public String getParam(@RequestParam String foo, @RequestParam String bar,
			Model model) {
		model.addAttribute("modelFoo", foo);
		model.addAttribute("modelBar", bar);
		return "c002/getParam";
	}

	@RequestMapping(value = "/getParam2")
	public String getparam2(@RequestParam("foo1") String foo,
			@RequestParam("bar1") String bar, Model model) {
		model.addAttribute("modelFoo", foo);
		model.addAttribute("modelBar", bar);
		return "c002/getParam";
	}

	@RequestMapping("/getParam3")
	public String getParam3(@RequestParam(required = false) String foo,
			@RequestParam(defaultValue = "default") String bar, Model model) {
		model.addAttribute("foo", foo);
		model.addAttribute("bar", bar);
		return "c002/getParam";
	}
}
