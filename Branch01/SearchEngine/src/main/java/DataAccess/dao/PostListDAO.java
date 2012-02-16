package dataaccess.dao;

import com.utn.searchengine.Document;
import com.utn.searchengine.DocumentResults;
import com.utn.searchengine.Word;
import java.util.List;
import java.util.Collection;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo PostList.
 * 
 * @author Altamirado Liberal Peker
 */
public interface PostListDAO {

    public List<Document> obtenerDocumentoCandidatos(Collection<String> c);

    public int getTF(Word word, Document document);

    public boolean grabarPostList(Word word, Document document, int fr);

    public int getNrWord(Word word);

    public boolean isContains(String palabra);

    public List<Word> getWordsDocument(Document document);

    public List<DocumentResults> getFilteredCandidates(Collection<String> c);
}
