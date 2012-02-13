package dataaccess.dao;

import com.utn.searchengine.Document;
import com.utn.searchengine.Word;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo PostList.
 * 
 * @author Altamirado Liberal Peker
 */
public  interface PostListDAO {
    public ArrayList<Document> 
            obtenerDocumentoCandidatos(Collection<String> c  );
     public int getTF(Word word,Document document);
      public boolean grabarPostList(Word word,Document document,int fr);
       public int getNrWord(Word word);
       public boolean isContains(String palabra);
       public ArrayList<Word> getWordsDocument(Document document); 
}
