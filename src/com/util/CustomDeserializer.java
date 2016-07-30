package com.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.model.Model;

public class CustomDeserializer implements JsonDeserializer<Model> {

	@Override
	public Model deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		// TODO Auto-generated method stub
		HashMap<String, List<Object>> json = parseJson(arg0, new HashMap<String,List<Object>>());
		System.out.println("Deserializer : "+json.toString());
		Model model = new Model();
		model.setKeys(json.keySet());
		model.setKeyValue(json.entrySet());
		model.setTotal(json.size());
		return model;
	}
	
	private HashMap<String,List<Object>> parseJson(JsonElement jsObject, HashMap<String,List<Object>> model) {
		// TODO Auto-generated method stub
		Set<Entry<String, JsonElement>> entries = jsObject.getAsJsonObject().entrySet();
		Iterator<Entry<String,JsonElement>> entriesIterator = entries.iterator();
		while(entriesIterator.hasNext()){
			Entry<String,JsonElement> entry = entriesIterator.next();
			String key = entry.getKey();
			JsonElement value = entry.getValue();
			if(value.isJsonObject()) {
				parseJson(entry.getValue(), model);
			} else if(value.isJsonPrimitive()) {
				if(model.keySet().contains(key)) {
					List<Object> rows = model.get(key);
					rows.add(value.getAsString());
					model.put(key, rows);
				} else {
					List<Object> rows = new ArrayList<>();
					rows.add(value.getAsString());
					model.put(key, rows);
					
				}
				
			} else if(value.isJsonArray()) {
				JsonArray array = value.getAsJsonArray();
				Iterator<JsonElement> arrayIterator = array.iterator();
				while(arrayIterator.hasNext()) {
					JsonElement innerProperties = arrayIterator.next();
					if(innerProperties.isJsonObject()) {
						parseJson(innerProperties, model);
					} else if(innerProperties.isJsonPrimitive()){
						if(model.keySet().contains(key)) {
							List<Object> rows = model.get(key);
							rows.add(innerProperties.getAsString());
							model.put(key, rows);
						} else {
							List<Object> rows = new ArrayList<>();
							rows.add(innerProperties.getAsString());
							model.put(key, rows);
							
						}
					}
					
				}
				
				
			} else if(value.isJsonNull()) {
				System.out.println("value against key " + key + "is null");
			}
		}
		return model;
	}

}
