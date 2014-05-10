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
public class Suma extends ExpresionBinaria{
    private int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    public Suma(Expresion expr1, Expresion expr2, int linea, int columna) {
        super(expr1, expr2, linea, columna);
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_suma");
        if(getTipoExpr()!=null) out.println(Utilidades.pad(n+2)+"tipo_expr: "+getTipoExpr());
        getExpr1().dump(out, n+2);
        getExpr2().dump(out, n+2);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }
}
