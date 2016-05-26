grammar Translator;

/*---------------- TOKEN DEFINITIONS ----------------*/

tokens
{
    /*-------Indentation------*/
    INDENT     = 'INDENT'                   ;
    DEDENT     = 'DEDENT'                   ;
    NEWLINE    = 'NL'                       ;
    
    /*-------Constants------*/
    TRUE       = 'true'                     ;
    FALSE      = 'false'                    ;
    PI         = 'pi'                       ;
    
    /*----------Types----------*/
    BOOLEAN    = 'boolean'                  ;
    CHARACTER  = 'character'                ;
    INTEGER    = 'integer'                  ;
    REAL       = 'real'                     ;
    STRING_    = 'string'                   ;
    ARRAY      = 'array'                    ;
    MATRIX     = 'matrix'                   ;

    /*---------Commands--------*/
    DECLARE    = 'declare'                  ;
    FOR        = 'for'                      ;
    FROM       = 'from'                     ;
    STEP       = 'step'                     ;
    PRINT      = 'print'                    ;
    PRINTLN    = 'println'                  ;
    READ       = 'read'                     ;
    IF         = 'if'                       ;    
    ELSE       = 'else'                     ;
    WHILE      = 'while'                    ;
    
    /*-------Operations--------*/
    ATTRIB     = '='                        ;
    PLUS       = '+'                        ;
    MINUS      = '-'                        ;
    TIMES      = '*'                        ;
    OVER       = '/'                        ; 
    REMAINDER  = '%'                        ;
    EQUAL      = '=='                       ;
    NE         = '!='                       ;
    GT         = '>'                        ;
    GE         = '>='                       ;
    LT         = '<'                        ;
    LE         = '<='                       ;
    NOT        = 'not'                      ;
    AND        = 'and'                      ;
    OR         = 'or'                       ;

    
    /*--------General----------*/
    OF         = 'of'                       ;    
    TO         = 'to'                       ;
    UP_        = 'up'                       ;
    DOWN_      = 'down'                     ;
    OPEN_P     = '('                        ;
    CLOSE_P    = ')'                        ;
    OPEN_C     = '{'                        ;
    CLOSE_C    = '}'                        ;
    OPEN_B     = '['                        ;
    CLOSE_B    = ']'                        ;
    COMMA      = ','                        ;
    THE        = 'the'                      ;
    POWER      = 'power'                    ;
    RANDOM     = 'random'                   ;

}

/*---------------- COMPILER INTERNALS ----------------*/

@lexer::members
{
    private int indentLevel = 0;
    private java.util.Queue<Token> tokens = new java.util.LinkedList<Token>();

    @Override
    public void emit(Token t)
    {
        state.token = t;
        tokens.offer(t);
    }

    @Override
    public Token nextToken()
    {
        super.nextToken();
        return tokens.isEmpty() ? Token.EOF_TOKEN : tokens.poll();
    }
}

@header
{
    import java.util.HashMap;
    import java.util.Map;
}

@members
{
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
                parameter = "\%d";
                break;
            case REAL:
                parameter = "\%lf";
                break;
            case CHARACTER:
                parameter = "\%c";
                break;
            case STRING:
                parameter = "\%s";
                break;
            case BOOLEAN:
                System.err.print("\%d");
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
  
    public static void main(String[] args) throws Exception
    {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        TranslatorLexer lexer = new TranslatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TranslatorParser parser = new TranslatorParser(tokens);
        
        parser.program();

        checkHeader();
        checkFooter();
        checkForHints();
        
        printHeader(); 
        printOutput();
        printFooter();
        
        printHints();

    }
}

/*---------------- LEXER RULES ----------------*/

SPACE : ' ' { skip(); } ;
    
TAB : '\t' 
    { 
        System.err.println("line " + getLine() + ": TAB is not allowed");
        System.exit(1);
    } ;

SCOPE : {getCharPositionInLine()==0}?=> (' ')+ 
    {
        if (input.LA(1) == '\n') {
            // empty line: do nothing
            skip();
            //emit(new CommonToken(INDENT, "EMPTY"));
        }
        else if (getText().length() > indentLevel) {
            // indent one level: must be 4 spaces wide
            if (getText().length() - indentLevel == 4) {
                emit(new CommonToken(INDENT, "INDENT"));
                indentLevel = getText().length();
            }
            else {
                System.err.println("line " + getLine() + ": indentation must be 4 spaces from previous level");
                System.exit(1);
            }
        }
        else if (getText().length() < indentLevel) {
            // dedent one or more levels: must be multiple of 4 spaces
            int d = indentLevel - getText().length();
            if ((d \% 4) == 0) {
                emit(new CommonToken(NEWLINE, "NL")); 
                for (int i = 1; i <= d/4; i++) {
                    emit(new CommonToken(DEDENT, "DEDENT"));
                }
                indentLevel = getText().length();
            }
            else {
                System.err.println("line " + getLine() + ": dedentation must be multiple of 4 spaces");
                System.exit(1);
            }
        }
        else {
            // same indentation level: do nothing
            skip();
            //emit(new CommonToken(INDENT, "SAME"));
        }
    } ;

