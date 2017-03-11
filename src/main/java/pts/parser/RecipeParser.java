package pts.parser;

import java.io.IOException;
import java.nio.file.Paths;

import xml.Item;
import xml.RecipeData;
import xml.RecipeData.Recipe;

public class RecipeParser extends AbstractParser<RecipeData> {

	@Override
	public RecipeData parse() {
		RecipeData data = new RecipeData();
		try {
			parse(Paths.get("data", "recipe.txt")).forEach(record -> {
				final Recipe recipe = new Recipe();
				data.recipe.add(recipe);
				recipe.name = record.get(0).asString();
				recipe.id = record.get(1).asInteger();
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
							recipe.npc_fee.add(new Item(value.get(0).asString(), value.get(1).asInteger()));
						});
						break;
					}
					case "material": {
						field.value.asArray().forEach(value -> {
							recipe.material.add(new Item(value.get(0).asString(), value.get(1).asInteger()));
						});
						break;
					}
					case "product": {
						field.value.asArray().forEach(value -> {
							recipe.product.add(new Item(value.get(0).asString(), value.get(1).asInteger()));
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
