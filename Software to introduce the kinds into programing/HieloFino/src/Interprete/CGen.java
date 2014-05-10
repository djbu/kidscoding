/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Interprete.VisitadorCGen;
import ast.Programa;
import ast.visitador.Visitador;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import tabla.TablaSimbolos;
import util.NucleoLenguaje;

/**
 *
 * @author Administrador
 */
public class CGen {
    private Programa program;
    private TablaSimbolos tabla;
    
    public CGen(Programa program, TablaSimbolos tabla){
        this.program = program;
        this.tabla = tabla;
    }
    
    public void generarCodigo(PrintStream cout){         
        Visitador visit=new VisitadorCGen( tabla);
        program.aceptar(visit, cout);
        
        
    }
}
