package com.example.spring.controller.c023;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class C023Controller {
	@RequestMapping("/c023/csvInit")
	public String csvInit() {
		return "c023/csvInit";
	}

	@RequestMapping(value = "/c023/csvDown", method = RequestMethod.GET, produces = "application/octet-stream;charset=utf-8")
	public ResponseEntity<String> csvDown() {
		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.add("contet-type", MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
				+ ";utf-8");
		headers.set("Content-Disposition", "filename=\"test2.csv\"");
		String csvData = "山田　太郎,33\r\n";
		csvData = csvData + "田中　花子,29\r\n";

		return new ResponseEntity<String>(csvData, headers, HttpStatus.OK);
	}
}
