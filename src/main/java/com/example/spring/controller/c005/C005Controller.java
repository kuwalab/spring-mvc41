package com.example.spring.controller.c005;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c005")
public class C005Controller {
	@RequestMapping(value = "/pathVar1/{foo}/{bar}", method = RequestMethod.GET)
	public String pathVar1(@PathVariable String foo, @PathVariable String bar) {
		return "c005/pathVar";
	}

	@RequestMapping(value = "/pathVar2/{bar1}/{foo1}", method = RequestMethod.GET)
	public String pathVar2(@PathVariable("bar1") String bar,
			@PathVariable("foo1") String foo) {
		return "c005/pathVar";
	}

	@RequestMapping(value = "/pathVar3/{foo}/param/{bar}", method = RequestMethod.GET)
	public String pathVar3(@PathVariable String foo, @PathVariable String bar) {
		return "c005/pathVar";
	}
}
