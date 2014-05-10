/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla;

/**
 *
 * @author Administrador
 */
public abstract class SimboloAbstracto {
    protected int indice;
    protected String texto;
    protected int linea;
    protected int columna;

    public SimboloAbstracto(int indice, String texto, int linea, int columna) {
        this.indice = indice;
        this.texto = texto;
        this.linea = linea;
        this.columna = columna;
    }

    public SimboloAbstracto(String texto, int linea, int columna) {
        this.texto = texto;
        this.linea = linea;
        this.columna = columna;
        indice = -1;
    }
       
    public boolean equalsTexto(String str){
        return texto.equals(str);
    }
    
    public boolean equalsIndice(int index){
        return indice==index;
    }
    
    @Override
    public boolean equals(Object otro) {
	return (otro instanceof SimboloAbstracto) && 
	    ((SimboloAbstracto)otro).indice == this.indice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.indice;
        hash = 29 * hash + (this.texto != null ? this.texto.hashCode() : 0);
        return hash;
    }
    
    public String getTexto(){
        return texto;
    }
    
    public int getIndice(){
        return indice;
    }

    public int getColumna() {
        return columna;
    }

    public int getLinea() {
        return linea;
    }
    
    @Override
    public String toString(){
        return texto;
    }
    
    @Override
    public abstract Object clone();
}
