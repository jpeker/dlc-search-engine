package beans;

import factories.DAOFactory;
//import engine.indexer.Indexer;

/**
 * Clase que representa una palabra
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class Word implements java.io.Serializable {
    
    public Word(String name, int totalDocumentsWhereWordAppears, int maxTF) {
        this.name = name;
        this.nr = totalDocumentsWhereWordAppears;
        this.maxTF = maxTF;
    }
    private String name;

    public int getMaxTF() {
        return maxTF;
    }

    public void setMaxTF(int maxTF) {
        this.maxTF = maxTF;
    }

 
    private int nr;

    public int getNr() {
        return nr;
    }

    public void setTotalDocumentsWhereWordAppears(int totalDocumentsWhereWordAppears) {
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
    public int hashCode (){
	return name.hashCode();
    }
    @Override
    public String toString(){
        return "Name: "+this.getName()+"N: "+this.getNr()+" - Max tf: "+this.getMaxTF();
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