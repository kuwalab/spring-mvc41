package com.example.spring.controller.c020;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C020ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void bookFormへのGET() throws Exception {
		mockMvc.perform(get("/c020/bookForm")).andExpect(status().isOk())
				.andExpect(view().name("c020/bookForm"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void bookRecvへのPOST_定価が価格より高い() throws Exception {
		mockMvc.perform(
				post("/c020/bookRecv").param("name", "よくわかるSpring")
						.param("price", "1000").param("listPrice", "1100"))
				.andExpect(status().isOk())
				.andExpect(view().name("c020/bookRecv"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void bookRecvへのPOST_価格が定価より高い() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c020/bookRecv").param("name", "よく分かるSpring")
								.param("price", "1000")
								.param("listPrice", "900"))
				.andExpect(status().isOk())
				.andExpect(view().name("c020/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c020Model",
								"validPrice"))
				.andExpect(model().attributeExists("c020Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c020ModelObject = model.get("c020Model");
		assertThat(c020ModelObject, is(notNullValue()));
		assertThat(c020ModelObject, is(instanceOf(C020Model.class)));
		C020Model c020Model = (C020Model) c020ModelObject;
		assertThat(c020Model.getName(), is("よく分かるSpring"));
		assertThat(c020Model.getPrice(), is(1000));
		assertThat(c020Model.getListPrice(), is(900));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c020Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkField(bindingResult, "validPrice", "AssertTrue");
	}

	private void checkField(BindingResult bindingResult, String fieldName,
			String errorCode) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is(errorCode));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(1));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
	}
}
