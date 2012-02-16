/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import dataaccess.factories.DAOFactory;

/**
 * This class is an auxiliar to manage all the basic info about the documents.
 * @author altamirano,peker,liberal
 */
public class DocumentManager {

    private int size;//cantidad de documentos

    /**
     * Recupera el modulo de un documento de la base de datos
     * @param document A document
     * @return the module of the documen. If the document is not present on the 
     * documentManager or its module hasn't been set, it returns -1
     */
    public double getDocumentModule(Document document) {

        return DAOFactory.getActiveDAOFactory().getDocumentDAO().obtenerDocument(document).getModule();
    }

    public boolean setDocumentModule(Document document) {
        return DAOFactory.getActiveDAOFactory().getDocumentDAO().grabarWebSite(document);
    }

    /**
     * Agrega un documento a la base de datos
     * @param document 
     */
    public void addDocument(Document document) {

        DAOFactory.getActiveDAOFactory().getDocumentDAO().grabarWebSite(document);

    }

    /**
     * devuelve la cantidad de documento de la base de datos
     * @return el size que es la cantidad de documentos
     */
    public int documentsSize() {
        return size;
    }

    /**
     * Recupera de la base de datos la cantidad de documentos 
     */
    public DocumentManager() {
        size = DAOFactory.getActiveDAOFactory().getDocumentDAO().obtenerCantidadDocument();
    }

    @Override
    public String toString() {
        String aux = "cantidad de documentos" + size;
        return aux;
    }
}
