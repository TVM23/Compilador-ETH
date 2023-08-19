/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paq1;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.undo.UndoManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.awt.event.KeyEvent;

/**
 *
 * @author CSjes
 */
public class principal extends javax.swing.JFrame {

    int cont, contant = 0, c = 0;
    boolean band;
    NumeroLinea2 numerolinea2;
    HerramientaArchivo archivo;
    UndoManager manager;
    //String componentes;
    Lexer lexer;
    public Stack<String> pilaAux = new Stack(); //Pila auxiliar
    public Stack<String> pilaPrincipal = new Stack(); //Pila principal
    //ArrayList<String> cadena = new ArrayList<>(Arrays.asList("num","+","id","%",")","num","%","id","-","id","num")); //Arreglo que almacena los componentes del lexico
    ArrayList<String> noTerminales = new ArrayList<>(Arrays.asList("P", "zona_dec", "modulo", "sigdec1", "list_arg", "sigpara", "list_para", "bloque", "sent", "sigsi", "sent_op", "sent_comp", "sent_simple", "asig", "sigdec", "Dec", "tipo", "L", "L'", "R", "R'", "E", "E'", "T", "T'", "F")); //Variable no terminales de las filas 
    ArrayList<String> terminales = new ArrayList<>(Arrays.asList("programa", ";", "[", "]", "funcion", "idf", "(", ")",
            "procedimiento", "idp", ",", "id", "{", "}", "sino", "inc", "num", "dec", "si", "mientras", "hacer", "para", "mostrar", ":=", "entero", "flotante", "caracter", "cadena", "booleano", "&&", "||", "!", "<", ">", "¡=", "@", "#", "==", "+", "-", "*", "/", "%", "litcar", "litcad", "verdadero", "falso", "leer", "$")); //Variable no terminales de las columnas 
    public String componente; //Va guardando de uno por uno los componentes del arreglo durante el for
    public String[][] transicion = {
        {"programa id ; zona_dec modulo bloque", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "[ Dec ]", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "funcion tipo idf ( list_arg ) bloque modulo", "Saltar", "Saltar", "Saltar", "procedimiento idp ( list_arg ) bloque modulo", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", ", list_arg", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "tipo id sigdec1", "tipo id sigdec1", "tipo id sigdec1", "tipo id sigdec1", "tipo id sigdec1", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", ", L sigpara", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "L sigpara", "L sigpara", "Vacia", "Saltar", "Saltar", "Saltar", "L sigpara", "Saltar", "Saltar", "Saltar", "Saltar", "L sigpara", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "L sigpara", "L sigpara", "L sigpara", "L sigpara", "L sigpara", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "{ sent }", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "sent_simple sent", "Saltar", "sent_simple sent", "Saltar", "Vacia", "Saltar", "sent_simple sent", "Saltar", "sent_simple sent", "sent_comp sent", "sent_comp sent", "sent_comp sent", "sent_comp sent", "sent_simple sent", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Vacia", "Saltar", "Vacia", "sino bloque", "Vacia", "Saltar", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "inc ( id , num )", "Saltar", "dec ( id , num )", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "si ( L ) bloque sigsi", "mientras ( L ) bloque", "hacer bloque mientras ( L ) ;", "para ( asig L ; sent_op ) bloque", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "idp ( list_arg )", "Saltar", "asig", "Saltar", "Saltar", "Saltar", "sent_op", "Saltar", "sent_op", "Saltar", "Saltar", "Saltar", "Saltar", "mostrar ( list_para ) ;", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "id := L ;", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", ", id sigdec", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "tipo id sigdec ; Dec", "tipo id sigdec ; Dec", "tipo id sigdec ; Dec", "tipo id sigdec ; Dec", "tipo id sigdec ; Dec", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "entero", "flotante", "caracter", "cadena", "booleano", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "R L'", "R L'", "Saltar", "Saltar", "Saltar", "Saltar", "R L'", "Saltar", "Saltar", "Saltar", "Saltar", "R L'", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "! L", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "R L'", "R L'", "R L'", "R L'", "Saltar", "Sacar"},
        {"Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "&& R L'", "|| R L'", "Saltar", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "E R'", "E R'", "Saltar", "Saltar", "Saltar", "Saltar", "E R'", "Saltar", "Saltar", "Saltar", "Saltar", "E R'", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "E R'", "E R'", "E R'", "E R'", "E R'", "E R'", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "E R'", "E R'", "E R'", "E R'", "Saltar", "Sacar"},
        {"Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Vacia", "Vacia", "< E", "> E", "¡= E", "@ E", "# E", "== E", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "T E'", "T E'", "Saltar", "Saltar", "Saltar", "Saltar", "T E'", "Saltar", "Saltar", "Saltar", "Saltar", "T E'", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "T E'", "T E'", "T E'", "T E'", "Saltar", "Sacar"},
        {"Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "+ T E'", "- T E'", "Vacia", "Vacia", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "F T'", "F T'", "Saltar", "Saltar", "Saltar", "Saltar", "F T'", "Saltar", "Saltar", "Saltar", "Saltar", "F T'", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "F T'", "F T'", "F T'", "F T'", "Saltar", "Sacar"},
        {"Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "Vacia", "* F T'", "/ F T'", "% F T'", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Sacar"},
        {"Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "idf ( list_arg )", "( L )", "Saltar", "Saltar", "Saltar", "Saltar", "id", "Saltar", "Saltar", "Saltar", "Saltar", "num", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "Saltar", "litcar", "litcad", "verdadero", "falso", "leer ( list_arg ) ;", "Sacar"}
    };
    String res, err;

    public principal() {
        this.manager = new UndoManager();
        initComponents();
        inicializar();
        codigoFuente.getDocument().addUndoableEditListener(manager);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        ///Comentario importante
    }

    private void AnalisisLexico() {
        InfoTokens infoToken;
        try {
            File codigo = new File("archivo.eth");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytes = codigoFuente.getText().getBytes();
            output.write(bytes);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF-8"));
            lexer = new Lexer(entrada);
            infoToken = new InfoTokens();
            Token t2 = null;
            String resu = "";
            while (true) {
                Tokens token = lexer.yylex();
                if (token == null) {
                    AnalisisSintactico("$", "", (infoToken.numeroLinea + 1) + "");
                    //Heho para pruebas
                    //componentes = "";
                    Iterator it = lexer.tablaSimbolos.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Token> entry = (Map.Entry) it.next();
                        //AnalisisSintactico(entry.getValue().getToken(), entry.getValue().getLexema(), (entry.getValue().getnLinea() + 1)+"");
                        //componentes += entry.getValue().getToken()+",";
                        //System.out.println("Lexema: " + entry.getValue().getLexema() + " Token: " + entry.getValue().getToken() + " Numero de linea: " + (entry.getValue().getnLinea() + 1));
                    }
                    //Hecho para pruebas
                    resu += "";
                    lexico.setText(resu);
                    return;
                }
                switch (token) {
                    case ERROR:
                        resu += "Error lexico en la linea " + (infoToken.numeroLinea + 1) + " simbolo: " + lexer.lexeme + " incorecto" + "\n";
                        break;
                    default:
                        if (token.getSimbolo() == null) {
                            resu += token + "\n";
                            AnalisisSintactico(token + "", infoToken.lexema, (infoToken.numeroLinea + 1) + "");
                        } else {
                            resu += token.getSimbolo() + "\n";
                            AnalisisSintactico(token.getSimbolo(), infoToken.lexema, (infoToken.numeroLinea + 1) + "");
                        }
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AnalisisSintactico(String comp, String lexema, String nlinea) {
        cont = 0;
        String letraPila, dir;
        int ncol, nfil; //Numero de columnas y filas
        boolean ban;
        componente = comp; //Se almacena el componente a analizar
        ban = true;
        System.out.println(componente);
        while (ban == true) { //Este while sirve para definir si se sigue usando el mismo componente o avanza
            letraPila = pilaPrincipal.peek(); //Se lamacena cual es el valor que esta en la pila principal
            if (terminales.contains(letraPila) && !componente.equals(letraPila)) {
                //Se verifica si el elemento de la pila es terminal y si es diferente al componente a analizar
                res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                sintactico.setText(res);
                //System.out.print("\t"+pilaPrincipal.peek());
                res += "\t" + pilaPrincipal.peek();
                sintactico.setText(res);
                //System.out.print("->"+dir+"\n");
                res += "->" + componente + "\n";
                sintactico.setText(res);
                res += "Error sintactico en la linea " + nlinea + " se esperaba el simbolo: " + letraPila + "\n";
                sintactico.setText(res);
                if (band == true) {
                    contant = 0;
                }
                cont = contant;
                cont++;
                if (cont == 1) {
                    //res += "Error sintactico en la linea " + nlinea + " se esperaba el simbolo: "+letraPila+"\n";
                    err += "Error sintactico en la linea " + nlinea + " se esperaba el simbolo: " + letraPila + "\n";
                    //sintactico.setText(res);
                    errores.setText(err);
                    contant++;
                    band = false;
                }
                pilaPrincipal.pop();
                ban = true;
            } else {
                if (componente.equals("$") && pilaPrincipal.peek().equals("$")) {
                    band = true;
                    //Esto ve si ya se llego al final de la cadena y pila
                    //System.out.println("Analisis sintactico finalizado correctamente");
                    res += "Analisis sintactico finalizado correctamente";
                    sintactico.setText(res);
                    //System.out.println("Cadena final resultante: "+res);
                    break;
                } else if (componente.equals(pilaPrincipal.peek())) {
                    band = true;
                    //Compara si el componente que se saco de la cadena es igual al valor de la Pila
                    res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                    sintactico.setText(res);
                    //System.out.print("\t"+pilaPrincipal.peek());
                    res += "\t" + pilaPrincipal.peek();
                    sintactico.setText(res);
                    //System.out.print("->"+dir+"\n");
                    res += "->" + componente + "\n";
                    sintactico.setText(res);
                    //System.out.println("concuerda"); //Accion de concuerda
                    res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                    //res += "\t"+pilaPrincipal.peek();
                    //res += "->"+componente+"\n";
                    res += "concuerda\n";
                    sintactico.setText(res);
                    //res+=componente;
                    pilaPrincipal.pop();
                    ban = false;
                    break;
                }
                ncol = 0;
                nfil = 0;
                //Calcula la posicion de las filas para la tabla de transicion
                for (String col : terminales) {
                    if (col.equals(componente)) {
                        break;
                    } else {
                        ncol++;
                    }
                }
                //Calcula la posicion de las columnas para la tabla de transicion
                for (String fila : noTerminales) {
                    if (fila.equals(letraPila)) {
                        break;
                    } else {
                        nfil++;
                    }
                }

                dir = transicion[nfil][ncol]; //dir guarda la accion de la tabla de transicion
                //AccionPilaPrin(dir);
                switch (dir) {
                    case "Vacia" -> {
                        //System.out.print(Arrays.toString(pilaPrincipal.toArray()));
                        res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                        sintactico.setText(res);
                        //System.out.print("\t"+pilaPrincipal.peek());
                        res += "\t" + pilaPrincipal.peek();
                        sintactico.setText(res);
                        //System.out.print("->"+dir+"\n");
                        res += "->" + dir + "\n";
                        sintactico.setText(res);
                        pilaPrincipal.pop();
                        ban = true;
                        break;
                    }
                    case "Saltar" -> {
                        //System.out.print(Arrays.toString(pilaPrincipal.toArray()));
                        res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                        sintactico.setText(res);
                        //System.out.print("\t"+pilaPrincipal.peek());
                        res += "\t" + pilaPrincipal.peek();
                        sintactico.setText(res);
                        //System.out.print("->"+dir+"\n");
                        res += "->" + dir + "\n";
                        sintactico.setText(res);
                        res += "Error sintactico en la linea " + nlinea + " no se esperaba " + lexema + " saltar" + "\n";
                        sintactico.setText(res);
                        //System.out.println("Error sintactico en la linea "+nlinea+" no se esperaba "+lexema+ " saltar");
                        if (band == true) {
                            contant = 0;
                        }
                        cont = contant;
                        cont++;
                        if (cont == 1) {
                            //res += "Error sintactico en la linea "+nlinea+" no se esperaba "+lexema+ " saltar"+"\n";
                            err += "Error sintactico en la linea " + nlinea + " no se esperaba " + lexema + " saltar" + "\n";
                            // sintactico.setText(res);
                            errores.setText(err);
                            contant++;
                            band = false;
                        }
                        ban = false;
                        break;
                    }
                    case "Sacar" -> {
                        //System.out.print(Arrays.toString(pilaPrincipal.toArray()));
                        res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                        sintactico.setText(res);
                        //System.out.print("\t"+pilaPrincipal.peek());
                        res += "\t" + pilaPrincipal.peek();
                        sintactico.setText(res);
                        //System.out.print("->"+dir+"\n");
                        res += "->" + dir + "\n";
                        sintactico.setText(res);
                        pilaPrincipal.pop();
                        //System.out.println("Error sintactico en la linea "+nlinea+" no se esperaba "+lexema+ " sacar");
                        res += "Error sintactico en la linea " + nlinea + " no se esperaba " + lexema + " sacar" + "\n";
                        sintactico.setText(res);
                        ban = true;
                        break;
                    }
                    default -> {
                        band = true;
                        //System.out.print(Arrays.toString(pilaPrincipal.toArray()));
                        res += Arrays.toString(pilaPrincipal.toArray()) + " ";
                        sintactico.setText(res);
                        //System.out.print("\t"+pilaPrincipal.peek());
                        res += "\t" + pilaPrincipal.peek();
                        sintactico.setText(res);
                        //System.out.print("->"+dir+"\n");
                        res += "->" + dir + "\n";
                        sintactico.setText(res);
                        pilaPrincipal.pop();
                        String[] temp = dir.split(" "); //Convierte la caden dir en arreglo
                        for (String f : temp) {
                            pilaAux.push(f);
                        }
                        while (!pilaAux.peek().equals("Z")) {
                            pilaPrincipal.push(pilaAux.peek());
                            pilaAux.pop();
                        }
                        ban = true;
                    }
                }
            }
        }
    }

    private void InicializarPilas() {
        pilaPrincipal.clear();
//        cadena.add("$"); //Añade el terminador de cadena
        pilaAux.push("Z");
        pilaPrincipal.push("$");
        pilaPrincipal.push("P");
    }

    private void inicializar() {
        archivo = new HerramientaArchivo();
        setTitle("ETHIDE");
        numerolinea2 = new NumeroLinea2(codigoFuente);
        jScrollPane2.setRowHeaderView(numerolinea2);
    }

    private void Cerrar() {
        String opciones[] = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Estas seguro de que quieres cerrar el programa? Todo cambio sin guardar se perdera", "Cierre de programa", 0, 0, null, opciones, EXIT_ON_CLOSE);
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void LimpiarComp() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        codigoFuente = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lexico = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        sintactico = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        errores = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codigoFuente.setColumns(20);
        codigoFuente.setRows(5);
        codigoFuente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoFuenteKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(codigoFuente);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 53, 515, 280));

        lexico.setColumns(20);
        lexico.setRows(5);
        jScrollPane3.setViewportView(lexico);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 506, 179));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-por-nueva-copia-24.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-abrir-carpeta-24.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-guardar-24.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-deshacer-24.png"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-rehacer-24.png"))); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-play-24.png"))); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1116, 35));

        sintactico.setColumns(20);
        sintactico.setRows(5);
        jScrollPane4.setViewportView(sintactico);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 506, 179));

        errores.setColumns(20);
        errores.setRows(5);
        jScrollPane1.setViewportView(errores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 480, 90));

        jMenu1.setText("Archivo");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-por-nueva-copia-24.png"))); // NOI18N
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-abrir-carpeta-24.png"))); // NOI18N
        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-guardar-24.png"))); // NOI18N
        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-guardar-como-24.png"))); // NOI18N
        jMenuItem4.setText("Guardar como...");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-cerrar-ventana-24.png"))); // NOI18N
        jMenuItem5.setText("Cerrar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-deshacer-24.png"))); // NOI18N
        jMenuItem6.setText("Deshacer");
        jMenuItem6.setActionCommand("");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-rehacer-24.png"))); // NOI18N
        jMenuItem7.setText("Rehacer");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-tijeras-24.png"))); // NOI18N
        jMenuItem8.setText("Cortar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-copiar-24.png"))); // NOI18N
        jMenuItem9.setText("Copiar");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-pegar-24.png"))); // NOI18N
        jMenuItem10.setText("Pegar");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons/IconoIDE.png"));
        return retValue;
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        lexico.setText("");
        archivo.Nuevo(this);
        LimpiarComp();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (manager.canUndo()) {
            manager.undo();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        codigoFuente.copy();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        archivo.Guardar(this);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        lexico.setText("");
        archivo.Abrir(this);
        LimpiarComp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        lexico.setText("");
        archivo.guardarC(this);
        LimpiarComp();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void codigoFuenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoFuenteKeyReleased
        int key = evt.getKeyCode();
        //if (KeyEvent.getKeyText(key).length() > 0) {
        if ((key >= 65 && key <= 90) || (key >= 48 && key <= 57) || (key >= 97 && key <= 122) || (key != 27 && (key >= 37
                && key <= 40) && !(key >= 16 && key <= 18) && key != 524 && key != 20)) {
            if (!getTitle().contains("*")) {
                setTitle(getTitle() + "*");
            }
            // Analisis dinamico
            //InicializarPilas();
            //res = "";
            //err = "";
            //AnalisisLexico();
            //AnalisisSintactico("$","","");
        }
    }//GEN-LAST:event_codigoFuenteKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lexico.setText("");
        archivo.Nuevo(this);
        LimpiarComp();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        lexico.setText("");
        archivo.Abrir(this);
        LimpiarComp();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        archivo.Guardar(this);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Limpiar() {
        errores.setText("");
        lexico.setText("");
        sintactico.setText("");
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        InicializarPilas();
        Limpiar();
        res = "";
        err = "";
        AnalisisLexico();
        //AnalisisSintactico("$","","");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (manager.canUndo()) {
            manager.undo();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (manager.canRedo())
            manager.redo();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (manager.canRedo())
            manager.redo();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        codigoFuente.cut();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        codigoFuente.paste();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea codigoFuente;
    private javax.swing.JTextArea errores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea lexico;
    private javax.swing.JTextArea sintactico;
    // End of variables declaration//GEN-END:variables
}
