package it.unibas.ceronen.controllo;

import it.unibas.ceronen.Applicazione;
import it.unibas.ceronen.modello.Archivio;
import it.unibas.ceronen.modello.Costanti;
import it.unibas.ceronen.persistenza.DAOException;
import it.unibas.ceronen.persistenza.IDAOArchivio;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import lombok.Getter;

@Getter

public class ControlloFrame {
    
    private Action azioneEsci = new AzioneEsci();
    private Action azioneCarica = new AzioneCarica();
    private Action azioneVerifica = new AzioneVerifica();
    
    public class AzioneVerifica extends AbstractAction {
        
        public AzioneVerifica() {
            this.putValue(NAME, "Verifica");
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            if (archivio.verificaAccessiDomenica()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore("Accessi duplicati \n");
                return;
            }
             Applicazione.getInstance().getFrame().mostraMessaggio("NON ci sono Accessi duplicati \n");
        }
    }
    
    public class AzioneCarica extends AbstractAction {
        
        public AzioneCarica() {
            this.putValue(NAME, "Carica");
            this.putValue(SHORT_DESCRIPTION, "Carica Archivio");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IDAOArchivio daoArchivio = Applicazione.getInstance().getDaoArchivio();
            try {
                Archivio archivio = daoArchivio.carica("");
                Applicazione.getInstance().getModello().putBean(Costanti.ARCHIVIO, archivio);
                Applicazione.getInstance().getFrame().mostraMessaggio("Caricato l'archivio contenente " + archivio.getListaAule().size() + " aule");
                Applicazione.getInstance().getControlloPrincipale().getAzioneCerca().setEnabled(true);
                Applicazione.getInstance().getControlloFrame().getAzioneVerifica().setEnabled(true);
                Applicazione.getInstance().getControlloPrincipale().getAzioneMesiFrequenti().setEnabled(true);
            } catch(DAOException ex) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore("Errore nel caricamento");
            }         
        }
        
    }
    
    public class AzioneEsci extends AbstractAction {
        
        public AzioneEsci() {
            this.putValue(NAME, "Esci");
            this.putValue(SHORT_DESCRIPTION, "Esci dall'applicazione");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
