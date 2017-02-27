	package concordia.haiyang.assign1;
	
	import java.util.Map;
	
	public enum TokenClass {
		Tclass, //class
		Tprogram, //program
		Tif, //if
		Tthen,
		Telse,
		Tfor,
		Tget,
		Tput,
		Treturn,
		
		Tid, 
		Tnum, // integer and float
		Tnot,
		Tint,
		Tfloat,
		TassignOp,
		TrelOp,
		TaddOp,
		TmultiOp,
		
		PUNC, //punctuation, ; , .  
		KW, //keyword and not, or , if, then, else, for, class, int, float, get, put, return, program
		SP, EP, SB, EB, SS, ES; //start/end parentheses, brace, square brace   
		static Map<TokenClass, String> regex;
		static{
			regex.put(Tclass, "class");
			regex.put(Tprogram, "program");
			regex.put(Tif,"if");
			regex.put(Tthen,"then");
			regex.put(Telse,"else");
			regex.put(Tfor,"for");
			regex.put(Tget,"get");
			regex.put(Tput, "put");
			regex.put(Treturn,"return");
			
			regex.put(Tid,"\\w"); 
			regex.put(Tnum, "\\d[.\\d]");
			regex.put(Tnot, "not");
			regex.put(Tint,"int");
			regex.put(Tfloat,"float");
			regex.put(TassignOp,"=");
			regex.put(TrelOp,"||||");
			regex.put(TaddOp,"||||");
			regex.put(TmultiOp,"");
			
			regex.put(PUNC, "");//punctuation, ; , .  
			regex.put(KW, ""); //keyword and not, or , if, then, else, for, class, int, float, get, put, return, program
			regex.put(SP, "(");
			regex.put(EP, "");
			regex.put(SB,"");regex.put(EB, "");
			regex.put(SS, "");
			regex.put(ES,""); //start/end parentheses, brace, square brace    
		}
	
	}
