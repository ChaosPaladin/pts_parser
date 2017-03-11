package xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Item {
	@XmlAttribute public String name;
	@XmlAttribute public int count;
	
	public Item() {}
	
	public Item(String name, int count) {
		this.name = name;
		this.count = count;
	}
}
