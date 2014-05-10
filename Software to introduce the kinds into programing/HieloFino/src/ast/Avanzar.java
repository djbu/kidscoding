package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;

/**
 *
 * @author diego.juliao
 */
public class Avanzar extends Sentencia
{
private Expresion expr;
    
    public Avanzar(Expresion expr,int linea, int columna) 
    {
        super(linea, columna);
        this.expr = expr;
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_Avanzar");
        expr.dump(out, n + 2);}

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public Expresion getExpr() {
        return expr;
    }
    
    
    
}
