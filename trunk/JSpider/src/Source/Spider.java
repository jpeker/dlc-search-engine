package Source;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

/**
 * That class implements a reusable spider that retrieves all possible and 
 * usefull text from an url.
 * 
 * @author Andres Altamirano
 * @version 1.1
 */
public class Spider {

  /**
   * A collection of URLs that resulted in an error
   */
  protected Collection workloadError = new ArrayList(3);

  /**
   * A collection of URLs that are waiting to be processed
   */
  protected Collection workloadWaiting = new ArrayList(3);

  /**
   * A collection of URLs that were processed
   */
  protected Collection workloadProcessed = new ArrayList(3);
  
  /**
   * A collection of URLs that will not be used because they dont have significant text
   */
  private Collection workloadUnused = new ArrayList(3);

  /**
   * The class that the spider should report its URLs to
   */
  protected ISpiderReportable report;

  /**
   * A flag that indicates whether this process
   * should be canceled
   */
  protected boolean cancel = false;

  /**
   * The constructor
   * 
   * @param report A class that implements the ISpiderReportable
   * interface, that will receive information that the
   * spider finds.
   */
  public Spider(ISpiderReportable report)
  {
    this.report = report;
  }

  /**
   * Get the URLs that resulted in an error.
   * 
   * @return A collection of URL's.
   */
  public Collection getWorkloadError()
  {
    return workloadError;
  }

  /**
   * Get the URLs that were waiting to be processed.
   * You should add one URL to this collection to
   * begin the spider.
   * 
   * @return A collection of URLs.
   */
  public Collection getWorkloadWaiting()
  {
    return workloadWaiting;
  }

  /**
   * Get the URLs that were processed by this spider.
   * 
   * @return A collection of URLs.
   */
  public Collection getWorkloadProcessed()
  {
    return workloadProcessed;
  }    

  /**
   * Clear all of the workloads.
   */
  public void clear()
  {
    getWorkloadError().clear();
    getWorkloadWaiting().clear();
    getWorkloadProcessed().clear();
    getWorkloadUnused().clear();
  }

  /**
   * Set a flag that will cause the begin
   * method to return before it is done.
   */
  public void cancel()
  {
    cancel = true;
  }

  /**
   * Add a URL for processing.
   * 
   * @param url
   */
  public void addURL(URL url)
  {
  
  if ( url.toString().endsWith("/"))
  {
     String pepe = url.toString().substring(0, url.toString().length()-1);
     
    try{
      url = new URL(pepe);} catch ( MalformedURLException e ) {
        log("Found malformed URL: " + pepe );
      }
  }
    if ( getWorkloadWaiting().contains(url) )
      return;
    if ( getWorkloadError().contains(url) )
      return;
    if ( getWorkloadProcessed().contains(url) )
      return;
    if(!isTextPage(url))
        return;
     
  
     
    log("Adding to workload: " + url );
    getWorkloadWaiting().add(url);
  }

  /**
   * Checkes if an URL is of text/html format
   * @param url the url to check
   * @return true if the url is of type text(text/css files are not acepted).
   */
  private boolean isTextPage(URLConnection connection)
  {
      if(connection ==null)
          return false;
      
      if ( (connection.getContentType()!=null) && (!connection.getContentType().toLowerCase().startsWith("text/")
              || (connection.getContentType().endsWith("css"))   )  )
      {
          return false;
      }
      return true;
  }
  private boolean isTextPage(URL url)
  {
      if(url == null ||workloadUnused.contains(url))
          return false;
      try
      {
        URLConnection connection = url.openConnection();
        return isTextPage(connection);
      }
      catch(IOException e)
      {
          System.out.println("Error processing url, "+e.getMessage());
      }
      return false;
  }
  
  
  /**
   * Called internally to process a URL
   * 
   * @param url The URL to be processed.
   */
  public void processURL(URL url)
  {
    try {
      log("Processing: " + url );
      RobotsParser.robotSafe(url); 
      // get the URL's contents
      URLConnection connection = url.openConnection();
      
      if(!isTextPage(connection)) {  
      getWorkloadWaiting().remove(url);
        getWorkloadProcessed().add(url);
        log("Not processing because content type is: " +
             connection.getContentType() );
        return;
      }

      // read the URL
      InputStream is = connection.getInputStream();//change to inputstream
      Reader r = new InputStreamReader(is);//change to reader
      // parse the URL
      HTMLEditorKit.Parser parse = new HTMLParse().getParser();
      parse.parse(r,new Parser(url),true);
    } catch ( IOException e ) {
      getWorkloadWaiting().remove(url);
      getWorkloadError().add(url);
      log("Error: " + url );
      report.spiderFoundURLError(url);
      
      return;
    }
    // mark URL as complete
    getWorkloadWaiting().remove(url);
    getWorkloadProcessed().add(url);
    log("Complete: " + url );
      System.out.println("EL tamaño de waiting" + getWorkloadWaiting().size() + "EL tamaño de processesed" + getWorkloadProcessed().size() );
  }

