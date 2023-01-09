package com.loja.paginas;

import com.loja.controles.Sistema;
import com.loja.io.LeitorDeEntrada;
import com.loja.util.MenssagensProntas;

public class PaginaPrincipal {

	private MenssagensProntas menssagens;
	private Sistema sistema;
	private LeitorDeEntrada leitor;
	
	public PaginaPrincipal() {
		this.menssagens = new MenssagensProntas();
		this.sistema = new Sistema();
		this.leitor = new LeitorDeEntrada();
	}

	public void iniciaPaginaPrincipal() {
		
		int opcao = -1;
		
		while (opcao != 0) {
			
			opcao = leitor.proximoInteiro("\n" + menssagens.getMenuInicial() + " ");
			
			switch (opcao) {
			
			case 1: 
				new PaginaComprar(menssagens, leitor, sistema).iniciaPaginaDeCompra();
				break;
			case 2:
				new PaginaVender(menssagens, leitor, sistema).iniciaPaginaDeVenda();
				break;
			case 0:
				this.sistema.fecharSistema();
				return;
			default:
				System.err.println("\nOpcao invalida\n");
			}
		
		}

	}
	
}
