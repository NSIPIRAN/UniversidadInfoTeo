/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.LinkedList;

/**
 *
 * @author nath
 */
public class Vivos {
    LinkedList<Nodo> lista= new LinkedList<>();
    void agregar(Nodo e) {
        lista.addFirst(e);
       
    }
    public Nodo quitar() {
        return lista.removeFirst();
    }
    boolean pilaVacia(){
        return lista.isEmpty();
    }
    void agregar(Nodo raiz, int i) {
        lista.addFirst(raiz);  
    }
    
}
