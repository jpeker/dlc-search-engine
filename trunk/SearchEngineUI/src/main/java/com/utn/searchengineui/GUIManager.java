/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;

/**
 *
 * @author PC ACER
 */
public class GUIManager {


    private JProgressBar _jpbArchivo;

    private JProgressBar _jpbProceso;
    
    private JLabel _jLabel;
    
    private JList _jList;
    
    private DefaultListModel _jListModel;
    
    public static enum Estado {iniciado, crawlear, descomprimir , comprimirPausado, descomprimirPausado};
    private  Estado estado ;

     
    public GUIManager( JProgressBar _jpbArchivo)
    {
   
     this._jpbArchivo = _jpbArchivo;
    }
    
    
    public GUIManager( JLabel _jLabel)
    {
     this._jLabel = _jLabel;
    }
    
   
    public GUIManager( JList _jList, DefaultListModel _jListModel)
    {
    this._jListModel = _jListModel;
     this._jList = _jList;

    }

     public void actualizarJPBCrawler (int i,boolean oscil)
    {
        if(oscil=true){        
        this._jpbArchivo.setValue(100);
        }
        else{        
        this._jpbArchivo.setValue(0);
        }
    }
    
    public void actualizarLabel (boolean visible)
    {
        if(visible)        
        this._jLabel.setVisible(true);
        else       
        this._jLabel.setVisible(false);

    }

    
    public void returnDataList()
    {
   
        //DefaultListModel listmodel= new DefaultListModel();
       // this._jList.setModel(listmodel);
        ArrayList<String>a=Communicator.linksprocessed;
        ArrayList<String>c=Communicator.linkserror;
        for(int b=0;b<2;b++)
        {
        switch(b){
            case 0:{
                _jListModel.addElement("List of Error Links");
                _jListModel.addElement(" ");
                for(int i=0;i<c.size();i++)
                {
                _jListModel.addElement((String)c.get(i).toString());
                }
                break;}
            case 1:{
                _jListModel.addElement("List of Processed Links");
                _jListModel.addElement(" ");
              for(int i=0;i<a.size();i++)
                {
               // _jListModel.addElement((String)a.get(i).toString());
                }
                break;}
        }

      }
        
        
    }
    
    public void returnDataLabel (String value)
    {
        this._jLabel.setText(value);
    }
     
     
    /*
     * Actualiza la progress Bar de Archivos
     * @param i valor
     * @param tam tamaño de la barra
     */

    public void actualizarJPBProceso(int i, long tam)
    {

        this._jpbProceso.setValue((int)((i*100/tam)));
        this._jpbProceso.setString("Porcentaje: " + this._jpbProceso.getValue() + "%");
        Float f = (float)tam;

    }

    public Estado getEstado()

    {
        return estado;
    }
    public void setEstado(Estado e)
    {
        this.estado = e;
    }

}
