/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 *
 * @author altamirano,peker,liberal
 */
public interface WordCountManager {
    /**
     * N element of weight Formula 
     * @return the total number of documents or pages that the search engine gots
     */
    public int getTotalNumberOfDocuments();
    /**
     * tfr, i element of the formula
     * @param word: a Word
     * @param document: A document
     * @return the number of times that a word repeats on a specific document.
     */
    public int timesThatAWordRepeatsOnDocument(Word word, Document document);
    /**
     * nr element of the weight formula
     * @param word: A Word 
     * @return the number of documents where the word appears
     */
    public int numberOfDocumentsWhereWordAppears(Word word);
    
    /**
     * idfr element of the weight formula
     * @return el logaritmo del cociente entre N y nr.
     */
    public double inverseFrecuency(Word word);
    /**
     * 
     * @param filePath 
     */
    public void addDocument(Document document);
    /**
     * 
     * @return 
     */
   
    
}
