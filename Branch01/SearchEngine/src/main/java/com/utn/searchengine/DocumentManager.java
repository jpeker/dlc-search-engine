/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import dataaccess.factories.DAOFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is an auxiliar to manage all the basic info about the documents.
 * @author altamirano,peker,liberal
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
       
        return  DAOFactory.getActiveDAOFactory().getDocumentDAO().obtenerDocument(document).getModule();
    }

    public void setDocuments(Collection<Document> documents) {
        this.documents = documents;
    }
    public void addDocument(Document document){
        documents.add(document);
          DAOFactory.getActiveDAOFactory().getDocumentDAO().grabarWebSite(document);

    }
    public void remove(Document document){
        documents.remove(document);
    }
    public int documentsSize(){
        return  DAOFactory.getActiveDAOFactory().getDocumentDAO().obtenerCantidadDocument();
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
