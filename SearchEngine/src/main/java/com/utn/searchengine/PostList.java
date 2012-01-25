/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author aaltamir
 */
public class PostList {
    private Map<String, WordTracker> postList = new HashMap<String, WordTracker>();

    public Map<String, WordTracker> getPostList() {
        return postList;
    }

    public void setPostList(Map<String, WordTracker> postList) {
        this.postList = postList;
    }

    public PostList() {
    }
    public PostList(Map<String, WordTracker> postList){
        this.postList = postList;
    }
    
    public void addDocumentWords(Map<String, Integer> words, String pageName){
        Iterator iterator = words.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            int timesRepeated = (Integer)entry.getValue();
            if(!postList.containsKey(entry.getKey().toString())){
                Word word = new Word(entry.getKey().toString());
                
                Map<String, Integer> wordLocations = new HashMap<String, Integer>();
                wordLocations.put(pageName, timesRepeated);
                WordTracker wordTracker = new WordTracker(word);
                wordTracker.setDocumentsPresent(wordLocations);
                postList.put(word.getName(), wordTracker);
            }
            else{
                WordTracker wordTrackerToModify = postList.get(entry.getKey().toString());
                wordTrackerToModify.getDocumentsPresent().put(pageName, timesRepeated);
            }
        }
    }
    public int totalTimesThatWordRepeatsOnDocument(Word word, Document document){
        int totalTimes=0;
        if(postList.containsKey(word.getName())){
            WordTracker wordTrackerOfRequestedWord = (WordTracker)postList.get(word.getName());
            totalTimes = wordTrackerOfRequestedWord.timesThatWordRepeatsOnDocument(document);
        }
        return totalTimes;
    }
    
    public int numberOfDocumentsWhereWordAppears(Word word){
        int numberOfDocuments = 0;
        if(postList.containsKey(word.getName())){
            WordTracker wordTracker = postList.get(word.getName());
            numberOfDocuments = wordTracker.numberOfDocumentsWhereWordAppears();
        }
        return numberOfDocuments;
    }
    public Set<String> getAllWords(){
       
        return postList.keySet();
    }
    /**
    public double getDocumentModule(Document document, int numberOfDocuments){
        
        Iterator iterator = postList.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            Word word = new Word(entry.getKey().toString());
            int wordFrecuencyOnDocument = this.totalTimesThatWordRepeatsOnDocument(word, document);
            
        }
    }
     * **/
    
    
}
