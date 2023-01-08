package com.loja.util;

import java.io.FileNotFoundException;
import java.util.List;

import com.loja.io.LeitorDeArquivo;

public class MenssagensProntas {
	
	private String menuInicial;
	private String informacoesCompra;
	private String informacoesCompraPagamento;
	private String informacoesVenda;
	
	public MenssagensProntas() {
		try { 

			this.menuInicial = LeitorDeArquivo.leArquivoCompleto(System.getProperty("user.dir") + "/resources/MenuInicial.txt");
			
			List<String> linhasInformacoesCompra = LeitorDeArquivo.pegaLinhasArquivoCompleto(System.getProperty("user.dir") + "/resources/MapaDeTelas.txt");		
			
			this.informacoesCompra = this.pegaAlgumasLinhasDeArray(linhasInformacoesCompra, 0, 4);
			
			this.informacoesCompraPagamento = this.pegaAlgumasLinhasDeArray(linhasInformacoesCompra, 6, 10);
			
			this.informacoesVenda = this.pegaAlgumasLinhasDeArray(linhasInformacoesCompra, 11, 16);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String pegaAlgumasLinhasDeArray(List<String> linhas, int inicio, int fim) {
		String saida = "";
		
		for (int i = inicio; i <= fim; i++)
			saida += linhas.get(i);
		
		return saida;
	
	}
	
	public String getMenuInicial() {
		return this.menuInicial;
	}

	public String getInformacoesCompra() {
		return informacoesCompra;
	}

	public String getInformacoesCompraPagamento() {
		return informacoesCompraPagamento;
	}
	
	public String getInformacoesVenda() {
		return informacoesVenda;
	}
	
}
