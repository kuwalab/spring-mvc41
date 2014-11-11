package com.example.spring.controller.c027;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class C027Controller {
	@RequestMapping("/c027/requestScope")
	public String requestScope(HttpServletRequest request,
			WebRequest webRequest, Model model) {
		request.setAttribute("req1", "httpServletRequest");
		webRequest.setAttribute("req2", "webRequest", WebRequest.SCOPE_REQUEST);
		model.addAttribute("req3", "model");
		return "c027/requestScope";
	}
}
