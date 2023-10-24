/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.apoyo;

import com.nrodas.archivosp1.listas.Lista;
import com.nrodas.archivosp1.listas.Matriz;
import com.nrodas.archivosp1.listas.Nodo;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Clase que llena las tablas de la aplicacion
 * @author lroda
 */
public class LlenadorTabla {
    
    /**
     * 
     * @param tabla La tabla a la cual se le cambiara el titulo
     * @param titulos Los titulos se le agregaran a la tabla
     */
    public static void cambiarEncabezado(JTable tabla, String[] titulos) {
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(94, 96, 98));
        header.setForeground(Color.white);
        header.setFont(new Font("SansSerif", Font.PLAIN, 18));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        DefaultTableModel modelo = new DefaultTableModel();
        for (int i = 0; i < titulos.length; i++) {
            modelo.addColumn(titulos[i]);
        }
        tabla.setRowHeight(30);
        tabla.setBackground(new Color(238, 238, 238));
        tabla.setGridColor(Color.white);
        tabla.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tabla.setEnabled(false);
        tabla.setCellSelectionEnabled(false); 
        tabla.setModel(modelo);
    }
    
    /**
     *
     * @param tabla La tabla a la cual se le insertaran datos
     * @param lista La matriz de la cual se obtendran los datos 
     */
    public static void llenarTabla(JTable tabla, Lista lista){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        Object[] objeto = new Object[lista.hallarTamanio()];
        Nodo temp = lista.getPrimero();
        int indice = 0;
        while (temp != null) {            
            objeto[indice] = " " + temp.getContenido();
            indice++;
            temp = temp.getSiguiente();
        }
        modelo.addRow(objeto);
    }
    
    /**
     *
     * @param tabla La tabla ala cual se le insertaran los datos
     * @param matriz La matriz de la cual se obtendran los datos
     */
    public static void llenarTabla(JTable tabla, Matriz matriz) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int tamanio = matriz.getPrimera().hallarTamanio();
        Lista temp = matriz.getPrimera();
        int orden = 1;
        while (temp != null) {
            Object[] objeto = new Object[tamanio + 1];
            Nodo tempo = temp.getPrimero();
            int indice = 1;
            objeto[0] = "  " + orden; 
            while (tempo != null) {                
                objeto[indice] = "  " + tempo.getContenido();
                indice++;
                tempo = tempo.getSiguiente();
            }
            orden++;
            modelo.addRow(objeto);
            temp = temp.getSiguiente();
        }
    }
    
    /**
     *
     * @param tabla La tabla a la cual se le insertaran los datos
     * @param matriz La matriz de la cual se obtendra los valores
     */
    public static void llenarTablaInventario(JTable tabla, Matriz matriz) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int tamanio = matriz.getPrimera().hallarTamanio();
        Lista temp = matriz.getPrimera();
        while (temp != null) {
            Object[] objeto = new Object[tamanio];
            Nodo tempo = temp.getPrimero();
            int indice = 0;
            while (tempo != null) {                
                objeto[indice] = "  " + tempo.getContenido();
                indice++;
                tempo = tempo.getSiguiente();
            }
            modelo.addRow(objeto);
            temp = temp.getSiguiente();
        }
    }
    
    //Metodo que verifica si un producto ya esta en la factura

    /**
     *
     * @param table La tabla en la cual se verificara un producto
     * @param codigoPdt El codigo del producto a buscar
     * @param cantidadPdt La cantidad del producto en el stock de la tienda
     * @param cantidad La cantidad que se le sumara a la venta
     * @return retorna un int que indica si el producto ingresado ya existia o no
     */
    public static int verificarCompra(JTable table, String codigoPdt, int cantidadPdt, int cantidad){
        int existe = 0;
        int filas = table.getRowCount();
        for (int i = 0; i < filas; i++) {
            if (codigoPdt.equals(String.valueOf(table.getValueAt(i, 0)).trim())) {
                int newcantidad = Integer.valueOf(String.valueOf(table.getValueAt(i, 2)).trim()) + cantidad;
                if (newcantidad <= cantidadPdt) {
                    table.setValueAt(" " + newcantidad, i, 2);
                    existe = 1;
                } else {
                    existe = 2;
                }
            }
        }
        return existe;
    }
}
