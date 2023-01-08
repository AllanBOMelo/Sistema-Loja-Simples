package com.loja.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorDeArquivo {

	public static String leArquivoCompleto(String caminhoDoArquivo) throws FileNotFoundException {

		String saida = "";
		
		List<String> linhas = LeitorDeArquivo.pegaLinhasArquivoCompleto(caminhoDoArquivo);
		
		for (String linha : linhas)
			saida += linha;
		
		return saida;
	}

	public static List<String> pegaLinhasArquivoCompleto(String caminhoDoArquivo) throws FileNotFoundException {

		List<String> linhas = new ArrayList<>();
		
		File arquivo = new File(caminhoDoArquivo.toString());
		
		// Le arquivo
		
		Scanner leitor = new Scanner(arquivo);

		while (leitor.hasNextLine())
			linhas.add("\n" + leitor.nextLine());

		leitor.close();
	
		return linhas;
		
	}
	
}
