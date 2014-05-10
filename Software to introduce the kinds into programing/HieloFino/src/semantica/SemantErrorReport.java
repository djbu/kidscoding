/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantica;

import ast.NodoArbol;
import java.io.PrintStream;

/**
 *
 * @author Administrador
 */
public class SemantErrorReport {
    protected int errores;
    protected PrintStream errorStream;
    private static SemantErrorReport report=null;

    private SemantErrorReport() {
	errores = 0;
	errorStream = System.err;
    }
    
    public void SemantErrorReportReset()
    {
        report = null;
    }
    
    public static SemantErrorReport getInstancia(){
        if(report==null)
            report = new SemantErrorReport();
        
        return report;
    }

    public PrintStream semantError(NodoArbol t) {
	errorStream.println(t.getLinea() + ":"+ t.getColumna() +": ");
	return semantError();
    }
        
    public PrintStream semantError(NodoArbol t, String mensaje) {
	errorStream.println("Error en línea " + t.getLinea() + ":"+ t.getColumna() +": "+mensaje);
	return semantError();
    }
    
    public PrintStream semantError() {
	errores++;
	return errorStream;
    }

    public boolean tieneErrores() {
	return errores != 0;
    }

    public int getErrores() {
        return errores;
    }
}
