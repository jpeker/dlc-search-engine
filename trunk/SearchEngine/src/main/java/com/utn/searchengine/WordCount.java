/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengine;

//import java.io.FileInputStream;
//import java.nio.CharBuffer;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.nio.charset.Charset;
//import java.nio.charset.CharsetDecoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class provides the utility of detect the different words that exist on a document
 * @author aaltamir
 */
public final class WordCount {
  
    /**
     * 
     * @param filePath: A path to a disk file
     * @return a collection of words and the times that those words repeats on the file
     */
      public static Map<String, Integer> retrieveWordCount(Document entry){
          if(entry==null){
              return null;
          }
          //String filename = filePath;
          try{
    // Map File from filename to byte buffer
    //FileInputStream input = new FileInputStream(filename);
    //FileChannel channel = input.getChannel();
    //int fileLength = (int) channel.size();
    //MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
    //    fileLength);

    // Convert to character buffer
    //Charset charset = Charset.forName("ISO-8859-1");
    //CharsetDecoder decoder = charset.newDecoder();
    //CharBuffer charBuffer = decoder.decode(buffer);

    // Create line pattern
    Pattern linePattern = Pattern.compile(".*$", Pattern.MULTILINE);

    // Create word pattern
    Pattern wordBreakPattern = Pattern.compile("[\\p{Punct}\\s}]");

    // Match line pattern to buffer
    Matcher lineMatcher = linePattern.matcher(entry.getText().subSequence(0, entry.getText().toString().length()).toString());

    //
    Map map = new TreeMap();
    Integer ONE = new Integer(1);

    // For each line
    while (lineMatcher.find()) {
      // Get line
      CharSequence line = lineMatcher.group();

      // Get array of words on line
      String words[] = wordBreakPattern.split(line);

      // For each word
      for (int i = 0, n = words.length; i < n; i++) {
        if (words[i].length() > 0) {
          Integer frequency = (Integer) map.get(words[i]);
          if (frequency == null) {
            frequency = ONE;
          } else {
            int value = frequency.intValue();
            frequency = new Integer(value + 1);
          }
          map.put(words[i], frequency);
        }
      }
    }
    System.out.println(map);
    return map;
      }
      
      catch(Exception e){
          return null;
      }
     
      
  }
}
