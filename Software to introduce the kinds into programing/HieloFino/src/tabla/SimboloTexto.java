/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla;

/**
 *
 * @author Administrador
 */
public class SimboloTexto extends SimboloAbstracto {
    public SimboloTexto(int indice, String texto, int linea, int columna) {
        super(indice, texto, linea, columna);
    }

    public SimboloTexto(String texto, int linea, int columna) {
        super(texto, linea, columna);
    }

    @Override
    public Object clone() {
        return new SimboloTexto(indice, texto, linea, columna);
    }    
}
