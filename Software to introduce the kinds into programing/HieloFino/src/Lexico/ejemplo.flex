package Lexico;
import java_cup.runtime.Symbol;
import Tokens.sym;
//import JFlex.sym;
import tabla.*;
import hielofino.inicio;
%%
%{
	private SimboloAbstracto nombreArchivo;
	
	public int getLinea(){
		return yyline+1;
	}
	
	public int getColumna(){
		return yycolumn+1;
	}

    public void setNombreArchivo(String nombre) {
		nombreArchivo = TablaAbstracta.idTabla.agregarSimbolo(nombre, getLinea(), getColumna());
    }

    public SimboloAbstracto getNombreArchivo() {
		return nombreArchivo;
    }
%}
%public
%cup
%full
%line
%column
%char
%ignorecase
%eofval{
	return new Symbol(sym.EOF,new String("Fin del archivo"));
%eofval}

digito = [0-9]
letra = [a-zA-Z]
id = {letra}({letra}|{digito}|"_")*
espacio = \t|\f|" "|\r|\n
COMENTARIO = "#"[^#]*"#"

%%

/*
expr -> expr opLog expr_relacionales     | expr_relacionales
expr_relacionales -> expr_relacionales opRel expr_aritmeticas | expr_aritmeticas
expr_aritmeticas -> expr_aritmeticas opArit term | term
opArit -> +     | -
opLog-> Y     | O 
opRel -> <     | <=     | >     | >=     | =     | <>
term -> term opAritMult factor    | factor
factor -> var     | NUM     | (expr) 
opAritMult -> *  | /
*/

"+"		{return new Symbol(sym.SUMA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"-"		{return new Symbol(sym.RESTA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"*"		{return new Symbol(sym.MULT, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"/"		{return new Symbol(sym.DIV, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"MOD"	{return new Symbol(sym.MOD, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"Y"		{return new Symbol(sym.Y, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"O"		{return new Symbol(sym.O, new SimboloTexto(yytext(),getLinea(),getColumna()));}

">="		{return new Symbol(sym.MAYOR_IGUAL, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"<="		{return new Symbol(sym.MENOR_IGUAL, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"<>"		{return new Symbol(sym.DIFERENTE, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"=="		{return new Symbol(sym.IGUAL_LOGICO, new SimboloTexto(yytext(),getLinea(),getColumna()));}
">"			{return new Symbol(sym.MAYOR, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"<"			{return new Symbol(sym.MENOR, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"="			{return new Symbol(sym.IGUAL, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"("			{return new Symbol(sym.PARENT_ABRE, new SimboloTexto(yytext(),getLinea(),getColumna()));}
")"			{return new Symbol(sym.PARENT_CIERRA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"{"			{return new Symbol(sym.LLAVE_ABRE, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"}"			{return new Symbol(sym.LLAVE_CIERRA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"["			{return new Symbol(sym.COR_ABRE, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"]"			{return new Symbol(sym.COR_CIERRA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
";"			{return new Symbol(sym.PUNTO_COMA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
","			{return new Symbol(sym.COMA, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"programa"	{return new Symbol(sym.PROGRAMA, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"si"	{return new Symbol(sym.SI, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"sino"	{return new Symbol(sym.SINO, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"finsi"	{return new Symbol(sym.ENDIF, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"entonces"	{return new Symbol(sym.THEN, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"verdadero"	{return new Symbol(sym.TRUE, TablaAbstracta.boolTabla.agregarSimbolo(yytext(),getLinea(),getColumna()));}
"falso"	{return new Symbol(sym.FALSE,  TablaAbstracta.boolTabla.agregarSimbolo(yytext(),getLinea(),getColumna()));}

"mirarArriba"		{return new Symbol(sym.MIRAR_ARRIBA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"mirarAbajo"		{return new Symbol(sym.MIRAR_ABAJO, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"mirarIzquierda"	{return new Symbol(sym.MIRAR_IZQ, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"mirarDerecha"		{return new Symbol(sym.MIRAR_DER, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"Avanzar"			{return new Symbol(sym.AVANZAR, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"int"	{return new Symbol(sym.INT, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"bool"	{return new Symbol(sym.BOOL, new SimboloTexto(yytext(),getLinea(),getColumna()));}

"repita"	{return new Symbol(sym.REPITA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"hasta"	{return new Symbol(sym.HASTA, new SimboloTexto(yytext(),getLinea(),getColumna()));}
"para"	{return new Symbol(sym.PARA, new SimboloTexto(yytext(),getLinea(),getColumna()));}


{id}		{return new Symbol(sym.ID,  TablaAbstracta.idTabla.agregarSimbolo(yytext(),getLinea(),getColumna())); }
{digito}+	{return new Symbol(sym.NUM, TablaAbstracta.intTabla.agregarSimbolo(yytext(),getLinea(),getColumna()));}
{espacio}	{}
{COMENTARIO}    {}
.			{ 
                            String mensaje = "Error Lexíco, caracter ilegal. Línea: " + yyline+1 + " Columna: "+ yycolumn + 
                                             ". \nTexto: \" " + yytext() + "\" \n***********************\n";                      
                            inicio.escribirMsjError(mensaje);
            }