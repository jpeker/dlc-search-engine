package factories;

import mysql.MySqlPalabraDAO;
import mysql.MySqlWebSiteDAO;
import dao.PalabraDAO;
import dao.WebSiteDAO;

/**
 * Representa una fábrica concreta de DAOs
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public WebSiteDAO getWebSiteDAO() {
        return new MySqlWebSiteDAO();
    }

    @Override
    public PalabraDAO getPalabraDAO() {
         return new MySqlPalabraDAO();
    }

}