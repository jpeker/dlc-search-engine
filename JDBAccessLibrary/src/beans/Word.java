package beans;

/**
 * Clase que representa una palabra
 * 
 * @author altamirano,peker,liberal
 */
public class Word implements java.io.Serializable {
   
    public Word(String name, int totalDocumentsWhereWordAppears, int maxTF) {
        this.name = name;
        this.nr = totalDocumentsWhereWordAppears;
        this.maxTF = maxTF;
    }
    private String name;
    private int nr;
    private int maxTF;
    
    public int getMaxTF() {
        return maxTF;
    }

    public void setMaxTF(int maxTF) {
        this.maxTF = maxTF;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int totalDocumentsWhereWordAppears) {
        this.nr = totalDocumentsWhereWordAppears;
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
        return "Name: "+name+"N: "+this.getNr()+" - Max tf: "+this.getMaxTF();
    }
}