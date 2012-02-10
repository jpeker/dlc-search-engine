package com.utn.searchengine;

//import dal.beans.Palabra;
//import dal.beans.WebSite;
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
    private Document document;
    /**
     * Palabras asociadas a la query que estan relacionadas con esta website
     * y frecuencia de cada una de ellas para esta website.
     */
    private HashMap<Long, NRLevel> nrLevels;
    private LinkedList<Word> words;

    /**
     * Constructor para crear una nuevo resultado de búsqueda
     * @param website website asociada
     */
    public SearchResult(Document document) {
        this.document = document;
        this.nrLevels = new HashMap<Long, NRLevel>();
        this.words = new LinkedList<Word>();
    }
    
    /**
     * Agrega una palabra al HashMap de palabras asociadas a la website
     * @param pal palabra
     * @param fr frecuencia
     * @return true si la agrego, false en caso contrario
     */
    public boolean addPalabra(Word currentWord, Long fr)
    {
        if(!this.words.contains(currentWord) && fr > 0)
        {
            this.words.add(currentWord);
            
            long nr =  currentWord.getNr();
            
            NRLevel nivelNR = nrLevels.get(new Long(nr));
            
            if(nivelNR==null)
            {
                nivelNR = new NRLevel(nr);
                nrLevels.put(new Long(nr), nivelNR);
            }
            
            nivelNR.addWord(currentWord, fr);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene la website de este resultado de búsqueda
     * @return la website asociada a este resultado de búsqueda 
     */
    public Document getDocument() {
        return document;
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
        if (this.document != other.document &&
                (this.document == null
                || !this.document.equals(other.document))) {
            return false;
        }
        return true;
    }

    /**
     * Determina el hashcode del SearchResult. Basado en el hashcode de la 
     * website asociada.
     * @return hashcode de este SearchResult
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.document
                != null ? this.document.hashCode() : 0);
        return hash;
    }
    

    public HashMap<Long, NRLevel> getNivelesNR() {
        return nrLevels;
    }   
}