 import java.time.LocalTime;

public class Sessao {
    private Filme filme;
    private Sala sala;
    private LocalTime horario;
    private double valorIngresso;
    private char[] poltronas; //l = livre; m = meia; i = inteira
    private boolean exibicao3D;
    private String tipoAudio;

    public Sessao(Filme filme, Sala sala, LocalTime horario, double valorIngresso, boolean exibicao3D, String tipoAudio){
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
        this.valorIngresso = valorIngresso;
        this.exibicao3D = exibicao3D;
        this.tipoAudio = tipoAudio;

        poltronas = new char[sala.getCapacidade()]; // = String[10], por exemplo
        for(int i=0; i < poltronas.length; i++) {
            poltronas[i] = 'l';
        }
    }

    public boolean ocuparPoltrona(int posicao, char tipoIngresso) { // Extra
        boolean boo=true;

        if(poltronas[posicao] == 'l') {
            poltronas[posicao] = tipoIngresso;
        } else {
            boo = false;
        }

        return boo;
    }

    public boolean liberarPoltrona(int posicao) { // Extra
        boolean boo=true;

        if(poltronas[posicao] != 'l') {
            poltronas[posicao] = 'l';
        } else {
            boo = false;
        }
        
        return boo;
    }

    public double taxaOcupacao(){
        double ocupados=0;
        
        for(int i=0; i < poltronas.length; i++) {
            if(poltronas[i] != 'l') { // == m ou == i
                ocupados++;
            }
        }

        return ocupados / sala.getCapacidade();
    }
   
    public String poltronasLivres(){
        int poltronasLivres = 0;
        String poltronasNum = "|  ";
        for(int i = 0; i < poltronas.length; i++){
            if(poltronas[i] == 'l'){
                poltronasLivres++;
                if (i<11){
                    poltronasNum += " " + (i+1) + "  |  ";    
                } else {
                    poltronasNum += (i+1) + "  |  ";
                }
                if(i % 10 == 0) {
                    poltronasNum += "\n";
                }
            }
        }
        return "Quantidade de poltronas livres: " + poltronasLivres + "\n   > Poltronas <   \n" + poltronasNum;
    }

    public String poltronasOcupadas(){
        int poltronasOcupadas = 0;
        String poltronasNum = "|  ";
        for(int i = 0; i < poltronas.length; i++){
            if(poltronas[i] != 'l'){
                poltronasOcupadas++;
                if (i<11){
                    poltronasNum += " " + (i+1) + "  |  ";    
                } else {
                    poltronasNum += (i+1) + "  |  ";    
                }
                if(i % 10 == 0) {
                    poltronasNum += "\n";
                }
            }
        }
        return "Quantidade de poltronas Ocupadas: " + poltronasOcupadas + "\n   > Poltronas <   \n" + poltronasNum;
    }

    public LocalTime getHorario(){
        return horario;
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

    public void setHorario(LocalTime horario){
        this.horario = horario; 
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
}
