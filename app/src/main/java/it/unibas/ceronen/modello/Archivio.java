package it.unibas.ceronen.modello;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter

public class Archivio {

    private List<Aula> listaAule = new ArrayList<>();

    public void addAula(Aula aula) {
        this.listaAule.add(aula);
    }
    
    public List<DatiMesiFrequenti> cercaMesiFrequenti() {
        List<DatiMesiFrequenti> listaMesiFrequenti = new ArrayList<>();
        Map<Integer, List<Accesso>> mappaPerMese = calcolaAccessiMese();
        for (Integer mese : mappaPerMese.keySet()) {
            List<Accesso> listaAccessiMese = mappaPerMese.get(mese);
            String motivazioneFrequente = calcolaMotivazioneFrequente(listaAccessiMese);
            int tempoTotale = calcolaTempoTotale(listaAccessiMese);
            DatiMesiFrequenti datiMesiFrequenti = new DatiMesiFrequenti(mese, motivazioneFrequente, tempoTotale);
            listaMesiFrequenti.add(datiMesiFrequenti);
        }
        Collections.sort(listaMesiFrequenti, new ComparatoreDatiMesi());
        return listaMesiFrequenti;
    }
    
    private int calcolaTempoTotale(List<Accesso> listaAccessiMese) {
        int somma = 0;
        for (Accesso accesso : listaAccessiMese) {
            somma += accesso.getPermanenza();
        }
        return somma;
    }
    
    private String calcolaMotivazioneFrequente(List<Accesso> listaAccessiMese) {
        Map<String, Integer> mappaPerTipologia = calcolaOccorrenzeTipologia(listaAccessiMese);
        String motivazioneMax = null;
        for (String motivazione : mappaPerTipologia.keySet()) {
            if (motivazioneMax == null || mappaPerTipologia.get(motivazione) > mappaPerTipologia.get(motivazioneMax)) {
                motivazioneMax = motivazione;
            }
        }
        return motivazioneMax;
    }
    
    private Map<String, Integer> calcolaOccorrenzeTipologia(List<Accesso> listaAccessiMese) {
        Map<String, Integer> mappPerTipologia = new HashMap<>();
        for (Accesso accesso : listaAccessiMese) {
            String tipologia = accesso.getMotivazione();
            Integer contaOccorrenze = mappPerTipologia.get(tipologia);
            if (contaOccorrenze == null) {
                mappPerTipologia.put(tipologia, 1); // SE C'E' INTEGER COME CONTAOCC. NEL PUNTO PUT BISOGNA METTERE 1 E NON CONTAOCCORRENZE
            } else {
                mappPerTipologia.put(tipologia, contaOccorrenze + 1);
            }
        }
        return mappPerTipologia;
    }
    
    private Map<Integer, List<Accesso>> calcolaAccessiMese() {
        Map<Integer, List<Accesso>> mappaPerMese = new HashMap<>();
        for (Aula aula : listaAule) {
            for (Accesso accesso : aula.getListaAccessi()) {
                Integer mese = accesso.getData().getMonthValue();
                List<Accesso> listaAccessiMese = mappaPerMese.get(mese);
                if (listaAccessiMese == null) {
                    listaAccessiMese = new ArrayList<>();
                    mappaPerMese.put(mese, listaAccessiMese);
                }
                listaAccessiMese.add(accesso);
            }
        }
        return mappaPerMese;
    }

    private List<Accesso> cercaAccessiDomenica() {
        List<Accesso> listaAccessiDomenica = new ArrayList<>();
        for (Aula aula : listaAule) {
            for (Accesso accesso : aula.getListaAccessi()) {
                if (accesso.getData().getDayOfWeek() == DayOfWeek.SUNDAY) {
                    listaAccessiDomenica.add(accesso);
                }
            }
        }
        return listaAccessiDomenica;
    }
    
    public boolean verificaAccessiDomenica() {
        Map<String, Integer> mappaUguali = new HashMap<>();
        List<Accesso> listaAccessiDomenica = cercaAccessiDomenica();
        for (Accesso accesso : listaAccessiDomenica) {
            int interoAnno = accesso.getData().getYear();
            int interoMese = accesso.getData().getMonthValue();
            int interoGiorno = accesso.getData().getDayOfYear();
            LocalDate dataPartizionata = LocalDate.of(interoAnno, interoMese, interoGiorno);
            String chiave = accesso.getMatricola() + "" + dataPartizionata + "";
            Integer conta = mappaUguali.get(chiave);
            if (conta == null) {
                conta = 0;
            }
            mappaUguali.put(chiave, conta + 1);
        }
        for (Integer conta : mappaUguali.values()) {
            if (conta > 1) {
                return true;
            }
        }
        return false;
    }

    public List<Accesso> cercaAccessi(Aula aulaSelezionata) {
        List<Accesso> listaAccessiFiltrata = new ArrayList<>();
        for (Accesso accesso : aulaSelezionata.getListaAccessi()) {
            listaAccessiFiltrata.add(accesso);
        }
        return listaAccessiFiltrata;
    }

    public List<Aula> cercaAule(String numeroPiano) {
        List<Aula> listaFiltrata = new ArrayList<>();
        int interoNumeroPiano = Integer.parseInt(numeroPiano);
        for (Aula aula : listaAule) {
            if (aula.getPiano() <= interoNumeroPiano) {
                listaFiltrata.add(aula);
            }
        }
        Collections.sort(listaFiltrata, new ComparatoreOrdinamentoCrescente());
        return listaFiltrata;
    }
}
