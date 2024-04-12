package it.unibas.ceronen.vista;

import it.unibas.ceronen.modello.DatiMesiFrequenti;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ModelloTabellaMesiFrequenti extends AbstractTableModel {
    
    private List<DatiMesiFrequenti> listaMesiFrequenti = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.listaMesiFrequenti.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DatiMesiFrequenti datiMesiFrequenti = this.listaMesiFrequenti.get(rowIndex);
        if (columnIndex == 0) {
            return datiMesiFrequenti.getMese();
        }
        if (columnIndex == 1) {
            return datiMesiFrequenti.getTipologia();
        }
        if (columnIndex == 3) {
            return datiMesiFrequenti.getTempoTotale();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Mese";
        }
        if (column == 1) {
            return "Tipologia";
        }
        if (column == 2) {
            return "Tempo Totale";
        }
        return "";
    }
    
    public void aggiornaTabella() {
        this.fireTableDataChanged();
    }
}
