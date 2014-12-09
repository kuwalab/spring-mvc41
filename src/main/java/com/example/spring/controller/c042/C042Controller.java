package com.example.spring.controller.c042;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c042")
public class C042Controller {
	@RequestMapping("/options")
	public String options(Model model) {
		List<C042Model> c042ModelList = new ArrayList<>();

		c042ModelList.add(new C042Model("123", "よく分かるSpring"));
		c042ModelList.add(new C042Model("456", "よく分かるJava"));
		c042ModelList.add(new C042Model("789", "よく分かるSpring MVC"));

		model.addAttribute("c042ModelList", c042ModelList);

		C042Form c042Form = new C042Form();
		c042Form.setSelectedIsbn("");
		model.addAttribute("c042Form", c042Form);
		return "c042/options";
	}
}
