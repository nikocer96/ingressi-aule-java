package it.unibas.ceronen.controllo;

import it.unibas.ceronen.Applicazione;
import it.unibas.ceronen.modello.Accesso;
import it.unibas.ceronen.modello.Archivio;
import it.unibas.ceronen.modello.Aula;
import it.unibas.ceronen.modello.Costanti;
import it.unibas.ceronen.vista.VistaDettagliAccessi;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import lombok.Getter;

@Getter

public class ControlloDettagliAccesso {

    private Action azioneAggiungi = new AzioneAggiungi();

    public class AzioneAggiungi extends AbstractAction {

        public AzioneAggiungi() {
            this.putValue(NAME, "Aggiungi");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaDettagliAccessi vistaDettagliAccessi = Applicazione.getInstance().getVistaDettagliAccessi();
            String matricola = vistaDettagliAccessi.getMatricola();
            String nome = vistaDettagliAccessi.getNome();
            String durata = vistaDettagliAccessi.getDurata();
            String motivazione = vistaDettagliAccessi.getMotivazione();
            String anno = vistaDettagliAccessi.getAnno();
            String mese = vistaDettagliAccessi.getMese();
            String giorno = vistaDettagliAccessi.getGiorno();
            String ora = vistaDettagliAccessi.getOra();
            String minuti = vistaDettagliAccessi.getMinuti();
            String errori = convalida(matricola, nome, durata, motivazione, anno, mese, giorno, ora, minuti);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore(errori);
                return;
            }
            int interoMatricola = Integer.parseInt(matricola);
            double doubleDurata = Double.parseDouble(durata);
            int interoAnno = Integer.parseInt(anno);
            int interoMese = Integer.parseInt(mese);
            int interoGiorno = Integer.parseInt(giorno);
            int interoOra = Integer.parseInt(ora);
            int interoMinuti = Integer.parseInt(minuti);
            LocalDateTime dataNuova = LocalDateTime.of(interoAnno, interoMese, interoGiorno, interoOra, interoMinuti);
            String erroreOra = convalidaOra(dataNuova);
            if (!erroreOra.isEmpty()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore(erroreOra);
                return;
            }
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            Aula aulaSelezionata = (Aula) Applicazione.getInstance().getModello().getBean(Costanti.AULA_SELEZIONATA);
            Accesso nuovoAccesso = new Accesso(interoMatricola, nome, doubleDurata, motivazione, dataNuova);
            aulaSelezionata.addAccesso(nuovoAccesso);
            List<Accesso> listaAccessiFiltrata = archivio.cercaAccessi(aulaSelezionata);
            Applicazione.getInstance().getModello().replace(Costanti.LISTA_ACCESSI_FILTRATA, listaAccessiFiltrata);
            vistaDettagliAccessi.aggiornaDati();                                                       
        }
        
        private String convalidaOra(LocalDateTime dataNuova) {
            StringBuilder errore = new StringBuilder();
            LocalDateTime dataOdierna = LocalDateTime.now();
            if (dataNuova.getHour() > dataOdierna.getHour()) {
                errore.append("L'ora non inserita non puo' essere successiva all'ora attuale \n");
            }
            return errore.toString();
        }

        private String convalida(String matricola, String nome, String durata, String motivazione, String anno, String mese, String giorno, String ora, String minuti) {
            StringBuilder errori = new StringBuilder();
            if (matricola.trim().isEmpty()) {
                errori.append("Il campo matricola non puo' essere vuoto \n");
            } else {
                try {
                    int interoMatricola = Integer.parseInt(matricola);
                } catch (NumberFormatException ex) {
                    errori.append("Matricola deve essere un intero \n");
                }
            }
            if (nome.trim().isEmpty()) {
                errori.append("Il campo nome non puo' essere vuoto \n");
            }
            if (durata.trim().isEmpty()) {
                errori.append("Il campo durata non puo' essere vuoto \n");
            } else {
                try {
                    double doubleDurata = Double.parseDouble(durata);
                } catch (NumberFormatException ex) {
                    errori.append("Durata deve essere un double \n");
                }
            }
            if (motivazione.trim().isEmpty()) {
                errori.append("Il campo motivzione non puo' essere vuoto \n");
            }
            if (anno.trim().isEmpty()) {
                errori.append("Il campo anno non puo' essere vuoto \n");
            }
            if (mese.trim().isEmpty()) {
                errori.append("Il campo mese non puo' essere vuoto \n");
            }
            if (giorno.trim().isEmpty()) {
                errori.append("Il campo giorno non puo' essere vuoto \n");
            }
            if (ora.trim().isEmpty()) {
                errori.append("Il campo ora non puo' essere vuoto \n");
            }
            if (minuti.trim().isEmpty()) {
                errori.append("Il campo minuti non puo' essere vuoto \n");
            }
            try {
                int interoAnno = Integer.parseInt(anno);
                int interoMese = Integer.parseInt(mese);
                int interoGiorno = Integer.parseInt(giorno);
                int interoOra = Integer.parseInt(ora);
                int interoMinuti = Integer.parseInt(minuti);
            } catch (NumberFormatException ex) {
                errori.append("Data scorretta\n");
            }
            return errori.toString();
        }

    }
}
