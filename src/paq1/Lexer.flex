
package paq1;
import static paq1.Tokens.*;
import java.util.Map;
import java.util.LinkedHashMap;

class Token {
    public String lexema;
    public String token;
    public int nLinea;

    public Token(String lexema, String token, int nLinea) {
        this.lexema = lexema;
        this.token = token;
        this.nLinea = nLinea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getnLinea() {
        return nLinea;
    }

    public void setnLinea(int nLinea) {
        this.nLinea = nLinea;
    }
    
    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
}

%%

%class Lexer
%type Tokens
%line
%column
%{
   public String lexeme;
   InfoTokens t = new InfoTokens();
   Map<String,Token> tablaSimbolos = new LinkedHashMap<>();
%}

terminadorDeLinea = \r|\n|\r\n
entradaDeCaracter = [^\r\n]
espacioEnBlanco = {terminadorDeLinea} | [ \t\f]
comentarioTradicional   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
finDeLineaComentario = "//" {entradaDeCaracter}* {terminadorDeLinea}?
contenidoComentario = ( [^*] | \*+ [^/*] )*
comentarioDeDocumentacion = "/**" {contenidoComentario} "*"+ "/"
comentario = {comentarioTradicional} | {finDeLineaComentario} | {comentarioDeDocumentacion}

letra = [a-zA-ZñÑ_$á-źÁ-Ź]
digito = [0-9]
espacio = [ ]+
caracter = \'(\\.|[^\'\\])?\'
carinc = \'(\\.|[^\'\\])?
cadena = \"(\\.|[^\"\\])*\"
cadinc = \"(\\.|[^\"\\])*
flotante = (-?[1-9][0-9]*\.[0-9]*[1-9])|(0\.0)|(-?[1-9][0-9]*\.0)|(-?[1-9][0-9]*\.[0-9]*[1-9][eE][-+][1-9][0-9]*)|(-?0\.[0-9]*[1-9][eE][-+][1-9][0-9]*)
entero = (0|-?[1-9][0-9]*)
num = {entero} | {flotante}
tipo = entero|flotante|caracter|cadena|booleano

id = {letra}({letra}|{digito})*
%%

{comentario}|{espacioEnBlanco} { /* Ignorar */ }

/* Reservadas */
programa {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"programa",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return programa;}
funcion {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"funcion",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return funcion;}
procedimiento {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"procedimiento",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return procedimiento;}
sino {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"sino",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return sino;}
inc {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"inc",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return inc;}
dec {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"dec",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return dec;}
mientras {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"mientras",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return mientras;}
hacer {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"hacer",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return hacer;}
para {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"para",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return para;}
entero {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"entero",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return entero;}
flotante {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"flotante",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return flotante;}
caracter {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"caracter",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return caracter;}
cadena {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"cadena",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return cadena;}
booleano {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"booleano",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return booleano;}
si {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"si",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return si;}
entonces {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"entonces",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return entonces;}
verdadero {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"verdadero",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return verdadero;}
falso {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"falso",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return falso;}
mostrar {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"mostrar",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return mostrar;}
leer {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"leer",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return leer;}

/* Identificadores */
{id} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"id",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return id;}
"f."{id} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"idf",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return idf;}
"p."{id} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"idp",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return idp;}

/* Literal caracter */
{caracter} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"litcar",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return litcar;}

/* Literal cadena */
{cadena} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"litcad",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return litcad;}

/* num */
{num} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"num",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return num;}

/* Simbolos de asignación */
":=" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),":=",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Igual;}

/* Simbolos aritmeticos */
"+" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"+",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Suma;}
"-" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"-",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Resta;}
"/" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"/",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Division;}
"*" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"*",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Producto;}
"%" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"%",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Factorial;}

/* Simbolos de comparación */
"==" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"==",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return DobleIgual;}
"¡=" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"¡=",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Diferencia;}
"<" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"<",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Menor;}
">" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),">",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Mayor;}
"<=" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"@",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return mI;}
">=" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"#",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return MI;}

/* Simbolos logicos */
"||" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"||",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return OR;}
"&&" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"&&",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return AND;}
"!" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"!",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Negacion;}

/* Simbolos de agrupación */
"(" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"(",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return AbreParentesis;}
")" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),")",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return CierraParentesis;}
"[" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"[",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return AbreCorchete;}
"]" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"]",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return CierraCorchete;}
"{" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"{",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return AbreLLave;}
"}" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),"}",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return CierreLLave;}

/* Simbolos de puntuación */
"," {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),",",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return Coma;}
";" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); Token t1 = new Token(yytext(),";",yyline); tablaSimbolos.put(yytext()+yyline+yycolumn,t1); return PuntoComa;}

 . {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return ERROR;}
{cadinc} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return ERROR;}
{carinc} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return ERROR;}
