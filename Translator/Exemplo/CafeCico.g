grammar Cafe;

/*---------------- TOKEN DEFINITIONS ----------------*/

tokens
{
  PLUS = '+';
  MINUS = '-';
  TIMES = '*';
  OVER = '/';
  REMAINDER = '%';
  OPEN_P = '(';
  CLOSE_P = ')'; 
  PRINT = 'print';
  READ = 'read'; //REMOVER
  READ_INT = 'read_int';
  READ_STR = 'read_str';
  LOOP = 'while';
  IF_COND = 'if';
  ELSE_COND = 'else';
  ATTRIB = '=';
  OPEN_C = '{'; 
  CLOSE_C = '}';
  GT = '>';
  GE = '>=';
  LT = '<';
  LE = '<=';
  EQ = '==';
  NE = '!=';
  OPEN_B = '[';
  CLOSE_B = ']';
  HASH_TAG = '#';  
  ARRAY = 'array';
  VOID = 'void';
  INTEGER_TKN = 'int';
  STRING_TKN = 'str';
  COMMA = ',';
  RETURN_TKN = 'return';
}

/*---------------- COMPILER INTERNALS ----------------*/

@header
{
  import java.util.List;
  import java.util.ArrayList;
  import java.util.Map;
  import java.util.Set;
  import java.util.HashSet;
  import java.util.HashMap;
}

@members
{
	
	public static final String MAIN_CONTEXT = "main";
	public static final Character INTEGER_TYPE = 'i';
	public static final Character STRING_TYPE = 's';
	public static final Character ARRAY_TYPE = 'a';
	public static final Character VOID_TYPE = 'V';
	
	private static String currentContext = MAIN_CONTEXT;
	private static List<String> functions = new ArrayList<String>();
	private static List<String> functionReturnType = new ArrayList<String>();
	public static ArrayList<String> functionParameters = new ArrayList<String>();

  private static List<String> symbol_table;
  private static List<Character> symbol_type;
  private static List<Exception> compilerExceptions;
  private static Map<String, Integer> symbolAccess;
  private static int currentStack = 0;
  private static int maxStack = 0;
  private static int whileCount = 0;
  private static int ifCount = 0;
  private static int identationLevel = 0;
  
  public static void main(String[] args) throws Exception
  {
    ANTLRInputStream input = new ANTLRInputStream(System.in);
    CafeLexer lexer = new CafeLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    CafeParser parser = new CafeParser(tokens);
    
    symbol_table = new ArrayList<String>();  
	symbol_type = new ArrayList<Character>();  
	compilerExceptions = new ArrayList<Exception>();  
    symbolAccess = new HashMap<String, Integer>();
    symbol_table.add("args");   
	symbol_type.add('s');
    
    System.out.println(".source Teste.j");
    System.out.println(".class  public Teste");
    System.out.println(".super  java/lang/Object");
    System.out.println();
    System.out.println(".method public <init>()V");
    System.out.println("\taload_0");
    System.out.println("\tinvokenonvirtual java/lang/Object/<init>()V");
    System.out.println("\treturn");
    System.out.println(".end method");
	System.out.println();

    parser.program();
    
	generateCompileWarningsAndErrors();	
	
  }

private static void cout(Object text) {
	System.err.println(text);
}

  	private static void generateCompileWarningsAndErrors() {
  		boolean isMainMethod = currentContext.equals(MAIN_CONTEXT);
  		System.err.println("------------------------ COMPILER REPORT FOR METHOD: "+currentContext+"() "+"--------------------------------");
  		if(isMainMethod) {
			symbol_table.remove(0); //Retira args
		    symbol_type.remove(0);
	    }

		System.err.println("SYMBOL TABLE: " + symbol_table);
		System.err.println("SYMBOL TYPE: " + symbol_type);

	    for(String variable : symbol_table) {
	      if(!symbolAccess.containsKey(variable)) {
	        System.err.println("WARNING: variable '"+ variable + "' is declared but never used");
	      }
	    }
		
		if(!compilerExceptions.isEmpty()) {
			System.err.println();
			System.err.println();
			System.err.println("------------------------ COMPILER ERROR REPORT --------------------------------");
			System.err.println();
			for(Exception e : compilerExceptions) {
				System.err.println(e.getMessage());
			}
			System.err.println();
			System.err.println("-------------------------------------------------------------------------------");
			System.err.println();
			System.err.println();
			if(isMainMethod) {
				throw new IllegalArgumentException("Compiler found some errors on your code :(");
			}
		}
  	}

	private static void generateEndMethod(boolean generateReturn) {
		if(generateReturn){
	    	generateCode("return", 0);	    
	    }
	    generateCode(".limit locals " + symbol_table.size(), 0);
	    generateCode(".limit stack " + maxStack, 0);	    
	    incrIdent(-1);
	    System.out.println(".end method");
	}

	private static void switchContext(String context, String returnType) {   
		functions.add(context);
		functionReturnType.add(returnType);
		symbol_table.clear();
		symbol_type.clear();
		compilerExceptions.clear();
		symbolAccess.clear();
		currentStack = 0;
		maxStack = 0;
		whileCount = 0;
		ifCount = 0;
		identationLevel = 0;	
		if(context.equals(MAIN_CONTEXT)) {
			symbol_table.add("args");   
			symbol_type.add('s');		
		}
		currentContext = context;
	}

    private static void generateCode(String code, int val) {   
	    generateCode(code, val, true);
    }  
    
    private static void generateCode(String code, int val, boolean newLine) {         
		code = ident(code);
		if(newLine) {
			System.out.println(code);
		} else {
			System.out.print(code);
		}
		incrementStack(val);      
    }
  
  
	private static void incrementStack(int val) {         
		currentStack += val;
		if(currentStack > maxStack) {
	    	maxStack = currentStack;
		}
	}
  
	private static void registerVarAccess(String name) {
		symbolAccess.put(name, symbolAccess.containsKey(name) ? symbolAccess.get(name)+1 : 1);
	}
  
	private static String replicate(String s, int times) {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < times; i++) {
			sb.append(s);
		}
		return sb.toString(); 
	}
  
	private static String ident(String s) {
		return replicate("\t", identationLevel) + s;
	}

	private static void incrIdent(int i) {
		identationLevel += i;
	}

	private static String join(Iterable<String> it) {
		StringBuilder sb = new StringBuilder();
		for(String s : it) {
			sb.append(s);
		}
		return sb.toString();
	}

	private static boolean invokeFunction(String function, String args, int line, int charPosition) {		
		if(functions.contains(function)) {
  			generateCode("invokestatic Teste/"+function+"("+args+")V", 0); 
  			return true;
		} else {
	  		compilerExceptions.add(
				new IllegalStateException("[ERROR] TRYING TO CALL UNDEFINED FUNCTION '"+function+"("+args+")'. POSITION [" + line + ","+charPosition+"]")
			);	
			return false;	
	  	} 
	}
}

