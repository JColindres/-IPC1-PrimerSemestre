/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author pablo
 */
public class ListaCircularDoble {

    private NodoLista inicio;

    public void insertar(int id, String nombre) {

        //Agregar nodo nuevo
        NodoLista nuevo = new NodoLista(id, nombre);
        if (inicio == null) { //Si la lista esta vacia
            inicio = nuevo;
            inicio.siguiente = inicio;
            inicio.anterior = inicio;
        } else { //Si la lista ya tiene un nodo o mas
            NodoLista aux = inicio; //Crear nodo auxiliar para recorrer la lista
            while ((aux.siguiente != inicio) && (aux.id < id)) { //Mientras el siguiente valor no sea el inicio de la lista y que el ID actual sea menor que el ID a ingresar
                aux = aux.siguiente; //Pasar al siguiente nodo en la lista
            }
            if(aux.id == id) { //Si el ID ya se encuentra en la lista, indicarlo
                System.out.println("Este id ya esta");
            }
            else if ((aux.siguiente == inicio) && (aux.id < id)) { //Si el ID a ingresar es mayor a los demas, insertarlo al final de la lista
                aux.siguiente = nuevo;
                nuevo.anterior = aux;
                nuevo.siguiente = inicio;
                inicio.anterior = nuevo;
            } else { //Si el ID esta entre dos IDs 
                NodoLista ant = aux.anterior;
                nuevo.anterior = ant;
                ant.siguiente = nuevo;
                nuevo.siguiente = aux;
                aux.anterior = nuevo;
                if ((aux == inicio) && (inicio.id > id)) { //Si el ID a ingresar es menor a los demas, insertarlo al inicio de la lista
                    inicio = nuevo;
                }
            }
        }
    }

    public void eliminar(int id) {
        if (inicio != null) { //Verificar que la lista no este vacia
            NodoLista aux = inicio;
            NodoLista ant = null;
            do { //Se hace por lo menos una vez
                if (aux.id == id) { //Si se encuentra el id a eliminar:
                    if (ant == null) { //Si anterior es igual a nulo entonces hay que borrar el primer valor de la lista
                        if (aux.siguiente == inicio) { //Si el valor siguiente es igual a inicio significa que solo hay un nodo en la lista entonces
                            inicio = null; //solo se indica que inicio de la lista es nulo, se borra
                            break; //y salimos del ciclo para que no se ejecute de nuevo.
                        } else { //sino hay mas valores en la lista
                            ant = aux.anterior; //anterior ahora es el ultimo valor de la lista
                            ant.siguiente = aux.siguiente; //el siguiente de anterior apunta al siguiente del ID a borrar
                            aux = aux.siguiente; //El actual ahora pasa a ser el siguiente
                            aux.anterior = ant; //el anterior del actual ahora es el anterior auxiliar
                            inicio = aux; //y el inicio es el nodo auxiliar acutal
                            ant = null; //auxiliar anterior vuelva a ser nulo
                        }
                    } else { //de lo contario, el ID se encuentra entre dos valor
                        aux.anterior = null; //Eliminamos el puntero del actual al anterior
                        ant.siguiente = aux.siguiente; //El anterior ahora apunta al siguiente del actual
                        aux = aux.siguiente; //y el  valor actual pasa a ser el siguiente
                        aux.anterior = ant;//el apuntador del nuevo actual es el auxiliar anterior
                    }
                } else { //Si no concide con el auxiliar actual
                    ant = aux; //El nodo auxiliar anterior pasa a ser el actual
                    aux = aux.siguiente; // y el actual pasa a ser el siguiente
                }
            } while (aux != inicio);
        }
    }

    public void graficarLCDE() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("POO.txt");
            pw = new PrintWriter(fichero);

            NodoLista lc = inicio;
            pw.println("digraph G {\n");
            pw.println("node[color = orange; style = filled];");
            if (inicio != null) {
                pw.println(lc.id + ";\n");
                lc = lc.siguiente;
                while (lc != inicio) {
                    pw.println(lc.id + "[ label=\"" + lc.id + ". " + lc.nombre + "\"];\n");
                    pw.print(lc.anterior.id + "->" + lc.id + ";\n");
                    pw.print(lc.anterior.id + "->" + lc.id + "[dir=back];\n");
                    lc = lc.siguiente;
                }
                if (lc == inicio) {
                    pw.println(lc.id + "[ label=\"" + lc.id + ". " + lc.nombre + "\"];\n");
                    pw.print(lc.anterior.id + "->" + lc.id + ";\n");
                    pw.print(lc.anterior.id + "->" + lc.id + "[dir=back];\n");
                }
            } else {
                pw.println(0 + "[ label=\"La lista esta vacia. \"];\n");
            }
            pw.println("}");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        try {
            String command = "dot -Tjpg POO.txt -o POO.jpg";
            Process child = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println("ex: " + e.getMessage());
        }
    }
}
