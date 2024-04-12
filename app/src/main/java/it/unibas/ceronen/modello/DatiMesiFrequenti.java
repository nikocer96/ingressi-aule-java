package it.unibas.ceronen.modello;

import lombok.Getter;

@Getter

public class DatiMesiFrequenti {
    
    private int mese;
    private String tipologia;
    private int tempoTotale;

    public DatiMesiFrequenti(int mese, String tipologia, int tempoTotale) {
        this.mese = mese;
        this.tipologia = tipologia;
        this.tempoTotale = tempoTotale;
    } 
}
