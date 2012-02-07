
package Spider;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

/*
 * RobotsParser clase que parsea el archivo robots.txt de un sitio web
 * Usando siempre la direccion base con protocolo http
 */
public class RobotsParser {
    //Palabras claves contenidas dentro del archivo robots.txt necesarias
    //para realizar el parseo ejemplo http://www.facebook.com/robots.txt
    public static final String SEARCH = "Search";
    public static final String STOP = "Stop";
    public static final String DISALLOW = "Disallow:";
    public static final int    SEARCH_LIMIT = 50;
    public  static boolean robotSafe(URL url) {
	String strHost = url.getHost();
        //Donde se encuentra el archivo robots.txt
        //http://www.robotstxt.org/robotstxt.html ver estandar aqui
	String strRobot = "http://" + strHost + "/robots.txt";
	URL urlRobot;
	try {
            //Checkea por URL mal formada
	    urlRobot = new URL(strRobot);
	} catch (MalformedURLException e) {
	    // URL Mal formada, no confiar devolver false
	    return false;
	}
	String strCommands;
	try {
	    InputStream urlRobotStream = urlRobot.openStream();
	    // Lee en todo el archivo, el valor del byte[] queda establecido como parametro definido
	    byte b[] = new byte[1000];
	    int numRead = urlRobotStream.read(b);
	    strCommands = new String(b, 0, numRead);
	    while (numRead != -1) {
		numRead = urlRobotStream.read(b);
		if (numRead != -1) {
		    String newCommands = new String(b, 0, numRead);
		    strCommands += newCommands;
		}
	    }
	    urlRobotStream.close();
	} catch (IOException e) {
	    //Sino existe el archivo robots.txt en el sitio web
            //crawleo sin limitaciones :)
            return true;
	}
	// Busco los "Disallow:" dentro del archivo.
	String strURL = url.getFile();
	int index = 0;
	while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
	    index += DISALLOW.length();
	    String strPath = strCommands.substring(index);
	    StringTokenizer st = new StringTokenizer(strPath);
            //Empleo StringTokenizer para splitear el path en subsStrings
	    if (!st.hasMoreTokens()) //Cuando no hay mas Tokens fuiste.
		break;	    
	    String strBadPath = st.nextToken();
            System.out.println("The foribidden path is: "+strBadPath);
	    //Si la url empieza con un disallowed path no es segura.
            if (strURL.indexOf(strBadPath) == 0)
		return false;
	}
	return true;
    }
}
