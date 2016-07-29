package com.test;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
			JsonParser jsonParser = new JsonParser();
			jsonParser.parse(response.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
