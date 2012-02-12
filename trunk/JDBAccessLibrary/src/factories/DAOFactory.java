package factories;

import dao.DocumentDAO;
import dao.PostListDAO;
import dao.WordDAO;

/**
 * Clase abstracta que representa una fábrica de DAOs.
 * 
 * @author Altamirano Liberal Peker
 */
public abstract class DAOFactory {
    
  //Definimos los tipos de factories
  public static final int POSTGRE = 1;


  //Definimos la factory activa (tipo de persistencia a utilizar) 
  
  public static final int activeFactoryPostgre = POSTGRE;

  public abstract WordDAO getWordDAO();
  public abstract DocumentDAO getDocumentDAO();
  public abstract PostListDAO getPostListDAO();

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