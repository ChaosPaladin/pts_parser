package data.type;

public class DFloat extends DValue {
	public float value;
	
	public DFloat(float value) {
		this.value = value;
	}
	
	@Override
	public int asInteger() {
		return (int) value;
	}
	
	@Override
	public float asFloat() {
		return value;
	}
}
