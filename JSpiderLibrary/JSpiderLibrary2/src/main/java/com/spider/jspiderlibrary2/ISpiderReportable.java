
package com.spider.jspiderlibrary2;

import java.net.URL;
/*
 * Interfaz empleada para implementar al momento de reportar si el crawler
 * spider.
 * Encuentra una url o una url con errores
 */
public interface ISpiderReportable {
    //Metodo empleado cuando se encuentra una url a partir de una
    //URL base que define el dominio del sitio web a crawlear
    public boolean spiderFoundURL(URL base, URL url);
    //Metodo empleado cuando se encuentra una url con errores
    public void spiderFoundURLError(URL url);   
}
