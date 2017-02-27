	package concordia.haiyang.assign1;
	
	import java.util.HashMap;
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
		TrelOp,
		TaddOp,
		TmultiOp,
		TassignOp,		
		Tpunctuation, //punctuation, ; , .  
		KW, //keyword and not, or , if, then, else, for, class, int, float, get, put, return, program
		SP, EP, SB, EB, SS, ES,  //start/end parentheses, brace, square brace
		Tblank;
		static Map<TC, String> res=new HashMap<>();
		static{
			res.put(Tclass, "class");
			res.put(Tprogram, "program");
			res.put(Tif,"if");
			res.put(Tthen,"then");
			res.put(Telse,"else");
			res.put(Tfor,"for");
			res.put(Tget,"get");
			res.put(Tput, "put");
			res.put(Treturn,"return");
			res.put(Tid,"[a-zA-Z](\\w)*"); 
			res.put(Tnum, "\\d+(\\.\\d+)?");
			res.put(Tnot, "not");
			res.put(Tint,"int");
			res.put(Tfloat,"float");

			res.put(TrelOp,"(==)|(<>)|(<=)|(>=)|[><]");
			res.put(TaddOp,"[\\+-]|(or)");
			res.put(TmultiOp,"[\\*/]|(and)");
			res.put(Tpunctuation, "[,;\\.]");//punctuation, ; , .  
			//res.put(KW, ""); //keyword and not, or , if, then, else, for, class, int, float, get, put, return, program
			res.put(TassignOp,"=");
			res.put(SP, "\\(");
			res.put(EP, "\\)");
			res.put(SB,"\\{");
			res.put(EB, "\\}");
			res.put(SS, "\\[");
			res.put(ES,"\\]"); //start/end parentheses, brace, square brace
			res.put(Tblank,"\\s+"); //start/end parentheses, brace, square brace
		}
	
	}
