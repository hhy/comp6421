package concordia.haiyang.assign1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class LexerImpl implements Lexer {
	
	
	private BufferedReader r;
	private String line;
	private int lineNo;
	private int pos;
	public LexerImpl(Reader r){
		this.r=new BufferedReader(r);
	}
	
	public Token nextToken() throws IOException, LineError{
		if( line ==null || pos==line.length()-1 ){
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
	static Logger log=LogManager.getLogger(LexerImpl.class);
	public void tt(){
		Map<TokenClass, String> m=TokenClass.regex;
		String s=m.get(TokenClass.Tclass);
		boolean ma= s.matches("class");
		s=m.get(TokenClass.Tid);
		ma="a".matches(s);
		log.error("match: "+ma);
		
		
	}
	
	public static class LineError extends Exception{
		public LineError(String line, int lineNo){
			
		}
	}
	
	public static void main(String[] args) throws IOException, LineError{
		InputStream sample = LexerImpl.class.getClassLoader().getResourceAsStream("sample.txt");
		Reader r=new InputStreamReader(sample);
		LexerImpl lexer=new LexerImpl(r);
		lexer.tt();
	//	while lexer.nextToken();
	}
	
	

}
