import java.util.ArrayList;
import java.util.Collections;

public class Cinema {
    private double faturamentoInteiras, faturamentoInteiras3D, faturamentoMeias, faturamentoMeias3D;
    private int ingressosInteiras, ingressosInteiras3D, ingressosMeias, ingressosMeias3D; 
    private ArrayList<Sala> salas;
    private ArrayList<Filme> filmes;
    private ArrayList<Sessao> sessoes;

    public Cinema(){
        faturamentoInteiras = 0;
        faturamentoInteiras3D = 0;
        faturamentoMeias = 0;
        faturamentoMeias3D = 0;
        ingressosInteiras = 0;
        ingressosInteiras3D = 0;
        ingressosMeias = 0;
        ingressosMeias3D =  0;
        salas = new ArrayList<>();
        filmes = new ArrayList<>();
        sessoes = new ArrayList<>();
    }

	public void fechar(){
		faturamentoInteiras = 0;
		faturamentoInteiras3D = 0;
        faturamentoMeias = 0;
        faturamentoMeias3D = 0;
        ingressosInteiras = 0;
        ingressosInteiras3D = 0;
        ingressosMeias = 0;
        ingressosMeias3D =  0;
        sessoes.clear();
	}

    public void novaSala(Sala sala) {
        salas.add(sala);
        Collections.sort(salas);
    }

    public void removerSala(Sala sala) {
        salas.remove(sala);
    }

    public void novoFilme(Filme filme) {
        filmes.add(filme);
        Collections.sort(filmes);
    }

    public void removerFilme(Filme filme) {
        filmes.remove(filme);
    }

    public void novaSessao(Sessao sessao) {
        sessoes.add(sessao);
        Collections.sort(sessoes);
    }

    public void removerSessao(Sessao sessao) {
        sessoes.remove(sessao);
    }

    public boolean venderIngresso(Sessao sessao, char tipoIngresso, int poltrona){               

        if(sessao.ocuparPoltrona(poltrona, tipoIngresso)) { //Poltrona ocupada com sucesso.

            if(sessao.getExibicao3D()) { //A sessão é 3D.

                if(tipoIngresso == 'i') {
                    ingressosInteiras3D++;
                    faturamentoInteiras3D += sessao.getValorIngresso();
                } else {
                    ingressosMeias3D++;
                    faturamentoMeias3D += sessao.getValorIngresso() / 2;
                }

            } else {                       

                if(tipoIngresso == 'i') {
                    ingressosInteiras++;
                    faturamentoInteiras += sessao.getValorIngresso();
                } else {
                    ingressosMeias++;
                    faturamentoMeias += sessao.getValorIngresso() / 2;
                }
            }

        } else {
            return false;
        }
        
        return true;
    }

    public boolean cancelarVenda(Sessao sessao, int poltrona){
        char tipoIngresso = sessao.getPoltronas()[poltrona]; //Salva o tipo de ingresso que será sobrescrito.

        if(sessao.liberarPoltrona(poltrona)) { //Poltrona liberada com sucesso.

            if(sessao.getExibicao3D()) {    //A sessão é 3D.    

                if(tipoIngresso == 'i') {
                    ingressosInteiras3D--;
                    faturamentoInteiras3D -= sessao.getValorIngresso();
                } else {
                    ingressosMeias3D--;
                    faturamentoMeias3D -= sessao.getValorIngresso() / 2;
                }

            } else {
                if(tipoIngresso == 'i') {
                    ingressosInteiras--;
                    faturamentoInteiras -= sessao.getValorIngresso();
                } else {
                    ingressosMeias--;
                    faturamentoMeias -= sessao.getValorIngresso() / 2;
                }
            }

        } else {
            return false;
        }

        return true;
    }

    public double getFaturamentoInteiras(){
        return faturamentoInteiras;
    }

    public double getFaturamentoInteiras3D(){
        return faturamentoInteiras3D;
    }

    public double getFaturamentoMeias(){
        return faturamentoMeias;
    }

    public double getFaturamentoMeias3D(){
        return faturamentoMeias3D;
    }

    public int getIngressosInteiras(){
        return ingressosInteiras;
    }

    public int getIngressosInteiras3D(){
        return ingressosInteiras3D;
    }

    public int getIngressosMeias(){
        return ingressosMeias;
    }

    public int getIngressosMeias3D(){
        return ingressosMeias3D;
    }

    public ArrayList<Sala> getSalas(){
        return salas;
    }

    public ArrayList<Filme> getFilmes(){
        return filmes;
    }

    public ArrayList<Sessao> getSessoes(){
        return sessoes;
    }

}

