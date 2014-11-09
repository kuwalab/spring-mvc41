package com.example.spring.controller.c024;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C024Controller {
	@RequestMapping("/c024/csvInit")
	public String csvInit() {
		return "c024/csvInit";
	}

	@RequestMapping(value = "/c024/csvDown", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String csvDown3(HttpServletResponse response) {
		response.setHeader("Content-Disposition",
				"attachment; filename=\"test3.csv\"");

		String csvData = "山田　太郎,33\r\n";
		csvData = csvData + "田中　花子,29\r\n";
		return csvData;
	}
}
