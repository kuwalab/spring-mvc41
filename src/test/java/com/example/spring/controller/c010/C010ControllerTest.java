package com.example.spring.controller.c010;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C010ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void modelFormのGET() throws Exception {
		mockMvc.perform(get("/c010/modelForm")).andExpect(status().isOk())
				.andExpect(view().name("c010/modelForm"));
	}

	@Test
	public void modelRecvのPOST() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c010/modelRecv").param("name", "abc").param(
								"age", "20")).andExpect(status().isOk())
				.andExpect(view().name("c010/modelRecv"))
				.andExpect(model().attributeExists("c010Model")).andReturn();

		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		Object c010ModelObject = model.get("c010Model");
		assertThat(c010ModelObject, is(notNullValue()));
		assertThat(c010ModelObject, is(instanceOf(C010Model.class)));
		C010Model c010Model = (C010Model) c010ModelObject;
		assertThat(c010Model.getName(), is("abc"));
		assertThat(c010Model.getAge(), is("20"));
	}
}
