/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import tabla.SimboloAbstracto;
import tabla.TablaSimbolos;

/**
 *
 * @author Administrador
 */
public abstract class Expresion extends NodoArbol{
    private SimboloAbstracto tipoExpr;

    public Expresion(int linea, int columna) {
        super(linea, columna);
        tipoExpr = null;
    }  

    public SimboloAbstracto getTipoExpr() {
        return tipoExpr;
    }

    public void setTipoExpr(SimboloAbstracto tipoExpr) {
        this.tipoExpr = tipoExpr;
    }
}
