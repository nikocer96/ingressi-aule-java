package it.unibas.ceronen.modello;

import java.util.Comparator;

public class ComparatoreDatiMesi implements Comparator<DatiMesiFrequenti> {

    @Override
    public int compare(DatiMesiFrequenti o1, DatiMesiFrequenti o2) {
        return o1.getMese() - (o2.getMese());
    } 
}
