import java.util.Collection;
import java.util.ArrayList;

public class Cinema {
    private double faturamentoInteiras, faturamentoInteiras3D, faturamentoMeias, faturamentoMeias3D;
    private Collection<Sala> salas;
    private Collection<Filme> filmes;
    private Collection<Sessao> sessoes;

    public Cinema(){
        faturamentoInteiras = 0;
        faturamentoInteiras3D = 0;
        faturamentoMeias = 0;
        faturamentoMeias3D = 0;
        salas = new ArrayList<Sala>();
        filmes = new ArrayList<Filme>();
        sessoes = new ArrayList<Sessao>();
    }

	public void resetar(){
		faturamentoInteiras = 0;
		faturamentoInteiras3D = 0;
        faturamentoMeias = 0;
        faturamentoMeias3D = 0;
        salas.clear();
        filmes.clear();
        sessoes.clear();
	}

    public void novaSala(Sala sala) { //Extra
        salas.add(sala);
    }

    public void novoFilme(Filme filme) { //Extra
        filmes.add(filme);
    }

    public void novaSessao(Sessao sessao) { //Extra
        sessoes.add(sessao);
    }

    public boolean venderIngresso(Sessao sessao, char tipoIngresso, int poltrona){               
        boolean boo = true;

        if(sessao.ocuparPoltrona(poltrona, tipoIngresso)) { //É o mesmo que sessao.ocuparPoltrona(poltrona, tipoIngresso) == True
            if(sessao.getExibicao3D()) {                    //É o mesmo que sessao.getExibicao3D() == True
                if(tipoIngresso == 'i') {
                    faturamentoInteiras3D += sessao.getValorIngresso();
                } else {
                    faturamentoMeias3D += sessao.getValorIngresso() / 2;
                }
            } else {                                        //sessao.getExibicao3D() == False
                if(tipoIngresso == 'i') {
                    faturamentoInteiras += sessao.getValorIngresso();
                } else {
                    faturamentoMeias += sessao.getValorIngresso() / 2;
                }
            }
        } else {
            boo = false;
        }
        
        return boo;
    }

    public boolean cancelarVenda(Sessao sessao, int poltrona){
        boolean boo = true;
        char tipoIngresso = sessao.getPoltronas()[poltrona];

        if(sessao.liberarPoltrona(poltrona)) {
            if(sessao.getExibicao3D()) {                    //sessao.getExibicao3D() == True
                if(tipoIngresso == 'i') {
                    faturamentoInteiras3D -= sessao.getValorIngresso();
                } else {
                    faturamentoMeias3D -= sessao.getValorIngresso() / 2;
                }
            } else {                                        //sessao.getExibicao3D() == False
                if(tipoIngresso == 'i') {
                    faturamentoInteiras -= sessao.getValorIngresso();
                } else {
                    faturamentoMeias -= sessao.getValorIngresso() / 2;
                }
            }
        } else {
            boo = false;
        }

        return boo;
    }

    public double getFaturamentoInteiras(){                 //Mundança de planos, acredito que assim vai ser melhor.
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

    public Sala[] getSalas(){
        return salas.toArray(new Sala[salas.size()]);
    }

    public Filme[] getFilmes(){
        return filmes.toArray(new Filme[filmes.size()]);
    }

    public Sessao[] getSessoes(){
        return sessoes.toArray(new Sessao[sessoes.size()]);
    }

}

