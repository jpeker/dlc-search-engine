/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import Spider.*;
import java.util.Map;



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

   
   
    public void estimateWeight(){
        LocalWordCountManager wordCountManager = new LocalWordCountManager();
   
    Communicator com = new Communicator ();
       
          Iterator i =  com.beginCrawler("http://www.yatefortuna.com.ar").entrySet().iterator();
          while(i.hasNext()){
                    Map.Entry entry = (Map.Entry) i.next();
                    Document documento = new Document(entry.getKey().toString(),entry.getKey().toString(),entry.getValue().toString());
                     wordCountManager.addDocument(documento);
               }
  
       /* Document document1 = new Document("Doc1","http://www.yatefortuna.com.ar","el combustible diesel es vital para la agricultura");
        Document document2 = new Document("Doc2","http://www.yatefortuna.com.ar/index.html","el transporte de pasajeros tiene un subsidio para el combustible diesel");
        Document document3 = new Document("Doc3","http://www.yatefortuna.com.ar/barcos.htm","el transporte no funciona hoy");
        Document document4 = new Document("Doc4","http://www.yatefortuna.com.ar/servicios.htm","hay transportes y transportes...");
        Document document5 = new Document("Doc5","http://www.yatefortuna.com.ar/galeria.htm","el diesel venezolano es de menor calidad que el diesel argentino");
        //Document document6 = new Document("Doc6","http://www.yatefortuna.com.ar/gato.htm","todo sobre combustible en la futura de la agricultura");
        //Document document7 = new Document("Doc7","http://www.yatefortuna.com.ar/ga.htm","todo sobre diesel en la futura de la agricultura");

        wordCountManager.addDocument(document1);
        wordCountManager.addDocument(document2);
        wordCountManager.addDocument(document3);
        wordCountManager.addDocument(document4);
        wordCountManager.addDocument(document5);
        //wordCountManager.addDocument(document6);
        //wordCountManager.addDocument(document7);*/
        /**
        Word wordToProve = new Word("combustible");
        double weight = wordCountManager.estimateWeight(wordToProve, document1);
        System.out.println("El peso de la palabra "+word.getName()+"es: "+weight);
        return weight;
         * **/
        String query = "Cada pescador debe traer su propio equipo de pesca";
        
        Collection<Similitude> similitudes = wordCountManager.determinateBestSimilitude(new Document("query", "query", query));
        System.out.println("Probando resultado de la query: \n");
        for(Similitude similitude: similitudes){
            System.out.println(similitude.toString());
        }
    }
    
}
