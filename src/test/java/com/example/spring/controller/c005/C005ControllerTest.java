package com.example.spring.controller.c005;

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
public class C005ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void pathVar1_123_abcへのGET() throws Exception {
		mockMvc.perform(get("/c005/pathVar1/123/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c005/pathVar"))
				.andExpect(request().attribute("foo", is("123")))
				.andExpect(request().attribute("bar", is("abc")));
	}

	@Test
	public void pathVar2_123_abcへのGET() throws Exception {
		mockMvc.perform(get("/c005/pathVar2/123/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c005/pathVar"))
				.andExpect(request().attribute("bar1", is("123")))
				.andExpect(request().attribute("foo1", is("abc")));
	}

	@Test
	public void pathVar3_123_param_abcへのGET() throws Exception {
		mockMvc.perform(get("/c005/pathVar3/123/param/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c005/pathVar"))
				.andExpect(request().attribute("foo", is("123")))
				.andExpect(request().attribute("bar", is("abc")));
	}
}
