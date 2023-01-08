package com.loja.model;

public class Item {

	public static enum Status {
		DISPONIVEL("Disponivel"),
		VENDIDO("Vendido");

		private final String nome;
		
		Status(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}
		
	}
	
    // Declarações
    private final int id;
    private String titulo;
    private double preco;
    private Status status;
    private String descricao;
    private final int idVendedor;
    
    // Declarações fim

    public Item(int id, 
		    String titulo, 
		    double preco, 
		    Status status, 
		    String descricao, 
		    int    idVendedor) {
        this.id             = id;
        this.titulo         = titulo;
        this.preco          = preco;
        this.status         = status;
        this.descricao      = descricao;
        this.idVendedor     = idVendedor;
    }

    // Getters e setters

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public int getIdVendedor() {
		return idVendedor;
	}

    // Get e set Fim

}