  /**
   * Called to start the spider
   */
  public void begin()
  {
    cancel = false;
    while ( !getWorkloadWaiting().isEmpty() && !cancel ) {
      Object list[] = getWorkloadWaiting().toArray(); //Chanchada
      for ( int i=0;(i<list.length)&&!cancel;i++ )
        processURL((URL)list[i]);
    }
  }

    /**
     * @return the workLoadUnused
     */
    public Collection getWorkloadUnused() {
        return workloadUnused;
    }

/**
 * A HTML parser callback used by this class to detect links
 * 
 * @author Jeff Heaton
 * @version 1.0
 */
  protected class Parser
  extends HTMLEditorKit.ParserCallback {
    protected URL base;
    
    private boolean isUndesiredTag = false;

    public Parser(URL base)
    {
      this.base = base;
    }
    
    /**
     * This method checks if the analized tag is undesired comparing it on a list
     * of undesired tags. If it is undesirable, it changes an external flag to
     * notify to handleText that the tag is not useful.
     * @param t a HTML.Tag
     */
    public void tagHandler(HTML.Tag t)
    {
        Collection <String> undesiredTags = new ArrayList <String> (); //create collection in another place
        undesiredTags.add(HTML.Tag.OPTION.toString());
        undesiredTags.add(HTML.Tag.LI.toString());
        undesiredTags.add(HTML.Tag.SCRIPT.toString());
        
        undesiredTags.add(HTML.Tag.STYLE.toString());
        undesiredTags.add(HTML.Tag.CODE.toString());


        for(String tag : undesiredTags)
        {
            if(tag.equalsIgnoreCase(t.toString()))
            {
                isUndesiredTag = true;
                break;
            }
        }
        return;
        
    }
    //recorre  la url en busca de links a otras pag con href
        @Override
       public void handleSimpleTag(HTML.Tag t,
                                MutableAttributeSet atributeSet,int pos)
    {
        System.out.println("the tag to analize is: "+t.toString());
        tagHandler(t);
      String href = (String)atributeSet.getAttribute(HTML.Attribute.HREF);

      if( (href==null) && (t==HTML.Tag.FRAME) )
        href = (String)atributeSet.getAttribute(HTML.Attribute.SRC);
      // busca href dentro contenedor de framset   http://www.w3schools.com/tags/tag_frame.asp
      if ( href==null )
        return;
//wtf?
      int i = href.indexOf('#');
      if ( i!=-1 )
        href = href.substring(0,i);
       
      int j = href.indexOf(".php");
      if ( j!=-1 )
      {  System.out.println("es un php");
      return;
      }
      if ( href.toLowerCase().startsWith("mailto:") ) {
        report.spiderFoundEMail(href);
        return;
      }
      /*if(!href.equals("")){
     char p = href.toString().charAt(href.toString().length()-1);
  char pe='/';
  if ( p ==pe)
  {

      System.out.println("son iguales");
      return;
  }
      }*/
      handleLink(base,href);
    }

        @Override
    public void handleStartTag(HTML.Tag t,
                               MutableAttributeSet a,int pos)
    {
      handleSimpleTag(t,a,pos);    // handle the same way

    }
    /**
         * 
         * @param data gots the tag text
         * @param pos 
         */
        @Override
    public void handleText(char[] data, int pos) {
        if(isUndesiredTag == true)
        {
            isUndesiredTag = false;
            return;
        }
        if(String.valueOf(data).trim().isEmpty())
        {
            System.out.println("no text");
            return;
        }
        System.out.println(String.valueOf(data));
       
        
    }

    protected void handleLink(URL base,String str)
    {
      try {
        URL url = new URL(base,str);
        //si encuentra una url la agrega a un contenedor de url, verifica la url q crea y sino te tira un
        //exception
        if ( report.spiderFoundURL(base,url) )
            
          addURL(url);
      } catch ( MalformedURLException e ) {
        log("Found malformed URL: " + str );
      }
    }
  }

  /**
   * Called internally to log information
   * This basic method just writes the log
   * out to the stdout.
   * 
   * @param entry The information to be written to the log.
   */
  public void log(String entry)
  {
    System.out.println( (new Date()) + ":" + entry );
  }
}