/*---------------- LEXER RULES ----------------*/

  NUM     : '0'..'9'+;
  SPACE   : (' '|'\t'|'\r'|'\n')+ { skip(); } ;
  VARIABLE: 'a'..'z'+;
  COMMENT : '//' ~('\r'|'\n')* { skip(); } ;
  STRING  : '"' ~('"')* '"' ;
  
  
  /*---------------- PARSER RULES ----------------*/
  
  program
  :  
  	( function )* 
  	{
  		switchContext("main", "V");
  		generateCode(".method public static main([Ljava/lang/String;)V", 0);  		  		
  		System.out.println();
  		incrIdent(1);
  	}
  	( statement )*
  	{  		
  		System.out.println();
  		generateEndMethod(true);
  	}
  ;
  
  function returns [String returnType]
  :
  {  	
  	$returnType = "V";
  	String args = "";
  	boolean gotReturn = false;
  }
  	( 
  		op = (VOID | INTEGER_TKN | STRING_TKN)
  		{
  			
  			if($op.type == VOID) {
  				$returnType = "V";
  				} else if($op.type == INTEGER_TKN) { 
  					$returnType = "I";
  					} else if($op.type == STRING_TKN) {
  						$returnType = "Ljava/lang/String;";
  					}
  		}
	) VARIABLE 
	{
		switchContext($VARIABLE.text, $returnType);
	} 
  	OPEN_P ( p = parameters {args = $p.type;})? CLOSE_P 
  	{
  		functionParameters.add(args);
  		generateCode(".method public static "+$VARIABLE.text+"("+args+")"+$returnType, 0);  		
  		incrIdent(1);
  	}
  	OPEN_C 
  		( statement )* 
	CLOSE_C
  	{  		
  		generateCompileWarningsAndErrors();  		
		generateEndMethod(!gotReturn);
		System.out.println();		
  	}
  ;

  parameters returns [String type]
  :
  	{$type = "";}
  	p = parameter {$type = $p.type;}  ( COMMA  (pa = parameter { $type += $pa.type; }) )*
  ;

  parameter returns [String type]
  :
  	(
  		typed = (INTEGER_TKN | STRING_TKN)
	)
  	 VARIABLE
  	 {
  	 	symbol_table.add($VARIABLE.text);
  	 	boolean isInt = $typed.type == INTEGER_TKN;
  	 	symbol_type.add(isInt ? INTEGER_TYPE : STRING_TYPE);
  	 	if(isInt) {
  	 		$type = "I";  	 		 
  	 	} else {
  	 		$type = "Ljava/lang/String;";  	 		
  	 	}
  	 }
  ;

  statement
  : print | attribuition | loop | if_cond | call | return_rule
  ;  
  
