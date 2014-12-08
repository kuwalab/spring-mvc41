package com.example.spring.controller.c040;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c040")
public class C040Controller {
	@RequestMapping("/select")
	public String select(Model model) {
		List<C040Model> c040ModelList = new ArrayList<>();

		c040ModelList.add(new C040Model("123", "よく分かるSpring"));
		c040ModelList.add(new C040Model("456", "よく分かるJava"));
		c040ModelList.add(new C040Model("789", "よく分かるSpring MVC"));

		model.addAttribute("c040ModelList", c040ModelList);

		C040Form c040Form = new C040Form();
		c040Form.setSelectedIsbn("456");
		model.addAttribute("c040Form", c040Form);
		return "c040/select";
	}
}
