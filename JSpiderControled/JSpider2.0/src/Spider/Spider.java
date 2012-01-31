package Spider;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.util.regex.Pattern;

/**
 *
 * Clase spider empleada para crawlear todo el sitio web, a travez de una url
 * base sin salir de corriente dominio, y con la capacidad de retornar,
 * el texto html leido de cada url del sitio que se encuentra dentro de la base
 * 
 */
public class Spider {
    private final static Pattern BOTTOMFILTERS =
            Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
            + "|png|tiff?|mid|mp2|mp3|JPG|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz|xml|php\\?rsd))$");
    private final static Pattern MIDDLEFILTERS =
                                            Pattern.compile(".*(rss|css).*");
  /**
   * Una Coleccion de URLs que resultan erroneas
   */
  protected Collection workloadError = new ArrayList(3);

  /**
   * Una Coleccion de URLs que estan esperando para ser procesadas
   * Cada URL añadida sirve para iniciar el crawleo del sitio
   */
  protected Collection workloadWaiting = new ArrayList(3);

  /**
   * StringBuilder empleado para concatenar el texto de cada url
   * del sitio crawleado
   */

  private StringBuilder SBConcatText = new StringBuilder();

  /**
   * HashMap que se entrega
   * Conteniendo la url con
   */

  private HashMap HashMapPage = new HashMap();

  /**
   * Un hash map que añade URL que estan ya procesadas con su texto
   * La key es la url y el value es el string builder con todo el texto
   */
  protected Collection workloadProcessed = new ArrayList(3);

  /**
   * Una Coleccion de URLs que no seran usadas porque
   * no emplean texto significativo
   */
   //private Collection workloadUnused = new ArrayList(3);

  /**
   * The class that the spider should report its URLs to
   */
  protected ISpiderReportable report;

  /**
   * Flags que indican cuando el proceso del crawler debe
   * ser cancelado
   */
  protected boolean cancel = false;

  private boolean permisible = false;

  /**
   *
   * @param report Es para aquella clase
   * que implemente la interface 
   * ISpiderReportable, y dicha clase va a recibir la informacion
   * que el spider encuentre
   */
  public Spider(ISpiderReportable report)
  {
    this.report = report;
  }

  /**
   * Trae la coleccion de URL con errores.
   *
   * @return una colleccion de URLs.
   */
  public Collection getWorkloadError()
  {
    return workloadError;
  }

  /**
   * Traen las URLS que estan esperando a ser procesadas
   * @return una colleccion de URLs.
   */
  public Collection getWorkloadWaiting()
  {
    return workloadWaiting;
  }

  /**
   * Traen las URLS que estan ya procesadas
   * @return una colleccion de URLs.
   */
  public Collection getWorkloadProcessed()
  {
    return workloadProcessed;
  }

  /**
   * Borra todas las colecciones.
   */
  public void clear()
  {
    getWorkloadError().clear();
    getWorkloadWaiting().clear();
    getWorkloadProcessed().clear();
    //getWorkloadUnused().clear();
    clearHashMapPages();

  }

  /**
   * Setea la flag que genera que el metodo begin
   * retorne al ser terminado.
   */
  public void cancel()
  {
    cancel = true;
  }

  /**
   * Añado una URL a procesar.
   *
   * @param url
   */
  public void addURL(URL url)
  {

  if ( url.toString().endsWith("/"))
  {
     String splitslash = url.toString().substring(0, url.toString().length()-1);
     //Quito el Slash al final del url si existiera
    try{
      url = new URL(splitslash);}
    //A partir de esa string creo otra url nueva sin la barra para evitar
    //ambiguedades
    catch ( MalformedURLException e ) {
        log("Found malformed URL: " + splitslash );
      }
  }
  //Si ya esta presente la url en alguna de las 3 listas no la empleo mas
    if ( getWorkloadWaiting().contains(url) )
      return;
    if ( getWorkloadError().contains(url) )
      return;
    if ( getWorkloadProcessed().contains(url) )
      return;
    if(!isTextPage(url))
        return;
    log("Adding to workload: " + url );
    //Sino la añado al cola de espera
    getWorkloadWaiting().add(url);
  }

  /**
   * Chekea si la url contiene texto de html
   * @param url la url que se va a verificar
   * @return 
   */
  private boolean isTextPage(URLConnection connection)
  {
      if(connection ==null)
          return false;
//     if ( (connection.getContentType()!=null)
//              && !connection.getContentType().toLowerCase().startsWith("text/")
//              || (connection.getContentType().endsWith("css")))
//              //|| !connection.getContentType().toLowerCase().startsWith("application/")
//              //|| (connection.getContentType().endsWith("rss+xml"))  )
//      {
//          return false;
//      }
//     if ( (connection.getContentType()!=null)
//              && !connection.getContentType().toLowerCase().startsWith("application/")
//              && (connection.getContentType().endsWith("rss+xml")))
//      {
//          return false;
//      }
      return true;
  }
  private boolean isTextPage(URL url)
  {
     // if(url == null ||workloadUnused.contains(url))
     //     return false;
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
   * Procesa una URL
   * @param url la url que va a ser procesada
   */
  public void processURL(URL url)
  {
    try {
      log("Processing: " + url );
       if(! RobotsParser.robotSafe(url)) {return;}
      //verifica si la url esta en el archivo
      //robots para ver si puede entrar, sino se puede entrar
      //retorno terminando el metodo
      //Trae el contenidos de la url
      URLConnection connection = url.openConnection();
      if(!isTextPage(connection)) {
      getWorkloadWaiting().remove(url);
        getWorkloadProcessed().add(url);
        log("Not processing because content type is: " +
             connection.getContentType() );
        return;
      }
      //Creo los flujos y reader para leer y parsear la url
      InputStream inputStreamURL = connection.getInputStream();
      Reader readerURL = new InputStreamReader(inputStreamURL);
      //Parseo la URL usando el HTML parser
      HTMLEditorKit.Parser parse = new HTMLParse().getParser();
      parse.parse(readerURL,new Parser(url),true);
    }
    catch ( IOException e ) {
      //Cuando termino de leer la URL fuerzo a que se dispare una excepcion
      //E informo error en la URL
      getWorkloadWaiting().remove(url);
      getWorkloadError().add(url);
      log("Error: " + url );
      report.spiderFoundURLError(url);
      return;
    }
    //Quito la URL actual de waiting ya que fue procesada
    //Añado hash map la url actual, con todo el texto de esa url
    getWorkloadWaiting().remove(url);
    HashMapPage.put(url,SBConcatText.toString());
    getWorkloadProcessed().add(url);
    //Vacio el StringBuilder
    SBConcatText.delete(0,SBConcatText.length());
    log("Complete: " + url );
    System.out.println("EL tamaño de waiting" + getWorkloadWaiting().size() + "EL tamaño de processesed" + getWorkloadProcessed().size() );
  }

  //Traer el hashMap
  public HashMap getHashMapPages()
  {
    return HashMapPage;
  }

  //Borrar El hashMap
  public void clearHashMapPages()
  {
    HashMapPage.clear();
  }

  /**
   * Metodo que lanza el spider
   */
  public void begin() 
  {
    cancel = false;
    while ( !getWorkloadWaiting().isEmpty() && !cancel ) {
      Object list[] = getWorkloadWaiting().toArray();
      //Lo convierto en un array de object para procesar las URL
      for ( int i=0;(i<list.length)&&!cancel;i++ )
        processURL((URL)list[i]);
  

    }
  }

    /**
     * @return the workLoadUnused
     */
    //public Collection getWorkloadUnused() {
    //    return workloadUnused;
    //}

/**
 * Creo un parser de HTML en funcion de la clase HTML editor
 * empleada para parsear y detectar los links
 */
  protected class Parser
  extends HTMLEditorKit.ParserCallback {
    //URL base del sitio web donde empieza el crawleo
    protected URL base;
    //Verifico que si el tag es indeseado o no
    private boolean isUndesiredTag = false;
    public Parser(URL base)
    {
      this.base = base;
    }
    /**
     * Verifico si existen tags indeseados, es decir tags que no deben
     * tener texto comparo el tag actual con el tag indeseado, con
     * eso seteo el flag para notificarle al handleText que el tag
     * NO debe ser procesado
     * @param tag a HTML.Tag
     */
    public void tagHandler(HTML.Tag tag)
    {
        Collection <String> undesiredTags = new ArrayList <String> (); 
        //Creo una collecion de los tags indeseados
        //Y luego verifico si el tag actual esta dentro de los indeseados
        undesiredTags.add(HTML.Tag.OPTION.toString());
        undesiredTags.add(HTML.Tag.LI.toString());
        undesiredTags.add(HTML.Tag.SCRIPT.toString());
        undesiredTags.add("time");
        undesiredTags.add(HTML.Tag.STYLE.toString());
        undesiredTags.add(HTML.Tag.CODE.toString());
        undesiredTags.add("embed");
        undesiredTags.add(HTML.Tag.SPAN.toString());
        for(String tag2 : undesiredTags)
        {
            if(tag2.equalsIgnoreCase(tag.toString()))
            {
                isUndesiredTag = true;
                break;
            }
        }
        return;
    }
    
    //Recorre  la url en busca de links a otras pag con href
     @Override
     public void handleSimpleTag(HTML.Tag tag,
                                MutableAttributeSet atributeSet,int pos)
    {
      System.out.println("the tag to analize is: "+tag.toString());
      tagHandler(tag);
      String href = (String)atributeSet.getAttribute(HTML.Attribute.HREF);
      if((href!=null)&&((BOTTOMFILTERS.matcher(href).matches())
                ||(MIDDLEFILTERS.matcher(href).matches())))
       //if((href!=null)&&(BOTTOMFILTERS.matcher(href).matches()))
       {
            //Verifco si el link encontrado tiene extension valida de texto
            //Html para luego poder acceder a ese contenido
            System.out.println("Link de Busqueda no valido");
           return;
       }
      if (isTagText(tag.toString()))
      {
      permisible = true;
      }
      if( (href==null) && (tag==HTML.Tag.FRAME) )
        href = (String)atributeSet.getAttribute(HTML.Attribute.SRC);
      //busca href dentro contenedor de framset
      //mas info en http://www.w3schools.com/tags/tag_frame.asp
      if ( href==null )
        return;
      //Genero un substring de los links que contienen #
      int i = href.indexOf('#');
      if ( i!=-1 )
      href = href.substring(0,i);
      //if ( href.toLowerCase().startsWith("mailto:") ) {
      //  report.spiderFoundEMail(href);
      //  return;
      //}
      handleLink(base,href);
    }

    private boolean isTagText(String tag)
    {
        //Links deseados a recorrer que contienen texto de html util
        Collection <String> desiredTags = new ArrayList <String> ();
        desiredTags.add(HTML.Tag.B.toString());
        desiredTags.add(HTML.Tag.ADDRESS.toString());
        desiredTags.add(HTML.Tag.BIG.toString());
        desiredTags.add(HTML.Tag.BLOCKQUOTE.toString());
        desiredTags.add(HTML.Tag.CITE.toString());
        desiredTags.add(HTML.Tag.CODE.toString());
        desiredTags.add(HTML.Tag.EM.toString());
        desiredTags.add(HTML.Tag.H4.toString());
        desiredTags.add(HTML.Tag.I.toString());
        desiredTags.add(HTML.Tag.KBD.toString());
        desiredTags.add(HTML.Tag.SAMP.toString());
        desiredTags.add(HTML.Tag.SMALL.toString());
        desiredTags.add(HTML.Tag.STRONG.toString());
        desiredTags.add(HTML.Tag.SUB.toString());
        desiredTags.add(HTML.Tag.SUP.toString());
        desiredTags.add(HTML.Tag.TT.toString());
        desiredTags.add(HTML.Tag.VAR.toString());
        desiredTags.add(HTML.Tag.S.toString());
        desiredTags.add(HTML.Tag.U.toString());
        desiredTags.add(HTML.Tag.DIV.toString());
        desiredTags.add(HTML.Tag.PRE.toString());
        desiredTags.add(HTML.Tag.BR.toString());
        desiredTags.add(HTML.Tag.TITLE.toString());
        //Si el tag es deseado retorno true sino false
        if(desiredTags.contains(tag))
        return true;
        return false;
    }

    @Override
    public void handleStartTag(HTML.Tag t,
                               MutableAttributeSet a,int pos)
    {
      handleSimpleTag(t,a,pos);// Empiezo el analisis de los tags
    }
    /**
    *
    * @param data gots the tag text
    * @param pos
    */ 
    @Override
    public void handleText(char[] data, int pos) {
        if(isUndesiredTag == true)
        //Si el tag es indeseado no es texto
        //que desee leer
        {
            isUndesiredTag = false;
            return;
        }
        if(String.valueOf(data).trim().isEmpty())
        //Si el tag esta vacio de texto
        //No almaceno y retorno
        {
            System.out.println("no text");
            return;
        }
        if(permisible)
        {
        //Cada tag que contiene texto es almacenado
        //En el string builder. Dicho Texto de cada tag
        //Luego es separado por un patron de espacio _ espacio
        //Para luego poder ser analizado con mas facilidad por el
        //Search engine
        System.out.println(String.valueOf(data));
        SBConcatText.append(String.valueOf(data).toLowerCase());
        SBConcatText.append(" _ ");
        }
   }

   protected void handleLink(URL base,String str)
   {
      try {
        URL url = new URL(base,str);
        //si encuentra una url la agrega a un contenedor de url,
        //verifica la url q crea y sino te tira un
        //exception
        if ( report.spiderFoundURL(base,url) )
          addURL(url);
      }
      catch (MalformedURLException e) {log("Found malformed URL: " + str );}
    }
  }

  /**
   * Called internally to log information
   * This basic method just writes the log
   * out to the stdout.
   * Emplea un log para controlar lo que va realizando el parser
   * @param entry The information to be written to the log.
   */
  public void log(String entry)
  {
    System.out.println( (new Date()) + ":" + entry );
  }
}
