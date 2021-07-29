import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class CinemaUtil{
    private static boolean aberto = false;
    private static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema = new Cinema();
    private static int opcaoInt=0;
    private static double opcaoDouble=0.0;
    private static String temp="";
    private static boolean loop = false;
    private static int ponteiro=-1;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private static DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args){

        do {
            limparTela();

            if(aberto) {
                System.out.println("--------------------------------------------");
                System.out.println("| -> A venda de ingressos foi iniciada! <- |");
                System.out.println("|                                          |");
                System.out.println("| 1 - Vender ingresso.                     |");
                System.out.println("| 2 - Cancelar a venda de um ingresso      |");
                System.out.println("| 3 - Visualizar sessões.                  |");
                System.out.println("| 4 - Visualizar faturamento.              |");
                System.out.println("| 5 - Finalizar venda de Ingressos.        |");
                System.out.println("| 6 - Encerrar.                            |");
                System.out.println("--------------------------------------------");

                System.out.println("\nDigite o número da opção desejada: ");
                leitor(1, 6, 'i');

                switch(opcaoInt) {
                    case 1:
                        venderIngresso();
                        opcaoInt = 0; //Evita do programa ser encerrado, caso o caixa venda a poltrona 5.
                        break;
                    case 2:
                        cancelarVenda();
                        opcaoInt = 0; //Evita do program ser encerrado, caso o caixa cancele a venda da poltrona 5.
                        break;
                    case 3:
                        lerSessoes();
                        break;
                    case 4:
                        lerFaturamento();
                        break;
                    case 5:
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
                            cinema.fechar();
                        }
                        break;
                    case 6:
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                        leitor(1, 2, 'i');
                        if(opcaoInt == 1) {
                            opcaoInt = 5;
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
                        if(cinema.getSessoes().size() == 0) { //Nenhuma sessão criada.
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

                            System.out.print("Opção: ");
                            leitor(1, 2, 'i');
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
                            opcaoInt = 5;
                        }
                }
            }

        } while(opcaoInt!=5);
    }

    /*MÉTODOS DE GERENCIAMENTO*/
    public static void gerenciarSessoes(){
        //atualizarSessoes();
        limparTela();

        if(cinema.getSessoes().size() == 0) {

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
                    criarSessao();
                    gerenciarSessoes();
                    break;
                case 2:
                    modificarSessao();
                    gerenciarSessoes();
                    break;
                case 3:
                    removerSessao();
                    gerenciarSessoes();
            }
        }
    }

    public static void lerSessoes() {
        //atualizarSessoes();
        ArrayList<Sessao> sessoes = cinema.getSessoes();

        if(aberto) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("|                                                 SESSÕES DISPONÍVEIS                                                            |");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Nº |                 FILME                 | SALA | INÍCIO |  FIM  |        ÁUDIO         |  3D  |  VALOR  |  TAXA DE OCUPAÇÃO |");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            for(int i = 0; i < sessoes.size(); i++) {
                String espacamentoTitulo = " ", espacamentoTipoAudio = " ", espacamentoTaxa = "  ";
                Sessao sessao = sessoes.get(i);
                
                //Formatação do título
                String titulo = sessao.getFilme().getTitulo(); 
                int comparacaoTitulo = 38 - titulo.length();
                for(int j = 0; j < comparacaoTitulo; j++){
                    espacamentoTitulo += " ";
                }
                
                //Formatação do tipo de áudio
                String tipoAudio = sessao.getTipoAudio();
                int comparacaoTipoAudio = 21 - tipoAudio.length();
                espacamentoTipoAudio += " ";

                //Fomatação da taxa de ocupação
                String taxaOcupacao = df.format(sessao.taxaOcupacao());
                int comparacaoTaxa = 17 - taxaOcupacao.length();
                for(int j = 0; j < comparacaoTaxa; j++){
                    espacamentoTaxa += " ";
                }

                
                String permite3D = "Não";
                if(sessao.getExibicao3D()) {
                    permite3D = "Sim";
                }
            
                System.out.println("| " + (i+1) + "  | " + titulo + espacamentoTitulo + "|  " + sessao.getSala().getNumSala() + "   | " + sessao.getHorarioInicial().format(formataHora) + "  | " 
                + sessao.getHorarioFinal().format(formataHora) + " | " + tipoAudio + espacamentoTipoAudio + "| " + permite3D + "  |    R$" + df.format(sessao.getValorIngresso()) + " | " + taxaOcupacao + espacamentoTaxa + "% |");
            }
            pausar();
        } else {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("|                                  SESSÕES DISPONÍVEIS                                  |");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("| Nº |         FILME            | SALA | INÍCIO |  FIM  |    ÁUDIO       |  3D  | VALOR |");
            System.out.println("-----------------------------------------------------------------------------------------");
            for(int i = 0; i < sessoes.size(); i++) {
                Sessao sessao = sessoes.get(i);
                
                String permite3D = "Não";
                if(sessao.getExibicao3D()) {
                    permite3D = "Sim";
                }
            
                System.out.println("|" + (i+1) + "| " + sessao.getFilme().getTitulo() + " | " + sessao.getSala().getNumSala() + " | " + sessao.getHorarioInicial().format(formataHora) + " | " 
                + sessao.getHorarioFinal().format(formataHora) + " | " + sessao.getTipoAudio() + " | " + permite3D + " | R$" + df.format(sessao.getValorIngresso()) + " | ");
            }
        }    
    }

    public static void criarSessao(){

        if(cinema.getFilmes().size() == 0) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
            System.out.println("|                                                                             |");
            System.out.println("| Como nenhum filme foi registrado, é necessário registrar o primeiro.        |");
            System.out.println("|                                                                             |");
            System.out.println("-------------------------------------------------------------------------------");

            criarFilme();
        }

        if(cinema.getSalas().size() == 0) {
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
        leitor(1, cinema.getFilmes().size(), 'i');
        Filme filme = cinema.getFilmes().get(opcaoInt-1);

        lerSalas(); //Listar salas disponíveis.

        System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
        leitor(1, cinema.getSalas().size(), 'i');
        Sala sala = cinema.getSalas().get(opcaoInt-1);

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Filme: " + filme.getTitulo() +                                               "|");
        System.out.println("Sala: " + sala.getNumSala() +                                                "|");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\nÉ necessário definir o horário da sessão.");
        int hora;
        int minuto;
        LocalTime horarioInicial;
        LocalTime horarioFinal;
        LocalTime horarioAtualSistema;
        do {
            loop = false;
            System.out.print("\nPor favor, informe a hora que a sessão irá acontecer (0 - 23): ");
            leitor(0, 23, 'i');
            hora = opcaoInt;

            System.out.print("\nAgora, informe os minutos (0 - 59): ");
            leitor(0, 59, 'i');
            minuto = opcaoInt;

            horarioInicial = LocalTime.of(hora, minuto, 0, 0);
            horarioFinal = horarioInicial; //Quebra o for-loop se o bloco catch for executado.
            horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + filme.getDuracao() * 60);
            /*
            try{
                horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + filme.getDuracao() * 60);
            }catch (java.time.DateTimeException e) {
                System.out.println("As sessões não devem ultrapassar as 23:59.");
                System.out.println("Para cancelar a operação, digite 0.");
                leitor(0, 1, 'i');
                loop = true;
            }
            */
            horarioAtualSistema = LocalTime.now();
            /*
            if(horarioInicial.toSecondOfDay() < horarioAtualSistema.toSecondOfDay()) {
                System.out.println("\nAinda não podemos voltar no tempo. Por favor, defina um horário depois das " + horarioAtualSistema.format(formataHora));
                loop = true;
            }
            */
            for (Sessao sessao : cinema.getSessoes()) { //Verifica o horário que está sendo criado com todos os horários já definidos.
                int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Horário que a sessão começa.
                int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Horário que a sessão acaba.

                if (horarioInicial == horarioFinal){
                    break;
                }

                if(sessao.getSala() == sala) { //Sessão que ocorre na mesma sala.
                    if(horarioInicialDefinido - horarioInicial.toSecondOfDay() == 0){ //Horário encontrado é igual o horário que está sendo definido.
                        System.out.print("\nJá existe uma sessão definida para este horário, defina outro.");
                        loop = true;
                        break;
                    } else if(horarioInicial.toSecondOfDay() > horarioInicialDefinido && horarioInicial.toSecondOfDay() < horarioFinalDefinido) { //O usuário está tentando definir uma sessão pra um horário que já está ocorrendo uma outra sessão.
                        System.out.println("\nUma sessão estará sendo exibida neste horário. O horário definido é conflitante, defina outro.");
                        loop = true;
                        break;
                    } else if(Math.abs(horarioFinalDefinido - horarioInicial.toSecondOfDay()) < 1200 || Math.abs(horarioInicialDefinido - horarioFinal.toSecondOfDay()) < 1200) { //O intervalo entre cada sessão precisa ser de 20 minutos.
                        System.out.println("\nUma sessão só pode ocorrer 20 minutos após a outra. O horário definido é conflitante, defina outro.");
                        loop = true;
                        break;
                    } else if (horarioInicialDefinido > horarioInicial.toSecondOfDay() && horarioInicialDefinido < horarioFinal.toSecondOfDay()){ //O horário inicial da sessão + o tempo do filme ultrapassam o horário inicial da próxima sessão.
                        System.out.println("\nEssa sessão não vai acabar antes da próxima. O horário definido é conflitante, defina outro.");
                        loop = true;
                        break;
                    }
                }
            }
        } while(loop);

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|               Qual tipo de áudio será reproduzido nesta sessão?             |");
        System.out.println("|                                                                             |");

        for(int i = 0; i < filme.getTipoAudio().length; i++) {
            System.out.println("| " + (i + 1) + " - " + filme.getTipoAudio()[i] +                            "|");
        }

        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("Opção: ");
        leitor(1, filme.getTipoAudio().length, 'i');
        String tipoAudio = filme.getTipoAudio()[opcaoInt-1];

        boolean exibicao3D = false;
        if(filme.getPermite3D()){
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                    O filme será reproduzido em 3D?                          |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Sim                                                                     |");
            System.out.println("| 2 - Não                                                                     |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.print("Opção: ");
            leitor(1, 2, 'i');
            if(opcaoInt == 1) {
                exibicao3D = true;
            }
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + filme.getTitulo() +                                             "|");
        System.out.println("| Sala: " + sala.getNumSala() +                                              "|");
        System.out.println("| Horario de início:: " + horarioInicial +                                   "|");
        System.out.println("| Horario de fim:: " + horarioFinal +                                        "|");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\n*Lembre-se, se o filme for reproduzido em 3D, haverá um incremento de 25% no valor digitado.");

        System.out.print("\nQual o valor do ingresso da sessão? ");
        leitor(1, 1000, 'd');
        double valorIngresso = opcaoDouble;
        if(exibicao3D) {
            valorIngresso *= 1.25;
        }

        Sessao sessao = new Sessao(filme, sala, horarioInicial, horarioFinal, valorIngresso, exibicao3D, tipoAudio);
        cinema.novaSessao(sessao);

    }

    public static void modificarSessao() {

        if(ponteiro == -1){
            System.out.print("\nQual sessão deseja modificar? ");
            leitor(1, cinema.getSessoes().size(), 'i');
            ponteiro = opcaoInt-1;
        }

        Sessao sessao = cinema.getSessoes().get(ponteiro);

        String permite3D = "Exibição em 2D";
        if(sessao.getExibicao3D()) {
            permite3D = "Exibição em 3D";
        }

        System.out.println("------------------------------------------------------");
        System.out.println("|             -> Modificando sessão <-               |");
        System.out.println("|                                                    |");
        System.out.println("| Filme: " + sessao.getFilme().getTitulo());
        System.out.println("| Sala: " + sessao.getSala().getNumSala());
        System.out.println("| Inicío: " + sessao.getHorarioInicial());
        System.out.println("| Fim: " + sessao.getHorarioFinal());
        System.out.println("| Tipo de áudio: " + sessao.getTipoAudio());
        System.out.println("| Tipo de exibição: " + permite3D);
        System.out.println("| Valor do ingresso: R$" + df.format(sessao.getValorIngresso()));
        System.out.println("------------------------------------------------------");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Alterar filme.                                 |");
        System.out.println("| 2 - Alterar sala.                                  |");
        System.out.println("| 3 - Alterar horário.                               |");
        System.out.println("| 4 - Alterar o tipo de áudio.                       |");
        System.out.println("| 5 - Alterar o tipo de exibição (3D ou 2D).         |");
        System.out.println("| 6 - Alterar valor do ingresso.                     |");
        System.out.println("| 7 - Voltar.                                        |");
        System.out.println("------------------------------------------------------");

        System.out.print("Opção: ");
        leitor(1, 7, 'i');
        switch(opcaoInt){
            case 1:
                lerFilmes();
                System.out.print("\nDigite o número correspondente ao filme que será exibido nesta sessão: ");
                leitor(1, cinema.getFilmes().size(), 'i');

                sessao.setFilme(cinema.getFilmes().get(opcaoInt-1));

                modificarSessao();    
                break;
            case 2:
                lerSalas();
                System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
                leitor(1, cinema.getSalas().size(), 'i');

                sessao.setSala(cinema.getSalas().get(opcaoInt-1));

                modificarSessao();    
                break;
            case 3:
            int hora;
            int minuto;
            LocalTime horarioInicial;
            LocalTime horarioFinal;
            LocalTime horarioAtualSistema;
            do {
                loop = false;
                System.out.print("\nPor favor, informe a hora que a sessão irá acontecer (0 - 23): ");
                leitor(0, 23, 'i');
                hora = opcaoInt;
    
                System.out.print("\nAgora, informe os minutos (0 - 59): ");
                leitor(0, 59, 'i');
                minuto = opcaoInt;
    
                horarioInicial = LocalTime.of(hora, minuto, 0, 0);
                horarioFinal = horarioInicial; //Quebra o for-loop se o bloco catch for executado.
                horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + sessao.getFilme().getDuracao() * 60);
                /*
                try{
                    horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + sessao.getFilme().getDuracao() * 60);
                }catch (java.time.DateTimeException e) {
                    System.out.println("As sessões não devem ultrapassar as 23:59.");
                    System.out.println("Para cancelar a operação, digite 0.");
                    leitor(0, 1, 'i');
                    loop = true;
                }
                */
                horarioAtualSistema = LocalTime.now();
                /*
                if(horarioInicial.toSecondOfDay() < horarioAtualSistema.toSecondOfDay()) {
                    System.out.println("\nAinda não podemos voltar no tempo. Por favor, defina um horário depois das " + horarioAtualSistema.format(formataHora));
                    loop = true;
                }
                */
                for (Sessao sessaoDefinida : cinema.getSessoes()) { //Verifica o horário que está sendo criado com todos os horários já definidos.
                    int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Horário que a sessão começa.
                    int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Horário que a sessão acaba.

                    if (horarioInicial == horarioFinal){
                        break;
                    }
    
                    if(sessaoDefinida.getSala() == sessao.getSala() && sessaoDefinida != sessao) { //As sessões ocorrem na mesma sala e são diferentes.
                        if(horarioInicialDefinido - horarioInicial.toSecondOfDay() == 0){ //Horário encontrado é igual o horário que está sendo definido.
                            System.out.print("\nJá existe uma sessão definida para este horário, defina outro.");
                            loop = true;
                            break;
                        } else if(horarioInicial.toSecondOfDay() > horarioInicialDefinido && horarioInicial.toSecondOfDay() < horarioFinalDefinido) { //O usuário está tentando definir uma sessão pra um horário que já está ocorrendo uma outra sessão.
                            System.out.println("\nUma sessão estará sendo exibida neste horário. O horário definido é conflitante, defina outro.");
                            loop = true;
                            break;
                        } else if(Math.abs(horarioFinalDefinido - horarioInicial.toSecondOfDay()) < 1200 || Math.abs(horarioInicialDefinido - horarioFinal.toSecondOfDay()) < 1200) { //O intervalo entre cada sessão precisa ser de 20 minutos.
                            System.out.println("\nUma sessão só pode ocorrer 20 minutos após a outra. O horário definido é conflitante, defina outro.");
                            loop = true;
                            break;
                        } else if (horarioInicialDefinido > horarioInicial.toSecondOfDay() && horarioInicialDefinido < horarioFinal.toSecondOfDay()){ //O horário inicial da sessão + o tempo do filme ultrapassam o horário inicial da próxima sessão.
                            System.out.println("\nEssa sessão não vai acabar antes da próxima. O horário definido é conflitante, defina outro.");
                            loop = true;
                            break;
                        }
                    }
                }
            } while(loop);

                sessao.setHorarioInicial(horarioInicial);
                sessao.setHorarioFinal(horarioFinal);
                
                modificarSessao();
                break;
            case 4:
                System.out.print("\nQual o tipo de áudio será reproduzido nesta sessão? ");
                for(int i=0; i < sessao.getFilme().getTipoAudio().length; i++){
                    System.out.println("1 - " + sessao.getFilme().getTipoAudio()[i]);
                }

                leitor(1, sessao.getFilme().getTipoAudio().length, 'i');

                sessao.setTipoAudio(sessao.getFilme().getTipoAudio()[opcaoInt-1]);

                modificarSessao();  
                break;
            case 5:
                if(sessao.getFilme().getPermite3D()){
                    System.out.println("\nO filme será reproduzido em 3D?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");

                    System.out.print("Opção: ");
                    leitor(1, 2, 'i');
                    sessao.setExibicao3D(false);
                    if(opcaoInt == 1) {
                        sessao.setExibicao3D(true);
                    }

                } else {
                    System.out.println("O filme que será exibido nesta sessão não permite reprodução em 3D.");
                    pausar();
                }
                
                modificarSessao();
                break;
            case 6:
                System.out.println("\n*Lembre-se, se o filme for reproduzido em 3D, haverá um incremento de 25% no valor digitado.");
                System.out.print("\nDigite o novo valor: ");
                leitor(1, 1000, 'd');

                if(sessao.getExibicao3D()) {
                    opcaoDouble*=1.25;
                }

                sessao.setValorIngresso(opcaoDouble);

                modificarSessao();    
                break;
            case 7:
                ponteiro = -1; //Reseta ponteiro global.
        }

    }

    public static void removerSessao() {
        System.out.println("\nQual sessão deseja remover?");
        leitor(1, cinema.getSessoes().size(), 'i');
        cinema.removerSessao(cinema.getSessoes().get(opcaoInt-1));
    }

    public static void gerenciarSalas(){
        ArrayList<Sala> salas = cinema.getSalas();
        
        limparTela();

        if(salas.size() == 0){ //GERENCIAR SALAS - PRIMEIRA VEZ
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                   -> Você está gerenciando as salas <-                      |");
            System.out.println("|                                                                             |");
            System.out.println("| Nenhuma sala foi definida.                                                  |");
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
                    criarSalas();
                    gerenciarSalas();
                    break;
                case 2:
                    modificarSala();
                    gerenciarSalas();
                    break;
                case 3:
                    removerSala();
                    gerenciarSalas();

            }
        }
    }

    public static void lerSalas() {
        ArrayList<Sala> salas = cinema.getSalas();

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                              SALAS DISPONÍVEIS                              |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| OPÇÃO |            NÚMERO DA SALA            |           CAPACIDADE DA SALA         |");
        System.out.println("-------------------------------------------------------------------------------");
        for (int i=0; i < salas.size(); i++ ) {
            System.out.println("| " + (i+1) + " | " + salas.get(i).getNumSala() + " | " + salas.get(i).getCapacidade());
        }
    }

    public static void criarSalas() {

        System.out.print("\nDigite o número da sala: ");
        int numSala = 0;
        do{
            leitor(1, 1000, 'i');
            numSala = opcaoInt;
            
            loop = false;
            for (Sala sala : cinema.getSalas()) {
                if(sala.getNumSala() == numSala){
                    System.out.print("\nEsta sala já foi definida, defina outra: ");
                    loop = true;
                    break;
                }
            }
        }while(loop);

        System.out.print("\nDigite a capacidade da sala: ");
        leitor(1, 1000, 'i');
        int capacidade = opcaoInt;

        Sala sala = new Sala(numSala, capacidade);
        cinema.novaSala(sala);
    }

    public static void modificarSala() {

        if(ponteiro == -1){
            System.out.print("\nQual sala deseja modificar? ");
            leitor(0, cinema.getSalas().size(), 'i');
            ponteiro = opcaoInt-1;
        }

        Sala sala = cinema.getSalas().get(ponteiro);

        System.out.println("------------------------------------------------------");
        System.out.println("|               -> Modificando sala <-               |");
        System.out.println("|                                                    |");
        System.out.println("| Número da sala: " + sala.getNumSala());
        System.out.println("| Capacidade da sala: " + sala.getCapacidade());
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
                do{
                    leitor(1, 1000, 'i');

                    loop = false;        
                    for (Sala salaCriada : cinema.getSalas()) {
                        if(salaCriada.getNumSala() == opcaoInt && salaCriada != sala){ //Já existe uma sala com esse número e não é a que está sendo modificada.
                            System.out.println("\nEsta sala já foi definida, defina outra: ");
                            loop = true;
                            break;
                        }
                    }

                }while(loop);

                sala.setNumSala(opcaoInt);
                
                modificarSala();
                break;
            case 2:
                System.out.print("\nDigite a nova capacidade: ");
                leitor(1, 1000, 'i');

                sala.setCapacidade(opcaoInt);

                modificarSala();
                break;
            case 3:
                ponteiro = -1; //Reseta ponteiro global.
        }
    }

    public static void removerSala() {

        System.out.print("\nQual sala deseja remover? ");
        leitor(1, cinema.getSalas().size(), 'i');
        Sala sala = cinema.getSalas().get(opcaoInt-1);

        opcaoInt = 1; //A sala será removida se opcaoInt == 1.

        boolean primeiraSessaoAfetada=false;
        for (Sessao sessao : cinema.getSessoes()) {

            if(sessao.getSala() == sala) { //Verifica se existem sessões com esta sala.

                if(primeiraSessaoAfetada==false){ //Isso faz ele perguntar apenas uma vez (o loop pode encontrar várias sessões).
                    System.out.println("Existe(m) sessão(ões) que acontecerá(ão) nesta sala. Ao remover a sala, a(s) sessão(ões) também será(ão) excluida(s).");
                    System.out.println("\n1 - Sim\n2 - Não");
                    System.out.print("Tem certeza que deseja continuar?");
                    leitor(1, 2, 'i');

                    if(opcaoInt == 2) { //opcaoInt == 2, a sala não será removida.
                        break;
                    }

                    primeiraSessaoAfetada=true;
                    cinema.removerSessao(sessao);
                } else {
                    cinema.removerSessao(sessao);
                }
            }
        }

        if(opcaoInt == 1) {
            cinema.removerSala(sala);
        }
    }

    public static void gerenciarFilmes() {
        ArrayList<Filme> filmes = cinema.getFilmes();
        
        if(filmes.size() == 0){ //GERENCIAR FILMES - PRIMEIRA VEZ
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
            System.out.println("|                                                                                                                                        |");
            System.out.println("| 1 - Criar uma novo filme.                                                                                                              |");
            System.out.println("| 2 - Modificar um filme.                                                                                                                |");
            System.out.println("| 3 - Remover um filme.                                                                                                                  |");
            System.out.println("| 4 - Voltar.                                                                                                                            |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
            leitor(1, 4, 'i');

            switch(opcaoInt) {
                case 1:
                    criarFilme();
                    gerenciarFilmes();
                    break;
                case 2:
                    modificarFilme();
                    gerenciarFilmes();
                    break;
                case 3:
                    removerFilme();
                    gerenciarFilmes();
            }
        }
    }

    public static void lerFilmes() {
        ArrayList<Filme> filmes = cinema.getFilmes();

        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                        FILMES DISPONÍVEIS                                                               |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| N° |           TITULO           |       TIPO DE ÁUDIO      |     TIPO DE PRODUÇÃO      |     DURAÇÃO    |       3D      |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");
        for(int i = 0; i < filmes.size(); i++){ 
            Filme filme = filmes.get(i);

            String permite3D = "Não disponível";
            if(filme.getPermite3D()) {
                permite3D = "Disponível";
            }

            System.out.println("| " + (i + 1) + " | " + filme.getTitulo() + " | " + Arrays.toString(filme.getTipoAudio()) + 
                               "| " + filme.getTipoProducao()+ " | " + filme.getDuracao() + " | "  + permite3D + " |");
        }

    }

    public static void criarFilme() {

        System.out.print("\nDigite o nome do filme: ");
        do{
            temp = scanner.nextLine();

            loop=false;
            for (Filme filme : cinema.getFilmes()){
                if(filme.getTitulo().equals(temp)){
                    System.out.print("\nJá existe um filme com esse nome, digite outro: ");
                    loop=true;
                    break;
                }
            }

        } while(loop);

        String tituloFilme = temp;

        System.out.print("\nDigite a duração do filme (em minutos): ");
        leitor(1, 1000, 'i');
        int duracao = opcaoInt;

        String[] tipoAudio = tipoAudio();

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    Qual o tipo de produção do filme?                        |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Nacional                                                                |");
        System.out.println("| 2 - Estrangeira                                                             |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        leitor(1, 2, 'i');
        String tipoProducao = "Estrangeira";
        if(opcaoInt == 1){
            tipoProducao = "Nacional";
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    O filme permite reprodução em 3D?                        |");
        System.out.println("|                                                                             |");
        System.out.println("| 1 - Sim                                                                     |");
        System.out.println("| 2 - Não                                                                     |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("\nDigite o número da opção desejada: ");
        leitor(1, 2, 'i');
        boolean permite3D = false;
        if(opcaoInt == 1){
            permite3D = true;
        }

        Filme filme = new Filme(tituloFilme, duracao, tipoProducao, tipoAudio, permite3D);
        cinema.novoFilme(filme);
    }

    public static void modificarFilme() {

        if(ponteiro == -1){
            System.out.print("\nQual filme deseja modificar? ");
            leitor(0, cinema.getFilmes().size(), 'i');
            ponteiro = opcaoInt-1;
        }

        Filme filme = cinema.getFilmes().get(ponteiro);

        String permite3D = "Não disponível";
        if(filme.getPermite3D()) {
            permite3D = "Disponível";
        }

        System.out.println("------------------------------------------------------");
        System.out.println("|              -> Modificando filme <-               |");
        System.out.println("|                                                    |");
        System.out.println("| Título: " + filme.getTitulo());
        System.out.println("| Tipos de áudio disponíveis: " + Arrays.toString(filme.getTipoAudio()));
        System.out.println("| Tipo de produção: " + filme.getTipoProducao());
        System.out.println("| Duração: " + filme.getDuracao());
        System.out.println("| Disponibilidade em 3D: " + permite3D);
        System.out.println("------------------------------------------------------");
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
                    
                    for (Filme filmeCriado : cinema.getFilmes()) {
                        if(filmeCriado.getTitulo() == temp) {
                            System.out.print("\nJá existe um filme com esse nome, digite outro: ");
                            loop=true;
                            break;
                        }
                    }

                } while(loop);

                filme.setTitulo(temp);

                modificarFilme();
                break;
            case 2: 
                filme.setTipoAudio(tipoAudio());

                modificarFilme();
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
                filme.setTipoProducao("Estrangeira");
                if(opcaoInt == 1) {
                    filme.setTipoProducao("Nacional");
                }

                modificarFilme();
                break;
            case 4:
                System.out.print("\nDigite a nova duração do filme (em minutos): ");
                leitor(1, 1000, 'i');
                
                filme.setDuracao(opcaoInt);
                
                modificarFilme();
                break;
            case 5:
                System.out.println("O filme permite reprodução em 3D?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
        
                System.out.print("\nOpção: ");
                leitor(1, 2, 'i');
                filme.setPermite3D(false);
                if(opcaoInt == 1) {
                    filme.setPermite3D(true);
                }

                modificarFilme();
                break;
            case 6:
                ponteiro = -1; //Reseta ponteiro global.
        }

    }

    public static void removerFilme() {

        System.out.print("\nQual filme deseja remover? ");
        leitor(1, cinema.getFilmes().size(), 'i');
        Filme filme = cinema.getFilmes().get(opcaoInt-1);

        opcaoInt = 1; //O filme será removido se opcaoInt == 1.

        boolean primeiraSessaoAfetada=false;
        for (Sessao sessao : cinema.getSessoes()) {

            if(sessao.getFilme() == filme){ //Verifica se existem sessões com este filme.

                if(primeiraSessaoAfetada==false){ //Faz a pergunta apenas uma primeira vez (o loop pode encontrar várias sessões).
                    System.out.println("Existe(m) sessão(ões) que irá(ão) exibir este filme. Ao remover o filme, a(s) sessão(ões) também será(ão) excluida(s).");
                    System.out.println("\n1 - Sim\n2 - Não");
                    System.out.print("Tem certeza que deseja continuar?");
                    leitor(1, 2, 'i');

                    if(opcaoInt == 2) { //O filme não será apagado, pois opcaoInt == 2
                        break;
                    }

                    primeiraSessaoAfetada=true;
                    cinema.removerSessao(sessao);

                } else {
                    cinema.removerSessao(sessao);
                }
            }
        }

        if(opcaoInt == 1) {
            cinema.removerFilme(filme);
        }
    }
    /*MÉTODOS DE GERENCIAMENTO*/

    /*MÉTODOS FINANCEIROS*/
    public static void venderIngresso() {

        lerSessoes();
        System.out.print("\nDeseja vender um ingresso para qual sessão? ");
        leitor(1, cinema.getSessoes().size(), 'i');

        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

        System.out.println("------------------------------------------------------");
        System.out.println("|          -> Qual o tipo de ingresso? <-            |");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Inteiro.                                       |");
        System.out.println("| 2 - Meio.                                          |");
        System.out.println("------------------------------------------------------");

        System.out.print("\nOpção: ");
        leitor(1, 2, 'i');
        char tipoIngresso = 'm';
        if(opcaoInt == 1) {
            tipoIngresso = 'i';
        }
        
        do {
            System.out.println(sessao.poltronasLivres());

            System.out.println("\nDigite o número da poltrona: ");
            leitor(1, sessao.getSala().getCapacidade(), 'i');

            if(cinema.venderIngresso(sessao, tipoIngresso, opcaoInt-1)) {
                break;
            } else {
                System.out.println("\nEssa poltrona já foi vendida!");
                pausar();
            }

        } while(true);

        System.out.println("Venda realizada com sucesso!");
        pausar();
    }

    public static void cancelarVenda() {

        lerSessoes();
        System.out.print("\nDeseja cancelar venda para qual sessão? ");
        leitor(1, cinema.getSessoes().size(), 'i');
        
        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

        do {
            System.out.println(sessao.poltronasOcupadas());

            System.out.print("\nQual a poltrona a ser liberada? ");
            leitor(1, sessao.getSala().getCapacidade(), 'i');

            if(cinema.cancelarVenda(sessao, opcaoInt-1)) {
                break;
            } else {
                System.out.println("\nEssa poltrona já foi vendida!");
                pausar();
            }
        } while (true);

        System.out.println("Venda Cancelada!");
        pausar();
    }

    public static void lerFaturamento() {
        System.out.println("\n                             > Sessões 3D < ");
        System.out.println("\nTotal de ingresso inteiros vendidos: " + cinema.getIngressosInteiras());
        System.out.println("Total de meio ingressos vendidos: " + cinema.getIngressosMeias3D());
        System.out.println("\n                             > Sessões 2D < ");
        System.out.println("\nTotal de ingresso inteiros vendidos: " + cinema.getIngressosInteiras());
        System.out.println("Total de meio ingresso vendidos: " + cinema.getIngressosMeias());
        pausar();

    }
    /*MÉTODOS FINANCEIROS*/

    /*MÉTODOS DE APOIO*/
    public static void leitor(int opcaoMin, int opcaoMax, Character tipoEntrada) {
        if(tipoEntrada.equals('i')) { //Quero receber um inteiro.
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

            } while(true);

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

            } while(true);

        }
    }
    
    public static void atualizarSessoes() {
        LocalTime horarioAtual = LocalTime.now();

        for (int i = 0; i < cinema.getSessoes().size(); i++) {
            if(cinema.getSessoes().get(i).getHorarioFinal().toSecondOfDay() < horarioAtual.toSecondOfDay()) {
                cinema.removerSessao(cinema.getSessoes().get(i));
            }
        }

    }

    public static void limparTela() {
        for(int i=0; i<100; i++) {
            System.out.println();
        }
    }

    public static void pausar() {
        System.out.println();
        System.out.println("Aperte enter para continuar...");
        temp = scanner.nextLine();
    }

    public static String[] tipoAudio(){
        String[] tipoAudio;

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|           O filme possui qual(is) tipo(s) de audio disponível(is)?          |");
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

        return tipoAudio;
    }
    /*MÉTODOS DE APOIO*/
}
