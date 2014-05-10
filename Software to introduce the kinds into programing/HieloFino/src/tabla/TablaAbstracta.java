/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public abstract class TablaAbstracta {
    private int idx;   
    private static int TAM_MAX_SYMB=5000;
    HashMap<String, SimboloAbstracto> tabla;
    public static TablaEntero intTabla = new TablaEntero();
    public static TablaId idTabla = new TablaId();
    public static TablaBool boolTabla=new TablaBool();
    
    
    public TablaAbstracta() {
        tabla = new HashMap<String, SimboloAbstracto>();
        idx = 0;
    }
    
    public SimboloAbstracto agregarSimbolo(String texto, int linea, int columna){
        return agregarSimbolo(texto, TAM_MAX_SYMB, linea, columna);
    }
    
    public SimboloAbstracto agregarSimbolo(String texto, int tam, int linea, int columna){
        SimboloAbstracto sym = null;
        
        
        
        if(!tabla.containsKey(texto)){
            sym = getNuevoSimbolo(texto, idx++, linea, columna);
            tabla.put(texto, sym);
        }else{
            sym = (SimboloAbstracto) tabla.get(texto).clone();
            sym.linea = linea;
            sym.columna = columna;
        }
        
        return sym;
    }

    public Collection<SimboloAbstracto> getSimbolos() {
        return tabla.values();
    }
    
    public SimboloAbstracto getSimbolo(String texto){
        return tabla.get(texto);
    }
    
    public SimboloAbstracto getSimbolo(int indice){
        for(SimboloAbstracto sym: tabla.values()){
            if(sym.equalsIndice(indice))
                return sym;
        }
        
        return null;
    }
    
    protected abstract SimboloAbstracto getNuevoSimbolo(String texto, int indice, int linea, int columna);
}
