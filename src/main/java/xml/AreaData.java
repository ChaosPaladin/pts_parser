package xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "data")
public class AreaData {
	@XmlElementWrapper(name = "settings")
	@XmlElement(name = "setting")
	public List<DefaultSetting> settings = new LinkedList<>();
	
	@XmlElement(name = "area")
	public List<Area> list = new LinkedList<>();
	
	public static enum BlockedAction {
		priv_store,
		priv_rstore
	}
	
	public static enum AffectRace {
		elf,
		all
	}
	
	public static class Area {
		@XmlAttribute public String name;
		@XmlAttribute public AreaType type;
		@XmlAttribute public int[] map_no;
		@XmlAttribute public int off_time;
		@XmlAttribute public int unit_tick;
		@XmlAttribute public int exp_penalty_per;
		@XmlAttribute public int damage_on_mp;
		@XmlAttribute public int damage_on_hp;
		@XmlAttribute public int hp_regen_bonus;
		@XmlAttribute public int mp_regen_bonus;
		@XmlAttribute public int message_no;
		@XmlAttribute public int leaving_message_no;
		@XmlAttribute public int entering_message_no;
		@XmlAttribute public int event_id;
		@XmlAttribute public int move_bonus;
		@XmlAttribute public int random_time;
		@XmlAttribute public int skill_prob;
		@XmlAttribute public int initial_delay;
		@XmlAttribute public int on_time;
		@XmlAttribute public int restart_allowed_time;
		@XmlAttribute public int restart_time;
		@XmlAttribute public String item_drop;
		@XmlAttribute public String target;
		@XmlAttribute public String skill_name;
		@XmlAttribute public String default_status;
		@XmlAttribute public String skill_action_type;
		@XmlAttribute public AffectRace affect_race;
		@XmlElement public WaterRange water_range;
		@XmlList 
		@XmlElement
		public BlockedAction[] blocked_actions;
		@XmlList 
		@XmlElement
		public String[] skill_list;
		@XmlElementWrapper(name = "range")
		@XmlElement(name = "point")
		public Point[] range;
	}
	
	public static class WaterRange {
		@XmlList @XmlElement public double[] min;
		@XmlList @XmlElement public double[] max;
	}
	
	public static enum AreaType {
		damage,
		ssq_zone,
		peace_zone,
		poison,
		no_restart,
		instant_skill,
		mother_tree,
		battle_zone,
		swamp,
		water
	}
	
	public static class Point {
		@XmlAttribute public int x;
		@XmlAttribute public int y;
		@XmlAttribute public int minZ;
		@XmlAttribute public int maxZ;
		
		public Point(int x, int y, int minZ, int maxZ) {
			this.x = x;
			this.y = y;
			this.minZ = minZ;
			this.maxZ = maxZ;
		}
	}
	
	public static class DefaultSetting {
		@XmlAttribute public String type;
		@XmlElementWrapper(name = "properties")
		@XmlElement(name = "property")
		public List<SettingEntry> values = new LinkedList<>();
	}
	
	public static class SettingEntry {
		@XmlAttribute public String key;
		@XmlAttribute public String value;
		
		public SettingEntry() { }
		
		public SettingEntry(String key, String value) {
			this.key =  key;
			this.value = value;
		}
	}
}