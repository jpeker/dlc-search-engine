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
    /**
     * 
     * @param c
     * @return Una lista de documentos candidatos
     */
    public List<Document> obtenerDocumentoCandidatos(Collection<String> c);
    /**
     * 
     * @param word: una Palabra
     * @param document: un Documento
     * @return La cantidad de veces que una palabra aparece en
     * un documento paricular
     */
    public int getTF(Word word, Document document);
    /**
     * Graba los elementosde la lista de posteo
     * @param word: Una Palabra
     * @param document: Un Documento
     * @param fr: La frecuencia de la palabra en el documento.
     * @return true si se pudo grabar bien, false si el 
     * elemento ya existe y se actualizo.
     */
    public boolean grabarPostList(Word word, Document document, int fr);
  
    /**
     * 
     * @param palabra: Una cadena que representa el texto de una palabra
     * @return: true si esa cadena esta asociada a una lista de posteo.
     */
    public boolean Contains(String palabra);
    /**
     * 
     * @param document: Un Documento
     * @return Una lista de palabras que pertenecen al documento
     * seleccionado
     */
    public List<Word> getWordsDocument(Document document);
    /**
     * Los documentos recuperados que se traian como candidatos eran demasiados
     * y muchos no resultaban muy significativos, por lo que se creo este
     * metodo para recuperar una cantidad mas precisa de documentos candidatos
     * @param c: Una coleccion de terminos
     * @return Los documentos candidatos filtrados.
     */
    public List<DocumentResults> getFilteredCandidates(Collection<String> c);
}
