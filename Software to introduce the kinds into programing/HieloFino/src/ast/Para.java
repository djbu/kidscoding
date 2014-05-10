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
public class Para extends Sentencia{
    
    private Sentencia inicializacion;    
    private Sentencia paso;
    private Expresion verificacion;
    private ListaSentencia sentencias;

    public Para(Sentencia inicializacion, Sentencia paso, Expresion verificacion, ListaSentencia sentencias, int linea, int columna) {
        super(linea, columna);        
        this.inicializacion = inicializacion;
        this.paso= paso;
        this.verificacion=verificacion;
        this.sentencias = sentencias;
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_repita_para");
        out.println(Utilidades.pad(n+2) + "_sentencias");
        sentencias.dump(out, n+4);
        
        
        inicializacion.dump(out, n+2);
        paso.dump(out, n+2);
        verificacion.dump(out, n+2);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }

    public Expresion getVerificacion() {
        return verificacion;
    }

    public ListaSentencia getSentencias() {
        return sentencias;
    }

    public Sentencia getInicializacion() {
        return inicializacion;
    }

    public Sentencia getPaso() {
        return paso;
    }
    
    
    
}
