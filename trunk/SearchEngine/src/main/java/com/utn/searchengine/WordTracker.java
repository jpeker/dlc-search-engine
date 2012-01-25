/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.HashMap;
import java.util.Map;

/**
 *
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
    public int timesThatWordRepeatsOnDocument(Document document){
        int totalTimes = 0;
        if(documentsPresent.containsKey(document.getLocation())){
            totalTimes = (Integer)documentsPresent.get(document.getLocation());
        }
        return totalTimes;
    }
    public int numberOfDocumentsWhereWordAppears(){
        return documentsPresent.size();
    }
    
}
