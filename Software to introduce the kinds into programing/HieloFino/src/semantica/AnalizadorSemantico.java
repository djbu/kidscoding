/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantica;


import ast.Programa;
import java.util.HashMap;
import tabla.SimboloAbstracto;
import tabla.TablaSimbolos;
import util.NucleoLenguaje;

/**
 *
 * @author Administrador
 */
public class AnalizadorSemantico {
    private Programa program;
    private TablaSimbolos tabla;
       
    public AnalizadorSemantico(Programa program) {
        this.program = program;
    }  
    
    private void instalarFuncBasicas(){   
        //agregar los métodos básicos a la tabla.

        
    }
    
    public int analizar(){
        VisitadorSemantico visit = new VisitadorSemantico();
        tabla = visit.getTabla();
        instalarFuncBasicas();
        program.aceptar(visit);
        
        return SemantErrorReport.getInstancia().errores;
    }
    
    public TablaSimbolos getTabla() {
        return tabla;
    }
}
