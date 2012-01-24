/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spider;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

/**
 *
 * @author aaltamir
 */
public class RobotsParser {
    public static final String SEARCH = "Search";
    public static final String STOP = "Stop";
    public static final String DISALLOW = "Disallow:";
    public static final int    SEARCH_LIMIT = 50;
    public  static boolean robotSafe(URL url) {
	String strHost = url.getHost();

	// form URL of the robots.txt file
	String strRobot = "http://" + strHost + "/robots.txt";
	URL urlRobot;
	try { 
	    urlRobot = new URL(strRobot);//check for fileNotFoundException
	} catch (MalformedURLException e) {
	    // something weird is happening, so don't trust it
	    return false;
	}

	String strCommands;
	try {
	    InputStream urlRobotStream = urlRobot.openStream();

	    // read in entire file,el valor queda establecido como parametro definido
	    byte b[] = new byte[1000];
	    int numRead = urlRobotStream.read(b);
	    strCommands = new String(b, 0, numRead);
	    while (numRead != -1) {
//		if (Thread.currentThread() != searchThread)
//		    break;
		numRead = urlRobotStream.read(b);
		if (numRead != -1) {
		    String newCommands = new String(b, 0, numRead);
		    strCommands += newCommands;
		}
	    }
	    urlRobotStream.close();
	} catch (IOException e) {
	    // if there is no robots.txt file, it is OK to search
	    return true;
	}

	// assume that this robots.txt refers to us and 
	// search for "Disallow:" commands.
	String strURL = url.getFile();
	int index = 0;
	while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
	    index += DISALLOW.length();
	    String strPath = strCommands.substring(index);
	    StringTokenizer st = new StringTokenizer(strPath);
            
	    if (!st.hasMoreTokens())
		break;
	    
	    String strBadPath = st.nextToken();
            System.out.println("The foribidden path is: "+strBadPath);
	    // if the URL starts with a disallowed path, it is not safe
	    if (strURL.indexOf(strBadPath) == 0)
		return false;
	}

	return true;
    }
}
