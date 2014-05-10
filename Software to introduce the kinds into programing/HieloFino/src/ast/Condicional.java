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
public class Condicional extends Sentencia{
    private Expresion condicion;
    private ListaSentencia entonces;
    private ListaSentencia sino;

    public Condicional(Expresion condicion, ListaSentencia entonces, ListaSentencia sino, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.entonces = entonces;
        this.sino = sino;
    }
   
    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_condicional");
        condicion.dump(out, n+4);
        out.println(Utilidades.pad(n+2) + "_entonces");
        entonces.dump(out, n+4);
        if(sino!=null){
            out.println(Utilidades.pad(n+2) + "_sino");
            sino.dump(out, n+4);
        }
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public Expresion getCondicion() {
        return condicion;
    }

    public ListaSentencia getEntonces() {
        return entonces;
    }

    public ListaSentencia getSino() {
        return sino;
    }
}
