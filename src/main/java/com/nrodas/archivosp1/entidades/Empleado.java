/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.entidades;

import com.nrodas.archivosp1.conexion.Conexion;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase que manera lo relacionado con la entidad Empleado
 * @author lroda
 */
public class Empleado {
    
    /**
     * Atributos
     */
    private long idUsuario;
    private String contrasenia;
    private String nombreEmpleado;
    private int sucursal;
    private int rol;
    private double salario;
    /*Constructores*/

    /**
     *
     */

    public Empleado() {
        //Constructor por defecto
    }

    /**
     *
     * @param idUsuario El id del usuario
     * @param contrasenia La contrsenia del usuario
     * @param nombreEmpleado El codigo del empleado 
     * @param sucursal La sucursal a la que pertenece la sucursal
     * @param rol El rol que posee el usuario
     * @param salario El salario que gana
     */
    public Empleado(long idUsuario, String contrasenia, String nombreEmpleado, int sucursal, int rol, double salario) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.nombreEmpleado = nombreEmpleado;
        this.sucursal = sucursal;
        this.rol = rol;
        this.salario = salario;
    }
    
    //Metodos de Query

    /**
     *
     * @param idUsuario El id del empleado
     * @param contraseniap La contrasenia del empleado
     * @return retorna un booleano que indica si el empleado es el que esta queriendo ingresar
     */
    public boolean comprobarInformacion(long idUsuario, String contraseniap) { //Comprueba si los datos ingresador por el usuario son correctos
        boolean banderaCorrecta = false; //Bandera que marca si el usuario existe
        String consulta = "SELECT * FROM ControlPersonal.Empleado WHERE idUsuario = ? AND contrasenia = ?"; //Query
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //SE utiliza un try-whith-resources por cualquier error
            preSt.setLong(1, idUsuario); //Se sustituye el primer ?
            preSt.setString(2, contraseniap); //Se sustituye el segundo ?
            ResultSet resultado = preSt.executeQuery(); //Se crea un conjunto de resultados
            if (resultado.next()) { //Valida si esta vacio es decir si se encontro el usuario correspondiente
                banderaCorrecta = true; //Se marca la bandero como verdader
                this.idUsuario = idUsuario; //Se llenan los datos del empleado
                this.contrasenia = contraseniap;
                this.sucursal = resultado.getInt("sucursal");
                this.nombreEmpleado = resultado.getString("nombreEmpleado");
                this.rol = resultado.getInt("rol");
                this.salario = resultado.getDouble("salario");
            }
            resultado.close(); //Se cierra el result-set para proteger la memoria
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage());
            //Muestra el error que ocurre del porque no se pudo ejecutar la instrccuion
        }
        return banderaCorrecta; //Se retorna el valor de la bandera
    }
    
    /**
     *
     * @return retorna un booleano que indica si el empleado fue agregado correctamente
     */
    public boolean agregarEmpleado() { //Metodo que se encarga de insertar un empleado
        boolean banderaAniadido = false; //Bandera que indica que fue aniadido
        String consulta = "INSERT INTO ControlPersonal.Empleado VALUES (?, ?, ?, ?, ?, ?)"; //Orden de consulta
         try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la consulta
             preSt.setLong(1, this.idUsuario); //Se sustituyen valores
             preSt.setString(2, this.contrasenia);
             preSt.setString(3, this.nombreEmpleado);
             preSt.setInt(4, this.sucursal);
             preSt.setInt(5, this.rol);
             preSt.setDouble(6, this.salario);
             preSt.executeUpdate(); //Se ejecuta la consulta
             banderaAniadido = true; //Tira afirmativo si fue aniadido
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra el error en pantalla 
         }
         return banderaAniadido; //Retorna el valor booleano
    }
    
    
    /*Getters y Setters*/

    /**
     *
     * @return retorna el id del empleado
     */

    public long getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario El id del empleado
     */
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @return retorna la contrasenia del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     *
     * @param contrasenia La contrasenia del empleado
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     *
     * @return retorna el nombre del empleado
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     *
     * @param nombreEmpleado El nombre del empleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     *
     * @return retorna la sucursal en la que trabaja
     */
    public int getSucursal() {
        return sucursal;
    }

    /**
     *
     * @param sucursal El id de la sucursal en la que trabaja
     */
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    /**
     *
     * @return retorna el rol del empleado
     */
    public int getRol() {
        return rol;
    }

    /**
     *
     * @param rol El rol que tiene el usuario
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     *
     * @return retorna el salario del empleado
     */
    public double getSalario() {
        return salario;
    }

    /**
     *
     * @param salario El salario que gana
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
}
