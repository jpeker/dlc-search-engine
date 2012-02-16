/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;
import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 *
 * @author Administrador
 */
public class JButtonWorker  extends SwingWorker<String,Object> {


    private JButton jbutton;

    JButtonWorker(JButton jbutton){
    this.jbutton=jbutton;
    }

    @Override
    protected String doInBackground() throws Exception {
    while(Communicator.crawl==true || Communicator.crawl==true){
    jbutton.setEnabled(false);}
    jbutton.setEnabled(true);
    return "listok";
    }


    @Override
    protected void done(){

    }



}
