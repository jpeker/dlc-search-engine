package com.utn.searchengine;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Clase StopWordMap, es un hashMap de strings que añade stop words
 * que no deben ser indexadas.
 * 
 */
public class StopWordMap {
    //Archivo de stops words
    //Para instanciar y asignar un archivo primero
    //Debe identificarse el idioma antes de leer el archivo.
    //Luego de leer el idioma el archivo es recorrido y
    //se añade al hashMap
    File stopWordFile=null;

    // HashMap que contiene las stop words que no seran indexadas
    private HashMap stopWordMap= new HashMap();

    // Añade una nueva stop word al HashMap
    private void addStopWord(String stopWordString)
    {
         stopWordMap.put(stopWordString.hashCode(), stopWordString);
    }
    
    //readStopWordList sirve para leer las stopword que empleo para la busqueda
    //para eso leo un archivo .txt del idioma de stopWord que quieda de
    //del documento o sitio visitado
    public boolean readStopWordList()
    {
        if(stopWordFile==null){return false;}
        try{
        //Creo los flujos y encapsulo
        FileInputStream stopISWordFile = new FileInputStream(stopWordFile);
        DataInputStream dataInputStreamStopWordFile =
                new DataInputStream(stopISWordFile);
        BufferedReader bufferReaderStreamStopWordFile =
                new BufferedReader
                (new InputStreamReader(dataInputStreamStopWordFile));
        String stopWordLine;
        //Leo cada Linea del archivo, cada linea es una palabra
        //El archivo debe tener una estructura de una palabra por linea
        while ((stopWordLine = bufferReaderStreamStopWordFile.readLine()) != null){
        //Muestro Las Stop Word y las añado
         System.out.println (stopWordLine);
         this.addStopWord(stopWordLine);
        }
        //Cierro el flujo
        bufferReaderStreamStopWordFile.close();
        }
        catch(Exception e){
        System.out.println("El archivo no se encuentra, hasta las manos!");
        }
        return true;
    }

    // Traigo el hashMap con todo su contenido
    public HashMap getStopWordMap()
    {
    return stopWordMap;
    }

    // En funcion del idioma en que este el documento trae
    // El archivo de stopwordXXX.txt que necesite
    // El documento debe reconocer que tipo de idioma es.
    // XXX es el idioma deseado
    public File stopWordIdiomDocumentFinder(int selectedIdiom){
        // 0=ingles
        // 1=español
        // ir agregando en funcion de las ganas.
        switch(selectedIdiom)
        {
        case 0: {stopWordFile= new File("stopwordsENG.txt");
            break;}
        case 1: {stopWordFile= new File("stopwordsSPA.txt");
            break;}
        }
        // Devuelvo en funcion del idioma elegido
        return stopWordFile;
    }
    //Verifica si la Word esta contenida en el HashMap de stopWords
    public boolean isStopWord(Word word){
        if(stopWordMap.containsValue(word.getName())){
        return true;
        }
    return false;
    }
}
