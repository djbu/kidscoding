package ast.visitador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ast.*;

/**
 *
 * @author mbsanchez
 */
public interface Visitador {
    public void visitar(Programa element, Object... params);
    public void visitar(Asignacion element, Object... params);
    public void visitar(Condicional element, Object... params);
    public void visitar(RepitaHasta element, Object... params);
    public void visitar(Suma element, Object... params);
    public void visitar(Resta element, Object... params);
    public void visitar(Multiplicacion element, Object... params);
    public void visitar(Division element, Object... params);
    public void visitar(Igual element, Object... params);
    public void visitar(Menor element, Object... params);
    public void visitar(Mayor element, Object... params);
    public void visitar(MayorIgual element, Object... params);
    public void visitar(MenorIgual element, Object... params);
    public void visitar(NumeroEntero element, Object... params);
    public void visitar(NumeroBool element, Object... params);
    public void visitar(Diferente element, Object... params);
    public void visitar(Y element, Object... params);
    public void visitar(O element, Object... params);
    public void visitar(Variable element, Object... params);
    public void visitar(Declaracion element, Object... params);
    public void visitar(Para element, Object... params);
    public void visitar(Vector element, Object... params);
    public void visitar(Modulo element, Object... params);
    public void visitar(Avanzar element, Object... params);
    
    public void visitar(MirarAbajo element, Object... params);
    public void visitar(MirarArriba element, Object... params);
    public void visitar(MirarDerecha element, Object... params);
    public void visitar(MirarIzquierda element, Object... params);
    
    
}
