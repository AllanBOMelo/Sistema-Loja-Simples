package com.loja.paginas;

import com.loja.controles.Sistema;
import com.loja.excessoes.CadastrarException;
import com.loja.io.LeitorDeEntrada;
import com.loja.model.CompradorAnonimo;
import com.loja.model.Item;
import com.loja.model.Item.Status;
import com.loja.model.VendedorAnonimo;
import com.loja.util.MenssagensProntas;

public class PaginaComprar {

	/*
	 * A se pensar: 
	 * 		- Adicionar id(s) de comprador às variaveis dentro do objeto item?
	 * 
	 * */
	
	private MenssagensProntas menssagens;
	private LeitorDeEntrada leitor;
	private Sistema sistema;
	
	public PaginaComprar(MenssagensProntas menssagens, LeitorDeEntrada leitor, Sistema sistema) {
		this.menssagens = menssagens;
		this.leitor = leitor;
		this.sistema = sistema;
	}
	
	public void iniciaPaginaDeCompra() {
        int opcao = 0;
        int selecionado;
        Item itemEncontrado;
        
        while (opcao != -1) {
            System.out.println("\n\n\n\n\n");
            System.out.println("<- [0]      MENU DE COMPRA");

            listarTodosOsItens();

            System.out.println(".........................................");
            System.out.println("""                     
                Selecione item atraves do ID
                ou "-1" para sair.
                """);

            selecionado = leitor.proximoInteiro("\nSelecione: ");

            if (selecionado == -1) // Sair
            	return;
            
            itemEncontrado = encontrarItemPorId(selecionado);

            if (itemEncontrado == null)
            	System.out.println("\nItem nao encontrado!\n");
            
            else if (itemEncontrado.getStatus().equals(Status.VENDIDO))
                System.out.println("Lamentamos, o produto selecionado ja foi vendido");
            
        	else if (itemEncontrado.getStatus().equals(Status.DISPONIVEL))
            	subPaginaDeConfirmacaoDeCompra(itemEncontrado);

        }
	}

	private Item encontrarItemPorId(int selecionado) {
		for (Item item : sistema.getItens())
		    if (item.getId() == selecionado)
		        return item;
		return null;
	}

	private void listarTodosOsItens() {

		for (Item p : sistema.getItens()) {
	        System.out.println("----------");
	        System.out.println("Id: " + p.getId() + " | Titulo: " + p.getTitulo());
	        System.out.println("Valor: R$" + p.getPreco());
	        System.out.println("Status: " + p.getStatus());
		}

	}

	private void subPaginaDeConfirmacaoDeCompra(Item itemEncontrado) {
		
		VendedorAnonimo vendedor = sistema.retornaVendedor(itemEncontrado.getIdVendedor());
		
		// Apresenta informações do produto

		System.out.println(menssagens.getInformacoesCompra());

		System.out.println("Detalhes do produto");
		System.out.println("..............................................................");
		System.out.println(
				"Id: "                  + itemEncontrado.getId()        + " | Titulo: " + itemEncontrado.getTitulo() + "\n" +
		        "Descrição: "           + itemEncontrado.getDescricao() + "\n" +
		        "Valor: "               + itemEncontrado.getPreco()     + " | Status [" + itemEncontrado.getStatus() + "] \n" +
		        "Autor: "               + vendedor.getPseudonimo()  + "\n" +
		        "Método de pagamento: " + vendedor.getDadosPagamento());

		// Info fim
		System.out.println("<- [0]                      [1] Confirmar");
		System.out.println("    Deseja continuar a compra?");
		
		int opcao = leitor.proximoInteiro("\nSelect: ");

		if (opcao == 1)
		    realizaCompra(itemEncontrado);

	}

	private void realizaCompra(Item itemEncontrado) {
		System.out.println(menssagens.getInformacoesCompraPagamento());

		String pseudonimoComprador = leitor.proximoTexto("\nInsira um pseudo de identificação: ");
		//String email               = leitor.proximoTexto("\nInsira um email para contato: ");
		String pagamento           = leitor.proximoTexto("\nInsira comprovate de pagamento: ");
		
		try {

			this.sistema.cadastrarComprador(new CompradorAnonimo(-1, pseudonimoComprador, pagamento));
		    
		    itemEncontrado.setStatus(Status.VENDIDO);

		    System.out.println("Compra realizada. Em breve você receberá um email do autor\n");
		
		} catch (CadastrarException e) {
			System.err.println(e.getMessage());
		}

	}

}
