/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.apoyo;

import com.nrodas.archivosp1.entidades.DetalleVenta;
import com.nrodas.archivosp1.entidades.Producto;
import javax.swing.JTable;

/**
 * Clase que ayuda en muchos otros metodos, en su mayoria a convertir
 * @author lroda
 */
public class Conversor {
    
    //Se crea un metodo statico que devuelve el numero de inventario con respecto al nombre del inmueble

    /**
     *
     * @param nombreSucursal que sera convertida a su inventario
     * @return un int que hace referencia al inventario
     */
    public static int determinarSucursal(String nombreSucursal) {
        int inventario = 0;
        String sucursalName = nombreSucursal.trim();
        if (sucursalName.equals("Sucursal Central")) {
            inventario = 2;
        } else if (sucursalName.equals("Sucursal Norte")) {
            inventario = 3;
        } else if (sucursalName.equals("Sucursal Sur")) {
            inventario = 4;
        } else if (sucursalName.equals("Bodega")) {
            inventario = 1;
        }
        return inventario;
    }
    
    //Metodo que determina el nombre de la sucursal con el id del Inventario

    /**
     *
     * @param inventario del cual obtendremos la sucursal
     * @return un String que hace referencia al nombre de la sucursal a partir del inventario
     */
    public static String determinarNombreSucursalInv(int inventario) {
        String sucursal = "";
        if (inventario == 1) {
            sucursal = "Bodega";
        } else if (inventario == 2) {
            sucursal = "Sucursal Central";
        } else if (inventario == 3) {
            sucursal = "Sucursal Norte";
        } else if (inventario == 4) {
            sucursal = "Sucursal Sur";
        }
        return sucursal;
    }
   
    //Metodo que determina el nombre de la sucursal con el id

    /**
     *
     * @param id que posee cada sucursal
     * @return retorna un string que hace referencia al nombre de la sucursal dependiendo del id que tenga
     */
    public static String determinarNombreSucursal(int id) {
        if (id == 3) {
            return "Sucursal Central";
        } else if (id == 4) {
            return "Sucursal Norte";
        } else if (id == 5) {
            return "Sucursal Sur";
        }
        return null;
    }
    
    //Metodo que regresa el pocertaje de descuento

    /**
     *
     * @param totalAnterior de la ultima compra
     * @return un double que hace referencia al descuento que tiene derecho el cliente
     */
    public static double retornarDescuento(double totalAnterior) {
        if (totalAnterior < 1000) {
            return 0.00;
        } else if (totalAnterior >= 1000 && totalAnterior < 5000 ) {
            return 0.02;     
        } else if (totalAnterior >= 5000 && totalAnterior < 10000) {
            return 0.05;
        } else {
            return 0.1;
        }
    }
    
    //Metodo que retorna el total

    /**
     *
     * @param tabla de la cual se obtendra el total de su columna
     * @param desc el descuendo al que tiene derecho el cliente
     * @return un double que hace referencia al nuevo total
     */
    public static double calcularTotal(JTable tabla, String desc) {
        int columna = 3;
        double total = 0.00;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            double subtotal = Double.valueOf(tabla.getValueAt(i, columna) + "");
            total += subtotal;
        }
        total = total - (total*Double.valueOf(desc));
        return total;
    }
    
    //Metodo que inserta los detalles de venta

    /**
     *
     * @param tabla de la cual se obtienen los detalles
     * @param idVenta que indica el id de la venta a la que pertenecera
     * @param inventario al cual hace referencia el detalle
     * @return un booleano qu eindica si se realizo con exito la insercion de detalles de venta
     */
    public static boolean insertarDetalles(JTable tabla, int idVenta, int inventario) {
        boolean operacionExitosa = true;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            DetalleVenta dtl = new DetalleVenta(idVenta, String.valueOf(tabla.getValueAt(i, 0)).trim(), inventario,
            String.valueOf(tabla.getValueAt(i, 1)).trim(), Integer.valueOf(String.valueOf(tabla.getValueAt(i, 2)).trim()), 
            Double.valueOf(String.valueOf(tabla.getValueAt(i, 3)).trim()));
            dtl.agregarDtlVenta();
            Producto product = new Producto();
            product.buscarProducto(dtl.getCodigoProducto(), dtl.getInventario());
            product.actualizarStock(dtl.getCodigoProducto(), dtl.getInventario(), product.getCantidad() - dtl.getCantidadProducto());
        }
        return operacionExitosa;
    }
         
    
}
