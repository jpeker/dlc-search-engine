/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;

/**
 *
 * @author altamirano,peker,liberal
 */
public class LabelRunnable extends Thread {

    private GUIManager gestor;

    public LabelRunnable(GUIManager gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        while (Communicator.crawl == true || Communicator.search == true) {
            gestor.actualizarLabel(true);
        }
        gestor.actualizarLabel(false);
    }
}
    

