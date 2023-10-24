/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.apoyo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que hasea las contraseñas
 * @author lroda
 */
public class Contrasenia {
    
    /*Atributos*/
    
    //Funciones para hashearla

    /**
     *
     * @param contrasenia que que sera hasheada
     * @return un string de la constrasenia hasheada
     */
    public static String hashearContrasenia(String contrasenia) {
        String passwrd = "";
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] datosEncriptados = msg.digest(contrasenia.getBytes());
            StringBuilder contraseniaFinal = new StringBuilder();
            for (int i = 0; i < datosEncriptados.length; i++) {
                String temp = Integer.toHexString(0xff & datosEncriptados[i]);
                if (temp.length() == 1) {
                    contraseniaFinal.append('0');
                }
                contraseniaFinal.append(temp);
            }
            passwrd = contraseniaFinal.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Contrasenia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passwrd;
    }
    
    
}
