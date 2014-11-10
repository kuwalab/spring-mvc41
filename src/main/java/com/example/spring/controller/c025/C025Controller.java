package com.example.spring.controller.c025;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/c025")
public class C025Controller {
	@RequestMapping("/uploadForm")
	public String uploadForm() {
		return "c025/uploadForm";
	}

	@RequestMapping(value = "/uploadRecv", method = RequestMethod.POST)
	public String uploadRecv(@RequestParam String test,
			@RequestParam MultipartFile file, Model model) throws IOException {
		model.addAttribute("test", test);
		Path path = Paths.get(System.getProperty("java.io.tmpdir"),
				file.getOriginalFilename());
		file.transferTo(path.toFile());
		model.addAttribute("fileName", path);

		return "c025/uploadRecv";
	}
}
