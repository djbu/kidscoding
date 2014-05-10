/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;

/**
 *
 * @author Administrador
 */
public class Programa extends NodoArbol{
    private ListaSentencia sentencias;

    public Programa(ListaSentencia sentencias, int linea, int columna) {
        super(linea, columna);
        this.sentencias = sentencias;
    }
    
    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_programa");
        sentencias.dump(out, n + 2);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public ListaSentencia getSentencias() {
        return sentencias;
    }
}

    