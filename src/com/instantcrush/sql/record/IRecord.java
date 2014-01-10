package com.instantcrush.sql.record;

import java.util.ArrayList;

public interface IRecord {

	/**
	 * @author Billa Norbert
	 * <p>To see more about type follow the link {@link http://www.sqlite.org/datatype3.html}.</p>
	 * <p>Value with TYPE_INDEX is set with "PRIMARY KEY AUTOINCREMENT".</p>
	 * */

	public static final String TYPE_INT_INDEX 		= "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String TYPE_BIGINT_INDEX 	= "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String TYPE_VARCHAR			= "TEXT";
	public static final String TYPE_INT				= "INTEGER";
	public static final String TYPE_BIGINT			= "INTEGER";
	public static final String TYPE_TEXT			= "TEXT";
	public static final String TYPE_BOOLEAN			= "NUMERIC";
	public static final String TYPE_DATE			= "NUMERIC";
	public static final String TYPE_DOUBLE			= "TEXT";
	
	/**
	 * </h1><b>Add index</b></h1>
	 * <p>method to add index in to the Table. Use static variable of Record class for define the type</p>
	 * <p>Exemple : {@code add("id_user", Record.TYPE_INT_INDEX)}</p>
	 * @author Norbert Billa
	 * @param index
	 * @param type
	 * */
	
	public void add(String index, String type);

	
	/**
	 * </h1><b>Get All Index</b></h1>
	 * <p>The method return all the index of a table in a ArrayList. The order of index depend</p>
	 * <p>on the index type first, first all the integer and numeric type then string type</p>
	 * @author Norbert Billa
	 * */
	public ArrayList<String>  getAllKey();
	
	
	/**
	 * </h1><b>Get Record</b></h1>
	 * <p>The method return record of the table. It's used to create a table in database.</p>
	 * @author Norbert Billa
	 * */
	public String getRecord();

}
