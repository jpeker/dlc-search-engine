/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 * This class represents a word and gots some extra info that can be used on the Vocabulary
 * @author altamirano,peker,liberal
 */
public class Word {

    public Word(String name, int totalDocumentsWhereWordAppears, int maxTF) {
        this.name = name;
        this.nr = totalDocumentsWhereWordAppears;
        this.maxTF = maxTF;
    }
    private String name;

    public int getMaxTF() {
        return maxTF;
    }

    public int getFrecuency() {
        return maxTF;
    }

    public void setMaxTF(int maxTF) {
        this.maxTF = maxTF;
    }
    private int nr;

    public int getNr() {
        return nr;
    }

    public void setNr(int totalDocumentsWhereWordAppears) {
        this.nr = totalDocumentsWhereWordAppears;
    }
    private int maxTF;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public Word(String name) {
        this.name = name;
        this.nr = -1;
        this.maxTF = -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Name: " + name + "nr: " + this.getNr() + " - Max tf: " + this.getMaxTF();
    }
}
