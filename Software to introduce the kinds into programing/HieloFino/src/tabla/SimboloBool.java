
package tabla;

/**
 *
 * @author Administrador
 */
public class SimboloBool extends SimboloAbstracto{

    public SimboloBool(int indice, String texto, int linea, int columna) {
        super(indice, texto, linea, columna);
    }

    public SimboloBool(String texto, int linea, int columna) {
        super(texto, linea, columna);
    }

    @Override
    public Object clone() {
        return new SimboloBool(indice, texto, linea, columna);
    }
}
