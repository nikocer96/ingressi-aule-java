package it.unibas.ceronen.vista;

import it.unibas.ceronen.modello.Aula;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ModelloTabellaAule extends AbstractTableModel {
    
    private List<Aula> listaAule = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.listaAule.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aula aula = this.listaAule.get(rowIndex);
        if (columnIndex == 0) {
            return aula.getCodiceUnivoco();
        }
        if (columnIndex == 1) {
            return aula.getNomeAula();
        }
        if (columnIndex == 2) {
            return aula.getPiano();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Codice Univoco";
        }
        if (column == 1) {
            return "Nome Aula";
        }
        if (column == 2) {
            return "Piano";
        }
        return "";
    }
    
    public void aggiornaTabella() {
        this.fireTableDataChanged();
    }
}
