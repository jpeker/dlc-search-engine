package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**Clase que tiene el driver sql para la coneccion a Postgres
 *
 * @author Altamirado Liberal Peker
 */
public class PostgreDBManager {
 /**
     * URL de la base de datos
     */
    private static final String DBURL="jdbc:postgresql://localhost:5432/SearchEngineDB";

    /**
     * Driver para acceder a la Base de Datos de Postgre
     */
    private static final String DRIVER="org.postgresql.Driver";

    /**
     * Usuario de la base de datos
     */
    private static String usuario="postgres";

    /**
     * Contraseña de la base de datos
     */
    private static String contraseña="dlc2012";

    /**
     * Representa la conexión a la base de datos
     */
    private static Connection con = null;

    /**
     * @return la única instancia válida de DBManager
     */
    public static synchronized Connection getConnection()
    {
        try {
            if(con == null || con.isClosed())
            {
                crearConnection();
            }
        } catch (SQLException ex) {
        }
        return con;
    }

    /**
     * Crea una nueva conexión a la base de datos
     */
    private static void crearConnection()
    {
        try {
            Class.forName(DRIVER);
        }
          catch (ClassNotFoundException ex)
        {
            System.exit(2);
        }
        try{
            con = DriverManager.getConnection(DBURL, usuario, contraseña);
        }
        catch (SQLException ex)
        {
        }
    }
}