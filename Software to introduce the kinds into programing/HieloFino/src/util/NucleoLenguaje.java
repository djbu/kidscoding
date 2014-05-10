/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import ast.ListaParamFormal;
import ast.ParamFormal;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import tabla.SimboloAbstracto;
import tabla.SimboloId;
import tabla.TablaAbstracta;
import tabla.TablaSimbolos;

/**
 *
 * @author Administrador
 */
public class NucleoLenguaje {
    public static final SimboloAbstracto tipoEntero = TablaAbstracta.idTabla.agregarSimbolo("_entero", 0, 0);
    public static final SimboloAbstracto tipoBool = TablaAbstracta.idTabla.agregarSimbolo("_logico", 0, 0);
    
    private static NucleoLenguaje nucleo=null;
    

    private NucleoLenguaje() {    
        //agregarFuncBasicas();
    }
    
    
    
    public static NucleoLenguaje getInstancia(){
        if(nucleo == null){
            nucleo = new NucleoLenguaje();
        }
        
        return nucleo;
    }
    
    
    
    
    
    
}
