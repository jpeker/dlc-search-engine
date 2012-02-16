package com.spider.jspiderlibrary2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author altamirano,peker,liberal
 */

public class Communicator implements ISpiderReportable, Runnable {

    // protected Thread backgroundThread; //El hilo de background para el spider
    public static String textToCrawl;// variable usadas para la urls que usa el
    //crawler
    public static boolean crawl = false;
    public static boolean search = false;
    protected Spider spider;//objeto que usamos para crawlear las urls
    protected URL base;//La url base donde el spider empieza a crawlear
    protected int badLinksCount = 0;//cuantos links malos hay en el recorrido
    protected int goodLinksCount = 0;//Cuantos links buens hay en el recorrido
    public static HashMap pages;//HashMap con todas las urls y su contenido util
    public static int totalLinks;// variable usadas para la urls que usa el
    public static ArrayList linksprocessed = new ArrayList();
    public static ArrayList linkserror = new ArrayList();

    private HashMap getHashMapPages() {
        return spider.getHashMapPages();
    }

    public void run() {
        if (crawl) {
            Communicator.totalLinks = 0;
            this.initSpider();
            linksprocessed = this.spider.workloadProcessed;
            linkserror = this.spider.workloadError;
            pages = getHashMapPages();
            crawl = false;
//            synchronized (this) {
//                try {
//                    this.wait();
//                } catch (InterruptedException ex) {
//                }
//            }
        }
    }
    /*Metodo que da inicio el spider, crea la url ,
     *verifica que este bien formado si no tira una excepcion
     */

    public void initSpider() {
        try {
            spider = new Spider(this);// Creo el objeto spider
            spider.clear();
            base = new URL(textToCrawl);
            //creo la url con el link en forma de string
            spider.addURL(base); //agrego la url como base
            spider.begin();//comienza a crawlear
        } catch (MalformedURLException e) {
            System.out.println("error communicator2  " + e.toString());
        }
    }
    /*
     * es llamado por el spyder cuando encuentra la url,
     * verifica si es buena y luego valida si pertene al mismo dominio
     * que la base
     * recibe la base y una url
     * devuelve true si pertence al mismo domnio
     */

    @Override
    public boolean spiderFoundURL(URL base, URL url) {
        if (!checkLink(url)) { //verifica que sea buena la url
            badLinksCount++;
            return false;
        }
        goodLinksCount++;
        if (!url.getHost().equalsIgnoreCase(base.getHost())) //valida que sea del mismo dominio que la base
        {
            return false;
        } else {
            return true;
        }
    }
    /*
     * Checkea que link sea bueno
     * recibe una url
     * devuelve true si es bueno el link
     */

    protected boolean checkLink(URL url) {
        try {
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    /*
     * Es llamado cuando hay un error en el link
     * recibe una url
     */

    @Override
    public void spiderFoundURLError(URL url) {
        badLinksCount++;
    }

    public static ArrayList SearchEngineContentToUi(ArrayList<String> listOfLinks) {
        return listOfLinks;
    }
}
