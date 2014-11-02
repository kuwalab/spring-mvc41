package com.example.spring.controller.c008;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c008")
public class C008Controller {
	@RequestMapping("/readerForm")
	public String readerForm() {
		return "c008/readerForm";
	}

	@RequestMapping(value = "/readerRecv", method = RequestMethod.POST)
	public String readerRecv(BufferedReader reader, Model model)
			throws IOException {
		model.addAttribute("body", reader.readLine());
		return "c008/readerRecv";
	}
}
