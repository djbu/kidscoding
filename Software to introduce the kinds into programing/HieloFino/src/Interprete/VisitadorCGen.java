package Interprete;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import ast.*;
import ast.visitador.Visitador;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import hielofino.ListadeMovimientos;
import hielofino.Play;
import hielofino.inicio;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import tabla.SimboloAbstracto;
import tabla.TablaSimbolos;
import util.NucleoLenguaje;

/**
 *
 * 
 */
public class VisitadorCGen implements Visitador {

    TablaSimbolos tabla;
    private int dirMirada;
    public static ListadeMovimientos list;

    public VisitadorCGen(TablaSimbolos tabla) {
        this.tabla = tabla;
        list =new ListadeMovimientos();
    }
    
    
    
    @Override
    public void visitar(Programa element, Object... params) {
        //lo primero que se debe tomar en cuenta es organizar el ambiente de ejecucion 
        //preludio estandar!! 
        PrintStream cout=(PrintStream)params[0];
        element.getSentencias().aceptar(this, params);
        
        
    }

    @Override    
    public void visitar(Asignacion element, Object... params) {
        
     element.getExpr().aceptar(this, params);
        System.out.println("valor antess " +getValorr( element.getExpr()));
        if(element.getExpr().getTipoExpr().getTexto().compareToIgnoreCase("_entero")==0){
        element.getId().setValor(getValorr(element.getExpr()));
        System.out.println("entro en asignacion entera");
        inicio.escribirMsjError("Interprete: entro en asignacion entera");
        }else{
            System.out.println("entro en asignacion booleana");
            inicio.escribirMsjError("Interprete entro en asignacion booleana");
            element.getId().setValor2( getValorrr(element.getExpr()));
        }
     
        tabla.agregarId(element.getId().getNombre(), element.getId());
                
           
            
     }

    @Override
    public void visitar(Condicional element, Object... params) {
        
            element.getCondicion().aceptar(this, params);
            boolean resulCond = getValorrr(element.getCondicion());
            
         if( resulCond ) 
         {  
            element.getEntonces().aceptar(this, params);
         }
         else
         {
            if(element.getSino()!=null)
            {
                element.getSino().aceptar(this, params);
            }
        }
    }

    @Override
    public void visitar(RepitaHasta element, Object... params) {
        
        //probando
        
        
        //element.aceptar(this, params);
        
            element.getSentencias().aceptar((Visitador) this, params);            
            
        element.getCondicion().aceptar(this, params);    
            if(!getValorrr(element.getCondicion()))
            {
                inicio.escribirMsjError("Interprete: se repite la condicion del repita hasta, aun es falso");
            element.aceptar(this, params);
            }
          //  CGenSupport.nextLine();            
        //    element.getEntonces().aceptar(this, sout);
            //CGenSupport.restoreLastAndSaveCurrentLineCounter();
            //
            
        
        
    }

    
    @Override
    public void visitar(Suma element, Object... params) {
        
        element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Sumando "+getValorr(element.getExpr1())+" + "+getValorr(element.getExpr2()));
       element.setValor(getValorr(element.getExpr1())+getValorr(element.getExpr2()));                                 
    }

    @Override
    public void visitar(Resta element, Object... params) {
        
    element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        
        inicio.escribirMsjError("Interprete: Restando "+getValorr(element.getExpr1())+" - "+getValorr(element.getExpr2()));
     element.setValor(getValorr(element.getExpr1())-getValorr(element.getExpr2()));
    
            
    
    
    }

    @Override
    public void visitar(Multiplicacion element, Object... params) {
        
        element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
     
        inicio.escribirMsjError("Interprete: Multiplicando "+getValorr(element.getExpr1())+" * "+getValorr(element.getExpr2()));
        element.setValor(getValorr(element.getExpr1())*getValorr(element.getExpr2()));
    
        
    }

    @Override
    public void visitar(Division element, Object... params) {
        
        
    element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        
        inicio.escribirMsjError("Interprete: Diviendo "+getValorr(element.getExpr1())+" / "+getValorr(element.getExpr2()));
   element.setValor(getValorr(element.getExpr1())/getValorr(element.getExpr2()));
         
   }

