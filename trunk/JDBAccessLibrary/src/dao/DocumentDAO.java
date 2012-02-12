package dao;

import beans.Document;

/**
 * Esta interface define las operaciones validas a realizarse en términos de
 * persistencia sobre un objeto de tipo Document.
 * 
 * @author Altamirado Liberal Peker
 */
public interface DocumentDAO {
    /**
     * Graba un Document en la base de datos. Si la Document ya existia la 
     * actualiza y si no existia la inserta en la base de datos.
     * @param Document la Document a grabar
     * @return true si la operacion se pudo realizar 
     * exitosamente, false en caso contrario 
     */
    boolean grabarWebSite(Document document);
    public int obtenerCantidadDocument();
    public Document obtenerDocument(Document website);  
}