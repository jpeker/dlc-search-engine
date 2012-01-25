/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 *
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
    private double module = -1;

    public double getModule() {
        return module;
    }

    public void setModule(double module) {
        this.module = module;
    }
    public boolean gotsModuleAssociated(){
        return this.module!=-1;
    }
    
}
