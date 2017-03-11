package pts.parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import data.type.DValue;
import xml.AreaData;
import xml.AreaData.AffectRace;
import xml.AreaData.Area;
import xml.AreaData.AreaType;
import xml.AreaData.BlockedAction;
import xml.AreaData.DefaultSetting;
import xml.AreaData.Point;
import xml.AreaData.SettingEntry;
import xml.AreaData.WaterRange;

public class AreaParser extends AbstractParser<AreaData	> {
	
	@Override
	public AreaData parse() {
		AreaData data = new AreaData();
		try {
			parse(data("areadata.txt")).forEach(record -> {
				if(record.type.equals("default_setting")) {
					DefaultSetting setting = new DefaultSetting();
					data.settings.add(setting);
					parser(record).
						field("type", setS(setting, "type")).
						field((name, value) -> setting.values.add(new SettingEntry(name, value.asString()))).
						parse();
				} else {
					Area area = new Area();
					data.list.add(area);
					parser(record).
						field("name", setS(area, "name")).
						field("skill_list", value -> {
							List<String> list = new LinkedList<>();
							value.asArray().forEach(x -> list.add(x.asString()));
							area.skill_list = list.toArray(new String[0]);
						}).
						field("skill_name", setS(area, "skill_name")).
						field("on_time", setI(area, "on_time")).
						field("mp_regen_bonus", setI(area, "mp_regen_bonus")).
						field("hp_regen_bonus", setI(area, "hp_regen_bonus")).
						field("initial_delay", setI(area, "initial_delay")).
						field("unit_tick", setI(area, "unit_tick")).
						field("skill_prob", setI(area, "skill_prob")).
						field("random_time", setI(area, "random_time")).
						field("target", setS(area, "target")).
						field("off_time", setI(area, "off_time")).
						field("damage_on_mp", setI(area, "damage_on_mp")).
						field("damage_on_hp", setI(area, "damage_on_hp")).
						field("message_no", setI(area, "message_no")).
						field("entering_message_no", setI(area, "entering_message_no")).
						field("leaving_message_no", setI(area, "leaving_message_no")).
						field("event_id", setI(area, "event_id")). 
						field("exp_penalty_per", setI(area, "exp_penalty_per")). 
						field("move_bonus", setI(area, "move_bonus")).
						field("item_drop", setS(area, "item_drop")).
						field("restart_allowed_time", setI(area, "restart_allowed_time")).
						field("restart_time", setI(area, "restart_time")).
						field("default_status", setS(area, "default_status")).
						field("blocked_actions", value -> {
							area.blocked_actions = value.asArray().list.stream().
									map(DValue::asString).
									map(BlockedAction::valueOf).
									toArray(BlockedAction[]::new);
						}).
						field("affect_race", value -> {
							area.affect_race = AffectRace.valueOf(value.asString());
						}).
						field("map_no", value -> {
							area.map_no = new int[] { value.get(0).asInteger(), value.get(1).asInteger() };
						}).
						field("skill_action_type", setS(area, "skill_action_type")).
						field("type", value -> {
							area.type = AreaType.valueOf(value.asString());
						}).
						field("range", value -> {
							area.range = 
								value.asArray().list.stream().map(p -> {
									return new Point(p.get(0).asInteger(), p.get(1).asInteger(), p.get(2).asInteger(), p.get(3).asInteger());
								}).toArray(Point[]::new);
						}).
						field("water_range", value -> {
							area.water_range = new WaterRange();
							area.water_range.min = 
									value.get("Min").asArray().list.stream().
										mapToDouble(DValue::asFloat).
										toArray();
							area.water_range.max = 
									value.get("Max").asArray().list.stream().
										mapToDouble(DValue::asFloat).
										toArray();
						}).
						parse();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
