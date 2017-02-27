package concordia.haiyang.assign1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

public class LexerImpl implements Lexer {
	
	
	private BufferedReader r;
	private String line;
	private int lineNo;
	private int pos;
	public LexerImpl(Reader r){
		this.r=new BufferedReader(r);
	}
	
	public Token nextToken(){
		if(pos==line.length()-1 || line==null){
			this.lineNo++;
			pos=0;
			line=r.readLine();
		}
		if(line==null)
		return null;
		
		int l=line.length();
		for(;l>pos;l--){
			String s=line.substring(pos, l);
			for(TokenClass tc: TokenClass.regex.keySet()){
				String re = TokenClass.regex.get(tc);
				if(re.matches(s)){
					return new Token(tc, s);
				};
			}
		}
		throw new LineError(line, lineNo);
		
	}
	
	public static class LineError extends Exception{
		public LineError(String line, int lineNo){
			
		}
	}

}
