/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public abstract class NodoLista<T> extends NodoArbol{
    protected ArrayList<T> lista;
    
    public NodoLista(int linea, int columna) {
        super(linea, columna);
        lista = new ArrayList<T>();
    }

    public ArrayList<T> getLista() {
        return lista;
    }
    
    public int getLongitud(){
        return lista.size();
    }
    
    public void agregarElemento(T element){
        lista.add(element);
    }
    
    public String toString(){
        return lista.toString();
    }
}
