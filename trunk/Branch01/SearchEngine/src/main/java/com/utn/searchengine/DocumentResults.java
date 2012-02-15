/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 *
 * @author aaltamir
 */
public class DocumentResults {

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public int getNumberOfQueryWords() {
        return numberOfQueryWords;
    }

    public void setNumberOfQueryWords(int numberOfQueryWords) {
        this.numberOfQueryWords = numberOfQueryWords;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentResults other = (DocumentResults) obj;
        if ((this.document.getLocation() == null) ? (other.document.getLocation() != null) : !this.document.getLocation().equals(other.document.getLocation())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.document.getLocation() != null ? this.document.getLocation().hashCode() : 0);
        return hash;
    }

    public DocumentResults(Document document, int numberOfQueryWords) {
        this.document = document;
        this.numberOfQueryWords = numberOfQueryWords;
    }
    private Document document;
    private int numberOfQueryWords;
    
}
