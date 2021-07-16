import java.sql.Time;

public class Sessao {
	private Filme filme;
	private Sala sala;
	private Time horario;
	private int numSessao;
	private double precoInteira, precoMeia;
	private boolean[] poltronas;

	public Sessao(Filme filme, Sala sala, Time horario, int numSessao, double precoInteria) {

		this.filme = filme;
		this.sala = sala;
		this.horario = horario;
		this.numSessao = numSessao;
		this.precoInteira = precoInteira;
		
		precoMeia = precoInteira / 2.0;
		poltronas = new boolean[sala.getCapacidade()];
	}
		
}
