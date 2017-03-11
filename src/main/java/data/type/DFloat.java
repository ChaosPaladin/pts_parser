package data.type;

public class DFloat extends DValue {
	public double value;
	
	public DFloat(double value) {
		this.value = value;
	}
	
	@Override
	public int asInteger() {
		return (int) value;
	}
	
	@Override
	public double asFloat() {
		return value;
	}
}
