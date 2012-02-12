/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package postgresql;

import dbmanager.*;
import dao.PalabraDAO;
import beans.Word;
import java.util.LinkedList;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Administrador
 */
public class PostgreSQLPalabraDAO implements PalabraDAO{

    /**
     * Graba una palabra en la base de datos. Si la misma ya existia la actualiza
     * y sino la inserta.
     * @param palabra palabra que se desea grabar
     * @return true si se pudo realizar exitosamente la operación, false en caso
     * contrario.
     */
        ////
    ////
    ///MAIN
    public static void main(String[] args) {
        //Word w0= new Word("Fede", 8, 2);
        
        PostgreSQLPalabraDAO paldao= new  PostgreSQLPalabraDAO();
        //paldao.grabarPalabra(w0);
        Word w1= new Word("casa", 8, 2);
        paldao.eliminarPalabra(w1);
         Iterator peker = paldao.obtenerVocabulary().iterator();
         while(peker.hasNext())
         {Word a = (Word) peker.next();
             System.out.println(a.toString());
         }
         
        // TODO code application logic here
    }
    
    
    //Actualiza o graba una palabra
    public boolean grabarPalabra(Word word) {
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select fn_save_word(?,?,?);";
                st = con.prepareStatement(query);
                st.setString(1, word.getName());
                st.setInt(2, word.getNr());
                st.setInt(3, word.getMaxTF());
                st.executeQuery();
                st.close();
                return true;
            }
        } catch (SQLException ex) {
           return false;
            // Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener palabra: " +  palabra.getPalabra(), ex);
        }
    }

    /**
     * 
     * Obtiene la palabra de la base de datos en base al objeto de búsqueda pasado
     * por parámetro.
     *
     * @param palabra palabra a buscar
     * @return la palabra almacenada en la base de datos, null si la misma no existia
     * o existen problemas de acceso a la base.
     */
    
    ///Terminado
    public Word obtenerPalabra(Word palabra) {
        Word ret=null;
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select name_Word,nr,max_Tf from Word where name_Word = ?";
                st = con.prepareStatement(query);
                st.setString(1, palabra.getName());
                ResultSet results=st.executeQuery();
                if(results.next())
                {
                    ret=new Word(results.getString("name_Word"),results.getInt("nr"),results.getInt("max_Tf"));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
           // Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener palabra: " +  palabra.getPalabra(), ex);
        }
        return ret;
    }

    //Obtiene todo el vocabulary
    public ArrayList<Word> obtenerVocabulary() {
        ArrayList Vocabulary = new ArrayList<Word>();
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select name_Word,nr,max_Tf from Word";
                st = con.prepareStatement(query);
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                    Vocabulary.add( new Word(results.getString("name_Word"),results.getInt("nr"),results.getInt("max_Tf")));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
           // Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener palabra: " +  palabra.getPalabra(), ex);
        }
        return Vocabulary;
    }
    
    /**
     * Elimina la palabra de la base de datos
     * @param palabra palabra a eliminar
     * @return true si se pudo eliminar correctamente, false en caso contrario.
     */
    public boolean eliminarPalabra(Word palabra){
        boolean ret;
        PreparedStatement st;
        Connection con = PostgreDBManager.getConnection();
        synchronized(con)
        {
            try {
                String queryPalabra="select pr_deleteWord(?);";
                st = con.prepareStatement(queryPalabra);
                st.setString(1, palabra.getName());
                ResultSet rs = st.executeQuery();
                rs.next();
                System.out.println(""+rs.getBoolean(1));
                st.close();
                ret=true;
            }
            catch (SQLException ex) {
                 ret=false;
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
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select count(*) from word as p, postlist "
                        + "lp where p.idWord=lp.idWord and p.name=?";
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
            //Notificador.getInstancia().reportar
            //(Evento.CONDICION_DE_ERROR,"Error al obtener NR de palabra", ex);
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
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="Select idWord from palabra where name = ?";
                st = con.prepareStatement(query);
                st.setInt(1, palabra.hashCode());
                ResultSet results=st.executeQuery();
                if(results.first())
                {
                    ret=results.getInt("idWord");
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
                String query="insert into word (name,nr,maxTf) values (?,?,?)";
                st = con.prepareStatement(query);
                st.setString(1, palabra.getName());
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
            con = PostgreDBManager.getConnection();
            synchronized(con){
                String query="update word "
                        + "set nr = ? "
                        + "where name = ? ";
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

       //Arreglar aqui.
       PostgreSQLWebSiteDAO websitesDAO = new PostgreSQLWebSiteDAO();
       long cantWebSites = websitesDAO.getCantidadWebsitesIndexadas();
       long nrMax = (long) (cantWebSites * razonStopWords);
       PreparedStatement st;
       Connection con;
       try {
            con = MySqlDBManager.getConnection();
            synchronized(con)
            {
                String query="Select nr,word from word where nr >= ?";
                st = con.prepareStatement(query);
                st.setLong(1, nrMax);
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                    stopWords.add(new Word(results.getString("name"),results.getInt("nr"),results.getInt("maxTf")));
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
