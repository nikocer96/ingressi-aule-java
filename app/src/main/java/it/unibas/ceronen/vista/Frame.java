package it.unibas.ceronen.vista;

import it.unibas.ceronen.Applicazione;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Frame extends javax.swing.JFrame {
    
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex) {
            
        }
    }

    public void inizializza() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setContentPane(new JScrollPane(Applicazione.getInstance().getVistaPrincipale()));
        this.setVisible(true);
        inizializzaAzioni();
        this.pack();
    }
    
    public void inizializzaAzioni() {
        this.menuEsci.setAction(Applicazione.getInstance().getControlloFrame().getAzioneEsci());
        this.menuCarica.setAction(Applicazione.getInstance().getControlloFrame().getAzioneCarica());
        this.menuVerifica.setAction(Applicazione.getInstance().getControlloFrame().getAzioneVerifica());
    }
    
    public void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Messaggio", JOptionPane.INFORMATION_MESSAGE);
    }
    public void mostraMessaggioErrore(String errore) {
        JOptionPane.showMessageDialog(this, errore, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
        menuCarica = new javax.swing.JMenuItem();
        menuVerifica = new javax.swing.JMenuItem();
        menuEsci = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        menuCarica.setText("jMenuItem1");
        jMenu1.add(menuCarica);

        menuVerifica.setText("jMenuItem2");
        jMenu1.add(menuVerifica);

        menuEsci.setText("jMenuItem3");
        jMenu1.add(menuEsci);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem menuCarica;
    private javax.swing.JMenuItem menuEsci;
    private javax.swing.JMenuItem menuVerifica;
    // End of variables declaration//GEN-END:variables
}
