/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

/**
 *
 * @author aaltamir
 */
public class StringSplitter {
    
    public static String[] splitString(String text)
    {
        String[] vector = text.split(" ");
        for(String word: vector)
        {
            word = word.trim();
        }
        return vector;
        
    }
    
}
