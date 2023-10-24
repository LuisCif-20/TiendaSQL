/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.listas;

/**
 * Clase encargada de manejar el comportamiento de un nodo
 * @author lroda
 */
public class Nodo {
    
    /**
     * Atributos
     */
    private Nodo siguiente;
    private Object contenido;

    /*Constructor*/

    /**
     *
     * @param siguiente El nodo siguiente
     * @param contenido El contenido que almacenara
     */

    public Nodo(Nodo siguiente, Object contenido) {
        this.siguiente = siguiente;
        this.contenido = contenido;
        
    }

    /*Getters y Setters*/

    /**
     *
     * @return el ndoo siguiente
     */

    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     *
     * @param siguiente El nodo siguiente
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     *
     * @return contenido del nodo
     */
    public Object getContenido() {
        return contenido;
    }

    /**
     *
     * @param contenido El contenido que tendra
     */
    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }
    
    
    
    
    
}
