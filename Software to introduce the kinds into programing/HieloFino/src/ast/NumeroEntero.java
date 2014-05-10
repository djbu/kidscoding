/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import tabla.SimboloAbstracto;
import tabla.TablaSimbolos;
import util.NucleoLenguaje;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public class NumeroEntero extends Expresion{
    private SimboloAbstracto token;
    private int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NumeroEntero(SimboloAbstracto token, int linea, int columna) {
        super(linea, columna);
        this.token = token;
        
        setTipoExpr( NucleoLenguaje.tipoEntero );
    }
    
    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_literal_entero");
        if(getTipoExpr()!=null) out.println(Utilidades.pad(n+2)+"tipo_expr: "+getTipoExpr());
        dump_SimboloAbstracto(out, n + 2, token);
    }

    
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public SimboloAbstracto getToken() {
        return token;
    }

    
    public void checkSemantica(TablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
