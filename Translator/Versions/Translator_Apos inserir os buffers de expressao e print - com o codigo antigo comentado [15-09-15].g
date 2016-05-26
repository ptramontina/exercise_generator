grammar Translator;

/*---------------- TOKEN DEFINITIONS ----------------*/

tokens
{
    /*-------Indentation------*/
    INDENT     = 'INDENT'                   ;
    DEDENT     = 'DEDENT'                   ;
    NEWLINE    = 'NL'                       ;
	
    
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
    NOT        = '!'                        ;
    AND        = '&&'                       ;
    OR         = '||'                       ;

    
    /*--------General----------*/
    OF         = 'of'                       ;    
    TO         = 'to'                       ;
    DOWNTO     = 'downto'                   ;
    OPEN_P     = '('                        ;
    CLOSE_P    = ')'                        ;
    OPEN_C     = '{'                        ;
    CLOSE_C    = '}'                        ;
    OPEN_B     = '['                        ;
    CLOSE_B    = ']'                        ;
    COMMA      = ','                        ;
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
        C, C_PLUS_PLUS, JAVA, PYTHON;
    }
    
    public enum Type {
        INTEGER, REAL, CHARACTER, BOOLEAN, STRING;   
    }
   
    //Variables
    private static Language currentLanguage = Language.C;
    
    private static boolean readExist = false;
    private static boolean scannerVariableAlreadyInserted = false;
    private static int currentIndentation = 0;
    private static ArrayList<String> outputString = new ArrayList<String>();
    
    //Implementing temporary buffers
    private static ArrayList<String> expressionBuffer = new ArrayList<>();
    private static ArrayList<String> mainBuffer = new ArrayList<>();
    private static ArrayList<String> printBuffer = new ArrayList<>();
    
    private static String printfParamenters = "";
    
    
    
    
    private static Map<String, Type> symbolTable = new HashMap();

    private static void insertCode(String code) {
        //outputString.add(code);
        mainBuffer.add(code);
    }
    
    private static void insertIntoExpressionBuffer ( String code ) {
        expressionBuffer.add(code);
    }
    
    private static void insertExpressionIntoMainBuffer() {
        mainBuffer.addAll(expressionBuffer);
        expressionBuffer.clear();
    }
    
    private static void insertIndentation () {
        String indentation = "";
        for(int i=0;i<currentIndentation; i++) {
            indentation = indentation + "    ";
        }
        //insertCode(indentation);
        insertIntoExpressionBuffer(indentation);
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
    
    private static void printOutput() {
    
        /*if(readExist) {
            System.out.println("import java.util.Scanner;");
        }*/
    
        //System.out.println("public class Teste {\n    public static void main (String [] args) {\n");
    
        /*for (String output : outputString) {
            System.out.print(output);
        }*/
        for (String output : mainBuffer) {
            System.out.print(output);
        }
        //System.out.println("\n    }\n}");
    }
//---------------------------------NEW FUNCTIONS------------------------------    
    private static void checkForImports() {
        
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
        insertCode("if ");        
        openParenthesis();
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
        closeBrace();
        newLine();
    }
    
    /*
     *  This method inserts the first part of the 'else' 
     *  statement, depending on 'currentLanguage' parameter.
     */ 
    private static void beginElse () {        
        insertIndentation();
        insertCode("else");        
        insertColon();
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
        insertIndentation();
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
            case PYTHON:
                insertCode("");
                break;
            default:
            insertCode(";");
        }       
    }
    
    /*
     *   C language requires to know the variable type before print.
     *   This method removes the last element in 'outputString' and
     *   inserts all print in the right order.
     */
    private static void insertPrint (Type type) {
        //Remove the last token in 'outputString' and puts in 'currentVariable'
        String currentVariable = outputString.remove(outputString.size()-1);
        //If the number contains '+' or '-', it is included in 'currentVariable'
        //And the token is removed from 'outputString'
        if(outputString.get(outputString.size()-1).contains("+") || 
           outputString.get(outputString.size()-1).contains("-")) {
            currentVariable = outputString.remove(outputString.size()-1) + currentVariable;
        }
        
        switch (currentLanguage) {
            case JAVA:
                insertCode("System.out.println("+currentVariable+");");
                break;
            case C:
                //Checks if the 'currentVariable' has quotation marks ("), for string purpose
                //Then, checks if 'currentVariable' is a number
                //And finally, 'currentVariable' is a variable
                if(currentVariable.contains("\"")) {
                    insertCode("printf("+currentVariable+");");
                } else if (currentVariable.matches("[-+]?\\d*\\.?\\d+")) {
                    insertCode("printf(\""+cPrintfScanfParameter(type)+"\", "+currentVariable+");");
                } else {
                    insertCode("printf(\""+cPrintfScanfParameter(type)+"\", &"+currentVariable+");");
                }
                break;
            case C_PLUS_PLUS:
                insertCode("cout << "+currentVariable+";");
                break;
            case PYTHON:
                insertCode("print ("+currentVariable+")");
                break;
            default:
                System.err.print ("Internal Error: InsertPrintFunction - Language not found!");
        }        
    }
    
    /*
     *   This method returns the specific parameter for 'printf' and 
     *   'scanf' function, depending on the parameter 'type'.
     *   For instance, it will return '%d' when type is Integer.
     */
    private static String cPrintfScanfParameter (Type type) {
        String parameter = "";

        switch (type) { // CHECK FOR THE ERRORS - COMPILING ERROR, BUT IT WORKS FINE
            case INTEGER:
                parameter = "\%d";
                break;
            case REAL:
                parameter = "\%f";
                break;
            case CHARACTER:
                parameter = "\%c";
                break;
            case STRING:
                parameter = "\%s";
                break;
            case BOOLEAN:
                System.err.print("Error: There's no boolean type in C language!");
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
        insertCode("while ");        
        openParenthesis(); 
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
            System.exit (1);
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
                separator = " + ";
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
    
    private static void insertPrint () {
        switch (currentLanguage) {
            case C:
                mainBuffer.add("printf(");
                mainBuffer.add("\""+ printfParamenters +"\", ");
                printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                mainBuffer.addAll(printBuffer);
                mainBuffer.add(");");
                printfParamenters = "";
                printBuffer.clear();
                break;
                
            case JAVA:
                mainBuffer.add("System.out.println(");
                printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                mainBuffer.addAll(printBuffer);
                mainBuffer.add(");");
                printBuffer.clear();
                break;
                
            case C_PLUS_PLUS:
                mainBuffer.add("cout << ");
                printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                mainBuffer.addAll(printBuffer);
                mainBuffer.add(";");
                printBuffer.clear();
                break;
                
            case PYTHON:
                mainBuffer.add("print (");
                printBuffer.remove(printBuffer.lastIndexOf(languagePrintSeparator()));
                mainBuffer.addAll(printBuffer);
                mainBuffer.add(")");
                printBuffer.clear();
                break;
        }
    }
    
    private static void insertRead (String variable) {
        
        if (!variableExists(variable)) {
            System.err.print("Error: Variable '" +variable+"' does not exist!");
            System.exit(1);
        }
        
        switch (currentLanguage) {
            case JAVA:
                if(!scannerVariableAlreadyInserted) {
                    insertCode("Scanner scanner = new Scanner(System.in);");
                    newLine();
                    insertIndentation();
                    scannerVariableAlreadyInserted = true;
                }
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
        }

    }
    
    private static void insertDeclaration (String variable, Type type) {

        if(symbolTable.put(variable, type) != null ) {
            System.err.println("Error: Variable '"+ variable +"' already exists!");
            System.exit(1);
        }

        if (currentLanguage != Language.PYTHON) {
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
    
    private static void insertInteger () {
        switch (currentLanguage) {
            case PYTHON:            
                break;            
            default:
                insertCode("int ");
                break;
        }
    }

    private static void insertReal () {
        switch (currentLanguage) {
            case PYTHON:            
                break;
            default:
                insertCode("double ");
                break;
        }        
    }    

    private static void insertCharacter () {
        switch (currentLanguage) {
            case PYTHON:            
                break;
            default:
                insertCode("char ");
                break;
        }        
    }
    
    private static void insertBoolean () {
        switch (currentLanguage) {
            case PYTHON: 
            case C:
                break;
            default:
                insertCode("boolean ");
                break;
        }        
    }
    
    private static void insertString () {
        switch (currentLanguage) {
            case PYTHON:            
                break;
            case JAVA:
                insertCode("String ");
                break;
            case C:
                insertCode("char* ");
                break;
            case C_PLUS_PLUS:
                insertCode("string ");
                break;
        }        
    }
    

//---------------------------------NEW FUNCTIONS------------------------------    
    public static void main(String[] args) throws Exception
    {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        TranslatorLexer lexer = new TranslatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TranslatorParser parser = new TranslatorParser(tokens);
        
        /*List<Token> list = tokens.getTokens();
        for (Token t : list) {
            System.out.println(t.getText());
        }*/
        
        parser.program();

        /*for (Map.Entry<String, Character> entry : symbolTable.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
        
        printOutput();
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
                for (int i = 1; i <= d/4; i++) {
                    emit(new CommonToken(NEWLINE, "NL")); // CONVERSAR COM MALHEIROS SOBRE ISSO
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
        if (indentLevel > 0 &&
            (input.LA(1) == -1 ||
             (input.LA(1) != ' ' && input.LA(1) != '/' && input.LA(1) != '\n'))) {
            // dedent until it reaches first level (column 0)
            for (int i = 1; i <= indentLevel / 4; i++) {
                emit(new CommonToken(NEWLINE, "NL")); // CONVERSAR COM MALHEIROS SOBRE ISSO
                emit(new CommonToken(DEDENT, "DEDENT"));
            }
            indentLevel = 0;
        }
        else {
            // keep NL token
            emit(new CommonToken(NEWLINE, "NL"));
            //setText("NL");
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
        declare            | 
        attribuition       | 
        read               | 
        print              |
        while_             | 
        if_                | 
        for_               |
        nl_              
    ;
    
declare
    :   
    {insertIndentation();}
    DECLARE 
    
    tp = type
    
    VARIABLE 
    
    {
        //insertCode($VARIABLE.text);
        //Insert variable in 'symbolTable'
        /*if(symbolTable.put($VARIABLE.text, $tp.type) != null ) {
            System.err.println("line " + $VARIABLE.line + ": Variable '"+ $VARIABLE.text +"' already exists!");
            System.exit(1);
        }
        insertSemiColon ();*/
        
        insertDeclaration($VARIABLE.text, $tp.type);
    }

    NEWLINE
    {newLine();}
    ;

attribuition
    :   
    {insertIndentation();}
    VARIABLE 
    {
        //insertCode($VARIABLE.text);
    }
        
    ATTRIB {/*insertCode(" = ");*/}
    {
        insertAttribution ($VARIABLE.text);    
    }
    expression
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
        /*insertCode("Scanner scanStr = new Scanner(System.in);\n");
        insertIndentation();
        //insertIndentation();
        if(symbolTable.get($VARIABLE.text) == Type.STRING) {
            insertCode($VARIABLE.text+" = scanStr.nextLine();");
        } else if (symbolTable.get($VARIABLE.text) == Type.INTEGER) {
            insertCode($VARIABLE.text+" = scanInt.nextInt();");
        }
        readExist = true;*/
        insertRead($VARIABLE.text);
    }
    NEWLINE
    {newLine();}
    ;
       
print
    :   
    {insertIndentation();}
    PRINT    

    ex = expression 
    {
        insertExpressionIntoPrintBuffer ($ex.type);
        //insertPrint($ex.type); 
    }
    
    (
        COMMA ex2 = expression 
        {
            insertExpressionIntoPrintBuffer ($ex2.type);
            //insertPrint($ex2.type);
        }
    )*
    
    {
        insertPrint();       
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
        incIndentation();
        newLine();
    }
    (statement)* 
    {
        decIndentation();
        endIf();
    }
    DEDENT

    (
    ELSE 
    { 
        beginElse();
        newLine();
    } 
    NEWLINE
    
    INDENT {incIndentation();}
        (statement)* 
        {decIndentation();insertIndentation();endElse();} 
    DEDENT
    )?
    ;
    
while_
    :    
    {insertIndentation();}
    WHILE 
    {
        //insertCode("while (");
        beginWhile();
    }
          
    expression 
    {
        insertExpressionIntoMainBuffer();
        //insertCode(")");
        middleWhile();
    } 
    
    NEWLINE
    
    INDENT 
    {incIndentation();}
    {newLine();} 
    (statement)*  
    {decIndentation();endWhile();}
    DEDENT 
    ;
    
for_
    :    
    {insertIndentation();}
    FOR VARIABLE FROM f1 = expression (op = TO | DOWNTO ) f2 = expression STEP f3 = expression NEWLINE
    {
        /*String operation1 = "", operation2 = "";
        if(Integer.parseInt($f1.type) > Integer.parseInt($f2.type)) {
            operation1 = " >= ";
            operation2 = " - ";
        } else {
            operation1 = " <= ";
            operation2 = " + ";            
        }

        insertCode("for (int " + $VARIABLE.text + " = " + $f1.type + ";");
        insertCode($VARIABLE.text + "" + operation1 + "" + $f2.type + ";");
        insertCode($VARIABLE.text + " = " + $VARIABLE.text + "" + operation2 + "" + $f3.type + ")");
    */}

    INDENT {incIndentation();}
        {insertCode("{");newLine();} (statement)* {insertIndentation();insertCode("}");} {decIndentation();}
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
                
                    if ($op.type == AND)      {val = " && ";}
                    if ($op.type == OR)       {val = " || ";} 
                    
                    //insertCode (val);
                    insertIntoExpressionBuffer(val);
                } 
             un2 = unary_not         
            )        
        )*
    ;
    
