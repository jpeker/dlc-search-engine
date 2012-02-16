/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.searchengineui;

import com.spider.jspiderlibrary2.Communicator;
import javax.swing.DefaultListModel;
import com.utn.searchengine.*;
import java.util.Collection;


/**
 *
 * @author PC ACER
 */
public class JFrameSearchEngineGUI extends javax.swing.JFrame {

    DefaultListModel listmodel= new DefaultListModel();
    /**
     * Creates new form JFrameSearchEngineGUI
     */
    public JFrameSearchEngineGUI() {
        initComponents(); 
        blockPanels();
        jLabelCrawl.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCrawler = new javax.swing.JPanel();
        JButtonCrawl = new javax.swing.JButton();
        jTextFieldTextToCrawl = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListWorkloads = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelTotalLinks = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelCrawl = new javax.swing.JLabel();
        jPanelSearcher = new javax.swing.JPanel();
        jTextFieldWordToSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jProgressSearch = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jLabelSearching = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListLinksFounded = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jPanelWorking = new javax.swing.JPanel();
        jLabelCrawling = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JButtonCrawl.setText("Crawlear Website");
        JButtonCrawl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonCrawlActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListWorkloads);

        jLabel1.setText("WebSite Base a Crawlear");

        jLabel2.setText("Links Crawleados");

        jLabel5.setText("links encontrados");

        jLabelCrawl.setText("Crawleando...");

        javax.swing.GroupLayout jPanelCrawlerLayout = new javax.swing.GroupLayout(jPanelCrawler);
        jPanelCrawler.setLayout(jPanelCrawlerLayout);
        jPanelCrawlerLayout.setHorizontalGroup(
            jPanelCrawlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrawlerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrawlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrawlerLayout.createSequentialGroup()
                        .addComponent(jTextFieldTextToCrawl)
                        .addGap(18, 18, 18)
                        .addComponent(JButtonCrawl))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCrawlerLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCrawl))
                    .addGroup(jPanelCrawlerLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabelTotalLinks, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCrawlerLayout.setVerticalGroup(
            jPanelCrawlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrawlerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelCrawlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCrawlerLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTextToCrawl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JButtonCrawl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCrawlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabelCrawl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrawlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalLinks)
                    .addComponent(jLabel5))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel6.setText("Inserte aqui la palabra que desea Searchear");

        jLabel7.setText("Progreso de la busqueda");

        jLabelSearching.setText("Buscando...");

        jButtonSearch.setText("Buscar");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jListLinksFounded);

        jLabel9.setText("Links encontrados con la palabra buscada.");

        javax.swing.GroupLayout jPanelSearcherLayout = new javax.swing.GroupLayout(jPanelSearcher);
        jPanelSearcher.setLayout(jPanelSearcherLayout);
        jPanelSearcherLayout.setHorizontalGroup(
            jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearcherLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSearcherLayout.createSequentialGroup()
                        .addGroup(jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldWordToSearch, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jProgressSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearch))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanelSearcherLayout.setVerticalGroup(
            jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearcherLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelSearcherLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldWordToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonSearch))
                .addGap(7, 7, 7)
                .addGroup(jPanelSearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSearcherLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelSearching, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jLabelCrawling.setText("Realizando Crawleo Aguarde unos Momentos");

        javax.swing.GroupLayout jPanelWorkingLayout = new javax.swing.GroupLayout(jPanelWorking);
        jPanelWorking.setLayout(jPanelWorkingLayout);
        jPanelWorkingLayout.setHorizontalGroup(
            jPanelWorkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWorkingLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabelCrawling, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanelWorkingLayout.setVerticalGroup(
            jPanelWorkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWorkingLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabelCrawling)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jMenu1.setText("Opciones");

        jMenuItem1.setText("Crawlear");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Buscar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelCrawler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelSearcher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelWorking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelCrawler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelSearcher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelWorking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        blockPanels();
        jPanelCrawler.setVisible(true);
        jListWorkloads.setModel(listmodel);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        blockPanels();
        jPanelSearcher.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void JButtonCrawlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonCrawlActionPerformed
     Thread com = new Thread(new Communicator());
     listmodel.clear();
     Communicator.textToCrawl=jTextFieldTextToCrawl.getText();
     Communicator.crawl=true;
     GUIManager g1= new GUIManager(jLabelCrawl);
     GUIManager g2= new GUIManager(jLabelTotalLinks);
     JListWorker jlw = new JListWorker(jListWorkloads,listmodel);
     ProgressBarRunnable pb1= new ProgressBarRunnable(g1);
     ReturnDataRunnable pb2= new ReturnDataRunnable(g2);
     JButtonWorker jb = new JButtonWorker(JButtonCrawl);
     JTextBoxWorker jt = new JTextBoxWorker(jTextFieldTextToCrawl);
     com.start();
     jb.execute();
     jt.execute();
     pb1.start();
     pb2.start();
     jlw.execute();
    }//GEN-LAST:event_JButtonCrawlActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
    LocalWordCountManager lwcm = new LocalWordCountManager();
    Collection<Similitude> i = lwcm.determinateBestSimilitude(new Document("query",jTextFieldWordToSearch.getText()));
    for(Similitude similitude: i){
           System.out.println("results are "+similitude.getDocumentA().getLocation().toString());

        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void blockPanels(){
    jPanelCrawler.setVisible(false);
    jPanelSearcher.setVisible(false);
    jPanelWorking.setVisible(false);
    }
    /*
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameSearchEngineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameSearchEngineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameSearchEngineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameSearchEngineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JFrameSearchEngineGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonCrawl;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCrawl;
    private javax.swing.JLabel jLabelCrawling;
    private javax.swing.JLabel jLabelSearching;
    private javax.swing.JLabel jLabelTotalLinks;
    private javax.swing.JList jListLinksFounded;
    private javax.swing.JList jListWorkloads;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanelCrawler;
    private javax.swing.JPanel jPanelSearcher;
    private javax.swing.JPanel jPanelWorking;
    private javax.swing.JProgressBar jProgressSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldTextToCrawl;
    private javax.swing.JTextField jTextFieldWordToSearch;
    // End of variables declaration//GEN-END:variables
 

}
