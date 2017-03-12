package pts.data;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import data.DataParser;
import pts.data.parser.MultiSellParser;
import pts.data.parser.Parser;
import pts.data.xml.XmlData;

public final class Data {

	private Data() {}
	
	private static HashMap<String, Parser<?>> parsers = new HashMap<>();
	
	static {
		parsers.put("MultiSell", new MultiSellParser());
	}
	
	public static XmlData from(String file) {
		XmlData data = new XmlData();
		try {
			DataParser.parse(Paths.get("data", file)).forEach(record -> {
				if(parsers.containsKey(record.type))
					data.list.add(parsers.get(record.type).parse(record));
				else
					System.out.println("not found parser for data " + record.type);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
}
