/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.entidades;

import com.nrodas.archivosp1.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase que maneja lo relacionado con la entidad DetalleVenta
 * @author lroda
 */
public class DetalleVenta {
    
    /**
     * Atributos
     */
    private int idVenta;
    private String codigoProducto;
    private int inventario;
    private String descripcionProducto;
    private int cantidadProducto;
    private double subTotal;
    
    /*Constructores*/

    /**
     *
     */

    public DetalleVenta() {
        //Constructor por defecto
    }

    /**
     *
     * @param idVenta El id de la Venta
     * @param codigoProducto El codigo del producto
     * @param inventario El inventario al que pertenece el producto
     * @param descripcionProducto La descripcion del producto
     * @param cantidadProducto La cantidad que se comprara del producto
     * @param subTotal El subtotal de la subcompra
     */
    public DetalleVenta(int idVenta, String codigoProducto, int inventario, String descripcionProducto, int cantidadProducto, double subTotal) {
        //Constructor con parametros
        this.idVenta = idVenta;
        this.codigoProducto = codigoProducto;
        this.inventario = inventario;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.subTotal = subTotal;
    }
    
    /*Metodos para las Query necesarias*/
    
    //Metodo que ingresa un nuevoDetalleVenta

    /**
     *
     * @return retorna un booleano  que indica si el detalle se agrego correctamente
     */
    public boolean agregarDtlVenta() {
        boolean detalleAgregado = false; //Bandera que indica si un detalle de vetna fue agregado correctamente
        //Se formula la Query para que inserte datos en la tabla DetalleVenta
        String consulta = "INSERT INTO ControlTienda.DetalleVenta (idVenta, codigoPdt, inventario, descripcion, cantidad, subTotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) {
            preSt.setInt(1, this.idVenta);
            preSt.setString(2, this.codigoProducto);
            preSt.setInt(3, this.inventario);
            preSt.setString(4, this.descripcionProducto);
            preSt.setInt(5, this.cantidadProducto);
            preSt.setDouble(6, this.subTotal);
            if (preSt.executeUpdate() > 0) {
                detalleAgregado = true;
            }  
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); //Muestra el mensaje de error
        }
        return detalleAgregado;
    }
    
    //Getters y Setters

    /**
     *
     * @return retorna el id de venta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     *
     * @param idVenta El id de la venta
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /**
     *
     * @return retorna el codigo del producto
     */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
     *
     * @param codigoProducto El codigo del producto
     */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     *
     * @return retorna la descripcion del producto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     *
     * @param descripcionProducto La descripcion del producto
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     *
     * @return retorna la cantidad del producto
     */
    public int getCantidadProducto() {
        return cantidadProducto;
    }

    /**
     *
     * @param cantidadProducto La nueva cantidad del producto
     */
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    /**
     *
     * @return retorna el subtotal que otorga el detalle de venta
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     *
     * @param subTotal El subtotal del detalle
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    /**
     *
     * @return retorna el inventario del detalle de venta
     */
    public int getInventario() {
        return inventario;
    }

    /**
     *
     * @param inventario El inventario al que pertenecera
     */
    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
       
    
}
