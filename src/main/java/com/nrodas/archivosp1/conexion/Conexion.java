/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que inicializa la conexion con la base de datos
 * @author lroda
 */
public class Conexion {
    
    private static Connection conexionDB;
    private final String url = "jdbc:postgresql://localhost:5432/electronichomes";
    private final String usuario = "postgres";
    private final String contrasenia = "nbpjxdxd2";
    
    //Creamos el metodo de conexion 

    /**
     *
     * @return un booleano que indica si la conexion fue realizada con exito
     */
    public boolean iniciarConexionDB() {
        try {
            conexionDB = DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al intentar conectar con al base de datos ->" + e.getMessage());
            return false;
        }
    }
    
    //Crea el metodo que cierra la conexion

    /**
     *
     */
    public static void cerrarConexionDB(){
        if (conexionDB != null) {
            try {
                conexionDB.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    //Getter de conexionDB

    /**
     *
     * @return retorna la variable conexion
     */

    public static Connection getConexionDB() {
        return conexionDB;
    }
}
