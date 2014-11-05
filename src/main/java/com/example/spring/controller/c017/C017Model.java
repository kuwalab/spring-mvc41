package com.example.spring.controller.c017;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class C017Model {
	@NotNull
	@Pattern(regexp = "ISBN[0-9]{10}", message = "{0}はISBNを入力してください")
	private String name;
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
