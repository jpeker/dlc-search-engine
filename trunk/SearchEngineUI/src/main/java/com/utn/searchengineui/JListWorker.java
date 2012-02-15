/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingWorker;

/**
 *
 * @author Administrador
 */



public class JListWorker extends SwingWorker<String,Object>{

    private JList jlist;
    private DefaultListModel dlf;
    JListWorker(JList jlist, DefaultListModel dlf){
    this.jlist=jlist;
    this.dlf=dlf;  
    }

    @Override
    protected String doInBackground() throws Exception {
    while(Communicator.crawl==true){}
    returnDataList();
    jlist.setModel(dlf);
    return "listok";
    }


    @Override
    protected void done(){
    
    }

    public void returnDataList()
    {
        for(int b=0;b<2;b++)
        {
        switch(b){
            case 0:{
                dlf.addElement("List of Error Links");
                dlf.addElement(" ");
                for(int i=0;i<Communicator.linkserror.size();i++)
                {
                dlf.addElement(Communicator.linksprocessed.get(i).toString().toString());
                }
                break;}
            case 1:{
                dlf.addElement("List of Processed Links");
                dlf.addElement(" ");
              for(int i=0;i<Communicator.linksprocessed.size();i++)
                {
                dlf.addElement(Communicator.linksprocessed.get(i).toString().toString());
                }
                break;}
        }

      }
    }


}
