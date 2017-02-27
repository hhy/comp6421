package concordia.haiyang.assign1;

import java.io.IOException;

import concordia.haiyang.assign1.LexerImpl.LineError;

interface Lexer {
	
	Token nextToken() throws IOException, LineError;
}
