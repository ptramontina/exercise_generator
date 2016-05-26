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
    READ_INT   = 'read_int'                 ;
    READ_STR   = 'read_str'                 ;
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
    GT         = '>'                        ;
    GE         = '>='                       ;
    LT         = '<'                        ;
    LE         = '<='                       ;
    NOT        = '!'                        ;
    AND        = '&'                        ;
    OR         = '|'                        ;
    NE         = '!='                       ;
    
    /*--------General----------*/
    OF         = 'of'                       ;    
    TO         = 'to'                       ;
    OPEN_P     = '('                        ;
    CLOSE_P    = ')'                        ;
    OPEN_C     = '{'                        ;
    CLOSE_C    = '}'                        ;
    OPEN_B     = '['                        ;
    CLOSE_B    = ']'                        ;
    BEGIN      = 'begin'                    ;
    END        = 'end'                      ;
    
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
    //import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;
}

@members
{
    //Constants
    public static final Character INTEGER_TYPE      = 'i';
    public static final Character REAL_TYPE      	= 'r';
    public static final Character CHARACTER_TYPE 	= 'c';
    public static final Character BOOLEAN_TYPE      = 'b';
    public static final Character STRING_TYPE     	= 's';
    
    //Variables
    private static boolean readExist = false;
    private static int currentIndentation = 1;
    private static ArrayList<String> outputString = new ArrayList<String>();
    //private static ArrayList<String> symbol_table;
    private static Map<String, Character> symbolTable = new HashMap();

    private static void insertCode(String code) {
        outputString.add(code);// =  outputString + code;
    }
    
    private static void insertIndentation () {
        String indentation = "";
        for(int i=0;i<currentIndentation; i++) {
            indentation = indentation + "    ";
        }
        insertCode(indentation);
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
    
        if(readExist) {
            System.out.println("import java.util.Scanner;");
        }
    
        System.out.println("public class Teste {\n    public static void main (String [] args) {\n");
    
        for (String output : outputString) {
            System.out.print(output);
        }
        
        System.out.println("\n    }\n}");
    }
//---------------------------------NEW FUNCTIONS------------------------------    
    private static void openParenthesis () {
        
    }
    
    private static void closeParenthesis () {
        
    }

    private static void beginIf () {
        
    }
    
    private static void endIf () {
        
    }

//---------------------------------NEW FUNCTIONS------------------------------    
    public static void main(String[] args) throws Exception
    {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        TranslatorLexer lexer = new TranslatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TranslatorParser parser = new TranslatorParser(tokens);
        
        List<Token> list = tokens.getTokens();
        for (Token t : list) {
            System.out.println(t.getText());
        }
        
        parser.program();

        for (Map.Entry<String, Character> entry : symbolTable.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        
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
    :   ( statement  )*
    ;

statement
    :   
    {incIndentation();insertIndentation();}    declare          {insertCode(";");}{newLine();}      | 
    {incIndentation();insertIndentation();}    attribuition     {insertCode(";");} {newLine();}     | 
    {incIndentation();insertIndentation();}    read             {newLine();}                        | 
    {incIndentation();insertIndentation();}    print            {newLine();}                        |
    {incIndentation();insertIndentation();}    while_           {newLine();}                        | 
    {incIndentation();insertIndentation();}    if_              {newLine();}                        | 
    {incIndentation();insertIndentation();}    for_             {newLine();}                        |
                                               nl_              {newLine();}
    ;
    
declare
    :   
    {decIndentation();}
    DECLARE 
    
    tp = type
    
    VARIABLE {insertCode($VARIABLE.text);} 
    
    {
        //Insert variable in 'symbolTable'
        if(symbolTable.put($VARIABLE.text, $tp.type) != null ) {
            System.err.println("line " + $VARIABLE.line + ": Variable '"+ $VARIABLE.text +"' already exists!");
            System.exit(1);
        }
    }
    
    NEWLINE
    ;

attribuition
    :   
    {decIndentation();}
    VARIABLE {insertCode($VARIABLE.text);}
        
    ATTRIB {insertCode(" = ");}
    (
        STRING {insertCode($STRING.text);} 
        | 
        expression
    )
    NEWLINE
    ;
    
read
    :
    {decIndentation();}
    READ VARIABLE
    {
        insertCode("Scanner scanStr = new Scanner(System.in);\n");
        insertIndentation();
        if(symbolTable.get($VARIABLE.text) == STRING_TYPE) {
            insertCode("    "+$VARIABLE.text+" = scanStr.nextLine();");
        } else if (symbolTable.get($VARIABLE.text) == INTEGER_TYPE) {
            insertCode("    "+$VARIABLE.text+" = scanInt.nextInt();");
        }
        readExist = true;
    }
    NEWLINE
    ;
       
print
    :   
    {decIndentation();}
    PRINT 
    { insertCode("System.out.println("); }
    (
        STRING { insertCode($STRING.text);}
        |
        expression
    )
    { insertCode(");");}
    NEWLINE
    ;
    
if_
    :    
    {decIndentation();}
    IF {insertCode("if (");}
    
    exp_comparison {insertCode(")");} 
    
    NEWLINE
    
    INDENT
    {incIndentation();}
    {insertCode("{");newLine();}(statement)* {insertIndentation();insertCode("}");}
    {decIndentation();}
    DEDENT
    
    (ELSE    { insertCode(" else {");newLine();} NEWLINE
    INDENT 
    {incIndentation();}
        (statement)* {insertIndentation();insertCode("}");} 
    {decIndentation();}
    DEDENT)?
    ;
    
while_
    :    
    {decIndentation();}
    WHILE {insertCode("while (");}
          
    exp_comparison {insertCode(")");} 
    
    NEWLINE
    
    INDENT {incIndentation();}
        {insertCode("{");newLine();} (statement)* {insertIndentation(); insertCode("}");} {decIndentation();}
    DEDENT 
    ;
    
for_
    :    
    {decIndentation();}
    FOR VARIABLE FROM f1 = factor TO f2 = factor STEP f3 = factor NEWLINE
    {
        String operation1 = "", operation2 = "";
        if(Integer.parseInt($f1.factor) > Integer.parseInt($f2.factor)) {
            operation1 = " >= ";
            operation2 = " - ";
        } else {
            operation1 = " <= ";
            operation2 = " + ";            
        }

        insertCode("for (int " + $VARIABLE.text + " = " + $f1.factor + ";");
        insertCode($VARIABLE.text + "" + operation1 + "" + $f2.factor + ";");
        insertCode($VARIABLE.text + " = " + $VARIABLE.text + "" + operation2 + "" + $f3.factor + ")");
    }

    INDENT {incIndentation();}
        {insertCode("{");newLine();} (statement)* {insertIndentation();insertCode("}");} {decIndentation();}
    DEDENT 
    ;
    
nl_ 
    :
    NEWLINE
    ;
    

exp_comparison
    :    
    ( expression | str = STRING {insertCode($str.text);}) 
    
    ( op = ( EQUAL | NE | GT | GE | LT | LE ) 
        {             
            String val = null;
        
            if ($op.type == EQUAL)      {val = " == ";}
            if ($op.type == NE)         {val = " != ";} 
            if ($op.type == GT)         {val = " > " ;}
            if ($op.type == GE)         {val = " >= ";}
            if ($op.type == LT)         {val = " < " ;}
            if ($op.type == LE)         {val = " <= ";}

            insertCode (val);
        } 
    ( expression | str = STRING {insertCode($str.text);})        
    )
    ;
    
expression
    :   term ( op = ( PLUS | MINUS ) 
            { 
                if ($op.type == PLUS)  { insertCode(" + "); }
                if ($op.type == MINUS) { insertCode(" - "); }
            }  
        term
        )*
    ;
    
term    
    :   
    f1 = factor {insertCode($f1.factor);} 
    ( op = ( TIMES | OVER | REMAINDER ) 
        { 
            if ($op.type == TIMES)     { insertCode(" * "); }
            if ($op.type == OVER)      { insertCode(" / "); }
            if ($op.type == REMAINDER) { insertCode(" \% "); }
        }
    f2 = factor {insertCode($f2.factor);}
    )*
    ;
    
factor returns [String factor]
    :   
        NUMBER { $factor = $NUMBER.text; }
    
    |   OPEN_P expression CLOSE_P
    
    |   VARIABLE { $factor = $VARIABLE.text; }
    ;
    
type returns [char type]
    :
    INTEGER    {insertCode("int ");     $type = INTEGER_TYPE;   }    |
    REAL       {insertCode("double ");  $type = REAL_TYPE;      }    |
    CHARACTER  {insertCode("char ");    $type = CHARACTER_TYPE; }    |
    BOOLEAN    {insertCode("boolean "); $type = BOOLEAN_TYPE;   }    |
    STRING_    {insertCode("String ");  $type = STRING_TYPE;    }       
    ;

