package com.example.spring.controller.c018;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class C018Model {
	@NotBlank
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
