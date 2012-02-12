package dataaccess.dao;

import com.utn.searchengine.Document;
import com.utn.searchengine.Word;
import java.util.ArrayList;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo PostList.
 * 
 * @author Altamirado Liberal Peker
 */
public  interface PostListDAO {
    public ArrayList<Document> 
            obtenerDocumentoCandidatos( ArrayList<Word> words );
     public int getTF(Word word,Document document);
      public boolean grabarPostList(Word word,Document document,int fr);
       public int getNrWord(Word word);
}