call returns [char type]
  :
  	{
  		String args = "";
  	}
  	VARIABLE 
  	OPEN_P ( arguments )? CLOSE_P
  	{ 
		if(functions.contains($VARIABLE.text)) {
			String typeStr = functionReturnType.get(functions.indexOf($VARIABLE.text));
			$type = typeStr.equals("I") ? INTEGER_TYPE : typeStr.equals("V") ? VOID_TYPE : STRING_TYPE;
  			generateCode("invokestatic Teste/"+$VARIABLE.text+"("+functionParameters.get(functions.indexOf($VARIABLE.text))+")"+functionReturnType.get(functions.indexOf($VARIABLE.text)), 0); 
		} else {
	  		compilerExceptions.add(
				new IllegalStateException("[ERROR] TRYING TO CALL UNDEFINED FUNCTION '"+$VARIABLE.text+"("+args+")'. POSITION [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]")
			);		
	  	} 
  	}
  ;

arguments
    :
        expression (COMMA expression)*
    ;

return_rule returns [String type]
:
	{ 
		boolean gotExpression = false;
		$type = "V";
	}
	RETURN_TKN 
	( 
		exp = expression
		{
			gotExpression = true;
			$type = $exp.type == INTEGER_TYPE ? "I" : "Ljava/lang/String;";
			generateCode($exp.type == INTEGER_TYPE ? "ireturn" : "areturn", 0);
		}
	)
	{
		if(!gotExpression) {
			generateCode("return", 0);
		}
	}
