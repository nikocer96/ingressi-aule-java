package it.unibas.ceronen.modello;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class Aula {
    
    private String codiceUnivoco;
    private String nomeAula;
    private int piano;
    private List<Accesso> listaAccessi = new ArrayList<>();

    public Aula(String codiceUnivoco, String nomeAula, int piano) {
        this.codiceUnivoco = codiceUnivoco;
        this.nomeAula = nomeAula;
        this.piano = piano;
    }
    
    public void addAccesso(Accesso accesso) {
        this.listaAccessi.add(accesso);
    }
    
    
}
