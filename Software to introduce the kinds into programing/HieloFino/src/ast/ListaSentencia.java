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
public class ListaSentencia extends NodoLista<Sentencia> {
    public ListaSentencia(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public void dump(PrintStream out, int n) {
        for(Sentencia s: lista){
            s.dump(out, n);
        }
    }    

    @Override
    public void aceptar(Visitador visit, Object... params) {
        for(Sentencia s:lista){
            s.aceptar(visit, params);
        }
    }
}
