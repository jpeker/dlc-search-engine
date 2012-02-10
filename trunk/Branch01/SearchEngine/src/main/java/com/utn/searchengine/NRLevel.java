package engine.searcher;

import dal.beans.Palabra;

/**
 * Representa un nivel NR
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class NRLevel implements Comparable {
    
    private long level;
    
    private long cantPalabras;
    
    private long frecuenciaAcumulada;
    
    private long presenciaEnLinkAcumulada;
    private long presenciaEnTituloAcumulada;
    
    public NRLevel()
    {
    }
    
    public NRLevel(long level)
    {
        this.level = level;
    }
    
    public void addPalabra(Palabra palabra, long frecuencia, boolean presenciaEnLink, boolean presenciaEnTitulo)
    {
        if(palabra.getNr()==level)
        {
            cantPalabras++;
            frecuenciaAcumulada += frecuencia;
            if(presenciaEnLink){presenciaEnLinkAcumulada++;}
            if(presenciaEnTitulo){presenciaEnTituloAcumulada++;}
        }
    }

    public long getCantPalabras() {
        return cantPalabras;
    }

    public void setCantPalabras(long cantPalabras) {
        this.cantPalabras = cantPalabras;
    }

    public long getFrecuenciaAcumulada() {
        return frecuenciaAcumulada;
    }

    public void setFrecuenciaAcumulada(long frecuenciaAcumulada) {
        this.frecuenciaAcumulada = frecuenciaAcumulada;
    }
    
    public long getPresenciaEnLinkAcumulada() {
        return presenciaEnLinkAcumulada;
    }

    public void setPresenciaEnLinkAcumulada(long presenciaEnLinkAcumulada) {
        this.presenciaEnLinkAcumulada = presenciaEnLinkAcumulada;
    }

    public long getPresenciaEnTituloAcumulada() {
        return presenciaEnTituloAcumulada;
    }

    public void setPresenciaEnTituloAcumulada(long presenciaEnTituloAcumulada) {
        this.presenciaEnTituloAcumulada = presenciaEnTituloAcumulada;
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