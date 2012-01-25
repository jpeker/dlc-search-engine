/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

/**
 * An entry that will be used by the vocabulary.
 * It consists of a word and some other usefull data
 * that the Vocabulary needs to know.
 * @author aaltamir
 */
public class VocabularyEntry {

    public int getDocumentBiggestTermFrecuency() {
        return DocumentBiggestTermFrecuency;
    }

    public void setDocumentBiggestTermFrecuency(int DocumentBiggestTermFrecuency) {
        this.DocumentBiggestTermFrecuency = DocumentBiggestTermFrecuency;
    }

    public int getTotalTimesThatWordAppears() {
        return totalTimesThatWordAppears;
    }

    public void setTotalTimesThatWordAppears(int totalTimesThatWordAppears) {
        this.totalTimesThatWordAppears = totalTimesThatWordAppears;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public VocabularyEntry(Word word) {
        this.word = word;
        this.DocumentBiggestTermFrecuency =0;
        this.totalTimesThatWordAppears = 0;
    }

    public VocabularyEntry(Word word, int totalTimesThatWordAppears, int DocumentBiggestTermFrecuency) {
        this.word = word;
        this.totalTimesThatWordAppears = totalTimesThatWordAppears;
        this.DocumentBiggestTermFrecuency = DocumentBiggestTermFrecuency;
    }
    private Word word;
    private int totalTimesThatWordAppears;
    private int DocumentBiggestTermFrecuency;
    
    @Override
    public int hashCode (){
	return word.hashCode();
    }
    @Override
    public String toString(){
        String aux = "Word: "+word.toString()+", Biggest Term Frecuency: "+ this.DocumentBiggestTermFrecuency+", Total times that word Appears: "+this.totalTimesThatWordAppears;
        return aux;
    }
    
}
