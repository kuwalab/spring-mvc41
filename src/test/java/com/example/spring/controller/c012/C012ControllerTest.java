package com.example.spring.controller.c012;

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
public class C012ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void bookFormへのGET() throws Exception {
		mockMvc.perform(get("/c012/bookForm")).andExpect(status().isOk())
				.andExpect(view().name("c012/bookForm"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void bookRecvへのPOST_NOT_NULL() throws Exception {
		mockMvc.perform(
				post("/c012/bookRecv").param("name", "よくわかるSpring").param(
						"price", "1000")).andExpect(status().isOk())
				.andExpect(view().name("c012/bookRecv"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void bookRecvへのPOST_nameがnull() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/c012/bookRecv").param("price", "1000"))
				.andExpect(status().isOk())
				.andExpect(view().name("c012/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("c012Model", "name"))
				.andExpect(model().attributeExists("c012Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c012ModelObject = model.get("c012Model");
		assertThat(c012ModelObject, is(notNullValue()));
		assertThat(c012ModelObject, is(instanceOf(C012Model.class)));
		C012Model c012Model = (C012Model) c012ModelObject;
		assertThat(c012Model.getName(), is(nullValue()));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c012Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkField(bindingResult, "name", "NotNull");
	}

	@Test
	public void bookRecvへのPOST_nameとpriceがnull() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/c012/bookRecv"))
				.andExpect(status().isOk())
				.andExpect(view().name("c012/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(2))
				.andExpect(model().attributeHasFieldErrors("c012Model", "name"))
				.andExpect(
						model().attributeHasFieldErrors("c012Model", "price"))
				.andExpect(model().attributeExists("c012Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c012ModelObject = model.get("c012Model");
		assertThat(c012ModelObject, is(notNullValue()));
		assertThat(c012ModelObject, is(instanceOf(C012Model.class)));
		C012Model c012Model = (C012Model) c012ModelObject;
		assertThat(c012Model.getName(), is(nullValue()));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c012Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkField(bindingResult, "name", "NotNull");
		checkField(bindingResult, "price", "NotNull");
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
