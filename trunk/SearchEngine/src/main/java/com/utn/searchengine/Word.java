/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 * This class represents a word and gots some extra info that can be used on the Vocabulary
 * @author aaltamir
 */
public class Word {
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Word(String name) {
        this.name = name;
    }
    @Override
    public int hashCode (){
    return name.hashCode();
    }
    @Override
    public String toString(){
        return name;
    }
    
}
