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
            switch (opc){
                //Encerrar programa
                case 0:
                    a = 1;
                    break;

                // Iniciar pagina de compra
                case 1:
                    int act;

                    System.out.println("<- [0]      MENU DE COMPRA");

                    for (Item p :lista) {
                        System.out.println("----------");
                        System.out.println("Id: " + p.getId() + " | Titulo: " + p.getTitle());
                        System.out.println("Valor: R$" + p.getPrice());
                    }
                    System.out.println("""
                            .........................................
                            Insira o id do produto desejado:
                            """);
                    act = scan.nextInt();

                    lista.remove(act);
                    break;

                // Iniciar pagina de postagem de item
                case 2:
                    Selling sell = new Selling();
                    Scanner scan1 = new Scanner(System.in);
                    double id;
                    String title;
                    String acc;
                    double price;

                    sell.sell();

                    System.out.print("Insira um titulo para seu produto: ");
                    title = scan1.nextLine();

                    id = Math.random();

                    System.out.print("Insira o valor do produto: R$");
                    price = scan.nextDouble();

                    System.out.print("Insira sua chave pix: ");
                    acc = scan1.nextLine();


                    lista.add(new Item(id, title, acc, price));
                    break;

                case 3:
                    break;
            }
            // Fim switch




        }
        // Loop principal fim


        // Corpo fim




    } // main fim
} // Main fim

