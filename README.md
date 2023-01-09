# AppSimple

Projeto de faculdade para a aula de POO.

## Descrição do que foi pedido

Projeto da disciplina tem como objetivo o desenvolvimento de um pequeno software que aborde os conceitos de orientação a objetos utilizando a linguagem Java, o acesso a banco de dados e o tratamento de exceções que forem necessários. 

As equipes terão entre 03 e 04 integrantes. Cada membro da equipe deve desenvolver pelo menos uma funcionalidade completa. Ou seja, criar a classe de domínio, criar a funcionalidade na classe principal e criar a classe de acesso ao banco de dados (DAO) correspondente a sua funcionalidade. Além disso, deve-se realizar todo o tratamento de exceção para a funcionalidade escolhida. 

Exemplo p/ uma Funcionalidade Cadastro de usuário:

1 - Criar a classe Usuario;
2 - Criar a(s) classe(s) DAO(s) necessária(s)

3 - Criar sua funcionalidade na classe principal (Receber os dados do usuário, Criar o objeto usuário e realizar sua inserção no banco) 

Obs.: Incluindo o tratamento de exceção. 

A avaliação incluirá a apresentação código desenvolvido por cada integrante de forma individual.

## Descrição de pacotes e classes

- `com.loja`
	- `Main`: Classe que inicia a interação com a classe `PaginaPrincipal`;
- `com.loja.excessoes`
	- `CadastrarException`: Classe exception que quando um erro ocorre ao tentar cadastrar uma linha de alguma tabela;
	- `AtualizarCadastroException`: Classe exception que quando o erro ocorre ao tentar atualizar uma linha de alguma tabela;
- `com.loja.io`
	- `LeitorDeArquivo`: Lê arquivo e retorna seu conteúdo como `String` ou `List<String>`;
	- `LeitorDeEntrada`: Lê entrada do usuário, usando `Scanner`, de forma confiável e simples;
- `com.loja.model`
	- `UsuarioAnonimo`: Classe abtrata da qual todos os usuário do sistema herdam;
	- `VendedorAnonimo`: Classe que implementa `UsuarioAnonimo` adaptado para vendedores;
	- `CompradorAnonimo`: Classe que implementa `UsuarioAnonimo` adaptado para compradores;
	- `Item`: Classe que representa o item a ser vendido;
    - `Produto`: Classe que presenta os dados do item a ser vendido;
- `com.loja.controles`:
	- `BancoDeDados`: Faz todo o CRUD(Create, Read, Update, Delete) com o banco de dados;
	- `Sistema`: Classe que conecta a classe `BancoDeDados` com as ações feitas no `Main`;
- `com.loja.paginas`
	- `PaginaPrincipal`: Classe que controla as demais páginas;
	- `PaginaVender`: Classe que contém toda a interacao para o usuário vender;
	- `PaginaComprar`: Classe que contém toda a interacao para o usuário comprar;
- `com.loja.util`
	- `MenssagensProntas`: Utiliza a classe `LeitorDeArquivo` para lê as menssagens prontas para o usuário.

### Descrição de pastas e arquivos

- `/src`: Pasta onde estão todos os pacotes e suas classes;
- `/resources`: Pasta contendo arquivos utilizados em `MenssagensProntas`
	- `MenuInicial`: Primeiro menu que aparece ao entrar;
	- `MapaDeTelas`: Mapa que guia e lembra o usuário em que tela ele está.
- `/libs`: Pasta onde está a biblioteca [SQLite](https://github.com/xerial/sqlite-jdbc);
	- `sqlite-jdbc-3.40.0.0.jar`: Atual jar do [SQLite](https://github.com/xerial/sqlite-jdbc#download).
