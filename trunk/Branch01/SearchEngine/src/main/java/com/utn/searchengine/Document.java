/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

import dataaccess.factories.DAOFactory;

/**
 * This class represents a text source, it can be a page
 * or a text document
 * @author altamirano,peker,liberal
 */
public class Document implements Comparable{

    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getText() {
        return text;
    }


 

    public Document( String location, String text) {
    
        this.location = location;
        this.text=text;
    }
 
    private String location;
    private String text;
    //it could be a nice idea to save on the document the module in
    //order to rehuse it and dont waste tamir recalculating that value allTime.
    private double module = -1;

    public double getModule() {
        return module;
    }

    public void setModule(double module) {
        this.module = module;
        DAOFactory.getActiveDAOFactory().getDocumentDAO().grabarWebSite(this);
    }
    /**
     * 
     * @return -1 if no module is associated to a document. 
     */
    public boolean gotsModuleAssociated(){
        return this.module!=-1;
    }
        @Override
    public String toString() {
        String aux = " location=" + location + "module=" + module ;
        return aux;
    }
 @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.location != null ? this.location.hashCode() : 0);
        return hash;
    }
   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Document other = (Document) obj;
        if ((this.location == null) ? (other.location != null) : !this.location.equals(other.location)) {
            return false;
        }
        return true;
    }
    public int compareTo(Object o) {
        Document documentToCompare = (Document) o;
        return this.getLocation().compareTo(documentToCompare.getLocation());
    }
}