unary_not returns [Type type]
    :
        (NOT
            {/*insertCode("!");*/insertIntoExpressionBuffer("!");}
        )? 
        
        e1 = exp_comparisson
        {$type = $e1.type;}
    ;
    
exp_comparisson returns [Type type]
    :    
     e1 = exp_arithmetic  
     {$type = $e1.type;}
    
    ( op = ( EQUAL | NE | GT | GE | LT | LE ) 
        {             
            String val = null;
        
            if ($op.type == EQUAL)      {val = " == ";}
            if ($op.type == NE)         {val = " != ";} 
            if ($op.type == GT)         {val = " > " ;}
            if ($op.type == GE)         {val = " >= ";}
            if ($op.type == LT)         {val = " < " ;}
            if ($op.type == LE)         {val = " <= ";}

            //insertCode (val);
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
                if ($op.type == PLUS)  { /*insertCode(" + ");*/ insertIntoExpressionBuffer("+"); }
                if ($op.type == MINUS) { /*insertCode(" - ");*/ insertIntoExpressionBuffer("-");}
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
            if ($op.type == TIMES)     { /*insertCode(" * ");*/ insertIntoExpressionBuffer("*");}
            if ($op.type == OVER)      { /*insertCode(" / ");*/ insertIntoExpressionBuffer("/");}
            if ($op.type == REMAINDER) { /*insertCode(" \% ");*/insertIntoExpressionBuffer("\%");  }
        }
    u2 = unary 
    )*    
    ;
    
