/*
 * JSpider20App.java
 */

package jspider20;
import Spider.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class JSpider20App extends SingleFrameApplication implements ISpiderReportable {

      /**
   * The background spider thread
   */
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

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new JSpider20View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of JSpider20App
     */
    public static JSpider20App getApplication() {
        return Application.getInstance(JSpider20App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(JSpider20App.class, args);
    }

    public boolean spiderFoundURL(URL base,URL url)
    {

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
  }
      
  public void spiderFoundURLError(URL url)  throws UnsupportedOperationException{
        badLinksCount++;
        System.out.println(url +" gots errors");
        //throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void setTextSearch(String textBoxText)
  {
  textToCrawl=textBoxText;
  }


  public void runy()
  {
    try {
      //errors.setText("");
      spider = new Spider(this);
      spider.clear();
      base = new URL(textToCrawl);
      spider.addURL(base);
      spider.begin();
      //begin.setText("Begin");
       //spider =null;
     // backgroundThread=null;

    } catch ( MalformedURLException e ) {
        System.out.println("error "+ e.toString());

    }
  }


    //public void spiderFoundURLError(URL url){return;}
    public void spiderFoundEMail(String email){return;}

}
