package it.unibas.ceronen.modello;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter

public class Accesso {
    
    private int matricola;
    private String nomeStudente;
    private double permanenza;
    private String motivazione;
    private LocalDateTime data;

    public Accesso(int matricola, String nomeStudente, double permanenza, String motivazione, LocalDateTime data) {
        this.matricola = matricola;
        this.nomeStudente = nomeStudente;
        this.permanenza = permanenza;
        this.motivazione = motivazione;
        this.data = data;
    }
}
