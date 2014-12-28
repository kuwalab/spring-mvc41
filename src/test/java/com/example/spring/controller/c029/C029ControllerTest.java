package com.example.spring.controller.c029;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
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
public class C029ControllerTest {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private MockHttpSession mockHttpSession;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void sessionStartのGET() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/c029/sessionStart").session(mockHttpSession))
				.andExpect(status().isOk())
				.andExpect(view().name("c029/sessionScope"))
				.andExpect(model().attributeExists("c029Model")).andReturn();
		checkC029Model(mvcResult);

		mvcResult = mockMvc
				.perform(get("/c029/sessionScope").session(mockHttpSession))
				.andExpect(status().isOk())
				.andExpect(view().name("c029/sessionScope"))
				.andExpect(model().attributeExists("c029Model")).andReturn();
		checkC029Model(mvcResult);
	}

	private void checkC029Model(MvcResult mvcResult) {
		// モデルデータの確認
		ModelAndView mav = mvcResult.getModelAndView();
		Object c029ModelObject = mav.getModel().get("c029Model");
		assertThat(c029ModelObject, is(notNullValue()));
		assertThat(c029ModelObject, is(instanceOf(C029Model.class)));

		C029Model c029Model = (C029Model) c029ModelObject;
		assertThat(c029Model.getName(), is("よくわかるHttpSession"));
		assertThat(c029Model.getPrice(), is(980));
	}
}
