/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import dataaccess.factories.DAOFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * An In-memory representation of a Post list.
 * This is recommend to be on disc so probably soon, aPostListManager will 
 * difference this Local Post list form the other.
 * @author  altamirano,peker,liberal
 */
public class PostList {

    private Map<String, ArrayList<WordTracker>> postList;

    public Map<String, ArrayList<WordTracker>> getPostList() {
        return postList;
    }

    public void setPostList(Map<String, ArrayList<WordTracker>> postList) {
        this.postList = postList;
    }

    public PostList() {
        postList = new HashMap<String, ArrayList<WordTracker>>();
    }

    public PostList(Map<String, ArrayList<WordTracker>> postList) {
        this.postList = postList;
    }

    /**
     * Adds the  words of a document to the postList.
     * In case a word already exists on the post list, 
     * it adds the newlocation where the word has been found,
     * and the amount of times that it appeared.
     * @param words An array of locations and times that the word that the word
     * has been found on that document.
     * @param documentLocation: The document Location
     */
    public void addDocumentWords(Map<String, Integer> words, String documentLocation) {
        Iterator iterator = words.entrySet().iterator();
        Word wordToAdd;
        Document docToAdd;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            int timesRepeated = (Integer) entry.getValue();
            if (!DAOFactory.getActiveDAOFactory().getPostListDAO().Contains(entry.getKey().toString())) {
                WordTracker wordTracker = new WordTracker(timesRepeated, documentLocation);
                wordToAdd = new Word(entry.getKey().toString(), 1, wordTracker.getFrequency());
                docToAdd = new Document(documentLocation, "");
                DAOFactory.getActiveDAOFactory().getPostListDAO().grabarPostList(wordToAdd, docToAdd, timesRepeated);
            } else {
                wordToAdd = new Word(entry.getKey().toString());
                docToAdd = new Document(documentLocation, "");
                DAOFactory.getActiveDAOFactory().getPostListDAO().grabarPostList(wordToAdd, docToAdd, timesRepeated);
            }
        }
    }

    /**
     * Frecuencia de una palabra en un documento
     * @param word: A Word
     * @param document: A Document
     * @return The total times that the word appears on the document.
     */
    public int totalTimesThatWordRepeatsOnDocument(Word word, Document document) {

        return DAOFactory.getActiveDAOFactory().getPostListDAO().getTF(word, document);
    }

    /**
     * cantidad de documento que aparece una palabra
     * @param word: A Word
     * @return The number of documents where the word has been found at least once.
     */
    public int numberOfDocumentsWhereWordAppears(Word word) {

        return DAOFactory.getActiveDAOFactory().getWordDAO().getNrWord(word);
    }

    /**
     * 
     * @return A Single Map containing all the words that exist on the post list.
     * This number should be the same that the number of words that exist on the
     * vocabulary.
     */
    public Map<String, Word> getAllWords() {

        return DAOFactory.getActiveDAOFactory().getWordDAO().getVocabulary();
    }

    /**
     * Collecion de documentos candidatos
     * @param wordquery palabras de la query
     * @return collecion de documentos que aparece las palabras de la query
     */
    public Collection<Document> getCandidateDocuments(Collection<String> wordquery) {
        return DAOFactory.getActiveDAOFactory().getPostListDAO().obtenerDocumentoCandidatos(wordquery);
    }

    /**
     * las palabras de un documentos
     * @param document que quiero que me traiga palabras
     * @return la lista de palabras
     */
    public List<Word> getWordsDocument(Document document) {
        return DAOFactory.getActiveDAOFactory().getPostListDAO().getWordsDocument(document);
    }

    /**
     * devuelve los documentos candidatos filtrados por la frecuencia y el nr
     * @param  wordquery palabras de la query
     * @return una lista de documentos candidatos filtrado
     */
    public List<DocumentResults> getCandidateDocumentsFiltered(Collection<String> wordquery) {
        return DAOFactory.getActiveDAOFactory().getPostListDAO().getFilteredCandidates(wordquery);
    }
}
