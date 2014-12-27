package com.example.spring.controller.c028;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/c028")
public class C028Controller {
	@RequestMapping("/sessionStart")
	public String sessionScope1(HttpSession session, WebRequest webRequest) {
		session.setAttribute("session1", "httpSession");
		webRequest.setAttribute("session2", "webRequest",
				WebRequest.SCOPE_SESSION);

		return "c028/sessionScope";
	}

	@RequestMapping("/sessionScope")
	public String sessionScope2() {
		return "c028/sessionScope";
	}

	@RequestMapping("/sessionClear")
	public String sessionScope3(HttpSession session) {
		session.invalidate();
		return "c028/sessionScope";
	}
}
