package mysql;

import dbmanager.*;
import dao.PalabraDAO;
import beans.Word;
import java.util.LinkedList;
import java.sql.*;
import java.sql.ResultSet;
//import notificador.Notificador;
//import notificador.ProcesadorDeNotificacion.Evento;


/**
 * Esta clase implementa la interface de PalabraDAO para la base de datos MySQL.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class MySqlPalabraDAO implements PalabraDAO{
        
    /**
     * Graba una palabra en la base de datos. Si la misma ya existia la actualiza
     * y sino la inserta.
     * @param palabra palabra que se desea grabar
     * @return true si se pudo realizar exitosamente la operación, false en caso
     * contrario.
     */
    public boolean grabarPalabra(Word palabra) {
        boolean ret=false;
        if(this.obtenerId(palabra)!=-1)
        {
            ret=this.actualizarPalabra(palabra);
        }
        else
        {
            ret=this.insertarPalabra(palabra);
        }                
        return ret;       
    }
    
    /**
     * Obtiene la palabra de la base de datos en base al objeto de búsqueda pasado
     * por parámetro.
     * 
     * @param palabra palabra a buscar
     * @return la palabra almacenada en la base de datos, null si la misma no existia
     * o existen problemas de acceso a la base.
     */
    public Word obtenerPalabra(Word palabra) {
        Word ret=null;
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized(con)
            {
            
                String query="Select nr, palabra from palabra where hash = ?";
                st = con.prepareStatement(query);
                st.setInt(1, palabra.hashCode());
                ResultSet results=st.executeQuery();
                if(results.next())
                {
               //     ret=new Word(results.getString("palabra"),results.getLong("nr"));
                }
                results.close();
                st.close(); 
            }
        } catch (SQLException ex) {
           // Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener palabra: " +  palabra.getPalabra(), ex);
        }        
        return ret;     
    }  

    /**
     * Elimina la palabra de la base de datos
     * @param palabra palabra a eliminar
     * @return true si se pudo eliminar correctamente, false en caso contrario.
     */
    public boolean eliminarPalabra(Word palabra){ 
        boolean ret=false;
        PreparedStatement st;
        Connection con = MySqlDBManager.getConnection();
        synchronized(con)
        {
            //Si la palabra no existe no hay nada que borrar
            if(this.obtenerId(palabra)==-1){return ret;}
            try {
                
                //Para borrar la palabra necesito antes borrar su lista de posteo

                //Empezar transacción transaccion
                con.setAutoCommit(false);

                //Borrar la lista de posteo de la palabra
                String queryIdPalabra="Select lp.idPalabra from listaposteo as lp, palabra as p "
                                            + "where p.idPalabra = lp.idPalabra and p.hash = ?";

                st = con.prepareStatement(queryIdPalabra);
                st.setInt(1, palabra.hashCode());
                ResultSet idSet=st.executeQuery();
                int idPalabra=0;

                if(idSet.next())
                {
                    idPalabra=idSet.getInt("idPalabra");
                }
                else
                {
                    idPalabra=obtenerId(palabra);
                }             

                String queryListaPosteo="DELETE FROM listaposteo "
                        + "where idPalabra = ?";

                st = con.prepareStatement(queryListaPosteo);
                st.setInt(1, idPalabra);
                st.executeUpdate();

                //Borrar la palabra
                String queryPalabra="DELETE FROM palabra "
                        + "where idPalabra = ? ";

                st = con.prepareStatement(queryPalabra);
                st.setInt(1, idPalabra);
                st.executeUpdate();

                //Finaliza transacción
                con.commit();
 
                ret=true;
                
                idSet.close();
                st.close(); 
            } 
            catch (SQLException ex) {
                 //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al eliminar palabra", ex);
                if(con!=null)
                {
                   try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al hacer rollback al eliminar palabra", ex1);
                    }
                }
            }
            finally{
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al cambiar autoCommit property al final de eliminación de palabra", ex);
                }
            }
        }
        return ret;        
    }

    /**
     * Calcula el NR de una palabra
     * 
     * @param palabra palabra a la que se desea calcular su NR
     * @return NR que representa la cantidad de documentos en los que esta presente
     * la palabra, -1 si sucedio algun error.
     */
    public long calcularNrDePalabra(Word palabra){
        long ret=0;
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized(con)
            {
                String query="Select Count(*) from palabra as p, listaposteo lp where p.idPalabra=lp.idPalabra and p.hash=?";
                st = con.prepareStatement(query);
                st.setInt(1, palabra.hashCode());
                ResultSet results=st.executeQuery();
                if(results.next())
                {
                    ret=results.getLong(1);
                }   
                results.close();
                st.close(); 
            }          
        } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener NR de palabra", ex);
            ret=-1;
        }
        return ret;        
    }
    
    
    /**
     * Obtiene el ID de la palabra
     * @param palabra palabra de la cual se desea conocer su ID
     * @return el ID de la palabra, -1 si la palabra era nula o se produjo un error
     */
    public int obtenerId(Word palabra)
    {
        int ret=-1;
        if(palabra==null){return ret;}
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized(con)
            {
                String query="Select idPalabra from palabra where hash = ?";
                st = con.prepareStatement(query);
                st.setInt(1, palabra.hashCode());
                ResultSet results=st.executeQuery();
                if(results.first())
                {
                    ret=results.getInt("idPalabra");
                }
                results.close();
                st.close(); 
            }
        } 
        catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener ID de palabra", ex);
        }        
        return ret;
    }
    
    /**
     * Inserta una palabra en la base de datos
     * @param palabra palabra a insertar
     * @return true si se inserto correctamente, false en caso contrario o si sucedió
     * algún error.
     */
    public boolean insertarPalabra(Word palabra) {
        boolean ret=false;
        PreparedStatement st;
        Connection con;
        if(this.obtenerId(palabra)!=-1){return ret;}
        try {
            con = MySqlDBManager.getConnection();
            synchronized(con){
                String query="INSERT INTO palabra (palabra,nr,hash) VALUES (?,?,?)";
                st = con.prepareStatement(query);
              //  st.setString(1, palabra.getPalabra());
                st.setLong(2, palabra.getNr());
                st.setInt(3,palabra.hashCode());
                if(st.executeUpdate()==1)
                {
                    ret=true;
                }
                st.close(); 
            }
        } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al insertar palabra", ex);
        }
        return ret;
    }
    
    /**
     * Actualiza la palabra.
     * @param palabra actualiza la palabra pasada por parámetro. El único atributo
     * de una palabra actualizable es su NR.
     * @return true si se pudo hacer la actualización correctamente y false
     * en caso contrario.
     */
    public boolean actualizarPalabra(Word palabra) {
        boolean ret=false;
        PreparedStatement st;
        Connection con;
        if(this.obtenerId(palabra)==-1){return ret;}
        try {
            con = MySqlDBManager.getConnection();
            synchronized(con){
                String query="UPDATE palabra "
                        + "SET nr = ? "
                        + "where hash = ? ";
                st = con.prepareStatement(query);
                st.setLong(1, palabra.getNr());
                st.setInt(2, palabra.hashCode());
                if(st.executeUpdate()==1)
                {
                    ret=true;
                }
                st.close(); 
            }
        } 
        catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al actualizar palabra", ex);
        }
        return ret;        
    }
    
    /**
     * Obtiene una lista con los stopWords para la razonStopWords pasada por parámetro
     * @param razonStopWords numero entre 0 y 1 que indica a través de una razón
     * en cuántas páginas por sobre el total de páginas indexadas debe estar una palabra
     * para ser considerada stopWord. Por ejemplo 0.8 significa 80%
     * @return la lista de stopWords para la razon pasada por parámetro
     */
    public LinkedList<Word> getStopWords(float razonStopWords) {
       
       LinkedList<Word> stopWords = new LinkedList<Word>();
       
       //razonStopWords válidos: 0 - 1 :)
       if(razonStopWords<0 || razonStopWords>1){return stopWords;}
             
       MySqlWebSiteDAO websitesDAO = new MySqlWebSiteDAO();
       long cantWebSites = websitesDAO.getCantidadWebsitesIndexadas();
       long nrMax = (long) (cantWebSites * razonStopWords);
       PreparedStatement st;
       Connection con;
       try {
            con = MySqlDBManager.getConnection();
            synchronized(con)
            {
                String query="Select nr, palabra from palabra where nr >= ?";
                st = con.prepareStatement(query);
                st.setLong(1, nrMax);
                ResultSet results=st.executeQuery();
                while(results.next())
                {
             //       stopWords.add(new Word(results.getString("palabra"),results.getLong("nr")));
                }
                results.close();
                st.close();              
            }
         } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener las stopWords", ex);
        }        
        return stopWords;  
    }
}