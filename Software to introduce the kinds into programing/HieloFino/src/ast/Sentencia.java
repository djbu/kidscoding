/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import tabla.TablaSimbolos;

/**
 *
 * @author Administrador
 */
public abstract class Sentencia extends NodoArbol{
    public Sentencia(int linea, int columna) {
        super(linea, columna);
    }
}
