package tabla;

import java.util.Stack;
import java.util.HashMap;
import util.Utilidades;

public class TablaSimbolos {
    private Stack<HashMap<SimboloAbstracto, Object>> tbl;
    
    public TablaSimbolos() {
	tbl = new Stack<HashMap<SimboloAbstracto, Object>>();
    }
    
    public void crearAmbito() {
	tbl.push(new HashMap<SimboloAbstracto, Object>());
    }

    public void quitarAmbito() {
	if (tbl.empty()) {
	    Utilidades.fatalError("salirAmbito: no se puede remover el ámbito de la tabla.");
	}
	tbl.pop();
    }

    public void agregarId(SimboloAbstracto id, Object info) {
	if (tbl.empty()) {
	    Utilidades.fatalError("agregarId: no se puede agregar id sin un ámbito.");
	}
	tbl.peek().put(id, info);
    }

    public Object buscar(SimboloAbstracto sym) {
	if (tbl.empty()) {
	    Utilidades.fatalError("buscar: no existen ámbitos en la tabla.");
	}

	for (int i = tbl.size() - 1; i >= 0; i--) {
	    Object info = tbl.elementAt(i).get(sym);
	    if (info != null) return info;
	}
	return null;
    }

    public Object buscarAmbitoActual(SimboloAbstracto sym) {
	if (tbl.empty()) {
	    Utilidades.fatalError("explorar: no existen ámbitos en la tabla.");
	}
	return tbl.peek().get(sym);
    }
    
    @Override
    public String toString() {
	String res = "";
	for (int i = tbl.size() - 1, j = 0; i >= 0; i--, j++) {
	    res += "Scope " + j + ": " + tbl.elementAt(i) + "\n";
	}
	return res;
    }
    public void combinar(TablaSimbolos table){
        HashMap<SimboloAbstracto, Object> env = table.tbl.peek();
        env.putAll(tbl.peek());
    }
    
    public TablaSimbolos copy(){
        TablaSimbolos table = new TablaSimbolos();
        for (int i = 0; i < tbl.size(); i++) {
            table.crearAmbito();
            table.tbl.peek().putAll(tbl.elementAt(i));
        }
        
        return table;
    }
}