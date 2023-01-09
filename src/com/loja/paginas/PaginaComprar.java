package com.loja.paginas;

import java.text.DecimalFormat;

import com.loja.controles.Sistema;
import com.loja.excessoes.CadastrarException;
import com.loja.io.LeitorDeEntrada;
import com.loja.model.CompradorAnonimo;
import com.loja.model.Item;
import com.loja.model.Item.Status;
import com.loja.model.Produto;
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
	private DecimalFormat formatadorDePreco;
	
	public PaginaComprar(MenssagensProntas menssagens, LeitorDeEntrada leitor, Sistema sistema) {
		this.menssagens = menssagens;
		this.leitor = leitor;
		this.sistema = sistema;
		this.formatadorDePreco = new DecimalFormat("###,###,###,###.00");
	}
	
	public void iniciaPaginaDeCompra() {
		
		if (this.sistema.getItens().length == 0) {
			System.out.println("\nNao ha itens cadastrados!\n");
			return;
		}
		
        int opcao = -1;
        int selecionado;
        Item itemEncontrado;
        
        while (opcao != 0) {
            System.out.println("\n\n\n\n\n");
            System.out.println("<- [0]      MENU DE COMPRA");

            listarTodosOsItens();

            System.out.println(".........................................");
            System.out.println("""                     
                Selecione item atraves do ID
                ou "0" para sair.
                """);

            selecionado = leitor.proximoInteiro("\nSelecione: ");

            if (selecionado == 0) // Para sair, por padrao, os ids do banco de dados comecam do 1
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
	        System.out.println("Id: "      + p.getId() + " | Titulo: " + p.getTitulo());
	        System.out.println("Valor: R$" + formatadorDePreco.format(p.getPreco()));
	        System.out.println("Status: "  + p.getStatus());
		}

	}

	private void subPaginaDeConfirmacaoDeCompra(Item itemEncontrado) {
		
		VendedorAnonimo vendedor = sistema.retornaVendedor(itemEncontrado.getIdVendedor());
		Produto produto = sistema.retornaProduto(itemEncontrado.getId());
		
		// Apresenta informações do produto

		System.out.println(menssagens.getInformacoesCompra());

		System.out.println("Detalhes do produto");
		System.out.println("..............................................................");
		System.out.println(
				"Id: "                       + itemEncontrado.getId()        
										     + " | Titulo: " + itemEncontrado.getTitulo()          + "\n" +
		        "Descrição: "                + itemEncontrado.getDescricao()                       + "\n" +
		        "Link de amostra de dados: " + produto.getLinkAmostraDados()                       + "\n" +
		        "Valor: "                    + formatadorDePreco.format(itemEncontrado.getPreco()) 
		        						     + " | Status [" + itemEncontrado.getStatus()          + "] \n" +
		        "Autor: "                    + vendedor.getPseudonimo()                            + "\n" +
		        "Método de pagamento: "      + vendedor.getDadosPagamento());

		// Info fim
		System.out.println("<- [0]                      [1] Confirmar");
		System.out.println("    Deseja continuar a compra?");
		
		int opcao = leitor.proximoInteiro("\nSelecione: ");

		if (opcao == 1)
		    realizaCompra(itemEncontrado, vendedor, produto);

	}

	private void realizaCompra(Item itemEncontrado, VendedorAnonimo vendedor, Produto produto) {
		System.out.println(menssagens.getInformacoesCompraPagamento());

		String pseudonimoComprador = leitor.proximoTexto("\nInsira um pseudonome de identificação: ");
		//String email               = leitor.proximoTexto("\nInsira um email para contato: ");
		String pagamento           = leitor.proximoTexto("\nInsira comprovate de pagamento: ");
		
		try {

			this.sistema.cadastrarComprador(new CompradorAnonimo(-1, pseudonimoComprador, pagamento));
		    
		    itemEncontrado.setStatus(Status.VENDIDO);

		    System.out.println("\nCompra realizada. Contate o autor para saber mais:"); // Em breve você receberá um email do autor\n");
		    System.out.println("Contato do vendedor: " + vendedor.getContato());
		    System.out.println("Link dos dados: " + produto.getLinkDados());
		    System.out.println("\n\n");
		    
		} catch (CadastrarException e) {
			System.err.println(e.getMessage());
		}

	}

}
