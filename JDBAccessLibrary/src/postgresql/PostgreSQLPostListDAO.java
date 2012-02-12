
package postgresql;
import beans.Document;
import beans.Word;
import dao.PostListDAO;
import dbmanager.PostgreDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Altamirano Peker Liberal
 */
public class PostgreSQLPostListDAO implements PostListDAO{
  
    public static void main(String[] args) {
   
        
       PostgreSQLPostListDAO paldao= new  PostgreSQLPostListDAO();
    
        Word w1= new Word("casa", 8, 2);
       Word w2= new Word("cara", 8, 2);
        Word w3= new Word("Julián", 8, 2); 
        Document docu = new Document ("doc","peker.com.ar/index","");
        
        System.out.println(  paldao.grabarPostList(w1, docu,45));
        // TODO code application logic here
    }
 
      /* Recupera los documentos candidatos de la busqueda
     *  @param ArrayList<Word> words
     * Return arraylist de los documentos candidatos
     */
    public ArrayList<Document> obtenerDocumentoCandidatos( ArrayList<Word> words )
    {
     ArrayList<Document> ret=new ArrayList();
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
                String query="select distinct d.url_Name from postlist p inner join page d on p.id_Url = d.id_Url inner join word w on p.id_Word = w.id_Word  where w.name_Word in ("+cad.toString()+");";
                st = con.prepareStatement(query);
                ResultSet results=st.executeQuery();
                while(results.next())
                {
                   ret.add(new Document("doc"+results.getRow(),results.getString(1),""));
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
}
