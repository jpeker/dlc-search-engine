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

    public Map<String, VocabularyEntry> getVocabularyWords() {
        return vocabularyWords;
    }

    public void setVocabularyWords(Map<String, VocabularyEntry> vocabularyWords) {
        this.vocabularyWords = vocabularyWords;
    }

    public Vocabulary() {
    }
    private Map<String, VocabularyEntry> vocabularyWords = new HashMap<String, VocabularyEntry>();
    
    public Vocabulary(Map<String, VocabularyEntry> vocabularyWords){
        this.vocabularyWords = vocabularyWords;
    }
    /**
     * Adds aword to the map
     * @param word
     * @return 
     */
    public void addWord(VocabularyEntry entry){
        this.vocabularyWords.put(entry.getWord().toString(), entry);
    }
    /**
     * adds all the new words of a new document to the vocabulary
     * @param words: a collection of words
     */
    public void addDocumentWords(Map<String, Integer> words){
        Iterator iterator = words.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            if(!vocabularyWords.containsKey(entry.getKey().toString())){
                Word word = new Word(entry.getKey().toString());
                //if the word appears for the first time on the vocabulary, the values of max term frecuency and 
                //the biggest frecuency and the total times that the word appears are the same.
                int values = (Integer)entry.getValue();
                VocabularyEntry vocabularyEntry = new VocabularyEntry(word, 1, values);
                vocabularyWords.put(vocabularyEntry.getWord().toString(), vocabularyEntry);
            }
            else{
                //the word already exists so it is necesarry to update their attributes.
                VocabularyEntry entryToModify = vocabularyWords.get(entry.getKey().toString());
                int timesThatWordAppearsOnNewDocument = (Integer)entry.getValue();
                int biggestDocumentTermFrecuency = entryToModify.getDocumentBiggestTermFrecuency();
                if(timesThatWordAppearsOnNewDocument > biggestDocumentTermFrecuency){
                    entryToModify.setDocumentBiggestTermFrecuency(timesThatWordAppearsOnNewDocument);
                }
                int newTotalDocuments =entryToModify.getTotalDocumentsThatWordAppears();
                newTotalDocuments++;
                entryToModify.setTotalDocumentsThatWordAppears(newTotalDocuments);
            }
        }
    
    }
    public boolean containsWord(String word){
        return vocabularyWords.containsKey(word);
    }
    
    @Override
    public String toString(){
        String aux ="";
        Iterator iterator = vocabularyWords.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            aux+= entry.getKey().toString()+"-"+entry.getValue().toString()+"\n";
        }
        return aux;
    }
   
}