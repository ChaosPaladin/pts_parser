package data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import data.lexer.Lexer;
import data.lexer.Scanner;
import data.lexer.Token;
import data.type.DArray;
import data.type.DFloat;
import data.type.DInteger;
import data.type.DPair;
import data.type.DRecord;
import data.type.DString;
import data.type.DValue;
import data.type.DWord;

public class DataParser {
	
	public static List<DRecord> parse(Path path) throws IOException {
		return parse(new Lexer(path));
	}
	
	public static List<DRecord> parse(Lexer lexer) throws IOException {
		return parse(new Scanner(lexer));
	}
	
	public static List<DRecord> parse(Scanner scanner) throws IOException {
		final LinkedList<DRecord> list = new LinkedList<>();
		Token token;
		while((token = scanner.scan()) != Token.EOF) {
			if(token.type == Token.Type.t_word) {
				String type = token.asString();
				if(type.endsWith("_begin")) {
					type = type.substring(0, type.length() - 6);
					list.add(parseRecord(scanner, type));
				} else
					throw new RuntimeException("parser error: wrong open TWord " + token);
			} else
				throw new RuntimeException("parser error: wrong first token " + token);
		}
		return list;
	}
	
	private static DRecord parseRecord(Scanner scanner, String type) throws IOException {
		final DRecord record = new DRecord(type);
		while(scanner.scan() != Token.EOF) {
			switch(scanner.current().type) {
			case t_word: {
				String word = scanner.current().asString();
				if(word.endsWith("_begin")) {
					word = word.substring(0, word.length() - 6);
					record.add(parseRecord(scanner, word));
					break;
				}
				if(word.equalsIgnoreCase(type+"_end"))
					return record;
				if(scanner.next() == Token.eq) {
					scanner.scan();//skip =
					record.add(word, parseValue(scanner));
				} else
					record.add(new DString(word));
				break;
			}
			case t_float:
			case t_string:
			case t_int: {
				record.add(parseValue(scanner.current()));
				break;
			}
			default:
				throw new RuntimeException(scanner.current().toString() + " " + scanner.pos());
			}
		}
		throw new RuntimeException("eof");
	}
	
	private static DValue parseValue(Scanner scanner) throws IOException {
		switch(scanner.scan().type) {
		case t_string:
			if(scanner.next() == Token.semicolon) {
				DValue v0 = parseValue(scanner.current());
				scanner.scan();//skip ;
				return new DPair(v0, parseValue(scanner.scan()));
			} else
				return parseValue(scanner.current());
		case t_float:
		case t_int: 
		case t_word: {
			return parseValue(scanner.current());
		}
		case t_open_array:
			return parseArray(scanner);
		default:
			throw new RuntimeException("parser error: parse value wrong token " + scanner.current());
		}
	}
	
	private static DValue parseArray(Scanner scanner) throws IOException {
		DArray array = new DArray();
		while(scanner.scan() != Token.EOF) {
			switch(scanner.current().type) {
			case t_reference:
			case t_float:
			case t_string:
			case t_word:
			case t_int: {
				array.add(parseValue(scanner.current()));
				if(scanner.next() == Token.semicolon)
					scanner.scan();//skip ;
				break;
			}
			case t_open_array:
				array.add(parseArray(scanner));
				if(scanner.next() == Token.semicolon)
					scanner.scan();//skip ;
				break;
			case t_close_array:
				return array;
			}
		}
		throw new RuntimeException("error parser: eof");
	}
	
	private static DValue parseValue(Token token) {
		switch(token.type) {
		case t_reference: 
			return new DString(token.asString());//TODO
		case t_string: return new DString(token.asString());
		case t_word: return new DWord(token.asString());
		case t_int: return new DInteger(token.asInteger());
		case t_float: return new DFloat(token.asFloat());
		default:
			throw new RuntimeException("parser error: try parse wrong value token " + token);
		}
	}
}
