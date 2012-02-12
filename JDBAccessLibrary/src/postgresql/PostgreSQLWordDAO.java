/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package postgresql;

import dbmanager.*;
import dao.WordDAO;
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
public class PostgreSQLWordDAO implements WordDAO{

    /**
     * Graba una palabra en la base de datos. Si la misma ya existia la actualiza
     * y sino la inserta.
     * @param palabra palabra que se desea grabar
     * @return true si se pudo realizar exitosamente la operación, false en caso
     * contrario.
     */
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

}
