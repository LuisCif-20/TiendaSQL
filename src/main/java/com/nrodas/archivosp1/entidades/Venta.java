/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.entidades;

import com.nrodas.archivosp1.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Clase relacionada con la entidad Venta
 * @author lroda
 */
public class Venta {
    
    /**
     * Atributos
     */
    private int sucursal;
    private Date fechaVenta;
    private long codigoEmpleado;
    private String nitCliente;
    private boolean cFinal;
    private double total;
    
    /*Constructor*/

    /**
     *
     */

    public Venta() {
        //Constructor por defecto
    }

    /**
     *
     * @param sucursal La sucursal en la que se realiza
     * @param fechaVenta La fecha en la que se realiza
     * @param codigoEmpleado El codigo del usuario que la realiza
     * @param nitCliente El nit del cliente que participa
     * @param cFinal Si presenta consumidor final
     * @param total El total de la venta
     */
    public Venta(int sucursal, Date fechaVenta, long codigoEmpleado, String nitCliente, boolean cFinal, double total) {
        this.sucursal = sucursal;
        this.fechaVenta = fechaVenta;
        this.codigoEmpleado = codigoEmpleado;
        this.nitCliente = nitCliente;
        this.cFinal = cFinal;
        this.total = total;
    }
    
    //Metodos para realizar las Query

    /**
     *
     * @return booleano que indica si la compra se realizo con exito
     */
    public boolean insertarCompra() { //Metodo que registra las ventas
        boolean insercionCorrecta = false; //Bandera que Indica que la vetna fue realizada
        //Se formula la Query
        String consulta = "INSERT INTO ControlTienda.Venta (sucursal, fechaVenta, codigoEmpleado, nitcli, cf, total) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la call
            preSt.setInt(1, this.sucursal); //Se sutituyen datos
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); //Se da formato a la fecha
            preSt.setDate(2, java.sql.Date.valueOf(formato.format(this.fechaVenta)));
            preSt.setLong(3, this.codigoEmpleado);
            if (this.nitCliente == null) { //Comprueba si se ingreso un nit
                preSt.setNull(4, Types.BIGINT); //Setea como null
            } else {
                preSt.setInt(4, Integer.parseInt(this.nitCliente)); //Sustituye por el nit
            }
            preSt.setBoolean(5,this.cFinal);
            preSt.setDouble(6, this.total);
            if (preSt.executeUpdate() > 0) { //Se comprueba que la insercion hubiera sido exitosa
                insercionCorrecta = true; //La operacion es exitosa
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error
        }
        return insercionCorrecta; //Retorna el resultado de la expresion
    }
    
    //Metodo que busca la ultima compra y retorna el descuento

    /**
     *
     * @param nitCliente El nit del cliente que compra
     * @return double que indica el total de la ultima compra de un cliente
     */
    public double buscarUltimaCompra(int nitCliente) { //Busca la ultima compra con el nit del cliente
        double totalAnterior = 0; //Retorne el total gastado de esa compra
        //Se formula la Query
        String consulta = "SELECT total FROM ControlTienda.Venta WHERE nitcli=? ORDER BY fechaVenta DESC LIMIT 1";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la call
            preSt.setInt(1, nitCliente); //Se sustituyen los valores
            ResultSet resultado = preSt.executeQuery(); //Se instancia un cuerpo de resultados
            if (resultado.next()) { //Verifica si retorno un resultado
                totalAnterior = resultado.getDouble(1); //Se le asigna el valor 
            }
            resultado.close(); //Se cierra para evitar problemas de optimizacion
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por si lo hay
        }
        return totalAnterior; //Retorna el valor
    }
    
    //Metodo que obtiene el id de factura mas alto

    /**
     *
     * @return entero que indica el valor maximo de la ultima venta
     */
    public int buscarUltimaInsercion() {
        int idMax = 0; //Id de la ultima venta
        String consulta = "SELECT MAX(idVenta) FROM ControlTienda.Venta"; //Query que selecciona el ID de la ultima venta
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se realiza la consulta
            ResultSet resultado = preSt.executeQuery();  //Se obtiene el id
            if (resultado.next()) { //Valida que la operacion resulto con exito
                idMax = resultado.getInt(1); //Se guarda el ultimo id de la venta
            }
            resultado.close(); //Se cierra el close
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por is lo hubiera
        }
        return idMax; //Se retorna el ultimo id de la venta
    }
    
    //Getters y Setters

    /**
     *
     * @return int que indica la sucursal de la vetna
     */
    public int getSucursal() {
        return sucursal;
    }

    /**
     *
     * @param sucursal La sucursal en la que se realiza
     */
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    /**
     *
     * @return la fecha de vetna
     */
    public Date getFechaVenta() {
        return fechaVenta;
    }

    /**
     *
     * @param fechaVenta La fecha en la que se realiza
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     *
     * @return el codigo del empleado
     */
    public Long getCodigoEmpleado() {
        return codigoEmpleado;
    }

    /**
     *
     * @param codigoEmpleado El empleado que la realiza
     */
    public void setCodigoEmpleado(Long codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    /**
     *
     * @return el nit del cliente
     */
    public String getNitCliente() {
        return nitCliente;
    }

    /**
     *
     * @param nitCliente El cliente que participa
     */
    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    /**
     *
     * @return booleano que indica si es Consumidor Final o no
     */
    public boolean iscFinal() {
        return cFinal;
    }

    /**
     *
     * @param cFinal Si es consumidor final o no
     */
    public void setcFinal(boolean cFinal) {
        this.cFinal = cFinal;
    }

    /**
     *
     * @return total de la venta
     */
    public double getTotal() {
        return total;
    }

    /**
     *
     * @param total El total de la venta
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
}
