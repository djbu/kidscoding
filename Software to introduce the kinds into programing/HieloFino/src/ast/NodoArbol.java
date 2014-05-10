/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import tabla.SimboloAbstracto;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public abstract class NodoArbol {
    protected int linea, columna;

    public NodoArbol(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }

    public int getColumna() {
        return columna;
    }

    public int getLinea() {
        return linea;
    }
    
    protected void dump_SimboloAbstracto(PrintStream out, int n, SimboloAbstracto sym) {
	out.print(Utilidades.pad(n)); 
	out.println(sym.getTexto());
    }
    
    protected void dumpLineaColumna(PrintStream out, int n) {
	out.print(Utilidades.pad(n) + "#" + linea + ":"+columna+": ");
    }
       
    public abstract void dump(PrintStream out, int n);
    public abstract void aceptar(Visitador visit, Object... params);
}
