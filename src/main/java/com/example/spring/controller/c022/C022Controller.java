package com.example.spring.controller.c022;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class C022Controller {
	@RequestMapping("/c022/csvInit")
	public String csvInit() {
		return "c022/csvInit";
	}

	@RequestMapping("/c022/csvDown")
	public void csvDown(HttpServletResponse response) {
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
				+ ";charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"test.csv\"");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("山田　太郎,33\r\n");
			pw.print("田中　花子,29\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
