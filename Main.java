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
        List<Item> lista = new ArrayList<>();
        lista.add(new Item(0, "blank", "blank", 0, "blank", "blank", "blank"));
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

                        for (Item p : lista) {

                            if (p.getId() == 0) {
                                System.out.println(" ");
                            } else {
                                System.out.println("----------");
                                System.out.println("Id: " + p.getId() + " | Titulo: " + p.getTitle());
                                System.out.println("Descrição: ");
                                System.out.println(p.getDescri());
                                System.out.println("Valor: R$" + p.getPrice());
                                System.out.println("Status: " + p.getStatus());
                            }

                        }

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
                                Selling sell1 = new Selling();
                                Scanner scan2 = new Scanner(System.in);
                                String pseudc;
                                String email;

                                // Fim declarações

                                for (Item p : lista) {
                                    if (p.getId() == act) {
                                        if (p.getStatus().equals("Vendido")) {
                                            msg = "Lamentamos, o produto selecionado ja foi vendido";
                                        }
                                        else {
                                            sell1.sell2();

                                            System.out.print("Insira um pseudo de identificação: ");
                                            pseudc = scan2.nextLine();
                                            System.out.print("Insira um email para contato: ");
                                            email = scan2.nextLine();

                                            p.setComprador(pseudc);
                                            p.setCompemail(email);
                                            p.setStatus("Vendido");

                                            msg = "\nCompra realizada com sucesso \n" +
                                                    "Em breve você receberá um email com mais informações \n" +
                                                    "De antemão, você pode enviar o valor para a seguinta chave pix: \n" +
                                                    p.getAcc() + "\n";
                                        }

                                    }
                                }
                            }
                        } catch (Exception e) {
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

                    sell.sell();

                    System.out.print("Insira um Pseudonimo de identificação: ");
                    pseudo = scan1.nextLine();

                    System.out.print("Insira um titulo para seu produto: ");
                    title = scan1.nextLine();

                    System.out.print("Insira uma descrição para seu produto: ");
                    descri = scan1.nextLine();

                    System.out.print("Insira o valor do produto: R$");
                    price = scan.nextDouble();

                    System.out.print("Insira sua chave pix: ");
                    acc = scan1.nextLine();

                    id = lista.toArray().length;
                    lista.add(new Item(id, title, acc, price, pseudo, "Disponivel", descri));
                    ini.setMsg("Produto Cadastrado com Sucesso");

                    break;


            }  // Fim switch
        }// Loop principal fim
        // Corpo fim
    } // main fim
} // Main fim

