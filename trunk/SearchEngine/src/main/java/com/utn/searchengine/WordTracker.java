/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;


/**
 * This Class is created in order to take atrack of the info of the
 * words, it will consist of a location and a frecuency
 * @TODO: check the way to order de locations and times in descendant order by amount of times.
 * @author aaltamir
 */
public class WordTracker {

    public WordTracker(int frequency, String location) {
        this.frequency = frequency;
        this.location = location;
    }


    public WordTracker ()
    {

    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString(){
        String aux = "Location: "+location+"- Frecuency: "+frequency;
        return aux;
    }
    private int frequency;
    private String location;
    
}
