public class Ini extends Screen{

    // Inicio Tela de inicio
    public void init(){
        System.out.println("""
                
                %%%%%   %%%%%  %%%%   %%%%     %%     %%  %%%%%%  %%    %%  %%%%%     %%%%%
                %%  %%  %%     %% %% %% %%      %%   %%     %%    %%%%  %%  %%   %%  %%   %%
                %%%%    %%%%   %%  %%%  %%      %%   %%     %%    %% %% %%  %%   %%  %%   %%
                %%  %%  %%     %%       %%       %% %%      %%    %%  %%%%  %%   %%  %%   %%
                %%%%%   %%%%%  %%       %%        %%%     %%%%%%  %%    %%  %%%%%     %%%%%
                """);
        System.out.println("INSIRA O METODO DE ENTRADA");
        System.out.println(getMsg());
        System.out.println("""
                
                [1] Comprar.
                [2] Vender.
                [0] Sair.
                _______________________________________________________________________________
                """);
        System.out.print("Escolha uma opção: ");
        setMsg(" ");
    }
    // Fim tela de inicio

}
