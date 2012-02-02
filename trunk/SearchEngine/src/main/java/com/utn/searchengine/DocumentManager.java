/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is an auxiliar to manage all the basic info about the documents.
 * @author aaltamir
 */
public class DocumentManager {

    public Collection<Document> getDocuments() {
        return documents;
    }

    /**
     * 
     * @param document A document
     * @return the module of the documen. If the document is not present on the 
     * documentManager or its module hasn't been set, it returns -1
     */
    public double getDocumentModule(Document document){
        for(Document actualDocument : documents){
            if(document.compareTo(actualDocument)==0){
                return actualDocument.getModule();
            }
        }
        return -1;
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
     @Override
    public String toString() {
           String aux = "";
        Iterator iterator = documents.iterator();
        while(iterator.hasNext()){
           Document document = (Document)iterator.next();
            aux+="\n"+document.toString();
        }
        return aux;
    }
}
