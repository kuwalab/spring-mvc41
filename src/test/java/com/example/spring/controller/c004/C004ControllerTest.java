package com.example.spring.controller.c004;

import static org.hamcrest.CoreMatchers.*;
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
public class C004ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void pathVar_12345へのGET() throws Exception {
		mockMvc.perform(get("/c004/pathVar1/12345")).andExpect(status().isOk())
				.andExpect(view().name("c004/pathVar"))
				.andExpect(request().attribute("var", is("12345")));
	}

	@Test
	public void pathVar2_abcdeへのGET() throws Exception {
		mockMvc.perform(get("/c004/pathVar2/abcde")).andExpect(status().isOk())
				.andExpect(view().name("c004/pathVar"))
				.andExpect(request().attribute("var1", is("abcde")));
	}
}