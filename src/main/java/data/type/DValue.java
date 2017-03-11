package data.type;

public class DValue {

	public DValue get(int index) {
		throw new RuntimeException("not array " + this);
	}
	
	public boolean isArray() {
		return false;
	}
	
	public String asString() {
		return "";
	}
	
	public int asInteger() {
		return 0;
	}
	
	public float asFloat() {
		return 0;
	}

	public DArray asArray() {
		return (DArray) this;
	}
}
