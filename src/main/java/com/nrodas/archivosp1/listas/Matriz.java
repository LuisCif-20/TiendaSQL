/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.listas;

/**
 * Clase encargada de crear listas bidimensionales
 * @author lroda
 */
public class Matriz {
    
    /**
     * Atributos
     */
    private Lista primera;
    private Lista ultima;
    
    /*Contructor*/

    /**
     *
     */

    public Matriz() {
        this.primera = null;
        this.ultima = null;
    }
    
    /*Metodo para agregar una nueva lista*/

    /**
     *
     * @param lista La lista que sera agregada
     */

    public void agregarNuevaLista(Lista lista) {
        if (this.primera == null) { //Si la primer lista es nula
            this.ultima = lista; //La ultima lista apuntara a una lista
            this.primera = this.ultima; //La primera apuntata a la ultima
        } else {
            this.ultima.setSiguiente(lista); //La ultima setea su siguiente a otra lista
            this.ultima = lista; //La ultima ahora apunta a la nueva lista
        }
    }

    /*Getters y Setters*/

    /**
     *
     * @return la prime rlista
     */

    public Lista getPrimera() {
        return primera;
    }

    /**
     *
     * @param primera La primer lista
     */
    public void setPrimera(Lista primera) {
        this.primera = primera;
    }

    /**
     *
     * @return la ultima lista
     */
    public Lista getUltima() {
        return ultima;
    }

    /**
     *
     * @param ultima La ultima lista
     */
    public void setUltima(Lista ultima) {
        this.ultima = ultima;
    }
    
    
    
}
