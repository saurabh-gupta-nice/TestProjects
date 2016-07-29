package com.util;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.model.Model;

public class JsonParser {
	
	public Model parse(String response) throws FileNotFoundException {
		//FileInputStream fin = new FileInputStream("employee.txt"); 
		//InputStreamReader isr = new InputStreamReader(fin);
		//JsonReader jsonReader = new JsonReader(response);
		JsonElement jsObject = new com.google.gson.JsonParser().parse(response);
		System.out.println(jsObject.isJsonObject() + ", " + jsObject.isJsonArray());
		System.out.println(jsObject.toString());
		
		HashMap<String, List<Object>> json = parseJson(jsObject, new HashMap<String,List<Object>>());
		System.out.println(json.toString());
		Model model = new Model();
		model.setKeys(json.keySet());
		model.setKeyValue(json.entrySet());
		model.setTotal(json.size());
		return null;
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
