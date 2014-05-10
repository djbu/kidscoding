/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;
import tabla.SimboloAbstracto;

/**
 *
 * @author Administrador
 */
public class ParamFormal extends NodoArbol{
    private SimboloAbstracto tipo;
    private SimboloAbstracto id;
    private boolean direccion;

    public ParamFormal(SimboloAbstracto tipo, SimboloAbstracto id, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.id = id;
        this.direccion = false;
    }

    public ParamFormal(SimboloAbstracto tipo, SimboloAbstracto id, boolean esDireccion, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.id = id;
        this.direccion = esDireccion;
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_parametro_formal");
        dump_SimboloAbstracto(out, n+2, tipo);
        dump_SimboloAbstracto(out, n+2, id);
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        throw new UnsupportedOperationException("Método no soportado");
    }

    public SimboloAbstracto getTipo() {
        return tipo;
    }

    public SimboloAbstracto getId() {
        return id;
    }

    public boolean esDireccion() {
        return direccion;
    }
}
