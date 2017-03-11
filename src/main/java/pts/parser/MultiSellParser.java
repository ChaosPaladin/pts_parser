package pts.parser;

import java.io.IOException;
import java.nio.file.Paths;

import data.lexer.Lexer;
import xml.MultiSellData;
import xml.MultiSellData.Barter;
import xml.MultiSellData.Item;
import xml.MultiSellData.MultiSell;

public class MultiSellParser extends AbstractParser<MultiSellData> {
	
	public MultiSellData parse() {
		MultiSellData data = new MultiSellData();
		try {
			parse(new Lexer(Paths.get("data", "multisell.txt"))).forEach(record -> {
				MultiSell shop = new MultiSell();
				shop.id = record.get(1).asInteger();
				shop.name = record.get(0).asString();
				record.fields().forEach((key, field) -> {
					switch(key) {
					case "is_dutyfree": {
						shop.is_dutyfree = field.value.asInteger() == 1;
						break;
					}
					case "is_show_all": {
						shop.is_show_all = field.value.asInteger() == 1;
						break;
					}
					case "keep_enchanted": {
						shop.keep_enchanted = field.value.asInteger() == 1;
						break;
					}
					case "selllist": {
						field.value.asArray().forEach(value -> {
							Barter barter = new Barter();
							value.asArray().get(0).asArray().forEach(product -> {
								barter.production.add(new Item(product.get(0).asString(), product.get(1).asInteger()));
							});
							value.asArray().get(1).asArray().forEach(goods -> {
								barter.goods.add(new Item(goods.get(0).asString(), goods.get(1).asInteger()));
							});
							
							if(value.asArray().size() == 3)
								barter.adena = "" + value.asArray().get(2).asArray().get(0).asInteger();
							shop.barter.add(barter);
						});
						break;
					}
					default:
						throw new RuntimeException("unknow field : " + key);
					}
				});
				data.list.add(shop);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
