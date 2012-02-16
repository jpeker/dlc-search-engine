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
import java.util.List;
import java.util.Map;

/**
 * An implementation of  services that are usefull to count words
 * This are using local Structures and can dissapear once the structure
 * is migrated to database, but the interface probably will be the same.
 * @author altamirano,peker,liberal
 */
public class LocalWordCountManager {

    private Vocabulary vocabulary;
    private PostList postList;
    private DocumentManager documentManager;
    private Map<String, Double> weigthQuery;

    public LocalWordCountManager() {
        vocabulary = new Vocabulary();
        postList = new PostList();
        documentManager = new DocumentManager();
        weigthQuery = new HashMap<String, Double>();
        vocabulary.loadVocabularyWords();
    }

    /**
     * Adds a document to all the structures.
     * @param document a Document
     */
    public void addDocument(Document document) {
        Map<String, Integer> pages = WordCount.retrieveWordCount(document);
        vocabulary.addDocumentWords(pages);
        documentManager.addDocument(document);
        postList.addDocumentWords(pages, document.getLocation());
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
        if (word == null) {
            return 0;
        }
        double N = getTotalNumberOfDocuments();
        double nr = word.getNr();
        if (nr == 0) {
            return 0;
        }
        double cociente = N / nr;
        double result = Math.log10(cociente);
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

    public double getDocumentModule(Document document, double invFrecuency){
       if(document ==null){
           return 0;
       }
       if(invFrecuency ==0){
           return 0;
       }   
        List<Word> words = postList.getWordsDocument(document);
       double moduleResult =0;
        for(Word word: words){
            double termFrecuencyOnDocument = word.getFrecuency();
            double product = termFrecuencyOnDocument * invFrecuency;
            double potency = Math.pow(product, 2);
            moduleResult += potency;
        }
        moduleResult = Math.pow(moduleResult, 0.5);
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
    public double estimateWeight(Word word, Document document) {
       if(document ==null){
           return 0;
       }
       if(word ==null){
           return 0;
       }
        double tfri;
        if (document.getLocation().equalsIgnoreCase("query")) {
            tfri = WordCount.getTF(word, document);
        } else {
            int nr = this.timesThatAWordRepeatsOnDocument(word, document);
            if (nr == 0) {
                return 0;
            }
            tfri = nr;
        }
        double invFrec = this.inverseFrecuency(word);
        double numerator = tfri * invFrec;
        double denominator;
        if (document.getModule() != -1) {
            denominator = document.getModule();
        } else {
            document.setModule(getDocumentModule(document, invFrec));
            documentManager.setDocumentModule(document);
            denominator = document.getModule();
        }
        return numerator / denominator;
    }

    /**
     * A query can have different similitudes between documents. 
     * Here the list of similitudes between a query and all the documents
     * @param document the query
     * @return An Array of Similitude.
     */

    public Collection<Similitude> determinateBestSimilitude(Document document) 
    {
        if(document ==null){
           return null;
       }
        Map <String, Integer> wordsOfQuery = WordCount.retrieveWordCount(document);
        wordsOfQuery = this.filterQuery(wordsOfQuery);
        Collection<String> c = wordsOfQuery.keySet();
        document.setModule(this.getQueryModule(wordsOfQuery));
        ArrayList<Similitude> sumilitudes = new ArrayList<Similitude>();

        Collection <DocumentResults> documentResults = postList.getCandidateDocumentsFiltered(c);
        if(documentResults.isEmpty()){
           return null;
        }
        for(DocumentResults res: documentResults){
            sumilitudes.add(this.determinateSimilitude(wordsOfQuery, res.getDocument(), document));
        }
        sortSimilitude(sumilitudes);
        return sumilitudes;

    }

    /**
     * ordeno las similitudes en forma descendiente por el coseno
     * @param sumilitu 
     */
    private void sortSimilitude (  List<Similitude> sumilitu){
        Comparator<Similitude> comparator = new Compare () ;
        Collections.sort(sumilitu,  comparator);
    }

    /**
     * class compare implement Comparator que ordena en forma descendiente
     */
    class Compare implements Comparator<Similitude> {

        @Override
        public int compare(Similitude lhs, Similitude rhs) {
            if (rhs.getSimilitude() >= lhs.getSimilitude()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * The words of a query need to be taken as a document,  and the document
     * needs to have a module. Because htis module is temporal and wount be 
     * saved on the postList, a different method was needed to calculate it.
     * @param query
     * @return The module of the query
     */
    public double getQueryModule(Map<String, Integer> wordsOfQuery) {
        double moduleResult = 0;
        Iterator iterator = wordsOfQuery.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Word word = this.vocabulary.getVocabularyWords().get(entry.getKey().toString());
            double termFrecuencyOnDocument = (Integer) entry.getValue();
            double invFrecuency = this.inverseFrecuency(word);
            double product = termFrecuencyOnDocument * invFrecuency;
            double potency = Math.pow(product, 2);
            moduleResult += potency;
        }
        moduleResult = Math.pow(moduleResult, 0.5);
        return moduleResult;
    }

    /**
     * Determina la similitud entre dos documentos
     * @param wordsOfQuery las palabra que aparecen en la query
     * @param document1 documento de la base de datos
     * @param document2 documento de la query
     * @return el coseno(similitud entre dos documentos)
     */
    public Similitude determinateSimilitude(Map<String, Integer> wordsOfQuery, Document document1, Document document2) {
        double coseno = 0;
        double weight2;
        Iterator iterator = wordsOfQuery.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Word word = this.vocabulary.getVocabularyWords().get(entry.getKey().toString());
            double weight1 = this.estimateWeight(word, document1);
            if (!weigthQuery.containsKey(word.getName())) {
                weight2 = this.estimateWeight(word, document2);
                weigthQuery.put(word.getName(), weight2);
            } else {
                weight2 = weigthQuery.get(word.getName());
            }

            coseno += weight1 * weight2;

        }
        return new Similitude(document1, document2, coseno);
    }

    /**
     * filtras sacas de la query las palabra que no esta en ningun documento
     * @param wordsOfQuery Map de palabras de la query
     * @return devuelve el Map de la query
     */
    public Map<String, Integer> filterQuery(Map<String, Integer> wordsOfQuery) {
        Map<String, Integer> filteredQuery = new HashMap<String, Integer>();
        Iterator iterator = wordsOfQuery.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (vocabulary.containsWord(entry.getKey().toString())) {
                filteredQuery.put(entry.getKey().toString(), (Integer) entry.getValue());
            }
        }
        return filteredQuery;
    }
}
