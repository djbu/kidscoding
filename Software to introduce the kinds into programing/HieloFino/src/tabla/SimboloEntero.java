/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla;

/**
 *
 * @author Administrador
 */
public class SimboloEntero extends SimboloAbstracto{

    public SimboloEntero(int indice, String texto, int linea, int columna) {
        super(indice, texto, linea, columna);
    }

    public SimboloEntero(String texto, int linea, int columna) {
        super(texto, linea, columna);
    }

    @Override
    public Object clone() {
        return new SimboloEntero(indice, texto, linea, columna);
    }
}
