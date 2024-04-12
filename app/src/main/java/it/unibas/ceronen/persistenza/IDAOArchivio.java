package it.unibas.ceronen.persistenza;

import it.unibas.ceronen.modello.Archivio;

public interface IDAOArchivio {

    Archivio carica(String nomeFile) throws DAOException;
    
}
