package data.lexer;

public class TInteger extends Token{
	public final int value;
	
	public TInteger(String value) {
		this(Integer.parseInt(value));
	}
	
	public TInteger(int value) {
		super(Token.Type.t_int);
		this.value = value;
	}
	
	@Override
	public int asInteger() {
		return value;
	}
	
	@Override
	public String toString() {
		return "TInteger("+value+")";
	}
}
