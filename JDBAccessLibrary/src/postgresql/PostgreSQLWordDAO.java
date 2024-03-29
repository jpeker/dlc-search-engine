
package postgresql;

import beans.Word;
import dao.WordDAO;
import dbmanager.PostgreDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altamirano Peker Liberal
 */
public class PostgreSQLWordDAO implements WordDAO{

    /**
     * Graba un Word en la base de datos. Si la misma ya existia la actualiza
     * y sino la inserta.
     * @param word Word que se desea grabar
     * @return true si se pudo realizar exitosamente la operación, false en caso
     * contrario.
     */
    //Actualiza o graba una palabra
    public boolean saveWord(Word word) {
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
     * Obtiene la word de la base de datos en base al objeto de búsqueda pasado
     * por parámetro.
     *
     * @param word Word a buscar
     * @return la word almacenada en la base de datos, null si la misma no existia
     * o existen problemas de acceso a la base.
     */
    
    ///Terminado
    public Word getWord(Word palabra) {
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
        }
        return ret;
    }

    //Obtiene todo el vocabulary
    public ArrayList<Word> getVocabulary() {
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
        }
        return Vocabulary;
    }
    
    /**
     * Elimina la word de la base de datos
     * @param word Word a eliminar
     * @return true si se pudo eliminar correctamente, false en caso contrario.
     */
    public boolean deleteWord(Word palabra){
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
