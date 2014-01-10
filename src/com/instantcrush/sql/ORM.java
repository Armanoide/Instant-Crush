package com.instantcrush.sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.instantcrush.sql.object.ASqlObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class ORM implements IORM {

	DataBase 	db;

	/**
	 * </h1><b>Constructor of Instant Crush ORM </b></h1>
	 * <p>the Instant Crush ORM implement a CRUD (create, read, update and delete) method for each table</p>
	 * <p>It use the reflection and AsqlObject class to define your Object. The Object is a field of the table</p>
	 * <p>the Instant Crush ORM is use only for android sqlite.</p>
	 * </p>For all errors during runtime check out the logCat in error section</p>
	 * @author Norbert Billa
	 * @param Context context  (context need to get instance of Database)
	 * @see IORM
	 * */	
	public ORM(Context context) {	
		db = DataBase.getInstance(context);
	}

	private void create_a_table(Class<?> cl, ASqlObject object)
	{
		String table = cl.getSimpleName();
		if (db.isTableExists(table) == false)
		{
			ArrayList<ASqlObject> defaults_objs = object.defaut_objects();
			db.create(CREATE_TABLE + table + "(" + getObjectRecord(object) +")");
			for (int i = 0; defaults_objs != null && i < defaults_objs.size(); i++) 
			{
				create(cl, defaults_objs.get(i));
			}
		}
	}

	@Override
	public void drop(Class<?> cl) { db.drop(cl.getSimpleName()); }

	@Override
	public void create(Class<?> cl, ASqlObject object)
	{
		String table = cl.getSimpleName();
		String method_name = null;
		try {
			create_a_table(cl, object);
			ContentValues values = new ContentValues();
			ArrayList<String> keys = object.getAllKeyValue();
			for (int i = 0; i < keys.size(); i++) 
			{

				if(keys.get(i).compareTo("id") != 0)
				{
					method_name = keys.get(i);
					Method method = cl.getMethod("get_" + keys.get(i), new Class[] {});	
					String res 	= (String) method.invoke(object, new Object[]{});
					values.put(keys.get(i), (res == null) ? "" : res );
				}
			}
			db.insert(table, values);
		}
		catch (IllegalArgumentException e) 			{e.printStackTrace();Log.e("0", "0");} 
		catch (IllegalAccessException e) 			{Log.e("ORM-ANDROID", "Error durring acess to the method " + method_name);} 
		catch (InvocationTargetException e) 		{Log.e("ORM-ANDROID", "Error during the invocation");}
		catch (NoSuchMethodException e) 			{Log.e("ORM-ANDROID", "the method 'void get_" + method_name + "();' is not found in the class " + cl.getSimpleName());}
	}

	private ArrayList<Object> reflexion(Class<?> cl, Cursor cursor, String table)
	{
		String method_name = null;
		ArrayList<Object>list = new ArrayList<Object>();
		try {
			if (cursor.moveToFirst()) {
				do {
					Object instance = cl.newInstance();
					for (int i = 0; i < cursor.getColumnCount(); i++)
					{
						method_name = "'void set_" + cursor.getColumnName(i) + "();'";
						Method method = cl.getMethod("set_" + cursor.getColumnName(i), new Class[] { String.class });	
						method.invoke(instance, new Object[]{ cursor.getString(i) });
					}
					list.add(instance);
				} while (cursor.moveToNext());
			}
		}
		catch (NoSuchMethodException e) 			{e.printStackTrace();}
		catch (IllegalAccessException e) 			{Log.e("ORM-ANDROID", "Error durring acess to the method " + method_name);} 
		catch (IllegalArgumentException e) 			{e.printStackTrace();}
		catch (InvocationTargetException e) 		{e.printStackTrace();} 
		catch (InstantiationException e) 			{e.printStackTrace();}
		return list;
	}

	@Override
	public ArrayList<Object> read(Class<?> cl, String field_order, String key_where, String value_where) {
		String table = cl.getSimpleName();
		try 
		{
			create_a_table(cl, (ASqlObject) cl.newInstance());
		} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		Cursor cursor = db.read(
				GET_ALL 
				+ table 
				+ ((key_where != null && value_where != null) ? SET_WHERE +  key_where  + " = " + "'" + value_where+ "'" : "")
				+ ((field_order != null)  ? SET_ORDER_BY  + field_order + " ASC": "") );
		return reflexion(cl, cursor, table);
	}

	@Override
	public ArrayList<Object> read(Class<?> cl, String field_order)
	{
		String method_name = null;
		String table = cl.getSimpleName();
		try 
		{
			create_a_table(cl, (ASqlObject) cl.newInstance());
		} 
		catch (InstantiationException e) 			{e.printStackTrace();} 
		catch (IllegalAccessException e) 			{Log.e("ORM-ANDROID", "Error durring acess to the method " + method_name);} 

		Cursor cursor = db.read(
				GET_ALL 
				+ table
				+ ((field_order != null) ? SET_ORDER_BY  + field_order + " ASC": "") );
		return reflexion(cl, cursor, table);
	}

	@Override
	public void update(Class<?> cl, ASqlObject object) {
		String table = cl.getSimpleName();
		String method_name = null;
		try {
			if (db.isTableExists(table) == false)
				db.create(CREATE_TABLE + table + "(" + getObjectRecord(object) +")");
			ContentValues values = new ContentValues();
			ArrayList<String> keys = object.getAllKeyValue();
			for (int i = 0; i < keys.size() ; i++){  
				Method method = cl.getMethod("get_" + keys.get(i), new Class[] {});	
				String res 	= (String) method.invoke(object, new Object[]{});
				values.put(keys.get(i), (res == null) ? "" : res );
			}
			db.update(table, values);
		}
		catch (IllegalArgumentException e) 			{e.printStackTrace();Log.e("0", "0");} 
		catch (IllegalAccessException e) 			{Log.e("ORM-ANDROID", "Error durring acess to the method " + method_name);} 
		catch (InvocationTargetException e) 		{Log.e("ORM-ANDROID", "Error during the invocation");}
		catch (NoSuchMethodException e) 			{Log.e("ORM-ANDROID", "the method 'void get_" + method_name + "();' is not found in the class " + cl.getSimpleName());}
	}

	@Override
	public void delete(Class<?> cl, ASqlObject object) {
		String table = cl.getSimpleName();
		String method_name = null;
		try{
			if (db.isTableExists(table) == false)
				db.create(CREATE_TABLE + table + "(" + getObjectRecord(object) +")");
			ArrayList<String> keys = object.getAllKeyValue();
			for (int i = 0; i < keys.size(); i++) 
			{
				if(keys.get(i).compareTo("id") == 0)
				{
					Method method = cl.getMethod("get_" + keys.get(i), new Class[] {});	
					String res 	= (String) method.invoke(object, new Object[]{});
					db.delete(table, res);
				}
			}
		}
		catch (IllegalArgumentException e) 			{e.printStackTrace();Log.e("0", "0");} 
		catch (IllegalAccessException e) 			{Log.e("ORM-ANDROID", "Error durring acess to the method " + method_name);} 
		catch (InvocationTargetException e) 		{Log.e("ORM-ANDROID", "Error during the invocation");}
		catch (NoSuchMethodException e) 			{Log.e("ORM-ANDROID", "the method 'void get_" + method_name + "();' is not found in the class " + cl.getSimpleName());}
	}


	private String getObjectRecord(Object object)
	{
		String method_name = RECORD;
		try {

			Method method;
			method = object.getClass().getMethod (RECORD, new Class[] {});
			String res 		= (String) method.invoke(object, new Object[]{ });
			if (res == null) Log.e("ORM-ANDROID", "Error with the method record in " + object.getClass().getSimpleName() + " check out that you return 'record.getRecord()'"); 
			return res;
		}
		catch (NoSuchMethodException e) 			{e.printStackTrace();}
		catch (IllegalAccessException e) 			{Log.e("ORM-ANDROID", "Error durring acess to the method " + method_name);} 
		catch (IllegalArgumentException e) 			{e.printStackTrace();}
		catch (InvocationTargetException e) 		{Log.e("ORM-ANDROID", "Error during the invocation");}
		return null;
	}
}
