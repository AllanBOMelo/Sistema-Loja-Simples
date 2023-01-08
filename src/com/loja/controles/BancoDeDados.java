package com.loja.controles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.loja.model.CompradorAnonimo;
import com.loja.model.Item;
import com.loja.model.Item.Status;
import com.loja.model.VendedorAnonimo;

public class BancoDeDados {

	private final String NOME_TABELA_VENDEDORES  = "Vendedores";
	private final String NOME_TABELA_ITENS       = "Itens";
	private final String NOME_TABELA_COMPRADORES = "Compradores";
	
	private Connection connection = null;
	
	public BancoDeDados() throws SQLException {
		
		// Cria conexao com banco de dados
		this.connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        
		criarTabelas();
		
		
	}
	
	private void criarTabelas() throws SQLException {
	
		List<String> nomesTabelas = retornaTabelasExistentes();
		
		Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        // Cria tabelas caso nao existam
        
        if (!nomesTabelas.contains(this.NOME_TABELA_VENDEDORES))
        	statement.executeUpdate("create table " + this.NOME_TABELA_VENDEDORES + 
        								" (id integer,"
        								+ " pseudonimo string,"
        								+ " dadosPagamento string,"
        								+ " contato string,"
        								+ " primary key(id asc)"        // Chave primaria
        								+ ")");
        
        if (!nomesTabelas.contains(this.NOME_TABELA_ITENS))
        	statement.executeUpdate("create table " + this.NOME_TABELA_ITENS + 
        								" (id integer,"
        								+ " titulo string, "
        								+ " preco real,"                                      // "real" eh o mesmo que double ou float no SQLite
        								+ " status string,"
        								+ " descricao string,"
        								+ " idVendedor integer,"
        								+ " primary key(id asc),"                            // Chave primaria
        								+ " foreign key(idVendedor) references Vendedor(id)" // Chave estrangeira
        								+ ")"); 
        
        if (!nomesTabelas.contains(this.NOME_TABELA_COMPRADORES))
        	statement.executeUpdate("create table " + this.NOME_TABELA_COMPRADORES 
        								+ " (id integer," 
        								+ " pseudonimo string,"
        								+ " dadosPagamento,"
        								+ " primary key(id asc)" // Chave primaria
        								+ ")");
	
	}
	
	private List<String> retornaTabelasExistentes() throws SQLException {
		List<String> saida = new ArrayList<>();
		
		Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
        
        while (rs.next())
      	  saida.add(rs.getString("name"));
        
		return saida;
	}

	// Cadastrar (Create)
		
	public VendedorAnonimo cadastrarVendedor(VendedorAnonimo vendedor) throws SQLException {
		
		int id = -1;
		
        // Cria query retornando no fim o id para atualizar na variavel
        String query = "insert into " + this.NOME_TABELA_VENDEDORES + "(pseudonimo, dadosPagamento, contato) values(?,?,?) returning id";
        
        // Executa
		
		PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        statement.setString(1, vendedor.getPseudonimo());
        statement.setString(2, vendedor.getDadosPagamento());
        statement.setString(3, vendedor.getContato());

        // Pega id retornada
        
        ResultSet rs = statement.executeQuery();
        
        if (rs.next())
        	id = rs.getInt("id");
		
		return new VendedorAnonimo(id, vendedor.getPseudonimo(), vendedor.getDadosPagamento(), vendedor.getContato());

	}

	public Item cadastrarItem(Item item) throws SQLException {

		int id = -1;
		
        // Cria query retornando no fim o id para atualizar na variavel
        String query = "insert into " + this.NOME_TABELA_ITENS + " (titulo, preco, status, descricao, idVendedor) values(?,?,?,?,?) returning id";
        
        // Executa
		
		PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        statement.setString(1, item.getTitulo());
		statement.setDouble(2, item.getPreco());
		statement.setString(3, item.getStatus().getNome().toUpperCase());
		statement.setString(4, item.getDescricao());
		statement.setInt   (5, item.getIdVendedor());
		
        // Pega id retornada
        
        ResultSet rs = statement.executeQuery();
        
        if (rs.next())
        	id = rs.getInt("id");

		return new Item(id, item.getTitulo(), item.getPreco(), item.getStatus(), item.getDescricao(), item.getIdVendedor());
	
	}

