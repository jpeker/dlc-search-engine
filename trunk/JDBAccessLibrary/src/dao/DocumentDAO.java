package dao;

//import beans.WebSite;
import beans.Document;
import beans.Word;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo WebSite.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public interface DocumentDAO {
    /**
     * Graba una website en la base de datos. Si la website ya existia la 
     * actualiza y si no existia la inserta en la base de datos.
     * @param website la website a grabar
     * @return true si la operacion se pudo realizar exitosamente, false en caso contrario 
     */
    boolean grabarWebSite(Document document);
    
    /**
     * Inserta la website en la base de datos.
     * @param website la website a insertar
     * @return true si se pudo insertar exitosamente, false en caso contrario
     */    
    //boolean insertarWebSite(WebSite website);
    
    /**
     * Actualiza la website en la base de datos.
     * @param website la website a actualizar
     * @return true si se pudo actualizar exitosamente, false en caso contrario
     */
    //boolean actualizarWebSite(WebSite website);
    
    /**
     * Elimina la website de la base de datos
     * @param website la website a eliminar
     * @return true si la website se pudo eliminar exitosamente, false en caso contrario
     */
   // boolean eliminarWebSite(WebSite website);
    
    /**
     * Obtiene la website correspondiente de la base de datos.
     * @param website la website a obtener
     * @return la website que se desea obtener, null en caso que la website no exista
     */    
    Document obtenerWebSite(Document website);
    
     /**
     * Obtiene la website correspondiente de la base de datos.
     * @param idUrl id de la website
     * @return la website que se desea obtener, null en caso que la website no exista
     */ 
   // Document obtenerWebSite(int idUrl);
    
    /**
     * Obtiene la lista de palabras que contiene esta website
     * @param website la website de la cual se desea obtener sus palabras
     * @return la lista de palabras de la website, si la website no tiene palabras
     * se devuelve una lista vacia.
     */
  //  Hashtable<Word, Long> obtenerPalabrasDeWebSite(WebSite website);

    /**
     * Obtiene el identificador de la website en la base de datos
     * @param website la website de la cual se desea saber su ID
     * @return el id de la website
     */
  //  int obtenerId(WebSite website);
    
    /**
     * Retorna todas las websites marcadas como base
     * @return la lista de las websites base
     */
    //LinkedList<WebSite> obtenerWebSitesBase(int rowLimit);
    
    /**
     * Retorna aquellas websites que por su marca de tiempo y estado son analizables
     * @return lista con websites analizables
     */
    //LinkedList<WebSite> obtenerWebSitesAnalizables(int rowLimit);
    
    /**
     * Retorna la cantidad websites que han sido indexadas.
     * @return cantidad de webistes indexadas que contienen por lo menos una palabra
     */
    //long getCantidadWebsitesIndexadas();
    
    /**
     * Reporta las websites base descubiertas
     * @param websites las websites base a reportar 
     */
    //void reportarWebSitesBase(LinkedList<WebSite> websites);
    
    /**
     * Devuelve la cantidad de veces en la que aparece esta palabra en esta website
     * @param palabra
     * @return la cantidad de veces que aparece la palabra en la website
     */
    //long getFrecuenciaDePalabra(WebSite site, Word palabra);
    
    /**
     * Obtiene las websites para una palabra determinada
     * @param palabra la palabra cuyas websites se quiere conocer
     * @return un HashMap conteniendo las websites de la palabra junto con la frecuencia
     * de ocurrencia de la misma en cada una
     */
    //HashMap<WebSite, Long> getWebSitesDePalabra(Word palabra);
}