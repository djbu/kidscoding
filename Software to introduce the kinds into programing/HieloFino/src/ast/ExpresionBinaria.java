/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author Administrador
 */
public abstract class ExpresionBinaria extends Expresion{
    private Expresion expr1;
    private Expresion expr2;

    public ExpresionBinaria(Expresion expr1, Expresion expr2, int linea, int columna) {
        super(linea, columna);
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public Expresion getExpr1() {
        return expr1;
    }

    public Expresion getExpr2() {
        return expr2;
    }
}
