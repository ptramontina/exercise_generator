// $ANTLR 3.2 Sep 23, 2009 12:02:23 Translator.g 2015-11-19 00:46:42
package generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TranslatorParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INDENT", "DEDENT", "NEWLINE", "TRUE", "FALSE", "PI", "BOOLEAN", "CHARACTER", "INTEGER", "REAL", "STRING_", "ARRAY", "MATRIX", "DECLARE", "FOR", "FROM", "STEP", "PRINT", "PRINTLN", "READ", "IF", "ELSE", "WHILE", "ATTRIB", "PLUS", "MINUS", "TIMES", "OVER", "REMAINDER", "EQUAL", "NE", "GT", "GE", "LT", "LE", "NOT", "AND", "OR", "OF", "TO", "UP_", "DOWN_", "OPEN_P", "CLOSE_P", "OPEN_C", "CLOSE_C", "OPEN_B", "CLOSE_B", "COMMA", "THE", "POWER", "RANDOM", "SPACE", "TAB", "SCOPE", "NL", "NUMBER", "VARIABLE", "COMMENT", "STRING"
    };
    public static final int PRINT=21;
    public static final int ARRAY=15;
    public static final int SCOPE=58;
    public static final int LT=37;
    public static final int FROM=19;
    public static final int MINUS=29;
    public static final int ELSE=25;
    public static final int IF=24;
    public static final int DOWN_=45;
    public static final int INTEGER=12;
    public static final int NUMBER=60;
    public static final int UP_=44;
    public static final int NEWLINE=6;
    public static final int FOR=18;
    public static final int TRUE=7;
    public static final int BOOLEAN=10;
    public static final int READ=23;
    public static final int NOT=39;
    public static final int NE=34;
    public static final int AND=40;
    public static final int REAL=13;
    public static final int NL=59;
    public static final int PLUS=28;
    public static final int PRINTLN=22;
    public static final int STRING_=14;
    public static final int OPEN_P=46;
    public static final int COMMENT=62;
    public static final int INDENT=4;
    public static final int THE=53;
    public static final int SPACE=56;
    public static final int VARIABLE=61;
    public static final int OF=42;
    public static final int CLOSE_P=47;
    public static final int OPEN_C=48;
    public static final int CHARACTER=11;
    public static final int OPEN_B=50;
    public static final int EOF=-1;
    public static final int GE=36;
    public static final int COMMA=52;
    public static final int MATRIX=16;
    public static final int OR=41;
    public static final int TIMES=30;
    public static final int EQUAL=33;
    public static final int RANDOM=55;
    public static final int POWER=54;
    public static final int STEP=20;
    public static final int CLOSE_B=51;
    public static final int GT=35;
    public static final int CLOSE_C=49;
    public static final int OVER=31;
    public static final int DECLARE=17;
    public static final int TAB=57;
    public static final int REMAINDER=32;
    public static final int ATTRIB=27;
    public static final int PI=9;
    public static final int LE=38;
    public static final int STRING=63;
    public static final int FALSE=8;
    public static final int WHILE=26;
    public static final int TO=43;
    public static final int DEDENT=5;

    // delegates
    // delegators


        public TranslatorParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TranslatorParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TranslatorParser.tokenNames; }
    public String getGrammarFileName() { return "Translator.g"; }


        //Constants
        public enum Language {    
            C, C_PLUS_PLUS, JAVA, PYTHON, PORTUGUESE;
        }
        
        public enum Type {
            INTEGER, REAL, CHARACTER, BOOLEAN, STRING;   
        }
        
        public enum HintLevel {
            BASIC, MEDIUM, ADVANCED;
        }
       
        //Variables
        private static Language currentLanguage = Language.C;
        private static HintLevel currentHintLevel = HintLevel.ADVANCED;
        
        private static Map<String, Type> symbolTable = new HashMap();
        private static String printfParamenters = "";   
        private static int currentIndentation = 0;
        
        private static boolean readExists      = false;
        private static boolean printExists     = false;
        private static boolean printLnExists   = false;
        private static boolean mathExists      = false;
        private static boolean booleanExists   = false;
        private static boolean stringExists    = false;
        private static boolean randomExists    = false;
        private static boolean powerExists     = false;
        private static boolean ifExists        = false;
        private static boolean whileExists     = false;
        private static boolean forExists       = false;
        private static boolean piExists        = false;
        private static boolean percentExists   = false;
        private static boolean integerExists   = false;
        private static boolean realExists      = false;
        private static boolean characterExists = false;
        private static boolean equalExists     = false;
        private static boolean notEqualExists  = false;
        private static boolean greaterExists   = false;    
        private static boolean lessExists      = false;
        private static boolean greaterEqExists = false;
        private static boolean lessEqExists    = false;
        private static boolean notExists       = false;
        private static boolean andExists       = false;
        private static boolean orExists        = false;
        private static boolean forUpToExists   = false;
        private static boolean forDownToExists = false;

        private static int forLevel = 0;
        private static int forMaxLevel = 0;
        private static int whileLevel = 0;
        private static int whileMaxLevel = 0;
        private static int ifLevel = 0;
        private static int ifMaxLevel = 0;
        
        private static ArrayList<String> expressionBuffer = new ArrayList<>();
        private static ArrayList<String> mainBuffer       = new ArrayList<>();
        private static ArrayList<String> printBuffer      = new ArrayList<>();    
        private static ArrayList<String> hintBuffer       = new ArrayList<>();
        private static ArrayList<String> headerBuffer     = new ArrayList<>();
        private static ArrayList<String> footerBuffer     = new ArrayList<>();
     
        //Functions
        private static void insertCode(String code) {
            mainBuffer.add(code);
        }
        
        private static void insertIntoHeaderBuffer(String code) {
            headerBuffer.add(code);
        }
        
        
        private static void insertIntoFooterBuffer(String code) {
            footerBuffer.add(code);
        }
        
        private static void insertIntoExpressionBuffer ( String code ) {
            expressionBuffer.add(code);
        }
        
        private static void insertExpressionIntoMainBuffer() {
            mainBuffer.addAll(expressionBuffer);
            expressionBuffer.clear();
        }
        
        private static void insertIntoHintBuffer(String code) {
            hintBuffer.add(code);
        }
        
        private static String standardIdentation () {
            String standardIdentation = "";
            switch (currentLanguage) {
                case JAVA:
                    standardIdentation = "        ";
                    break;
                case C:
                case C_PLUS_PLUS:
                    standardIdentation = "    ";
                    break;
            }
            return standardIdentation;        
        }
        
        private static void insertIndentation () {
            String indentation = "";
            for(int i=0;i<currentIndentation; i++) {
                indentation = indentation + "    ";
            }
            insertCode(indentation + standardIdentation());
        }

        private static void newLine() {
            insertCode ("\n");        
        }
        
        private static void incIndentation() {
            currentIndentation++;
        }
        
        private static void decIndentation() {
            currentIndentation--;
        }
        
        private static void incForLevel() {
            forLevel++;
            if(forLevel > forMaxLevel) {
                forMaxLevel = forLevel;
            }
        }
        
        private static void decForLevel() {
            forLevel--;
        }
        
        private static void incWhileLevel() {
            whileLevel++;
            if(whileLevel > whileMaxLevel) {
                whileMaxLevel = whileLevel;
            }
        }
        
        private static void decWhileLevel() {
            whileLevel--;
        }

        private static void incIfLevel() {
            ifLevel++;
            if(ifLevel > ifMaxLevel) {
                ifMaxLevel = ifLevel;
            }
        }
        
        private static void decIfLevel() {
            ifLevel--;
        }
        
        private static void printOutput() {
            for (String output : mainBuffer) {
                System.out.print(output);
            }
        }

        private static void printHeader() {
            for (String output : headerBuffer) {
                System.out.print(output);
            }
        }
        
        private static void printFooter() {
            for (String output : footerBuffer) {
                System.out.print(output);
            }
        }
        
        private static void printHints() {
            for (String output : hintBuffer) {
                System.out.print(output);
            }        
        }
        /*
         *  This method inserts the output upper part,
         *  depending on the 'currentLanguage' parameter.
         */    
        private static void checkHeader () {
            switch (currentLanguage) {
                case PYTHON:
                    insertPythonHeader();
                    break;
                case JAVA:
                    insertJavaHeader();
                    break;
                case C:
                case C_PLUS_PLUS:
                    insertCHeader();                                
                    break;
            }        
        }
        
        /*
         *  This method inserts the output bottom part,
         *  depending on the 'currentLanguage' parameter.
         */     
        private static void checkFooter () {
            switch (currentLanguage) {
                case JAVA:
                    insertJavaFooter();
                    break;
                case C:
                case C_PLUS_PLUS:
                    insertCFooter();                                
                    break;
            }        
        }
        
        private static void checkForHints() {
            switch (currentHintLevel) {
                case BASIC:
                    checkForBasicHints();
                case MEDIUM:
                    checkForMediumHints();
                case ADVANCED:
                    checkForAdvancedHints();
                    break;
            }
        }
        
        private static void checkForBasicHints() {
            if(integerExists) {
                insertIntoHintBuffer("An integer should be declared.");
            }
            if(realExists) {
                insertIntoHintBuffer("A real (double) should be declared.");
            }
            if(characterExists) {
                insertIntoHintBuffer("A character should be declared.");
            }
            if(booleanExists) {
                insertIntoHintBuffer("A boolean should be declared.");
            }
            if(stringExists) {
                insertIntoHintBuffer("A string should be declared.");
            }
            if(equalExists) {
                insertIntoHintBuffer("An 'equal' operator should be used.");
            }
            if(notEqualExists) {
                insertIntoHintBuffer("A 'not equal' operator should be used.");
            }
            if(greaterExists) {
                insertIntoHintBuffer("A 'greater than' operator should be used.");
            }
            if(lessExists) {
                insertIntoHintBuffer("A 'less than' operator should be used.");
            }
            if(greaterEqExists) {
                insertIntoHintBuffer("A 'greater or equal' operator should be used.");
            }
            if(lessEqExists) {
                insertIntoHintBuffer("A 'less or equal' operator should be used.");
            }
            if(printExists) {
                insertIntoHintBuffer("The 'Print' statement should be used.");
            }
            if(printLnExists) {
                insertIntoHintBuffer("The 'Print Line' statement should be used.");
            }
            if(ifExists) {
                insertIntoHintBuffer("The 'If' statement should be used.");
            }
            if(whileExists) {
                insertIntoHintBuffer("The 'While' statement should be used.");
            }
        }    

        private static void checkForMediumHints() {
            if(percentExists) {
                insertIntoHintBuffer("The percent operator should be used.");
            }
            if(andExists) {
                insertIntoHintBuffer("The logical operator 'and' should be used.");
            }
            if(orExists) {
                insertIntoHintBuffer("The logical operator 'or' should be used.");
            }
            if(readExists) {
                insertIntoHintBuffer("The 'Read' statement should be used.");
            }
            if(forUpToExists) {
                insertIntoHintBuffer("The 'for' statement should be used, and it's counter should be increased.");
            }
            if(forDownToExists) {
                insertIntoHintBuffer("The 'for' statement should be used, and it's counter should be decreased.");;
            }
        }    

        private static void checkForAdvancedHints() {
            if(piExists) {
                insertIntoHintBuffer("The 'PI' constant should be used.");
            }
            if(randomExists) {
                insertIntoHintBuffer("The 'Random' function should be used.");
            }
            if(forDownToExists) {
                insertIntoHintBuffer("The 'Power' function should be used.");
            }     
            if(forMaxLevel > 1) {
                insertIntoHintBuffer("There's a "+ forMaxLevel +"-level nested loop, whith the statement 'for'.");
            }
            if(whileMaxLevel > 1) {
                insertIntoHintBuffer("There's a "+ whileMaxLevel +"-level nested loop, whith the statement 'while'.");
            }
            if(ifMaxLevel > 1) {
                insertIntoHintBuffer("There's a "+ ifMaxLevel +"-level nested 'if' statement.");
            }
        }    
        
        private static void insertPythonHeader() {
            if (randomExists) {
                insertIntoHeaderBuffer("import random");
                insertIntoHeaderBuffer("\n");
            }
        }    
        
        /*
         *  This method inserts the Java language output upper part.
         */     
        private static void insertJavaHeader () {
            insertJavaImports();
            insertIntoHeaderBuffer("public class Exercise\n{\n");
            insertJavaVariables();
            insertIntoHeaderBuffer("    public static void main (String[] args) \n    {\n");
        }

        /*
         *  This method inserts the Java language output bottom part.
         */     
        private static void insertJavaFooter () {
            insertIntoFooterBuffer("    }\n}");
        }

        /*
         *  This method checks the used functions, based on boolean variables, 
         *  and when needed, inserts all necessary imports, in Java language.
         */     
        private static void insertJavaImports() {
            if(mathExists || randomExists || piExists) {
                insertIntoHeaderBuffer("import java.lang.Math;");
                insertIntoHeaderBuffer("\n");
            }
            
            if (readExists) {
                insertIntoHeaderBuffer("import java.util.Scanner;");
                insertIntoHeaderBuffer("\n");
            }
        }

        /*
         *  This method checks the used functions, based on boolean variables, 
         *  and when needed, inserts all global variables, in Java language.
         */     
        private static void insertJavaVariables () {
            if (readExists) {
                insertIntoHeaderBuffer("    public static Scanner scanner = new Scanner(System.in);");
                insertIntoHeaderBuffer("\n");
            }        
        }

        /*
         *  This method inserts the C and C++ language output upper part.
         */     
        private static void insertCHeader () {
            switch (currentLanguage) {
                case C:
                insertCImports();
                break;
                
                case C_PLUS_PLUS:
                insertCPlusPlusImports();
                break;
            }
            insertIntoHeaderBuffer("\n");
            insertIntoHeaderBuffer("int main()");
            insertIntoHeaderBuffer("\n");
            insertIntoHeaderBuffer("{");
            insertIntoHeaderBuffer("\n");
        }

        /*
         *  This method inserts the C and C++ language output bottom part.
         */      
        private static void insertCFooter () {
            insertIntoFooterBuffer("}");
        }

        /*
         *  This method checks the used functions, based on boolean variables, 
         *  and when needed, inserts all necessary imports, in C language.
         */     
        private static void insertCImports () {
            if (printExists || readExists) {
                insertIntoHeaderBuffer("#include <stdio.h>");
                insertIntoHeaderBuffer("\n");
            }
            
            if (mathExists) {
                insertIntoHeaderBuffer("#include <math.h>");
                insertIntoHeaderBuffer("\n");
            }
            
            if (booleanExists) {
                insertIntoHeaderBuffer("#define TRUE    1\n");
                insertIntoHeaderBuffer("#define FALSE   0\n");
                insertIntoHeaderBuffer("\n");
            }
        }

        /*
         *  This method checks the used functions, based on boolean variables, 
         *  and when needed, inserts all necessary imports, in C++ language.
         */     
        private static void insertCPlusPlusImports () {
            
            if (printExists || readExists) {
                insertIntoHeaderBuffer("#include <iostream>");
                insertIntoHeaderBuffer("\n");
                insertIntoHeaderBuffer("#include <stdio.h>");
                insertIntoHeaderBuffer("\n");
            }
            
            if(stringExists) {
                insertIntoHeaderBuffer("#include <string>");
                insertIntoHeaderBuffer("\n");
            }
            
            if (mathExists) {
                insertIntoHeaderBuffer("#include <math.h>");
                insertIntoHeaderBuffer("\n");
            }
            
            if(randomExists) {
                insertIntoHeaderBuffer("#include <stdlib.h>");
                insertIntoHeaderBuffer("\n");
                insertIntoHeaderBuffer("#include <time.h>");
                insertIntoHeaderBuffer("\n");
            }

            insertIntoHeaderBuffer("using namespace std;");
            insertIntoHeaderBuffer("\n");   
           
        }
        
        /*
         *  This method inserts colon when necessary,
         *  depending on 'currentLanguage' parameter.
         *  Python for instance, needs colon after some statements.
         */
        private static void insertColon () {
            switch (currentLanguage) {
                case PYTHON:
                    insertCode(":");
                    break;
            }
        }
            
        /*
         *  This method opens parenthesis when necessary,
         *  depending on 'currentLanguage' parameter.
         *  Python for instance, does not require parenthesis.
         */
        private static void openParenthesis () {
            switch (currentLanguage) {
                case PORTUGUESE:            
                case PYTHON:
                    insertCode("");
                    break;

                default:
                    insertCode("(");
            }        
        }

        /*
         *  This method closes parenthesis when necessary,
         *  depending on 'currentLanguage' parameter.
         *  Python for instance, does not require parenthesis.
         */
        private static void closeParenthesis () {        
            switch (currentLanguage) {
                case PORTUGUESE:
                case PYTHON:
                    insertCode("");
                    break;
                default:
                    insertCode(")");
            }
        }

        /*
         *  This method opens brace when necessary,
         *  depending on 'currentLanguage' parameter.
         *  Python for instance, does not require brace.
         */    
        private static void openBrace () {        
            switch (currentLanguage) {
                case PORTUGUESE:            
                case PYTHON:
                    insertCode("");
                    break;
                default:
                insertCode("{");
            }       
        }

        /*
         *  This method closes brace when necessary,
         *  depending on 'currentLanguage' parameter.
         *  Python for instance, does not require brace.
         */     
        private static void closeBrace () {        
            switch (currentLanguage) {
                case PORTUGUESE:  
                case PYTHON:
                    insertCode("");
                    break;
                default:
                insertCode("}");
            }          
        }

        /*
         *  This method inserts the first part of the 'if' 
         *  statement, depending on 'currentLanguage' parameter.
         */ 
        private static void beginIf () { 
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("se ");
                    break;
                default:
                    insertCode("if ");        
                    openParenthesis();
                    break;
            }
        }

        /*
         *  This method inserts the middle part of the 'if' 
         *  statement, depending on 'currentLanguage' parameter.
         */     
        private static void middleIf () {
            closeParenthesis();
            insertColon();
            if(currentLanguage != Language.PYTHON) {
                newLine();
                insertIndentation();
                openBrace();
            }
        }

        /*
         *  This method inserts the last part of the 'if' 
         *  statement, depending on 'currentLanguage' parameter.
         */     
        private static void endIf () {
            insertIndentation();
            closeBrace();
            newLine();
        }
        
        /*
         *  This method inserts the first part of the 'else' 
         *  statement, depending on 'currentLanguage' parameter.
         */ 
        private static void beginElse () {        
            insertIndentation();
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("caso contrario");
                    break;
                default:   
                    insertCode("else");        
                    insertColon();
            }
            if(currentLanguage != Language.PYTHON) {
                newLine();
                insertIndentation();
                openBrace();
            }
        }

        /*
         *  This method inserts the last part of the 'else' 
         *  statement, depending on 'currentLanguage' parameter.
         */     
        private static void endElse () {
            closeBrace();
            newLine();
        }

        /*
         *  This method inserts semicolon when necessary,
         *  depending on 'currentLanguage' parameter.
         *  Python for instance, does not require semicolon.
         */
        private static void insertSemiColon () {
            //Python does not have semicolon
            switch (currentLanguage) {
                case PORTUGUESE:
                case PYTHON:
                    insertCode("");
                    break;
                default:
                insertCode(";");
            }       
        }
        
        /*
         *   This method returns the specific parameter for 'printf' and 
         *   'scanf' function, depending on the parameter 'type'.
         *   For instance, it will return '%d' when type is Integer.
         */
        private static String cPrintfScanfParameter (Type type) {
            String parameter = "";

            switch (type) { 
                case INTEGER:
                    parameter = "%d";
                    break;
                case REAL:
                    parameter = "%lf";
                    break;
                case CHARACTER:
                    parameter = "%c";
                    break;
                case STRING:
                    parameter = "%s";
                    break;
                case BOOLEAN:
                    System.err.print("%d");
                    break;
            }
            
            return parameter;
        }

        /*
         *   This method returns the specific parameter for the Java 
         *   object 'Scanner', depending on the parameter 'type'.
         *   For instance, it will return 'nextInt()' when type is Integer.
         */    
        private static String javaScannerParameter (Type type) {
            String parameter = "";
            
            switch (type) { 
                case INTEGER:
                    parameter = ".nextInt();";
                    break;
                case REAL:
                    parameter = ".nextDouble();";
                    break;
                case CHARACTER:
                    System.err.print("Error: There's no character in object Scanner!");
                    System.exit(1);
                    break;
                case STRING:
                    parameter = ".nextLine();";
                    break;
                case BOOLEAN:
                    parameter = ".nextBoolean();";
                    break;
            }
            
            return parameter;
        }
        
        private static boolean variableExists (String variable) {
            
            if (symbolTable.get(variable) != null) {
                return true;
            } else { 
                return false;
            }
        }
        
        private static void beginWhile () {
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("enquanto ");
                    break;
                default:
                    insertCode("while ");        
                    openParenthesis(); 
                    break;
            }
        }
        
        private static void middleWhile () {
            closeParenthesis();
            insertColon();
            if(currentLanguage != Language.PYTHON) {
                newLine();
                insertIndentation();
                openBrace();
            }        
        }
        
        private static void endWhile () {
            insertIndentation();
            closeBrace();
            newLine();        
        }
        
        private static void insertAttribution (String variable) {
            if(!variableExists(variable)) {
                System.err.print("Error: Variable '" + variable + "' does not exist!");
            } else {
                insertCode(variable + " = ");
            }
        }
        
        private static String languagePrintSeparator () {
            String separator = "";
            switch (currentLanguage) {
                case JAVA:
                    separator = " + ";
                    break;
                case C:
                    separator = ", ";
                    break;
                case C_PLUS_PLUS:
                    separator = " << ";
                    break;
                case PYTHON:
                case PORTUGUESE:
                    separator = ", ";
                    break;
            }
            return separator;
        }
       
        private static void insertExpressionIntoPrintBuffer (Type type) {

            switch (currentLanguage) {
                case C:
                    printfParamenters += (cPrintfScanfParameter(type) + " ");
                    printBuffer.addAll(expressionBuffer);
                    printBuffer.add(languagePrintSeparator());
                    expressionBuffer.clear();
                    break;
                    
                case PYTHON:
                    if(type != Type.STRING) printBuffer.add("str(");
                    printBuffer.addAll(expressionBuffer);
                    if(type != Type.STRING) printBuffer.add(")");
                    printBuffer.add(languagePrintSeparator());
                    expressionBuffer.clear();
                    break;
                  
                default:
                    printBuffer.addAll(expressionBuffer);
                    printBuffer.add(languagePrintSeparator());
                    expressionBuffer.clear();
                    break;
            }
            
        }
        
        private static void insertPrint (String printType) {
            printExists = true;
            if(printType.equals("println")) printLnExists = true;
            
            switch (currentLanguage) {
                case C:
                    mainBuffer.add("printf(");
                    mainBuffer.add("\""+ printfParamenters + (printType.equals("print") ? "" : "\\n") +"\", ");
                    printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                    mainBuffer.addAll(printBuffer);
                    mainBuffer.add(");");
                    printfParamenters = "";
                    printBuffer.clear();
                    break;
                    
                case JAVA:
                    mainBuffer.add(printType.equals("print") ? "System.out.print(" : "System.out.println(");
                    printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                    mainBuffer.addAll(printBuffer);
                    mainBuffer.add(");");
                    printBuffer.clear();
                    break;
                    
                case C_PLUS_PLUS:
                    mainBuffer.add("cout << ");
                    if(printType.equals("println")) {
                        printBuffer.add("\"\\n\"");
                    } else {
                        printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                    }
                    mainBuffer.addAll(printBuffer);
                    mainBuffer.add(";");
                    printBuffer.clear();
                    break;
                    
                case PYTHON:
                    mainBuffer.add("print (");
                    if (printType.equals("print")) {
                        printBuffer.add("end=\"\"");
                    } else {
                        printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                    }
                    mainBuffer.addAll(printBuffer);
                    mainBuffer.add(")");
                    printBuffer.clear();
                    break;
                    
                case PORTUGUESE:
                    mainBuffer.add(printType.equals("print") ? "imprima " : "imprimalinha ");
                    printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                    mainBuffer.addAll(printBuffer);
                    printBuffer.clear();
                    break;
            }
        }
        
        private static void insertRead (String variable) {
            
            if (!variableExists(variable)) {
                System.err.print("Error: Variable '" +variable+"' does not exist!");
                System.exit(1);
            }
            
            readExists = true;
            
            switch (currentLanguage) {
                case JAVA:
                    insertCode(variable + " = scanner" + javaScannerParameter(symbolTable.get(variable)));
                    break;
                
                case C:
                    insertCode("scanf(\""+cPrintfScanfParameter(symbolTable.get(variable))+"\", &" + variable+");");
                    break;
                
                case C_PLUS_PLUS:
                    insertCode("cin >> " + variable + ";");
                    break;
                
                case PYTHON:
                    insertCode(variable + " = input()");
                    break;
                    
                case PORTUGUESE:
                    insertCode("leia " + variable);
                    break;
            }

        }
        
        private static void insertDeclaration (String variable, Type type) {

            if(symbolTable.put(variable, type) != null ) {
                System.err.println("Error: Variable '"+ variable +"' already exists!");
                System.exit(1);
            }

            if (currentLanguage != Language.PYTHON) {
                
                if(currentLanguage == Language.PORTUGUESE) {
                    insertCode("declare ");
                }
                
                switch (type) {
                    case INTEGER:
                        insertInteger();
                        break;
                    case REAL:
                        insertReal();
                        break;
                    case CHARACTER:
                        insertCharacter();
                        break;
                    case BOOLEAN:
                        insertBoolean();
                        break;
                    case STRING:
                        insertString();
                        break;
                }
                
                insertCode(variable);
                
                insertSemiColon();
            }
        }
        
        private static void beginFor (String variable) {
            switch (currentLanguage) { 
                case PORTUGUESE:
                    insertCode("para "+variable+" de ");
                    insertExpressionIntoMainBuffer();
                    break;
                case PYTHON:
                    insertCode("for "+variable+" in range (");
                    insertExpressionIntoMainBuffer();
                    insertCode(",");
                    break;
                    
                default:
                    insertCode("for ("+variable+"=");
                    insertExpressionIntoMainBuffer();
                    insertCode(";");
                    break;
            }
        }

        private static void middleFor (String variable, String toDownto) {
            switch (currentLanguage) {
                case PORTUGUESE:
                    
                    insertCode(toDownto.equals("up") ? " incremente " : " decremente ");  
                    insertCode("ate ");
                    insertExpressionIntoMainBuffer();                
                    
                    break;
                case PYTHON:
                    insertExpressionIntoMainBuffer();
                    insertCode("+1,");
                    break;
                    
                default:
                    insertCode(variable);
                    insertCode(toDownto.equals("up") ? "<=" : ">=");
                    insertExpressionIntoMainBuffer();
                    insertCode(";");
                    break;
            }        
        }

        private static void endFor (String variable, String toDownto) {
            switch (currentLanguage) {
                case PORTUGUESE:                
                    insertCode(" ao passo ");
                    insertExpressionIntoMainBuffer();
                    break;
                case PYTHON:
                    insertExpressionIntoMainBuffer();
                    insertCode("):");
                    break;
                default:
                    insertCode(variable + "=" + variable);
                    insertCode(toDownto.equals("up") ? "+" : "-");
                    insertExpressionIntoMainBuffer();
                    insertCode(")");
                    break;
            } 
        }
        
        private static void insertInteger () {
            integerExists = true;
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("inteiro ");
                    break;
                case PYTHON:            
                    break;            
                default:
                    insertCode("int ");
                    break;
            }
        }

        private static void insertReal () {
            realExists = true;
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("real ");
                    break;
                case PYTHON:            
                    break;
                default:
                    insertCode("double ");
                    break;
            }        
        }    

        private static void insertCharacter () {
            characterExists = true;
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("caracter ");
                    break;
                case PYTHON:            
                    break;
                default:
                    insertCode("char ");
                    break;
            }        
        }
        
        private static void insertBoolean () {
            booleanExists = true;
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertCode("booleano ");
                    break;
                case PYTHON: 
                    break;
                case C:
                    insertCode("int ");
                    break;
                case C_PLUS_PLUS:
                    insertCode("bool ");
                    break;
                default:
                    insertCode("boolean ");
                    break;
            }        
        }
        
        private static void insertString () {
            stringExists = true;
            switch (currentLanguage) {
                case PYTHON:            
                    break;
                case JAVA:
                    insertCode("String ");
                    break;
                case C:
                    insertCode("char* ");
                    break;
                case PORTUGUESE:
                case C_PLUS_PLUS:
                    insertCode("string ");
                    break;
            }        
        }
        
        private static void insertPi() {
            piExists = true;
            
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertIntoExpressionBuffer("pi ");
                    break;
                case PYTHON:  
                    insertIntoExpressionBuffer("math.pi");            
                    break;
                case JAVA:
                    insertIntoExpressionBuffer("Math.PI");
                    break;
                case C:
                    insertIntoExpressionBuffer("M_PI");
                    break;
                case C_PLUS_PLUS:
                    insertIntoExpressionBuffer("M_PI");
                    break;
            }        
        }
       
        private static void insertTrue() {
            booleanExists = true;
            
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertIntoExpressionBuffer("verdadeiro");
                    break;
                case PYTHON:
                    insertIntoExpressionBuffer("True");
                    break;
                case C:
                    insertIntoExpressionBuffer("TRUE");
                    break;
                default:
                    insertIntoExpressionBuffer("true");
                    break;
            }        
        }  

        private static void insertFalse() {
            booleanExists = true;
            
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertIntoExpressionBuffer("falso");
                    break;
                case PYTHON:
                    insertIntoExpressionBuffer("False");            
                    break;
                case C:
                    insertIntoExpressionBuffer("FALSE");
                    break;
                default:
                    insertIntoExpressionBuffer("false");
                    break;
            }        
        }

        private static void beginPower () {  
            powerExists = true;
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertIntoExpressionBuffer("potencia(");
                    break;
                case JAVA:
                    insertIntoExpressionBuffer("Math.pow(");
                    break;
                case C:
                case C_PLUS_PLUS:
                    insertIntoExpressionBuffer("pow(");
                    break;
            }        
        }
        
        private static void middlePower () {
            switch (currentLanguage) {
                case PYTHON:
                    insertIntoExpressionBuffer("**");
                    break;
                default:
                    insertIntoExpressionBuffer(", ");
                    break;
            }         
        }

        private static void endPower () {
            switch (currentLanguage) {
                case JAVA:
                case C:
                case C_PLUS_PLUS:
                case PORTUGUESE:
                    insertIntoExpressionBuffer(")");
                    break;            
            }         
        }
        
        private static void beginRandom() {
            randomExists = true;
            switch (currentLanguage) {
                case PORTUGUESE:
                    insertIntoExpressionBuffer("aleatorio(");
                    break;
                case PYTHON:
                    insertIntoExpressionBuffer("random.randint(");
                    break;
                case JAVA: 
                    insertIntoExpressionBuffer("(int)((-");
                    break;
                case C:
                case C_PLUS_PLUS:
                    insertIntoExpressionBuffer("rand()%(-");
                    break;            
            }            
            
        }

        private static void middleRandom() {
            switch (currentLanguage) {
                case PORTUGUESE:
                case PYTHON:
                    insertIntoExpressionBuffer(",");
                    break;
                case JAVA: 
                    insertIntoExpressionBuffer("+");
                    break;
                case C:
                case C_PLUS_PLUS:
                    insertIntoExpressionBuffer("+");
                    break;            
            }          
        }

        private static void endRandom(String expression) {
            switch (currentLanguage) {
                case PORTUGUESE:
                case PYTHON:
                    insertIntoExpressionBuffer(")");
                    break;
                case JAVA: 
                    insertIntoExpressionBuffer("+1)*Math.random()+"+expression+")");
                    break;
                case C:
                case C_PLUS_PLUS:
                    insertIntoExpressionBuffer("+1)+"+expression);
                    break;            
            }              
        }    
      
    public static ArrayList<String> getHints() {
        return hintBuffer;
    }

    public static String getSolution() {
        ArrayList<String> arraySolution = new ArrayList<>();
        String solution = "";

        arraySolution.addAll(headerBuffer);
        arraySolution.addAll(mainBuffer);
        arraySolution.addAll(footerBuffer);

        for (String output : arraySolution) {
            solution += output;
        }
        return solution;
    }

    public static void clearAll() {

        symbolTable = new HashMap();
        printfParamenters = "";
        currentIndentation = 0;

        readExists = false;
        printExists = false;
        printLnExists = false;
        mathExists = false;
        booleanExists = false;
        stringExists = false;
        randomExists = false;
        powerExists = false;
        ifExists = false;
        whileExists = false;
        forExists = false;
        piExists = false;
        percentExists = false;
        integerExists = false;
        realExists = false;
        characterExists = false;
        equalExists = false;
        notEqualExists = false;
        greaterExists = false;
        lessExists = false;
        greaterEqExists = false;
        lessEqExists = false;
        notExists = false;
        andExists = false;
        forExists = false;
        forUpToExists = false;
        forDownToExists = false;

        forLevel = 0;
        forMaxLevel = 0;
        whileLevel = 0;
        whileMaxLevel = 0;
        ifLevel = 0;
        ifMaxLevel = 0;

        expressionBuffer = new ArrayList<>();
        mainBuffer = new ArrayList<>();
        printBuffer = new ArrayList<>();
        hintBuffer = new ArrayList<>();
        headerBuffer = new ArrayList<>();
        footerBuffer = new ArrayList<>();

    }

    public static void setOutputLanguage(TranslatorParser.Language language) {
        currentLanguage = language;
    }

    public static void setHintLevel(TranslatorParser.HintLevel hintLevel) {
        currentHintLevel = hintLevel;
    }

    public static void translate(String pseudocode) throws Exception {
        clearAll();
        InputStream stream = new ByteArrayInputStream(pseudocode.getBytes(StandardCharsets.UTF_8));
        ANTLRInputStream input = new ANTLRInputStream(stream);
        TranslatorLexer lexer = new TranslatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TranslatorParser parser = new TranslatorParser(tokens);

        parser.program();

        checkHeader();
        checkFooter();
        checkForHints();
    }



    // $ANTLR start "program"
    // Translator.g:1385:1: program : ( statement )* ;
    public final void program() throws RecognitionException {
        try {
            // Translator.g:1386:5: ( ( statement )* )
            // Translator.g:1387:9: ( statement )*
            {
            // Translator.g:1387:9: ( statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NEWLINE||(LA1_0>=DECLARE && LA1_0<=FOR)||(LA1_0>=PRINT && LA1_0<=IF)||LA1_0==WHILE||LA1_0==VARIABLE) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Translator.g:1387:11: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_program2264);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "program"


    // $ANTLR start "statement"
    // Translator.g:1390:1: statement : ( declare | attribution | read | print | while_ | if_ | for_ | nl_ );
    public final void statement() throws RecognitionException {
        try {
            // Translator.g:1391:5: ( declare | attribution | read | print | while_ | if_ | for_ | nl_ )
            int alt2=8;
            switch ( input.LA(1) ) {
            case DECLARE:
                {
                alt2=1;
                }
                break;
            case VARIABLE:
                {
                alt2=2;
                }
                break;
            case READ:
                {
                alt2=3;
                }
                break;
            case PRINT:
            case PRINTLN:
                {
                alt2=4;
                }
                break;
            case WHILE:
                {
                alt2=5;
                }
                break;
            case IF:
                {
                alt2=6;
                }
                break;
            case FOR:
                {
                alt2=7;
                }
                break;
            case NEWLINE:
                {
                alt2=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Translator.g:1392:9: declare
                    {
                    pushFollow(FOLLOW_declare_in_statement2295);
                    declare();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Translator.g:1393:9: attribution
                    {
                    pushFollow(FOLLOW_attribution_in_statement2317);
                    attribution();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // Translator.g:1394:9: read
                    {
                    pushFollow(FOLLOW_read_in_statement2335);
                    read();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // Translator.g:1395:9: print
                    {
                    pushFollow(FOLLOW_print_in_statement2360);
                    print();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // Translator.g:1396:9: while_
                    {
                    pushFollow(FOLLOW_while__in_statement2383);
                    while_();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // Translator.g:1397:9: if_
                    {
                    pushFollow(FOLLOW_if__in_statement2406);
                    if_();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // Translator.g:1398:9: for_
                    {
                    pushFollow(FOLLOW_for__in_statement2432);
                    for_();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // Translator.g:1399:9: nl_
                    {
                    pushFollow(FOLLOW_nl__in_statement2456);
                    nl_();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "declare"
    // Translator.g:1402:1: declare : DECLARE tp= type VARIABLE NEWLINE ;
    public final void declare() throws RecognitionException {
        Token VARIABLE1=null;
        Type tp = null;


        try {
            // Translator.g:1403:5: ( DECLARE tp= type VARIABLE NEWLINE )
            // Translator.g:1404:5: DECLARE tp= type VARIABLE NEWLINE
            {
            insertIndentation();
            match(input,DECLARE,FOLLOW_DECLARE_in_declare2504); 
            pushFollow(FOLLOW_type_in_declare2520);
            tp=type();

            state._fsp--;

            VARIABLE1=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_declare2531); 

                    insertDeclaration((VARIABLE1!=null?VARIABLE1.getText():null), tp);
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_declare2550); 

                    //Declaration should not print new line in Python language
                    //since it does not print any declaration
                    if(currentLanguage != Language.PYTHON) {
                        newLine();
                    }
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declare"


    // $ANTLR start "attribution"
    // Translator.g:1425:1: attribution : VARIABLE ATTRIB ex= expression NEWLINE ;
    public final void attribution() throws RecognitionException {
        Token VARIABLE2=null;
        TranslatorParser.expression_return ex = null;


        try {
            // Translator.g:1426:5: ( VARIABLE ATTRIB ex= expression NEWLINE )
            // Translator.g:1427:5: VARIABLE ATTRIB ex= expression NEWLINE
            {
            insertIndentation();
            VARIABLE2=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_attribution2586); 
            match(input,ATTRIB,FOLLOW_ATTRIB_in_attribution2602); 

                    insertAttribution ((VARIABLE2!=null?VARIABLE2.getText():null)); 
                
            pushFollow(FOLLOW_expression_in_attribution2619);
            ex=expression();

            state._fsp--;

                    
                    insertExpressionIntoMainBuffer();
                    insertSemiColon();
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_attribution2636); 
            newLine();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "attribution"


    // $ANTLR start "read"
    // Translator.g:1445:1: read : READ VARIABLE NEWLINE ;
    public final void read() throws RecognitionException {
        Token VARIABLE3=null;

        try {
            // Translator.g:1446:5: ( READ VARIABLE NEWLINE )
            // Translator.g:1447:5: READ VARIABLE NEWLINE
            {
            insertIndentation();
            match(input,READ,FOLLOW_READ_in_read2673); 
            VARIABLE3=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_read2675); 
                
                    insertRead((VARIABLE3!=null?VARIABLE3.getText():null));
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_read2687); 
            newLine();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "read"


    // $ANTLR start "print"
    // Translator.g:1456:1: print : printType= ( PRINT | PRINTLN ) ex= expression ( COMMA ex2= expression )* NEWLINE ;
    public final void print() throws RecognitionException {
        Token printType=null;
        TranslatorParser.expression_return ex = null;

        TranslatorParser.expression_return ex2 = null;


        try {
            // Translator.g:1457:5: (printType= ( PRINT | PRINTLN ) ex= expression ( COMMA ex2= expression )* NEWLINE )
            // Translator.g:1458:5: printType= ( PRINT | PRINTLN ) ex= expression ( COMMA ex2= expression )* NEWLINE
            {
            insertIndentation();
            printType=(Token)input.LT(1);
            if ( (input.LA(1)>=PRINT && input.LA(1)<=PRINTLN) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            pushFollow(FOLLOW_expression_in_print2755);
            ex=expression();

            state._fsp--;


                    insertExpressionIntoPrintBuffer ((ex!=null?ex.type:null));
                
            // Translator.g:1466:5: ( COMMA ex2= expression )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Translator.g:1467:9: COMMA ex2= expression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_print2783); 
            	    pushFollow(FOLLOW_expression_in_print2789);
            	    ex2=expression();

            	    state._fsp--;


            	                insertExpressionIntoPrintBuffer ((ex2!=null?ex2.type:null));
            	            

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


                    insertPrint((printType!=null?printType.getText():null));       
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_print2829); 
            newLine();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "print"


    // $ANTLR start "if_"
    // Translator.g:1481:1: if_ : IF expression NEWLINE INDENT ( statement )* DEDENT ( ELSE NEWLINE INDENT ( statement )* DEDENT )? ;
    public final void if_() throws RecognitionException {
        try {
            // Translator.g:1482:5: ( IF expression NEWLINE INDENT ( statement )* DEDENT ( ELSE NEWLINE INDENT ( statement )* DEDENT )? )
            // Translator.g:1483:5: IF expression NEWLINE INDENT ( statement )* DEDENT ( ELSE NEWLINE INDENT ( statement )* DEDENT )?
            {
            insertIndentation();
            match(input,IF,FOLLOW_IF_in_if_2870); 
            beginIf();
            pushFollow(FOLLOW_expression_in_if_2889);
            expression();

            state._fsp--;


                    insertExpressionIntoMainBuffer();
                    middleIf();
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_if_2908); 
            match(input,INDENT,FOLLOW_INDENT_in_if_2919); 

                    incIfLevel();
                    incIndentation();
                    if(currentLanguage != Language.PORTUGUESE && 
                       currentLanguage != Language.PYTHON) {
                        newLine();
                    }
                
            // Translator.g:1504:5: ( statement )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==NEWLINE||(LA4_0>=DECLARE && LA4_0<=FOR)||(LA4_0>=PRINT && LA4_0<=IF)||LA4_0==WHILE||LA4_0==VARIABLE) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Translator.g:1504:6: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_if_2932);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


                    decIfLevel();
                    decIndentation();
                    endIf();
                
            match(input,DEDENT,FOLLOW_DEDENT_in_if_2947); 
            // Translator.g:1512:5: ( ELSE NEWLINE INDENT ( statement )* DEDENT )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ELSE) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Translator.g:1513:5: ELSE NEWLINE INDENT ( statement )* DEDENT
                    {
                    match(input,ELSE,FOLLOW_ELSE_in_if_2960); 
                     
                            beginElse();
                            if(currentLanguage != Language.PORTUGUESE && 
                               currentLanguage != Language.PYTHON) {
                                newLine();
                            }
                        
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_if_2974); 
                    match(input,INDENT,FOLLOW_INDENT_in_if_2985); 

                            incIndentation();
                        
                    // Translator.g:1528:5: ( statement )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==NEWLINE||(LA5_0>=DECLARE && LA5_0<=FOR)||(LA5_0>=PRINT && LA5_0<=IF)||LA5_0==WHILE||LA5_0==VARIABLE) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // Translator.g:1528:6: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_if_3004);
                    	    statement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                            decIndentation();
                            insertIndentation();
                            endElse();
                        
                    match(input,DEDENT,FOLLOW_DEDENT_in_if_3029); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "if_"


    // $ANTLR start "while_"
    // Translator.g:1540:1: while_ : WHILE expression NEWLINE INDENT ( statement )* DEDENT ;
    public final void while_() throws RecognitionException {
        try {
            // Translator.g:1541:5: ( WHILE expression NEWLINE INDENT ( statement )* DEDENT )
            // Translator.g:1542:5: WHILE expression NEWLINE INDENT ( statement )* DEDENT
            {
            insertIndentation();
            match(input,WHILE,FOLLOW_WHILE_in_while_3071); 

                    beginWhile();
                
            pushFollow(FOLLOW_expression_in_while_3095);
            expression();

            state._fsp--;


                    insertExpressionIntoMainBuffer();
                    middleWhile();
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_while_3114); 
            match(input,INDENT,FOLLOW_INDENT_in_while_3125); 

                    incWhileLevel();
                    incIndentation();
                    if(currentLanguage != Language.PORTUGUESE && 
                       currentLanguage != Language.PYTHON) {
                        newLine();
                    }
                
            // Translator.g:1565:5: ( statement )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==NEWLINE||(LA7_0>=DECLARE && LA7_0<=FOR)||(LA7_0>=PRINT && LA7_0<=IF)||LA7_0==WHILE||LA7_0==VARIABLE) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Translator.g:1565:6: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_while_3140);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


                    decWhileLevel();
                    decIndentation();
                    endWhile();
                
            match(input,DEDENT,FOLLOW_DEDENT_in_while_3156); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "while_"


    // $ANTLR start "for_"
    // Translator.g:1574:1: for_ : FOR VARIABLE FROM ex1= expression op= ( UP_ | DOWN_ ) TO ex2= expression STEP ex3= expression NEWLINE INDENT ( statement )* DEDENT ;
    public final void for_() throws RecognitionException {
        Token op=null;
        Token VARIABLE4=null;
        TranslatorParser.expression_return ex1 = null;

        TranslatorParser.expression_return ex2 = null;

        TranslatorParser.expression_return ex3 = null;


        try {
            // Translator.g:1575:5: ( FOR VARIABLE FROM ex1= expression op= ( UP_ | DOWN_ ) TO ex2= expression STEP ex3= expression NEWLINE INDENT ( statement )* DEDENT )
            // Translator.g:1576:5: FOR VARIABLE FROM ex1= expression op= ( UP_ | DOWN_ ) TO ex2= expression STEP ex3= expression NEWLINE INDENT ( statement )* DEDENT
            {
            insertIndentation();
            match(input,FOR,FOLLOW_FOR_in_for_3192); 
            VARIABLE4=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_for_3194); 
            match(input,FROM,FOLLOW_FROM_in_for_3196); 
            pushFollow(FOLLOW_expression_in_for_3202);
            ex1=expression();

            state._fsp--;


                    beginFor((VARIABLE4!=null?VARIABLE4.getText():null));
                
            op=(Token)input.LT(1);
            if ( (input.LA(1)>=UP_ && input.LA(1)<=DOWN_) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            match(input,TO,FOLLOW_TO_in_for_3229); 
            pushFollow(FOLLOW_expression_in_for_3235);
            ex2=expression();

            state._fsp--;


                    middleFor((VARIABLE4!=null?VARIABLE4.getText():null), (op!=null?op.getText():null));
                
            match(input,STEP,FOLLOW_STEP_in_for_3248); 
            pushFollow(FOLLOW_expression_in_for_3254);
            ex3=expression();

            state._fsp--;


                    endFor((VARIABLE4!=null?VARIABLE4.getText():null), (op!=null?op.getText():null));
                
            match(input,NEWLINE,FOLLOW_NEWLINE_in_for_3272); 
            match(input,INDENT,FOLLOW_INDENT_in_for_3279); 

                    incForLevel();
                    if(currentLanguage != Language.PORTUGUESE && 
                       currentLanguage != Language.PYTHON) {
                        newLine();
                    }
                    insertIndentation();
                    openBrace();
                    incIndentation();
                    newLine();
                
            // Translator.g:1604:5: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==NEWLINE||(LA8_0>=DECLARE && LA8_0<=FOR)||(LA8_0>=PRINT && LA8_0<=IF)||LA8_0==WHILE||LA8_0==VARIABLE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Translator.g:1604:6: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_for_3294);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


                    decForLevel();
                    decIndentation();
                    insertIndentation();    
                    closeBrace();
                    if(currentLanguage != Language.PORTUGUESE && 
                       currentLanguage != Language.PYTHON) {
                        newLine();
                    }
                
            match(input,DEDENT,FOLLOW_DEDENT_in_for_3310); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "for_"


    // $ANTLR start "nl_"
    // Translator.g:1618:1: nl_ : NEWLINE ;
    public final void nl_() throws RecognitionException {
        try {
            // Translator.g:1619:5: ( NEWLINE )
            // Translator.g:1620:5: NEWLINE
            {
            match(input,NEWLINE,FOLLOW_NEWLINE_in_nl_3337); 
            newLine();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "nl_"

    public static class expression_return extends ParserRuleReturnScope {
        public Type type;
    };

    // $ANTLR start "expression"
    // Translator.g:1624:1: expression returns [Type type] : un1= unary_not ( (op= ( AND | OR ) un2= unary_not ) )* ;
    public final TranslatorParser.expression_return expression() throws RecognitionException {
        TranslatorParser.expression_return retval = new TranslatorParser.expression_return();
        retval.start = input.LT(1);

        Token op=null;
        Type un1 = null;

        Type un2 = null;


        try {
            // Translator.g:1625:5: (un1= unary_not ( (op= ( AND | OR ) un2= unary_not ) )* )
            // Translator.g:1626:9: un1= unary_not ( (op= ( AND | OR ) un2= unary_not ) )*
            {
            pushFollow(FOLLOW_unary_not_in_expression3376);
            un1=unary_not();

            state._fsp--;

            retval.type = un1;
            // Translator.g:1628:9: ( (op= ( AND | OR ) un2= unary_not ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=AND && LA9_0<=OR)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Translator.g:1629:13: (op= ( AND | OR ) un2= unary_not )
            	    {
            	    // Translator.g:1629:13: (op= ( AND | OR ) un2= unary_not )
            	    // Translator.g:1629:15: op= ( AND | OR ) un2= unary_not
            	    {
            	    op=(Token)input.LT(1);
            	    if ( (input.LA(1)>=AND && input.LA(1)<=OR) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	                 
            	                        String val = null;
            	                    
            	                        if ((op!=null?op.getType():0) == AND)      {val = currentLanguage == Language.PYTHON ? "and" : "&&"; andExists = true;}
            	                        if ((op!=null?op.getType():0) == OR)       {val = currentLanguage == Language.PYTHON ? "or"  : "||"; orExists = true;} 
            	                        
            	                        insertIntoExpressionBuffer(val);
            	                    
            	    pushFollow(FOLLOW_unary_not_in_expression3463);
            	    un2=unary_not();

            	    state._fsp--;


            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"


    // $ANTLR start "unary_not"
    // Translator.g:1643:1: unary_not returns [Type type] : ( NOT )? e1= exp_comparison ;
    public final Type unary_not() throws RecognitionException {
        Type type = null;

        Type e1 = null;


        try {
            // Translator.g:1644:5: ( ( NOT )? e1= exp_comparison )
            // Translator.g:1645:9: ( NOT )? e1= exp_comparison
            {
            // Translator.g:1645:9: ( NOT )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NOT) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Translator.g:1645:10: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_unary_not3539); 
                    insertIntoExpressionBuffer("!");

                    }
                    break;

            }

            pushFollow(FOLLOW_exp_comparison_in_unary_not3588);
            e1=exp_comparison();

            state._fsp--;

            type = e1;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "unary_not"


    // $ANTLR start "exp_comparison"
    // Translator.g:1653:1: exp_comparison returns [Type type] : e1= exp_arithmetic (op= ( EQUAL | NE | GT | GE | LT | LE ) e2= exp_arithmetic )? ;
    public final Type exp_comparison() throws RecognitionException {
        Type type = null;

        Token op=null;
        Type e1 = null;

        Type e2 = null;


        try {
            // Translator.g:1654:5: (e1= exp_arithmetic (op= ( EQUAL | NE | GT | GE | LT | LE ) e2= exp_arithmetic )? )
            // Translator.g:1655:6: e1= exp_arithmetic (op= ( EQUAL | NE | GT | GE | LT | LE ) e2= exp_arithmetic )?
            {
            pushFollow(FOLLOW_exp_arithmetic_in_exp_comparison3636);
            e1=exp_arithmetic();

            state._fsp--;

            type = e1;
            // Translator.g:1658:5: (op= ( EQUAL | NE | GT | GE | LT | LE ) e2= exp_arithmetic )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=EQUAL && LA11_0<=LE)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Translator.g:1658:7: op= ( EQUAL | NE | GT | GE | LT | LE ) e2= exp_arithmetic
                    {
                    op=(Token)input.LT(1);
                    if ( (input.LA(1)>=EQUAL && input.LA(1)<=LE) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                                 
                                String val = null;
                            
                                if ((op!=null?op.getType():0) == EQUAL)      {val = " == "; equalExists = true;}
                                if ((op!=null?op.getType():0) == NE)         {val = " != "; notEqualExists = true;} 
                                if ((op!=null?op.getType():0) == GT)         {val = " > " ; greaterExists = true;}
                                if ((op!=null?op.getType():0) == GE)         {val = " >= "; greaterEqExists = true;}
                                if ((op!=null?op.getType():0) == LT)         {val = " < " ; lessExists = true;}
                                if ((op!=null?op.getType():0) == LE)         {val = " <= "; lessEqExists = true;}

                                insertIntoExpressionBuffer(val);
                            
                    pushFollow(FOLLOW_exp_arithmetic_in_exp_comparison3709);
                    e2=exp_arithmetic();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "exp_comparison"


    // $ANTLR start "exp_arithmetic"
    // Translator.g:1675:1: exp_arithmetic returns [Type type] : t1= term (op= ( PLUS | MINUS ) t2= term )* ;
    public final Type exp_arithmetic() throws RecognitionException {
        Type type = null;

        Token op=null;
        Type t1 = null;

        Type t2 = null;


        try {
            // Translator.g:1676:5: (t1= term (op= ( PLUS | MINUS ) t2= term )* )
            // Translator.g:1676:9: t1= term (op= ( PLUS | MINUS ) t2= term )*
            {
            pushFollow(FOLLOW_term_in_exp_arithmetic3756);
            t1=term();

            state._fsp--;

            type = t1;
            // Translator.g:1678:9: (op= ( PLUS | MINUS ) t2= term )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=PLUS && LA12_0<=MINUS)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Translator.g:1678:11: op= ( PLUS | MINUS ) t2= term
            	    {
            	    op=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	     
            	                    if ((op!=null?op.getType():0) == PLUS)  { insertIntoExpressionBuffer("+"); }
            	                    if ((op!=null?op.getType():0) == MINUS) { insertIntoExpressionBuffer("-");}
            	                
            	    pushFollow(FOLLOW_term_in_exp_arithmetic3822);
            	    t2=term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "exp_arithmetic"


    // $ANTLR start "term"
    // Translator.g:1687:1: term returns [Type type] : u1= unary (op= ( TIMES | OVER | REMAINDER ) u2= unary )* ;
    public final Type term() throws RecognitionException {
        Type type = null;

        Token op=null;
        Type u1 = null;

        Type u2 = null;


        try {
            // Translator.g:1688:5: (u1= unary (op= ( TIMES | OVER | REMAINDER ) u2= unary )* )
            // Translator.g:1689:5: u1= unary (op= ( TIMES | OVER | REMAINDER ) u2= unary )*
            {
            pushFollow(FOLLOW_unary_in_term3880);
            u1=unary();

            state._fsp--;

            type = u1;
            // Translator.g:1691:5: (op= ( TIMES | OVER | REMAINDER ) u2= unary )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=TIMES && LA13_0<=REMAINDER)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // Translator.g:1691:7: op= ( TIMES | OVER | REMAINDER ) u2= unary
            	    {
            	    op=(Token)input.LT(1);
            	    if ( (input.LA(1)>=TIMES && input.LA(1)<=REMAINDER) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	     
            	                if ((op!=null?op.getType():0) == TIMES)     { insertIntoExpressionBuffer("*");}
            	                if ((op!=null?op.getType():0) == OVER)      { insertIntoExpressionBuffer("/");}
            	                if ((op!=null?op.getType():0) == REMAINDER) { insertIntoExpressionBuffer("%"); percentExists = true; }
            	            
            	    pushFollow(FOLLOW_unary_in_term3931);
            	    u2=unary();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "term"


    // $ANTLR start "unary"
    // Translator.g:1701:1: unary returns [Type type] : ( PLUS )? ( MINUS )? f1= factor ;
    public final Type unary() throws RecognitionException {
        Type type = null;

        Type f1 = null;


        try {
            // Translator.g:1702:5: ( ( PLUS )? ( MINUS )? f1= factor )
            // Translator.g:1703:9: ( PLUS )? ( MINUS )? f1= factor
            {
            // Translator.g:1703:9: ( PLUS )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==PLUS) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // Translator.g:1703:10: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_unary3977); 

                                insertIntoExpressionBuffer("+");
                            

                    }
                    break;

            }

            // Translator.g:1708:9: ( MINUS )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==MINUS) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // Translator.g:1708:10: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_unary4009); 

                                insertIntoExpressionBuffer("-");
                            

                    }
                    break;

            }

            pushFollow(FOLLOW_factor_in_unary4053);
            f1=factor();

            state._fsp--;

            type = f1;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "unary"


    // $ANTLR start "factor"
    // Translator.g:1717:1: factor returns [Type type] : ( NUMBER | VARIABLE | STRING | TRUE | FALSE | PI | POWER OPEN_P expression COMMA expression CLOSE_P | RANDOM OPEN_P ex= expression COMMA expression CLOSE_P | OPEN_P ex= expression CLOSE_P );
    public final Type factor() throws RecognitionException {
        Type type = null;

        Token NUMBER5=null;
        Token VARIABLE6=null;
        Token STRING7=null;
        TranslatorParser.expression_return ex = null;


        try {
            // Translator.g:1718:5: ( NUMBER | VARIABLE | STRING | TRUE | FALSE | PI | POWER OPEN_P expression COMMA expression CLOSE_P | RANDOM OPEN_P ex= expression COMMA expression CLOSE_P | OPEN_P ex= expression CLOSE_P )
            int alt16=9;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt16=1;
                }
                break;
            case VARIABLE:
                {
                alt16=2;
                }
                break;
            case STRING:
                {
                alt16=3;
                }
                break;
            case TRUE:
                {
                alt16=4;
                }
                break;
            case FALSE:
                {
                alt16=5;
                }
                break;
            case PI:
                {
                alt16=6;
                }
                break;
            case POWER:
                {
                alt16=7;
                }
                break;
            case RANDOM:
                {
                alt16=8;
                }
                break;
            case OPEN_P:
                {
                alt16=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // Translator.g:1719:9: NUMBER
                    {
                    NUMBER5=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_factor4099); 
                     
                                //Inserts code and returns integer or real
                                insertIntoExpressionBuffer((NUMBER5!=null?NUMBER5.getText():null));
                                type = (NUMBER5!=null?NUMBER5.getText():null).contains(".") ? Type.REAL : Type.INTEGER;
                            

                    }
                    break;
                case 2 :
                    // Translator.g:1726:9: VARIABLE
                    {
                    VARIABLE6=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_factor4125); 

                                //Inserts code and returns variable type
                                //Prints error if variable does not exist
                                insertIntoExpressionBuffer((VARIABLE6!=null?VARIABLE6.getText():null));

                                type = symbolTable.get((VARIABLE6!=null?VARIABLE6.getText():null));

                                if(type == null) {
                                    System.err.print ("Error: Variable '"+(VARIABLE6!=null?VARIABLE6.getText():null)+"' does not exist");
                                }
                            

                    }
                    break;
                case 3 :
                    // Translator.g:1739:9: STRING
                    {
                    STRING7=(Token)match(input,STRING,FOLLOW_STRING_in_factor4154); 

                                //Inserts code and returns string
                                insertIntoExpressionBuffer((STRING7!=null?STRING7.getText():null));
                                type = Type.STRING; 
                            

                    }
                    break;
                case 4 :
                    // Translator.g:1746:9: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_factor4184); 

                                insertTrue();
                                type = Type.BOOLEAN; 
                            

                    }
                    break;
                case 5 :
                    // Translator.g:1752:9: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_factor4209); 

                                insertFalse();
                                type = Type.BOOLEAN; 
                            

                    }
                    break;
                case 6 :
                    // Translator.g:1758:9: PI
                    {
                    match(input,PI,FOLLOW_PI_in_factor4234); 

                                insertPi();
                                type = Type.REAL; 
                            

                    }
                    break;
                case 7 :
                    // Translator.g:1764:9: POWER OPEN_P expression COMMA expression CLOSE_P
                    {
                    match(input,POWER,FOLLOW_POWER_in_factor4263); 
                    match(input,OPEN_P,FOLLOW_OPEN_P_in_factor4265); 
                         
                                beginPower();
                            
                    pushFollow(FOLLOW_expression_in_factor4295);
                    expression();

                    state._fsp--;

                    match(input,COMMA,FOLLOW_COMMA_in_factor4313); 

                                middlePower();
                            
                    pushFollow(FOLLOW_expression_in_factor4341);
                    expression();

                    state._fsp--;

                    match(input,CLOSE_P,FOLLOW_CLOSE_P_in_factor4359); 

                              endPower();
                              type = Type.REAL;
                            

                    }
                    break;
                case 8 :
                    // Translator.g:1785:7: RANDOM OPEN_P ex= expression COMMA expression CLOSE_P
                    {
                    match(input,RANDOM,FOLLOW_RANDOM_in_factor4389); 
                    match(input,OPEN_P,FOLLOW_OPEN_P_in_factor4391); 

                                beginRandom();
                            
                    pushFollow(FOLLOW_expression_in_factor4425);
                    ex=expression();

                    state._fsp--;

                    match(input,COMMA,FOLLOW_COMMA_in_factor4445); 

                                middleRandom();
                            
                    pushFollow(FOLLOW_expression_in_factor4475);
                    expression();

                    state._fsp--;

                    match(input,CLOSE_P,FOLLOW_CLOSE_P_in_factor4495); 

                                endRandom((ex!=null?input.toString(ex.start,ex.stop):null));
                            

                    }
                    break;
                case 9 :
                    // Translator.g:1804:9: OPEN_P ex= expression CLOSE_P
                    {
                    match(input,OPEN_P,FOLLOW_OPEN_P_in_factor4520); 

                                insertIntoExpressionBuffer("(");
                            
                    pushFollow(FOLLOW_expression_in_factor4545);
                    ex=expression();

                    state._fsp--;

                    match(input,CLOSE_P,FOLLOW_CLOSE_P_in_factor4565); 

                                insertIntoExpressionBuffer(")");
                                type = (ex!=null?ex.type:null);            
                            

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "factor"


    // $ANTLR start "type"
    // Translator.g:1817:1: type returns [Type type] : ( INTEGER | REAL | CHARACTER | BOOLEAN | STRING_ );
    public final Type type() throws RecognitionException {
        Type type = null;

        try {
            // Translator.g:1818:5: ( INTEGER | REAL | CHARACTER | BOOLEAN | STRING_ )
            int alt17=5;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt17=1;
                }
                break;
            case REAL:
                {
                alt17=2;
                }
                break;
            case CHARACTER:
                {
                alt17=3;
                }
                break;
            case BOOLEAN:
                {
                alt17=4;
                }
                break;
            case STRING_:
                {
                alt17=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // Translator.g:1819:5: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_type4604); 
                     type = Type.INTEGER;   

                    }
                    break;
                case 2 :
                    // Translator.g:1820:5: REAL
                    {
                    match(input,REAL,FOLLOW_REAL_in_type4620); 
                     type = Type.REAL;      

                    }
                    break;
                case 3 :
                    // Translator.g:1821:5: CHARACTER
                    {
                    match(input,CHARACTER,FOLLOW_CHARACTER_in_type4639); 
                     type = Type.CHARACTER; 

                    }
                    break;
                case 4 :
                    // Translator.g:1822:5: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_type4653); 
                     type = Type.BOOLEAN;   

                    }
                    break;
                case 5 :
                    // Translator.g:1823:5: STRING_
                    {
                    match(input,STRING_,FOLLOW_STRING__in_type4669); 
                     type = Type.STRING;    

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "type"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_program2264 = new BitSet(new long[]{0x2000000005E60042L});
    public static final BitSet FOLLOW_declare_in_statement2295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribution_in_statement2317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_in_statement2335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_in_statement2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while__in_statement2383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if__in_statement2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_statement2432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nl__in_statement2456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declare2504 = new BitSet(new long[]{0x0000000000007C00L});
    public static final BitSet FOLLOW_type_in_declare2520 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_VARIABLE_in_declare2531 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_declare2550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_attribution2586 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ATTRIB_in_attribution2602 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_attribution2619 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_attribution2636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_READ_in_read2673 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_VARIABLE_in_read2675 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_read2687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_print2734 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_print2755 = new BitSet(new long[]{0x0010000000000040L});
    public static final BitSet FOLLOW_COMMA_in_print2783 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_print2789 = new BitSet(new long[]{0x0010000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_print2829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_2870 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_if_2889 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_if_2908 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_if_2919 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_statement_in_if_2932 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_DEDENT_in_if_2947 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ELSE_in_if_2960 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_if_2974 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_if_2985 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_statement_in_if_3004 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_DEDENT_in_if_3029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_3071 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_while_3095 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_while_3114 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_while_3125 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_statement_in_while_3140 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_DEDENT_in_while_3156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_3192 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_VARIABLE_in_for_3194 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_FROM_in_for_3196 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_for_3202 = new BitSet(new long[]{0x0000300000000000L});
    public static final BitSet FOLLOW_set_in_for_3219 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_TO_in_for_3229 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_for_3235 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STEP_in_for_3248 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_for_3254 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_for_3272 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_for_3279 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_statement_in_for_3294 = new BitSet(new long[]{0x2000000005E60060L});
    public static final BitSet FOLLOW_DEDENT_in_for_3310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_nl_3337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_not_in_expression3376 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_set_in_expression3416 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_unary_not_in_expression3463 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_NOT_in_unary_not3539 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_exp_comparison_in_unary_not3588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_arithmetic_in_exp_comparison3636 = new BitSet(new long[]{0x0000007E00000002L});
    public static final BitSet FOLLOW_set_in_exp_comparison3662 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_exp_arithmetic_in_exp_comparison3709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_exp_arithmetic3756 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_set_in_exp_arithmetic3783 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_term_in_exp_arithmetic3822 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_unary_in_term3880 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_set_in_term3898 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_unary_in_term3931 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_PLUS_in_unary3977 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_MINUS_in_unary4009 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_factor_in_unary4053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_factor4099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_factor4125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_factor4154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_factor4184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_factor4209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PI_in_factor4234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POWER_in_factor4263 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_OPEN_P_in_factor4265 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_factor4295 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_COMMA_in_factor4313 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_factor4341 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_CLOSE_P_in_factor4359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANDOM_in_factor4389 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_OPEN_P_in_factor4391 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_factor4425 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_COMMA_in_factor4445 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_factor4475 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_CLOSE_P_in_factor4495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_P_in_factor4520 = new BitSet(new long[]{0xB0C0408030000380L});
    public static final BitSet FOLLOW_expression_in_factor4545 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_CLOSE_P_in_factor4565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_type4604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_type4620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_in_type4639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_type4653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING__in_type4669 = new BitSet(new long[]{0x0000000000000002L});

}