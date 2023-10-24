/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.interfazgrafica;

import com.nrodas.archivosp1.entidades.Empleado;

/**
 * Clase dedicada a almacenar los datos del empleado logueado de manera estatica
 * @author lroda
 */
public class EmpLogueado {
    
    /**
     * Atributo estatico
     */
    public static Empleado empleadoLogueado;
    
    /**
     * Metodo que reinicia los valores
     */
    public static void reiniciarValores() {
        empleadoLogueado = null;
    };

}
