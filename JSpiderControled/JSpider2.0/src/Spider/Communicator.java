/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class Communicator implements Runnable,ISpiderReportable{
   /* The background spider thread */
  protected Thread backgroundThread;

  /**
   * The spider object being used
   */
  protected String textToCrawl;
  protected Spider spider;

  /**
   * The URL that the spider began with
   */
  protected URL base;

   /**
   * How many bad links have been found
   */
  protected int badLinksCount = 0;

  /**
   * How many good links have been found
   */
  protected int goodLinksCount = 0;

  public void beginCrawler(String textToCrawl)throws FinishException{
     if ( backgroundThread==null ) {
      this.textToCrawl=textToCrawl;
         //backgroundThread = new Thread(this);
        //backgroundThread.start()
       this.runy() ;
        goodLinksCount=0;
        badLinksCount=0;
    }
    else {
        spider.cancel();
    }
}
    public HashMap getHashMapPages()
  {
    return spider.getHashMapPages();
  }
    public void run() {
      try {
        spider = new Spider(this);
        spider.clear();
        base = new URL(textToCrawl);
        spider.addURL(base);
        //   spider.begin();
        backgroundThread=null;
    }
      catch ( MalformedURLException e ) {
        System.out.println("error "+ e.toString());
    }


    }
 public void runy() throws FinishException {
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
 public boolean spiderFoundURL(URL base, URL url) {
    if ( !checkLink(url) ) {
        badLinksCount++;
        return false;
     }
    goodLinksCount++;
    if ( !url.getHost().equalsIgnoreCase(base.getHost()) )
      return false;
    else
      return true;
  }
  
  protected boolean checkLink(URL url)
  {
    try {
      URLConnection connection = url.openConnection();
      connection.connect();   
      return true;
    } catch ( IOException e ) {
      return false;
    }
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    public void spiderFoundURLError(URL url) {
        badLinksCount++;
        System.out.println(url +" gots errors");
        throw new UnsupportedOperationException("Not supported yet.");
    }
}