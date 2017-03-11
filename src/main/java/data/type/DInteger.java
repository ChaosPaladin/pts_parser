package data.type;

public class DInteger extends DValue {
	public int value;
	
	public DInteger(int value) {
		this.value = value;
	}
	
	@Override
	public float asFloat() {
		return value;
	}
	
	@Override
	public int asInteger() {
		return value;
	}
}
