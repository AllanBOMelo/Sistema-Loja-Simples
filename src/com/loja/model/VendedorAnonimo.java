package com.loja.model;

public class VendedorAnonimo extends UsuarioAnonimo {

	private final String contato;
	
	public VendedorAnonimo(int id, String pseudonimo, String dadosPagamento, String contato) {
		super(id, pseudonimo, dadosPagamento);
		this.contato = contato;
	}

	public String getContato() {
		return contato;
	}

}
