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



public class ProgressBarRunnable extends Thread{
    private GUIManager gestor;


    public ProgressBarRunnable(GUIManager gestor)
    {
        this.gestor = gestor;
    }

    @Override
    public void run()
    {

        while ( Communicator.crawl==true )
          {
           gestor.actualizarLabel(true);
          }
            gestor.actualizarLabel(false);

         } 

    }
    

