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
public class ListaParamFormal extends NodoLista<ParamFormal> {
    public ListaParamFormal(int linea, int columna) {
        super(linea, columna);
    }
    
    @Override
    public void dump(PrintStream out, int n) {
        for(ParamFormal pf : lista){
            pf.dump(out, n);
        }
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        throw new UnsupportedOperationException("Método no soportado");
    }
}
