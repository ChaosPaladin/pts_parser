package data.lexer;

public class TFloat extends Token {
	public final double value;
	
	public TFloat(String value) {
		this(Double.parseDouble(value));
	}
	
	public TFloat(double d) {
		super(Token.Type.t_float);
		this.value = d;
	}
	
	public double asFloat() {
		return value;
	}
	
	@Override
	public String toString() {
		return "TDouble("+value+")";
	}
}
