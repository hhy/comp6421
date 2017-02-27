package concordia.haiyang.assign1;

public class Token {
	public String lexeme;
	public TokenClass tc;
	public Token(TokenClass tc, String lexeme){
		this.tc=tc;
		this.lexeme=lexeme;
	}

}