	public CompradorAnonimo cadastrarComprador(CompradorAnonimo comprador) throws SQLException {
		
		int id = -1;
		
        // Cria query retornando no fim o id para atualizar na variavel
        String query = "insert into " + this.NOME_TABELA_COMPRADORES + " (pseudonimo, dadosPagamento) values(?,?) returning id";
        
        // Executa
		
		PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        statement.setString(1, comprador.getPseudonimo());
        statement.setString(2, comprador.getDadosPagamento());

        // Pega id retornada
        
        ResultSet rs = statement.executeQuery();
        
        if (rs.next())
        	id = rs.getInt("id");
		
		return new CompradorAnonimo(id, comprador.getPseudonimo(), comprador.getDadosPagamento());
	
	}

	// Ler (Read)
	
	public List<VendedorAnonimo> lerTabelaVendedores() throws SQLException {

		int id;
		String pseudonimo, dadosPagamento, contato;
		
		List<VendedorAnonimo> vendedores = new ArrayList<>();

		// Executa query
		
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        ResultSet rs = statement.executeQuery("select * from " + this.NOME_TABELA_VENDEDORES);

        // Le resutados
        
        while(rs.next()) {
        	id             = rs.getInt   ("id");
        	pseudonimo     = rs.getString("pseudonimo");
        	dadosPagamento = rs.getString("dadosPagamento");
        	contato        = rs.getString("contato");
        	
        	vendedores.add(new VendedorAnonimo(id, pseudonimo, dadosPagamento, contato));
       }

		return vendedores;
	}

	public List<Item> lerTabelaItens() throws SQLException {

		int id, idVendedor;
		double preco;
		String titulo, status, descricao;
		
		List<Item> itens = new ArrayList<>();

		// Executa query
		
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        ResultSet rs = statement.executeQuery("select * from " + this.NOME_TABELA_ITENS);

        // Le resutados
        
        while(rs.next()) {
        	id         = rs.getInt   ("id");
        	titulo     = rs.getString("titulo");
        	preco      = rs.getDouble("preco");
        	status     = rs.getString("status");
        	descricao  = rs.getString("descricao");
        	idVendedor = rs.getInt   ("idVendedor");
        	
        	itens.add(new Item(id, titulo, preco, Status.valueOf(status.toUpperCase()), descricao, idVendedor));
       }

		return itens;
	}

	public List<CompradorAnonimo> lerTabelaCompradores() throws SQLException {

		int id;
		String pseudonimo, dadosPagamento;
		
		List<CompradorAnonimo> compradores = new ArrayList<>();

		// Executa query
		
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

        ResultSet rs = statement.executeQuery("select * from " + this.NOME_TABELA_COMPRADORES);

        // Le resutados
        
        while(rs.next()) {
        	id             = rs.getInt   ("id");
        	pseudonimo     = rs.getString("pseudonimo");
        	dadosPagamento = rs.getString("dadosPagamento");
        	
        	compradores.add(new CompradorAnonimo(id, pseudonimo, dadosPagamento));
       }

		return compradores;
	}

	
	// Atualizar (Update)
	
	public boolean atualizarVendedor(VendedorAnonimo vendedor) throws SQLException {
        
		String query = "update " + this.NOME_TABELA_VENDEDORES + " set pseudonimo = ?, dadosPagamento = ?, contato = ? where id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(query);
		
		statement.setString(1, vendedor.getPseudonimo());
		statement.setString(2, vendedor.getDadosPagamento());
		statement.setString(3, vendedor.getContato());
		statement.setInt   (4, vendedor.getId());
		
		statement.executeUpdate();
		
		return true;
	}
	
