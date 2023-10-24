/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.nrodas.archivosp1.interfazgrafica;

import com.nrodas.archivosp1.entidades.Cliente;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * Clase encargada de permitir agregar al cliente
 * @author lroda
 */
public class InsertClientForm extends javax.swing.JDialog {

    /**
     * Atributos
     */
    int nitCliente;
    
    /**
     *
     * @param frame El frame al cual bloqueara
     * @param modal El modo que realizada
     * @param nitcliente El nit del cliente
     */
    public InsertClientForm(JFrame frame, boolean modal, int nitcliente) {
        super(frame, modal);
        initComponents();
        this.nitCliente = nitcliente;
        this.jLabelNit.setText(this.jLabelNit.getText() + this.nitCliente);
        this.setSize(450, 350);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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
        jLabelNit = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jTextFieldApellidos = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelNombres = new javax.swing.JLabel();
        jTextFieldNombres = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelAgregar = new javax.swing.JPanel();
        jLabelAgregar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNit.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabelNit.setForeground(new java.awt.Color(0, 0, 0));
        jLabelNit.setText("NIT: ");
        jPanelFondo.add(jLabelNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 280, -1));

        jLabelApellidos.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabelApellidos.setForeground(new java.awt.Color(0, 0, 0));
        jLabelApellidos.setText("Apellidos: ");
        jPanelFondo.add(jLabelApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 120, -1));

        jTextFieldApellidos.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldApellidos.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTextFieldApellidos.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldApellidos.setBorder(null);
        jPanelFondo.add(jTextFieldApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 230, 30));
        jPanelFondo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 230, 10));

        jLabelNombres.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabelNombres.setForeground(new java.awt.Color(0, 0, 0));
        jLabelNombres.setText("Nombres: ");
        jPanelFondo.add(jLabelNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 120, -1));

        jTextFieldNombres.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNombres.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTextFieldNombres.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldNombres.setBorder(null);
        jPanelFondo.add(jTextFieldNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 230, 30));
        jPanelFondo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 230, 10));

        jPanelAgregar.setBackground(new java.awt.Color(94, 96, 98));

        jLabelAgregar.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabelAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAgregar.setText("Agregar");
        jLabelAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAgregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAgregarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelAgregarLayout = new javax.swing.GroupLayout(jPanelAgregar);
        jPanelAgregar.setLayout(jPanelAgregarLayout);
        jPanelAgregarLayout.setHorizontalGroup(
            jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAgregarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelAgregarLayout.setVerticalGroup(
            jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAgregarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelFondo.add(jPanelAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 150, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgregarMouseEntered
        this.jPanelAgregar.setBackground(new Color(152, 155, 158));
    }//GEN-LAST:event_jLabelAgregarMouseEntered

    private void jLabelAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgregarMouseExited
        this.jPanelAgregar.setBackground(new Color(94, 96, 98));
    }//GEN-LAST:event_jLabelAgregarMouseExited

    private void jLabelAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgregarMouseClicked
        
        if (!this.jTextFieldApellidos.getText().isEmpty() && !this.jTextFieldNombres.getText().isEmpty()) {
            Cliente cliente = new Cliente(this.nitCliente, this.jTextFieldApellidos.getText(), this.jTextFieldNombres.getText());
            cliente.insertarCliente();
            this.dispose();
        }
    }//GEN-LAST:event_jLabelAgregarMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAgregar;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelNit;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JPanel jPanelAgregar;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldNombres;
    // End of variables declaration//GEN-END:variables
}
