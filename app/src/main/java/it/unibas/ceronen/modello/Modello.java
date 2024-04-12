package it.unibas.ceronen.modello;

import java.util.HashMap;
import java.util.Map;

public class Modello {
    
    private Map<String, Object> beans = new HashMap<>();
    
    public void putBean(String chiave, Object valore) {
        this.beans.put(chiave, valore);
    }
    
    public Object getBean(String chiave) {
        return this.beans.get(chiave);
    }
    
    public void replace(String chiave, Object valore) {
        this.beans.replace(chiave, valore);
    }
}
