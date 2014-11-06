package com.example.spring.controller.c021;

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
public class C021ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void branchFormのGET() throws Exception {
		mockMvc.perform(get("/c021/branchForm")).andExpect(status().isOk())
				.andExpect(view().name("c021/branchForm"));
	}

	@Test
	public void branch1のPOST() throws Exception {
		mockMvc.perform(post("/c021/branchRecv").param("branch1", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("c021/branch1"));
	}

	@Test
	public void branch2のPOST() throws Exception {
		mockMvc.perform(post("/c021/branchRecv").param("branch2", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("c021/branch2"));
	}

	@Test
	public void branch3のPOST() throws Exception {
		mockMvc.perform(post("/c021/branchRecv").param("branch3", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("c021/branch3"));
	}

	@Test
	public void branch4のPOST() throws Exception {
		mockMvc.perform(post("/c021/branchRecv").param("branch4", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("c021/branch4"));
	}
}
