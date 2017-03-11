package xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class MultiSellData {
	@XmlElement(name = "multisell", type = MultiSell.class)
	public List<MultiSell> list = new LinkedList<>();
	
	public static class MultiSell {
		@XmlAttribute public String name;
		@XmlAttribute public int id;
		@XmlAttribute public boolean is_dutyfree = false;
		@XmlAttribute public boolean is_show_all = true;
		@XmlAttribute public boolean keep_enchanted = false;
		@XmlElement public List<Barter> barter = new LinkedList<>();
	}

	
	public static class Barter {
		@XmlElement public List<Item> goods = new LinkedList<>();
		@XmlElement public List<Item> production = new LinkedList<>();
		@XmlAttribute public String adena;
	}

	public static class Item {
		@XmlAttribute public String name;
		@XmlAttribute public int count;
		
		public Item() {}
		
		public Item(String name, int count) {
			this.name = name;
			this.count = count;
		}
	}
}
