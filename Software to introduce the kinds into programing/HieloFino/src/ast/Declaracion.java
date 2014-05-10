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
public class Declaracion extends Sentencia{
    private Variable id;
    private Expresion expr;

    public Declaracion(Variable id, Expresion expr, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.expr = expr;
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_declaracion");
        id.dump(out, n + 2);
        expr.dump(out, n + 2);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public Variable getId() {
        return id;
    }

    public Expresion getExpr() {
        return expr;
    }
}
