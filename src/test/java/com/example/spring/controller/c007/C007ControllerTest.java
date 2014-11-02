package com.example.spring.controller.c007;

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
public class C007ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void reqのGET() throws Exception {
		mockMvc.perform(get("/c007/req").param("foo", "foo"))
				.andExpect(status().isOk()).andExpect(view().name("c007/req"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("foo", is("foo")));
	}

	@Test
	public void req2のGET() throws Exception {
		mockMvc.perform(get("/c007/req2").param("foo", "foo"))
				.andExpect(status().isOk()).andExpect(view().name("c007/req"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("foo", is("foo")));
	}

	@Test
	public void req3のGET() throws Exception {
		mockMvc.perform(get("/c007/req3").param("foo", "foo"))
				.andExpect(status().isOk()).andExpect(view().name("c007/req"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("foo", is("foo")));
	}
}
