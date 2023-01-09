package com.loja.controles;

import java.sql.SQLException;
import java.util.List;

import com.loja.excessoes.AtualizarCadastroException;
import com.loja.excessoes.CadastrarException;
import com.loja.model.CompradorAnonimo;
import com.loja.model.Item;
import com.loja.model.Produto;
import com.loja.model.VendedorAnonimo;

public class Sistema {

	private BancoDeDados banco;
	private List<VendedorAnonimo> vendedores;
	private List<Produto> produtos;
	private List<Item> itens;
	private List<CompradorAnonimo> compradores;
	
	public Sistema() {
		try {
			this.banco = new BancoDeDados();
			this.vendedores  = this.banco.lerTabelaVendedores();
			this.produtos    = this.banco.lerTabelaProdutos();
			this.itens       = this.banco.lerTabelaItens();
			this.compradores = this.banco.lerTabelaCompradores();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Sistema(BancoDeDados banco,
					List<VendedorAnonimo> vendedores,
					List<Item> itens,
					List<CompradorAnonimo> compradores) {
		this.banco       = banco;
		this.vendedores  = vendedores;
		this.itens       = itens;
		this.compradores = compradores;
	}

	// Cadastros
	
	// Eh privado pois soh eh possivel cadastrar vendedor junto com um item
	private int cadastrarVendedor(VendedorAnonimo vendedor) throws CadastrarException {
		try {
			
			// Retorna com id atualizado
			vendedor = banco.cadastrarVendedor(vendedor); 
			
			this.vendedores.add(vendedor);
			
		} catch (Exception e) {
			throw new CadastrarException("Vendedor", e);
		}
		
		return vendedor.getId();
	}
	
	// Eh privado pois soh eh possivel cadastrar produto junto com um item
	private int cadastrarProduto(Produto produto) throws CadastrarException {
		try {
			
			// Retorna com id atualizado
			produto = banco.cadastrarProduto(produto); 
			
			this.produtos.add(produto);
			
		} catch (Exception e) {
			throw new CadastrarException("Produto", e);
		}
		
		return produto.getId();
	}
	
	public int cadastrarItem(Item item, VendedorAnonimo vendedor, Produto produto) throws CadastrarException {
		try {
			
			int idVendedor = cadastrarVendedor(vendedor);
			
			int idProduto = cadastrarProduto(produto);

			// Cadastra id do vendedor no item
			item = new Item(item.getId(), item.getTitulo(), item.getDescricao(), item.getPreco(), item.getStatus(), idProduto, idVendedor);
			
			// Retorna com id atualizado
			item = banco.cadastrarItem(item);
		
			this.itens.add(item);
		
		} catch (Exception e) {
			throw new CadastrarException("Item", e);
		}
		
		return item.getId();
	}
	
	public int cadastrarComprador(CompradorAnonimo comprador) throws CadastrarException {
		try {
			comprador = banco.cadastrarComprador(comprador); // Retorna com id atualizado
			this.compradores.add(comprador);
		} catch (Exception e) {
			throw new CadastrarException("Comprador", e);
		}
		
		return comprador.getId();
	}
	
	// Atualizacoes

	public void atualizarVendedor(VendedorAnonimo vendedor) throws AtualizarCadastroException {
		
		try {
			
			if (banco.atualizarVendedor(vendedor)) // Se conseguiu atualizar
				for (int i = 0; i < this.vendedores.size(); i++)
					if (this.vendedores.get(i).getId() == vendedor.getId()) { // Acha Vendedor
						this.vendedores.set(i, vendedor);
						return; // Sai do metodo de encontrar
					}
			
		} catch (Exception e) {
			throw new AtualizarCadastroException("Vendedor", e); // Se nao encontrar
		}
	}
	
	public void atualizarItem(Item item) throws AtualizarCadastroException {
		
		try {
			
			if (banco.atualizarItens(item)) // Se conseguiu atualizar
				for (int i = 0; i < this.itens.size(); i++)
					if (this.itens.get(i).getId() == item.getId()) { // Acha item
						this.itens.set(i, item);
						return; // Sai do metodo de encontrar
					}
			
		} catch (Exception e) {
			throw new AtualizarCadastroException("Item", e); // Se nao encontrar
		}
		
	}
	
	public void atualizarComprador(CompradorAnonimo comprador) throws AtualizarCadastroException {
		
		try {
		
		if (banco.atualizarComprador(comprador)) // Se conseguiu atualizar
			for (int i = 0; i < this.compradores.size(); i++)
				if (this.compradores.get(i).getId() == comprador.getId()) { // Acha comprador
					this.compradores.set(i, comprador);
					return; // Sai do metodo de encontrar
				}
		
		} catch (Exception e) {
			throw new AtualizarCadastroException("Comprador", e); // Se nao encontrar
		}
		
	}
	
	// Fechar sistema
	
	public void fecharSistema() {
		try {
			this.banco.fecharConexao();
		} catch (SQLException e) {
			System.err.println("Um erro ocorreu ao tentar fechar o sistema: " + e.getMessage());
		}
	}
	
	// Getters
	
	/*
	 * Nota: Os metodos "get" com retorno array("[]") sao feitos dessa forma por que apenas atribuindo "toArray()" ao retorno causa exception,
	 * 	aparentemente essa eh a forma certa
	 * */
	
	public VendedorAnonimo[] getVendedores() {
		VendedorAnonimo[] vendedoresArray = new VendedorAnonimo[this.vendedores.size()];
		vendedoresArray = vendedores.toArray(vendedoresArray);
		
		return vendedoresArray;
	}

	public Produto[] getProdutos() {
		Produto[] produtosArray = new Produto[this.produtos.size()];
		produtosArray = vendedores.toArray(produtosArray);
		
		return produtosArray;
	}
	
	public Item[] getItens() {
		Item[] itensArray = new Item[itens.size()];
		itensArray = itens.toArray(itensArray);
		
		return itensArray;
	}

	public CompradorAnonimo[] getCompradores() {
		CompradorAnonimo[] compradoresArray = new CompradorAnonimo[this.compradores.size()];
		compradoresArray = compradores.toArray(compradoresArray);
		
		return compradoresArray;
	}
	
	public Produto retornaProduto(int idProduto) {
		
		for (Produto produto : this.produtos)
			if (produto.getId() == idProduto)
				return produto;
		
		return null;
	}

	public VendedorAnonimo retornaVendedor(int idVendedor) {
		
		for (VendedorAnonimo vendedor : this.vendedores)
			if (vendedor.getId() == idVendedor)
				return vendedor;
		
		return null;
	}
	
}
