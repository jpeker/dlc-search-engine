package com.utn.searchengine;

import java.io.File;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {        
        Word word = new Word ("combustible");
        Document document = new Document("doc1", "E:\\Projects\\SearchEngine\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc1.txt");
        WeightManager weight = new WeightManager(word, document);
        weight.estimateWeight();
        
        //WordCount wordCount = new WordCount();
        //wordCount.retrieveWordCount("C:\\Users\\aaltamir\\Desktop\\JSpider TODO.txt");
    }
}
