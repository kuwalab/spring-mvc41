package com.example.spring.controller.c037;

public class C037Model {
	private String isbn;
	private String name;

	public C037Model(String isbn, String name) {
		this.isbn = isbn;
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
