/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;

/**
 *
 * @author PC ACER
 */
public class JListRunnable extends Thread {
    private GUIManager gestor;
    public JListRunnable(GUIManager gestor)
    {
        this.gestor = gestor;
    }
    
    
    public void run() {
         while ( Communicator.crawl==true)
          {
           
          }
         gestor.returnDataList();
    }
    
}