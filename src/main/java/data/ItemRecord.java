package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/*
 * 
 * 
equip_condition

can_equip_clan_leader
can_equip_nobless
can_equip_chaotic
can_equip_hero
can_equip_class
can_equip_clan_level
can_equip_agit
can_equip_change_class
can_equip_castle_num
can_equip_castle
can_equip_race
can_equip_sex


reduced_spiritshot
attack_skill
attack_range


item_skill
immediate_effect
attack_speed
critical_attack_skill
avoid_modify
physical_defense

reduced_soulshot
enchant_enable

reuse_delay

soulshot_count
mp_consume
item_skill_enchanted_four
can_penetrate

random_damage
shield_defense
shield_defense_rate





equip_pet
recipe_id
initial_count
magical_damage
armor_type
physical_damage
critical


magical_defense
mp_bonus
hit_modify


damage_range
magic_weapon


blessed
spiritshot_count
dual_fhit_rate
reduced_mp_consume
maximum_count
enchanted
damaged

magic_skill
category

+default_price
+weight
+crystal_count
+price
+durability

+html

+default_action
+consume_type
+crystal_type
+etcitem_type
+weapon_type
+item_type
+slot_bit_type??
+material_type
+armor_type

+is_destruct
+is_drop
+is_trade
 */
@XmlType(name = "item")
public class ItemRecord {
	
	@XmlAttribute
	public int id;
	@XmlAttribute
	public String name;

	@XmlAttribute
	public ItemAction defaultAction;
	
	@XmlAttribute
	public ItemType type;
	
	@XmlAttribute
	public int price;
	@XmlAttribute
	public int defaultPrice;
	@XmlAttribute
	public int weight;
	@XmlAttribute
	public int crystalCount;
	@XmlAttribute
	public int durability;
	
	@XmlAttribute
	public ConsumeType consumeType;
	
	@XmlAttribute
	public CrystalType crystalType;
	
	@XmlAttribute
	public WeaponType weaponType;
	
	@XmlAttribute
	public MaterialType materialType;
	
	@XmlAttribute
	public ArmorType armorType;
	
	@XmlAttribute
	public ItemType itemType;
	
	@XmlAttribute
	public EtcitemType etcitemType;
	
	@XmlAttribute
	public SlotBitType[] slotBitType;
	@XmlAttribute
	public boolean isDestruct;
	@XmlAttribute
	public boolean isDrop;
	@XmlAttribute
	public boolean isTrade;
	@XmlAttribute
	public boolean canPenetrate;
	@XmlAttribute
	public String html;
	@XmlAttribute
	boolean enchantEnable;
	@XmlAttribute
	public int recipeId;
	@XmlAttribute
	public boolean magicWeapon;

	@XmlAttribute
	public int[] reducedSoulshot;
	@XmlAttribute
	public int[] reducedSpiritshot;
	@XmlAttribute
	public int soulshotCount;
	@XmlAttribute
	public int spiritshotCount;
	@XmlAttribute
	public int initialCount;
	@XmlAttribute
	public int maximumCount;

	@XmlAttribute
	public int[] reducedMpConsume;
	@XmlAttribute
	public int mpConsume;
	@XmlAttribute
	public int mpBonus;

	@XmlAttribute
	public int magicalDamage;
	@XmlAttribute
	public int physicalDamage;
	@XmlAttribute
	public int critical;
	@XmlAttribute
	public int randomDamage;
	@XmlAttribute
	public int attackSpeed;
	@XmlAttribute
	public int[] damageRange;
	
	@XmlAttribute
	public int physicalDefense;
	@XmlAttribute
	public int magicalDefense;
	@XmlAttribute
	public float hitModify;
	@XmlAttribute
	public int avoidModify;
	@XmlAttribute
	public int reuseDelay;

	@XmlAttribute
	public int shieldDefense;
	@XmlAttribute
	public int shieldDefenseRate;
	@XmlAttribute
	public int dualFhitRate;
	@XmlAttribute
	public int attackRange;
	@XmlAttribute
	public String itemSkillEnchantedFour;

	@XmlAttribute
	public String attackSkill;
	@XmlAttribute
	public String criticalAttackSkill;
	@XmlAttribute
	public String itemSkill;
	
	
	@XmlAttribute
	public boolean immediateEffect;
	
	public String magic_skill;
	
	public String category;

	Object equip_pet;
	Object blessed;
	Object enchanted;	
	Object damaged;
}
