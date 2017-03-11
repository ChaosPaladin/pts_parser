package pts.parser;

import java.io.IOException;
import java.nio.file.Paths;

import data.type.DValue;
import xml.RecipeData;
import xml.RecipeData.Item;
import xml.RecipeData.Recipe;

public class RecipeParser extends AbstractParser<RecipeData> {

	public static Item item(DValue value) {
		return item(value.get(0).asString(), value.get(1).asInteger());
	}
	
	public static Item item(String name, int count) {
		final Item item = new Item();
		item.name = name;
		item.count = count;
		return item;
	}
	@Override
	public RecipeData parse() {
		RecipeData data = new RecipeData();
		try {
			parse(Paths.get("data", "recipe.txt")).forEach(record -> {
				final Recipe recipe = new Recipe();
				data.recipe.add(recipe);
				recipe.name = record.get(1).asString();
				recipe.id = record.get(0).asInteger();
				record.fields().forEach((key, field) -> {
					switch(key) { 
					case "iscommonrecipe":
						recipe.iscommonrecipe = field.value.asInteger() == 1;
						break;
					case "success_rate":
						recipe.success_rate = field.value.asInteger();
						break;
					case "mp_consume": {
						recipe.mp_consume = field.value.asInteger();
						break;
					}
					case "item_id": {
						recipe.item_id = field.value.asInteger();
						break;
					}
					case "catalyst": {//not use
						if(field.value.asArray().size() > 0)
							System.out.println(recipe.name);
						break;
					}
					case "level": {
						recipe.level = field.value.asInteger();
						break;
					}
					case "npc_fee": {
						field.value.asArray().forEach(value -> {
							recipe.npc_fee.add(item(value));
						});
						break;
					}
					case "material": {
						field.value.asArray().forEach(value -> {
							recipe.material.add(item(value));
						});
						break;
					}
					case "product": {
						field.value.asArray().forEach(value -> {
							recipe.product.add(item(value));
						});
						break;
					}
					default:
						throw new RuntimeException("unknown field " + key);
					}
				});
				
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
