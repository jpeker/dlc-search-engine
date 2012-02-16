/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author Administrador
 */
public class JTextBoxWorker extends SwingWorker<String,Object> {

    private JTextField jtext;

    JTextBoxWorker(JTextField jtext){
    this.jtext=jtext;
    }

    @Override
    protected String doInBackground() throws Exception {
    while(Communicator.crawl==true || Communicator.search==true){
    jtext.setEnabled(false);
    }
    jtext.setEnabled(true);
    return "listok";
    }


    @Override
    protected void done(){

    }



}
