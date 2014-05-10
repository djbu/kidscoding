package semantica;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ast.Asignacion;
import ast.Avanzar;
import ast.Condicional;
import ast.Declaracion;
import ast.Diferente;
import ast.Division;
import ast.Expresion;
import ast.MayorIgual;
import ast.Igual;
import ast.Mayor;
import ast.Menor;
import ast.MenorIgual;
import ast.MirarAbajo;
import ast.MirarArriba;
import ast.MirarDerecha;
import ast.MirarIzquierda;
import ast.Modulo;
import ast.Multiplicacion;
import ast.NumeroEntero;
import ast.NumeroBool;
import ast.O;
import ast.Para;
import ast.Programa;
import ast.RepitaHasta;
import ast.Resta;
import ast.Suma;
import ast.Variable;
import ast.Vector;
import ast.Y;
import ast.visitador.Visitador;
import hielofino.inicio;
import tabla.TablaSimbolos;
import util.NucleoLenguaje;

/**
 *
 * @author mbsanchez
 */
public class VisitadorSemantico implements Visitador {

    TablaSimbolos tabla;

    public VisitadorSemantico() {
        tabla = new TablaSimbolos();
        tabla.crearAmbito();
    }

    public TablaSimbolos getTabla() {
        return tabla;
    }

    @Override
    public void visitar(Programa element, Object... params) {
        element.getSentencias().aceptar(this);
    }

    @Override
    public void visitar(Asignacion element, Object... params) {
       
        element.getExpr().aceptar(this);   
        element.getId().aceptar(this);
        
        
        if (!element.getId().getTipoExpr().equals(element.getExpr().getTipoExpr())) {
            SemantErrorReport.getInstancia().semantError(element.getId(),
                    "no se puede convertir un valor de tipo " + element.getExpr().
                    getTipoExpr() + " a tipo " + element.getId().getTipoExpr());
        }
        
        
    }

    @Override
    public void visitar(Condicional element, Object... params) {
        element.getCondicion().aceptar(this);

        //validar que la condición sea de tipo booleana
        if (!element.getCondicion().getTipoExpr().equals(NucleoLenguaje.tipoBool)) {
            SemantErrorReport.getInstancia().semantError(element.getCondicion(),
                    "la condición del \"if\" debe ser un valor de tipo booleano");
            inicio.escribirMsjError("la condición del \"if\" debe ser un valor de tipo booleano");
        }

        element.getEntonces().aceptar(this);

        if (element.getSino() != null) {
            element.getSino().aceptar(this);
        }
    }

    @Override
    public void visitar(RepitaHasta element, Object... params) {
        element.getSentencias().aceptar(this);

        element.getCondicion().aceptar(this);
        //validar que la condición sea de tipo booleana
        if (!element.getCondicion().getTipoExpr().equals(NucleoLenguaje.tipoBool)) {
            SemantErrorReport.getInstancia().semantError(element.getCondicion(),
                    "la condición del repita-hasta debe ser un valor de tipo booleano");
            inicio.escribirMsjError("la condición del repita-hasta debe ser un valor de tipo booleano");
        }
    }
    
    @Override
    public void visitar(Suma element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element,
                    "la operación de adición no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de adición no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoEntero);
    }

    @Override
    public void visitar(Resta element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element,
                    "la operación de sustracción no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de sustracción no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoEntero);
    }

    @Override
    public void visitar(Multiplicacion element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element,
                    "la operación de multiplicación no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de multiplicación no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoEntero);
    }

    @Override
    public void visitar(Division element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element,
                    "la operación de división no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de división no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoEntero);
    }

    @Override
    public void visitar(Igual element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación de igualdad no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de igualdad no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(Menor element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación relacional 'menor que' no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación relacional 'menor que' no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(NumeroEntero element, Object... params) {
        element.setTipoExpr(NucleoLenguaje.tipoEntero);
    }

    @Override
    public void visitar(Variable element, Object... params) {
        Variable sym = (Variable) tabla.buscar(element.getNombre());
        

        if (sym == null) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la variable \"" + element.getNombre() + "\" no ha sido declarada");
            inicio.escribirMsjError("la variable \"" + element.getNombre() + "\" no ha sido declarada");
        } else if (!(sym instanceof Variable)) //verificar que sea una nombre
        {
            SemantErrorReport.getInstancia().semantError(element, 
                    "el identificador \"" + element.getNombre() + "\" es inválido");
            inicio.escribirMsjError("el identificador \"" + element.getNombre() + "\" es inválido");
        }
        
        element.setTipoExpr(sym.getTipoExpr());
    }

