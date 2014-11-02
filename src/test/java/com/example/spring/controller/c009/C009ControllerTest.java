package com.example.spring.controller.c009;

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
public class C009ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void entityFormのGET() throws Exception {
		mockMvc.perform(get("/c009/entityForm")).andExpect(status().isOk())
				.andExpect(view().name("c009/entityForm"));
	}

	@Test
	public void entityRecvのPOST() throws Exception {
		mockMvc.perform(post("/c009/entityRecv").content("name=test&age=33"))
				.andExpect(status().isOk())
				.andExpect(view().name("c009/entityRecv"))
				.andExpect(model().attributeExists("body"))
				.andExpect(model().attribute("body", is("name=test&age=33")))
				.andExpect(model().attributeDoesNotExist("name", "age"));
	}
}
