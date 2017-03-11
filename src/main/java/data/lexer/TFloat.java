package data.lexer;

public class TFloat extends Token {
	public final float value;
	
	public TFloat(String value) {
		this(Float.parseFloat(value));
	}
	
	public TFloat(float value) {
		super(Token.Type.t_float);
		this.value = value;
	}
	
	public float asFloat() {
		return value;
	}
	@Override
	public String toString() {
		return "TDouble("+value+")";
	}
}
