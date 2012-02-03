package factories;

import dao.PalabraDAO;
import dao.WebSiteDAO;

/**
 * Clase abstracta que representa una fábrica de DAOs.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public abstract class DAOFactory {
    
  //Definimos los tipos de factories
  public static final int MYSQL = 1;
  public static final int POSTGRE = 2;


  //Definimos la factory activa (tipo de persistencia a utilizar) 
  public static final int activeFactoryMySql = MYSQL;
  public static final int activeFactoryPostgre = POSTGRE;

  public abstract WebSiteDAO getWebSiteDAO();
  public abstract PalabraDAO getPalabraDAO();

  public static DAOFactory getDAOFactory(int factory) {
  
    switch (factory) {
      case MYSQL: 
          return new MySqlDAOFactory();
       case POSTGRE:
          return new PostgreSQLDAOFactory();
      default           : 
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