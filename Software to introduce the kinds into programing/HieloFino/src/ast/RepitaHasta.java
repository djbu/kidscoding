/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public class RepitaHasta extends Sentencia{
    private Expresion condicion;
    private ListaSentencia sentencias;

    public RepitaHasta(Expresion condicion, ListaSentencia sentencias, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_repita-hasta");
        out.println(Utilidades.pad(n+2) + "_sentencias");
        sentencias.dump(out, n+4);
        condicion.dump(out, n+2);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public Expresion getCondicion() {
        return condicion;
    }

    public ListaSentencia getSentencias() {
        return sentencias;
    }
}
