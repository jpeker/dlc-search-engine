/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;



/**
 * This class still gots no behavior, but it 
 * is supposed to estimate each word weight on each document.
 * Now it is only ued to prove at least one word.
 * @author aaltamir
 */
public class WeightManager {
        private Word word;
    private Document document;
    protected HashMap hashMapCrawlered=new HashMap();
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
   
        hashMapCrawlered.put("http://www.yatefortuna.com.ar", "El combustible diesel es vital para la agricultura");
        hashMapCrawlered.put("http://www.yatefortuna.com.ar/index.html","El transporte de pasajeros tiene un subsidio para el combustible diesel");
        hashMapCrawlered.put("http://www.yatefortuna.com.ar/barcos.htm", "El transporte no funciona hoy");
        hashMapCrawlered.put("http://www.yatefortuna.com.ar/servicios.htm", "Hay transportes y transportes...");
        hashMapCrawlered.put("http://www.yatefortuna.com.ar/galeria.htm", "El diesel venezolano es de menor calidad que el diesel argentino");


        Collection c =  hashMapCrawlered.values();
        Vector vValues = new Vector();
        Iterator itr = c.iterator();
        while(itr.hasNext())
        {
        String s=itr.next().toString();
        System.out.println(s);
        vValues.add(s);
        }
  
        Document document1 = new Document("Doc1",hashMapCrawlered.get("http://www.yatefortuna.com.ar").toString(),"El combustible diesel es vital para la agricultura");
        Document document2 = new Document("Doc2",hashMapCrawlered.get("http://www.yatefortuna.com.ar/index.html").toString(),"El transporte de pasajeros tiene un subsidio para el combustible diesel");
        Document document3 = new Document("Doc3",hashMapCrawlered.get("http://www.yatefortuna.com.ar/barcos.htm").toString(),"El transporte no funciona hoy");
        Document document4 = new Document("Doc4",hashMapCrawlered.get("http://www.yatefortuna.com.ar/servicios.htm").toString(),"Hay transportes y transportes...");
        Document document5 = new Document("Doc5",hashMapCrawlered.get("http://www.yatefortuna.com.ar/galeria.htm").toString(),"El diesel venezolano es de menor calidad que el diesel argentino");

        wordCountManager.addDocument(document1);
        wordCountManager.addDocument(document2);
        wordCountManager.addDocument(document3);
        wordCountManager.addDocument(document4);
        wordCountManager.addDocument(document5);
        Word wordToProve = new Word("combustible");
        double weight = wordCountManager.estimateWeight(wordToProve, document1);
        System.out.println("El peso de la palabra "+word.getName()+"es: "+weight);
        return weight;
        
        
        
    }
    
    
     
    
    
    
}