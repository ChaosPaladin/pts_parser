package pts.parser;

import data.DataParser;

public abstract class AbstractParser<T> extends DataParser {

	public abstract T parse();
	
}
