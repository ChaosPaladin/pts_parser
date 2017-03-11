package data.lexer;

public class TWord extends Token {
	public final String value;
	
	public TWord(String value) {
		super(Token.Type.t_word);
		this.value = value;
	}
	
	@Override
	public String asString() {
		return value;
	}
	
	public String toString() {
		return "TWord("+ value+")";
	}

}
