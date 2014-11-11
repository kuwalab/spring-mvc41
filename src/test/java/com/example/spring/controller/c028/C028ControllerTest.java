package com.example.spring.controller.c028;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C028ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void requestScope1のGET() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/c028/requestScope1"))
				.andExpect(status().isOk())
				.andExpect(view().name("c028/requestScope1"))
				.andExpect(request().attribute("c028Model", is(notNullValue())))
				.andReturn();
		HttpServletRequest request = mvcResult.getRequest();
		C028Model c028Model = (C028Model) request.getAttribute("c028Model");
		assertThat(c028Model.getName(), is("よくわかるSpring"));
		assertThat(c028Model.getPrice(), is(2900));
	}

	@Test
	public void requestScope2のGET() throws Exception {
		mockMvc.perform(get("/c028/requestScope1"))
				.andExpect(status().isOk())
				.andExpect(view().name("c028/requestScope1"))
				.andExpect(request().attribute("c028Model", is(notNullValue())));
		mockMvc.perform(get("/c028/requestScope2")).andExpect(status().isOk())
				.andExpect(view().name("c028/requestScope2"))
				.andExpect(request().attribute("c028Model", is(nullValue())));

	}
}
