package com.example.spring.controller.c016;

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
public class C016ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void bookRecvへのPOST_nameが10文字() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c016/bookRecv").param("name", "1234567890")
								.param("price", "123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c016/bookRecv"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("c016Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c016ModelObject = model.get("c016Model");
		assertThat(c016ModelObject, is(notNullValue()));
		assertThat(c016ModelObject, is(instanceOf(C016Model.class)));
		C016Model c016Model = (C016Model) c016ModelObject;
		assertThat(c016Model.getName(), is("1234567890"));
		assertThat(c016Model.getPrice(), is(123));
	}

	@Test
	public void bookRecvへのPOST_nameが9文字() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c016/bookRecv").param("name", "123456789")
								.param("price", "123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c016/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("c016Model", "name"))
				.andExpect(model().attributeExists("c016Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c016ModelObject = model.get("c016Model");
		assertThat(c016ModelObject, is(notNullValue()));
		assertThat(c016ModelObject, is(instanceOf(C016Model.class)));
		C016Model c016Model = (C016Model) c016ModelObject;
		assertThat(c016Model.getName(), is("123456789"));
		assertThat(c016Model.getPrice(), is(123));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c016Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkSizeField(bindingResult, "name", 10, 10);
	}

	@Test
	public void bookRecvへのPOST_nameが11文字() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/c016/bookRecv").param("name", "12345678901")
								.param("price", "123"))
				.andExpect(status().isOk())
				.andExpect(view().name("c016/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("c016Model", "name"))
				.andExpect(model().attributeExists("c016Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c016ModelObject = model.get("c016Model");
		assertThat(c016ModelObject, is(notNullValue()));
		assertThat(c016ModelObject, is(instanceOf(C016Model.class)));
		C016Model c016Model = (C016Model) c016ModelObject;
		assertThat(c016Model.getName(), is("12345678901"));
		assertThat(c016Model.getPrice(), is(123));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c016Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkSizeField(bindingResult, "name", 10, 10);
	}

	private void checkSizeField(BindingResult bindingResult, String fieldName,
			Integer min, Integer max) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is("Size"));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(3));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
		// value
		assertThat(args[1], is(instanceOf(Integer.class)));
		assertThat(args[1], is(max));
		assertThat(args[2], is(instanceOf(Integer.class)));
		assertThat(args[2], is(min));
	}
}
