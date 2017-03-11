package data.lexer;

public class TString extends Token {
	public final String value;
	
	public TString(String value) {
		super(Token.Type.t_string);
		this.value = value;
	}
	
	@Override
	public String asString() {
		return value;
	}
	
	public String toString() {
		return "TString("+value+")";
	}
}
