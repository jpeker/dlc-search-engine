package beans;

import factories.DAOFactory;
//import engine.indexer.Indexer;

/**
 * Clase que representa una palabra
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class Palabra implements java.io.Serializable {
    
    /**
     * Es la palabra en si misma
     */
    private String palabra;
    /**
     * NR representa la cantidad de documentos en los que esta presente esta palabra
     */
    private long nr;

    public Palabra()
    {
        this.palabra="";
        this.nr=0;
    }
    
    public Palabra(String palabra) {
        this.palabra = palabra;
        this.nr=0;
    }

    public Palabra(String palabra, long nr) {
        this.palabra = palabra;
        this.nr = nr;
    }   

    public long getNr() {
        return nr;
    }

    public void setNr(long nr) {
        this.nr = nr;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    
    public boolean esStopWord()
    {
        //Palabra pal = DAOFactory.getActiveDAOFactory().getPalabraDAO().obtenerPalabra(this);
        // ver mañana
        //long cantWebSites = Indexer.getInstancia().getCantWebSitesIndexadas();
        //long nrMax = (long) (cantWebSites * Setting.getInstancia().getStopWords());
        //if(pal.nr >= nrMax)
        //{
        //    return true;
        //}
        return false;
    }


}