unary returns [Type type]
    :
        (PLUS
        {
            //insertCode("+");
            insertIntoExpressionBuffer("+");
        }
        )?
        (MINUS
        {
            //insertCode("-");
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
            //insertCode($NUMBER.text); 
            insertIntoExpressionBuffer($NUMBER.text);
            $type = $NUMBER.text.contains(".") ? Type.REAL : Type.INTEGER;
        }
    
    |   VARIABLE        
        {
            //Inserts code and return variable type
            //Prints error if variable does not exist
            insertIntoExpressionBuffer($VARIABLE.text);
            //insertCode($VARIABLE.text);
            $type = symbolTable.get($VARIABLE.text);

            if($type == null) {
                System.err.print ("Error: Variable '"+$VARIABLE.text+"' does not exist");
            }
        }

    |   STRING 
        {
            //Inserts code and returns string
            //insertCode($STRING.text); 
            insertIntoExpressionBuffer($STRING.text);
            $type = Type.STRING; 
        }
    
    |   OPEN_P expression CLOSE_P

    ;
    
type returns [Type type]
    :
    INTEGER    {/*insertInteger(); */    $type = Type.INTEGER;   }    |
    REAL       {/*insertReal();*/        $type = Type.REAL;      }    |
    CHARACTER  {/*insertCharacter();*/   $type = Type.CHARACTER; }    |
    BOOLEAN    {/*insertBoolean();*/     $type = Type.BOOLEAN;   }    |
    STRING_    {/*insertString();*/      $type = Type.STRING;    }       
    ;

