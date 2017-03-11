package pts.parser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import data.DataParser;
import data.type.DField;
import data.type.DRecord;
import data.type.DValue;

public abstract class AbstractParser<T> extends DataParser {
	private HashMap<String, HashSet<String>> collector = new HashMap<>();
	
	public Consumer<DValue> collector(final String key) {
		if(!collector.containsKey(key))
			collector.put(key, new HashSet<>());
		return value -> {
			collector.get(key).add(value.asString().trim());
		};
	}
	public void collect(String key, String value) {
		if(!collector.containsKey(key))
			collector.put(key, new HashSet<>());
		collector.get(key).add(value);
	}
	
	public void print(String key) {
		collector.get(key).forEach(System.out::println);
	}
	
	
	public abstract T parse();
	
	
	public RecordParser parser(DRecord record) {
		return new RecordParser(record);
	}
	
	protected interface FieldScanner {
		public boolean test(DField field);
		
		public void scanner(DField field);
	}
	
	protected interface RecordScanner {
		public boolean test(DRecord record);
		public boolean scanner(DRecord record);
	}
	
	private class BaseScanner<T> {
		private Predicate<T> p;
		
		private Consumer<T> f;
		
		public BaseScanner(Predicate<T> p, Consumer<T> f) {
			this.p = p;
			this.f = f;
		}
		
		public boolean test(T value) {
			return p.test(value);
		}
		
		public void apply(T value) {
			f.accept(value);
		}
	}
	
	protected Consumer<DValue> setS(Object obj, String name) {
		return value -> {
			try {
				obj.getClass().getDeclaredField(name).set(obj, value.asString());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}
	
	protected Consumer<DValue> setB(Object obj, String name) {
		return value -> {
			try {
				obj.getClass().getDeclaredField(name).setBoolean(obj, Boolean.parseBoolean(value.asString()));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}
	
	protected Consumer<DValue> setF(Object obj, String name) {
		return value -> {
			try {
				obj.getClass().getDeclaredField(name).setDouble(obj, value.asFloat());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}
	
	protected Consumer<DValue> setI(Object obj, String name) {
		return value -> {
			try {
				obj.getClass().getDeclaredField(name).setInt(obj, value.asInteger());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		};
	}
	
	private <X> BaseScanner<X> scanner(Predicate<X> p, Consumer<X> f) {
		return new BaseScanner<>(p, f);
	}
	
	protected class RecordParser {
		private DRecord record;
		
		private ArrayList<BaseScanner<DRecord>> rscan = new ArrayList<>();
		
		private ArrayList<BaseScanner<DField>> fscan = new ArrayList<>();
		
		public RecordParser(DRecord record) {
			this.record = record;
		}
		
		public RecordParser field(BiConsumer<String, DValue> f) {
			fscan.add(scanner(x -> true, x -> f.accept(x.name, x.value)));
			return this;
		}
		public RecordParser field(final String name, Consumer<DValue> f) {
			fscan.add(scanner(field -> field.name.equals(name), field -> f.accept(field.value)));
			return this;
		}
		public RecordParser record(final String name, Consumer<DRecord> record) {
			rscan.add(scanner(x -> x.type.equals(name), record));
			return this;
		}
		
		public RecordParser parse() {
			record.fields().forEach((name, field) -> {
				for(BaseScanner<DField> s : fscan)
					if(s.test(field)) {
						s.apply(field);
						return;
					}
				throw new RuntimeException("unknown field " + record.type + " " + name);
			});
			
			record.records.forEach(record -> {
				for(BaseScanner<DRecord> s : rscan)
					if(s.test(record)) {
						s.apply(record);
						break;
					}
			});
			return this;
		}
	}
	
	public static Path data(String file) {
		return Paths.get("data", file);
	}
}