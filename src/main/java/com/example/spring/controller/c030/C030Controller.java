package com.example.spring.controller.c030;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/c030")
@SessionAttributes("scopedTarget.c030Model")
public class C030Controller {
	@Autowired
	private C030Model c030Model;

	@RequestMapping("/sessionStart")
	public String sessionStart(Model model) {
		c030Model.setName("よくわかるHttpSession");
		c030Model.setPrice(980);
		model.addAttribute("c030Model", c030Model);
		return "c030/sessionScope";
	}

	@RequestMapping("/sessionScope")
	public String sessionScope(Model model) {
		model.addAttribute("c030Model", c030Model);
		return "c030/sessionScope";
	}

	@RequestMapping("/sessionClear")
	public String sesseionClear(Model model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		model.addAttribute("c030Model", c030Model);
		return "c030/sessionScope";
	}
}
