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
public class POO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListaCircularDoble listirijilla = new ListaCircularDoble();
        listirijilla.insertar(1, "1. primero");
        listirijilla.insertar(2, "2. segundo");
        listirijilla.insertar(4, "4. tercero");
        listirijilla.insertar(3, "3. cuarto");
        //listirijilla.eliminar(2);
        listirijilla.graficarLCDE();
        
        /*
        * 
        * 
        * Para generar las grafica es probable que no les aparezca, primero deben instalar graphviz
        * Si ya tienen graphviz instalado y no les genera la imagen, intentar ejecutar netbeans como administrador
        * 
        *
        */
    }
    
}
