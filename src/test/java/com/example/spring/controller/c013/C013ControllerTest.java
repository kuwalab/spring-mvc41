package com.example.spring.controller.c013;

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
public class C013ControllerTest {
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
						post("/c013/bookRecv").param("name", "よく分かるSpring")
								.param("price", "1"))
				.andExpect(status().isOk())
				.andExpect(view().name("c013/bookRecv"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("c013Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c013ModelObject = model.get("c013Model");
		assertThat(c013ModelObject, is(notNullValue()));
		assertThat(c013ModelObject, is(instanceOf(C013Model.class)));
		C013Model c013Model = (C013Model) c013ModelObject;
		assertThat(c013Model.getName(), is("よく分かるSpring"));
		assertThat(c013Model.getPrice(), is(1));
	}

	@Test
	public void bookRecvへのPOST_priceが0() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c013/bookRecv").param("name", "よく分かるSpring")
								.param("price", "0"))
				.andExpect(status().isOk())
				.andExpect(view().name("c013/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c013Model", "price"))
				.andExpect(model().attributeExists("c013Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c013ModelObject = model.get("c013Model");
		assertThat(c013ModelObject, is(notNullValue()));
		assertThat(c013ModelObject, is(instanceOf(C013Model.class)));
		C013Model c013Model = (C013Model) c013ModelObject;
		assertThat(c013Model.getName(), is("よく分かるSpring"));
		assertThat(c013Model.getPrice(), is(0));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c013Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkDecimalField(bindingResult, "price", "DecimalMin", true, "1");
	}

	@Test
	public void bookRecvへのPOST_priceが100000() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c013/bookRecv").param("name", "よく分かるSpring")
								.param("price", "100000"))
				.andExpect(status().isOk())
				.andExpect(view().name("c013/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c013Model", "price"))
				.andExpect(model().attributeExists("c013Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c013ModelObject = model.get("c013Model");
		assertThat(c013ModelObject, is(notNullValue()));
		assertThat(c013ModelObject, is(instanceOf(C013Model.class)));
		C013Model c013Model = (C013Model) c013ModelObject;
		assertThat(c013Model.getName(), is("よく分かるSpring"));
		assertThat(c013Model.getPrice(), is(100000));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c013Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkDecimalField(bindingResult, "price", "DecimalMax", false, "100000");
	}

	private void checkDecimalField(BindingResult bindingResult,
			String fieldName, String errorCode, boolean inclusive, String value) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is(errorCode));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(3));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));

		assertThat(args[1], is(instanceOf(Boolean.class)));
		assertThat(args[1], is(inclusive));

		assertThat(args[2], is(instanceOf(String.class)));
		assertThat(args[2], is(value));
	}
}
