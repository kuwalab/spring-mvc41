package com.example.spring.controller.c012;

import javax.validation.constraints.NotNull;

public class C012Model {
	@NotNull
	private String name;
	@NotNull
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