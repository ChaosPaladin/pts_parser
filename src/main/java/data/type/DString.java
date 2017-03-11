package data.type;

public class DString extends DValue {
	public String value;
	
	public DString(String value) {
		this.value = value;
	}
	
	@Override
	public String asString() {
		return value;
	}
	
	
}
