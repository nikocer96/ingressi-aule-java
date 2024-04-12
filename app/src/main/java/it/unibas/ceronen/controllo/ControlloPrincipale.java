package it.unibas.ceronen.controllo;

import it.unibas.ceronen.Applicazione;
import it.unibas.ceronen.modello.Accesso;
import it.unibas.ceronen.modello.Archivio;
import it.unibas.ceronen.modello.Aula;
import it.unibas.ceronen.modello.Costanti;
import it.unibas.ceronen.modello.DatiMesiFrequenti;
import it.unibas.ceronen.vista.VistaPrincipale;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import lombok.Getter;

@Getter

public class ControlloPrincipale {
    
    private Action azioneCerca = new AzioneCerca();
    private Action azioneSeleziona = new AzioneSeleziona();
    private Action azioneMesiFrequenti = new AzioneMesiFrequenti();
    
    public class AzioneMesiFrequenti extends AbstractAction {
        
        public AzioneMesiFrequenti() {
            this.putValue(NAME, "Verifica-M");
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            List<DatiMesiFrequenti> listaMesiFrequenti = archivio.cercaMesiFrequenti();
            Applicazione.getInstance().getModello().putBean(Costanti.LISTA_MESI_FREQUENTI, listaMesiFrequenti);
            Applicazione.getInstance().getVistaDettagliMesiFrequenti().visualizza();
        }
        
    }
    
    public class AzioneSeleziona extends AbstractAction {
        
        public AzioneSeleziona() {
            this.putValue(NAME, "Seleziona");
            this.putValue(SHORT_DESCRIPTION, "Seleziona un'aula");
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Aula> listaFiltrata = (List<Aula>) Applicazione.getInstance().getModello().getBean(Costanti.LISTA_FILTRATA);
            int selezionaPiano = Applicazione.getInstance().getVistaPrincipale().getSelezionaPiano();
            if (selezionaPiano == -1 ) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore("Selezionare prima un piano \n");
                return;
            }
            Aula aulaSelezionata = listaFiltrata.get(selezionaPiano);
            Applicazione.getInstance().getModello().putBean(Costanti.AULA_SELEZIONATA, aulaSelezionata);
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            List<Accesso> listaAccessiFiltrata = archivio.cercaAccessi(aulaSelezionata);
            Applicazione.getInstance().getModello().putBean(Costanti.LISTA_ACCESSI_FILTRATA, listaAccessiFiltrata);
            Applicazione.getInstance().getVistaDettagliAccessi().visualizza();
        }
        
    }
    
    public class AzioneCerca extends AbstractAction {
        
        public AzioneCerca() {
            this.putValue(NAME, "Cerca");
            this.putValue(SHORT_DESCRIPTION, "Cerca le aule");
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaPrincipale vistaPrincipale = Applicazione.getInstance().getVistaPrincipale();
            String numeroPiano = vistaPrincipale.getNumeroPiano();
            if (numeroPiano.trim().isEmpty()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore("Aggiungere un piano \n");
                return;
            }
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            List<Aula> listaFiltrata = archivio.cercaAule(numeroPiano);
            Applicazione.getInstance().getModello().putBean(Costanti.LISTA_FILTRATA, listaFiltrata);
            vistaPrincipale.aggiorna();
            Applicazione.getInstance().getControlloPrincipale().getAzioneSeleziona().setEnabled(true);
        }
    }
}
