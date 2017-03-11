package data.type;

public class DValue {
	
	public DValue get(String key) {
		throw new RuntimeException("not array " + this);
	}
	
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
	
	public double asFloat() {
		return 0;
	}

	public DArray asArray() {
		return (DArray) this;
	}
}
