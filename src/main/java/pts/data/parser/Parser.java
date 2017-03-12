package pts.data.parser;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import data.type.DArray;
import data.type.DInteger;
import data.type.DRecord;
import data.type.DString;
import data.type.DValue;
import pts.data.xml.XmlMultiSell.XmlBarter;

public abstract class Parser<T> {

	public RecordParser parser(Class<T> type) {
		return new RecordParser(type);
	}
	
	@SuppressWarnings("unchecked")
	public <V, X> BiConsumer<X, DValue> collector(String name, Function<DValue, V> f) {
		return (v0, v1) -> {
			v1.asArray().forEach(value -> {
				try {
					((List<V>)v0.getClass().getField(name).get(v0)).add(f.apply(value));
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
			});
		};
	}
	
	public <X> ArrayParser<X> array(Class<X> type) {
		return new ArrayParser<>(type);
	}
	
	public <X> BiConsumer<X, DValue> setS(String name) {
		return (v0, v1) -> {
			try {
				v0.getClass().getField(name).set(v0, v1.asString());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}

	public  <X> BiConsumer<X, DValue> setF(String name) {
		return (v0, v1) -> {
			try {
				v0.getClass().getField(name).setFloat(v0, (float)v1.asFloat());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}

	public  <X> BiConsumer<X, DValue> setI(String name) {
		return (v0, v1) -> {
			try {
				v0.getClass().getField(name).setInt(v0, v1.asInteger());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}

	public <X> BiConsumer<X, DValue> setD(String name) {
		return (v0, v1) -> {
			try {
				v0.getClass().getField(name).setDouble(v0, v1.asFloat());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}

	public BiConsumer<T, DValue> setB(String name) {
		return (v0, v1) -> {
			try {
				v0.getClass().getField(name).setBoolean(v0, parseBoolean(v1));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}

	public BiConsumer<XmlBarter, DValue> setI(String name, Function<DValue, Object> f) {
		return (v0, v1) -> {
			try {
				v0.getClass().getField(name).setInt(v0, (Integer)f.apply(v1));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}
	
	private final boolean parseBoolean(DValue v1) {
		if(v1 instanceof DInteger) {
			return v1.asInteger() > 0;
		} else if(v1 instanceof DString) {
			switch(v1.asString()) {
			case "true":
			case "on":
			case "1": return true;
			case "false":
			case "off": 
			case "0": return false;
			default: return false;
			}
		} else
			return false;
	}
	
	public abstract T parse(DRecord record);
	
	public class ArrayParser<N> {
		private Class<N> type;

		private HashMap<Integer, BiConsumer<N, DValue>> indexs = new HashMap<>();
		
		public ArrayParser(Class<N> type) {
			this.type = type;
		}
		
		public ArrayParser<N> index(int index, BiConsumer<N, DValue> f) {
			indexs.put(index, f);
			return this;
		}
		
		public Object to(DValue val) {
			return val.get(0).asInteger();
		}
		public N parse(DValue val) {
			DArray array = val.asArray();
			try {
				N o = type.newInstance();
				for(int i = 0 ; i < array.size() ; i++)
					if(indexs.containsKey(i)) {
						indexs.get(i).accept(o, array.get(i));
					} else
						System.out.println("not found parser for index " + i);
				return o;
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public class RecordParser {
		private Class<T> type;
		
		private HashMap<String , BiConsumer<T, DValue>> fields = new HashMap<>();
		private HashMap<Integer, BiConsumer<T, DValue>> indexs = new HashMap<>();
		
		public RecordParser(Class<T> type) {
			this.type = type;
		}
		
		public RecordParser index(int index, BiConsumer<T, DValue> f) {
			indexs.put(index, f);
			return this;
		}
		public RecordParser field(String name, BiConsumer<T, DValue> f) {
			fields.put(name, f);
			return this;
		}
		
		public T parse(DRecord record) {
			try {
				T o = type.newInstance();
				for(int i = 0 ; i < record.header.size() ; i++)
					if(indexs.containsKey(i)) {
						indexs.get(i).accept(o, record.header.get(i));
					} else
						System.out.println("not found parser for index " + i);
				record.fields().forEach((key, field) -> {
					if(fields.containsKey(key)) {
						fields.get(key).accept(o, field.value);
					} else
						System.out.println("not found parser for field " + key);
				});
				return o;
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
