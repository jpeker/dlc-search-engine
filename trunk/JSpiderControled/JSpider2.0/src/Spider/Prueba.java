/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Spider;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peker
 */
public class Prueba {
     public static void main (String[] args)
    {
	 Communicator com = new Communicator ();
        try {
            com.beginCrawler("http://www.yatefortuna.com.ar");
        } catch (FinishException ex) {
            System.out.println("men"+ex.getMenssaje());
            com.getHashMapPages();
        }
    }

}
