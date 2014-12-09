package com.example.spring.controller.c042;

public class C042Model {
	private String isbn;
	private String name;

	public C042Model(String isbn, String name) {
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
