package data.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class DArray extends DValue {
	public final ArrayList<DValue> list = new ArrayList<DValue>();

	public final HashMap<String, DValue> map = new HashMap<>();
	
	public void add(int index, DValue value) {
		list.add(index, value);
	}
	
	public void add(DValue value) {
		list.add(value);
	}
	
	public DValue get(String key) {
		return map.get(key);
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

	public void put(String key, DValue value) {
		map.put(key, value);
	}
}