NL : ('\r')? '\n'
    {
        emit(new CommonToken(NEWLINE, "NL")); 
        if (indentLevel > 0 &&
            (input.LA(1) == -1 ||
             (input.LA(1) != ' ' && input.LA(1) != '/' && input.LA(1) != '\n'))) {
            // dedent until it reaches first level (column 0)
            for (int i = 1; i <= indentLevel / 4; i++) {
                emit(new CommonToken(DEDENT, "DEDENT"));
            }
            indentLevel = 0;
        }
    } ;


NUMBER   : '0'..'9'+('.' '0'..'9'+)? ;
VARIABLE : ('a'..'z'|'A'..'Z')+ ;  
COMMENT  : '//' ~('\r'|'\n')* { skip(); } ;
STRING   : '"' ~('"')* '"' ;


/*---------------- PARSER RULES ----------------*/

program
    :   
        ( statement )*
    ;

statement
    :   
        declare          | 
        attribution      | 
        read             | 
        print            |
        while_           | 
        if_              | 
        for_             |
        nl_              
    ;
    
declare
    :   
    {insertIndentation();}
    DECLARE 
    
    tp = type
    
    VARIABLE 
    
    {
        insertDeclaration($VARIABLE.text, $tp.type);
    }

    NEWLINE
    {
        //Declaration should not print new line in Python language
        //since it does not print any declaration
        if(currentLanguage != Language.PYTHON) {
            newLine();
        }
    }
    ;

attribution
    :   
    {insertIndentation();}
    VARIABLE 
        
    ATTRIB
    {
        insertAttribution ($VARIABLE.text); 
    }

    ex = expression
    {        
        insertExpressionIntoMainBuffer();
        insertSemiColon();
    }
    
    NEWLINE
    {newLine();}
    ;
    
read
    :
    {insertIndentation();}
    READ VARIABLE
    {    
        insertRead($VARIABLE.text);
    }
    NEWLINE
    {newLine();}
    ;
       
print
    :   
    {insertIndentation();}
    printType = ( PRINT | PRINTLN )  

    ex = expression 
    {
        insertExpressionIntoPrintBuffer ($ex.type);
    }
    
    (
        COMMA ex2 = expression 
        {
            insertExpressionIntoPrintBuffer ($ex2.type);
        }
    )*
    
    {
        insertPrint($printType.text);       
    }
    
    NEWLINE
    {newLine();}
    ;
    
if_
    :    
    {insertIndentation();}
    IF 
    {beginIf();} 
    
    expression 
    {
        insertExpressionIntoMainBuffer();
        middleIf();
    } 
    
    NEWLINE
    
    INDENT
    {
        incIfLevel();
        incIndentation();
        if(currentLanguage != Language.PORTUGUESE && 
           currentLanguage != Language.PYTHON) {
            newLine();
        }
    }
    (statement)* 
    {
        decIfLevel();
        decIndentation();
        endIf();
    }
    DEDENT

    (
    ELSE 
    { 
        beginElse();
        if(currentLanguage != Language.PORTUGUESE && 
           currentLanguage != Language.PYTHON) {
            newLine();
        }
    } 
    NEWLINE
    
    INDENT 
    {
        incIndentation();
    }
    
    (statement)* 
    
    {
        decIndentation();
        insertIndentation();
        endElse();
    }
    
    DEDENT
    )?
    ;
    
while_
    :    
    {insertIndentation();}
    WHILE 
    {
        beginWhile();
    }
          
    expression 
    {
        insertExpressionIntoMainBuffer();
        middleWhile();
    } 
    
    NEWLINE
    
    INDENT 
    {
        incWhileLevel();
        incIndentation();
        if(currentLanguage != Language.PORTUGUESE && 
           currentLanguage != Language.PYTHON) {
            newLine();
        }
    } 
    (statement)*  
    {
        decWhileLevel();
        decIndentation();
        endWhile();
    }
    DEDENT 
    ;
    
for_
    :    
    {insertIndentation();}
    FOR VARIABLE FROM ex1 = expression 
    {
        beginFor($VARIABLE.text);
    }
    op = ( UP_ | DOWN_ ) TO ex2 = expression 
    {
        middleFor($VARIABLE.text, $op.text);
    }
    STEP ex3 = expression 
    {
        endFor($VARIABLE.text, $op.text);
    }
    
    NEWLINE

    INDENT 
    {
        incForLevel();
        if(currentLanguage != Language.PORTUGUESE && 
           currentLanguage != Language.PYTHON) {
            newLine();
        }
        insertIndentation();
        openBrace();
        incIndentation();
        newLine();
    } 
    (statement)*  
    {
        decForLevel();
        decIndentation();
        insertIndentation();    
        closeBrace();
        if(currentLanguage != Language.PORTUGUESE && 
           currentLanguage != Language.PYTHON) {
            newLine();
        }
    }
    DEDENT 
    ;
    
