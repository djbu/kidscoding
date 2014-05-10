/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.PrintStream;
import tabla.SimboloAbstracto;
import java_cup.runtime.Symbol;
import Tokens.sym;

/**
 *
 * @author Administrador
 */
public class Utilidades {

    private static String padding = "                                                                                ";

    public static String tokenEnTexto(Symbol s) {
        switch (s.sym) {
            /*
            

terminal MIRAR_ARRIBA;
terminal MIRAR_ABAJO;
terminal MIRAR_IZQ;
terminal MIRAR_DER;
terminal AVANZAR;
terminal INT;
terminal BOOL;
terminal COMA;
terminal ID;
terminal Y;
terminal O;
terminal SUMA;
terminal RESTA;
terminal MULT;
terminal DIV;
terminal SI;
terminal THEN;
terminal ENDIF;
terminal SINO;
terminal REPITA;
terminal HASTA;
terminal NUM;
terminal TRUE;
terminal FALSE;
terminal PARA;
terminal INICIOPARA ;
terminal FINPARA;
terminal IGUAL_LOGICO;


            */
            case sym.MIRAR_ARRIBA:
                return "MirarArriba";
            case sym.Y:
                return "Y";            
            case sym.O:
                return "O";            
            case sym.MIRAR_ABAJO:
                return "MirarAbajo";
            case sym.MIRAR_DER:
                return "MirarDerecha";
            case sym.MIRAR_IZQ:
                return "MirarIzquierda";
            case sym.PROGRAMA:
                return "PROGRAMA";
            case sym.EOF:
                return "FIN_ARCHIVO";
            case sym.SUMA:
                return "+";
            case sym.RESTA:
                return "-";
            case sym.DIV:
                return "/";
            case sym.MULT:
                return "*";
            case sym.PUNTO_COMA:
                return ";";
            case sym.MENOR:
                return "<";
            case sym.MENOR_IGUAL:
                return "<=";
            case sym.MAYOR:
                return ">";
            case sym.MAYOR_IGUAL:
                return ">=";                               
            case sym.DIFERENTE:
                return "<>";
            case sym.IGUAL:
                return "=";
            case sym.IGUAL_LOGICO:
                return "==";                
            case sym.PARENT_ABRE:
                return "(";
            case sym.PARENT_CIERRA:
                return ")";
            case sym.LLAVE_ABRE:
                return "{";
            case sym.LLAVE_CIERRA:
                return "}";
            case sym.COR_ABRE:
                return "[";
            case sym.COR_CIERRA:
                return "]";
            case sym.COMA:
                return ",";
            case sym.NUM:
                return "ENTERO";
            case sym.ID:
                return "ID";
            case sym.SI:
                return "SI";
            case sym.THEN:
                return "ENTONCES";
            case sym.SINO:
                return "SINO";
            case sym.ENDIF:
                return "FINSI";
            case sym.REPITA:
                return "REPITA";
            case sym.PARA:
                return "PARA";           
            case sym.HASTA:
                return "HASTA";
            default:
                return ("<Token inválido: " + s.sym + ">");
        }
    }

    public static String imprimirToken(Symbol s) {
        System.err.print(tokenEnTexto(s));
        SimboloAbstracto sy = (SimboloAbstracto) s.value;

        String msj = null;
        
        switch (s.sym) {
            case Tokens.sym.INT:
            case Tokens.sym.ID:
                msj = " = " + sy.getTexto();
                
                break;
            case Tokens.sym.error:
                msj = " = \"" + sy.getTexto() +"\"";                
                break;
        }
        
        System.err.print(msj);
        System.err.println("");  
      
        return msj;
    }

    public static void dumpToken(PrintStream str, Symbol s) {
        /*SimboloAbstracto sy = (SimboloAbstracto) s.value;
        str.print("#" + sym.getLinea() + ":" + sym.getColumna() + ": " + tokenEnTexto(s));

        switch (s.sym) {
            case sy.LIT_ENTERO:
            case sy.IDENTIFICADOR:
                str.print(" = " + sy.getTexto());
                break;
            case sy.error:
            case sy.ERROR:
                str.print(" = \"");
                str.print(sy.getTexto());
                str.print("\"");
                break;
        }
        str.println("");      PENDIENTEEEEE CON ESTOOOOOOO*/
        
        
    }

    public static String pad(int n) {
        if (n > 80) {
            return padding;
        }
        if (n < 0) {
            return "";
        }
        return padding.substring(0, n);
    }

    public static void fatalError(String msg) {
        (new Throwable(msg)).printStackTrace();
        System.exit(1);
    }
}
