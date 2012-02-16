/*
 * HTMLParse clase que extiende de HTMLEditorKit para parsear streams de HTML
 */
package com.spider.jspiderlibrary2;
//HTML Editor clase para reconocer e implementar documentos de html, basado en HTML 3.2 sin implementacion con HTML 4 aun
import javax.swing.text.html.HTMLEditorKit;

public class HTMLParse extends HTMLEditorKit {
//Trae el parser de HTML que se utilizará para la lectura de streams de HTML.

    @Override
    public HTMLEditorKit.Parser getParser() {
        return super.getParser();
    }
}
