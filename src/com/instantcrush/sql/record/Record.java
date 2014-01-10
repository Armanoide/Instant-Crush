package com.instantcrush.sql.record;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.Set;

import android.content.ContentValues;

public class Record implements IRecord {

	ContentValues 		values = null;
	
	public Record() {
		values = new ContentValues();
	}
	
	@Override
	public void add(String index, String type)
	{
		values.put(index, type);
	}
	
	@Override
	public String getRecord()
	{
		Set<String> keys = values.keySet();
		String record = new String();
		
		for (Iterator<String> i = keys.iterator(); i.hasNext();) {
			String key = i.next();
			record += key + " " + values.getAsString(key);
			record += (i.hasNext() == true) ? ", " : "";
		}
		return record;
	}

	@Override
	public ArrayList<String>  getAllKey() {
		ArrayList<String> record_key = new ArrayList<String>();
		for (String key : values.keySet()) {
			record_key.add(key);
		}
		return record_key;
	}

	public void clear() { values.clear();}

}
