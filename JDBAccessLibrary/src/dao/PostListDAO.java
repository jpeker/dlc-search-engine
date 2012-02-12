/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Document;
import beans.Word;
import java.util.ArrayList;

/**
 *
 * @author PC ACER
 */
public  interface PostListDAO {
    public ArrayList<Document> 
            obtenerDocumentoCandidatos( ArrayList<Word> words );

}
