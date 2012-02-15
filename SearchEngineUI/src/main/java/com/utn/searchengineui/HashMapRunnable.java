/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author PC ACER
 */
public class HashMapRunnable extends Thread {
    


    public void run() {
           while ( Communicator.crawl==false){
                 Iterator i =  Communicator.pages.entrySet().iterator();
                   while(i.hasNext()){
                   Map.Entry entry = (Map.Entry) i.next();
                   System.out.println("url "+entry.getKey()+"\n"+entry.getValue().toString()  );
                }
           }
        
    }
    
}
