package com.example.spring.controller.c033;

public class C033Model {
	private String name;
	private Integer price;
	private Integer listPrice;

	public C033Model(String name, Integer price, Integer listPrice) {
		super();
		this.name = name;
		this.price = price;
		this.listPrice = listPrice;
	}

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

	public Integer getListPrice() {
		return listPrice;
	}

	public void setListPrice(Integer listPrice) {
		this.listPrice = listPrice;
	}
}
