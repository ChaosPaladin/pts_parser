package xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class SkillAcquireData {
	@XmlElement(name = "acquire")
	public List<AcquireList> list = new LinkedList<>();
	
	public static class AcquireList {
		@XmlAttribute public String type;
		@XmlAttribute public String include = null;
		@XmlElement(name = "skill")
		public List<Skill> list = new LinkedList<>();
	}
	
	public static class Skill {
		@XmlAttribute public String skill_name;
		@XmlAttribute public int get_lv;
		@XmlAttribute public long lv_up_sp;
		@XmlAttribute public boolean auto_get;
		@XmlElement(name = "item")
		public LinkedList<Item> item_needed = new LinkedList<>();
	}
	
	public static class Item {
		@XmlAttribute public String name;
		@XmlAttribute public int count;
	}
}
