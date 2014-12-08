package com.example.spring.controller.c039;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/c039")
public class C039Controller {
	@RequestMapping("/radiobuttons")
	public String radiobuttons(Model model) {
		List<C039Model> c039ModelList = new ArrayList<>();

		c039ModelList.add(new C039Model("123", "よく分かるSpring"));
		c039ModelList.add(new C039Model("456", "よく分かるJava"));
		c039ModelList.add(new C039Model("789", "よく分かるSpring MVC"));

		model.addAttribute("c039ModelList", c039ModelList);

		C039Form c039Form = new C039Form();
		c039Form.setSelectedIsbn("456");
		model.addAttribute("c039Form", c039Form);
		return "c039/radiobuttons";
	}

	@RequestMapping("/radiobuttonsRecv")
	public String radiobuttonsRecv(
			@RequestParam(required = false) String selectedIsbn, Model model) {
		model.addAttribute("isbn", selectedIsbn);
		return "c039/radiobuttonsRecv";
	}
}