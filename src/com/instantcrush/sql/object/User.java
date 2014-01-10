package com.instantcrush.sql.object;

import java.util.ArrayList;

import com.instantcrush.sql.record.Record;

public class User extends ASqlObject{

	String email = null;
	int		id;

	public User() {}
	
	public User(String id, String email)
	{
		if (id != null) this.id = Integer.valueOf(id);
		this.email 	= email;
	}
	
	/**
	 * @author Billa Norbert
	 * <p>The method getter and setter must be like set_ and get_ </p>
	 * */
	
	public void set_email(String mail) {this.email = mail;}
	public void set_id(String id) {this.id = Integer.valueOf(id);}

	public String get_email() {return email;}
	public String get_id() {return String.valueOf(id);}

	
	@Override
	public String record() {
		record.add("id", Record.TYPE_BIGINT_INDEX);
		record.add("email", Record.TYPE_VARCHAR);
		return record.getRecord();
	}

	@Override
	public ArrayList<ASqlObject> defaut_objects() {
		ArrayList<ASqlObject> default_objs = new  ArrayList<ASqlObject>();
		default_objs.add(new User(null, "instant-crush@test.com"));
		return default_objs;
	}

}
