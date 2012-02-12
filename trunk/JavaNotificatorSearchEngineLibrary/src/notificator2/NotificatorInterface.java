/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package notificator2;
import java.util.ArrayList;

/**
 * Esta interface comunica operaciones efectuadas entre los modulos del
 * todo el proyecto search engine.
 * 
 * @author Altamirado Liberal Peker
 */
public interface NotificatorInterface {
    public void sendNotification(String notificaton);
    public String recieveNotification();
    public void sendSetOfNotifications(ArrayList<String> notificaton);
    public ArrayList<String> recieveNotificationSetOfNotifications();
}
