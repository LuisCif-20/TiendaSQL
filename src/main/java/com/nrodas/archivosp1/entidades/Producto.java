/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.entidades;

import com.nrodas.archivosp1.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase que maneja lo relacionado con la entidad Producto
 * @author lroda
 */
public class Producto {
    
    /**
     * Atributos
     */
    private String codigoProducto;
    private int inventario;
    private String descripcionProducto;
    private int cantidad;
    private double precioUnitario;
    
    /*Contructores*/

    /**
     *
     */

    public Producto() {
        //Constructor por defecto
    }

    /**
     *
     * @param codigoProducto El codigo del producto
     * @param inventario El inventario al que pertenece
     * @param descripcionProducto La descripcion del producto
     * @param cantidad La cantidad del producto
     * @param precioUnitario El precio unitario del producto
     */
    public Producto(String codigoProducto, int inventario, String descripcionProducto, int cantidad, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.inventario = inventario;
        this.descripcionProducto = descripcionProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    //Metodos para las Query de Productos

    /**
     *
     * @param codigoProducto LE codigo del producto a buscar
     * @param inventario El inventari del producto en donde buscar
     * @return productoEncontrado booleano que hace referencia a que el producto existe
     */
    public boolean buscarProducto(String codigoProducto, int inventario) { //Metodo que busca el producto
        boolean productoEncontrado = false; //Bandera que indica si fue encontrado
        //Se prepara la consulta
        String consulta = "SELECT descripcion, cantidad, precioUnitario FROM ControlTienda.Producto WHERE codigoPdt = ? AND inventario = ?";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la llamada
            //Se sustituyen los datos
            preSt.setString(1, codigoProducto);
            preSt.setInt(2, inventario);
            ResultSet resultado = preSt.executeQuery(); //Se hace la consulta
            if (resultado.next()) { //Si existe sustituye los datos y afirma la operacion con exito
                this.codigoProducto = codigoProducto;
                this.inventario = inventario;
                this.descripcionProducto = resultado.getString(1);
                this.cantidad = resultado.getInt(2);
                this.precioUnitario = resultado.getDouble(3);
                productoEncontrado = true;
            }
            resultado.close(); //se cierra el resultado
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Se imprime el error
        }
        return productoEncontrado; //Se retorna el valor
    }
    
    //Metodo para agregar un producto

    /**
     *
     * @return retorna un booleano que indica si el producto fue ingresaco correctamente 
     */
    public boolean insertarProducto () {
        boolean correctInsert = false; //Bandera que indica que el producto fue agregado
        String consulta = "INSERT INTO ControlTienda.Producto VALUES (?, ?, ?, ?, ?)"; //Se formula el Query
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la llamada
            //Se sustituyen los datos
            preSt.setString(1, this.codigoProducto);
            preSt.setInt(2, this.inventario);
            preSt.setString(3, this.descripcionProducto);
            preSt.setInt(4, this.cantidad);
            preSt.setDouble(5, this.precioUnitario);
            if (preSt.executeUpdate() > 0) { //Se ejecuta el Query
                correctInsert = true; //Si setea a true si fue realizada con exito
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por si lo hubiera
        }
        return correctInsert; //Devuelve si la operacion se realizo con exito
    }
 
    //Metodo para actualizar el stock de un producto

    /**
     *
     * @param codigoProducto El codigo del producto a actualizar
     * @param noInventario El inventario del producto a actualizar
     * @param cantidadActual La neuva cantidad de ese producto
     * @return booleano que indica si el stock fue actualizado correctamente
     */
    public boolean actualizarStock(String codigoProducto, int noInventario, int cantidadActual) {
        boolean stockActualizado = false; //Bandera que indica que la operacion fue realizada con exito
        String consulta = "UPDATE ControlTienda.Producto SET cantidad = ? WHERE codigoPdt = ? AND inventario = ?"; //Se formula la Query
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se preapra la llamadda
            preSt.setInt(1, cantidadActual); //Se sustituyen los datos
            preSt.setString(2, codigoProducto);
            preSt.setInt(3, noInventario);
            if (preSt.executeUpdate() > 0) { //Se actualiza el stock
                stockActualizado = true; //Se cambia el valor a true
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un mensaje de error por si hubiera
        }
        return stockActualizado; //Se retorna el valor de exito de la operacion
    }
    
    /**
     * Metodo que actualiza el nombre de un producto
     * @param nuevaDesc La nueva descripcion
     * @return El resultado de la operacion si es verdader o falso
     */
    public boolean acutalizatDesc(String nuevaDesc) {
        boolean actualizado = false;
        String consulta = "UPDATE ControlTienda.Producto SET descripcion = ? WHERE codigoPdt = ? AND inventario = ?";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) {
            preSt.setString(1, nuevaDesc);
            preSt.setString(2, this.codigoProducto);
            preSt.setInt(3, this.inventario);
            if (preSt.executeUpdate() > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return actualizado;
    }
    
    /**
     * Metodo que actualiza el prezio de un producto
     * @param nuevoPrecio El nuevo precio que tendra
     * @return El resultado de la operacion
     */
    public boolean actualizarPrezio(double nuevoPrecio) {
        boolean actualizado = false;
        String consulta = "UPDATE ControlTienda.Producto SET precioUnitario = ? WHERE codigoPdt = ? AND inventario = ?";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) {
            preSt.setDouble(1, nuevoPrecio);
            preSt.setString(2, this.codigoProducto);
            preSt.setInt(3, this.inventario);
            if (preSt.executeUpdate() > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return actualizado;
    }
            
    
    

    /*Getters y Setters*/

    /**
     *
     * @return codigo de producto
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
     * @return numero de inventario
     */
    public int getInventario() {
        return inventario;
    }

    /**
     *
     * @param inventario El inventario
     */
    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    /**
     *
     * @return la descripcion del producto
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
     * @return la cantiad del producto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param cantidad La cantidad del producto
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     *
     * @return el precio unitario del producto
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     *
     * @param precioUnitario El precio del producto
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    
}
