package dao;

import beans.Document;
import beans.Word;
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
}
