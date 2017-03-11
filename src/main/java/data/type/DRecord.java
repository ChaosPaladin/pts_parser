package data.type;

import java.util.ArrayList;
import java.util.HashMap;

public class DRecord {
	private final ArrayList<DValue> header = new ArrayList<>();
	
	private final HashMap<String, DField> fields = new HashMap<>();
	
	public final String type;
	
	public DRecord(String type) {
		this.type = type;
	}
	
	//add value for header
	public void add(DValue value) {
		header.add(value);
	}
	
	public void add(String name, DValue value) {
		fields.put(name, new DField(name, value));
	}
	
	public DValue get(int index) {
		return header.get(index);
	}
	public DValue get(String name) {
		return fields.get(name).value;
	}
	
	public HashMap<String, DField> fields() {
		return fields;
	}
	
	public boolean asBoolean(String name) {
		if(fields.get(name).value.asInteger() > 0)
			return true;
		else
			return false;
	}
	
	public String asString(String name) {
		return fields.get(name).value.asString();
	}

	public int asInteger(String name) {
		return fields.get(name).value.asInteger();
	}
}
