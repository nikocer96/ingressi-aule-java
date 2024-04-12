package it.unibas.ceronen.vista;

import it.unibas.ceronen.Applicazione;
import it.unibas.ceronen.modello.Costanti;
import it.unibas.ceronen.modello.DatiMesiFrequenti;
import java.util.List;

public class VistaDettagliMesiFrequenti extends javax.swing.JDialog {

    public VistaDettagliMesiFrequenti(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    public void inizializza() {
        initComponents();
        this.tabellaMesiFrequenti.setModel(new ModelloTabellaMesiFrequenti());
        this.pack();
    }
    
    public void visualizza() {
        aggiornaDati();
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
    }
    
    public void aggiornaDati() {
        List<DatiMesiFrequenti> listaMesiFrequenti = (List<DatiMesiFrequenti>) Applicazione.getInstance().getModello().getBean(Costanti.LISTA_MESI_FREQUENTI);
        ModelloTabellaMesiFrequenti modelloMesiFrequenti = (ModelloTabellaMesiFrequenti) this.tabellaMesiFrequenti.getModel();
        modelloMesiFrequenti.setListaMesiFrequenti(listaMesiFrequenti);
        modelloMesiFrequenti.aggiornaTabella();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaMesiFrequenti = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabellaMesiFrequenti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabellaMesiFrequenti);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabellaMesiFrequenti;
    // End of variables declaration//GEN-END:variables
}
