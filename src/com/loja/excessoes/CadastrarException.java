package com.loja.excessoes;

public class CadastrarException extends Exception {

	public CadastrarException(String nomeDoTipoDeCadastro, Exception e) {
		super("Ocorreu um erro ao cadastar o \"" + nomeDoTipoDeCadastro + "\" no banco de dados.", e);
	}
}
