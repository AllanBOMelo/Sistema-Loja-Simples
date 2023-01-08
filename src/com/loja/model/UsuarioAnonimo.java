package com.loja.model;

public abstract class UsuarioAnonimo {

	private final int id;
	private final String pseudonimo;
	private final String dadosPagamento;
	
	public UsuarioAnonimo(int id, String pseudonimo, String dadosPagamento) {
		this.id = id;
		this.pseudonimo = pseudonimo;
		this.dadosPagamento = dadosPagamento;
	}

	public int getId() {
		return id;
	}

	public String getPseudonimo() {
		return pseudonimo;
	}

	public String getDadosPagamento() {
		return dadosPagamento;
	}

}