    @Override
    public void visitar(Igual element, Object... params) {
        
        
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorr(element.getExpr1())+" == "+getValorr(element.getExpr2()));
        if( getValorr(element.getExpr1()) == getValorr(element.getExpr2())){
        
        element.setValor(true);        
        }else
        element.setValor(false);            
        
    }

    @Override
    public void visitar(Menor element, Object... params) {
                
        
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorr(element.getExpr1())+" < "+getValorr(element.getExpr2()));
        if( getValorr(element.getExpr1()) < getValorr(element.getExpr2()))
            element.setValor(true);
                    
        else {
            element.setValor(false);
        }            
        
        
    }

    @Override
    public void visitar(NumeroEntero element, Object... params) {
        
            int val=Integer.parseInt(element.getToken().getTexto());            
            element.setValor(val);
            System.out.println("prueba de entero "+val);
            inicio.escribirMsjError("Interprete: Literal entero "+val);
    }

    @Override
    public void visitar(Variable element, Object... params) {      
        
        Variable sym = (Variable) tabla.buscar(element.getNombre());
        
        if( sym.getTipoExpr() == NucleoLenguaje.tipoBool)
            element.setValor2( sym.getValor2() );
        else
            element.setValor( sym.getValor() );
        
    }

    @Override
    public void visitar(Mayor element, Object... params) {
        
        
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorr(element.getExpr1())+" > "+getValorr(element.getExpr2()));
        if( getValorr(element.getExpr1()) > getValorr(element.getExpr2())){
        
        element.setValor(true);        
        }else
        element.setValor(false);            
        
        
        
    }

    @Override
    public void visitar(MayorIgual element, Object... params) {
        
        
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorr(element.getExpr1())+" >= "+getValorr(element.getExpr2()));
        if( getValorr(element.getExpr1()) >= getValorr(element.getExpr2())){
        
        element.setValor(true);        
        }else
        element.setValor(false);            
        
        
    }

    @Override
    public void visitar(MenorIgual element, Object... params) {
        
        
     
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorr(element.getExpr1())+" <= "+getValorr(element.getExpr2()));
        if( getValorr(element.getExpr1()) <= getValorr(element.getExpr2())){
        
        element.setValor(true);        
        }else
        element.setValor(false);            
        
    }

    @Override
    public void visitar(NumeroBool element, Object... params) {
        
        inicio.escribirMsjError("Interprete valor booleano...");
        if(element.getToken().getTexto().compareToIgnoreCase("falso")==0)  {
        element.setValor(false);
        }          
        else{
        element.setValor(true);
        }
        
        
    }

    @Override
    public void visitar(Diferente element, Object... params) {
       
        
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorr(element.getExpr1())+" <> "+getValorr(element.getExpr2()));
        if( getValorr(element.getExpr1()) != getValorr(element.getExpr2())){
        
        element.setValor(true);        
        }else
        element.setValor(false);            
        
        
        
    }

    @Override
    public void visitar(Y element, Object... params) {
    
        
     element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorrr(element.getExpr1())+" Y "+getValorrr(element.getExpr2()));
        if( getValorrr(element.getExpr1()) && getValorrr(element.getExpr2())){
        
        element.setValor(true);        
        }else
        element.setValor(false);            
        
    }

    @Override
    public void visitar(O element, Object... params) {
        element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Comparando "+getValorrr(element.getExpr1())+" O "+getValorrr(element.getExpr2()));
        if( getValorrr(element.getExpr1()) || getValorrr(element.getExpr2())){
        
        element.setValor(true);        
        }else{
        element.setValor(false);            
        }
        
    }

    @Override
    public void visitar(Declaracion element, Object... params) {
        element.getExpr().aceptar(this, params);
        //element.getId().aceptar(this, params);
        if(element.getExpr().getTipoExpr().getTexto().compareToIgnoreCase("_entero")==0){
        element.getId().setValor(0);
        System.out.println("entro en declaracion entera");                
        inicio.escribirMsjError("entro en declaracion entera");
        }else{
            inicio.escribirMsjError("entro en declaracion booleana");
            System.out.println("entro en declaracion booleana");
            element.getId().setValor2(false);
        }
                             
    }

    @Override
    public void visitar(Para element, Object... params) {
        inicio.escribirMsjError("Interprete: Ciclo para");
        
        if(element.getVerificacion()==null){
    element.getInicializacion().aceptar(this, params);
        }
    element.getVerificacion().aceptar(this, params);
        
    if(getValorrr(element.getVerificacion())){
        element.getSentencias().aceptar(this, params);        
        element.getPaso().aceptar(this, params);
        element.aceptar(this, params);
    }
    
        
        
    }

    
    public boolean getValorrr(Expresion e){

        boolean b=false;
    
    if(e instanceof Menor)
    {
        Menor n=(Menor)e;
        b=n.isValor();
    }
    
    if(e instanceof Variable)
    {
        Variable  n=(Variable)e;
        b=n.getValor2();
    }
    
    if(e instanceof Igual)
    {
        Igual n=(Igual) e;
        b=n.isValor();
    }
    if(e instanceof MenorIgual)
    {
        MenorIgual n=(MenorIgual)e;
        b=n.isValor();
    }
    if(e instanceof Mayor){
    Mayor n=(Mayor) e;
    b=n.isValor();
    }
    if(e instanceof MayorIgual){
    MayorIgual n=(MayorIgual) e;
    b=n.isValor();
    }
    if(e instanceof Diferente){
    Diferente n=(Diferente) e;
    b=n.isValor();
    }
    if(e instanceof Y){
    Y n=(Y) e;
    b=n.isValor();
    }
    if(e instanceof O){
    O n=(O)e;
    b=n.isValor();
    }
    
    if(e instanceof NumeroBool)
    {
    NumeroBool nb = (NumeroBool) e;
    b=nb.getValor();
    }
        
    return b;
    }
    
    public int getValorr(Expresion e){
        
        
        int r=0;          
        if(e instanceof NumeroEntero){
    NumeroEntero n=(NumeroEntero)e;        
    r= n.getValor();
    }
        
    if(e instanceof Modulo){
    Modulo n=(Modulo)e;
    
    r= n.getValor();
    }
    if(e instanceof Division)
    {
    Division n = (Division) e;
    
    r= n.getValor();
    }
    if(e instanceof Multiplicacion)
    {
    Multiplicacion n = (Multiplicacion) e;
    
    r= n.getValor();
    }
    if(e instanceof Suma)
    {
    Suma n = (Suma) e;
    
    r= n.getValor();
    }
    if(e instanceof Resta)
    {
    Resta n = (Resta) e;
    
    r= n.getValor();
    }
    if(e instanceof Variable){
    Variable n=(Variable)e;
    r=n.getValor();    
    }
  
    return r;
    }
    
    
    @Override
    public void visitar(Vector element, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitar(Modulo element, Object... params) {
        element.getExpr1().aceptar(this, params);
        element.getExpr2().aceptar(this, params);      
        inicio.escribirMsjError("Interprete: Modulo "+getValorr(element.getExpr1())+" mod "+getValorr(element.getExpr2()));
        element.setValor(getValorr(element.getExpr1())%getValorr(element.getExpr2()));
    }

    @Override
    public void visitar(Avanzar element, Object... params) {
        
        element.getExpr().aceptar(this, params);
        inicio.escribirMsjError("Interprete: Avanzado "+getValorr( element.getExpr() )+" pasos");
        list.agregarMovimiento(dirMirada, getValorr( element.getExpr() ) );
        
    }

    @Override
    public void visitar(MirarAbajo element, Object... params) {
        dirMirada = Play.DezAbajo;
    }

    @Override
    public void visitar(MirarArriba element, Object... params) {
        dirMirada = Play.DezArriba;
    
    }

    @Override
    public void visitar(MirarDerecha element, Object... params) {
        dirMirada = Play.DezDerecha;
    
    }

    @Override
    public void visitar(MirarIzquierda element, Object... params) {
        dirMirada = Play.DezIzquierda;
    }

    
}
