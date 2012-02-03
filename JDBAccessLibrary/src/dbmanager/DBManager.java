package dbmanager;

///////// Modificar con nuestro dbms


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import notificador.Notificador;
//import notificador.ProcesadorDeNotificacion.Evento;

/**
 * Clase que encapsula el acceso a la base de datos 
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class DBManager {
    
    /**
     * URL de la base de datos
     */
    private static final String DBURL="jdbc:mysql://sgic.dyndns.info:3306/dlc";
    
    /**
     * Driver para acceder a la Base de Datos de MySQL
     */
    private static final String DRIVER="com.mysql.jdbc.Driver";
    
    /**
     * Usuario de la base de datos
     */
    private static String usuario="dlc";
    
    /**
     * Contraseña de la base de datos
     */
    private static String contraseña="dlcdlc";
    
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