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

                System.out.print("\nDigite o número da opção desejada: ");
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
                        System.out.println("\n-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao finalizar a venda de ingressos, todas as sessões serão apagadas e o faturamento será reiniciado.");
                        System.out.print("Opção: ");
                        leitor(1, 2, 'i');
                        if(opcaoInt == 1){ 
                            aberto = false;
                            cinema.fechar();
                        }
                        break;
                    case 6:
                        System.out.println("\n-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                        System.out.print("Opção: ");
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

                System.out.print("\nDigite o número da opção desejada: ");
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
                            System.out.println("\n-------------------------------------------------------------------------------");
                            System.out.println("|                    Tem certeza que deseja continuar?                        |");
                            System.out.println("|                                                                             |");
                            System.out.println("| 1 - Sim                                                                     |");
                            System.out.println("| 2 - Não                                                                     |");
                            System.out.println("-------------------------------------------------------------------------------");

                            System.out.println("\nApós iniciar as vendas, não será possível modificar e nem criar sessões.");

                            System.out.print("Opção: ");
                            leitor(1, 2, 'i');
                            if(opcaoInt == 1){
                                aberto = true;
                            }
                        

                        }
                        break;
                    case 5:
                        System.out.println("\n-------------------------------------------------------------------------------");
                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
                        System.out.println("|                                                                             |");
                        System.out.println("| 1 - Sim                                                                     |");
                        System.out.println("| 2 - Não                                                                     |");
                        System.out.println("-------------------------------------------------------------------------------");

                        System.out.println("Ao encerrar o programa, todos os dados serão apagados.");
                        System.out.print("Opção: ");
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

            System.out.print("\nDigite o número da opção desejada: ");
            leitor(1, 2, 'i');

            if(opcaoInt == 1){
                limparTela();
                criarSessao();
                gerenciarSessoes();
            }
                    
        } else {
            lerSessoes();
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("|                                                                                                              |");
            System.out.println("| 1 - Criar uma nova sessão.                                                                                   |");
            System.out.println("| 2 - Modificar uma sessão.                                                                                    |");
            System.out.println("| 3 - Remover uma sessão.                                                                                      |");
            System.out.println("| 4 - Voltar.                                                                                                  |");
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
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
        atualizarSessoes();
        ArrayList<Sessao> sessoes = cinema.getSessoes();

        if(aberto) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("|                                                 SESSÕES DISPONÍVEIS                                                              |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Nº |                 FILME                 | SALA | INÍCIO |  FIM  |        ÁUDIO         |  3D  |   VALOR   |  TAXA DE OCUPAÇÃO |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            for(int i = 0; i < sessoes.size(); i++) {
                Sessao sessao = sessoes.get(i);
                
                //Formatação número
                String numero;
                if(i < 9) {
                    numero = "  " + (i+1)  + " ";
                } else  {
                    numero = " " + (i+1) + " ";
                }

                //Formatação filme
                String filme = " " + sessao.getFilme().getTitulo() + " ";
                for(int f=filme.length(); f < 39; f++) {
                    filme += " ";
                }

                //Formatação sala
                String sala;
                if(sessao.getSala().getNumSala() < 10) {
                    sala = "   " + sessao.getSala().getNumSala() + "  ";
                } else {
                    sala = "  " + sessao.getSala().getNumSala() + "  ";
                }

                //Formatação inicio
                String inicio = "  " + sessao.getHorarioInicial() + " ";

                //Formatação fim
                String fim = " " + sessao.getHorarioFinal() + " ";

                //Formatação áudio
                String audio = " " + sessao.getTipoAudio() + " ";
                for(int a = audio.length(); a < 22; a++) {
                    audio += " ";
                }

                String permite3D = " Não  ";
                if(sessao.getExibicao3D()) {
                    permite3D = " Sim  ";
                }

                String valor = " R$" + df.format(sessao.getValorIngresso()) + "  ";
                for(int v=valor.length(); v < 11; v++) {
                    valor += " ";
                }

                String taxa = " " + df.format(sessao.taxaOcupacao() * 100) + "% ";
                for(int t = taxa.length(); t < 19; t++) {
                    taxa += " ";
                }

                System.out.println("|" + numero + "|" + filme + "|" + sala + "|" + inicio + "|" + fim + "|" + audio + "|" + permite3D + "|" + valor + "|" + taxa + "|");
            }
            pausar();
        } else {
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("|                                              SESSÕES DISPONÍVEIS                                             |");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("| Nº |                 FILME                 | SALA | INÍCIO |  FIM  |        ÁUDIO         |  3D  |   VALOR   |");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            for(int i = 0; i < sessoes.size(); i++) {
                Sessao sessao = sessoes.get(i);

                //Formatação número
                String numero;
                    if(i < 9) {
                        numero = "  " + (i+1)  + " ";
                    } else  {
                        numero = " " + (i+1) + " ";
                    }

                //Formatação filme
                String filme = " " + sessao.getFilme().getTitulo() + " ";
                    for(int f=filme.length(); f < 39; f++) {
                        filme += " ";
                    }

                //Formatação sala
                String sala;
                    if(sessao.getSala().getNumSala() < 10) {
                        sala = "   " + sessao.getSala().getNumSala() + "  ";
                    } else {
                        sala = "  " + sessao.getSala().getNumSala() + "  ";
                    }

                //Formatação inicio
                String inicio = "  " + sessao.getHorarioInicial() + " ";

                //Formatação fim
                String fim = " " + sessao.getHorarioFinal() + " ";

                //Formatação audio
                String audio = " " + sessao.getTipoAudio() + " ";
                    for(int a = audio.length(); a < 22; a++) {
                        audio += " ";
                    }
                
                //Formatação 3d
                String permite3D = " Não  ";
                    if(sessao.getExibicao3D()) {
                        permite3D = " Sim  ";
                    }

                //Formatação valor
                String valor = " R$" + df.format(sessao.getValorIngresso()) + "  ";
                    for(int v=valor.length(); v < 11; v++) {
                        valor += " ";
                    }
            
                System.out.println("|" + numero + "|" + filme + "|" + sala + "|" + inicio + "|" + fim + "|" + audio + "|" + permite3D + "|" + valor + "|");
            }
        }    
    }

    public static void criarSessao(){

        if(cinema.getFilmes().size() == 0) {
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
            System.out.println("|                                                                             |");
            System.out.println("| Como nenhum filme foi registrado, é necessário registrar o primeiro.        |");
            System.out.println("|                                                                             |");
            System.out.println("-------------------------------------------------------------------------------");

            criarFilme();
        }

        if(cinema.getSalas().size() == 0) {
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
            System.out.println("|                                                                             |");
            System.out.println("| Como nenhuma sala foi registrada, é necessário registrar a primeira.        |");
            System.out.println("|                                                                             |");
            System.out.println("-------------------------------------------------------------------------------");

            criarSalas();
        }

        lerFilmes();

        Filme filme;
        do{ 
            System.out.print("\nDigite o número correspondente ao filme que será exibido nesta sessão: ");
            leitor(1, cinema.getFilmes().size(), 'i');
            filme = cinema.getFilmes().get(opcaoInt-1);
            if((LocalTime.now().toSecondOfDay() + filme.getDuracao() * 60) > 86399){
                System.out.print("\nAs sessões não devem ultrapassar as 23:59.");
                System.out.print("\nDigite 0 para definir um novo filme ou 1 para continuar: ");
                leitor(0,1, 'i');
                if(opcaoInt==0) {
                    criarFilme();
                }
                loop = true;
            }
        } while (loop);

        lerSalas();

        System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
        leitor(1, cinema.getSalas().size(), 'i');
        Sala sala = cinema.getSalas().get(opcaoInt-1);

        //Formatação título
        String titulo = filme.getTitulo() + " ";
        for(int f=filme.getTitulo().length(); f < 68; f++) {
            titulo += " ";
        }

        //Formatação número da sala
        String numSala = sala.getNumSala() + " ";
        for(int s = numSala.length(); s < 70; s++) {
            numSala += " ";
        }

        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + titulo                                                        + "|");
        System.out.println("| Sala: " + numSala                                                        + "|");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\nÉ necessário definir o horário da sessão.");
        int hora;
        int minuto;
        LocalTime horarioInicial;
        LocalTime horarioFinal;
        do {
            loop = false;
            System.out.print("\nPor favor, informe a hora que a sessão irá acontecer (0 - 23): ");
            leitor(0, 23, 'i');
            hora = opcaoInt;

            System.out.print("\nAgora, informe os minutos (0 - 59): ");
            leitor(0, 59, 'i');
            minuto = opcaoInt;

            horarioInicial = LocalTime.of(hora, minuto, 0, 0);
            if((horarioInicial.toSecondOfDay() + filme.getDuracao() * 60) > 86399){
                System.out.print("\nAs sessões não devem ultrapassar as 23:59.");
                loop = true;
                horarioFinal = horarioInicial;
            } else {
                horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + filme.getDuracao() * 60);
            }

            if(horarioInicial.toSecondOfDay() < LocalTime.now().toSecondOfDay()) {
                System.out.println("\nAinda não podemos voltar no tempo. Por favor, defina um horário depois das " + LocalTime.now().format(formataHora));
                loop = true;
            }

            for (Sessao sessao : cinema.getSessoes()) { //Verifica o horário que está sendo criado com todos os horários já definidos.
                int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Horário que a sessão começa.
                int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Horário que a sessão acaba.

                if(horarioFinal == horarioInicial) {
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

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("|               Qual tipo de áudio será reproduzido nesta sessão?             |");
        System.out.println("|                                                                             |");

        for(int i = 0; i < filme.getTipoAudio().length; i++) {
            //Formatação áudio
            String tipoAudio = filme.getTipoAudio()[i];
            for(int a = tipoAudio.length(); a < 72; a++) {
                tipoAudio += " ";
            }

            System.out.println("| " + (i+1) + " - " + tipoAudio + "|");
        }

        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("Opção: ");
        leitor(1, filme.getTipoAudio().length, 'i');
        String tipoAudio = filme.getTipoAudio()[opcaoInt-1];

        boolean exibicao3D = false;
        if(filme.getPermite3D()){
            System.out.println("\n-------------------------------------------------------------------------------");
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

        //Formatação horário início
        String horaInicio = horarioInicial.format(formataHora) + "                                                    ";

        //Formatação horário final
        String horaFinal = horarioFinal.format(formataHora) + "                                                       ";

        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + titulo +                                                        "|");
        System.out.println("| Sala: " + numSala +                                                        "|");
        System.out.println("| Horario de início: " + horaInicio +                                        "|");
        System.out.println("| Horario de fim: " + horaFinal +                                            "|");
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

        //Formatação filme
        String tituloFilme = sessao.getFilme().getTitulo();
        for(int f = tituloFilme.length(); f < 44; f++) {
            tituloFilme += " ";
        }

        //Formatação sala
        String sala = sessao.getSala().getNumSala() + "";
        for(int s = sala.length(); s < 45; s++) {
            sala += " ";
        }

        //Formatação horário início
        String horaInicio = sessao.getHorarioInicial().format(formataHora) + "                                      ";

        //Formatação horário final
        String horaFinal = sessao.getHorarioFinal().format(formataHora) + "                                         ";

        //Formatação tipo de áudio
        String audio = sessao.getTipoAudio();
        for(int a = audio.length(); a < 36; a++) {
            audio += " ";
        }

        //Formatação 3D
        String permite3D = "Exibição em 2D                   ";
        if(sessao.getExibicao3D()) {
            permite3D = "Exibição em 3D                   ";
        }

        //Formatação valor
        String valor = df.format(sessao.getValorIngresso());
        for(int v = valor.length(); v < 30; v++) {
            valor += " ";
        }

        System.out.println("\n------------------------------------------------------");
        System.out.println("|             -> Modificando sessão <-               |");
        System.out.println("|                                                    |");
        System.out.println("| Filme: " + tituloFilme + "|");
        System.out.println("| Sala: " + sala + "|");
        System.out.println("| Inicío: " + horaInicio + "|");
        System.out.println("| Fim: " + horaFinal + "|");
        System.out.println("| Tipo de áudio: " + audio + "|");
        System.out.println("| Tipo de exibição: " + permite3D + "|");
        System.out.println("| Valor do ingresso: R$" + valor + "|");
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
                do {
                    loop = false;
                    System.out.print("\nPor favor, informe a hora que a sessão irá acontecer (0 - 23): ");
                    leitor(0, 23, 'i');
                    hora = opcaoInt;
        
                    System.out.print("\nAgora, informe os minutos (0 - 59): ");
                    leitor(0, 59, 'i');
                    minuto = opcaoInt;
        
                    horarioInicial = LocalTime.of(hora, minuto, 0, 0);
                    if((horarioInicial.toSecondOfDay() + sessao.getFilme().getDuracao() * 60) > 86399){
                        System.out.print("\nAs sessões não devem ultrapassar as 23:59.");
                        loop = true;
                        horarioFinal = horarioInicial;
                    } else {
                        horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + sessao.getFilme().getDuracao() * 60);
                    }
        
                    if(horarioInicial.toSecondOfDay() < LocalTime.now().toSecondOfDay()) {
                        System.out.println("\nAinda não podemos voltar no tempo. Por favor, defina um horário depois das " + LocalTime.now().format(formataHora));
                        loop = true;
                    }
        
                    for (Sessao sessaoDefinida : cinema.getSessoes()) { //Verifica o horário que está sendo criado com todos os horários já definidos.
                        int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Horário que a sessão começa.
                        int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Horário que a sessão acaba.
        
                        if(horarioFinal == horarioInicial) {
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
                System.out.println("\n------------------------------------------------------");
                System.out.println("|  Qual tipo de áudio será reproduzido nesta sessão? |");
                System.out.println("|                                                    |");
        
                for(int i = 0; i < sessao.getFilme().getTipoAudio().length; i++) {

                    //Formatação áudio
                    String tipoAudio = sessao.getFilme().getTipoAudio()[i];
                    for(int a = tipoAudio.length(); a < 48; a++) {
                        tipoAudio += " ";
                    }
        
                    System.out.println("| " + (i+1) + " - " + tipoAudio + "|");
                }
        
                System.out.println("------------------------------------------------------");
        
                System.out.print("Opção: ");

                leitor(1, sessao.getFilme().getTipoAudio().length, 'i');

                sessao.setTipoAudio(sessao.getFilme().getTipoAudio()[opcaoInt-1]);

                modificarSessao();  
                break;
            case 5:
                if(sessao.getFilme().getPermite3D()){
                    System.out.println("\n------------------------------------------------------");
                    System.out.println("|           O filme será reproduzido em 3D?          |");
                    System.out.println("|                                                    |");
                    System.out.println("| 1 - Sim                                            |");
                    System.out.println("| 2 - Não                                            |");
                    System.out.println("------------------------------------------------------");
                    System.out.println("ATENÇÃO: Essa modificação não afeta as sessões já registradas.");

                    System.out.print("\nOpção: ");
                    leitor(1, 2, 'i');
                    sessao.setExibicao3D(false);
                    if(opcaoInt == 1) {
                        sessao.setExibicao3D(true);
                    }

                } else {
                    System.out.println("\nO filme que será exibido nesta sessão não permite reprodução em 3D.");
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
        System.out.print("\nQual sessão deseja remover?");
        leitor(1, cinema.getSessoes().size(), 'i');
        cinema.removerSessao(cinema.getSessoes().get(opcaoInt-1));
    }

    public static void gerenciarSalas(){
        ArrayList<Sala> salas = cinema.getSalas();
        
        limparTela();

        if(salas.size() == 0){ //GERENCIAR SALAS - PRIMEIRA VEZ
            System.out.println("-------------------------------------------");
            System.out.println("|   -> Você está gerenciando as salas <-  |");
            System.out.println("|                                         |");
            System.out.println("| Nenhuma sala foi definida.              |");
            System.out.println("|                                         |");
            System.out.println("| 1 - Criar uma nova sala.                |");
            System.out.println("| 2 - Voltar.                             |");
            System.out.println("-------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
            leitor(1, 2, 'i');

            if(opcaoInt == 1){
                criarSalas();
                gerenciarSalas();
            }
        } else { //GERENCIAR SALAS - >0 SALAS
            lerSalas();
            System.out.println("|                                         |");
            System.out.println("| 1 - Criar uma nova sala.                |");
            System.out.println("| 2 - Modificar uma sala.                 |");
            System.out.println("| 3 - Remover uma sala.                   |");
            System.out.println("| 4 - Voltar.                             |");
            System.out.println("-------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
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

        System.out.println("\n-------------------------------------------");
        System.out.println("|               SALAS DISPONÍVEIS         |");
        System.out.println("-------------------------------------------");
        System.out.println("| OPCAO | Nº DA SALA | CAPACIDADE DA SALA |");
        for (int i=0; i < salas.size(); i++ ) {
            Sala sala = salas.get(i);

            //Formatação número
            String numero = "  " + (i+1) + " "; 
            for(int n = numero.length(); n < 7; n++) {
                numero += " ";
            }
            
            //Formatação sala
            String numSala = "    " + sala.getNumSala() + " ";
            for(int s=numSala.length(); s < 12; s++) {
                numSala += " ";
            }

            //Formatação capacidade
            String capacidade = "        " + sala.getCapacidade() + " ";
            for(int c = capacidade.length(); c < 20; c++) {
                capacidade += " ";
            }

            System.out.println("|" + numero + "|" + numSala + "|" + capacidade + "|");
            System.out.println("-------------------------------------------");
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

        //Formatação número da sala
        String numSala = sala.getNumSala() + " ";
        for(int s = numSala.length(); s < 35; s++){
            numSala += " ";
        }

        //Formatação capacidade
        String capacidade = sala.getCapacidade() + " ";
        for(int c = capacidade.length(); c < 31; c++){
            capacidade += " ";
        }

        System.out.println("\n------------------------------------------------------");
        System.out.println("|               -> Modificando sala <-               |");
        System.out.println("|                                                    |");
        System.out.println("| Número da sala: " + numSala + "|");
        System.out.println("| Capacidade da sala: " + capacidade + "|");
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

        System.out.print("\nDigite a opção correspondente à sala que deseja remover: ");
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

	    limparTela();
        
        if(filmes.size() == 0){ //GERENCIAR FILMES - PRIMEIRA VEZ
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("|                  -> Você está gerenciando os filmes <-                      |");
            System.out.println("|                                                                             |");
            System.out.println("| Nenhum filme foi definido.                                                  |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Criar um novo filme.                                                    |");
            System.out.println("| 2 - Voltar.                                                                 |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
            leitor(1, 2, 'i');

            if(opcaoInt == 1){
                criarFilme();
                gerenciarFilmes();
            }
        } else { //GERENCIAR FILMES - >0 FILMES
            lerFilmes();
            System.out.println("|                                                                                                                                              |");
            System.out.println("| 1 - Criar uma novo filme.                                                                                                                    |");
            System.out.println("| 2 - Modificar um filme.                                                                                                                      |");
            System.out.println("| 3 - Remover um filme.                                                                                                                        |");
            System.out.println("| 4 - Voltar.                                                                                                                                  |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

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

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                              FILMES DISPONÍVEIS                                                              |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Nº |                 TITULO                 |                TIPO DE ÁUDIO               |  TIPO DE PRODUÇÃO  |  DURAÇÃO  |        3D        |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i = 0; i < filmes.size(); i++){ 
            Filme filme = filmes.get(i);

            //Formatação número
            String numero;
            if(i < 9) {
                numero = " " + (i+1)  + "  ";
            } else  {
                numero = " " + (i+1) + " ";
            }

            //Formatação título
            String titulo = " " + filme.getTitulo() + " ";
            for(int f=titulo.length(); f < 40; f++) {
                titulo += " ";
            }

            //Formatação áudio
            String audio = " " + Arrays.toString(filme.getTipoAudio()).replaceAll("\\[", " ").replaceAll("\\]", " ") + " ";
            for(int a = audio.length(); a < 44; a++) {
                audio += " ";
            }

            //Formatação produção
            String producao = " " + filme.getTipoProducao() + " ";
            for(int p=producao.length(); p < 20;p++){
                producao += " ";
            }
            
            //Formatação 3D
            String permite3D = "  Não disponível  ";
            if(filme.getPermite3D()) {
                permite3D = "    Disponível    ";
            }

            //Formatação duração
            String duracao = " " + filme.getDuracao() + " ";
            for(int d = duracao.length(); d < 11; d++){
                duracao += " ";
            }

            System.out.println("|" + numero + "|" + titulo + "|" + audio + "|" + producao + "|" + duracao + "|" + permite3D + "|");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
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

        System.out.println("\n-------------------------------------------------------------------------------");
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

        System.out.println("\n-------------------------------------------------------------------------------");
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

        //Formatação título
        String titulo = filme.getTitulo();
        for(int t=titulo.length(); t < 62; t++) {
            titulo += " ";
        }

        //Formatação tipo de áudio
        String audio = Arrays.toString(filme.getTipoAudio()).replaceAll("\\[", " ").replaceAll("\\]", " ") + " ";
        for(int a = audio.length(); a < 42; a++) {
            audio += " ";
        }

        //Formatação tipo de produção
        String producao = filme.getTipoProducao() ;
        for(int p = producao.length(); p < 52; p++) {
            producao += " ";
        }

        //Formatação duração
        String duracao = filme.getDuracao() + "";
        for(int d = duracao.length(); d < 61; d++) {
            duracao += " ";
        }

        //Formatação 3D
        String permite3D = "Não disponível                                 ";
        if(filme.getPermite3D()) {
            permite3D = "Disponível                                     ";
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                       -> Modificando filme <-                         |");
        System.out.println("|                                                                       |");
        System.out.println("| Título: " + titulo + "|");
        System.out.println("| Tipos de áudio disponíveis: " + audio + "|");
        System.out.println("| Tipo de produção: " + producao + "|");
        System.out.println("| Duração: " + duracao + "|");
        System.out.println("| Disponibilidade em 3D: " + permite3D + "|");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                                                                       |");
        System.out.println("| 1 - Alterar o título.                                                 |");
        System.out.println("| 2 - Alterar o tipo de áudio.                                          |");
        System.out.println("| 3 - Alterar o tipo de produção.                                       |");
        System.out.println("| 4 - Alterar a duração.                                                |");
        System.out.println("| 5 - Alterar a disponibilidade de 3D.                                  |");
        System.out.println("| 6 - Voltar.                                                           |");
        System.out.println("-------------------------------------------------------------------------");
        

        System.out.print("\nOpção: ");
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
                System.out.println("\nATENÇÃO: Essa modificação não altera a informação já registrada em alguma sessão.");
                filme.setTipoAudio(tipoAudio());

                modificarFilme();
                break;
            case 3:
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.println("|                  Qual o tipo de produção do filme?                    |");
                System.out.println("|                                                                       |");
                System.out.println("| 1 - Nacional                                                          |");
                System.out.println("| 2 - Estrangeira                                                       |");
                System.out.println("-------------------------------------------------------------------------");
        
                System.out.print("\nOpção: ");
                leitor(1, 2, 'i');
                filme.setTipoProducao("Estrangeira");
                if(opcaoInt == 1) {
                    filme.setTipoProducao("Nacional");
                }

                modificarFilme();
                break;
            case 4:
                System.out.println("\nATENÇÃO: Essa modificação não altera a informação já registrada em alguma sessão.");
                System.out.print("\nDigite a nova duração do filme (em minutos): ");
                leitor(1, 1000, 'i');
                
                filme.setDuracao(opcaoInt);
                
                modificarFilme();
                break;
            case 5:
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("|                  O filme permite reprodução em 3D?                    |");
            System.out.println("|                                                                       |");
            System.out.println("| 1 - Sim                                                               |");
            System.out.println("| 2 - Não                                                               |");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\nATENÇÃO: Essa modificação altera a informação já registrada em alguma sessão.");

                System.out.print("\nOpção: ");
                leitor(1, 2, 'i');

                if(opcaoInt == 1) {
                    filme.setPermite3D(true);
                } else {
                    for (Sessao sessao : cinema.getSessoes()) {
                        if(sessao.getFilme() == filme && sessao.getExibicao3D()){
                            sessao.setExibicao3D(false);
                        }
                    }
                    filme.setPermite3D(false);
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
        do {
            loop = false;
            System.out.print("\nDeseja vender um ingresso para qual sessão? ");
            leitor(1, cinema.getSessoes().size(), 'i');
            if(cinema.getSessoes().get(opcaoInt-1).taxaOcupacao() == 1) {
                System.out.print("\nEssa sessão não possui poltronas disponíveis. ");
                loop = true;
            }
        } while (loop);

        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

        System.out.println("\n------------------------------------------------------");
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

            System.out.print("\nDigite o número da poltrona: ");
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
        do {
            loop = false;
            System.out.print("\nDeseja cancelar venda para qual sessão? ");
            leitor(1, cinema.getSessoes().size(), 'i');
            if(cinema.getSessoes().get(opcaoInt-1).taxaOcupacao() == 0) {
                System.out.print("\nNenhum ingresso foi vendido para essa sessão.. ");
                loop = true;
            }
        } while (loop);

        
        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

        do {
            System.out.print(sessao.poltronasOcupadas());

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
        System.out.println("\nTotal de ingressos inteiros vendidos: " + cinema.getIngressosInteiras3D());
	    System.out.println("Total de ingressos inteiros vendidos (em reais): " + df.format(cinema.getFaturamentoInteiras3D()));
        System.out.println("Total de meio ingressos vendidos: " + cinema.getIngressosMeias3D());
	    System.out.println("Total de meio ingressos vendidos (em reais): " + df.format(cinema.getFaturamentoMeias3D()));
        System.out.println("\n                             > Sessões 2D < ");
        System.out.println("\nTotal de ingressos inteiros vendidos: " + cinema.getIngressosInteiras());
	    System.out.println("Total de ingressos inteiros vendidos (em reais): " + df.format(cinema.getFaturamentoInteiras()));
        System.out.println("Total de meio ingresso vendidos: " + cinema.getIngressosMeias());
	    System.out.println("Total de meio ingressos vendidos (em reais): " + df.format(cinema.getFaturamentoMeias()));
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
                        System.out.print("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-quantidadeOpcoes.
                    }

                } catch (NumberFormatException e) {
                    System.out.print("\nValor inválido, tente novamente: "); //Não é um inteiro.
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
                        System.out.print("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-quantidadeOpcoes.
                    }

                } catch (NumberFormatException e) {
                    System.out.print("\nValor inválido, tente novamente: "); //Não é um double.
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
        System.out.print("\nAperte enter para continuar...");
        temp = scanner.nextLine();
    }

    public static String[] tipoAudio(){
        String[] tipoAudio;

        System.out.println("\n-------------------------------------------------------------------------------");
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
