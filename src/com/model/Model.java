package com.model;

import java.util.List;

import java.util.Map.Entry;
import java.util.Set;

public class Model {
	
	Set<String> keys;
	Set<Entry<String, List<Object>>> keyValue;
	int total;
	
	public Set<String> getKeys() {
		return keys;
	}
	public void setKeys(Set<String> keys) {
		this.keys = keys;
		
	}
	public Set<Entry<String, List<Object>>> getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(Set<Entry<String, List<Object>>> keyValue) {
		this.keyValue = keyValue;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
