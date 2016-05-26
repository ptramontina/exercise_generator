// $ANTLR 3.2 Sep 23, 2009 12:02:23 Translator.g 2015-11-19 00:46:43

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TranslatorLexer extends Lexer {
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


    // delegates
    // delegators

    public TranslatorLexer() {;} 
    public TranslatorLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TranslatorLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Translator.g"; }

    // $ANTLR start "INDENT"
    public final void mINDENT() throws RecognitionException {
        try {
            int _type = INDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:22:8: ( 'INDENT' )
            // Translator.g:22:10: 'INDENT'
            {
            match("INDENT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INDENT"

    // $ANTLR start "DEDENT"
    public final void mDEDENT() throws RecognitionException {
        try {
            int _type = DEDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:23:8: ( 'DEDENT' )
            // Translator.g:23:10: 'DEDENT'
            {
            match("DEDENT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEDENT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:24:9: ( 'NL' )
            // Translator.g:24:11: 'NL'
            {
            match("NL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:25:6: ( 'true' )
            // Translator.g:25:8: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:26:7: ( 'false' )
            // Translator.g:26:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "PI"
    public final void mPI() throws RecognitionException {
        try {
            int _type = PI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:27:4: ( 'pi' )
            // Translator.g:27:6: 'pi'
            {
            match("pi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PI"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:28:9: ( 'boolean' )
            // Translator.g:28:11: 'boolean'
            {
            match("boolean"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "CHARACTER"
    public final void mCHARACTER() throws RecognitionException {
        try {
            int _type = CHARACTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:29:11: ( 'character' )
            // Translator.g:29:13: 'character'
            {
            match("character"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHARACTER"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:30:9: ( 'integer' )
            // Translator.g:30:11: 'integer'
            {
            match("integer"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:31:6: ( 'real' )
            // Translator.g:31:8: 'real'
            {
            match("real"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "STRING_"
    public final void mSTRING_() throws RecognitionException {
        try {
            int _type = STRING_;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:32:9: ( 'string' )
            // Translator.g:32:11: 'string'
            {
            match("string"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_"

    // $ANTLR start "ARRAY"
    public final void mARRAY() throws RecognitionException {
        try {
            int _type = ARRAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:33:7: ( 'array' )
            // Translator.g:33:9: 'array'
            {
            match("array"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARRAY"

    // $ANTLR start "MATRIX"
    public final void mMATRIX() throws RecognitionException {
        try {
            int _type = MATRIX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:34:8: ( 'matrix' )
            // Translator.g:34:10: 'matrix'
            {
            match("matrix"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MATRIX"

    // $ANTLR start "DECLARE"
    public final void mDECLARE() throws RecognitionException {
        try {
            int _type = DECLARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:35:9: ( 'declare' )
            // Translator.g:35:11: 'declare'
            {
            match("declare"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECLARE"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:36:5: ( 'for' )
            // Translator.g:36:7: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:37:6: ( 'from' )
            // Translator.g:37:8: 'from'
            {
            match("from"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "STEP"
    public final void mSTEP() throws RecognitionException {
        try {
            int _type = STEP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:38:6: ( 'step' )
            // Translator.g:38:8: 'step'
            {
            match("step"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STEP"

    // $ANTLR start "PRINT"
    public final void mPRINT() throws RecognitionException {
        try {
            int _type = PRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:39:7: ( 'print' )
            // Translator.g:39:9: 'print'
            {
            match("print"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRINT"

    // $ANTLR start "PRINTLN"
    public final void mPRINTLN() throws RecognitionException {
        try {
            int _type = PRINTLN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:40:9: ( 'println' )
            // Translator.g:40:11: 'println'
            {
            match("println"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRINTLN"

    // $ANTLR start "READ"
    public final void mREAD() throws RecognitionException {
        try {
            int _type = READ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:41:6: ( 'read' )
            // Translator.g:41:8: 'read'
            {
            match("read"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:42:4: ( 'if' )
            // Translator.g:42:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:43:6: ( 'else' )
            // Translator.g:43:8: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:44:7: ( 'while' )
            // Translator.g:44:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "ATTRIB"
    public final void mATTRIB() throws RecognitionException {
        try {
            int _type = ATTRIB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:45:8: ( '=' )
            // Translator.g:45:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ATTRIB"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:46:6: ( '+' )
            // Translator.g:46:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:47:7: ( '-' )
            // Translator.g:47:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:48:7: ( '*' )
            // Translator.g:48:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "OVER"
    public final void mOVER() throws RecognitionException {
        try {
            int _type = OVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:49:6: ( '/' )
            // Translator.g:49:8: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OVER"

    // $ANTLR start "REMAINDER"
    public final void mREMAINDER() throws RecognitionException {
        try {
            int _type = REMAINDER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:50:11: ( '%' )
            // Translator.g:50:13: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REMAINDER"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:51:7: ( '==' )
            // Translator.g:51:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:52:4: ( '!=' )
            // Translator.g:52:6: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:53:4: ( '>' )
            // Translator.g:53:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:54:4: ( '>=' )
            // Translator.g:54:6: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:55:4: ( '<' )
            // Translator.g:55:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:56:4: ( '<=' )
            // Translator.g:56:6: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:57:5: ( 'not' )
            // Translator.g:57:7: 'not'
            {
            match("not"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:58:5: ( 'and' )
            // Translator.g:58:7: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:59:4: ( 'or' )
            // Translator.g:59:6: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "OF"
    public final void mOF() throws RecognitionException {
        try {
            int _type = OF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:60:4: ( 'of' )
            // Translator.g:60:6: 'of'
            {
            match("of"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OF"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:61:4: ( 'to' )
            // Translator.g:61:6: 'to'
            {
            match("to"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "UP_"
    public final void mUP_() throws RecognitionException {
        try {
            int _type = UP_;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:62:5: ( 'up' )
            // Translator.g:62:7: 'up'
            {
            match("up"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UP_"

    // $ANTLR start "DOWN_"
    public final void mDOWN_() throws RecognitionException {
        try {
            int _type = DOWN_;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:63:7: ( 'down' )
            // Translator.g:63:9: 'down'
            {
            match("down"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOWN_"

    // $ANTLR start "OPEN_P"
    public final void mOPEN_P() throws RecognitionException {
        try {
            int _type = OPEN_P;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:64:8: ( '(' )
            // Translator.g:64:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_P"

    // $ANTLR start "CLOSE_P"
    public final void mCLOSE_P() throws RecognitionException {
        try {
            int _type = CLOSE_P;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:65:9: ( ')' )
            // Translator.g:65:11: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_P"

    // $ANTLR start "OPEN_C"
    public final void mOPEN_C() throws RecognitionException {
        try {
            int _type = OPEN_C;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:66:8: ( '{' )
            // Translator.g:66:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_C"

    // $ANTLR start "CLOSE_C"
    public final void mCLOSE_C() throws RecognitionException {
        try {
            int _type = CLOSE_C;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:67:9: ( '}' )
            // Translator.g:67:11: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_C"

    // $ANTLR start "OPEN_B"
    public final void mOPEN_B() throws RecognitionException {
        try {
            int _type = OPEN_B;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:68:8: ( '[' )
            // Translator.g:68:10: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_B"

    // $ANTLR start "CLOSE_B"
    public final void mCLOSE_B() throws RecognitionException {
        try {
            int _type = CLOSE_B;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:69:9: ( ']' )
            // Translator.g:69:11: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_B"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:70:7: ( ',' )
            // Translator.g:70:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "THE"
    public final void mTHE() throws RecognitionException {
        try {
            int _type = THE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:71:5: ( 'the' )
            // Translator.g:71:7: 'the'
            {
            match("the"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THE"

    // $ANTLR start "POWER"
    public final void mPOWER() throws RecognitionException {
        try {
            int _type = POWER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:72:7: ( 'power' )
            // Translator.g:72:9: 'power'
            {
            match("power"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "POWER"

    // $ANTLR start "RANDOM"
    public final void mRANDOM() throws RecognitionException {
        try {
            int _type = RANDOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:73:8: ( 'random' )
            // Translator.g:73:10: 'random'
            {
            match("random"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANDOM"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            int _type = SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1314:7: ( ' ' )
            // Translator.g:1314:9: ' '
            {
            match(' '); 
             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "TAB"
    public final void mTAB() throws RecognitionException {
        try {
            int _type = TAB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1316:5: ( '\\t' )
            // Translator.g:1316:7: '\\t'
            {
            match('\t'); 
             
                    System.err.println("line " + getLine() + ": TAB is not allowed");
                    System.exit(1);
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TAB"

    // $ANTLR start "SCOPE"
    public final void mSCOPE() throws RecognitionException {
        try {
            int _type = SCOPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1322:7: ({...}? => ( ' ' )+ )
            // Translator.g:1322:9: {...}? => ( ' ' )+
            {
            if ( !((getCharPositionInLine()==0)) ) {
                throw new FailedPredicateException(input, "SCOPE", "getCharPositionInLine()==0");
            }
            // Translator.g:1322:41: ( ' ' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Translator.g:1322:42: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


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
                        if ((d % 4) == 0) {
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
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SCOPE"

    // $ANTLR start "NL"
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1362:4: ( ( '\\r' )? '\\n' )
            // Translator.g:1362:6: ( '\\r' )? '\\n'
            {
            // Translator.g:1362:6: ( '\\r' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // Translator.g:1362:7: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

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
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NL"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1377:10: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
            // Translator.g:1377:12: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
            {
            // Translator.g:1377:12: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Translator.g:1377:12: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // Translator.g:1377:21: ( '.' ( '0' .. '9' )+ )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='.') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // Translator.g:1377:22: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // Translator.g:1377:26: ( '0' .. '9' )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // Translator.g:1377:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1378:10: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
            // Translator.g:1378:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
            {
            // Translator.g:1378:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='A' && LA6_0<='Z')||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Translator.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1379:10: ( '//' (~ ( '\\r' | '\\n' ) )* )
            // Translator.g:1379:12: '//' (~ ( '\\r' | '\\n' ) )*
            {
            match("//"); 

            // Translator.g:1379:17: (~ ( '\\r' | '\\n' ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='\t')||(LA7_0>='\u000B' && LA7_0<='\f')||(LA7_0>='\u000E' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Translator.g:1379:17: ~ ( '\\r' | '\\n' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Translator.g:1380:10: ( '\"' (~ ( '\"' ) )* '\"' )
            // Translator.g:1380:12: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 
            // Translator.g:1380:16: (~ ( '\"' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Translator.g:1380:16: ~ ( '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    public void mTokens() throws RecognitionException {
        // Translator.g:1:8: ( INDENT | DEDENT | NEWLINE | TRUE | FALSE | PI | BOOLEAN | CHARACTER | INTEGER | REAL | STRING_ | ARRAY | MATRIX | DECLARE | FOR | FROM | STEP | PRINT | PRINTLN | READ | IF | ELSE | WHILE | ATTRIB | PLUS | MINUS | TIMES | OVER | REMAINDER | EQUAL | NE | GT | GE | LT | LE | NOT | AND | OR | OF | TO | UP_ | DOWN_ | OPEN_P | CLOSE_P | OPEN_C | CLOSE_C | OPEN_B | CLOSE_B | COMMA | THE | POWER | RANDOM | SPACE | TAB | SCOPE | NL | NUMBER | VARIABLE | COMMENT | STRING )
        int alt9=60;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // Translator.g:1:10: INDENT
                {
                mINDENT(); 

                }
                break;
            case 2 :
                // Translator.g:1:17: DEDENT
                {
                mDEDENT(); 

                }
                break;
            case 3 :
                // Translator.g:1:24: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 4 :
                // Translator.g:1:32: TRUE
                {
                mTRUE(); 

                }
                break;
            case 5 :
                // Translator.g:1:37: FALSE
                {
                mFALSE(); 

                }
                break;
            case 6 :
                // Translator.g:1:43: PI
                {
                mPI(); 

                }
                break;
            case 7 :
                // Translator.g:1:46: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 8 :
                // Translator.g:1:54: CHARACTER
                {
                mCHARACTER(); 

                }
                break;
            case 9 :
                // Translator.g:1:64: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 10 :
                // Translator.g:1:72: REAL
                {
                mREAL(); 

                }
                break;
            case 11 :
                // Translator.g:1:77: STRING_
                {
                mSTRING_(); 

                }
                break;
            case 12 :
                // Translator.g:1:85: ARRAY
                {
                mARRAY(); 

                }
                break;
            case 13 :
                // Translator.g:1:91: MATRIX
                {
                mMATRIX(); 

                }
                break;
            case 14 :
                // Translator.g:1:98: DECLARE
                {
                mDECLARE(); 

                }
                break;
            case 15 :
                // Translator.g:1:106: FOR
                {
                mFOR(); 

                }
                break;
            case 16 :
                // Translator.g:1:110: FROM
                {
                mFROM(); 

                }
                break;
            case 17 :
                // Translator.g:1:115: STEP
                {
                mSTEP(); 

                }
                break;
            case 18 :
                // Translator.g:1:120: PRINT
                {
                mPRINT(); 

                }
                break;
            case 19 :
                // Translator.g:1:126: PRINTLN
                {
                mPRINTLN(); 

                }
                break;
            case 20 :
                // Translator.g:1:134: READ
                {
                mREAD(); 

                }
                break;
            case 21 :
                // Translator.g:1:139: IF
                {
                mIF(); 

                }
                break;
            case 22 :
                // Translator.g:1:142: ELSE
                {
                mELSE(); 

                }
                break;
            case 23 :
                // Translator.g:1:147: WHILE
                {
                mWHILE(); 

                }
                break;
            case 24 :
                // Translator.g:1:153: ATTRIB
                {
                mATTRIB(); 

                }
                break;
            case 25 :
                // Translator.g:1:160: PLUS
                {
                mPLUS(); 

                }
                break;
            case 26 :
                // Translator.g:1:165: MINUS
                {
                mMINUS(); 

                }
                break;
            case 27 :
                // Translator.g:1:171: TIMES
                {
                mTIMES(); 

                }
                break;
            case 28 :
                // Translator.g:1:177: OVER
                {
                mOVER(); 

                }
                break;
            case 29 :
                // Translator.g:1:182: REMAINDER
                {
                mREMAINDER(); 

                }
                break;
            case 30 :
                // Translator.g:1:192: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 31 :
                // Translator.g:1:198: NE
                {
                mNE(); 

                }
                break;
            case 32 :
                // Translator.g:1:201: GT
                {
                mGT(); 

                }
                break;
            case 33 :
                // Translator.g:1:204: GE
                {
                mGE(); 

                }
                break;
            case 34 :
                // Translator.g:1:207: LT
                {
                mLT(); 

                }
                break;
            case 35 :
                // Translator.g:1:210: LE
                {
                mLE(); 

                }
                break;
            case 36 :
                // Translator.g:1:213: NOT
                {
                mNOT(); 

                }
                break;
            case 37 :
                // Translator.g:1:217: AND
                {
                mAND(); 

                }
                break;
            case 38 :
                // Translator.g:1:221: OR
                {
                mOR(); 

                }
                break;
            case 39 :
                // Translator.g:1:224: OF
                {
                mOF(); 

                }
                break;
            case 40 :
                // Translator.g:1:227: TO
                {
                mTO(); 

                }
                break;
            case 41 :
                // Translator.g:1:230: UP_
                {
                mUP_(); 

                }
                break;
            case 42 :
                // Translator.g:1:234: DOWN_
                {
                mDOWN_(); 

                }
                break;
            case 43 :
                // Translator.g:1:240: OPEN_P
                {
                mOPEN_P(); 

                }
                break;
            case 44 :
                // Translator.g:1:247: CLOSE_P
                {
                mCLOSE_P(); 

                }
                break;
            case 45 :
                // Translator.g:1:255: OPEN_C
                {
                mOPEN_C(); 

                }
                break;
            case 46 :
                // Translator.g:1:262: CLOSE_C
                {
                mCLOSE_C(); 

                }
                break;
            case 47 :
                // Translator.g:1:270: OPEN_B
                {
                mOPEN_B(); 

                }
                break;
            case 48 :
                // Translator.g:1:277: CLOSE_B
                {
                mCLOSE_B(); 

                }
                break;
            case 49 :
                // Translator.g:1:285: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 50 :
                // Translator.g:1:291: THE
                {
                mTHE(); 

                }
                break;
            case 51 :
                // Translator.g:1:295: POWER
                {
                mPOWER(); 

                }
                break;
            case 52 :
                // Translator.g:1:301: RANDOM
                {
                mRANDOM(); 

                }
                break;
            case 53 :
                // Translator.g:1:308: SPACE
                {
                mSPACE(); 

                }
                break;
            case 54 :
                // Translator.g:1:314: TAB
                {
                mTAB(); 

                }
                break;
            case 55 :
                // Translator.g:1:318: SCOPE
                {
                mSCOPE(); 

                }
                break;
            case 56 :
                // Translator.g:1:324: NL
                {
                mNL(); 

                }
                break;
            case 57 :
                // Translator.g:1:327: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 58 :
                // Translator.g:1:334: VARIABLE
                {
                mVARIABLE(); 

                }
                break;
            case 59 :
                // Translator.g:1:343: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 60 :
                // Translator.g:1:351: STRING
                {
                mSTRING(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\uffff\20\50\1\105\3\uffff\1\107\2\uffff\1\111\1\113\3\50\7\uffff"+
        "\1\120\5\uffff\2\50\1\124\1\50\1\126\4\50\1\133\5\50\1\141\12\50"+
        "\10\uffff\1\50\1\156\1\157\1\160\2\uffff\2\50\1\uffff\1\50\1\uffff"+
        "\1\165\1\50\1\167\1\50\1\uffff\5\50\1\uffff\5\50\1\u0084\5\50\1"+
        "\u008a\4\uffff\2\50\1\u008d\1\uffff\1\50\1\uffff\1\u008f\5\50\1"+
        "\u0095\1\u0096\2\50\1\u0099\1\50\1\uffff\2\50\1\u009d\1\u009e\1"+
        "\50\1\uffff\2\50\1\uffff\1\u00a2\1\uffff\1\u00a4\1\u00a5\3\50\2"+
        "\uffff\2\50\1\uffff\1\u00ab\2\50\2\uffff\1\u00ae\1\u00af\1\u00b0"+
        "\1\uffff\1\50\2\uffff\3\50\1\u00b5\1\u00b6\1\uffff\1\u00b7\1\50"+
        "\3\uffff\1\u00b9\1\u00ba\1\50\1\u00bc\3\uffff\1\u00bd\2\uffff\1"+
        "\50\2\uffff\1\u00bf\1\uffff";
    static final String DFA9_eofS =
        "\u00c0\uffff";
    static final String DFA9_minS =
        "\1\11\1\116\1\105\1\114\1\150\1\141\1\151\1\157\1\150\1\146\1\141"+
        "\1\164\1\156\1\141\1\145\1\154\1\150\1\75\3\uffff\1\57\2\uffff\2"+
        "\75\1\157\1\146\1\160\7\uffff\1\40\5\uffff\2\104\1\101\1\165\1\101"+
        "\1\145\1\154\1\162\1\157\1\101\1\151\1\167\1\157\1\141\1\164\1\101"+
        "\1\141\1\156\1\145\1\162\1\144\1\164\1\143\1\167\1\163\1\151\10"+
        "\uffff\1\164\3\101\1\0\1\uffff\2\105\1\uffff\1\145\1\uffff\1\101"+
        "\1\163\1\101\1\155\1\uffff\1\156\1\145\1\154\1\162\1\145\1\uffff"+
        "\2\144\1\151\1\160\1\141\1\101\1\162\1\154\1\156\1\145\1\154\1\101"+
        "\4\uffff\2\116\1\101\1\uffff\1\145\1\uffff\1\101\1\164\1\162\1\145"+
        "\1\141\1\147\2\101\1\157\1\156\1\101\1\171\1\uffff\1\151\1\141\2"+
        "\101\1\145\1\uffff\2\124\1\uffff\1\101\1\uffff\2\101\1\141\1\143"+
        "\1\145\2\uffff\1\155\1\147\1\uffff\1\101\1\170\1\162\2\uffff\3\101"+
        "\1\uffff\1\156\2\uffff\1\156\1\164\1\162\2\101\1\uffff\1\101\1\145"+
        "\3\uffff\2\101\1\145\1\101\3\uffff\1\101\2\uffff\1\162\2\uffff\1"+
        "\101\1\uffff";
    static final String DFA9_maxS =
        "\1\175\1\116\1\105\1\114\3\162\1\157\1\150\1\156\1\145\1\164\1"+
        "\162\1\141\1\157\1\154\1\150\1\75\3\uffff\1\57\2\uffff\2\75\1\157"+
        "\1\162\1\160\7\uffff\1\40\5\uffff\2\104\1\172\1\165\1\172\1\145"+
        "\1\154\1\162\1\157\1\172\1\151\1\167\1\157\1\141\1\164\1\172\1\141"+
        "\1\156\2\162\1\144\1\164\1\143\1\167\1\163\1\151\10\uffff\1\164"+
        "\3\172\1\0\1\uffff\2\105\1\uffff\1\145\1\uffff\1\172\1\163\1\172"+
        "\1\155\1\uffff\1\156\1\145\1\154\1\162\1\145\1\uffff\1\154\1\144"+
        "\1\151\1\160\1\141\1\172\1\162\1\154\1\156\1\145\1\154\1\172\4\uffff"+
        "\2\116\1\172\1\uffff\1\145\1\uffff\1\172\1\164\1\162\1\145\1\141"+
        "\1\147\2\172\1\157\1\156\1\172\1\171\1\uffff\1\151\1\141\2\172\1"+
        "\145\1\uffff\2\124\1\uffff\1\172\1\uffff\2\172\1\141\1\143\1\145"+
        "\2\uffff\1\155\1\147\1\uffff\1\172\1\170\1\162\2\uffff\3\172\1\uffff"+
        "\1\156\2\uffff\1\156\1\164\1\162\2\172\1\uffff\1\172\1\145\3\uffff"+
        "\2\172\1\145\1\172\3\uffff\1\172\2\uffff\1\162\2\uffff\1\172\1\uffff";
    static final String DFA9_acceptS =
        "\22\uffff\1\31\1\32\1\33\1\uffff\1\35\1\37\5\uffff\1\53\1\54\1"+
        "\55\1\56\1\57\1\60\1\61\1\uffff\1\66\1\70\1\71\1\72\1\74\32\uffff"+
        "\1\36\1\30\1\73\1\34\1\41\1\40\1\43\1\42\5\uffff\1\67\2\uffff\1"+
        "\3\1\uffff\1\50\4\uffff\1\6\5\uffff\1\25\14\uffff\1\46\1\47\1\51"+
        "\1\65\3\uffff\1\62\1\uffff\1\17\14\uffff\1\45\5\uffff\1\44\2\uffff"+
        "\1\4\1\uffff\1\20\5\uffff\1\12\1\24\2\uffff\1\21\3\uffff\1\52\1"+
        "\26\3\uffff\1\5\1\uffff\1\22\1\63\5\uffff\1\14\2\uffff\1\27\1\1"+
        "\1\2\4\uffff\1\64\1\13\1\15\1\uffff\1\23\1\7\1\uffff\1\11\1\16\1"+
        "\uffff\1\10";
    static final String DFA9_specialS =
        "\44\uffff\1\1\53\uffff\1\0\157\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\45\1\46\2\uffff\1\46\22\uffff\1\44\1\27\1\51\2\uffff\1\26"+
            "\2\uffff\1\35\1\36\1\24\1\22\1\43\1\23\1\uffff\1\25\12\47\2"+
            "\uffff\1\31\1\21\1\30\2\uffff\3\50\1\2\4\50\1\1\4\50\1\3\14"+
            "\50\1\41\1\uffff\1\42\3\uffff\1\14\1\7\1\10\1\16\1\17\1\5\2"+
            "\50\1\11\3\50\1\15\1\32\1\33\1\6\1\50\1\12\1\13\1\4\1\34\1\50"+
            "\1\20\3\50\1\37\1\uffff\1\40",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\57\6\uffff\1\56\2\uffff\1\55",
            "\1\60\15\uffff\1\61\2\uffff\1\62",
            "\1\63\5\uffff\1\65\2\uffff\1\64",
            "\1\66",
            "\1\67",
            "\1\71\7\uffff\1\70",
            "\1\73\3\uffff\1\72",
            "\1\74",
            "\1\76\3\uffff\1\75",
            "\1\77",
            "\1\100\11\uffff\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "",
            "",
            "",
            "\1\106",
            "",
            "",
            "\1\110",
            "\1\112",
            "\1\114",
            "\1\116\13\uffff\1\115",
            "\1\117",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\121",
            "",
            "",
            "",
            "",
            "",
            "\1\122",
            "\1\123",
            "\32\50\6\uffff\32\50",
            "\1\125",
            "\32\50\6\uffff\32\50",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\32\50\6\uffff\32\50",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\32\50\6\uffff\32\50",
            "\1\142",
            "\1\143",
            "\1\145\14\uffff\1\144",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\155",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "\1\uffff",
            "",
            "\1\162",
            "\1\163",
            "",
            "\1\164",
            "",
            "\32\50\6\uffff\32\50",
            "\1\166",
            "\32\50\6\uffff\32\50",
            "\1\170",
            "",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "",
            "\1\177\7\uffff\1\176",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\32\50\6\uffff\32\50",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\32\50\6\uffff\32\50",
            "",
            "",
            "",
            "",
            "\1\u008b",
            "\1\u008c",
            "\32\50\6\uffff\32\50",
            "",
            "\1\u008e",
            "",
            "\32\50\6\uffff\32\50",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "\1\u0097",
            "\1\u0098",
            "\32\50\6\uffff\32\50",
            "\1\u009a",
            "",
            "\1\u009b",
            "\1\u009c",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "\1\u009f",
            "",
            "\1\u00a0",
            "\1\u00a1",
            "",
            "\32\50\6\uffff\32\50",
            "",
            "\32\50\6\uffff\13\50\1\u00a3\16\50",
            "\32\50\6\uffff\32\50",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "",
            "",
            "\1\u00a9",
            "\1\u00aa",
            "",
            "\32\50\6\uffff\32\50",
            "\1\u00ac",
            "\1\u00ad",
            "",
            "",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "",
            "\1\u00b1",
            "",
            "",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "",
            "\32\50\6\uffff\32\50",
            "\1\u00b8",
            "",
            "",
            "",
            "\32\50\6\uffff\32\50",
            "\32\50\6\uffff\32\50",
            "\1\u00bb",
            "\32\50\6\uffff\32\50",
            "",
            "",
            "",
            "\32\50\6\uffff\32\50",
            "",
            "",
            "\1\u00be",
            "",
            "",
            "\32\50\6\uffff\32\50",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( INDENT | DEDENT | NEWLINE | TRUE | FALSE | PI | BOOLEAN | CHARACTER | INTEGER | REAL | STRING_ | ARRAY | MATRIX | DECLARE | FOR | FROM | STEP | PRINT | PRINTLN | READ | IF | ELSE | WHILE | ATTRIB | PLUS | MINUS | TIMES | OVER | REMAINDER | EQUAL | NE | GT | GE | LT | LE | NOT | AND | OR | OF | TO | UP_ | DOWN_ | OPEN_P | CLOSE_P | OPEN_C | CLOSE_C | OPEN_B | CLOSE_B | COMMA | THE | POWER | RANDOM | SPACE | TAB | SCOPE | NL | NUMBER | VARIABLE | COMMENT | STRING );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_80 = input.LA(1);

                         
                        int index9_80 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (!(((getCharPositionInLine()==0)))) ) {s = 113;}

                        else if ( ((getCharPositionInLine()==0)) ) {s = 81;}

                         
                        input.seek(index9_80);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA9_36 = input.LA(1);

                         
                        int index9_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA9_36==' ') && ((getCharPositionInLine()==0))) {s = 81;}

                        else s = 80;

                         
                        input.seek(index9_36);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}