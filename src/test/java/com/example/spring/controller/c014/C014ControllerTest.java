package com.example.spring.controller.c014;

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
public class C014ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void bookRecvへのPOST_priceが1() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c014/bookRecv").param("name", "よく分かるSpring")
								.param("price", "1"))
				.andExpect(status().isOk())
				.andExpect(view().name("c014/bookRecv"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("c014Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c014ModelObject = model.get("c014Model");
		assertThat(c014ModelObject, is(notNullValue()));
		assertThat(c014ModelObject, is(instanceOf(C014Model.class)));
		C014Model c014Model = (C014Model) c014ModelObject;
		assertThat(c014Model.getName(), is("よく分かるSpring"));
		assertThat(c014Model.getPrice(), is(1));
	}

	@Test
	public void bookRecvへのPOST_priceが0() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c014/bookRecv").param("name", "よく分かるSpring")
								.param("price", "0"))
				.andExpect(status().isOk())
				.andExpect(view().name("c014/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c014Model", "price"))
				.andExpect(model().attributeExists("c014Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c014ModelObject = model.get("c014Model");
		assertThat(c014ModelObject, is(notNullValue()));
		assertThat(c014ModelObject, is(instanceOf(C014Model.class)));
		C014Model c014Model = (C014Model) c014ModelObject;
		assertThat(c014Model.getName(), is("よく分かるSpring"));
		assertThat(c014Model.getPrice(), is(0));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c014Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkMinMaxField(bindingResult, "price", "Min", 1L);
	}

	@Test
	public void bookRecvへのPOST_priceが100000() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c014/bookRecv").param("name", "よく分かるSpring")
								.param("price", "100001"))
				.andExpect(status().isOk())
				.andExpect(view().name("c014/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c014Model", "price"))
				.andExpect(model().attributeExists("c014Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c014ModelObject = model.get("c014Model");
		assertThat(c014ModelObject, is(notNullValue()));
		assertThat(c014ModelObject, is(instanceOf(C014Model.class)));
		C014Model c014Model = (C014Model) c014ModelObject;
		assertThat(c014Model.getName(), is("よく分かるSpring"));
		assertThat(c014Model.getPrice(), is(100001));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c014Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkMinMaxField(bindingResult, "price", "Max", 100000L);
	}

	private void checkMinMaxField(BindingResult bindingResult,
			String fieldName, String errorCode, Long value) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is(errorCode));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(2));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
		// value
		assertThat(args[1], is(instanceOf(Long.class)));
		assertThat(args[1], is(value));
	}
}
