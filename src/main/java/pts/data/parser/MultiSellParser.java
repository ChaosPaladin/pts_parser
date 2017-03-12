package pts.data.parser;

import java.util.function.Function;

import data.type.DRecord;
import data.type.DValue;
import pts.data.xml.XmlItem;
import pts.data.xml.XmlMultiSell;
import pts.data.xml.XmlMultiSell.XmlBarter;

public class MultiSellParser extends Parser<XmlMultiSell> {

	@Override
	public XmlMultiSell parse(DRecord record) {
		Function<DValue, XmlItem> collector0 = array(XmlItem.class).
				index(0, setS("name")).
				index(1, setI("count"))::parse;
		
		Function<DValue, XmlBarter> collector1 = 
				array(XmlBarter.class).
					index(0, collector("production", collector0)).
					index(1, collector("goods", collector0)).
					index(2, setI("adena", array(int.class)::to))::parse;
		
		return 
			parser(XmlMultiSell.class).
				index(0, setS("name")).
				index(1, setI("id")).
				field("is_dutyfree", setB("is_dutyfree")).
				field("is_show_all", setB("is_show_all")).
				field("keep_enchanted", setB("keep_enchanted")).
				field("selllist", collector("list", collector1)).
				parse(record);
	}

}
