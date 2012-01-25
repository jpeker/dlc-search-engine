/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;



/**
 *
 * @author aaltamir
 */
public class WeightManager {
    private Word word;
    private Document document;

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public WeightManager(Word word, Document document) {
        this.word = word;
        this.document = document;
    }
    
    public double estimateWeight(){
        LocalWordCountManager wordCountManager = new LocalWordCountManager();
        Document document1 = new Document("Doc1","E:\\Projects\\SearchEngine\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc1.txt" );
        Document document2 = new Document("Doc1","E:\\Projects\\SearchEngine\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc2.txt" );
        Document document3 = new Document("Doc1","E:\\Projects\\SearchEngine\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc3.txt" );
        Document document4 = new Document("Doc1","E:\\Projects\\SearchEngine\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc4.txt" );
        Document document5 = new Document("Doc1","E:\\Projects\\SearchEngine\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc5.txt" );
        wordCountManager.addDocument(document1);
        wordCountManager.addDocument(document2);
        wordCountManager.addDocument(document3);
        wordCountManager.addDocument(document4);
        wordCountManager.addDocument(document5);
        Word wordToProve = new Word("Combustible");
        double weight = wordCountManager.estimateWeight(wordToProve, document1);
        System.out.println("El peso de la palabra "+word.getName()+"es: "+weight);
        return weight;
        
        
        
    }
    
    
     
    
    
    
}
