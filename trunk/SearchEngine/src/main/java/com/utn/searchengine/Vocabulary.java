/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is a temporal-in memory array that manages the information about
 * the words
 * @author aaltamir
 */
public class Vocabulary {

    public Map<Integer, VocabularyEntry> getVocabularyWords() {
        return vocabularyWords;
    }

    public void setVocabularyWords(Map<Integer, VocabularyEntry> vocabularyWords) {
        this.vocabularyWords = vocabularyWords;
    }

    public Vocabulary() {
    }
    private Map<Integer, VocabularyEntry> vocabularyWords = new HashMap<Integer, VocabularyEntry>();
    
    public Vocabulary(Map<Integer, VocabularyEntry> vocabularyWords){
        this.vocabularyWords = vocabularyWords;
    }
    /**
     * Adds aword to the map
     * @param word
     * @return 
     */
    public void addWord(VocabularyEntry entry){
        this.vocabularyWords.put(entry.hashCode(), entry);
    }
    /**
     * adds all the new words of a new document to the vocabulary
     * @param words: a collection of words
     */
    public void addDocumentWords(Map<String, Integer> words){
        Iterator iterator = words.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            if(!vocabularyWords.containsKey(entry.getKey().hashCode())){
                Word word = new Word(entry.getKey().toString());
                //if the word appears for the first time on the vocabulary, the values of max term frecuency and 
                //the biggest frecuency and the total times that the word appears are the same.
                int values = Integer.getInteger(entry.getValue().toString());
                VocabularyEntry vocabularyEntry = new VocabularyEntry(word, values, values);
                vocabularyWords.put(vocabularyEntry.hashCode(), vocabularyEntry);
            }
            else{
                VocabularyEntry entryToModify = vocabularyWords.get(entry.getKey().hashCode());
                int timesThatWordAppearsOnNewDocument = (Integer)entry.getValue();
                int biggestDocumentTermFrecuency = entryToModify.getDocumentBiggestTermFrecuency();
                if(timesThatWordAppearsOnNewDocument > biggestDocumentTermFrecuency){
                    entryToModify.setDocumentBiggestTermFrecuency(timesThatWordAppearsOnNewDocument);
                }
                int newTotalTimes =entryToModify.getTotalTimesThatWordAppears()+timesThatWordAppearsOnNewDocument;
                entryToModify.setTotalTimesThatWordAppears(newTotalTimes);
            }
        }
    
    }
   
}