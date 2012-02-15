/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import javax.swing.JLabel;
import javax.swing.JProgressBar;


/**
 *
 * @author PC ACER
 */
public class GUIManager {

    private JProgressBar _jpbArchivo;

    private JProgressBar _jpbProceso;
    
    private JLabel _jLabel;
   
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