nl_ 
    :
    NEWLINE
    {newLine();}
    ;

expression returns [Type type]
    :
        un1 = unary_not
        {$type = $un1.type;}
        (
            ( op = ( AND | OR ) 
                {             
                    String val = null;
                
                    if ($op.type == AND)      {val = currentLanguage == Language.PYTHON ? "and" : "&&"; andExists = true;}
                    if ($op.type == OR)       {val = currentLanguage == Language.PYTHON ? "or"  : "||"; orExists = true;} 
                    
                    insertIntoExpressionBuffer(val);
                } 
             un2 = unary_not         
            )        
        )*
    ;
    
unary_not returns [Type type]
    :
        (NOT
            {insertIntoExpressionBuffer("!");}
        )? 
        
        e1 = exp_comparison
        {$type = $e1.type;}
    ;
    
exp_comparison returns [Type type]
    :    
     e1 = exp_arithmetic  
     {$type = $e1.type;}
    
    ( op = ( EQUAL | NE | GT | GE | LT | LE ) 
        {             
            String val = null;
        
            if ($op.type == EQUAL)      {val = " == "; equalExists = true;}
            if ($op.type == NE)         {val = " != "; notEqualExists = true;} 
            if ($op.type == GT)         {val = " > " ; greaterExists = true;}
            if ($op.type == GE)         {val = " >= "; greaterEqExists = true;}
            if ($op.type == LT)         {val = " < " ; lessExists = true;}
            if ($op.type == LE)         {val = " <= "; lessEqExists = true;}

            insertIntoExpressionBuffer(val);
        } 
     e2 = exp_arithmetic         
    )?
    ;
    
exp_arithmetic returns [Type type]
    :   t1 = term 
        {$type = $t1.type;}
        ( op = ( PLUS | MINUS ) 
            { 
                if ($op.type == PLUS)  { insertIntoExpressionBuffer("+"); }
                if ($op.type == MINUS) { insertIntoExpressionBuffer("-");}
            }  
        t2 = term
        )*        
    ;
    
term returns [Type type]   
    :   
    u1 = unary
    {$type = $u1.type;}
    ( op = ( TIMES | OVER | REMAINDER ) 
        { 
            if ($op.type == TIMES)     { insertIntoExpressionBuffer("*");}
            if ($op.type == OVER)      { insertIntoExpressionBuffer("/");}
            if ($op.type == REMAINDER) { insertIntoExpressionBuffer("\%"); percentExists = true; }
        }
    u2 = unary 
    )*    
    ;
    
unary returns [Type type]
    :
        (PLUS
        {
            insertIntoExpressionBuffer("+");
        }
        )?
        (MINUS
        {
            insertIntoExpressionBuffer("-");
        }
        )?         
        f1 = factor
        {$type = $f1.type;}
    ;
    
factor returns [Type type]
    :   
        NUMBER 
        { 
            //Inserts code and returns integer or real
            insertIntoExpressionBuffer($NUMBER.text);
            $type = $NUMBER.text.contains(".") ? Type.REAL : Type.INTEGER;
        }
    
    |   VARIABLE        
        {
            //Inserts code and returns variable type
            //Prints error if variable does not exist
            insertIntoExpressionBuffer($VARIABLE.text);

            $type = symbolTable.get($VARIABLE.text);

            if($type == null) {
                System.err.print ("Error: Variable '"+$VARIABLE.text+"' does not exist");
            }
        }

    |   STRING 
        {
            //Inserts code and returns string
            insertIntoExpressionBuffer($STRING.text);
            $type = Type.STRING; 
        }
        
    |   TRUE
        {
            insertTrue();
            $type = Type.BOOLEAN; 
        }
    
    |   FALSE
        {
            insertFalse();
            $type = Type.BOOLEAN; 
        }
    
    |   PI
        {
            insertPi();
            $type = Type.REAL; 
        }
        
    |   POWER OPEN_P 
        {     
            beginPower();
        }
        
        expression 
      
        COMMA 
        {
            middlePower();
        }
      
        expression 
      
        CLOSE_P
      
        {
          endPower();
          $type = Type.REAL;
        }
    
    | RANDOM OPEN_P 
        {
            beginRandom();
        }
        
        ex = expression 
        
        COMMA 
        {
            middleRandom();
        }
        
        expression 
        
        CLOSE_P
        {
            endRandom($ex.text);
        }
    
    |   OPEN_P 
        {
            insertIntoExpressionBuffer("(");
        }
        ex = expression 
        
        CLOSE_P
        {
            insertIntoExpressionBuffer(")");
            $type = $ex.type;            
        }
    ;
    
type returns [Type type]
    :
    INTEGER    { $type = Type.INTEGER;   }    |
    REAL       { $type = Type.REAL;      }    |
    CHARACTER  { $type = Type.CHARACTER; }    |
    BOOLEAN    { $type = Type.BOOLEAN;   }    |
    STRING_    { $type = Type.STRING;    }       
    ;

