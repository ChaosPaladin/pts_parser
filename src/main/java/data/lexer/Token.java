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
		t_lbrace, //(
		t_rbrace, //)
		t_eof,
		t_reference, //@
		t_colon //:
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
	
	public static final class TColon extends Token {
		private TColon() {
			super(Type.t_colon);
		}
		
		@Override
		public String toString() {
			return "TColon";
		}
	}
	
	public static final class TLBrace extends Token {
		private TLBrace() { 
			super(Type.t_lbrace);
		}
		
		@Override
		public String toString() {
			return "TLBrace";
		}
	}
	
	public static final class TRBrace extends Token {
		private TRBrace() { 
			super(Type.t_rbrace);
		}
		
		@Override
		public String toString() {
			return "TRBrace";
		}
	}
	
	public double asFloat() {
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
	public final static TColon colon = new TColon();
	
	public final static TLBrace lbrace = new TLBrace();
	public final static TRBrace rbrace = new TRBrace();
}
