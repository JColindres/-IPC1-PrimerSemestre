/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo;

/**
 *
 * @author pablo
 */
public class NodoLista {
    
    //Atributos
    NodoLista siguiente;
    NodoLista anterior;
    int id;
    String nombre;

    //Constructor
    public NodoLista(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.siguiente = null;
        this.anterior = null;
    }

}
