package com.loja.io;

import java.util.Scanner;

public class LeitorDeEntrada {

	private Scanner scan = null;

	public LeitorDeEntrada() {
		instaciaNovoScanner();
	}

	private void instaciaNovoScanner() {
		this.scan = new Scanner(System.in);
	}
	
	public String proximoTexto(String menssagem) {
		// Essa foi a melhor forma que encontrei para limpar qualquer coisa antes de um nextLine
		this.instaciaNovoScanner();
		
		while (true) {
			System.out.print(menssagem);
			while (this.scan.hasNextLine())
				return this.scan.nextLine();
		}
	}

	public int proximoInteiro(String menssagem) {
		
		while (true) {
			System.out.print(menssagem);
			while (this.scan.hasNextInt())
				return this.scan.nextInt();
			
			limpaBufferDeEntrada();
		}
		
	}
	
	public double proximoDecimal(String menssagem) {
		
		while (true) {
			System.out.print(menssagem);
			while (this.scan.hasNextDouble())
				return this.scan.nextDouble();
			
			limpaBufferDeEntrada();
		}
	}
	
	private void limpaBufferDeEntrada() {
		if (this.scan.hasNextLine())
			this.scan.nextLine();
	}

}
