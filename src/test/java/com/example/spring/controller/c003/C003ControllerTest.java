package com.example.spring.controller.c003;

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
public class C003ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void getParam_foo$abc_bar$123のGET() throws Exception {
		mockMvc.perform(get("/c003/getParam?foo=abc&bar=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c003/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("modelFoo", is("abc")))
				.andExpect(model().attribute("modelBar", is(123)))
				.andExpect(request().attribute("foo", is(nullValue())))
				.andExpect(request().attribute("bar", is(nullValue())));
	}

	@Test
	public void getParam_foo$null_bar$nullのGET() throws Exception {
		mockMvc.perform(get("/c003/getParam")).andExpect(status().isOk())
				.andExpect(view().name("c003/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("modelFoo", is("nullです")))
				.andExpect(model().attribute("modelBar", is(-9999)))
				.andExpect(request().attribute("foo", is(nullValue())))
				.andExpect(request().attribute("bar", is(nullValue())));
	}
}