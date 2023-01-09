package com.loja.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.loja.io.LeitorDeArquivo;

public class MenssagensProntas {
	
	private String menuInicial;
	private String informacoesCompra;
	private String informacoesCompraPagamento;
	private String informacoesVenda;
	
	public MenssagensProntas() {
		try { 
			
			URL pastaAtualUrl = this.getClass().getResource("");
			File pastaAtual = null;
			try {
				pastaAtual = new File(pastaAtualUrl.toURI().getPath())   // Pasta "util"
										.getParentFile()  // Pasta "loja"
										.getParentFile()  // Pasta "com"
										.getParentFile()  // Pasta "bin"
										.getParentFile(); // Pasta "AppSimple"
			} catch (URISyntaxException e) {
				System.err.println("Erro ao tentar localizar arquivos de menssagens: " + e.getMessage());
			}
			
			String caminho = System.getProperty("user.dir");
			//String caminho = pastaAtual.toString();
			
			//String caminho = getClass().getResourceAsStream(""));
			//System.out.println(caminho);
			
			this.menuInicial = LeitorDeArquivo.leArquivoCompleto(caminho + "/resources/MenuInicial.txt");
			
			List<String> linhasInformacoesCompra = LeitorDeArquivo.pegaLinhasArquivoCompleto(caminho + "/resources/MapaDeTelas.txt");		
			
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
