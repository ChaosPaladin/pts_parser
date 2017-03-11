package data.lexer;

import java.io.IOException;
import java.util.Arrays;

public class Scanner {
	private boolean eof = false;
	
	private final Token[] buffer = new Token[11];
	
	private final Lexer lexer;

	private Token peek = Token.EOF;
	
	public Scanner(Lexer lexer) {
		this.lexer = lexer;
		Arrays.fill(buffer, Token.EOF);
	}
	
	private Token shift() throws IOException {
		if(eof)
			return Token.EOF;
		if(buffer[5] == Token.EOF) {
			for(int i = 5 ; i < 11 ; i++)
				buffer[i] = lexer.scan();
		}
		peek = buffer[5];
		if(peek == Token.EOF)
			eof = true;
		if(!eof) {
			for(int i = 0 ; i < 10 ; i++)
				buffer[i] = buffer[i + 1];
			if(buffer[10] == Token.EOF)
				buffer[10] = Token.EOF;
			else
				buffer[10] = lexer.scan();
		}
		return peek;
	}
	
	public Token scan() throws IOException {
		return shift();
	}
	
	public Token next() {
		return buffer[5];
	}
	
	public Token next(int offset) {
		return buffer[5 + offset];
	}

	public Token current() {
		return peek;
	}

	public String pos() {
		return lexer.line() + ":" + lexer.pos();
	}
}
