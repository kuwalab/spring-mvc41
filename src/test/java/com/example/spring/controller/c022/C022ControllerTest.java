package com.example.spring.controller.c022;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C022ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void csvInitのGET() throws Exception {
		mockMvc.perform(get("/c022/csvInit")).andExpect(status().isOk())
				.andExpect(view().name("c022/csvInit"));
	}

	@Test
	public void csvDownのGET() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("山田　太郎,33\r\n");
		sb.append("田中　花子,29\r\n");
		mockMvc.perform(get("/c022/csvDown"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								"application/octet-stream;charset=utf-8"))
				.andExpect(content().string(sb.toString()));
	}
}
