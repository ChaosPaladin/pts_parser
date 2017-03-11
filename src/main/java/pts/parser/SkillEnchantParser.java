package pts.parser;

import java.io.IOException;
import java.nio.file.Paths;

import xml.Item;
import xml.SkillEnchantData;
import xml.SkillEnchantData.SkillEnchant;

public class SkillEnchantParser extends AbstractParser<SkillEnchantData> {

	@Override
	public SkillEnchantData parse() {
		SkillEnchantData data = new SkillEnchantData();
		try {
			parse(Paths.get("data", "skillenchantdata.txt")).forEach(record -> {
				SkillEnchant enchant = new SkillEnchant();
				data.list.add(enchant);
				parser(record).
					field("original_skill", setS(enchant, "original_skill")).
					field("route_id", setI(enchant, "route_id")).
					field("enchant_id", setI(enchant, "enchant_id")).
					field("skill_level", setI(enchant, "skill_level")).
					field("exp", setI(enchant, "exp")).
					field("sp", setI(enchant, "sp")).
					field("prob_76", setI(enchant, "prob_76")).
					field("prob_77", setI(enchant, "prob_77")).
					field("prob_78", setI(enchant, "prob_78")).
					field("item_needed", value -> {
						value.asArray().forEach(item -> {
							enchant.item_needed.add(new Item(item.get(0).asString(), item.get(1).asInteger()));
						});
					}).
					parse();
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
