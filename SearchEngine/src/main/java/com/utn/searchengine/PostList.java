/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private  ArrayList<WordTracker> wordTrackers=null;
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
     * @param documentLocation: The document Location
     */
    public void addDocumentWords(Map<String, Integer> words, String documentLocation){
        Iterator iterator = words.entrySet().iterator();
        
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            int timesRepeated = (Integer)entry.getValue();
            if(!postList.containsKey(entry.getKey().toString())){
                WordTracker wordTracker = new WordTracker(timesRepeated, documentLocation);                
                
              wordTrackers = new ArrayList<WordTracker>();
               wordTrackers.add(wordTracker);
                sort();
                postList.put(entry.getKey().toString(), wordTrackers);
            }
            else{
              ArrayList<WordTracker> wordTrackersToModify = (ArrayList<WordTracker>) postList.get(entry.getKey().toString());
              wordTrackersToModify.add(new WordTracker(timesRepeated, documentLocation));
            }
        }
        
      
    }
    // lo ordeno mediante
     private void sort ()
    {
        Comparator<WordTracker> comparator = new Compare () ;
    Collections.sort(wordTrackers,  comparator);
   /* Iterator i =  wordTrackers.iterator();// recorro para ver que lo ordena
    while(i.hasNext())
    {
        WordTracker w = (WordTracker)i.next();
        System.out.println("ordena "+w.getFrequency());
    }*/
    }
     //class Comparator que ordena en forma decreciente
    class Compare implements Comparator<WordTracker> {
	@Override
		public int compare(WordTracker lhs, WordTracker rhs) {
  		if(rhs.getFrequency()>=lhs.getFrequency())
            return 1 ;
                else
                    
                    return -1;
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
            ArrayList<WordTracker> wordTrackerOfRequestedWord = postList.get(word.getName());
            wordTrackerOfRequestedWord.indexOf(word);
            for(WordTracker auxiliarWordTracker: wordTrackerOfRequestedWord){
                if (auxiliarWordTracker.getLocation().equalsIgnoreCase(document.getLocation())){
                    totalTimes = auxiliarWordTracker.getFrequency();
                    break;
                }
            }
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
            ArrayList<WordTracker> wordTrackers = postList.get(word.getName());
            numberOfDocuments = wordTrackers.size(); 
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
