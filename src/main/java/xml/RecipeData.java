package xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class RecipeData {
	@XmlElement
	public List<Recipe> recipe = new LinkedList<>();
	
	public static class Recipe {
		@XmlAttribute public String name;
		@XmlAttribute public int id;
		@XmlAttribute public int level;
		@XmlAttribute public int item_id;
		@XmlAttribute public int mp_consume;
		@XmlAttribute public int success_rate;
		@XmlAttribute public boolean iscommonrecipe;
		
		@XmlElementWrapper(name = "product")
		@XmlElement(name = "item")
		public List<Item> product = new LinkedList<>();
		
		@XmlElementWrapper(name = "material")
		@XmlElement(name = "item")
		public List<Item> material = new LinkedList<>();
		
		@XmlElementWrapper(name = "npc-fee")
		@XmlElement(name = "item")
		public List<Item> npc_fee = new LinkedList<>();
	}
}
