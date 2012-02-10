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
 * @author altamirano,peker,liberal
 */
public class Vocabulary {

    public Map<String, Word> getVocabularyWords() {
        return vocabularyWords;
    }

    public void setVocabularyWords(Map<String, Word> vocabularyWords) {
        this.vocabularyWords = vocabularyWords;
    }

    public Vocabulary() {
    }
    private Map<String, Word> vocabularyWords = new HashMap<String, Word>();
    
    public Vocabulary(Map<String, Word> vocabularyWords){
        this.vocabularyWords = vocabularyWords;
    }
    /**
     * Adds aword to the map
     * @param word
     * @return 
     */
    public void addWord(Word entry){
        this.vocabularyWords.put(entry.getName(), entry);
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
                //if the word appears for the first time on the vocabulary, the values of max term frecuency and 
                //the biggest frecuency and the total times that the word appears are the same.
                int values = (Integer)entry.getValue();
                Word word = new Word(entry.getKey().toString(), 1, values);
                //VocabularyEntry vocabularyEntry = new VocabularyEntry(word, 1, values);
                vocabularyWords.put(word.getName(), word);
            }
            else{
                //the word already exists so it is necesarry to update their attributes.
                Word wordToModify = vocabularyWords.get(entry.getKey().toString());
                //VocabularyEntry entryToModify = vocabularyWords.get(entry.getKey().toString());
                int timesThatWordAppearsOnNewDocument = (Integer)entry.getValue();
                
                int biggestDocumentTermFrecuency = wordToModify.getMaxTF();
                if(timesThatWordAppearsOnNewDocument > biggestDocumentTermFrecuency){
                    wordToModify.setMaxTF(timesThatWordAppearsOnNewDocument);
                    //entryToModify.setDocumentBiggestTermFrecuency(timesThatWordAppearsOnNewDocument);
                }
                int newTotalDocuments =wordToModify.getNr();
                newTotalDocuments++;
                wordToModify.setNr(newTotalDocuments);
                //entryToModify.setTotalDocumentsThatWordAppears(newTotalDocuments);
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