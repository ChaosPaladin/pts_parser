package pts.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import data.DataParser;
import data.lexer.Scanner;
import data.type.DField;
import data.type.DRecord;
import data.type.DValue;

public abstract class AbstractParser<T> extends DataParser {

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
		
		public void parse() {
			record.fields().forEach((name, field) -> {
				for(BaseScanner<DField> s : fscan)
					if(s.test(field)) {
						s.apply(field);
						return;
					}
			});
			
			record.records.forEach(record -> {
				for(BaseScanner<DRecord> s : rscan)
					if(s.test(record)) {
						s.apply(record);
						break;
					}
			});
		}
	}
}