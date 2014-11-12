package com.example.spring.controller.c029;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class C029Controller {
	@RequestMapping("/c029/sessionScope1")
	public String sessionScope1(HttpSession session, WebRequest webRequest) {
		session.setAttribute("session1", "httpSession");
		webRequest.setAttribute("session2", "webRequest",
				WebRequest.SCOPE_SESSION);

		return "c029/sessionScope";
	}

	@RequestMapping("/c029/sessionScope2")
	public String sessionScope2() {
		return "c029/sessionScope";
	}

	@RequestMapping("/c029/sessionScope3")
	public String sessionScope3(HttpSession session) {
		session.invalidate();
		return "c029/sessionScope";
	}
}