;

  loop
  : 
       	{				
			whileCount++;
		int local  = whileCount;				
			System.out.println();			
			generateCode("BEGIN_WHILE_"+(local)+":", 0);
		}
			LOOP exp_comparison 
			{
				generateCode(" END_WHILE_"+(local)+" ;", 0);
				System.out.println();
				incrIdent(1);
			}
			OPEN_C (statement)* CLOSE_C	
  	       	{
				incrIdent(-1);
				System.out.println();
				generateCode("goto BEGIN_WHILE_"+(local)+" ;", 0);
				generateCode("END_WHILE_"+(local)+":\n", 0);				
				
			}
  ;
  
  if_cond
  : 
       	{				
			ifCount++;
			int local  = ifCount;										
			boolean elsePresent = false;			
			System.out.println();
		}
		
		IF_COND exp_comparison 
			{				
				generateCode(" NOT_IF_"+(local)+" ;", 0);
				System.out.println();
				incrIdent(1);
			}			
			OPEN_C (statement)* CLOSE_C		
				
			{
				incrIdent(-1);
			}
			
			(ELSE_COND OPEN_C 			
			{
				System.out.println();
				generateCode("goto END_ELSE_"+(local)+" ;", 0);
				System.out.println();
				generateCode("NOT_IF_"+(local)+":", 0);		
				System.out.println();
			}
			(statement)* 
			
			{				
					
					System.out.println();				
					System.out.println();
					generateCode("END_ELSE_"+(local)+":", 0); 
					System.out.println();						
			}
			
			CLOSE_C {elsePresent = true;})? 
			
			{
				
				if(!elsePresent) {					
					System.out.println();
					generateCode("NOT_IF_"+(local)+":", 0);		
					System.out.println();				
				}
			}
			
  ;
     	
  print
  :
  PRINT 	  
  { generateCode("getstatic java/lang/System/out Ljava/io/PrintStream;", 1); }
	  (		  
		  type = expression 
		  { 		  	
		  	if(type == ARRAY_TYPE) {
		  		compilerExceptions.add(
					new IllegalStateException("[ERROR] PRINT OF ARRAY NOT IMPLEMENTED YET. POSITION [" + $PRINT.line+ ","+$PRINT.getCharPositionInLine()+"]")
				);	
		  	} else {		  		
				if(type == INTEGER_TYPE){
					generateCode("invokevirtual  java/io/PrintStream/println(I)V", -2);
				} else {
					generateCode("invokevirtual  java/io/PrintStream/println(Ljava/lang/String;)V", -2);			
				}
			}
		  }
	  )
	{
	  System.out.println();
	  }  
  ;
  
  attribuition
  : 
  {
  	boolean gotError = false;
	boolean isVarArray = false;
	boolean isAttribArray = false;
  }
  VARIABLE 
  (
	{ 
		isVarArray = true;
		if(symbol_table.contains($VARIABLE.text)) {				
			if(symbol_type.get(symbol_table.indexOf($VARIABLE.text)) == ARRAY_TYPE) {
				generateCode("aload "+ symbol_table.indexOf($VARIABLE.text), 1);				
			} else {
				compilerExceptions.add(new IllegalArgumentException("[ERROR] VARIABLE '" + $VARIABLE.text + "'' IS NOT AN ARRAY: variable you trying to access is of type '"+symbol_type.get(symbol_table.indexOf($VARIABLE.text))+"'. Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
				gotError = true;
			}
		} else {
			compilerExceptions.add(
				new IllegalStateException("[ERROR] TRYING TO ACCESS UNDEFINED VARIABLE '"+$VARIABLE.text+"' ON POSITION [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]")
				);			
			gotError = true;
		}

	}
	OPEN_B 
		type = expression 
	CLOSE_B 
	{
		if(type != INTEGER_TYPE) {
			compilerExceptions.add(new IllegalArgumentException("[ERROR] INVALID ARRAY ACCESS:  trying to access array with value of type '"+type+"' (must be an integer). Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
			gotError = true;
		}
	}
  )? 
  ATTRIB 
  (ARRAY 
	{
		isAttribArray = true;		
	}
  )? (
	  type = expression 
	  {
		if(isAttribArray) {
			if(type == STRING_TYPE) {
				compilerExceptions.add(new IllegalArgumentException("[ERROR] INVALID NEW ARRAY DECLARATION:  trying to create array with invalid size of type '"+type+"' (must be an integer). Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
				gotError = true;
			} else {
				generateCode("newarray int", 0);
				type = ARRAY_TYPE;
			}
		} 
		
		String store = !isAttribArray && type == INTEGER_TYPE ? "istore " : "astore ";	
		store = isVarArray ? "iastore" : store;

		if(symbol_table.contains($VARIABLE.text)) {
			Character symbolType = symbol_type.get(symbol_table.indexOf($VARIABLE.text));
			if(symbolType.equals(type) || isVarArray) {
				if(type == STRING_TYPE) {
					compilerExceptions.add(new IllegalArgumentException("[ERROR] TYPE NOT SUPPORTED BY ARRAY:  trying to set an '"+type+"' value on variable '"+$VARIABLE.text+"' of type '"+symbolType+"', currently this is not supported. Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
					gotError = true;
				} else {
					generateCode(store + (isVarArray ? "" : symbol_table.indexOf($VARIABLE.text)), isVarArray ? -3 : -1);
				}
			} else {
				compilerExceptions.add(new IllegalArgumentException("[ERROR] VARIABLE TYPE MISMATCH:  trying to set an '"+type+"' value on variable '"+$VARIABLE.text+"' of type '"+symbolType+"'. Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
				gotError = true;
			}
		} else {
			if(!gotError) {
		  symbol_table.add($VARIABLE.text); 
		  symbol_type.add(type); 
		  generateCode(store + (symbol_table.size()-1), -1);
		  }
		}
		
		System.out.println();		
	  }
	)
  ;
    
  expression returns [char type]
  :   t1 = term ( op = ( PLUS | MINUS ) t2 = term 
              { 			  
				if($t1.type == INTEGER_TYPE && $t2.type == INTEGER_TYPE) {
					generateCode($op.type == PLUS ? "iadd" : "isub", -1);
				} else {
					compilerExceptions.add(new IllegalArgumentException("[ERROR] ARITHMETIC EXPRESSION WITH STRINGS:  "+$t1.type+" "+$op.text+" "+$t2.type + " on Position ["+$op.line+","+$op.getCharPositionInLine()+"]"));
				}
			  }
  )*
  {
  	$type = $t1.type;
  }
    ;
    
  exp_comparison
  :   e1 = expression ( op = ( GT | GE | LT | LE | EQ | NE ) )  e2 = expression 
              { 
			  if($e1.type == INTEGER_TYPE && $e2.type == INTEGER_TYPE) {
				String val = null;

				if($op.type == EQ) val = "if_icmpne";
				if($op.type == NE) val = "if_icmpeq";
				if($op.type == GT) val = "if_icmple"; 
				if($op.type == GE) val = "if_icmplt";
				if($op.type == LT) val = "if_icmpge";
				if($op.type == LE) val = "if_icmpgt";				
				
		        generateCode(val, -2, false); 
				} else {
					compilerExceptions.add(new IllegalArgumentException("[ERROR] LOGICAL EXPRESSION WITH UNSUPORTED TYPES:  "+$e1.type+" "+$op.text+" "+$e2.type + " on Position ["+$op.line+","+$op.getCharPositionInLine()+"]"));
				}
              }
    ;
  
  term returns [char type]   
  :   f1 = factor ( op = ( TIMES | OVER | REMAINDER ) f2 = factor 
                { 
				if($f1.type == INTEGER_TYPE && $f2.type == INTEGER_TYPE) {
				generateCode($op.type == TIMES ? "imul" : $op.type == OVER ? "idiv" : "irem", -1);
				} else {
					compilerExceptions.add(new IllegalArgumentException("[ERROR] ARITHMETIC EXPRESSION WITH UNSUPORTED TYPES:  "+$f1.type+" "+$op.text+" "+$f2.type + " on Position ["+$op.line+","+$op.getCharPositionInLine()+"]"));
				}
				} 
  )*
  {$type = $f1.type;}    
  ;
  
  factor returns [char type]
  :   
    NUM
    { 
		generateCode("ldc " + $NUM.text, 1);
		$type = INTEGER_TYPE;
	}
	|  
	(
		c = call
		{
			$type = $c.type;
		}
	)
	| STRING
    { 
		generateCode("ldc " + $STRING.text, 1);
		$type = STRING_TYPE;
	}
    | OPEN_P ex = expression CLOSE_P
	{	$type = $ex.type; }
    | 
	{ 
		boolean isArray = false;
		boolean isArrayLength = false;
	}
	(
		HASH_TAG 
		{
			isArrayLength = true;
		}
	)?
	VARIABLE 
	(	
		{
			isArray = true;		

			if(symbol_table.contains($VARIABLE.text)) {				
				if(symbol_type.get(symbol_table.indexOf($VARIABLE.text)) == ARRAY_TYPE) {
					generateCode("aload "+ symbol_table.indexOf($VARIABLE.text), 1);				
				} else {
					compilerExceptions.add(new IllegalArgumentException("[ERROR] VARIABLE '" + $VARIABLE.text + "'' IS NOT AN ARRAY: variable you trying to access is of type '"+symbol_type.get(symbol_table.indexOf($VARIABLE.text))+"'. Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
				}
			} else {
				compilerExceptions.add(
					new IllegalStateException("[ERROR] TRYING TO ACCESS UNDEFINED VARIABLE '"+$VARIABLE.text+"' ON POSITION [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]")
					);			
			}
			
		}
		OPEN_B expression CLOSE_B
	)?
    { 


	if(isArrayLength) {
			if(symbol_table.contains($VARIABLE.text)) {				
				if(symbol_type.get(symbol_table.indexOf($VARIABLE.text)) == ARRAY_TYPE) {
					generateCode("aload "+ symbol_table.indexOf($VARIABLE.text), 1);				
				} else {
					compilerExceptions.add(new IllegalArgumentException("[ERROR] VARIABLE '" + $VARIABLE.text + "'' IS NOT AN ARRAY: variable you trying to access is of type '"+symbol_type.get(symbol_table.indexOf($VARIABLE.text))+"'. Position [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
				}
			} else {
				compilerExceptions.add(
					new IllegalStateException("[ERROR] TRYING TO ACCESS UNDEFINED VARIABLE '"+$VARIABLE.text+"' ON POSITION [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]")
					);			
			}		
		generateCode("arraylength", 0);
		$type = INTEGER_TYPE;
	} else {
      if(symbol_table.contains($VARIABLE.text)) {
		int idx = symbol_table.indexOf($VARIABLE.text);
		$type = isArray ? INTEGER_TYPE : symbol_type.get(idx);
		String load = $type == INTEGER_TYPE ? "iload " : "aload ";
		load = isArray ? "iaload" : load;
        generateCode(load + (isArray ? "" : idx), isArray ? 0 : 1);
        registerVarAccess($VARIABLE.text);
      } else {
      	compilerExceptions.add(new IllegalStateException("[ERROR] TRYING TO ACCESS UNDEFINED VARIABLE '"+$VARIABLE.text+"' ON POSITION [" + $VARIABLE.line+ ","+$VARIABLE.getCharPositionInLine()+"]"));
      }
	  }
    }
	| READ_INT
    {
		generateCode("invokestatic Runtime/readInt()I ;", 1);
		$type = INTEGER_TYPE;
	}
	| READ_STR
    {
		generateCode("invokestatic Runtime/readString()Ljava/lang/String;", 1);
		$type = STRING_TYPE;
	}
    ;