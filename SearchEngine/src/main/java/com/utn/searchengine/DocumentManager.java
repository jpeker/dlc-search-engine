/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author aaltamir
 */
public class DocumentManager {

    public Collection<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Collection<Document> documents) {
        this.documents = documents;
    }
    public void addDocument(Document document){
        documents.add(document);
    }
    public void remove(Document document){
        documents.remove(document);
    }
    public int documentsSize(){
        return documents.size();
    }
    public DocumentManager() {
    }
    public void clearDocuments(){
        documents.clear();
    }
    private Collection<Document> documents = new ArrayList<Document>();
    
}
