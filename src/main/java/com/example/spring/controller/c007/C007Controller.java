package com.example.spring.controller.c007;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/c007")
public class C007Controller {
	@RequestMapping("/req")
	public String req(HttpServletRequest request, Model model) {
		model.addAttribute("foo", request.getParameter("foo"));
		return "c007/req";
	}

	@RequestMapping("/req2")
	public String req2(WebRequest request, Model model) {
		model.addAttribute("foo", request.getParameter("foo"));
		return "c007/req";
	}

	@RequestMapping("/req3")
	public String req3(NativeWebRequest request, Model model) {
		model.addAttribute("foo", request.getParameter("foo"));
		return "c007/req";
	}
}
