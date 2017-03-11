package data.type;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DArray extends DValue {
	private ArrayList<DValue> list = new ArrayList<DValue>();
	
	public void add(int index, DValue value) {
		list.add(index, value);
	}
	
	public void add(DValue value) {
		list.add(value);
	}
	
	public DValue get(int index) {
		return list.get(index);
	}
	
	public boolean isArray() {
		return true;
	}
	
	public int size() {
		return list.size();
	}
	
	public void forEach(Consumer<DValue> f) {
		list.forEach(f);
	}
}
