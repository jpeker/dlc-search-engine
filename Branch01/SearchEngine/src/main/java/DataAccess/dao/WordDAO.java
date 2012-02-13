package dataaccess.dao;

import com.utn.searchengine.Word;
import java.util.ArrayList;
import java.util.Map;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo Word.
 * 
 * @author Altamirano Liberal Peker
 */
public interface WordDAO {
    
    /**
     * Graba un Word en la base de datos. Si el Word ya existia la 
     * actualiza y si no existia la inserta en la base de datos.
     * @param word la palabra a grabar
     * @return true si la operacion se pudo realizar exitosamente, false en caso contrario 
     */
    boolean saveWord(Word word);
    
    /**
     * Elimina la Word de la base de datos
     * @param word la palabra a eliminar
     * @return true si el word se pudo eliminar exitosamente, false en caso contrario
     */
    boolean deleteWord(Word word);
    
    /**
     * Obtiene la Word correspondiente de la base de datos.
     * @param word la palabra a obtener
     * @return el word que se desea obtener, null en caso que la palabra no exista
     */    
    Word getWord(Word word);
 
     /**
     * Obtiene el identificador del word en la base de datos
     * @param word el Word de la cual se desea saber su ID
     * @return el id del word
     */
     public  Map<String, Word> getVocabulary();
}