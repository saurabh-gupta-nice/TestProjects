package com.test;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Model;
import com.util.CustomDeserializer;
import com.util.JsonParser;

public class TestParser {

	private static BufferedReader br;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			br = new BufferedReader(new FileReader("employee.txt"));
			String s = new String();
			StringBuilder response = new StringBuilder();
			while((s = br.readLine()) != null) {
				response.append(s);
			}
			
			//Method-1
			JsonParser jsonParser = new JsonParser();
			jsonParser.parse(response.toString());
			
			//Method-2 : Overriding deserializer
			
			Gson gson = new GsonBuilder().registerTypeAdapter(Model.class, new CustomDeserializer()).create();
			Model model = gson.fromJson(response.toString(), Model.class);
			System.out.println("Deserialized: " + model.getKeyValue().toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
