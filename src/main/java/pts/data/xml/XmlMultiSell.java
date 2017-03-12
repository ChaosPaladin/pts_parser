package pts.data.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class XmlMultiSell {
	@XmlAttribute public String name;
	@XmlAttribute public int id;
	@XmlAttribute public boolean is_dutyfree = false;
	@XmlAttribute public boolean is_show_all = false;
	@XmlAttribute public boolean keep_enchanted = false;
	@XmlElement(name = "barter")
	public List<XmlBarter> list = new LinkedList<>();
	public static class XmlBarter {
		@XmlAttribute public int adena;
		@XmlElementWrapper(name = "goods")
		@XmlElement(name = "item")
		public List<XmlItem> goods = new LinkedList<>();
		@XmlElementWrapper(name = "production")
		@XmlElement(name = "item")
		public List<XmlItem> production = new LinkedList<>();
	}
}
