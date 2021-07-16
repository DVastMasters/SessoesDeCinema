import java.sql.Time;

public class Filme {
	private String titulo, tipoProducao;
	private Time duracao;
	private String[] tipoAudio;
	private boolean permite3D;
	
	public Filme(String titulo, String tipoProducao, Time duracao, String[] tipoAudio, boolean permite3d){
		this.titulo = titulo;
		this.tipoProducao = tipoProducao;
		this.duracao = duracao;
		this.tipoAudio = tipoAudio;
		this.permite3D = permite3D;	
	}
}
