/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * An implementation of  services that are usefull to count words
 * This are using local Structures and can dissapear once the structure
 * is migrated to database, but the interface probably will be the same.
 * @author altamirano,peker,liberal
 */
public class LocalWordCountManager implements WordCountManager{

    private Vocabulary vocabulary; 
    private PostList postList ;
    private DocumentManager documentManager;

    public LocalWordCountManager() {
         vocabulary = new Vocabulary();
         postList = new PostList();
         documentManager=new DocumentManager();
         vocabulary.loadVocabularyWords();
    }
        
    /**
     * Adds a document to all the structures.
     * @param document a Document
     */
    public void addDocument(Document document){
       // Map.Entry<String, String> entry= new Map.Entry<String, String>("antichrist", "666");
        //entry.setValue(document.getLocation());
        Map<String, Integer> pages  = WordCount.retrieveWordCount(document);
        vocabulary.addDocumentWords(pages);
        documentManager.addDocument(document);
        postList.addDocumentWords(pages, document.getLocation());
        
       // this.updatingModuleDocuments();
       // System.out.println("\nContenido de documentManager: "+documentManager.toString());
        //System.out.println("\nContenido de Vocabulary: "+vocabulary.toString());
        //System.out.println("\nContenido de PostList: "+postList.toString());
        
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
        //System.out.println("Determinating inverse frecuency of word \" "+word.getName()+"\"");
        double N = getTotalNumberOfDocuments();
        //System.out.println("Total of documents: "+N);
        double nr = numberOfDocumentsWhereWordAppears(word);//este metodo se va a poder rehusar desde getAllWordsAndLocations
        //System.out.println("nr: "+nr);
        if(nr ==0){
            return 0;
        }
        double cociente = N/nr;
        //System.out.println("Cociente: "+cociente);
        double result = Math.log10(cociente);
        //System.out.println("result: "+result);
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
         //Set<String> words = postList.getAllWords();
       ArrayList<Word> words = postList.getWordsDocument(document);
        double moduleResult =0;
        for(Word word: words){
            double termFrecuencyOnDocument = this.timesThatAWordRepeatsOnDocument(word, document);
            double invFrecuency = this.inverseFrecuency(word);
            double product = termFrecuencyOnDocument*invFrecuency;
            double potency = Math.pow(product, 2);
            moduleResult+= potency;
        }
        moduleResult =  Math.pow(moduleResult, 0.5);
        System.out.println("The document module is "+moduleResult);
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
        double tfri;
        if(document.getLocation().equalsIgnoreCase("query")){
            tfri = WordCount.getTF(word, document);
        }
        else{
            if( postList.totalTimesThatWordRepeatsOnDocument(word, document)==0){
            return 0;
        }
            tfri = this.timesThatAWordRepeatsOnDocument(word, document);
        }
        System.out.println("Determining the weight of word "+word.getName()+" on document "+document.getLocation()); 
        System.out.println("tfri is: "+tfri);
        double invFrec = this.inverseFrecuency(word);
        System.out.println("Inverse Frecuency is: "+ invFrec);
        double numerator = tfri*invFrec;
        System.out.println("Numerator is: "+numerator);
        double denominator;
        if(document.getModule()!=-1){
            denominator = document.getModule();
        }
        else{
             document.setModule(getDocumentModule(document));
            documentManager.setDocumentModule(document);
            denominator =document.getModule();
        }
        return numerator/denominator;

    }
    /**
     * A query can have different similitudes between documents. 
     * Here the list of similitudes between a query and all the documents
     * @param document the query
     * @return An Array of Similitude.
     */
    public Collection<Similitude> determinateBestSimilitude(Document document){
        Map <String, Integer> wordsOfQuery = WordCount.retrieveWordCount(document);
        Collection<String> c = wordsOfQuery.keySet();
        document.setModule( this.getQueryModule(wordsOfQuery));
        ArrayList<Similitude> sumilitudes = new ArrayList<Similitude>();
        Collection<Document> documents = postList.getCandidateDocuments(c);
        for(Document auxiliarDocument: documents){
           
          sumilitudes.add(this.determinateSimilitude(wordsOfQuery, auxiliarDocument, document));
        }
        sortSimilitude(sumilitudes);
        return sumilitudes;
       
    }
       //  ordeno Similitudes de documentos
   private void sortSimilitude (  ArrayList<Similitude> sumilitu)
    {
        Comparator<Similitude> comparator = new Compare () ;
    Collections.sort(sumilitu,  comparator);
   
    }
     //class Comparator que ordena en forma decreciente
    class Compare implements Comparator<Similitude> {
	@Override
		public int compare(Similitude lhs, Similitude rhs) {
  		if(rhs.getSimilitude()>=lhs.getSimilitude())
            return 1 ;
                else
                     return -1;
		}	
	}
    /**
     * The words of a query need to be taken as a document,  and the document
     * needs to have a module. Because htis module is temporal and wount be 
     * saved on the postList, a different method was needed to calculate it.
     * @param query
     * @return The module of the query
     */
   public double getQueryModule(Map<String, Integer> wordsOfQuery){
        //Map<String, Integer> words = WordCount.retrieveWordCount(query);
        double moduleResult =0;
        Iterator iterator = wordsOfQuery.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Word word = new Word (entry.getKey().toString());
            double termFrecuencyOnDocument =  (Double)entry.getValue() ;
            double invFrecuency = this.inverseFrecuency(word);
            double product = termFrecuencyOnDocument*invFrecuency;
            double potency = Math.pow(product, 2);
            moduleResult+= potency;
        }
        moduleResult =  Math.pow(moduleResult, 0.5);
        return moduleResult;
    }
    public Similitude determinateSimilitude(Map <String, Integer> wordsOfQuery, Document document1, Document document2){
        double coseno =0; 
        Iterator iterator = wordsOfQuery.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Word word = new Word(entry.getKey().toString());
            double weight1 = this.estimateWeight(word, document1);
            double weight2 = this.estimateWeight(word, document2);
            System.out.println("el peso de la palabra-----  >  "+word.getName());
            System.out.println("docmanger "+document1.getLocation()+" peso "+weight1);
            System.out.println("docquery "+document2.getLocation()+" peso "+weight2);
            coseno+= weight1*weight2;
         
        }
        return new Similitude(document1, document2, coseno);
    }
     
    public Map<String, Integer> filterQuery(Map <String, Integer> wordsOfQuery){
        Map<String, Integer>  filteredQuery= new HashMap<String, Integer> ();
        Iterator iterator = wordsOfQuery.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if(vocabulary.containsWord(entry.getKey().toString())){
                filteredQuery.put(entry.getKey().toString(), (Integer)entry.getValue());
            }
        }
        return filteredQuery;
        
    }
    
}
