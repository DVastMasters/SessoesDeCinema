import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;

public class CinemaUtil{
    private static boolean aberto = false;
    private static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema = new Cinema();
    private static int opcaoInt=0;
    private static double opcaoDouble=0.0;
    private static String confirmacao = "";
    private static String temp="";
    private static boolean loop = true;

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

                System.out.println("\nDigite o número da opção desejada: ");
                leitor(1, 5, 'i');

                switch(opcaoInt) {
                    case 1:
                        venderIngresso();
                        break;
                    case 2:
                        lerSessoes();
                        venderIngresso();
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
                            opcaoInt = 0;
                        }
                }   

            } else {
                System.out.println("------------------------------------------------------");
                System.out.println("| -> A venda de ingressos ainda não foi iniciada! <- |");
                System.out.println("|                                                    |");
                System.out.println("| 1 - Gerenciar sessões.                             |");
                System.out.println("| 2 - Gerenciar salas.                               |");
                System.out.println("| 3 - Gerenciar filmes.                              |");
                System.out.println("| 4 - Iniciar venda de ingressos.                    |");
                System.out.println("| 5 - Encerrar.                                      |");
                System.out.println("------------------------------------------------------");

                System.out.println("\nDigite o número da opção desejada: ");
                leitor(1, 5, 'i');

                switch(opcaoInt) {
                    case 1:
                        limparTela();
                        gerenciarSessoes();
                        break;
                    case 2:
                        limparTela();
                        gerenciarSalas();
                        break;
                    case 3:
                        limparTela();
                        lerFilmes();
                        break;
                    case 4:
                        if(cinema.getSessoes().length == 0) { //Nenhuma sessão criada.
                            System.out.println("É necessário criar uma sessão antes de iniciar as vendas.");
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
                            opcaoInt = 0;                           
                        }
                }
            }

        } while(opcaoInt!=5);

    }

    public static void gerenciarSessoes(){
        if(cinema.getSessoes().length == 0) {

            System.out.println("------------------------------------------------------");
            System.out.println("|           -> Gerenciamento de sessões <-           |");
            System.out.println("|                                                    |");
            System.out.println("| Nenhuma sessão foi criada.                         |");
            System.out.println("|                                                    |");
            System.out.println("| 1 - Criar uma nova sessão.                         |");
            System.out.println("| 2 - Voltar.                                        |");
            System.out.println("------------------------------------------------------");

            System.out.println("\nDigite o número da opção desejada: ");
            leitor(1, 2, 'i');

            if(opcaoInt == 1){
                limparTela();
                criarSessao();
            }
                    
        } else {
            Sessao[] sessoes = cinema.getSessoes();
                    
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("|                   -> Gerenciamento de sessões <-                                              |");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("|                            SESSÕES CRIADAS                                                    |");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println("|         FILME            |     SALA     |    HORÁRIO    |    ÁUDIO    |    3D    |   VALOR    |");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    for(int i = 0; i < sessoes.length; i++) {
                        String perm3D;
                        if(sessoes[i].getExibicao3D()){
                            perm3D = "Sim";
                        }
                        else{
                            perm3D = "Não";
                        }
                    
                        System.out.println("| " + sessoes[i].getFilme().getTitulo() + " | " + sessoes[i].getSala().getNumSala() + " | " + sessoes[i].getHorario() + " | " 
                        + sessoes[i].getTipoAudio() + " | " + perm3D + " | R$" + sessoes[i].getValorIngresso());
                    }
                    System.out.println("|                                                                                               |");
                    System.out.println("| 1 - Criar uma nova sessão.                                                                    |");
                    System.out.println("| 2 - Modificar uma sessão.                                                                     |");
                    System.out.println("| 3 - Remover uma sessão.                                                                       |");
                    System.out.println("| 4 - Voltar.                                                                                   |");
                    System.out.println("-------------------------------------------------------------------------------------------------");

                    System.out.println("\nDigite o número da opção desejada: ");
                    leitor(1, 4, 'i');

                    switch(opcaoInt) {
                        case 1:
                            limparTela();
                            criarSessao();
                            opcaoInt = 0;
                            break;
                        case 2:
                            limparTela();
                            modificarSessao();
                            break;
                        case 3:
                            limparTela();
                            removerSessao();
                            break;
                        case 4:
                            opcaoInt = 2;

                    }

        }
    }

    public static void gerenciarFilme(){

    }

    public static void gerenciarSalas(){
        Sala[] salas = cinema.getSalas();

        if(salas.length == 0){ //GERENCIAR SALAS - PRIMEIRA VEZ
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                   -> Você está gerenciando as salas <-                      |");
            System.out.println("|                                                                             |");
            System.out.println("| Nnehuma sala foi definida.                                                  |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Criar uma nova sala.                                                    |");
            System.out.println("| 2 - Voltar.                                                                 |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.println("\nDigite o número da opção desejada: ");
        
            do{
                temp = scanner.nextLine();

                try {
                    opcaoInt = Integer.parseInt(temp);

                    if(opcaoInt > 0 && opcaoInt < 3) { 
                        break;
                    } else {
                        System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-2.
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
                }

            }while(true);

            if(opcaoInt == 1){
                criarSalas();
                gerenciarSalas();
            }
        } else {
            lerSalas();
            System.out.println("|                                                                                               |");
            System.out.println("| 1 - Criar uma nova sala.                                                                    |");
            System.out.println("| 2 - Modificar uma sala.                                                                     |");
            System.out.println("| 3 - Remover uma sala.                                                                       |");
            System.out.println("| 4 - Voltar.                                                                                   |");
            System.out.println("-------------------------------------------------------------------------------------------------");

            System.out.println("\nDigite o número da opção desejada: ");
            leitor(1, 4, 'i');

            switch(opcaoInt) {
                case 1:
                    limparTela();
                    criarSalas();
                    gerenciarSalas();
                    break;
                case 2:
                    limparTela();
                    modificarSala();
                    gerenciarSalas();
                    break;
                case 3:
                    limparTela();
                    removerSala();
                    gerenciarSalas();

            }
        }
    }

    public static void lerSessoes() {
        
        System.out.println("------------------------------------------------------");
        System.out.println("|           -> Gerenciamento de sessões <-           |");
        System.out.println("|                                                    |");
        System.out.println("| Sessões:                                           |");
        System.out.println("|                                                    |");
        System.out.println("------------------------------------------------------");                
    }

    public static void criarSessao(){
        Filme filme=null;
        Sala sala=null;
        int horarioHora=0;
        int horarioMinuto=0;
        LocalTime horario;
        String tipoAudio=null;
        double valorIngresso=0.0;
        boolean exibicao3D=false;

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

            criarSalas();
        }


        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");

        lerFilmes(); //Listar filmes disponíveis.

        System.out.print("\nDigite o número correspondente ao filme que será exibido nesta sessão: ");
        leitor(1, cinema.getFilmes().length, 'i');
        filme = cinema.getFilmes()[opcaoInt-1];

        /*
        do{
            temp = scanner.nextLine();

            try {
                opcaoInt = Integer.parseInt(temp);
                filme = cinema.getFilmes()[opcaoInt-1];
                loop = false;

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não existe filme que corresponda ao valor digitado.
            }

        }while(loop);
        */

        lerSalas(); //Listar salas disponíveis.

        System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
        leitor(1, cinema.getSalas().length, 'i');
        sala = cinema.getSalas()[opcaoInt-1];
        /*
        do{
            temp = scanner.nextLine();

            try {
                opcaoInt = Integer.parseInt(temp);
                sala = cinema.getSalas()[opcaoInt-1];
                loop = false;

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não existe filme que corresponda ao valor digitado.
            }

        }while(loop);
        */

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Filme: " + filme.getTitulo());
        System.out.println("Sala: " + sala.getNumSala());
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\nÉ necessário definir o horário da sessão.");
        do {
            boolean loop = false;
            leitor(0, 23, 'i');
            horarioHora = opcaoInt;

            leitor(0, 59, 'i');
            horarioMinuto = opcaoInt;

            horario = LocalTime.of(horarioHora, horarioMinuto, 0, 0);
            for(int i=0; i < cinema.getSessoes().length; i++) {
                LocalTime horarioTemp = cinema.getSessoes()[i].getHorario(); //Horário já registrado.

                if(cinema.getSessoes()[i].getSala() == sala) {
                    if(Math.abs(horarioTemp.toSecondOfDay() - horario.toSecondOfDay()) == 1200) {
                        System.out.println("\nJá existe uma sessão neste horário, defina outro.");
                        loop = true;
                    } else if(Math.abs(horarioTemp.toSecondOfDay() - horario.toSecondOfDay()) < 1200) {
                        System.out.println("\nUma sessão só pode ocorrer 20 minutos após a outra. O horário definido é conflitante, defina outro.");
                        loop = true;
                    }
                }

            }

        } while(loop);
        /*
        do {
            System.out.print("Por favor, informe a hora que a sessão irá acontecer (0 - 23): ");
            do{
                temp = scanner.nextLine();

                try {
                    opcaoInt = Integer.parseInt(temp);

                    if(opcaoInt > -1 && opcaoInt < 60) { 
                        horarioHora = opcaoInt;
                        loop = false;
                    } else {
                        System.out.println("\nValor inválido, tente novamente: "); //O valor digitado não está entre 0-59.
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
                }

            }while(loop);

            loop = true; //Reset   

            System.out.print("Agora, informe os minutos (0 - 59): ");
            do{
                temp = scanner.nextLine();

                try {
                    opcaoInt = Integer.parseInt(temp);

                    if(opcaoInt > -1 && opcaoInt < 60) { 
                        horarioMinuto = opcaoInt;
                        loop = false;
                    } else {
                        System.out.println("\nValor inválido, tente novamente: "); //O valor digitado não está entre 0-59.
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
                }

            }while(loop);

            horario = LocalTime.of(horarioHora, horarioMinuto, 0, 0);

            for(int i=0; i < cinema.getSessoes().length; i++) {
                LocalTime horarioTemp = cinema.getSessoes()[i].getHorario(); //Horário já registrado.

                if(cinema.getSessoes()[i].getSala() == sala) {
                    if(Math.abs(horarioTemp.toSecondOfDay() - horario.toSecondOfDay()) == 1200) {
                        System.out.println("\nJá existe uma sessão neste horário, defina outro.");
                        loop = true;
                    } else if(Math.abs(horarioTemp.toSecondOfDay() - horario.toSecondOfDay()) < 1200) {
                        System.out.println("\nUma sessão só pode ocorrer 20 minutos após a outra. O horário definido é conflitante, defina outro.");
                        loop = true;
                    }
                }

            }

        } while (loop);

        loop = true; //Reset  
        */

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|               Qual tipo de áudio será reproduzido nesta sessão?             |");
        System.out.println("|                                                                             |");
        for(int i = 0; i < filme.getTipoAudio().length; i++) {
            System.out.println((i + 1) + " - " + filme.getTipoAudio()[i]);
        }
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("Opção: ");
        leitor(1, filme.getTipoAudio().length, 'i');
        tipoAudio = filme.getTipoAudio()[opcaoInt-1];
        /*
        do{
            temp = scanner.nextLine();

            try {
                opcaoInt = Integer.parseInt(temp);

                if(opcaoInt > 0 && opcaoInt < filme.getTipoAudio().length) { 
                    tipoAudio = filme.getTipoAudio()[opcaoInt-1];
                    loop = false;
                } else {
                    System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 0-TamanhoDaArray.
                }

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            }

        }while(loop);

        loop = true; //Reset 
        */

        if(filme.getPermite3D()){
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                    O filme será reproduzido em 3D?                          |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Sim                                                                     |");
            System.out.println("| 2 - Não                                                                     |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.print("Opção: ");
            leitor(1, 2, 'i');
            if(opcaoInt == 1){
                exibicao3D = true;
            }
            /*
            do{
                temp = scanner.nextLine();
    
                try {
                    opcaoInt = Integer.parseInt(temp);
    
                    if(opcaoInt == 1) { 
                        exibicao3D = true;
                        loop = false;
                    } else if (opcaoInt == 2){
                        exibicao3D = false;
                        break;
                    } else {
                        System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-2.
                    }
    
                } catch (NumberFormatException e) {
                    System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
                }
    
            }while(loop);
    
            loop = true; //Reset */
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Filme: " + filme.getTitulo());
        System.out.println("Sala: " + sala.getNumSala());
        System.out.println("Horario da sessão: " + horario);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\n*Lembre-se, se o filme for reproduzido em 3d, haverá um incremento de 25% no valor digitado.");

        System.out.print("Qual o valor do ingresso da sessão? ");
        leitor(0, , );
        do {
            temp = scanner.nextLine();

            try{
                valorIngresso = Double.parseDouble(temp);

                if(valorIngresso <= 0) {
                    System.out.print("\nO valor do ingresso precisa ser maior que 0."); //O valor digitado não está entre 1-2
                } else {
                    loop = false;
                }

                if(exibicao3D){
                    valorIngresso *= 1.25;
                }

            } catch (NumberFormatException e) {
                System.out.print("\nValor inválido, tente novamente: "); //Não é um double.
            }
        }while(loop);

        loop = true; //Reset

        Sessao sessao = new Sessao(filme, sala, horario, valorIngresso, exibicao3D, tipoAudio);
        cinema.novaSessao(sessao);

    }

    public static void definirFilme() {
        //Variáveis temporárias para o construtor de Filme
        String tituloFilme = null;
        int duracao = 0;
        String tipoProducao = null;
        String[] tipoAudio = new String[3];
        boolean permite3D = false;

        System.out.print("\nDigite o nome do filme: ");
        tituloFilme = scanner.nextLine();

        System.out.print("\nDigite a duração do filme (em minutos): ");
        do{
            temp = scanner.nextLine();

            try {

                duracao = Integer.parseInt(temp);
                loop = false;

            } catch (NumberFormatException e) {

                System.out.println("\nValor inválido, tente novamente: "); //Não é um inteiro.

            }

        }while(loop);

        loop = true; //Reset

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

        System.out.print("\n\nDigite o número da opção desejada: ");
        do{
            temp = scanner.nextLine();

            try {
                opcaoInt = Integer.parseInt(temp);
                if(opcaoInt > 0 && opcaoInt < 8){
                    switch (opcaoInt) {
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
                    loop = false;
                } else {
                    System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-7.
                }

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            }

        }while(loop);

        loop = true; //Reset

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    Qual o tipo de produção do filme?                        |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Nacional                                                                |");
        System.out.println("| 2 - Estrangeira                                                             |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");

        do{
            temp = scanner.nextLine();

            try {
                opcaoInt = Integer.parseInt(temp);
                if(opcaoInt > 0 && opcaoInt < 3){
                    switch(opcaoInt) {
                        case 1:
                            tipoProducao = "Nacional";
                            break;
                        case 2: 
                            tipoProducao = "Estrangeira";
                    }
                    loop = false;
                } else {
                    System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-2.
                }

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            }

        }while(loop);

        loop = true; //Reset

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    O filme permite reprodução em 3D?                        |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Sim                                                                     |");
        System.out.println("| 2 - Não                                                                     |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        do{
            temp = scanner.nextLine();

            try {
                opcaoInt = Integer.parseInt(temp);
                if(opcaoInt > 0 && opcaoInt < 2){
                    permite3D = true;
                    loop = false;
                } else if(opcaoInt == 2){
                    permite3D = false;
                    loop = false;
                } else {
                    System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-2.
                }

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            }

        }while(loop);

        loop = true; //Reset

        Filme filme = new Filme(tituloFilme, duracao, tipoProducao, tipoAudio, permite3D);
        cinema.novoFilme(filme);
    }

    public static void criarSalas() {
        int numSala = 0;
        int capacidade = 0;

        System.out.print("\nDigite o número da sala: ");
        do{
            temp = scanner.nextLine();

            try {
                numSala = Integer.parseInt(temp);

                for(int i=0; i < cinema.getSalas().length; i++){
                    if(cinema.getSalas()[i].getNumSala() == numSala){
                        System.out.println("\nEsta sala já foi definida, defina outra: ");
                        break;
                    }
                }

                loop = false;

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            }

        }while(loop);

        loop = true; //Reset

        System.out.print("\nDigite a capacidade da sala: ");
        do{
            temp = scanner.nextLine();

            try {
                capacidade = Integer.parseInt(temp);
                loop = false;

            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida, tente novamente: "); //Não é um inteiro.
            }

        }while(loop);

        loop = true; //Reset

        Sala sala = new Sala(numSala, capacidade);
        cinema.novaSala(sala);
    }

    public static void lerSalas() {

            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                              SALAS DISPONÍVEIS                              |");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|           NÚMERO DA SALA            |           CAPACIDADE DA SALA          |");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                                                                             |");

    }

    public static void lerFilmes() {
        Filme[] filmes = cinema.getFilmes();

        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                        FILMES DISPONÍVEIS                                                               |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| OPÇÃO |           TITULO           |       TIPO DE ÁUDIO      |     TIPO DE PRODUÇÃO      |   DURAÇÃO   |       3D      |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");


        for(int i = 0; i < filmes.length; i++){ 
            String perm3D = null;

            if(filmes[i].getPermite3D()){
                perm3D = "Disponível";
            }else{
                perm3D = "Não disponível";
            }

            System.out.println("| Opção " + (i + 1) + "   " + filmes[i].getTitulo() + " | " + Arrays.toString(filmes[i].getTipoAudio()) + " | " + filmes[i].getTipoProducao()+ " | " + filmes[i].getDuracao() + " | "  + perm3D + " |");
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

    
    public static void leitor(int opcaoMin, int opcaoMax, Character tipoEntrada) {
        if(tipoEntrada.equals('i')) { ////Quero receber um inteiro.
            do {
                temp = scanner.nextLine();

                try {
                    opcaoInt = Integer.parseInt(temp);

                    if(opcaoInt >= opcaoMin && opcaoInt <= opcaoMax) { 
                        break;
                    } else {
                        System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-quantidadeOpcoes.
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\nValor inválido, tente novamente: "); //Não é um inteiro.
                }

            } while(loop);

        } else { //Quero receber um double.
            do {
                temp = scanner.nextLine();

                try {
                    opcaoDouble = Double.parseDouble(temp);

                    if(opcaoDouble >= 0 && opcaoDouble <= opcaoMax) { 
                        break;
                    } else {
                        System.out.println("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-quantidadeOpcoes.
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\nValor inválido, tente novamente: "); //Não é um double.
                }

            } while(loop);

        }
    }
    
    public static void limparTela() {
        for(int i=0; i<10; i++) {
            System.out.println();
        }
    }

    public static void pausar() {
        System.out.println("Aperte enter para continuar...");
        temp = scanner.nextLine();
    }
}
