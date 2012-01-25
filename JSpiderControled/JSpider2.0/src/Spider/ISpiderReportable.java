/*
 * Interfaz empleada para implementar al momento de reportar si el crawler spider
 * Encuentra una url, errores de url o Email
 */
package Spider;

import java.net.URL;

/*
 *
 *
 */
public interface ISpiderReportable {
    public boolean spiderFoundURL(URL base, URL url);
    public void spiderFoundURLError(URL url);
    public void spiderFoundEMail(String email);
    
}
