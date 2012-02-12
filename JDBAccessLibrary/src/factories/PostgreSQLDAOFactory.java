package factories;

import dao.DocumentDAO;
import dao.PostListDAO;
import dao.WordDAO;
import postgresql.PostgreSQLDocumentDAO;
import postgresql.PostgreSQLPostListDAO;
import postgresql.PostgreSQLWordDAO;

/**
 * Factory concreta para manejar DAOs Postgres.
 * @author Altamirano Liberal Peker
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
    
    @Override
    public PostListDAO getPostListDAO() {
        return new PostgreSQLPostListDAO();
    }
    
}
