/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * An In-memory representation of a Post list.
 * This is recommend to be on disc so probably soon, aPostListManager will 
 * difference this Local Post list form the other.
 * @author aaltamir
 */
public class PostList {
    private Map<String,  ArrayList<WordTracker> > postList = new HashMap<String,  ArrayList<WordTracker> >();

    public Map<String,  ArrayList<WordTracker> > getPostList() {
        return postList;
    }

    public void setPostList(Map<String, ArrayList<WordTracker> > postList) {
        this.postList = postList;
    }

    public PostList() {
    }
    public PostList(Map<String,  ArrayList<WordTracker> > postList){
        this.postList = postList;
    }
    /**
     * Adds the  words of a document to the postList.
     * In case a word already exists on the post list, 
     * it adds the newlocation where the word has been found,
     * and the amount of times that it appeared.
     * @param words An array of locations and times that the word that the word
     * has been found on that document.
     * @param pageName: Don't remember why the name of this parameter, 
     * it is the word text.
     */
    public void addDocumentWords(Map<String, Integer> words, String pageName){
        Iterator iterator = words.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            int timesRepeated = (Integer)entry.getValue();
            if(!postList.containsKey(entry.getKey().toString())){
                Word word = new Word(entry.getKey().toString());
                
                Map<String, Integer> wordLocations = new HashMap<String, Integer>();
                wordLocations.put(pageName, timesRepeated);
              //  WordTracker wordTracker = new WordTracker(word);
               // wordTracker.setDocumentsPresent(wordLocations);
               // postList.put(word.getName(), wordTracker);
            }
            else{
              //  WordTracker wordTrackerToModify = postList.get(entry.getKey().toString());
              //  wordTrackerToModify.getDocumentsPresent().put(pageName, timesRepeated);
            }
        }
    }
    /**
     * 
     * @param word: A Word
     * @param document: A Document
     * @return The total times that the word appears on the document.
     */
    public int totalTimesThatWordRepeatsOnDocument(Word word, Document document){
        int totalTimes=0;
        if(postList.containsKey(word.getName())){
          //  WordTracker wordTrackerOfRequestedWord = (WordTracker)postList.get(word.getName());
           // totalTimes = wordTrackerOfRequestedWord.timesThatWordRepeatsOnDocument(document);
        }
        return totalTimes;
    }
    /**
     * 
     * @param word: A Word
     * @return The number of documents where the word has been found at least once.
     */
    public int numberOfDocumentsWhereWordAppears(Word word){
        int numberOfDocuments = 0;
        if(postList.containsKey(word.getName())){
//            WordTracker wordTracker = postList.get(word.getName());
  //          numberOfDocuments = wordTracker.numberOfDocumentsWhereWordAppears();
        }
        return numberOfDocuments;
    }
    /**
     * 
     * @return A Single Map containing all the words that exist on the post list.
     * This number should be the same that the number of words that exist on the
     * vocabulary.
     */
    public Set<String> getAllWords(){
       
        return postList.keySet();
    }
  
    @Override
    public String toString(){
        String aux = "";
        Iterator iterator = postList.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            aux+="\n"+entry.getKey().toString()+" - "+entry.getValue().toString();
        }
        return aux;
    }
    
    
}
