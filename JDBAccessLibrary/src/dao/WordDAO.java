package dao;

import beans.Word;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo Word.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public interface WordDAO {
    
    /**
     * Graba una palabra en la base de datos. Si la palabra ya existia la 
     * actualiza y si no existia la inserta en la base de datos.
     * @param palabra la palabra a grabar
     * @return true si la operacion se pudo realizar exitosamente, false en caso contrario 
     */
    boolean grabarPalabra(Word palabra);
    
    /**
     * Elimina la palabra de la base de datos
     * @param palabra la palabra a eliminar
     * @return true si la palabra se pudo eliminar exitosamente, false en caso contrario
     */
    boolean eliminarPalabra(Word palabra);
    
    /**
     * Obtiene la palabra correspondiente de la base de datos.
     * @param palabra la palabra a obtener
     * @return la palabra que se desea obtener, null en caso que la palabra no exista
     */    
    Word obtenerPalabra(Word palabra);
 
     /**
     * Obtiene el identificador de la palabra en la base de datos
     * @param palabra la palabra de la cual se desea saber su ID
     * @return el id de la palabra
     */
     public ArrayList<Word> obtenerVocabulary();
}