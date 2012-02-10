package engine.searcher;

import dal.beans.Palabra;
import dal.beans.WebSite;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Representa un resultado de búsqueda
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class SearchResult {

    /**
     * WebSite resultado
     */
    private WebSite website;
    /**
     * Palabras asociadas a la query que estan relacionadas con esta website
     * y frecuencia de cada una de ellas para esta website.
     */
    private HashMap<Long, NRLevel> nivelesNR;
    private LinkedList<Palabra> palabras;

    /**
     * Constructor para crear una nuevo resultado de búsqueda
     * @param website website asociada
     */
    public SearchResult(WebSite website) {
        this.website = website;
        this.nivelesNR = new HashMap<Long, NRLevel>();
        this.palabras = new LinkedList<Palabra>();
    }
    
    /**
     * Agrega una palabra al HashMap de palabras asociadas a la website
     * @param pal palabra
     * @param fr frecuencia
     * @return true si la agrego, false en caso contrario
     */
    public boolean addPalabra(Palabra pal, Long fr)
    {
        if(!this.palabras.contains(pal) && fr > 0)
        {
            this.palabras.add(pal);
            
            long nr =  pal.getNr();
            
            NRLevel nivelNR = nivelesNR.get(new Long(nr));
            
            if(nivelNR==null)
            {
                nivelNR = new NRLevel(nr);
                nivelesNR.put(new Long(nr), nivelNR);
            }
            
            nivelNR.addPalabra(pal, fr, this.presenteEnLinkDeWebSite(pal), this.presenteEnTituloDeWebSite(pal));
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene la website de este resultado de búsqueda
     * @return la website asociada a este resultado de búsqueda 
     */
    public WebSite getWebsite() {
        return website;
    }  
    
   
    /**
     * Determina si dos objetos SearchResult son iguales. Esta basado en la website
     * asociada.
     * @param obj objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SearchResult other = (SearchResult) obj;
        if (this.website != other.website && (this.website == null || !this.website.equals(other.website))) {
            return false;
        }
        return true;
    }

    /**
     * Determina el hashcode del SearchResult. Basado en el hashcode de la website
     * asociada.
     * @return hashcode de este SearchResult
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.website != null ? this.website.hashCode() : 0);
        return hash;
    }
    
    private boolean presenteEnLinkDeWebSite(Palabra p)
    {
       return this.website.getUrl().toUpperCase().contains(p.getPalabra());
    }
    
    private boolean presenteEnTituloDeWebSite(Palabra p)
    {
        return this.website.getTitulo().contains(p.getPalabra());
    }

    public HashMap<Long, NRLevel> getNivelesNR() {
        return nivelesNR;
    }   
}