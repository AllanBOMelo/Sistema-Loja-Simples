package com.loja.model;

public class Produto {

	private final int id;
	private final String linkAmostraDados;
	private final String linkDados;
	
	public Produto(int id, String linkAmostraDados, String linkDados) {
		this.id = id;
		this.linkAmostraDados = linkAmostraDados;
		this.linkDados = linkDados;
	}

	public int getId() {
		return id;
	}

	public String getLinkAmostraDados() {
		return linkAmostraDados;
	}

	public String getLinkDados() {
		return linkDados;
	}
	
}
