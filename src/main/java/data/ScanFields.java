package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import data.lexer.Lexer;
import data.type.DArray;
import data.type.DRecord;

/*
reduced_spiritshot
attack_skill

attack_range

default_price
item_skill
immediate_effect
attack_speed
can_equip_sex

critical_attack_skill
avoid_modify
physical_defense
slot_chest
price
reduced_soulshot
enchant_enable
can_equip_clan_leader
reuse_delay
html
soulshot_count
mp_consume
item_skill_enchanted_four
can_penetrate
slot_feet

random_damage
set_additional_effect_skill
shield_defense

shield_defense_rate
durability
can_equip_nobless
slot_additional
weight
slot_legs

default_action
equip_pet
recipe_id
initial_count
magical_damage

set_skill


physical_damage
critical
can_equip_chaotic

magical_defense
slot_lhand
mp_bonus
hit_modify

crystal_count
damage_range
magic_weapon
can_equip_hero
set_effect_skill
can_equip_class

slot_gloves
can_equip_clan_level
can_equip_agit
can_equip_castle
blessed
spiritshot_count

slot_head
dual_fhit_rate

reduced_mp_consume

maximum_count
enchanted
can_equip_change_class
can_equip_castle_num
damaged
magic_skill
category
can_equip_race

+default_action
+slot_bit_type
+etcitem_type
+item_type
+armor_type
+material_type
+weapon_type
+consume_type
+crystal_type


dex_inc
int_inc
wit_inc
str_inc
con_inc
men_inc
is_destruct
is_trade
is_drop
 */
public class ScanFields {
	private static String fieldName = "default_action";
	
	public static void main(String...args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		List<DRecord> list = DataParser.parse(new Lexer(Paths.get("data", "itemdata0.txt")));

		HashSet<String> set = newSet();
		HashSet<String> value = newSet();
		
		final ItemList items = new ItemList();
		
		list.forEach(record -> {
			if(record.type.equals("item")) {
				items.list.add(asItem(record));
				record.fields().forEach((name, field) -> { 
					if(name.equals(fieldName)) {
						if(field.value.isArray()) {
							field.value.asArray().forEach(v -> value.add(v.asString()));
						} else
							value.add(field.value.asString()); 
					}
				}); 
				record.fields().keySet().forEach(set::add);
			}

		});
		
		JAXB.marshal(items, new File("items.xml"));
		//set.forEach(System.out::println);
		value.forEach(System.out::println);
		
	}
	
	@XmlRootElement(name="data")
	public static class ItemList {
		@XmlElement(name="item")
		public List<ItemRecord> list = new LinkedList<>();
		
	}
	
	public static ItemRecord asItem(DRecord record) {
		final ItemRecord item = new ItemRecord();
		item.id = record.get(1).asInteger();
		item.name = record.get(2).asString();
		item.consumeType = ConsumeType.valueOf(record.asString("consume_type"));
		item.crystalType = CrystalType.valueOf(record.asString("crystal_type"));
		item.armorType = ArmorType.valueOf(record.asString("armor_type"));
		item.weaponType = WeaponType.valueOf(record.asString("weapon_type"));
		item.etcitemType = EtcitemType.valueOf(record.asString("etcitem_type"));
		item.itemType = ItemType.valueOf(record.asString("item_type"));
		item.isDestruct = record.asBoolean("is_destruct");
		item.isDrop = record.asBoolean("is_drop");
		item.isTrade = record.asBoolean("is_trade");
		item.defaultAction = ItemAction.valueOf(record.asString("default_action"));
		item.html = record.asString("html");
		item.defaultPrice = record.asInteger("default_price");
		item.crystalCount = record.asInteger("crystal_count");
		item.price = record.asInteger("price");
		item.weight = record.asInteger("weight");
		item.recipeId = record.asInteger("recipe_id");
		item.enchantEnable = record.asBoolean("enchant_enable");
		item.magicWeapon = record.asBoolean("magic_weapon");
		item.canPenetrate = record.asBoolean("can_penetrate");
		
		DArray array = record.get("reduced_soulshot").asArray();
		if(array.size() > 0) {
			item.reducedSoulshot = new int[] {
				array.get(0).asInteger(),
				array.get(1).asInteger()
			};
		} else
			item.reducedSoulshot = new int[0];
		
		array = record.get("reduced_spiritshot").asArray();
		if(array.size() > 0) {
			item.reducedSpiritshot = new int[] {
				array.get(0).asInteger(),
				array.get(1).asInteger()
			};
		} else
			item.reducedSpiritshot = new int[0];
		
		item.soulshotCount = record.asInteger("soulshot_count");
		item.spiritshotCount = record.asInteger("spiritshot_count");
		item.initialCount = record.asInteger("initial_count");
		item.maximumCount = record.asInteger("maximum_count");
		
		array = record.get("reduced_mp_consume").asArray();
		if(array.size() > 0) {
			item.reducedMpConsume = new int[] {
				array.get(0).asInteger(),
				array.get(1).asInteger()
			};
		} else
			item.reducedMpConsume = new int[0];
		item.mpConsume = record.asInteger("mp_consume");
		item.mpBonus = record.asInteger("mp_bonus");
		
		item.magicalDamage = record.asInteger("magical_damage");
		item.physicalDamage = record.asInteger("physical_damage");
		item.critical = record.asInteger("critical");
		item.randomDamage = record.asInteger("random_damage");
		item.attackSpeed = record.asInteger("attack_speed");
		array = record.get("damage_range").asArray();
		if(array.size() > 0) {
			item.damageRange = new int[] {
				array.get(0).asInteger(),
				array.get(1).asInteger(),
				array.get(2).asInteger(),
				array.get(3).asInteger()
			};
		} else
			item.damageRange = new int[0];

		item.physicalDefense = record.asInteger("physical_defense");
		item.magicalDefense = record.asInteger("magical_defense");
		item.hitModify = record.get("hit_modify").asFloat();
		item.avoidModify = record.asInteger("avoid_modify");
		item.reuseDelay = record.asInteger("reuse_delay");
		
		item.shieldDefense = record.asInteger("shield_defense");
		item.shieldDefenseRate = record.asInteger("shield_defense_rate");
		item.dualFhitRate = record.asInteger("dual_fhit_rate");
		item.magicalDefense = record.asInteger("magical_defense");
		item.attackRange = record.asInteger("attack_range");
		item.itemSkillEnchantedFour = record.asString("item_skill_enchanted_four");
		item.attackSkill = record.asString("attack_skill");
		item.criticalAttackSkill = record.asString("critical_attack_skill");
		item.itemSkill = record.asString("item_skill");
		
		item.immediateEffect = record.asBoolean("immediate_effect");
		return item;
	}
	
	
	public static HashSet<String> newSet() {
		return new HashSet<>();
	}
}
