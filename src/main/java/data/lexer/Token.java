package data.lexer;

public abstract class Token {
	public enum Type {
		t_none,
		t_word,
		t_float,
		t_int,
		t_equal,//=
		t_semicolon,//;
		t_open_array,//{
		t_close_array,//}
		t_string,
		t_eof,
		t_reference//@
	}

	public final Type type;
	
	public Token(Type type) {
		this.type = type;
	}
	
	public static final class TEof extends Token {
		
		private TEof() {
			super(Type.t_eof);
		}
	}
	
	public static final class TEqual extends Token {
		private TEqual() {
			super(Type.t_equal);
		}
		
		@Override
		public String toString() {
			return "TEqual";
		}
	}
	
	public static final class TOpenArray extends Token {
		private TOpenArray() {
			super(Type.t_open_array);
		}
		@Override
		public String toString() {
			return "TOpenArray";
		}
	}
	
	public static final class TCloseArray extends Token {
		private TCloseArray() {
			super(Type.t_close_array);
		}
		@Override
		public String toString() {
			return "TCloseArray";
		}
	}
	
	public static final class TSemicolon extends Token {
		private TSemicolon() {
			super(Type.t_semicolon);
		}
		
		@Override
		public String toString() {
			return "TSemicolon";
		}
	}
	
	public float asFloat() {
		return 0;
	}
	
	public int asInteger() {
		return 0;
	}
	public String asString() {
		return "";
	}
	
	public final static TEof EOF = new TEof();
	public final static TEqual eq = new TEqual();
	public final static TOpenArray open_array = new TOpenArray();
	public final static TCloseArray close_array = new TCloseArray();
	public final static TSemicolon semicolon = new TSemicolon();
}
