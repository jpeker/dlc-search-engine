/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresql;
import dbmanager.*;
import dao.*;
//import beans.WebSite;
import beans.Document;
import beans.Word;
import java.util.LinkedList;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
/**
 *
 * @author Julian
 */
public class PostgreSQLPostListDAO {
    
      public static void main(String[] args) {
        //Word w0= new Word("Fede", 8, 2);
        
       PostgreSQLPostListDAO paldao= new  PostgreSQLPostListDAO();
        //paldao.grabarPalabra(w0);
        Word w1= new Word("casa", 8, 2);
       Word w2= new Word("cara", 8, 2);
        Word w3= new Word("Julián", 8, 2); 
        ArrayList<Word> worda= new ArrayList();
        worda.add(w1);
        worda.add(w2);
        worda.add(w3);
        Iterator i=paldao.obtenerDocumentoCandidatos(worda).iterator();
        while(i.hasNext())
        {
            Document doc =(Document)i.next();
            System.out.println(doc.toString());
        }
        // TODO code application logic here
    }
    public ArrayList<Document> obtenerDocumentoCandidatos( ArrayList<Word> words )
    {
     ArrayList<Document> ret=new ArrayList();
     ArrayList<String> cadena= new ArrayList();
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
                String query="select distinct d.url_Name from postlist p inner join page d on p.id_Url = d.id_Url inner join word w on p.id_Word = w.id_Word  where w.name_Word in ("+cad.toString()+")";
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
    
}
