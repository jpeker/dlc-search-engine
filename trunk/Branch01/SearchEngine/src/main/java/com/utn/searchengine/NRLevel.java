package com.utn.searchengine;


/**
 * Representa un nivel NR
 * 
 * @author Altamirano,Liberal,Peker
 */
public class NRLevel implements Comparable {
    
    private long level;
    
    private long numberOfWords;
    
    private long totalFreq;
    
    
    public NRLevel()
    {
    }
    
    public NRLevel(long level)
    {
        this.level = level;
    }
    
    public void addWord(Word word, long freq)
    {
        if(word.getNr()==level)
        {
            numberOfWords++;
            totalFreq += freq;
        }
    }

    public long getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(long numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public long getTotalFreq() {
        return totalFreq;
    }

    public void setTotalFreq(long frecuenciaAcumulada) {
        this.totalFreq = frecuenciaAcumulada;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NRLevel other = (NRLevel) obj;
        if (this.level != other.level) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.level ^ (this.level >>> 32));
        return hash;
    }

    public int compareTo(Object o) {
       if(o==null){return 1;}
       if(o.getClass() != this.getClass()){return 1;}
       long otherNRLevel = ((NRLevel)o).level;
       if(this.level < otherNRLevel)
       {
           return -1;
       }
       else
       {
           if(this.level > otherNRLevel)
           {
               return 1;
           }
       }
       return 0;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }   
}