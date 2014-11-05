package com.example.spring.controller.c015;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class C015Model {
	@NotNull
	private String name;
	@NotNull
	@Digits(integer = 3, fraction = 2)
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
