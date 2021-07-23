import java.time.LocalTime;

public class Filme {
	private String titulo, tipoProducao;
	private int duracao;
	private String[] tipoAudio;
	private boolean permite3D;

	public Filme(String titulo, int duracao, String tipoProducao, String[] tipoAudio, boolean permite3D) {
		this.titulo = titulo;
		this.duracao = duracao;
		this.tipoProducao = tipoProducao;
		this.tipoAudio = tipoAudio;
		this.permite3D = permite3D;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public String getTipoProducao() {
		return tipoProducao;
	}	
	
	public String[] getTipoAudio() {
		return tipoAudio;
	}
	
	public boolean getPermite3D()  {
		return permite3D;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public void setTipoProducao(String tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

	public void setTipoAudio(String[] tipoAudio) {
		this.tipoAudio = tipoAudio;
	}

	public void setPermite3D(boolean permite3D) {
		this.permite3D = permite3D;
	}
}
