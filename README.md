# AppSimple

Projeto de faculdade para a aula de POO.

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