public class Sala implements Comparable<Sala>{
	private int numSala;
	private int capacidade;

	public Sala(int numSala, int capacidade) {
		this.numSala = numSala;
		this.capacidade = capacidade;
	}

	public int getNumSala() {
		return numSala;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;	
	}	

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
    }

	@Override
	public int compareTo(Sala sala) {
		if(this.numSala > sala.numSala){
			return 1;
		}
		if(this.numSala < sala.numSala){
			return -1;
		}
		return 0;
	}	
}
