import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
    
public class CinemaUtil{
    private static boolean aberto = false;
    private static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema = new Cinema();
    private static int opcao=0;
    private static String confirmacao = "";

    public static void main(String[] args){
        
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
                }
            }

        } while(opcao!=5);

    }

    public static void lerSessoes() {
        do {
            
            if(aberto) {
            
            } else {
                if(cinema.getSessoes().length == 0) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("|           -> Gerenciamento de sessões <-           |");
                    System.out.println("|                                                    |");
                    System.out.println("| Nenhuma sessão foi criada.                         |");
                    System.out.println("|                                                    |");
                    System.out.println("| 1- Criar uma nova sessão.                          |");
                    System.out.println("| 2- Voltar.                                         |");
                    System.out.println("------------------------------------------------------");

                    System.out.print("\nDigite o número da opção desejada: ");
                    try{
                        opcao = scanner.nextInt();
                    } catch(java.util.InputMismatchException e) {
                        scanner.next();
                    }
                
                    switch(opcao){
                        case 1:
                            definirSessao();
                            break;
                        case 2:
                

                    }
                }
            }
            
        } while(opcao !=2);
    }

    public static void definirSessao(){
        Filme filme;
        Sala sala;
        int op;

        if(cinema.getFilmes().length == 0) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
            System.out.println("|                                                                             |");
            System.out.println("| Como nenhum filme foi registrado, é necessário registrar o primeiro.        |");
            System.out.println("|                                                                             |");
            System.out.println("-------------------------------------------------------------------------------");
            
            definirFilme();
        }

        if(cinema.getSalas().length == 0) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
            System.out.println("|                                                                             |");
            System.out.println("| Como nenhuma sala foi registrada, é necessário registrar a primeira.        |");
            System.out.println("|                                                                             |");
            System.out.println("-------------------------------------------------------------------------------");

            definirSala();
        }

        
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");

        lerFilmes(); //Listar filmes disponíveis.

        System.out.print("\nDigite o número correspondente ao filme que será exibido nesta sessão: ");
        op = scanner.nextInt();

        filme = cinema.getFilmes()[op-1];

        lerSalas(); //Listar salas disponíveis.
        System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
        op = scanner.nextInt();

        sala = cinema.getSalas()[op-1];

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|Filme: " + filme.getTitulo() + "                                             |");
        System.out.println("|Sala: " + sala.getNumSala() + "                                              |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\nÉ necessário definir o horário da sessão.");

        System.out.print("Por favor, informe a hora que a sessão irá acontecer (0 - 23): ");
        op = scanner.nextInt();
 
    }

    public static void definirFilme() {
        int opTemp;
        String tituloFilme = null;
        int duracao = 0;
        String tipoProducao = null;
        String[] tipoAudio = new String[3];
        boolean permite3D = false;
            
        System.out.print("\nDigite o nome do filme: ");
        tituloFilme = scanner.nextLine();

        System.out.print("\nDigite a duração do filme (em minutos): ");
        duracao = scanner.nextInt();

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|           O filme possui qual(is) tipo(s) de áudio(s) disponível(is)?       |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Original                                                                |");
        System.out.println("| 2 - Original com legenda                                                    |");
        System.out.println("| 3 - Dublado                                                                 |");
        System.out.println("| 4 - Original e original com legenda                                         |");
        System.out.println("| 5 - Original e dublado                                                      |");
        System.out.println("| 6 - Original com legenda e dublado                                          |");
        System.out.println("| 7 - Original, original com legenda e dublado                                |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        opTemp = scanner.nextInt();
            
        switch (opTemp) {
            case 1:
                tipoAudio[0] = "Original";
                break;
            case 2:
                tipoAudio[0] = "Original com legenda";
                break;
            case 3:
                tipoAudio[0] = "Dublado";
                break;
            case 4:
                tipoAudio[0] = "Original";
                tipoAudio[1] = "Original com legenda";
                break;
            case 5:
                tipoAudio[0] = "Original";
                tipoAudio[1] = "Dublado";
                break;
            case 6:
                tipoAudio[0] = "Original com legenda";
                tipoAudio[1] = "Dublado";
                break;
            case 7:
                tipoAudio[0] = ("Original");
                tipoAudio[1] = ("Original com legenda");
                tipoAudio[2] = ("Dublado");
            }

            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                    Qual o tipo de produção do filme?                        |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Nacional                                                                |");
            System.out.println("| 2 - Estrangeira                                                             |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
            opTemp = scanner.nextInt();
            
            switch(opTemp) {
                case 1:
                    tipoProducao = "Nacional";
                break;
                case 2: 
                    tipoProducao = "Estrangeira";
            }
            
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                    O filme permite reprodução em 3D?                        |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Sim                                                                     |");
            System.out.println("| 2 - Não                                                                     |");
            System.out.println("-------------------------------------------------------------------------------");
            
            System.out.print("\nDigite o número da opção desejada: ");
            opTemp = scanner.nextInt();

            switch(opTemp) {
                case 1:
                    permite3D = true;
                break;
                case 2: 
                    permite3D = false;
            }
            
            Filme filme = new Filme(tituloFilme, duracao, tipoProducao, tipoAudio, permite3D);
            cinema.novoFilme(filme);

    }

    public static void definirSala() {
        int numSala = 0;
        int capacidade = 0;

        System.out.print("\nDigite o número da sala: ");
        numSala = scanner.nextInt();

        System.out.print("\nDigite a capacidade da sala: ");
        capacidade = scanner.nextInt();

        Sala sala = new Sala(numSala, capacidade);
        cinema.novaSala(sala);
    }

    public static void lerSalas() {
        Sala[] salas = cinema.getSalas();

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                            SALAS DISPONÍVEIS                                |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|           NÚMERO DA SALA            |           CAPACIDADE DA SALA          |");
        System.out.println("-------------------------------------------------------------------------------");

        for(int i = 0; i < salas.length; i++){
            System.out.println("| Opção " + (i + 1) + salas[i].getNumSala() + " | " + salas[i].getCapacidade() + " |");
        }
    }

    public static void lerFilmes() {
        Filme[] filmes = cinema.getFilmes();

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                            FILMES DISPONÍVEIS                               |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|     TITULO     |  TIPO DE ÁUDIO  |  TIPO DE PRODUÇÃO   |   DURAÇÃO   |  3D  |");
        System.out.println("-------------------------------------------------------------------------------");


        for(int i = 0; i < filmes.length; i++){ 
            String perm3D = null;

            if(filmes[i].getPermite3D()){
                perm3D = "Disponível";
            }else{
                perm3D = "Não disponível";
            }
            
            System.out.println("| Opção " + (i + 1) + filmes[i].getTitulo() + " | " + filmes[i].getTipoAudio() + " | " + filmes[i].getTipoProducao()+ " | " + filmes[i].getDuracao() + " | "  + perm3D + " |");
        }

    }

    public static void venderIngresso() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                               Qual a sessão?                                |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Sim                                                                     |");
        System.out.println("| 2 - Não                                                                     |");
        System.out.println("-------------------------------------------------------------------------------");

    }


    public static void limparTela() {
        for(int i=0; i<100; i++) {
            System.out.println();
        }
    }

    public static void pausar() {
        Scanner input = new Scanner(System.in);
        System.out.println("Aperte enter para continuar...");
        input.nextLine();
    }
    
    public static void cancelarVenda() {

    }

    public static void lerFaturamento() {

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
}
