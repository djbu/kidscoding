/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import ast.visitador.Visitador;
import java.io.PrintStream;

/**
 *
 * @author diego.juliao
 */
public class MirarIzquierda extends Sentencia
{
    public MirarIzquierda(int linea, int columna)
    {
        super(linea, columna);
    }

    @Override
    public void dump(PrintStream out, int n) {
        dumpLineaColumna(out, n);
        out.println("_MirarIzquierda");
    }

    @Override
    public void aceptar(Visitador visit, Object... params) {
        visit.visitar(this, params);
    }
}
