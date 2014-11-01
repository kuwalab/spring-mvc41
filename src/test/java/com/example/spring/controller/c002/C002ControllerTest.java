package com.example.spring.controller.c002;

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
public class C002ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void getParam_foo$abc_bar$123のGET() throws Exception {
		mockMvc.perform(get("/c002/getParam?foo=abc&bar=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c002/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("modelFoo", is("abc")))
				.andExpect(model().attribute("modelBar", is("123")))
				.andExpect(request().attribute("foo", is(nullValue())))
				.andExpect(request().attribute("bar", is(nullValue())));
	}

	@Test
	public void getParam_foo$abcのGET_パラメータ不足によるエラー() throws Exception {
		mockMvc.perform(get("/c002/getParam?foo=abc")).andExpect(
				status().isBadRequest());
	}

	@Test
	public void getParam2_foo1$abc_bar1$123のGET() throws Exception {
		mockMvc.perform(get("/c002/getParam2?foo1=abc&bar1=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c002/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("modelFoo", is("abc")))
				.andExpect(model().attribute("modelBar", is("123")))
				.andExpect(request().attribute("foo1", is(nullValue())))
				.andExpect(request().attribute("bar1", is(nullValue())));
	}

	@Test
	public void getParam3_foo$abc_bar$123のGET() throws Exception {
		mockMvc.perform(get("/c002/getParam3?foo=abc&bar=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c002/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("foo", is("abc")))
				.andExpect(model().attribute("bar", is("123")));
	}

	@Test
	public void getParam3のGET() throws Exception {
		mockMvc.perform(get("/c002/getParam3")).andExpect(status().isOk())
				.andExpect(view().name("c002/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("foo", is(nullValue())))
				.andExpect(model().attribute("bar", is("default")));
	}
}