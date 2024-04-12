package it.unibas.ceronen;

import it.unibas.ceronen.controllo.ControlloDettagliAccesso;
import it.unibas.ceronen.controllo.ControlloFrame;
import it.unibas.ceronen.controllo.ControlloPrincipale;
import it.unibas.ceronen.modello.Modello;
import it.unibas.ceronen.persistenza.DAOArchivioMock;
import it.unibas.ceronen.persistenza.IDAOArchivio;
import it.unibas.ceronen.vista.Frame;
import it.unibas.ceronen.vista.VistaDettagliAccessi;
import it.unibas.ceronen.vista.VistaDettagliMesiFrequenti;
import it.unibas.ceronen.vista.VistaPrincipale;
import javax.swing.SwingUtilities;
import lombok.Getter;

@Getter

public class Applicazione {
    
    private static Applicazione singleton = new Applicazione();
    
    public static Applicazione getInstance() {
        return singleton;
    }

    private Applicazione() {
    }
    
    private Modello modello;
    private ControlloPrincipale controlloPrincipale;
    private ControlloDettagliAccesso controlloDettagliAccesso;
    private ControlloFrame controlloFrame;
    private IDAOArchivio daoArchivio;
    private VistaPrincipale vistaPrincipale;
    private VistaDettagliAccessi vistaDettagliAccessi;
    private VistaDettagliMesiFrequenti vistaDettagliMesiFrequenti;
    private Frame frame;
    
    public void inizializza() {
        this.modello = new Modello();
        this.controlloPrincipale = new ControlloPrincipale();
        this.controlloDettagliAccesso = new ControlloDettagliAccesso();
        this.controlloFrame = new ControlloFrame();
        this.daoArchivio = new DAOArchivioMock();
        this.vistaPrincipale = new VistaPrincipale();
        this.vistaDettagliAccessi = new VistaDettagliAccessi(frame, true);
        this.vistaDettagliMesiFrequenti = new VistaDettagliMesiFrequenti(frame, true);
        this.frame = new Frame();
        this.vistaPrincipale.inizializza();
        this.vistaDettagliAccessi.inizializza();
        this.vistaDettagliMesiFrequenti.inizializza();
        this.frame.inizializza();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Applicazione.getInstance().inizializza();
            }
        });
    }
}
