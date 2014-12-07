package com.example.spring.controller.c037;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/c037")
public class C037Controller {
	@RequestMapping("/checkboxes")
	public String checkboxes(Model model) {
		List<C037Model> c037ModelList = new ArrayList<>();

		c037ModelList.add(new C037Model("123", "よく分かるSpring"));
		c037ModelList.add(new C037Model("456", "よく分かるJava"));
		c037ModelList.add(new C037Model("789", "よく分かるSpring MVC"));

		model.addAttribute("c037ModelList", c037ModelList);

		C037Form c037Form = new C037Form();
		c037Form.setSelectedIsbn(new String[] { "456" });
		model.addAttribute("c037Form", c037Form);
		return "c037/checkboxes";
	}

	@RequestMapping("/checkboxesRecv")
	public String checkboxesRecv(
			@RequestParam(required = false) String[] selectedIsbn, Model model) {
		model.addAttribute("isbns", selectedIsbn);
		return "c037/checkboxesRecv";
	}
}