package it.unibas.ceronen.vista;

import it.unibas.ceronen.modello.Accesso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ModelloTabellaAccessi extends AbstractTableModel {
    
    private List<Accesso> listaAccessi = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.listaAccessi.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Accesso accesso = this.listaAccessi.get(rowIndex);
        if (columnIndex == 0) {
            return accesso.getData();
        }
        if (columnIndex == 1) {
            return accesso.getMatricola();
        }
        if (columnIndex == 2) {
            return accesso.getPermanenza();
        }
        if (columnIndex == 3) {
            return accesso.getMotivazione();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Data";
        }
        if (column == 1) {
            return "Matricola";
        }
        if (column == 2) {
            return "Permanenza";
        }
        if (column == 3) {
            return "Motivazione";
        }
        return "";
    }
    
    public void aggiornaTabella() {
        this.fireTableDataChanged();
    }
}
