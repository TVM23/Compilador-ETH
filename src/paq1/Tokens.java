
package paq1;

public enum Tokens {
    id,
    idf,
    idp,
    litcar,
    litcad,
    num,
    programa,
    funcion, 
    procedimiento,
    sino,
    inc,
    dec,
    mientras,
    hacer,
    para,
    mI("@"),
    MI("#"),
    entero,
    flotante,
    caracter,
    cadena,
    booleano,
    si,
    entonces, 
    verdadero,
    falso,
    mostrar,
    leer,
    Igual(":="),
    Suma("+"),
    Resta("-"),
    Division("/"),
    Producto("*"),
    Factorial("%"),
    DobleIgual("=="),
    Diferencia("¡="),
    Menor("<"),
    Mayor(">"),
    OR("||"),
    AND("&&"),
    Negacion("!"),
    AbreParentesis("("),
    CierraParentesis(")"),
    AbreCorchete("["),
    CierraCorchete("]"),
    AbreLLave("{"),
    CierreLLave("}"),
    Coma(","),
    PuntoComa(";"),
    ERROR;
    
    private final String simbolo;

    private Tokens() {
        this.simbolo = null;
    }

    private Tokens(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
