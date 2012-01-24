/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spider;

import java.net.URL;

/**
 *
 * @author aaltamir
 */
public interface ISpiderReportable {
    public boolean spiderFoundURL(URL base, URL url);
    public void spiderFoundURLError(URL url);
   public void spiderFoundEMail(String email);
    
}
