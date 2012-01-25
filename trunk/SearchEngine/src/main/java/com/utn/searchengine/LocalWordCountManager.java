/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author aaltamir
 */
public class LocalWordCountManager implements WordCountManager{

    private Vocabulary vocabulary = new Vocabulary();
    private PostList postList = new PostList();
    private Map<String, Integer> pages = new HashMap();
    private Map<String,Double> moduleValues;
    private DocumentManager documentManager=new DocumentManager();
    //@TODO:check moduleValues behavior  
    //private Map<String, Collection<String>> postList;
    public Map<String, Integer> getPages() {
        return pages;
    }
   

    
    
    public void addDocument(Document document){
        File file = new File(document.getLocation());
        pages = WordCount.retrieveWordCount(document.getLocation());
        vocabulary.addDocumentWords(pages);
        postList.addDocumentWords(pages, document.getLocation());
        documentManager.addDocument(document);
        this.getDocumentModule(document);
        
    }
    
    public int getTotalNumberOfDocuments() {
        return documentManager.documentsSize();
    }

    public int timesThatAWordRepeatsOnDocument(Word word, Document document) {
       return postList.totalTimesThatWordRepeatsOnDocument(null, null);
    }
    public int numberOfDocumentsWhereWordAppears(Word word) {
       return postList.numberOfDocumentsWhereWordAppears(word);
    }

    public double inverseFrecuency(Word word) {
        double N = getTotalNumberOfDocuments();
        double nr = numberOfDocumentsWhereWordAppears(word);//este metodo se va a poder rehusar desde getAllWordsAndLocations
        double cociente = N/nr;
        double result = Math.log10(cociente);
        return result;
    }
    
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

    public Map<String, Collection<String>> getAllWordsAndLocations() {
        Map<String, Collection<String>> wordsAndLocations = new HashMap<String, Collection<String>>();
        
        Iterator pagesIterator = pages.entrySet().iterator();
        while (pagesIterator.hasNext()) {
            Map.Entry e = (Map.Entry)pagesIterator.next();
            Map actualPage = (Map)e.getValue();
            Iterator specificPageIterator = actualPage.entrySet().iterator();
            while (specificPageIterator.hasNext()){
                Map.Entry e2 = (Map.Entry)specificPageIterator.next();
                if(!wordsAndLocations.containsKey(e2.getKey().toString())){
                    List<String> directions = new ArrayList<String>();
                    directions.add(e.getKey().toString());
                    wordsAndLocations.put(e2.getKey().toString(),directions);
                }
                else{
                    wordsAndLocations.get(e2.getKey().toString()) .add(e.getKey().toString());
                }
                    
            }
            
        }
        System.out.println("the list of words is : \n"+wordsAndLocations);
        return wordsAndLocations;
    }
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



   /**
     * 
     * double numberOfTimes= 0;
        Iterator pagesIterator = pages.entrySet().iterator();
        while (pagesIterator.hasNext()) {
            Map.Entry e = (Map.Entry)pagesIterator.next();
            String documentName = (String)e.getKey();
            int aux1 = this.timesThatAWordRepeatsOnDocument(word, documentName);
            double aux2 = this.inverseFrecuency(word);
            double product = aux1*aux2;
            double squareProduct = Math.pow(product, 2);
            numberOfTimes+=squareProduct;
        }
        return Math.pow(numberOfTimes, 0.5);
     */
    
    
}
