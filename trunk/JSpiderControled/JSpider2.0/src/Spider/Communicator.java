package Spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Communicator implements Runnable,ISpiderReportable{

  protected Thread backgroundThread; //El hilo de background para el spider
  protected String textToCrawl;// variable usadas para la urls que usa el
  //crawler
  protected Spider spider;//objeto que usamos para crawlear las urls
  protected URL base;//La url base donde el spider empieza a crawlear
  protected int badLinksCount = 0;//cuantos links malos hay en el recorrido
  protected int goodLinksCount = 0;//Cuantos links buens hay en el recorrido
  private HashMap pages;//HashMap con todas las urls y su contenido util

  /* metodo que recibe un link como string, se utiliza para dar
   * comienzo el proceso del crawler
   * y devuelve el HashMap con las urls y su contenidos
   */
  public HashMap beginCrawler(String textToCrawl){
     if ( backgroundThread==null ) {
        this.textToCrawl=textToCrawl;
        this.initSpider();
        goodLinksCount=0;
        badLinksCount=0;
        return getHashMapPages();
    }
    else {
         pages=null;
         spider.cancel();
         return pages;
        }
    }
  public HashMap getHashMapPages()
  {
    return spider.getHashMapPages();
  }

  public void run()  {
      try {
        spider = new Spider(this);
        spider.clear();
        base = new URL(textToCrawl);
        spider.addURL(base);
          spider.begin();
          
        backgroundThread=null;
    }
      catch ( MalformedURLException e ) {
        System.out.println("error "+ e.toString());
    }
    }

    /*Metodo que da inicio el spider, crea la url ,
     *verifica que este bien formado si no tira una excepcion
     */
  public void initSpider()  {
      try {
        spider = new Spider(this);// Creo el objeto spider
        spider.clear();
        base = new URL(textToCrawl);
        //creo la url con el link en forma de string
        spider.addURL(base); //agrego la url como base
        spider.begin();//comienza a crawlear
         }
      catch ( MalformedURLException e ) {
        System.out.println("error "+ e.toString());
        }
}
  /*
   * es llamado por el spyder cuando encuentra la url,
   * verifica si es buena y luego valida si pertene al mismo dominio
   * que la base
   * recibe la base y una url
   * devuelve true si pertence al mismo domnio
   */
 public boolean spiderFoundURL(URL base, URL url) {
    if ( !checkLink(url) ) { //verifica que sea buena la url
        badLinksCount++;
        return false;
     }
    goodLinksCount++;
    if ( !url.getHost().equalsIgnoreCase(base.getHost()) )
        //valida que sea del mismo dominio que la base
      return false;
    else
      return true;
  }
  /*
   * Checkea que link sea bueno
   * recibe una url
   * devuelve true si es bueno el link
   */
  protected boolean checkLink(URL url)
  {
    try {
      URLConnection connection = url.openConnection();
      connection.connect();   
      return true;
    } catch ( IOException e ) {
      return false;
    }
  }
  /*
   * Es llamado cuando hay un error en el link
   * recibe una url
   */
    public void spiderFoundURLError(URL url) {
        badLinksCount++;
        System.out.println(url +" gots errors");
    }
}