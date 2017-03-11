package data.type;

public class DWord extends DValue {
	public String value;
	
	public DWord(String value) {
		this.value = value;
	}
	
	public String asString() {
		return value;
	}
}
