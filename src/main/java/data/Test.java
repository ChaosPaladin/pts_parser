package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import data.lexer.Lexer;
import data.type.DArray;
import data.type.DFloat;
import data.type.DInteger;
import data.type.DPair;
import data.type.DRecord;
import data.type.DString;
import data.type.DValue;
import data.type.DWord;

public class Test {
	
	public static class Item {
		Object[] reduced_spiritshot;
		String attack_skill;
		Object[] str_inc;
		int attack_range;
		String item_type;
		int default_price;
		String item_skill;
		int immediate_effect;
		int attack_speed;
		Object[] con_inc;
		String critical_attack_skill;
		int avoid_modify;
		int ex_immediate_effect;
		int physical_defense;
		Object[] unequip_skill;
		Object[] slot_chest;
		int can_move;
		Object[] base_attribute_attack;
		int price;
		int delay_share_group;
		Object[] reduced_soulshot;
		int enchant_enable;
		int reuse_delay;
		String html;
		int soulshot_count;
		int drop_period;
		int mp_consume;
		String item_skill_enchanted_four;
		int can_penetrate;
		int period;
		Object[] slot_feet;
		String crystal_type;
		int random_damage;
		String set_additional_effect_skill;
		int shield_defense;
		Object[] dex_inc;
		int shield_defense_rate;
		int durability;
		String slot_additional;
		int weight;
		Object[] slot_legs;
		String etcitem_type;
		int equip_reuse_delay;
		String default_action;
		int recipe_id;
		int is_premium;
		int use_skill_distime;
		Object[] use_condition;
		int initial_count;
		int magical_damage;
		Object[] int_inc;
		String set_skill;
		String armor_type;
		Object[] wit_inc;
		String set_additional2_effect_skill;
		int physical_damage;
		int critical;
		String weapon_type;
		int set_additional2_condition;
		int magical_defense;
		Object[] slot_lhand;
		int mp_bonus;
		int hit_modify;
		Object[] duration;
		String material_type;
		int crystal_count;
		Object[] damage_range;
		int magic_weapon;
		Object[] base_attribute_defend;
		String set_effect_skill;
		int is_destruct;
		Object[] item_equip_option;
		Object[] slot_gloves;
		int keep_type;
		Object[] equip_condition;
		Object[] slot_bit_type;
		int elemental_enable;
		int blessed;
		int spiritshot_count;
		Object[] men_inc;
		Object[] slot_head;
		int dual_fhit_rate;
		int is_drop;
		Object[] item_multi_skill_list;
		Object[] reduced_mp_consume;
		int is_trade;
		int for_npc;
		int maximum_count;
		int enchanted;
		Object[] capsuled_items;
		int damaged;
		String consume_type;
		String magic_skill;
		Object[] category;
	}
	
	/*
	 * 	
	Object[] reduced_spiritshot;
	String attack_skill;
	Object[] str_inc;
	int attack_range;
	String item_type;
	int default_price;
	String item_skill;
	int immediate_effect;
	int attack_speed;
	int can_equip_sex;
	Object[] con_inc;
	String critical_attack_skill;
	int avoid_modify;
	int physical_defense;
	int slot_chest;
	int price;
	Object[] reduced_soulshot;
	int enchant_enable;
	int can_equip_clan_leader;
	int reuse_delay;
	String html;
	int soulshot_count;
	int mp_consume;
	String item_skill_enchanted_four;
	int can_penetrate;
	int slot_feet;
	String crystal_type;
	int random_damage;
	String set_additional_effect_skill;
	int shield_defense;
	Object[] dex_inc;
	int shield_defense_rate;
	int durability;
	int can_equip_nobless;
	String slot_additional;
	int weight;
	int slot_legs;
	String etcitem_type;
	String default_action;
	Object[] equip_pet;
	int recipe_id;
	int initial_count;
	int magical_damage;
	Object[] int_inc;
	String set_skill;
	String armor_type;
	Object[] wit_inc;
	int physical_damage;
	int critical;
	int can_equip_chaotic;
	String weapon_type;
	int magical_defense;
	int slot_lhand;
	int mp_bonus;
	int hit_modify;
	String material_type;
	int crystal_count;
	Object[] damage_range;
	int magic_weapon;
	int can_equip_hero;
	String set_effect_skill;
	Object[] can_equip_class;
	int is_destruct;
	int slot_gloves;
	int can_equip_clan_level;
	int can_equip_agit;
	Object[] slot_bit_type;
	int can_equip_castle;
	int blessed;
	int spiritshot_count;
	Object[] men_inc;
	int slot_head;
	int dual_fhit_rate;
	int is_drop;
	Object[] reduced_mp_consume;
	int is_trade;
	int maximum_count;
	int enchanted;
	int can_equip_change_class;
	Object[] can_equip_castle_num;
	int damaged;
	String consume_type;
	String magic_skill;
	Object[] category;
	Object[] can_equip_race;
	 */
	class Item0 {
		public int id;
		
		public String name;
		
		public String type;
		
		public String itemType;
	}
	
	public static void main(String...args) throws IOException {
		//scan fields name
		HashSet<String> fields = new HashSet<>();
		
		HashMap<String, String> fieldsType = new HashMap<>();
		
		try {
			//Scanner scanner = new Scanner(new Lexer(Paths.get("data", "Itemdata.txt")));
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			//System.out.println(scanner.scan());
			List<DRecord> list = DataParser.parse(new Lexer(Paths.get("data", "itemdata0.txt")));
			System.out.println(list.size());
			list.forEach(record -> {
				if(record.type.equals("item")) {
					if(!record.get("item_type").asString().equals(record.get(0).asString())) {
						System.out.println(record.get(2).asString());
					}
					
				} else
					System.out.println(record.type);
				record.fields().keySet().forEach(fields::add);
				
				record.fields().values().forEach(f -> {
					fields.add(f.name);
					fieldsType.putIfAbsent(f.name, asType(f.value));
				});
			});
			fieldsType.forEach((name, type) -> System.out.println("\t" + type + " " + name + ";"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String asType(DValue value) {
		if(value instanceof DWord)
			return "String";
		if(value instanceof DString)
			return "String";
		if(value instanceof DArray)
			return "Object[]";
		if(value instanceof DFloat)
			return "float";
		if(value instanceof DInteger)
			return "int";
		if(value instanceof DPair)
			return "Object[2]";
		return "";
	}
}
