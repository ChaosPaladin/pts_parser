package data.lexer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Arrays;

public class Lexer {
	private final BufferedReader reader;
	
	private char peek = ' ';
	private int line = 1;
	private int pos = 0;
	private boolean eof = false;
	
	public Lexer(Path source) throws FileNotFoundException, UnsupportedEncodingException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(source.toFile()), "UTF-16"));
		
		Arrays.fill(buffer, -1);
	}
	
	private final int[] buffer = new int[11];
	
	private char next() {
		return next(0);
	}
	
	private char next(int offset) {
		return (char) buffer[5 + offset];
	}
	
	private int shift() throws IOException {
		if(!eof && buffer[5] == -1) {
			boolean eof = false;
			for(int i = 5 ; i < 11 ; i++) {
				if(!eof) {
					buffer[i] = reader.read();
					if(buffer[i] == -1)
						eof = true;
				} else
					buffer[i] = -1;
			}
		}
		int current = buffer[5];
		if(current == -1)
			eof = true;
		if(!eof) {
			//shifting
			for(int i = 0 ; i < 10 ; i++)
				buffer[i] = buffer[i + 1];
			if(buffer[9] == -1)
				buffer[10] = -1;
			else
				buffer[10] = reader.read();
		}
		return current;
	}
	
	private void dump() {
		System.out.println(Arrays.toString(buffer));
	}
	
	boolean readch() throws IOException {
		int c = shift();
		peek = (char) c;
		pos++;
		return !eof;
	}
	
	public Token scan() throws IOException {
		while(readch()) {
			if(peek == ' ' || peek == '\t' || peek == '\r')
				continue;
			if(peek == '\n') {
				line++;
				pos = 0;
				continue;
			}
			if(peek == '/') {
				if(next() == '*') {
					readch();//skip *
					while(!(next() == '*' && next(1) == '/') && readch());
					if(!eof) {
						//skip */ closing comment
						readch();// *
						readch();// /
					}
					continue;
				} else if(next() == '/') {
					readch();
					while(next() != '\n')
						readch();
					continue;
				} else
					throw new RuntimeException("syntax error: awaiting comment " + line + ":" + pos);
			}
			break;
		}
		if(eof)
			return Token.EOF;
		final StringBuilder buffer = new StringBuilder();
		switch(peek) {
		case '[': {
			while(next() != ']' && readch()) {
				if(peek == '\n')
					throw new RuntimeException("syntax error: new line in string " + line + ":" + pos);
				buffer.append(peek);
			}
			if(!eof)
				readch();//skip ]
			return new TString(buffer.toString());
		}
		case '(': return Token.lbrace;
		case ')': return Token.rbrace;
		case ':': return Token.colon;
		case '=': return Token.eq;
		case ';': return Token.semicolon;
		case '{': return Token.open_array;
		case '}': return Token.close_array;
		}
		if(Character.isLetter(peek) || peek == '_') {
			do {
				buffer.append(peek);
				if(!(Character.isLetterOrDigit(next()) || next() == '_'))
					break;
			} while(readch());
			return new TWord(buffer.toString());
		}
		if(peek == '-' || Character.isDigit(peek)) {
			boolean isFloat = false;
			do {
				buffer.append(peek);
				if(peek == '.')
					isFloat = true;
			} while((Character.isDigit(next()) || next() == '.') && readch());			
			return isFloat ? new TFloat(buffer.toString()) : new TInteger(buffer.toString());
		}
		if(peek == '@') {
			while((Character.isLetterOrDigit(next())|| next() == '_') && readch())
				buffer.append(peek);
			return new TReference(buffer.toString());
		}
		throw new RuntimeException("syntax error: wrong character " + peek + "(" + ((int) peek) + ")" + " " + line + ":" + pos);
	}

	public int pos() {
		return pos;
	}
	
	public int line() {
		return line;
	}
	
	/*
	 * comment
	 * word
	 * number->real->word
	 * number->word
	 * -number
	 * -real
	 * 
	 * string
	 * eq =
	 */
	
}
