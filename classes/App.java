import java.util.Scanner;
import java.sql.Time;
import java.util.Calendar;

public class App {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Time duracao = new Time(cal.getTimeInMillis());
		Scanner entrada = new Scanner(System.in);
		
		System.out.println(duracao + "\n" + cal);
		a = 3
		String[3] tipoAudio;
		Filme filme1 = new Filme("Em nome da Rosa", "Estrangeira", duracao, tipoAudio, true);

/*
		Scanner entrada = new Scanner(System.in);	
		System.out.println("-> A venda de ingressos ainda não foi inicada! <-\n");
		System.out.println("Escolha uma opção:\n");
		System.out.println("1 - Gerenciar sessões.\n2 - Gerenciar salas.\n3 - Gerenciar filmes.\n");

*/
	}	
}
