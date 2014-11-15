package com.example.spring.controller.c032;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.servlet.view.AbstractView;

public class C032DownloadView extends AbstractView {

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
				+ ";charset=utf-8");
		;

		response.setHeader("Content-Disposition",
				"attachment; filename=\"test.csv\"");
		try (PrintWriter pw = response.getWriter()) {
			for (C032Model c032Model : (List<C032Model>) model
					.get("c032ModelList")) {
				pw.print(c032Model.getName());
				pw.print(",");
				pw.print(c032Model.getPrice());
				pw.print("\r\n");
			}
		}
	}
}
