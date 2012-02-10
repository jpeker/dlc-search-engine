package com.utn.searchengine;

//import dal.beans.WebSite;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * Clase utilizada para realizar las comparaciones durante el ordenamiento de 
 * resultados para una búsqueda.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class ComparadorSearchResult implements Comparator {
    
    private enum TipoDeComparacion{
        NR,
        FRECUENCIA,
        LINK,
        TITULO
    };  
    
    private WebSite siteO1;
    private WebSite siteO2;
    
    private LinkedList<NRLevel> nivelesNRO1;
    private LinkedList<NRLevel> nivelesNRO2;    

    /**
     * Compara dos objetos
     * @param o1 objeto 1 a comparar
     * @param o2 objeto 2 a comparar
     * @return retorna un entero negativo, cero, o un entero positivo  si el primer
     * argumento es menor, igual o mayor que el segundo argumento.
     */
    public int compare(Object o1, Object o2) {
        
        if((o1.getClass() != SearchResult.class) || (o2.getClass()!=SearchResult.class)){return -1;}
        
        if(o1==null && o2==null){return 0;}
        if(o1==null && o2!=null){return -1;}
        if(o1!=null && o2==null){return 1;}
               
        int ret = 0;
        
        //Obtenemos las websites de los SearchResult
        this.siteO1 = ((SearchResult)o1).getWebsite();
        this.siteO2 = ((SearchResult)o2).getWebsite();
        
        //Obtenemos las palabras de cada SearchResult
        this.nivelesNRO1 = new LinkedList<NRLevel>(((SearchResult)o1).getNivelesNR().values());
        Collections.sort(nivelesNRO1);
        
        this.nivelesNRO2 = new LinkedList<NRLevel>(((SearchResult)o2).getNivelesNR().values());
        Collections.sort(nivelesNRO2);
        
        ret = compararPorNR();
        
        if(ret == 0)
        {
            ret = compararPorFrecuencia();
            
            if(ret == 0)
            {
                ret = compararPorPresenciaEnLink(); 
                
                if(ret == 0)
                {
                    ret = compararPorPresenciaEnTitulo();
                }
            }
        }
        
        return ret;
    }
    
    private int compararPorNR()
    {
        return algoritmoStandard(TipoDeComparacion.NR);
    }
    
    private int compararPorFrecuencia()
    {
        return algoritmoStandard(TipoDeComparacion.FRECUENCIA);
    }
    
    private int compararPorPresenciaEnLink()
    {
        return algoritmoStandard(TipoDeComparacion.LINK);
    }
    
    private int compararPorPresenciaEnTitulo()
    {
        return algoritmoStandard(TipoDeComparacion.TITULO);
    }
    
    private boolean compararCondicionPor(NRLevel o1, NRLevel o2, TipoDeComparacion comparacion)
    {
       boolean ret = false;
       switch(comparacion)
       {
           case NR:
           {
               if((o1.getLevel() == o2.getLevel()) && (o1.getCantPalabras() == o2.getCantPalabras()))
               {
                   ret = true;
               }
               break;
           }
           case FRECUENCIA:
           {
               if(o1.getFrecuenciaAcumulada()==o2.getFrecuenciaAcumulada())
               {
                   ret = true;
               }
               break;
           }
           case LINK:
           {
               if(o1.getPresenciaEnLinkAcumulada() == o2.getPresenciaEnLinkAcumulada())
               {
                   ret = true;
               }
               break;               
           }
           case TITULO:
           {
               if(o1.getPresenciaEnTituloAcumulada() == o2.getPresenciaEnTituloAcumulada())
               {
                   ret = true;
               }
               break;
           }
           default:
           {
               break;
           }
       }
       
       return ret;
    }
    
    private int algoritmoStandard(TipoDeComparacion comparacion)
    {
        int ret = 0;
        
        int i = 0;
        
        //Obtenemos las palabras con menos NR
        NRLevel nrLevelO1 = this.getProximoMenorNRLevel(i, nivelesNRO1);
        NRLevel nrLevelO2 = this.getProximoMenorNRLevel(i, nivelesNRO2);
                
        while(nrLevelO1!=null && nrLevelO2!=null && compararCondicionPor(nrLevelO1, nrLevelO2, comparacion))
        {
            i++;
            
            nrLevelO1 = this.getProximoMenorNRLevel(i, nivelesNRO1);
            nrLevelO2 = this.getProximoMenorNRLevel(i, nivelesNRO2);
        }
        
        if(nrLevelO1!=null)
        {
           if(nrLevelO2==null)
           {
             //Entonces O2 no tenia mas, gana O1
             ret = -1;
           }
           else
           {
              ret = compararPor(nrLevelO1, nrLevelO2, comparacion);
           }
        }
        else
        {
            if(nrLevelO2==null)
            {
                //Empataron en todo
                ret = 0;
            }
            else
            {
                //Entonces O1 no tenia mas, gana O2
                ret =  1;
            }
        }
        
        return ret;
    }
    
    private int compararPor(NRLevel o1, NRLevel o2, TipoDeComparacion comparacion)
    {
        int ret = 0;
        
        switch(comparacion)
        {
           case NR:
           {
               if(o1.getLevel() < o2.getLevel())
               {
                   return -1;
               }
               else
               {
                   if(o1.getLevel() > o2.getLevel())
                   {
                       return 1;
                   }
                   else
                   {
                       //Son iguales en NIVEL. Desempatar por cantidad de palabras en la banda.
                       if(o1.getCantPalabras()>o2.getCantPalabras())
                       {
                           ret = -1;
                       }
                       else
                       {
                           if(o1.getCantPalabras()<o2.getCantPalabras())
                           {
                               ret = 1;
                           }
                           else
                           {
                               //Tienen el mismo nivel y la misma cantidad de palabras.
                               //Se necesita desempatar por otro criterio.
                           }
                       }
                    }
               }             
               break;
           }
           case FRECUENCIA:
           {
               if(o1.getFrecuenciaAcumulada()>o2.getFrecuenciaAcumulada())
               {
                   ret = -1;
               }
               else
               {
                   if(o1.getFrecuenciaAcumulada()<o2.getFrecuenciaAcumulada())
                   {
                       ret = 1;                       
                   }
               }
               break;
           }
           case LINK:
           {
               if(o1.getPresenciaEnLinkAcumulada() > o2.getPresenciaEnLinkAcumulada())
               {
                   ret = -1;
               }
               else
               {
                  if(o1.getPresenciaEnLinkAcumulada() < o2.getPresenciaEnLinkAcumulada())
                  {
                      ret = 1;
                  }                  
               }
               break;               
           }
           case TITULO:
           {
               if(o1.getPresenciaEnTituloAcumulada() > o2.getPresenciaEnTituloAcumulada())
               {
                   ret = -1;
               }
               else
               {
                  if(o1.getPresenciaEnTituloAcumulada() < o2.getPresenciaEnTituloAcumulada())
                  {
                      ret = 1;
                  }                  
               }
               break; 
               
           }
           default:
           {
               break;
           }
        }
        
        return ret;
    }
    
    private NRLevel getProximoMenorNRLevel(int i, LinkedList<NRLevel> nrLevels )
    {
        NRLevel ret = null;
        try
        {
            ret = nrLevels.get(i);
        }
        catch(IndexOutOfBoundsException ex)
        {
            ret = null;
        }
        return ret;
    }
}