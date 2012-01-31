package com.utn.searchengine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {      
        Word word = new Word ("combustible");
        
        HashMap hashMapCrawlered= new HashMap();
        hashMapCrawlered.put("http://www.yatefortuna.com.ar", "El combustible diesel es vital para la agricultura");
        Collection c = hashMapCrawlered.values();
        Vector vValues = new Vector();
        Iterator itr = c.iterator();
        while(itr.hasNext())
        {
        String s=itr.next().toString();
        System.out.println(s);
        vValues.add(s);
        }
        Document document = new Document("Doc1",hashMapCrawlered.get("http://www.yatefortuna.com.ar").toString(),"El combustible diesel es vital para la agricultura");

        WeightManager weight = new WeightManager(word, document);
        weight.estimateWeight();
        
        //WordCount wordCount = new WordCount();
        //wordCount.retrieveWordCount("C:\\Users\\aaltamir\\Desktop\\JSpider TODO.txt");
    }
}
