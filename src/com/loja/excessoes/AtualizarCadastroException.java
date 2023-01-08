package com.loja.excessoes;

public class AtualizarCadastroException extends Exception {

	public AtualizarCadastroException(String nomeDoTipoDeCadastro, Exception e) {
		super("O cadastro do \"" + nomeDoTipoDeCadastro + "\" nao existe ou desapareceu do banco de dados.", e);
	}
	
}
