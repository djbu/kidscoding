/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantica;

import Sintactico.*;
import ast.Programa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import Lexico.*;
import util.Utilidades;

/**
 *
 * @author Administrador
 */
public class Semant {
    /*public static void main(String[] args){
       FileInputStream input=null;
        
        try {
            input = new FileInputStream(new File(args[0]));
            
            Yylex lexer = new Yylex(input);
            Yylex.setNombreArchivo(args[0]);
            TinyParser parser = new TinyParser(lexer);
            
            
            
            Programa result = (Programa) (parser.parse().value);
            if (parser.getOmerrs() > 0) {
		System.err.println("La compilación ha terminado con "+ parser.getOmerrs() +" errores");
		System.exit(1);
	    }
            AnalizadorSemantico sem = new AnalizadorSemantico(result);
            sem.analizar();
            
            if(SemantErrorReport.getInstancia().tieneErrores()){
                System.err.println("La compilación ha terminado con "+ SemantErrorReport.getInstancia().getErrores() +" errores");
		System.exit(1);
            }
            result.dump(System.out, 0);
            System.out.println("La compilación ha finalizado correctamente");
        }catch (URISyntaxException ex) {
            System.out.println("Ha ocurrido un error de formato en la ruta del archivo");
        }catch (FileNotFoundException ex) {
            System.out.println("No se pudo abrir el archivo de entrada");
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Usar: parser.Parser programa.tny");
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
	    Utilidades.fatalError("Error inesperado en el analizador sintáctico");
        }
    }*/
}
