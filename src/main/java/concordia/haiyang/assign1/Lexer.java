package concordia.haiyang.assign1;

public class Lexer {
	public static enum Token{
		ID, INT, FLOAT
	}
	Specification sp;
	private boolean roundBracketOpen=false, squareBraketOpen=false, curlyBraketOpen=false;
	public Lexer(Specification sp){
		this.sp=sp;
	}
	
	public Token getToken(String s){
		return Token.ID;
	}

}
