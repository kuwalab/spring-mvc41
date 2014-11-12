package com.example.spring.controller.c029;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C029ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private MockHttpSession mockHttpSession;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
		mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID
				.randomUUID().toString());
	}

	@Test
	public void requestScopeのGET() throws Exception {
		assertThat(mockHttpSession.getAttribute("session1"), is(nullValue()));
		assertThat(mockHttpSession.getAttribute("session2"), is(nullValue()));

		mockMvc.perform(get("/c029/sessionScope1").session(mockHttpSession))
				.andExpect(status().isOk())
				.andExpect(view().name("c029/sessionScope"));
		assertThat(mockHttpSession.getAttribute("session1"), is("httpSession"));
		assertThat(mockHttpSession.getAttribute("session2"), is("webRequest"));

		// セッションは維持される
		mockMvc.perform(get("/c029/sessionScope2").session(mockHttpSession))
				.andExpect(view().name("c029/sessionScope"));

		assertThat(mockHttpSession.getAttribute("session1"), is("httpSession"));
		assertThat(mockHttpSession.getAttribute("session2"), is("webRequest"));

		// セッションは破棄される
		mockMvc.perform(get("/c029/sessionScope3").session(mockHttpSession))
				.andExpect(view().name("c029/sessionScope"));

		assertThat(mockHttpSession.isInvalid(), is(true));
	}
}
