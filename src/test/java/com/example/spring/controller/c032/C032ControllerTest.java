package com.example.spring.controller.c032;

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
public class C032ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void csvInitのGET() throws Exception {
		mockMvc.perform(get("/c032/csvInit")).andExpect(status().isOk())
				.andExpect(view().name("c032/csvInit"));
	}

	@Test
	public void csvDownのGET() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("よくわかるSpring,3000\r\n");
		sb.append("よくわかるJava,2980\r\n");
		mockMvc.perform(get("/c032/csvDown"))
				.andExpect(status().isOk())
				.andExpect(view().name("c032Download"))
				.andExpect(
						content().contentType(
								"application/octet-stream;charset=utf-8"))
				.andExpect(content().string(sb.toString()));
	}
}
