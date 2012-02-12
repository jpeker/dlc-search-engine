
package dataaccess.postgresql;

import com.utn.searchengine.Document;
import dataaccess.dao.DocumentDAO;
import dataaccess.dbmanager.PostgreDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Altamirano Peker Liberal
 */
public class PostgreSQLDocumentDAO implements DocumentDAO  {

    /**
     * Graba el Document en la base de datos. Si no existia la inserta y si existia
     * la actualiza
     * @param document Document a grabar
     * @return true si se pudo grabar con éxito, false en caso contrario
     */
    public boolean grabarWebSite(Document document) {
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="SELECT fn_save_page(?,?);";
                st = con.prepareStatement(query);
                st.setString(1, document.getLocation());
                st.setDouble(2, document.getModule());
                st.executeQuery();
                st.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
           return false;}
    }
    /*
     * obtiene la cantidad total de documento
     */
   public int obtenerCantidadDocument()
   {
    int cant;
    PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="SELECT COUNT(*) FROM Page p;";
                st = con.prepareStatement(query);
                ResultSet results= st.executeQuery();
                results.next();
                cant = results.getInt(1);
                st.close();
                
            }
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
            cant=-1;
        }
   
     return cant;
   
   }
    /**
     * Obtiene el Document desde la base de datos que se corresponde a la
     * Document de busqueda dada.
     * @param document Document que se desea buscar en la base de datos
     * @return el Document de de la base de datos, null en caso que no exista o se
     * produzca algun error.
     */
    public Document obtenerDocument(Document website) {
        Document ret = null;
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
              String query="select id_Url ,url_Name,Modulo from Page where url_Name = ?";
                st = con.prepareStatement(query);
                st.setString(1, website.getLocation());
                ResultSet results=st.executeQuery();
                if(results.next())
                {
                    ret=new Document(results.getString("url_Name"),results.getString("url_Name"),"");
                    ret.setModule(results.getDouble("Modulo"));
                }
                results.close();
                st.close();
            
        } catch (SQLException ex) {}
        return ret;
    }
  }
