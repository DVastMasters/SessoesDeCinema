import java.util.ArrayList;
import java.util.Scanner;
    
public class CinemaUtil{
    private static boolean aberto = false;
    private static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema = new Cinema();

    public static void main(String[] args){
        int opcao=0;
        String confirmacao = "";

        do {
            limparTela();

            if(aberto) {
                System.out.println("--------------------------------------------");
                System.out.println("| -> A venda de ingressos foi iniciada! <- |");
                System.out.println("|                                          |");
                System.out.println("| 1 - Vender ingresso.                     |");
                System.out.println("| 2 - Visualizar sessões.                  |");
                System.out.println("| 3 - Visualizar faturamento.              |");
                System.out.println("| 4 - Finalizar venda de Ingressos.        |");
                System.out.println("| 5 - Encerrar.                            |");
                System.out.println("--------------------------------------------");

                System.out.print("\nDigite o número da opção desejada: ");
                try {
                    opcao = scanner.nextInt();
                } catch(java.util.InputMismatchException e) {
                    scanner.next();
                }

                switch(opcao) {
                    case 1:
                        venderIngresso();
                        break;
                    case 2:
                        lerSessoes();
                        break;
                    case 3:
                        lerFaturamento();
                        break;
                    case 4:
                        System.out.println("Ao finalizar a venda de ingressos, todas as sessões serão apagadas e o faturamento será reiniciado.");
                        
                        System.out.print("\nTem certeza que deseja continuar? [S/N]");
                        confirmacao = scanner.next();
                    
                        if(confirmacao.equals("s") || confirmacao.equals("S")) { 
                            aberto = false;
                            cinema.resetar();
                        }
                        break;

                    case 5:
                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                            
                        System.out.print("\nTem certeza que deseja continuar? [S/N]");
                        confirmacao = scanner.next();

                        if(!(confirmacao.equals("s") || confirmacao.equals("S"))) { //Se confirmacao =! "s" e confirmacao =! "S"
                            opcao = 0;
                        }
                }   

            } else {
                System.out.println("------------------------------------------------------");
                System.out.println("| -> A venda de ingressos ainda não foi iniciada! <- |");
                System.out.println("|                                                    |");
                System.out.println("| 1 - Gerenciar sessões.                             |");
                System.out.println("| 2 - Gerenciar salas.                               |");
                System.out.println("| 3 - Gerenciar filmes.                              |");
                System.out.println("| 4 - Inicar venda de ingressos.                     |");
                System.out.println("| 5 - Encerrar.                                      |");
                System.out.println("------------------------------------------------------");
                
                System.out.print("\nDigite o número da opção desejada: ");
                try {
                    opcao = scanner.nextInt();
                }catch(java.util.InputMismatchException e) {
                    scanner.next();
                }

                switch(opcao) {
                    case 1:
                        lerSessoes();
                        break;
                    case 2:
                        lerSalas();
                        break;
                    case 3:
                        lerFilmes();
                        break;
                    case 4:
                        if(cinema.getSessoes().length == 0) { //Nenhuma sessão criada.
                            System.out.println("É necessário criar uma sessão antes de iniciar as vendas");
                            pausar();   
                        } else {
                            System.out.println("\nApós iniciar as vendas não será possível modificar e nem criar sessões.");

                            System.out.println("\nTem certeza que deseja continuar? [S/N]");
                            confirmacao = scanner.next();

                            if(confirmacao.equals("s") || confirmacao.equals("S")) {
                                aberto = true;
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                        System.out.print("\nTem certeza que deseja continuar? [S/N]");
                        confirmacao = scanner.next();
                        
                        if(!(confirmacao.equals("s") || confirmacao.equals("S"))) { //Se confirmacao != "s" e confirmacao != "S"
                            opcao = 0;                           
                        }
                        System.out.println(opcao);
                }
            }

        } while(opcao!=5);

    }

    public static void definirSessao(){

    }

    public static void definirSala() {

    }

    public static void definirFilme() {

    }

    public static void modificarSessao() {

    }

    public static void modificarSala() {

    }

    public static void modificarFilme() {

    }

    public static void removerSessao() {

    }

    public static void removerSala() {

    }

    public static void removerFilme() {

    }

    public static void lerSessoes() {

    }

    public static void lerSalas() {

    }

    public static void lerFilmes() {

    }

    public static void lerFaturamento() {

    }

    public static void venderIngresso() {

    }

    public static void cancelarVenda() {

    }

    public static void limparTela() {
        for(int i=0; i<100; i++) {
            System.out.println();
        }
    }

    public static void pausar() {
        System.out.println("Aperte enter para continuar...");
        scanner.nextLine();
    }   
}
