/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This Class is created in order to take atrack of the info of the
 * words, such as locations and times that the word appeared.
 * @TODO: check the way to order de locations and times in descendant order by amount of times.
 * @author aaltamir
 */
public class WordTracker {

    public WordTracker(Word word) {
        this.word = word;
    }
    
    private Word word;
    private Map<String, Integer> documentsPresent = new HashMap<String, Integer>();

    public Map<String, Integer> getDocumentsPresent() {
        return documentsPresent;
    }

    public void setDocumentsPresent(Map<String, Integer> documentsPresent) {
        this.documentsPresent = documentsPresent;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
    /**
     * 
     * @param document A document.
     * @return the times that a the word of the wordTracker appears on a specified 
     * document.
     */
    public int timesThatWordRepeatsOnDocument(Document document){
        int totalTimes = 0;
        if(documentsPresent.containsKey(document.getLocation())){
            totalTimes = (Integer)documentsPresent.get(document.getLocation());
        }
        return totalTimes;
    }
    /**
     * 
     * @return The number of documents where the word appears at least once.
     */
    public int numberOfDocumentsWhereWordAppears(){
        return documentsPresent.size();
    }
    @Override
    public String toString(){
        String aux = "word: "+this.word.toString(); 
        Iterator iterator = documentsPresent.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            aux+="\n"+entry.getKey().toString()+"-"+entry.getValue().toString();
        }
        return aux;
    }
    
}
