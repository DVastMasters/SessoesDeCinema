import java.time.LocalTime;

public class Sessao implements Comparable<Sessao>{
    private Filme filme;
    private Sala sala;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private double valorIngresso;
    private char[] poltronas; //l = livre; m = meia; i = inteira
    private boolean exibicao3D;
    private String tipoAudio;

    public Sessao(Filme filme, Sala sala, LocalTime horarioInicial, LocalTime horarioFinal, double valorIngresso, boolean exibicao3D, String tipoAudio){
        this.filme = filme;
        this.sala = sala;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.valorIngresso = valorIngresso;
        this.exibicao3D = exibicao3D;
        this.tipoAudio = tipoAudio;

        poltronas = new char[sala.getCapacidade()];
        for(int i=0; i < poltronas.length; i++) { //Inicializando todas as poltronas como livres.
            poltronas[i] = 'l';
        }
    }

    public boolean ocuparPoltrona(int poltrona, char tipoIngresso) {

        if(poltronas[poltrona] == 'l') {
            poltronas[poltrona] = tipoIngresso;
            return true;
        } else {
            return false;
        }

    }

    public boolean liberarPoltrona(int poltrona) {

        if(poltronas[poltrona] != 'l') {
            poltronas[poltrona] = 'l';
            return true;
        } else {
            return false;
        }

    }

    public double taxaOcupacao(){
        double ocupados=0;

        for (char p : poltronas) {
            if(p != 'l'){
                ocupados++;
            }         
        }

        return ocupados / sala.getCapacidade();
    }
   
    public String poltronasLivres(){
        int quantidade = 0;
        String poltronasLivres = "|  ";

        for(int i = 0; i < poltronas.length; i++){

            if(poltronas[i] == 'l'){
                quantidade++;

                if (i<9){
                    poltronasLivres += " " + (i+1) + "  |  ";    
                } else {
                    poltronasLivres += (i+1) + "  |  ";
                }

                if((i+1) % 10 == 0 && i != 0 && i != poltronas.length) { //Dividir em 10 colunas
                    poltronasLivres += "\n|  ";
                }
            }
        }
        return "Quantidade de poltronas livres: " + quantidade + "\n   > Poltronas <   \n" + poltronasLivres;
    }

    public String poltronasOcupadas(){
        int quantidade = 0;
        String poltronasOcupadas = "|  ";

        for(int i = 0; i < poltronas.length; i++){

            if(poltronas[i] != 'l'){
                quantidade++;

                if (i<9){
                    poltronasOcupadas += " " + (i+1) + "  |  ";    
                } else {
                    poltronasOcupadas += (i+1) + "  |  ";    
                }

                if(i % 9 == 0 && i != 0 && i != poltronas.length) { //Dividir em 10 colunas
                    poltronasOcupadas += "\n|  ";
                }
            }
        }
        return "\n Quantidade de poltronas Ocupadas: " + quantidade + "\n\n    > Poltronas <   \n" + poltronasOcupadas;
    }

    public LocalTime getHorarioInicial(){
        return horarioInicial;
    }

    public LocalTime getHorarioFinal(){
        return horarioFinal;
    }

    public void setHorarioInicial(LocalTime horarioInicial){
        this.horarioInicial = horarioInicial;
    }

    public void setHorarioFinal(LocalTime horarioFinal){
        this.horarioFinal = horarioFinal;
    }

    public double getValorIngresso(){
        return valorIngresso;
    }

    public char[] getPoltronas(){
        return poltronas;
    }

    public Sala getSala(){
        return sala;
    }

    public Filme getFilme(){
        return filme;
    }
    
    public boolean getExibicao3D(){
        return exibicao3D;
    }

    public String getTipoAudio(){
        return tipoAudio;
    }

    public void setValorIngresso(double valorIngresso){
        this.valorIngresso = valorIngresso; 
    }

    public void setSala(Sala sala){
        this.sala = sala; 
    }

    public void setFilme(Filme filme){
        this.filme = filme; 
    }

    public void setExibicao3D(boolean exibicao3D){
        this.exibicao3D = exibicao3D; 
    }

    public void setTipoAudio(String tipoAudio){
        this.tipoAudio = tipoAudio;
    }

    @Override
    public int compareTo(Sessao sessao) {
        if(this.horarioInicial.toSecondOfDay() > sessao.horarioInicial.toSecondOfDay()) {
            return 1;
        }
        if(this.horarioInicial.toSecondOfDay() < sessao.horarioInicial.toSecondOfDay()) {
            return -1;
        }
        return 0;
    }
}
