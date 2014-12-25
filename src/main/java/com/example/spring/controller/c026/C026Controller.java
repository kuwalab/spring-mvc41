package com.example.spring.controller.c026;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/c026")
public class C026Controller {
	@RequestMapping("/uploadForm")
	public String uploadForm(Model model) {
		System.out.println("#########");
		return "c026/uploadForm";
	}

	@RequestMapping(value = "/uploadRecv", method = RequestMethod.POST)
	public String uploadRecv(@RequestParam String test,
			@RequestParam MultipartFile file, Model model) throws IOException {
		model.addAttribute("test", test);
		Path path = Paths.get(System.getProperty("java.io.tmpdir"),
				file.getOriginalFilename());
		file.transferTo(path.toFile());
		model.addAttribute("fileName", path);

		return "c026/uploadRecv";
	}

	@RequestMapping(value = "/error")
	public String error(HttpServletRequest request, Model model) {
		String uploadUrl = request.getParameter("uploadUrl");
		model.addAttribute("errorMessage", "ファイルサイズが大きすぎます");
		return "forward:" + uploadUrl;
	}
}
