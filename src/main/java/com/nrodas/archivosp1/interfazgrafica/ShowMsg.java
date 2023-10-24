/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.nrodas.archivosp1.interfazgrafica;

import java.awt.Color;

/**
 * Clase encargada de presentar mensajes de informacion
 * @author lroda
 */
public class ShowMsg extends javax.swing.JDialog {

    /**
     * Creates new form ShowMsg
     * @param parent El frame que bloqueara
     * @param modal El modo que dentra
     * @param mensaje El mensaje que mostrara
     */
    public ShowMsg(java.awt.Frame parent, boolean modal, String mensaje) {
        super(parent, modal);
        initComponents();
        this.jTextPaneMsg.setText(mensaje);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFondo = new javax.swing.JPanel();
        jLabelInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneMsg = new javax.swing.JTextPane();
        jPanelBtnA = new javax.swing.JPanel();
        jLabelBtnA = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ojito.png"))); // NOI18N
        jPanelFondo.add(jLabelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextPaneMsg.setEditable(false);
        jTextPaneMsg.setBackground(new java.awt.Color(255, 255, 255));
        jTextPaneMsg.setBorder(null);
        jTextPaneMsg.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextPaneMsg.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTextPaneMsg);

        jPanelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 360, 90));

        jPanelBtnA.setBackground(new java.awt.Color(94, 96, 98));

        jLabelBtnA.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelBtnA.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnA.setText("Aceptar");
        jLabelBtnA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnAMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnALayout = new javax.swing.GroupLayout(jPanelBtnA);
        jPanelBtnA.setLayout(jPanelBtnALayout);
        jPanelBtnALayout.setHorizontalGroup(
            jPanelBtnALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnALayout.createSequentialGroup()
                .addComponent(jLabelBtnA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelBtnALayout.setVerticalGroup(
            jPanelBtnALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnALayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelBtnA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelFondo.add(jPanelBtnA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBtnAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnAMouseEntered
        this.jPanelBtnA.setBackground(new Color(152, 155, 158));
    }//GEN-LAST:event_jLabelBtnAMouseEntered

    private void jLabelBtnAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnAMouseExited
        this.jPanelBtnA.setBackground(new Color(94, 96, 98));
    }//GEN-LAST:event_jLabelBtnAMouseExited

    private void jLabelBtnAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnAMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabelBtnAMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBtnA;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JPanel jPanelBtnA;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneMsg;
    // End of variables declaration//GEN-END:variables
}