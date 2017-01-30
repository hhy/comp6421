package concordia.haiyang.assign1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Specification {
	static final String reId="^[a-zA-Z][a-zA-Z0-9]*$";
	/*
	static final Pattern pId;
	
	static {
		pId=Pattern.compile(reId);
	}
	*/
	public static boolean isId(String s){
		return Pattern.matches(reId, s);
	}
	
	static final String _reInt="((0)|([1-9][0-9]*))";
	static final String reInt="^"+_reInt+"$";
	static final String reFloat="^"+_reInt+"\\.((0)|([0-9]*[1-9]))$";
	
	
	public static boolean isNum(String s){
		return Pattern.matches(reFloat, s)||Pattern.matches(reInt, s);
	}

	
	public static class OpRel{
		static final String eq="==", ne="<>", gt=">", lt="<", ge=">=", le="<=";
	}
	public static Set<String> relOps=new HashSet<>();
	static{
		relOps.addAll(Arrays.asList(OpRel.eq, OpRel.ge, OpRel.gt, OpRel.le, OpRel.lt, OpRel.ne));
	}
	
	
	
	public static class Separator{
		static final String semicolon=";", comma=",", dot=".";
	}
	static Set<String> separators=new HashSet<>();
	static {
		separators.addAll(Arrays.asList(Separator.semicolon, Separator.comma, Separator.dot));
	}
	
	
	public static class OpBin{
		static final String plus="+", minus="-", multiply="*", divide="/", assign="=";
	}
	
	
	static Set<String> binOps=new HashSet<>();
	static{
		binOps.addAll(Arrays.asList(OpBin.plus, OpBin.minus, OpBin.multiply, OpBin.divide));
	}
	
	public static class OpLogic{
		static public final String and="and", not="not", or="or";
	}
	static Set<String> logicOps=new HashSet<>();
	static{
		logicOps.addAll(Arrays.asList(OpLogic.and, OpLogic.not, OpLogic.or));
	}
	
	
	public static class Bracket{
		public static class Round{
			static final String open="(", close=")";
		}
		public static class Squre{
			static final String open="[", close="]";
		}
		public static class curly{
			static final String open="{", close="}";
		}
	}
	static Set<String> brackets=new HashSet<>();
	static{
		brackets.addAll(Arrays.asList(Bracket.curly.open, Bracket.curly.close, Bracket.Round.open, Bracket.Round.close, Bracket.Squre.open, Bracket.Squre.close));
	}
	
	public static class Cmt{
		public static String line="//";
		public static class Block{
			public static String open="/*", close="*/";
		}
	}
	public static class Keyword{
		public static class Ctrl{
			static public final String kIf="if", kThen="then", kElse="else", kFor="for", kReturnt="return";
		}
		public static class Datatype{
			public static String kClass="class", kInt="int", kFloat="float";
		}
		public static class Act{
			public static String get="get", put="put";
		}
	}
	static Set<String> keywords=new HashSet<>();
	static{
		keywords.addAll(Arrays.asList(Keyword.Act.get, Keyword.Act.put));
		keywords.addAll(Arrays.asList(Keyword.Ctrl.kIf, Keyword.Ctrl.kElse, Keyword.Ctrl.kFor, Keyword.Ctrl.kReturnt, Keyword.Ctrl.kThen));
		keywords.addAll(Arrays.asList(Keyword.Datatype.kClass, Keyword.Datatype.kInt, Keyword.Datatype.kFloat));
	}
	
}
