/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package factories;

import dao.PalabraDAO;
import dao.WebSiteDAO;

/**
 *
 * @author Administrador
 */
public class PostgreSQLDAOFactory extends DAOFactory{

    @Override
    public WebSiteDAO getWebSiteDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PalabraDAO getPalabraDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}