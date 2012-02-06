package beans;

import factories.DAOFactory;
import java.util.Hashtable;

/**
 * 
 * Clase que representa a una website
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class WebSite implements java.io.Serializable {
    
    /**
     * Título de la website
     */
    private String titulo;
    /**
     * URL de la website
     */
    private String url;
    /**
     * Estado de la website. TRUE significa ONLINE y FALSE OFFLINE
     */
    private boolean estado;
    /**
     * Timestamp del último escaneo realizado a la website. Tomado de
     * los milisegundos del sistema
     */
    private long timeStamp;
    /**
     * Indica si la website es BASE y debe ser analizada.
     */
    private boolean esBase;
    
    private Hashtable<Word, Long> palabras;

    public WebSite() {}

    public WebSite(String titulo, String url, boolean estado, long timeStamp, boolean esBase) {
        this.titulo = titulo;
        this.url = url;
        this.estado = estado;
        this.timeStamp = timeStamp;
        this.esBase = esBase;
        this.palabras=new Hashtable<Word, Long>();
    }
    public WebSite(String url) {
        this.url = url;
        this.titulo = "";
        this.estado = false;
        this.timeStamp = 0;
        this.esBase = false;
    }   

    public boolean isEsBase() {
        return esBase;
    }

    public void setEsBase(boolean esBase) {
        this.esBase = esBase;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Hashtable<Word, Long> getPalabras() {
        //Lazy Load
        if(this.palabras==null)
        {
            this.palabras =
                    DAOFactory.getActiveDAOFactory()
                    .getWebSiteDAO().obtenerPalabrasDeWebSite(this);
        }
        return palabras;
    }

    public void setPalabras(Hashtable<Word, Long> palabras) {
        this.palabras = palabras;
    }

    public long getTimeStamp(){
        return timeStamp;
    }
 
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}