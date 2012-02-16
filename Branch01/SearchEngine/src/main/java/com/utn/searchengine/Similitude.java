/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 *  This class represents the similitude between two 
 * documents
 * @author altamirano,peker,liberal
 */
public class Similitude {

    public Similitude(Document documentA, Document documentB) {
        this.documentA = documentA;
        this.documentB = documentB;
    }

    public Document getDocumentA() {
        return documentA;
    }

    public void setDocumentA(Document documentA) {
        this.documentA = documentA;
    }

    public Document getDocumentB() {
        return documentB;
    }

    public void setDocumentB(Document documentB) {
        this.documentB = documentB;
    }

    public double getSimilitude() {
        return similitude;
    }

    public void setSimilitude(double similitude) {
        this.similitude = similitude;
    }
    
    @Override
    public String toString(){
        String aux = "";
       aux+= "First Document: "+this.documentA.getLocation()+"-Module: " +this.documentA.getModule()+"-Second document: "+this.documentB.getLocation()
               +"-Similitude: "+this.similitude;

        return aux;
    }

    public Similitude(Document documentA, Document documentB, double similitude) {
        this.documentA = documentA;
        this.documentB = documentB;
        this.similitude = similitude;
    }
    private Document documentA;
    private Document documentB;
    private double similitude;
}
