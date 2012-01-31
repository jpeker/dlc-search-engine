/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of  services that are usefull to count words
 * This are using local Structures and can dissapear once the structure
 * is migrated to database, but the interface probably will be the same.
 * @author aaltamir
 */
public class LocalWordCountManager implements WordCountManager{

    private Vocabulary vocabulary = new Vocabulary();
    private PostList postList = new PostList();
    private Map<String, Integer> pages = new HashMap();
    private DocumentManager documentManager=new DocumentManager();
    public Map<String, Integer> getPages() {
        return pages;
    }
   

    
    /**
     * Adds a document to all the structures.
     * @param document a Document
     */
    public void addDocument(Document document){
       // Map.Entry<String, String> entry= new Map.Entry<String, String>("antichrist", "666");
        //entry.setValue(document.getLocation());
        pages = WordCount.retrieveWordCount(document);
        vocabulary.addDocumentWords(pages);
        postList.addDocumentWords(pages, document.getLocation());
        documentManager.addDocument(document);
        this.updatingModuleDocuments();
        System.out.println("\nContenido de documentManager: "+documentManager.toString());
        System.out.println("\nContenido de Vocabulary: "+vocabulary.toString());
        System.out.println("\nContenido de PostList: "+postList.toString());
        
    }
    //actualiza los modulos de los documento por que se modifican cuando se agregan nuevos documentos
    public void updatingModuleDocuments ()
    {
     Collection<Document> documentos= documentManager.getDocuments();
     Iterator i = documentos.iterator();
        while(i.hasNext())
        {
        Document docum =(Document) i.next();
        docum.setModule(getDocumentModule(docum));
          }
     documentManager.setDocuments(documentos);
   }
    /**
     * 
     * @return the total of documents that exist. 
     */
    public int getTotalNumberOfDocuments() {
        return documentManager.documentsSize();
    }
    /**
     * 
     * @param word: A Word
     * @param document: A Document
     * @return the times that the document contains the word.
     */
    public int timesThatAWordRepeatsOnDocument(Word word, Document document) {
       return postList.totalTimesThatWordRepeatsOnDocument(word, document);
    }
    /**
     * 
     * @param word: A Word
     * @return The number of documents that contain the Word at least once
     */
    public int numberOfDocumentsWhereWordAppears(Word word) {
       return postList.numberOfDocumentsWhereWordAppears(word);
    }
    /**
     * The inverse frecuency is a common operation on the Weigth calcule.
     * @param word: A Word
     * @return The Base 10 Logaritm of the difference between the total documents and the number
     * of documents where the word appears.
     */
    public double inverseFrecuency(Word word) {
        System.out.println("Determinating inverse frecuency of word \" "+word.getName()+"\"");
        double N = getTotalNumberOfDocuments();
        System.out.println("Total of documents: "+N);
        double nr = numberOfDocumentsWhereWordAppears(word);//este metodo se va a poder rehusar desde getAllWordsAndLocations
        System.out.println("nr: "+nr);
        double cociente = N/nr;
        System.out.println("Cociente: "+cociente);
        double result = Math.log10(cociente);
        System.out.println("result: "+result);
        return result;
    }
    /**
     * Each Document gots a module that can be considered as a position Vector.
     * The module of a document will depend of the total amount of words,
     * so, if new words are added to the vocabulary, all the document modules will
     * have to be recalculated.
     * @param document: A document
     * @return The module that represents de vector of the document.
     */
    public double getDocumentModule(Document document){
        Set<String> words = postList.getAllWords();
        Iterator iterator = words.iterator();
        double moduleResult =0;
        while(iterator.hasNext()){
            String wordName = (String)iterator.next();
            Word word = new Word(wordName);
            double termFrecuencyOnDocument = postList.totalTimesThatWordRepeatsOnDocument(word, document);
            double invFrecuency = this.inverseFrecuency(word);
            double product = termFrecuencyOnDocument*invFrecuency;
            double potency = Math.pow(product, 2);
            moduleResult+= potency;
        }
        moduleResult =  Math.pow(moduleResult, 0.5);
        return moduleResult;
    }

 
    /**
     * The Weight of a word is important to determinate wich words are more
     * usefull to perform a search and wich of them not. Each word can have 
     * different weight on different documents.
     * @param word: A Word
     * @param document: A Document.
     * @return The weight of the word on the Document.
     */
    public double estimateWeight(Word word, Document document){
        System.out.println("Determining the weight of word "+word.getName()+" on document "+document.getName());
        double tfri = this.numberOfDocumentsWhereWordAppears(word);
        System.out.println("tfri is: "+tfri);
        double invFrec = this.inverseFrecuency(word);
        System.out.println("Inverse Frecuency is: "+ invFrec);
        double numerator = tfri*invFrec;
        System.out.println("Numerator is: "+numerator);
        double denominator = this.getDocumentModule(document);
        System.out.println("The document module is: "+denominator);
        return numerator/denominator;
    }
    
    
}
