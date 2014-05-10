/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import tabla.SimboloAbstracto;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public class Vector extends Expresion{
    private SimboloAbstracto nombre;

    public Vector(SimboloAbstracto nombre, int linea, int columna) {
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