package com.example.spring.controller.c017;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C017ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void bookRecvへのPOST_nameがISBN1234567890() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c017/bookRecv").param("name", "ISBN1234567890")
								.param("price", "123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c017/bookRecv"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("c017Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c017ModelObject = model.get("c017Model");
		assertThat(c017ModelObject, is(notNullValue()));
		assertThat(c017ModelObject, is(instanceOf(C017Model.class)));
		C017Model c017Model = (C017Model) c017ModelObject;
		assertThat(c017Model.getName(), is("ISBN1234567890"));
		assertThat(c017Model.getPrice(), is(123));
	}

	@Test
	public void bookRecvへのPOST_nameがISBN123456789() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c017/bookRecv").param("name", "ISBN123456789")
								.param("price", "123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c017/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("c017Model", "name"))
				.andExpect(model().attributeExists("c017Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c017ModelObject = model.get("c017Model");
		assertThat(c017ModelObject, is(notNullValue()));
		assertThat(c017ModelObject, is(instanceOf(C017Model.class)));
		C017Model c017Model = (C017Model) c017ModelObject;
		assertThat(c017Model.getName(), is("ISBN123456789"));
		assertThat(c017Model.getPrice(), is(123));
	}
}
