/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import javax.swing.JLabel;

/**
 *
 * @author altamirano,peker,liberal
 */
public class GUIManager {

    private JLabel _jLabel;

    public GUIManager(JLabel _jLabel) {
        this._jLabel = _jLabel;
    }

    public void actualizarLabel(boolean visible) {
        if (visible) {
            this._jLabel.setVisible(true);
        } else {
            this._jLabel.setVisible(false);
        }
    }

    public void returnDataLabel(String value) {
        this._jLabel.setText(value);
    }
}
