/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package notificator;
import java.util.ArrayList;

/**
 * Esta interface comunica operaciones efectuadas entre los modulos del
 * todo el proyecto search engine.
 * 
 * @author Altamirado Liberal Peker
 */
public interface NotificatorInterface {
    public String sendNotification(String notification);
    public void recieveNotification(String notification);
    public ArrayList<String> sendSetOfNotifications(ArrayList<String> notifications);
    public ArrayList<String> sendSetOfNotifications(String notification);
    public void recieveNotificationSetOfNotifications(ArrayList<String> notifications);
}
