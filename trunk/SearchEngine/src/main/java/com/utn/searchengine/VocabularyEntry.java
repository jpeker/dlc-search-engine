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

    public int getTotalDocumentsThatWordAppears() {
        return totalDocumentsWhereWordAppears;
    }

    public void setTotalDocumentsThatWordAppears(int totalTimesThatWordAppears) {
        this.totalDocumentsWhereWordAppears = totalTimesThatWordAppears;
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
        this.totalDocumentsWhereWordAppears = 0;
    }

    public VocabularyEntry(Word word, int totalDocumentsWhereWordAppears, int DocumentBiggestTermFrecuency) {
        this.word = word;
        this.totalDocumentsWhereWordAppears = totalDocumentsWhereWordAppears;
        this.DocumentBiggestTermFrecuency = DocumentBiggestTermFrecuency;
    }
    private Word word;
    private int totalDocumentsWhereWordAppears;
    private int DocumentBiggestTermFrecuency;
    
    @Override
    public int hashCode (){
	return word.hashCode();
    }
    @Override
    public String toString(){
        String aux = "Word: "+word.toString()+", Biggest Term Frecuency: "+ this.DocumentBiggestTermFrecuency+", Total times that word Appears: "+this.totalDocumentsWhereWordAppears;
        return aux;
    }
    
}
