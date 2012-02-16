package dataaccess.postgresql;

import com.utn.searchengine.Word;
import dataaccess.dao.WordDAO;
import dataaccess.dbmanager.PostgreDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Altamirano Peker Liberal
 */
public class PostgreSQLWordDAO implements WordDAO {

    /**
     * Graba un Word en la base de datos. Si la misma ya existia la actualiza
     * y sino la inserta.
     * @param word Word que se desea grabar
     * @return true si se pudo realizar exitosamente la operación, false en caso
     * contrario.
     */
    public boolean saveWord(Word word) {
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized (con) {
                String query = "select fn_save_word(?,?,?);";
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
    public Word getWord(Word palabra) {
        Word ret = null;
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized (con) {
                String query = "select name_Word,nr,max_Tf from Word where name_Word = ?";
                st = con.prepareStatement(query);
                st.setString(1, palabra.getName());
                ResultSet results = st.executeQuery();
                if (results.next()) {
                    ret = new Word(results.getString("name_Word"), results.getInt("nr"), results.getInt("max_Tf"));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
        }
        return ret;
    }

    public Map<String, Word> getVocabulary() {
        Map<String, Word> Vocabulary = new HashMap<String, Word>();
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized (con) {
                String query = "select name_Word,nr,max_Tf from Word";
                st = con.prepareStatement(query);
                ResultSet results = st.executeQuery();
                while (results.next()) {
                    Vocabulary.put(results.getString("name_Word"), new Word(results.getString("name_Word"), results.getInt("nr"), results.getInt("max_Tf")));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex.getMessage());
        }
        return Vocabulary;
    }

    public boolean deleteWord(Word palabra) {
        boolean ret;
        PreparedStatement st;
        Connection con = PostgreDBManager.getConnection();
        synchronized (con) {
            try {
                String queryPalabra = "select pr_deleteWord(?);";
                st = con.prepareStatement(queryPalabra);
                st.setString(1, palabra.getName());
                ResultSet rs = st.executeQuery();
                rs.next();
                System.out.println("" + rs.getBoolean(1));
                st.close();
                ret = true;
            } catch (SQLException ex) {
                ret = false;
            }
        }
        return ret;
    }

    public int getNrWord(Word word) {
        int nr = 0;
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized (con) {
                String query = "select nr from Word where name_Word = ?";
                st = con.prepareStatement(query);
                st.setString(1, word.getName());
                ResultSet results = st.executeQuery();
                while (results.next()) {
                    nr = results.getInt("nr");
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            nr = -1;
        }
        return nr;

    }
}
