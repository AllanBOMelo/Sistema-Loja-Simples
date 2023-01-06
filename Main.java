import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        // Declarações
        int a = 0;
        Ini ini = new Ini();
        Scanner scan = new Scanner(System.in);
        String opca;
        int opc = 5;
        List<Item> lista = new ArrayList<>(); // Declarando lista
        // Declarando um primeiro elemento que não será usado, so para preencher o index "0", pois mais a frente
        // Usa o index para estabelecer o ID do produto, e para selecionar iten. Ai o "0" é a opção de voltar.
        lista.add(new Item(0, "blank", "blank", 0, "blank", "blank", "blank", "blank"));
        // Declarações fim


        // Corpo

        // Loop principal
        while (a == 0) {
            ini.init();

            // Tratamento de exception para valor inserido diferente do esperado
            try {
                opca = scan.next();
                if (!opca.equals("0") && !opca.equals("1") && !opca.equals("2")) {
                    throw new OpcInvalidException();
                }
                else {
                    opc = Integer.parseInt(opca); //Converter valor de string para int
                }
            }
            catch (OpcInvalidException e) {
                ini.setMsg("Opção inserida invalida. Tente novamente.");
            }
            // Tratamento de exception fim

            // Inicio switch de opções para cada caso
            switch (opc) {
                //Encerrar programa
                case 0:
                    a = 1;
                    break;

                // Iniciar pagina de compra
                case 1:
                    int act;
                    int b = 0;
                    String msg = "";

                    while (b == 0) {
                        System.out.println("\n\n\n\n\n");
                        System.out.println("<- [0]      MENU DE COMPRA");

                        // Função para exibir todos os objetos armazenados na lita
                        for (Item p : lista) {

                            if (p.getId() == 0) {
                                System.out.println(" ");
                            } else {
                                System.out.println("----------");
                                System.out.println("Id: " + p.getId() + " | Titulo: " + p.getTitle());
                                System.out.println("Valor: R$" + p.getPrice());
                                System.out.println("Status: " + p.getStatus());
                            }

                        }
                        // Fim função

                        System.out.println(".........................................");
                        System.out.println(msg);
                        msg = "";
                        System.out.println("""                     
                            Selecione item atraves do ID
                            ou "0" para sair.
                            """);

                        System.out.print("Select: ");
                        act = scan.nextInt();

                        try {
                            if (act == 0) {
                                b = 1;
                            } else {
                                // declarações
                                Buy buy = new Buy();
                                Scanner scan2 = new Scanner(System.in);
                                String pseudc;
                                String email;

                                // Fim declarações

                                // Função para percorrer a lista e ao chegar no objeto desejado, exibir as proximas informações
                                // Se precisar alterar ela para o uso do banco de dados, é só reutilizar a logica dela
                                // E acredito que maior parte do codigo pode ser reutilizado nisso
                                for (Item p : lista) {
                                    if (p.getId() == act) {
                                        if (p.getStatus().equals("Vendido")) {
                                            msg = "Lamentamos, o produto selecionado ja foi vendido";
                                        }
                                        else {
                                            // Informações do produto
                                            buy.buyi();
                                            int select;

                                            System.out.println("Detalhes do produto");
                                            System.out.println("..............................................................");
                                            System.out.println("Id: " + p.getId() + " | Titulo: " + p.getTitle() + "\n" +
                                                    "Descrição: " + p.getDescri() + "\n" +
                                                    "Valor: " + p.getPrice() + " | Status [" + p.getStatus() + "] \n" +
                                                    "Autor: " + p.getPseudo() + "\n" +
                                                    "Método de pagamento: " + p.getPagmet());

                                            // Info fim
                                            System.out.println("<- [0]                      [1] Confirmar");
                                            System.out.println("    Deseja continuar a compra?");
                                            System.out.print("Select: ");
                                            select = scan.nextInt();

                                            if (select != 0 && select != 1) {
                                                throw new OpcInvalidException();
                                            }
                                            else {
                                                switch (select) {
                                                    case 1:
                                                        buy.buyc();

                                                        System.out.print("Insira um pseudo de identificação: ");
                                                        pseudc = scan2.nextLine();
                                                        System.out.print("Insira um email para contato: ");
                                                        email = scan2.nextLine();

                                                        // Adicionar dados do comprador às variaveis dentro do objeto
                                                        p.setComprador(pseudc);
                                                        p.setCompemail(email);
                                                        p.setStatus("Vendido");

                                                        msg = "Compra realizada. Em breve você receberá um email do autor\n";
                                                        break;

                                                    case 0:
                                                        break;
                                                } // fim switch
                                            } // fim else

                                        } // fim else

                                    } // Fim if
                                } // Fim função

                            }
                        }
                        catch (Exception e) {
                            System.out.println("Opção invalida, insira apenas numeros");
                        }
                    }



                    break;
                    // Iniciar pagina de postagem de item
                case 2:
                    Selling sell = new Selling();
                    Scanner scan1 = new Scanner(System.in);
                    double id;
                    String title;
                    String acc;
                    double price;
                    String pseudo;
                    String descri;
                    String pagmet;

                    sell.sell();

                    System.out.print("Insira um Pseudonimo de identificação: ");
                    pseudo = scan1.nextLine();

                    System.out.print("Insira um titulo para seu produto: ");
                    title = scan1.nextLine();

                    System.out.print("Insira uma descrição para seu produto: ");
                    descri = scan1.nextLine();

                    System.out.print("Insira o valor do produto: R$");
                    price = scan.nextDouble();

                    System.out.print("Insira o método de pagamento: ");
                    pagmet = scan1.nextLine();

                    System.out.print("Insira os dados necessarios para pagamento: ");
                    acc = scan1.nextLine();

                    // Como o primeiro item da lista esta um generico, o id dos proximos elementos é a posição do index
                    id = lista.toArray().length;
                    lista.add(new Item(id, title, acc, price, pseudo, "Disponivel", descri, pagmet)); // Adicionar elemento a lista / banco de dados

                    ini.setMsg("Produto Cadastrado com Sucesso");

                    break;


            }  // Fim switch
        }// Loop principal fim
        // Corpo fim
    } // main fim
} // Main fim

