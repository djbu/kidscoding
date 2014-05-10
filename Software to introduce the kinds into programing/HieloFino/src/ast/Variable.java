/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import tabla.SimboloAbstracto;
import util.NucleoLenguaje;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public class Variable extends Expresion{
    private SimboloAbstracto nombre;
    private int valor;
    private boolean valor2;
    
    public int getValor() {
        return valor;
    }
    
    

    public void setValor(int valor) {
        this.valor = valor;
        this.setTipoExpr(NucleoLenguaje.tipoEntero);
    }

    public boolean getValor2() {
        return valor2;
    }

    public void setValor2(boolean valor2) {
        this.valor2 = valor2;
        this.setTipoExpr(NucleoLenguaje.tipoBool);
    }
    

    public Variable(SimboloAbstracto nombre, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_variable");
        if(getTipoExpr()!=null) out.println(Utilidades.pad(n+2)+"tipo_expr: "+getTipoExpr());
        dump_SimboloAbstracto(out, n + 2, nombre);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public SimboloAbstracto getNombre() {
        return nombre;
    }
}