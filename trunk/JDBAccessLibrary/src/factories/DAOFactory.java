package factories;

import dao.WordDAO;
import dao.DocumentDAO;

/**
 * Clase abstracta que representa una fábrica de DAOs.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public abstract class DAOFactory {
    
  //Definimos los tipos de factories
  public static final int POSTGRE = 1;


  //Definimos la factory activa (tipo de persistencia a utilizar) 
  
  public static final int activeFactoryPostgre = POSTGRE;

  public abstract DocumentDAO getDocumentDAO();
  public abstract WordDAO getWordDAO();

  public static DAOFactory getDAOFactory(int factory) {
  
    switch (factory) {
       case POSTGRE:
          return new PostgreSQLDAOFactory();
      default: 
          return null;
    }
  }
  
  /**
   * Devuelve la factory activa
   * @return la factory activa (según el tipo de persistencia a utilizar)
   */
  public static DAOFactory getActiveDAOFactory()
  {
      return getDAOFactory(activeFactoryPostgre);
  } 
}