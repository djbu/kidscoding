/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import tabla.TablaSimbolos;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public class Menor extends ExpresionBinaria{
    
    private boolean valor;

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }
    
    public Menor(Expresion expr1, Expresion expr2, int linea, int columna) {
        super(expr1, expr2, linea, columna);
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_menor_que");
        if(getTipoExpr()!=null) out.println(Utilidades.pad(n+2)+"tipo_expr: "+getTipoExpr());
        getExpr1().dump(out, n+2);
        getExpr2().dump(out, n+2);
    }

    
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    
    public void checkSemantica(TablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
