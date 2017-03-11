package data.lexer;

public class TReference extends Token {
	private String value;
	
	public TReference(String value) {
		super(Type.t_reference);
		this.value = value;
	}
	
	@Override
	public String asString() {
		return value;
	}
}
