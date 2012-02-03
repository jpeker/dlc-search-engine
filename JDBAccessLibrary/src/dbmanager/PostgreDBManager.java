/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmanager;

import java.sql.*;

/**
 *
 * @author Administrador
 */
public class PostgreDBManager {
 /**
     * URL de la base de datos
     */
    private static final String DBURL="jdbc:postgresql://localhost:5432";

    /**
     * Driver para acceder a la Base de Datos de MySQL
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
     * Método del singleton que devuelve una única instancia de la clase
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
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al intentar realizar un getConnection()", ex);
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
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "No se pudo cargar: " + DRIVER, ex);
            System.exit(2);
        }
        try{
            con = DriverManager.getConnection(DBURL, usuario, contraseña);
        }
        catch (SQLException ex)
        {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Error al crear conexión a la base de datos", ex);
        }
    }
}
