package dataaccess.factories;

import dataaccess.dao.DocumentDAO;
import dataaccess.dao.PostListDAO;
import dataaccess.dao.WordDAO;
import dataaccess.postgresql.PostgreSQLDocumentDAO;
import dataaccess.postgresql.PostgreSQLPostListDAO;
import dataaccess.postgresql.PostgreSQLWordDAO;

/**
 * Factory concreta para manejar DAOs Postgres.
 * @author Altamirano Liberal Peker
 */
public class PostgreSQLDAOFactory extends DAOFactory {

    @Override
    public DocumentDAO getDocumentDAO() {
        return new PostgreSQLDocumentDAO();
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