	public boolean atualizarItens(Item item) throws SQLException {
		String query = "update " + this.NOME_TABELA_ITENS + " set titulo = ?, preco = ?, status = ?, descricao = ?, idVendedor = ? where id = ?"; 

		PreparedStatement statement = this.connection.prepareStatement(query);
		
		statement.setString(1, item.getTitulo());
		statement.setDouble(2, item.getPreco());
		statement.setString(3, item.getStatus().getNome().toUpperCase());
		statement.setString(4, item.getDescricao());
		statement.setInt   (5, item.getIdVendedor());
		statement.setInt   (6, item.getId());
		
		statement.executeUpdate();
		
		return true;
	}
	
	public boolean atualizarComprador(CompradorAnonimo comprador) throws SQLException {

		String query = "update " + this.NOME_TABELA_COMPRADORES + " set pseudonimo = ?, dadosPagamento = ?, where id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(query);
		
		statement.setString(1, comprador.getPseudonimo());
		statement.setString(2, comprador.getDadosPagamento());
		statement.setInt   (3, comprador.getId());
		
		statement.executeUpdate();
		
		return true;
	}

	// Apagar (Delete)
	
	
	/* Uso APENAS em testes
	 * 
	 */ 
	  public void apagarTodasAsTabelas() throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Tempo limite de 30 segundos

		statement.executeUpdate("drop table if exists " + this.NOME_TABELA_VENDEDORES);
    	statement.executeUpdate("drop table if exists " + this.NOME_TABELA_ITENS);
    	statement.executeUpdate("drop table if exists " + this.NOME_TABELA_COMPRADORES);
	}
    
	// Fechar
	
	public void fecharConexao() throws SQLException {
		if (!this.connection.isClosed())
			this.connection.close();
	}
	
	// Test
	/*
	public static void main(String[] args) {
		BancoDeDados banco = null;
		try {
			banco = new BancoDeDados();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Inserir nas tabelas
		VendedorAnonimo vendedor = null;
		Item item = null;
		CompradorAnonimo comprador = null;
		try {
			vendedor  = banco.cadastrarVendedor (new VendedorAnonimo (-1, "perseu", "bitcoin:", "email@mail.com"));
			item      = banco.cadastrarItem     (new Item            (-1, "titulo", 50.0, "status", "descricao", vendedor.getId()));
			comprador = banco.cadastrarComprador(new CompradorAnonimo(-1, "jackson", "bitcoin:"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Ler tabelas
		try {
			banco.lerTabelaVendedores() .forEach(v -> System.out.println(v.getPseudonimo()));
			banco.lerTabelaItens()      .forEach(i -> System.out.println(i.getTitulo()));
			banco.lerTabelaCompradores().forEach(c -> System.out.println(c.getPseudonimo()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Atualizar linhas
		try {
			banco.cadastrarVendedor(new VendedorAnonimo(-1, "perseu3", "bitcoin:", "email@mail.com"));
			banco.cadastrarItem(new Item(-1, "titulo2", 50.0, "status", "descricao", vendedor.getId()));
			banco.cadastrarComprador(new CompradorAnonimo(-1, "jackson5", "bitcoin:"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// Ler tabelas
		try {
			banco.lerTabelaVendedores() .forEach(v -> System.out.println(v.getPseudonimo()));
			banco.lerTabelaItens()      .forEach(i -> System.out.println(i.getTitulo()));
			banco.lerTabelaCompradores().forEach(c -> System.out.println(c.getPseudonimo()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		// Apagar tabelas (Para testar, ou voce executa so esse ou so os outros, todos ao mesmo tempo da erro)
		/*try {
			banco.apagarTodasAsTabelas();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		// Fechar banco
		/*try {
			banco.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}*/
	
}
