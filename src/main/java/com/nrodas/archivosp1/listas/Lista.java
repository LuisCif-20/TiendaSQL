/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.listas;

/**
 * Clase encargada de crear listas y manejarlas
 * @author lroda
 */
public class Lista {
    
    /**
     * Atributos
     */
    private Nodo primero;
    private Nodo ultimo;
    private Lista siguiente;

    /*Constructor*/

    /**
     *
     * @param siguiente La lista que le seguira
     */

    public Lista(Lista siguiente) {
        this.primero = null;
        this.ultimo = null;
        this.siguiente = siguiente;
    }
    
    /*Metodo para expandir la matriz*/

    /**
     *
     * @param objeto El contenido que tendra el nodo
     */

    public void agregarNodo(Object objeto) {
        if (this.primero == null) { //Si el primer nodo es nulo
            this.ultimo = new Nodo(null, objeto); //El ultimo sera un nuevo nodo
            this.primero = this.ultimo; //El primero apuntara al ultimo
        } else {
            Nodo nuevo = new Nodo(null, objeto); //Se crea un nuevo nodo
            this.ultimo.setSiguiente(nuevo); //El siguiente del ultimo sera este nuevo nodo
            this.ultimo = nuevo; //El ultimo ahora sea este nuevo nodo
        }
    }
    
    //Metodo para hallar su tamanio

    /**
     *
     * @return int que indica el tamanio de la lista
     */
    public int hallarTamanio() {
        int tamanio = 0; //Se inicializa una variable tamanio
        Nodo temp = this.primero; //Se crea un nodo temporal
        while (temp != null) { //Se recorre la lista dependiendo sus nodos
            temp = temp.getSiguiente();
            tamanio++; //Incrementa el tamanio en 1
        }
        return tamanio; //Retorna el tamanio
    }

    /*Getters y Setters*/

    /**
     *
     * @return el primer nodo
     */

    public Nodo getPrimero() {
        return primero;
    }

    /**
     *
     * @param primero El primer nodo
     */
    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    /**
     *
     * @return el ultimo nodo
     */
    public Nodo getUltimo() {
        return ultimo;
    }

    /**
     *
     * @param ultimo El ultimo nodo
     */
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }  

    /**
     *
     * @return la lista siguiente
     */
    public Lista getSiguiente() {
        return siguiente;
    }

    /**
     *
     * @param siguiente La lista que sigue
     */
    public void setSiguiente(Lista siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
