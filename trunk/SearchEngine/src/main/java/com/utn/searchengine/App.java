package com.utn.searchengine;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {        
        Word word = new Word ("combustible");
        Document document = new Document("doc1", "G:\\Julian Peker\\Final dlc\\SearchEngine\\src\\main\\resources\\TestDocuments\\doc1.txt");
        WeightManager weight = new WeightManager(word, document);
        weight.estimateWeight();
        
        //WordCount wordCount = new WordCount();
        //wordCount.retrieveWordCount("C:\\Users\\aaltamir\\Desktop\\JSpider TODO.txt");
    }
}
