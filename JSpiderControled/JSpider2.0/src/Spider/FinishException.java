/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Spider;

/**
 *
 * @author Peker
 */
public class FinishException extends Exception {
   private String mensaje = "Error: termino de procesar url";

    public FinishException(String message) {
        mensaje=message;
    }

    public FinishException() {
    }



    public String getMenssaje() {
        return mensaje;
    }
}
