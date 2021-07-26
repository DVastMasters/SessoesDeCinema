import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;

public class CinemaUtil{
    private static boolean aberto = false;
    private static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema = new Cinema();
    private static int opcaoInt=0;
    private static double opcaoDouble=0.0;
    private static String temp="";
    private static boolean loop = true;
    private static int ponteiro=-1;

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
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao finalizar a venda de ingressos, todas as sessões serão apagadas e o faturamento será reiniciado.");
                        leitor(1, 2, 'i');
                        if(opcaoInt == 1){ 
                            aberto = false;
                            cinema.resetar();
                        }
                        break;
                    case 5:
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                        leitor(1, 2, 'i');
                        if(opcaoInt == 1){ 
                            opcaoInt = 5; //Encerra o loop
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
                        gerenciarSessoes();
                        break;
                    case 2:
                        gerenciarSalas();
                        break;
                    case 3:
                        gerenciarFilmes();
                        break;
                    case 4:
                        if(cinema.getSessoes().length == 0) { //Nenhuma sessão criada.
                            System.out.println("É necessário criar uma sessão antes de iniciar as vendas.");
                            pausar(); 
                              
                        } else {
                            System.out.println("-------------------------------------------------------------------------------");
                            System.out.println("|                    Tem certeza que deseja continuar?                        |");
                            System.out.println("|                                                                             |");
                            System.out.println("| 1 - Sim                                                                     |");
                            System.out.println("| 2 - Não                                                                     |");
                            System.out.println("-------------------------------------------------------------------------------");

                            System.out.println("\nApós iniciar as vendas não será possível modificar e nem criar sessões.");

                            if(opcaoInt == 1){ 
                                aberto = true;
                            }

                        }
                        break;
                    case 5:
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                        leitor(1, 2, 'i');
                        if(opcaoInt == 1){ 
                            opcaoInt = 5; //Encerra o loop
                        }
                }
            }

        } while(opcaoInt!=5);
    }

    /*MÉTODOS DE GERENCIAMENTO*/
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
                gerenciarSessoes();
            }
                    
        } else {
            lerSessoes();
            System.out.println("-------------------------------------------------------------------------------------------------");
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
                    gerenciarSessoes();
                    break;
                case 2:
                    limparTela();
                    modificarSessao();
                    gerenciarSessoes();
                    break;
                case 3:
                    limparTela();
                    removerSessao();
                    gerenciarSessoes();
            }
        }
    }

    public static void lerSessoes() {
        Sessao[] sessoes = cinema.getSessoes();

        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("|                            SESSÕES DISPONÍVEIS                                                |");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("|Nº|         FILME            |     SALA     |    HORÁRIO    |    ÁUDIO    |    3D    |   VALOR    |");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for(int i = 0; i < sessoes.length; i++) {
            String permite3D;
            if(sessoes[i].getExibicao3D()){
                permite3D = "Sim";
            }
            else{
                permite3D = "Não";
            }
        
            System.out.println("|" + (i+1) + "| " + sessoes[i].getFilme().getTitulo() + " | " + sessoes[i].getSala().getNumSala() + " | " + sessoes[i].getHorario() + " | " 
            + sessoes[i].getTipoAudio() + " | " + permite3D + " | R$" + sessoes[i].getValorIngresso());
        }           
    }

    public static void criarSessao(){
        Filme filme;
        Sala sala;
        int horarioHora;
        int horarioMinuto;
        LocalTime horario;
        String tipoAudio;
        double valorIngresso;
        boolean exibicao3D=false;

        if(cinema.getFilmes().length == 0) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
            System.out.println("|                                                                             |");
            System.out.println("| Como nenhum filme foi registrado, é necessário registrar o primeiro.        |");
            System.out.println("|                                                                             |");
            System.out.println("-------------------------------------------------------------------------------");

            criarFilme();
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

        lerSalas(); //Listar salas disponíveis.

        System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
        leitor(1, cinema.getSalas().length, 'i');
        sala = cinema.getSalas()[opcaoInt-1];

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Filme: " + filme.getTitulo());
        System.out.println("Sala: " + sala.getNumSala());
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\nÉ necessário definir o horário da sessão.");
        boolean loop = false;
        do {
            System.out.print("Por favor, informe a hora que a sessão irá acontecer (0 - 23): ");
            leitor(0, 23, 'i');
            horarioHora = opcaoInt;

            System.out.print("Agora, informe os minutos (0 - 59): ");
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
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Filme: " + filme.getTitulo());
        System.out.println("Sala: " + sala.getNumSala());
        System.out.println("Horario da sessão: " + horario);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\n*Lembre-se, se o filme for reproduzido em 3d, haverá um incremento de 25% no valor digitado.");

        System.out.print("\nQual o valor do ingresso da sessão? ");
        leitor(1, 100, 'd');
        valorIngresso=opcaoDouble;

        Sessao sessao = new Sessao(filme, sala, horario, valorIngresso, exibicao3D, tipoAudio);
        cinema.novaSessao(sessao);

    }

    public static void modificarSessao() {
        Sessao sessao;
        String permite3D;

        if(ponteiro == -1){
            System.out.print("\nQual sessão deseja modificar? ");
            leitor(1, cinema.getSessoes().length, 'i');
            ponteiro = opcaoInt;

        }

        sessao = cinema.getSessoes()[opcaoInt-1];

        if(sessao.getExibicao3D()){
            permite3D = "Exibição em 3D";
        }
        else{
            permite3D = "Exibição em 2D";
        }
        System.out.println("------------------------------------------------------");
        System.out.println("|             -> Modificando sessão <-               |");
        System.out.println("|                                                    |");
        System.out.println("| " + sessao.getFilme().getTitulo() + " | " + sessao.getSala().getNumSala() + " | " + sessao.getHorario() + 
                          " | " + sessao.getTipoAudio() + " | " + permite3D + " | R$" + sessao.getValorIngresso() + " | ");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Alterar filme.                                 |");
        System.out.println("| 2 - Alterar sala.                                  |");
        System.out.println("| 3 - Alterar horário.                               |");
        System.out.println("| 4 - Alterar o tipo de áudio.                       |");
        System.out.println("| 5 - Alterar o tipo de exibição (3D ou comum).      |");
        System.out.println("| 6 - Alterar valor do ingresso.                     |");
        System.out.println("| 7 - Voltar.                                        |");
        System.out.println("------------------------------------------------------");

        System.out.print("Opção: ");
        leitor(1, 7, 'i');
        switch(opcaoInt){
            case 1:
                lerFilmes();
                System.out.print("\nDigite o número correspondente ao filme que será exibido nesta sessão: ");
                leitor(1, cinema.getFilmes().length, 'i');

                cinema.getSessoes()[ponteiro].setFilme(cinema.getFilmes()[opcaoInt-1]);

                modificarSessao();    
                break;
            case 2:
                lerSalas();
                System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
                leitor(1, cinema.getSalas().length, 'i');

                cinema.getSessoes()[ponteiro].setSala(cinema.getSalas()[opcaoInt-1]);

                modificarSessao();    
                break;
            case 3:
                boolean loop = false;
                LocalTime horario;

                do {
                    System.out.print("Por favor, informe a hora que a sessão irá acontecer (0 - 23): ");
                    leitor(0, 23, 'i');
                    int horarioHora = opcaoInt;

                    System.out.print("Agora, informe o(s) minuto(s) (0 - 59): ");
                    leitor(0, 59, 'i');
                    int horarioMinuto = opcaoInt;

                    horario = LocalTime.of(horarioHora, horarioMinuto, 0, 0);
                    for(int i=0; i < cinema.getSessoes().length; i++) {
                        LocalTime horarioTemp = cinema.getSessoes()[i].getHorario(); //Horário já registrado.

                        if(cinema.getSessoes()[i].getSala() == sessao.getSala()) { //Sessão que ocorre na mesma sala.
                            if(cinema.getSessoes()[i] != sessao) { //A sessão encotrada é diferente da sessão que está sendo modificada.
                                if(Math.abs(horarioTemp.toSecondOfDay() - horario.toSecondOfDay()) == 1200) {
                                    System.out.println("\nJá existe uma sessão neste horário, defina outro.");
                                    loop = true;
                                } else if(Math.abs(horarioTemp.toSecondOfDay() - horario.toSecondOfDay()) < 1200) {
                                    System.out.println("\nUma sessão só pode ocorrer 20 minutos após a outra. O horário definido é conflitante, defina outro.");
                                    loop = true;
                                }
                            }
                        }
                    }
                } while(loop);

                cinema.getSessoes()[ponteiro].setHorario(horario);

                modificarSessao();
                break;
            case 4:
                for(int i=0; i < sessao.getFilme().getTipoAudio().length; i++){
                    System.out.println("1 - " + sessao.getFilme().getTipoAudio()[i]);
                }

                System.out.print("Qual o tipo de áudio será reproduzido nesta sessão? ");
                leitor(1, sessao.getFilme().getTipoAudio().length, 'i');

                cinema.getSessoes()[ponteiro].setTipoAudio(sessao.getFilme().getTipoAudio()[opcaoInt-1]);

                modificarSessao();  
                break;
            case 5:
                if(sessao.getFilme().getPermite3D()){
                    System.out.println("\nO filme será reproduzido em 3D?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");

                    System.out.print("Opção: ");
                    leitor(1, 2, 'i');
                    if(opcaoInt == 1){
                        cinema.getSessoes()[ponteiro].setExibicao3D(true);
                    } else {
                        cinema.getSessoes()[ponteiro].setExibicao3D(false);
                    }
                } else {
                    System.out.println("O filme que será exibido nesta sessão não permite reprodução em 3D.");
                    pausar();
                }
                
                modificarSessao();
                break;
            case 6:
                System.out.print("\nDigite o novo valor: ");
                leitor(1, 1000, 'd');

                cinema.getSessoes()[ponteiro].setValorIngresso(opcaoDouble);

                modificarSessao();    
                break;
            case 7:
                ponteiro = -1;
        }

    }

    public static void removerSessao() { //Precisa deste método?
        System.out.println("\nQual sessão deseja remover?");
        leitor(1, cinema.getSessoes().length, 'i');
        cinema.removerSessao(cinema.getSessoes()[opcaoInt-1]);
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
            leitor(1, 2, 'i');

            if(opcaoInt == 1){
                criarSalas();
                gerenciarSalas();
            }
        } else { //GERENCIAR SALAS - >0 SALAS
            lerSalas();
            System.out.println("|                                                                                               |");
            System.out.println("| 1 - Criar uma nova sala.                                                                      |");
            System.out.println("| 2 - Modificar uma sala.                                                                       |");
            System.out.println("| 3 - Remover uma sala.                                                                         |");
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

    public static void lerSalas() {
        Sala[] salas = cinema.getSalas();

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                              SALAS DISPONÍVEIS                              |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|           NÚMERO DA SALA            |           CAPACIDADE DA SALA          |");
        System.out.println("-------------------------------------------------------------------------------");
        for(int i = 0; i < salas.length; i++){ 
            System.out.println("| " + (i + 1) + " | " + salas[i].getNumSala() + " | " + salas[i].getCapacidade() + " |");
        }

    }

    public static void criarSalas() {
        int numSala = 0;
        int capacidade = 0;

        System.out.print("\nDigite o número da sala: ");
        boolean loop = false;
        do{
            leitor(1, 1000, 'i');
            numSala = opcaoInt;

            for(int i=0; i < cinema.getSalas().length; i++){
                if(cinema.getSalas()[i].getNumSala() == numSala){
                    System.out.println("\nEsta sala já foi definida, defina outra: ");
                    loop = true;
                    break;
                }
            }
        }while(loop);

        System.out.print("\nDigite a capacidade da sala: ");
        leitor(1, 1000, 'i');
        capacidade = opcaoInt;

        Sala sala = new Sala(numSala, capacidade);
        cinema.novaSala(sala);
    }

    public static void modificarSala() {
        Sala sala;

        if(ponteiro == -1){
            System.out.print("\nQual sala deseja modificar? ");
            leitor(0, cinema.getSalas().length, 'i');
            ponteiro = opcaoInt;
        }

        sala = cinema.getSalas()[opcaoInt-1];

        System.out.println("------------------------------------------------------");
        System.out.println("|               -> Modificando sala <-               |");
        System.out.println("|                                                    |");
        System.out.println("| " + sala.getNumSala() + " | " + sala.getCapacidade() + " |");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Alterar número da sala.                        |");
        System.out.println("| 2 - Alterar capacidade da sala.                    |");
        System.out.println("| 3 - Voltar.                                        |");
        System.out.println("------------------------------------------------------");
        
        System.out.print("\nOpção: ");
        leitor(1, 3, 'i');

        switch(opcaoInt) {
            case 1:
                System.out.print("\nDigite o novo número: ");
                boolean loop = false;
                do{
                    leitor(1, 1000, 'i');
        
                    for(int i=0; i < cinema.getSalas().length; i++){
                        if(cinema.getSalas()[i].getNumSala() == opcaoInt && cinema.getSalas()[i] != sala){ //Já existe uma sala com esse número e não é a que está sendo modificada.
                            System.out.println("\nEsta sala já foi definida, defina outra: ");
                            loop = true;
                            break;
                        }
                    }
                }while(loop);

                cinema.getSalas()[ponteiro].setNumSala(opcaoInt);
                
                modificarSala();
                break;
            case 2:
                System.out.print("\nDigite a nova capacidade: ");
                leitor(1, 1000, 'i');

                cinema.getSalas()[ponteiro].setCapacidade(opcaoInt);

                modificarSala();
                break;
            case 3:
                ponteiro = -1;
        }



    }

    public static void removerSala() {
        System.out.print("\nQual sala deseja remover? ");
        leitor(1, cinema.getSalas().length, 'i');
        cinema.removerSala(cinema.getSalas()[opcaoInt-1]);
    }

    public static void gerenciarFilmes() {
        Filme[] filmes = cinema.getFilmes();
        
        if(filmes.length == 0){ //GERENCIAR FILMES - PRIMEIRA VEZ
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                  -> Você está gerenciando os filmes <-                      |");
            System.out.println("|                                                                             |");
            System.out.println("| Nenhum filme foi definido.                                                  |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Criar um novo filme.                                                    |");
            System.out.println("| 2 - Voltar.                                                                 |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.println("\nDigite o número da opção desejada: ");
            leitor(1, 2, 'i');

            if(opcaoInt == 1){
                criarFilme();
                gerenciarFilmes();
            }
        } else { //GERENCIAR FILMES - >0 FILMES
            lerFilmes();
            System.out.println("|                                                                                               |");
            System.out.println("| 1 - Criar uma novo filme.                                                                     |");
            System.out.println("| 2 - Modificar um filme.                                                                       |");
            System.out.println("| 3 - Remover um filme.                                                                         |");
            System.out.println("| 4 - Voltar.                                                                                   |");
            System.out.println("-------------------------------------------------------------------------------------------------");

            System.out.println("\nDigite o número da opção desejada: ");
            leitor(1, 4, 'i');

            switch(opcaoInt) {
                case 1:
                    limparTela();
                    criarFilme();
                    gerenciarFilmes();
                    break;
                case 2:
                    limparTela();
                    modificarFilme();
                    gerenciarFilmes();
                    break;
                case 3:
                    limparTela();
                    removerFilme();
                    gerenciarFilmes();
            }
        }
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

            System.out.println("| " + (i + 1) + " | " + filmes[i].getTitulo() + " | " + Arrays.toString(filmes[i].getTipoAudio()) + 
                               "| " + filmes[i].getTipoProducao()+ " | " + filmes[i].getDuracao() + " | "  + perm3D + " |");
        }

    }

    public static void criarFilme() {
        //Variáveis temporárias para o construtor de Filme
        String tituloFilme="";
        int duracao;
        String tipoProducao;
        boolean permite3D;
        String[] tipoAudio;

        System.out.print("\nDigite o nome do filme: ");
        do{
            temp = scanner.nextLine();
            
            for(int i=0; i < cinema.getFilmes().length; i++) {
                if(cinema.getFilmes()[ponteiro].getTitulo() == temp) {
                    System.out.print("\nJá existe um filme com esse nome, digite outro: ");
                    loop=true;
                    break;
                }
            }
        } while(loop);

        System.out.print("\nDigite a duração do filme (em minutos): ");
        leitor(1, 1000, 'i');
        duracao = opcaoInt;

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
        leitor(1, 7, 'i');
        if(opcaoInt < 4) {
            tipoAudio = new String[1];
        } else if (opcaoInt < 7){
            tipoAudio = new String[2];
        } else {
            tipoAudio = new String[3];
        }

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
                tipoAudio[0] = "Original";
                tipoAudio[1] = "Original com legenda";
                tipoAudio[2] = "Dublado";
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    Qual o tipo de produção do filme?                        |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Nacional                                                                |");
        System.out.println("| 2 - Estrangeira                                                             |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        leitor(1, 2, 'i');
        if(opcaoInt == 1) {
            tipoProducao = "Nacional";
        } else {
            tipoProducao = "Estrangeira";
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    O filme permite reprodução em 3D?                        |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Sim                                                                     |");
        System.out.println("| 2 - Não                                                                     |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        leitor(1, 2, 'i');
        if(opcaoInt == 1){
            permite3D = true;
        } else {
            permite3D = false;
        }

        Filme filme = new Filme(tituloFilme, duracao, tipoProducao, tipoAudio, permite3D);
        cinema.novoFilme(filme);
    }

    public static void modificarFilme() {
        Filme filme;
        String permite3D;

        if(ponteiro == -1){
            System.out.print("\nQual filme deseja modificar? ");
            leitor(0, cinema.getFilmes().length, 'i');
            ponteiro = opcaoInt;
        }

        filme = cinema.getFilmes()[opcaoInt-1];

        if(filme.getPermite3D()){
            permite3D = "Disponível em 3D";
        }else{
            permite3D = "Não disponível em 3D";
        }

        System.out.println("------------------------------------------------------");
        System.out.println("|              -> Modificando filme <-               |");
        System.out.println("|                                                    |");
        System.out.println("| " + filme.getTitulo() + " | " + Arrays.toString(filme.getTipoAudio()) + 
                           "| " + filme.getTipoProducao()+ " | " + filme.getDuracao() + " | "  + permite3D + " |");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Alterar o título.                              |");
        System.out.println("| 2 - Alterar o tipo de áudio.                       |");
        System.out.println("| 3 - Alterar o tipo de produção.                    |");
        System.out.println("| 4 - Alterar a duração.                             |");
        System.out.println("| 5 - Alterar a disponibilidade de 3D.               |");
        System.out.println("| 6 - Voltar.                                        |");
        System.out.println("------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        leitor(1, 6, 'i');

        switch(opcaoInt) {
            case 1:
                System.out.print("\nDigite o novo nome do filme: ");
                boolean loop=false;
                do{
                    temp = scanner.nextLine();
                    
                    for(int i=0; i < cinema.getFilmes().length; i++) {
                        if(cinema.getFilmes()[ponteiro].getTitulo() == temp) {
                            System.out.print("\nJá existe um filme com esse nome, digite outro: ");
                            loop=true;
                            break;
                        }
                    }
                } while(loop);

                cinema.getFilmes()[ponteiro].setTitulo(temp);

                modificarFilme();
                break;
            case 2: //Falta

                gerenciarFilmes();
                break;
            case 3:
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("|                    Qual o tipo de produção do filme?                        |");
                System.out.println("|                                                                             |");
                System.out.println("| 1 - Nacional                                                                |");
                System.out.println("| 2 - Estrangeira                                                             |");
                System.out.println("-------------------------------------------------------------------------------");
        
                System.out.print("\nDigite o número da opção desejada: ");
                leitor(1, 2, 'i');
                if(opcaoInt == 1) {
                    cinema.getFilmes()[ponteiro].setTipoProducao("Nacional");
                } else {
                    cinema.getFilmes()[ponteiro].setTipoProducao("Estrangeira");
                }

                gerenciarFilmes();
                break;
            case 4:
                System.out.print("\nDigite a nova duração do filme (em minutos): ");
                leitor(1, 1000, 'i');
                
                cinema.getFilmes()[ponteiro].setDuracao(opcaoInt);
                
                gerenciarFilmes();
                break;
            case 5:
                System.out.println("O filme permite reprodução em 3D?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
        
                System.out.print("\nOpção: ");
                leitor(1, 2, 'i');
                if(opcaoInt == 1){
                    cinema.getFilmes()[ponteiro].setPermite3D(true);
                } else {
                    cinema.getFilmes()[ponteiro].setPermite3D(false);
                }

                gerenciarFilmes();
                break;
            case 6:
                ponteiro = -1;

        }

    }

    public static void removerFilme() {
        System.out.print("\nQual filme deseja remover? ");
        leitor(1, cinema.getFilmes().length, 'i');
        cinema.removerFilme(cinema.getFilmes()[opcaoInt-1]);

    }
    /*MÉTODOS DE GERENCIAMENTO*/

    /*MÉTODOS FINANCEIROS*/
    public static void venderIngresso() {
        lerSessoes();
        System.out.print("\nDeseja vender um ingresso para qual sessão? ");
        leitor(1, cinema.getSessoes().length, 'i');
        ponteiro = opcaoInt;
        
        System.out.println("------------------------------------------------------");
        System.out.println("|          -> Qual o tipo de ingresso? <-            |");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Inteiro.                                       |");
        System.out.println("| 2 - Meio.                                          |");
        System.out.println("------------------------------------------------------");

        System.out.print("\nDeseja vender um ingresso para qual sessão? ");
        leitor(1, 2, 'i');





    }

    public static void cancelarVenda() {

    }

    public static void lerFaturamento() {

    }
    /*MÉTODOS FINANCEIROS*/

    /*MÉTODOS DE APOIO*/
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
    /*MÉTODOS DE APOIO*/
}
