package com.example.spring.controller.c011;

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
public class C011ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void checkTypeへのGET_priceが1000() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/c011/checkType").param("price", "1000"))
				.andExpect(status().isOk())
				.andExpect(view().name("c011/checkType"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists("c011Model")).andReturn();

		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		Object c011ModelObject = model.get("c011Model");
		assertThat(c011ModelObject, is(notNullValue()));
		assertThat(c011ModelObject, is(instanceOf(C011Model.class)));
		C011Model c011Model = (C011Model) c011ModelObject;
		assertThat(c011Model.getPrice(), is(1000));
	}

	@Test
	public void checkTypeへのGET_priceがabc() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/c011/checkType").param("price", "abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c011/checkType"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(
						model().attributeHasFieldErrors("c011Model", "price"))
				.andExpect(model().attributeExists("c011Model")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object c011ModelObject = model.get("c011Model");
		assertThat(c011ModelObject, is(notNullValue()));
		assertThat(c011ModelObject, is(instanceOf(C011Model.class)));
		C011Model c011Model = (C011Model) c011ModelObject;
		assertThat(c011Model.getPrice(), is(nullValue()));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.c011Model");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors("price");
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is("typeMismatch"));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(1));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is("price"));
	}
}
