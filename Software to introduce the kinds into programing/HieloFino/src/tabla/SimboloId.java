/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla;

/**
 *
 * @author Administrador
 */
public class SimboloId extends SimboloAbstracto{

    public SimboloId(int indice, String texto, int linea, int columna) {
        super(indice, texto, linea, columna);
    }

    public SimboloId(String texto, int linea, int columna) {
        super(texto, linea, columna);
    }

    @Override
    public Object clone() {
        return new SimboloId(indice, texto, linea, columna);
    }    
}
