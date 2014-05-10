package ejemplo;
import java_cup.runtime.Symbol;

/*
Directivas:
public: clase publica
cup: compatibilidad con cup
full: extender el alfabeto con todos los valores de 8 bits
line: agrega la variable int yyline, para indicar la fila del lexema
char: agrega la variable int yychar, indica el indice del primer caracter del lexema
ignorecase: validar, indistitntamente si la letra es mayuscula o minuscula
eofval: especifica un valor de retorno al final del archivo
*/

%%


%cup
%full
%line
%char
%ignorecase
%eofval{
	return new Symbol(sym.EOF,new String("Fin del archivo"));
%eofval}

digito = [0-9]
letra = [a-zA-Z]
id = {letra}({letra}|{digito}|"_")*
espacio = \t|\f|" "|\r|\n

%%

"<>"		{return new Symbol(sym.DIFERENTE, yychar, yyline, yytext());}
">="		{return new Symbol(sym.MAYORIGUAL, yychar, yyline, yytext());}
"<="		{return new Symbol(sym.MENORIGUAL, yychar, yyline, yytext());}
"-="		{return new Symbol(sym.MENOSNUM, yychar, yyline, yytext());}
"--"		{return new Symbol(sym.MENOSUNO, yychar, yyline, yytext());}
"+="		{return new Symbol(sym.MASNUM, yychar, yyline, yytext());}
"++"		{return new Symbol(sym.MASUNO, yychar, yyline, yytext());}
"="			{return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
">"			{return new Symbol(sym.MAYOR, yychar, yyline, yytext());}
"<"			{return new Symbol(sym.MENOR, yychar, yyline, yytext());}
";"			{return new Symbol(sym.PYCOMA, yychar, yyline, yytext());}
","			{return new Symbol(sym.COMA, yychar, yyline, yytext());}
"("			{return new Symbol(sym.PARIZQ, yychar, yyline, yytext());}
")"			{return new Symbol(sym.PARDER, yychar, yyline, yytext());}
"{"			{return new Symbol(sym.LLAVEIZQ, yychar, yyline, yytext());}
"}"			{return new Symbol(sym.LLAVEDER, yychar, yyline, yytext());}
"for"		{return new Symbol(sym.FOR, yychar, yyline, yytext());}
"int"		{return new Symbol(sym.INT, yychar, yyline, yytext());}
{id}		{return new Symbol(sym.ID, yychar, yyline, yytext());}
{digito}+	{return new Symbol(sym.NUMERO, yychar, yyline, new Integer(yytext()));}
{espacio}	{}
.			{ System.out.println("Caracter ilegal: " + yytext()); }