/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package factories;

import dao.WordDAO;
import dao.DocumentDAO;
import postgresql.PostgreSQLDocumentDAO;
import postgresql.PostgreSQLWordDAO;
import dao.PostListDAO;

/**
 *
 * @author Administrador
 */
public class PostgreSQLDAOFactory extends DAOFactory{

    @Override
    public DocumentDAO getDocumentDAO() {
       return new  PostgreSQLDocumentDAO();
    }

    @Override
    public WordDAO getWordDAO() {
        return new PostgreSQLWordDAO();
    }

}
