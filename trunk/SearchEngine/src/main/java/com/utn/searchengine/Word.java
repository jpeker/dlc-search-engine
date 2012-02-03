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
    private int totalFrecuency;
    private int maxTF;

    public int getMaxTF(){
        return maxTF;
    }

    public void setMaxTF(int maxTF) {
        this.maxTF = maxTF;
    }

    public int getTotalFrecuency() {
        return totalFrecuency;
    }

    public void setTotalFrecuency(int totalFrecuency) {
        this.totalFrecuency = totalFrecuency;
    }

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
