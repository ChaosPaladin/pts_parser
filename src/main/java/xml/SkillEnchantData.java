package xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class SkillEnchantData {
	@XmlElement(name = "skill-enchant")
	public List<SkillEnchant> list = new LinkedList<>();
	
	public static class SkillEnchant {
		@XmlAttribute public String original_skill;
		@XmlAttribute public int route_id;
		@XmlAttribute public int enchant_id;
		@XmlAttribute public int skill_level;
		@XmlAttribute public int exp;
		@XmlAttribute public int sp;
		@XmlAttribute public int prob_76;
		@XmlAttribute public int prob_77;
		@XmlAttribute public int prob_78;
		@XmlElement(name = "item")
		public List<Item> item_needed = new LinkedList<>();
	}
}
