package dao;

import beans.Palabra;
import java.util.LinkedList;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo Palabra.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public interface PalabraDAO {
    
    /**
     * Graba una palabra en la base de datos. Si la palabra ya existia la 
     * actualiza y si no existia la inserta en la base de datos.
     * @param palabra la palabra a grabar
     * @return true si la operacion se pudo realizar exitosamente, false en caso contrario 
     */
    boolean grabarPalabra(Palabra palabra);
    
    /**
     * Inserta la palabra en la base de datos.
     * @param palabra la palabra a insertar
     * @return true si se pudo insertar exitosamente, false en caso contrario
     */    
    boolean insertarPalabra(Palabra palabra);
    
    /**
     * Actualiza la palabra en la base de datos.
     * @param palabra la palabra a actualizar
     * @return true si se pudo actualizar exitosamente, false en caso contrario
     */
    boolean actualizarPalabra(Palabra palabra);
    
    /**
     * Elimina la palabra de la base de datos
     * @param palabra la palabra a eliminar
     * @return true si la palabra se pudo eliminar exitosamente, false en caso contrario
     */
    boolean eliminarPalabra(Palabra palabra);
    
    /**
     * Obtiene la palabra correspondiente de la base de datos.
     * @param palabra la palabra a obtener
     * @return la palabra que se desea obtener, null en caso que la palabra no exista
     */    
    Palabra obtenerPalabra(Palabra palabra);
    
 
    /**
     * Calcula el NR de la palabra.
     * @param palabra la palabra de la cual se desea obtener su NR.
     * @return el NR de la palabra.
     */
    long calcularNrDePalabra(Palabra palabra);
    
    /**
     * Obtiene el identificador de la palabra en la base de datos
     * @param palabra la palabra de la cual se desea saber su ID
     * @return el id de la palabra
     */
    int obtenerId(Palabra palabra);
    
    /**
     * Obtiene las stop words para el porcentaje indicado
     * @param percentage el porcentaje de websites en el que tiene que estar
     * una palabra para ser considerada una stopWord.
     * @return lista de las stop words.
     */
    LinkedList<Palabra> getStopWords(float percentage);
}