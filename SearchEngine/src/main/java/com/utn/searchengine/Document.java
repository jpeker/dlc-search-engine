/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 * This class represents a text source, it can be a page
 * or a text document
 * @author aaltamir
 */
public class Document {

    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document(String name, String location) {
        this.name = name;
        this.location = location;
    }
    private String name;
    private String location;
    //it could be a nice idea to save on the document the module in
    //order to rehuse it and dont waste tamir recalculating that value allTime.
    private double module = -1;

    public double getModule() {
        return module;
    }

    public void setModule(double module) {
        this.module = module;
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
        String aux ="name=" + name + " location=" + location + "module=" + module ;
        return aux;
    }
}
