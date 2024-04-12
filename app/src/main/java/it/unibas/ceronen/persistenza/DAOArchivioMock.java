package it.unibas.ceronen.persistenza;

import it.unibas.ceronen.modello.Accesso;
import it.unibas.ceronen.modello.Archivio;
import it.unibas.ceronen.modello.Aula;
import it.unibas.ceronen.modello.Costanti;
import java.time.LocalDateTime;
import java.time.Month;

public class DAOArchivioMock implements IDAOArchivio {
    
    @Override
    public Archivio carica(String nomeFile) throws DAOException {
        Archivio archivio = new Archivio();
        
        Aula a1 = new Aula("ASED", "Aula 1", 2);
        Aula a2 = new Aula("LOPI", "Aula 2", 2);
        Aula a3 = new Aula("NMJI", "Aula 3", 1);
        
        a1.addAccesso(new Accesso(12345, "Gino", 14.55, Costanti.LEZIONE, LocalDateTime.of(2024, Month.JANUARY, 7, 13, 50)));
        a2.addAccesso(new Accesso(23457, "Rocco", 19.55, Costanti.LEZIONE, LocalDateTime.of(2020, Month.SEPTEMBER, 22, 15, 50)));
        a3.addAccesso(new Accesso(12345, "Lucia", 24.55, Costanti.RICEVIMENTO, LocalDateTime.of(2024, Month.JANUARY, 7, 14, 50)));
        a2.addAccesso(new Accesso(78906, "Finizia", 34.55, Costanti.ESAME, LocalDateTime.of(2019, Month.JANUARY, 17, 21, 50)));
        a1.addAccesso(new Accesso(23431, "Moreno", 54.55, Costanti.ESAME, LocalDateTime.of(2021, Month.MARCH, 2, 20, 50)));
        
        archivio.addAula(a1);
        archivio.addAula(a2);
        archivio.addAula(a3);
        
        return archivio;
    }
}
