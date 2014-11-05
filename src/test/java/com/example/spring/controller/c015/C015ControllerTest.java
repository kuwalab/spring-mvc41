package com.example.spring.controller.c015;

import static org.hamcrest.Matchers.*;
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
public class C015ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void bookRecvへのPOST_priceが123_45() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c015/bookRecv").param("name", "よく分かるSpring")
								.param("price", "123.45"))
				.andExpect(status().isOk())
				.andExpect(view().name("c015/bookRecv"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("c015Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c015ModelObject = model.get("c015Model");
		assertThat(c015ModelObject, is(notNullValue()));
		assertThat(c015ModelObject, is(instanceOf(C015Model.class)));
		C015Model c015Model = (C015Model) c015ModelObject;
		assertThat(c015Model.getName(), is("よく分かるSpring"));
		assertThat(c015Model.getPrice(), is(123.45));
	}

	@Test
	public void bookRecvへのPOST_priceが1234() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c015/bookRecv").param("name", "よく分かるSpring")
								.param("price", "1234"))
				.andExpect(status().isOk())
				.andExpect(view().name("c015/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c015Model", "price"))
				.andExpect(model().attributeExists("c015Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c015ModelObject = model.get("c015Model");
		assertThat(c015ModelObject, is(notNullValue()));
		assertThat(c015ModelObject, is(instanceOf(C015Model.class)));
		C015Model c015Model = (C015Model) c015ModelObject;
		assertThat(c015Model.getName(), is("よく分かるSpring"));
		assertThat(c015Model.getPrice(), is(closeTo(1234, 0.001)));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c015Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkDigitsField(bindingResult, "price", 3, 2);
	}

	@Test
	public void bookRecvへのPOST_priceが1_234() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c015/bookRecv").param("name", "よく分かるSpring")
								.param("price", "1.234"))
				.andExpect(status().isOk())
				.andExpect(view().name("c015/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c015Model", "price"))
				.andExpect(model().attributeExists("c015Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c015ModelObject = model.get("c015Model");
		assertThat(c015ModelObject, is(notNullValue()));
		assertThat(c015ModelObject, is(instanceOf(C015Model.class)));
		C015Model c015Model = (C015Model) c015ModelObject;
		assertThat(c015Model.getName(), is("よく分かるSpring"));
		assertThat(c015Model.getPrice(), is(closeTo(1.234, 0.001)));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c015Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkDigitsField(bindingResult, "price", 3, 2);
	}

	private void checkDigitsField(BindingResult bindingResult,
			String fieldName, Integer integer, Integer fraction) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is("Digits"));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(3));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
		// value
		assertThat(args[1], is(instanceOf(Integer.class)));
		assertThat(args[1], is(fraction));
		assertThat(args[2], is(instanceOf(Integer.class)));
		assertThat(args[2], is(integer));
	}
}
