
package dataaccess.postgresql;
import com.utn.searchengine.Document;
import com.utn.searchengine.Word;
import dataaccess.dao.PostListDAO;
import dataaccess.dbmanager.PostgreDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 *
 * @author Altamirano Peker Liberal
 */
public class PostgreSQLPostListDAO implements PostListDAO{
      /* Recupera los documentos candidatos de la busqueda
     *  @param ArrayList<Word> words
     * Return arraylist de los documentos candidatos
     */
    public ArrayList<Document> obtenerDocumentoCandidatos( Collection<String> c   )
    {
     ArrayList<Document> ret=new ArrayList();
     ArrayList<Word> words=new ArrayList();
     for(String name : c)
     {
         words.add(new Word (name));
     }
     Iterator i = words.iterator();
     StringBuilder cad = new StringBuilder();
     while(i.hasNext())
        {
            Word word =(Word)i.next();
            cad.append("'");
            cad.append(word.getName());
            cad.append("'");
            if(i.hasNext())cad.append(",");
        }
    PreparedStatement st;
        Connection con;
      try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select distinct d.url_Name,d.Modulo from postlist p inner join page d on p.id_Url = d.id_Url inner join word w on p.id_Word = w.id_Word  where w.name_Word in ("+cad.toString()+");";
                st = con.prepareStatement(query);
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                   ret.add(new Document(results.getString(1),"",results.getDouble(2)));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            ret=null;
        }
    return ret;
    }
    /* Recupera el nr de una palabra
     * @param Word word
     * return el nr  y -1 en caso de que no pudo recuperarse
     */
    public int getNrWord(Word word)
    {
   int nr=0;
    PreparedStatement st;
        Connection con;
      try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select nr from Word where name_Word = ?";
                st = con.prepareStatement(query);
                st.setString(1,word.getName());
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                   nr=results.getInt("nr");
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            nr=-1;
        }
    return nr;
    
    }
    /* Recupera la cantidad de veces que aparece una palabra en un documento
     * @param Word word
     * @param Document document
     * return el tf y -1 en caso de que no pudo recuperarse
     */
    public int getTF(Word word,Document document)
    {
   int tf=0;
    PreparedStatement st;
        Connection con;
      try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select p.frequency from PostList p inner join page d on p.id_Url = d.id_Url inner join word w on p.id_Word = w.id_Word  where w.name_Word = ? and d.url_Name = ?";
                st = con.prepareStatement(query);
                st.setString(1,word.getName());
                st.setString(2,document.getLocation());
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                   tf=results.getInt(1);
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            System.out.println("exception en getTF"+ex.getMessage());
            tf=-1;
        }
    return tf;
    
    }
    /* graba un documento por palabra en el postlist indicandole la frecuencia
     * @param Word word
     * @param Document document
     * @param int fr es la frecuencia de la palbra en un documento
     * return true si se pudo grabar en la base de datos
     */
   public boolean grabarPostList(Word word,Document document,int fr)
   {
   
    PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="SELECT fn_Save_Postlist(?,?,?);";
                st = con.prepareStatement(query);
                st.setString(1, word.getName());
                st.setString(2, document.getLocation());
                st.setInt(3, fr);
                st.executeQuery();
                st.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
           return false;
          
        }
   
   }
    public boolean isContains(String palabra) {
        boolean ret=false;
        PreparedStatement st;
        Connection con;
        try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select  * from postlist p  inner join word w on p.id_Word = w.id_Word where w.name_Word = ?";
                st = con.prepareStatement(query);
                st.setString(1, palabra);
                ResultSet results=st.executeQuery();
                if(results.next())
                {
                   ret=true;
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {          
        }
        return ret;
    }
      public ArrayList<Word> getWordsDocument(Document document)
      {
      ArrayList<Word> ret=new ArrayList();
      PreparedStatement st;
        Connection con;
      try {
            con = PostgreDBManager.getConnection();
            synchronized(con)
            {
                String query="select w.name_Word, w.nr,w.max_Tf  from postlist p inner join page d on p.id_Url = d.id_Url inner join word w on p.id_Word = w.id_Word  where d.url_Name = ?";
                st = con.prepareStatement(query);
                   st.setString(1, document.getLocation());
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                   ret.add(new Word(results.getString(1),results.getInt(2),results.getInt(3)));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            ret=null;
        }
    return ret;
      
      }
}
