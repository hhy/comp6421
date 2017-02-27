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
	private int lineNo=-1;
	private int pos;
	public LexerImpl(Reader r){
		this.r=new BufferedReader(r);
	}
	
	
	boolean isSwitch(String sa, String sb){
		if(sb.length()==0){
			return true;
		}
		
		if((!sa.matches("\\s")) && sb.matches("\\s")){
			return true;
		}
		if((!sb.matches("\\s")) && sa.matches("\\s")){
			return true;
		}
		String toNonW="[\\+\\-\\*\\/=<>\\{\\}\\(\\)\\[\\];\\.]";
		if( sa.matches(toNonW) || sb.matches(toNonW) ){
			return true;
		}
		return false;
	}
	
	
	
	
	public Token nextToken() throws IOException, LineError{
		if( line ==null || pos>=line.length() ){
			this.lineNo++;
			this.pos=0;
			this.line=r.readLine();
		}
		if(line==null){
			log("no token anymore");
			return null;
		}
		
		int l=line.length()-1;
		String sNow="", sPre="";
		for(;l>=pos;l--){
			sPre=sNow;
			sNow=line.charAt(l)+"";
			if(!this.isSwitch(sNow, sPre)){
				continue;
			}

			String s=line.substring(pos, l+1);
			for(TC tc: TC.res.keySet()){
				if(tc==TC.Tblank){
					//log("bbbb");
				}
				String re = TC.res.get(tc);
				if(s.matches(re)){
					pos+=s.length();
					return new Token(tc, s);
				};
			}
		}
		
		//if(pos=l-1)
			throw new LineError(line, lineNo, pos);
		
		
	}
	
	static void log(String msg){
		System.out.println(msg);
	}
	
	public void tt(){
		Map<TC, String> m=TC.res;
		String s=m.get(TC.Tclass);
		boolean ma= s.matches("class");
		s=m.get(TC.Tid);
		ma="/*".matches("[\\*\\/]");
		log("match: "+ma);
	}
	
	
	
	public static class LineError extends Exception{
		String line;
		int lNo;
		int pos;
		public LineError(String line, int lineNo, int pos){
			this.line=line;
			this.lNo=lineNo;
			this.pos=pos;
		}
		@Override
		public String toString(){
			return String.format("[Error # %d ]: %s, left: [%s]", this.lNo, this.line, this.line.substring(pos));
		}
	}
	
	public static void main(String[] args) throws IOException, LineError{
		InputStream sample = LexerImpl.class.getClassLoader().getResourceAsStream("sample.txt");
		Reader r=new InputStreamReader(sample);
		LexerImpl lexer=new LexerImpl(r);
		
		//log("-----------"+"100".matches("\\d(\\.\\d+)?"));
		
		try{
			int l=0;
			for(Token tk=lexer.nextToken(); tk!=null; tk=lexer.nextToken()){
				if(l!=lexer.lineNo){
					System.out.println();
					l=lexer.lineNo;
				}
				
				System.out.print(String.format("%s[%s]", tk.lexeme, tk.tc));
				
			}
		}catch(LineError e){
			log("");
			log(e.toString());
		}
		
	//	while lexer.nextToken();
	}
	
	

}
