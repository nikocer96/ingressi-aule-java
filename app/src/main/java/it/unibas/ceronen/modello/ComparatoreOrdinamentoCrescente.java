package it.unibas.ceronen.modello;

import java.util.Comparator;

public class ComparatoreOrdinamentoCrescente implements Comparator<Aula> {

    @Override
    public int compare(Aula o1, Aula o2) {
        return o1.getPiano() - o2.getPiano();
    }
}
