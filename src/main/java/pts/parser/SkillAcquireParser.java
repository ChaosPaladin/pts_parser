package pts.parser;

import java.io.IOException;
import java.nio.file.Paths;

import xml.SkillAcquireData;
import xml.SkillAcquireData.AcquireList;
import xml.SkillAcquireData.Item;
import xml.SkillAcquireData.Skill;

public class SkillAcquireParser extends AbstractParser<SkillAcquireData> {
	@Override
	public SkillAcquireData parse() {
		SkillAcquireData data = new SkillAcquireData();
		try {
			parse(Paths.get("data", "skillacquire.txt")).forEach(record -> {
				AcquireList list = new AcquireList();
				data.list.add(list);
				list.type = record.type;
				if(record.header.size() > 0)
					list.include = record.get(0).asString().substring(8);
				parser(record).
					record("skill", rSkill -> {
						final Skill skill = new Skill();
						list.list.add(skill);
						parser(rSkill).
							field("auto_get", v -> skill.auto_get = Boolean.parseBoolean(v.asString())).
							field("get_lv", v -> skill.get_lv = v.asInteger()).
							field("lv_up_sp", v -> skill.lv_up_sp = v.asInteger()).
							field("skill_name", v -> skill.skill_name = v.asString()).
							field("item_needed", v -> {
								v.asArray().forEach(v0 -> {
									final Item item = new Item();
									item.name = v0.get(0).asString();
									item.count = v0.get(1).asInteger();
									skill.item_needed.add(item);
								});
							}).
							field((name, value) -> System.out.println(name)).
							parse();
						
					}).parse();
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}


}
