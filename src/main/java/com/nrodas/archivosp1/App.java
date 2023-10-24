package com.nrodas.archivosp1;


import com.nrodas.archivosp1.conexion.Conexion;
import com.nrodas.archivosp1.interfazgrafica.Login;

/**
 * Clase Principal de la Aplicacion
 *
 */
public class App 
{

    /**
     *
     * @param args donde args son las lineas de comandos
     */
    public static void main( String[] args ) {
        Conexion conexion = new Conexion(); //Se instancia una nueva conexion
        if (conexion.iniciarConexionDB()) { //Si la conexion se realiza con exito
            Login login = new Login(); //Se muestra el jframe para loguearse
        }
    }
}
