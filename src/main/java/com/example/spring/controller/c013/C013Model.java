package com.example.spring.controller.c013;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class C013Model {
	@NotNull
	private String name;
	@NotNull
	@DecimalMin("1")
	@DecimalMax(value = "100000", inclusive = false)
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
