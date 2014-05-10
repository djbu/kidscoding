/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla;

/**
 *
 * @author Administrador
 */
public class TablaEntero extends TablaAbstracta{
    @Override
    protected SimboloAbstracto getNuevoSimbolo(String texto, int indice, int linea, int columna) {
        return new SimboloEntero(indice, texto, linea, columna);
    }    
}