    @Override
    public void visitar(NumeroBool element, Object... params) {
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(Mayor element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación relacional 'mayor que' no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación relacional 'mayor que' no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(MayorIgual element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación relacional 'mayor igual que' no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación relacional 'mayor igual que' no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
        
    }

    @Override
    public void visitar(MenorIgual element, Object... params) {
        
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación de Menor Igual no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de Menor Igual no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(Diferente element, Object... params) {
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación de diferente no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de diferente no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(Y element, Object... params) {
        
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoBool)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoBool)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación de Y lógico no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de Y lógico no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
    }

    @Override
    public void visitar(O element, Object... params) {
        
        
        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoBool)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoBool)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación de O lógico no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de O lógico no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoBool);
        
    }

    @Override
    public void visitar(Declaracion element, Object... params) {
        Object sym = tabla.buscar(element.getId().getNombre());
        element.getExpr().aceptar(this);
        
        
        //no olvidar
        //Agregar el id a la tabla de símbolos
        if (sym == null) {
            
            tabla.agregarId(element.getId().getNombre(), element.getId());
            if(element.getExpr() instanceof NumeroEntero){
                
            element.getId().setTipoExpr(NucleoLenguaje.tipoEntero);
            
            //element.getId().setTipoExpr(NucleoLenguaje.tipoEntero);
             //SemantErrorReport.getInstancia().semantError(element, "la variable \"" + element.getId().getNombre() + "\" no ha sido declarada");            
            }
            else {
            element.getId().setTipoExpr(NucleoLenguaje.tipoBool);
            }
            
            
        }
        else{
        SemantErrorReport.getInstancia().semantError(element, "la variable \"" + element.getId().getNombre() + "\" ya ha sido declarada");            
        inicio.escribirMsjError("la variable \"" + element.getId().getNombre() + "\" ya ha sido declarada");
        }

        element.getId().aceptar(this);
        //chequear el tipo del id y la expresión
       /* if (!element.getId().getTipoExpr().equals(element.getExpr().getTipoExpr())) {
            SemantErrorReport.getInstancia().semantError(element.getId(),
                    "no se puede convertir un valor de tipo " + element.getExpr().
                    getTipoExpr() + " a tipo " + element.getId().getTipoExpr());
        }*/

    }

    @Override
    public void visitar(Para element, Object... params) {
         element.getSentencias().aceptar(this);

        element.getInicializacion().aceptar(this, params);
        element.getPaso().aceptar(this);
        element.getVerificacion().aceptar(this, params);
        
        
        //validar que los iniciacion sea una asignacion
        if (!element.getVerificacion().getTipoExpr().equals(NucleoLenguaje.tipoBool)) {
            SemantErrorReport.getInstancia().semantError(element.getVerificacion(),
                    "la condición del verificacion debe ser un valor de tipo booleano");
            inicio.escribirMsjError("la condición del verificacion debe ser un valor de tipo booleano");
        }
    
    }

    @Override
    public void visitar(Vector element, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitar(Modulo element, Object... params) {

        element.getExpr1().aceptar(this);
        element.getExpr2().aceptar(this);

        if (!element.getExpr1().getTipoExpr().equals(NucleoLenguaje.tipoEntero)
                || !element.getExpr2().getTipoExpr().equals(NucleoLenguaje.tipoEntero)) {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la operación de Modulo no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
            inicio.escribirMsjError("la operación de Modulo no se puede aplicar a valores de tipo "
                    + element.getExpr1().getTipoExpr() + " y " + element.getExpr2().getTipoExpr());
        }
        element.setTipoExpr(NucleoLenguaje.tipoEntero);

        
    }

    @Override
    public void visitar(Avanzar element, Object... params) {
    
        element.getExpr().aceptar(this);
                
        if (!element.getExpr().getTipoExpr().equals(NucleoLenguaje.tipoEntero))
        {
            SemantErrorReport.getInstancia().semantError(element, 
                    "la funcion Avanzar no resive parametros del tipo"
                    + element.getExpr().getTipoExpr());
            inicio.escribirMsjError("la funcion Avanzar no recibe parametros del tipo"
                    + element.getExpr().getTipoExpr());
        }
        
    }

    @Override
    public void visitar(MirarAbajo element, Object... params) {
        //yo soy inmortal
    }

    @Override
    public void visitar(MirarArriba element, Object... params) {
    }

    @Override
    public void visitar(MirarDerecha element, Object... params) {
    }

    @Override
    public void visitar(MirarIzquierda element, Object... params) {
    }
    
}
