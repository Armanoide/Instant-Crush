package com.instantcrush.sql;

import java.util.ArrayList;

import com.instantcrush.sql.object.ASqlObject;

public interface IORM {

		
	/**
	 * </h1><b>Create</b></h1>
	 * <p>Use to insert a field in the Database .If the table doesn't exist, it will be create</p>
	 * @author Norbert Billa
	 * @param cl  (The class that the table is defined. It has to be an heir of the abstract class AsqlObject)
	 * @param ASqlObject object (only object heir form IObject can be use in Database)
	 * @see ASqlObject
	 * */	
	public void		 			create(Class<?> cl, ASqlObject object);

	/**
	 * </h1><b>Read</b></h1>
	 * <p>Read a table with the option to order by a filed. If the table doesn't exist, it will be create</p>
	 * <p>{@code read(Exemple.class, "id_user")}</p>
	 * <p>{@code read(Exemple.class, null)}</p>
	 * @author Norbert Billa
	 * @param cl  (The class that the table is defined. It has to be an heir of the abstract class AsqlObject)
	 * @param field_order (the field which the request will be ordered)
	 * @see ASqlObject
	 * */	
	public ArrayList<Object>	read(Class<?> cl, String field_order);

	
	/**
	 * </h1><b>Read</b></h1>
	 * <p>Read a table with a where clause. The table can be order by a field. If the table doesn't exist, it will be create</p>
	 * @author Norbert Billa
	 * @param cl  (The class that the table is defined. It has to be an heir of the abstract class AsqlObject)
	 * @param field_order (the field which the request will be ordered)
	 * @param key_where (the index of the field)
	 * @param value (the value of the field)
	 * @see ASqlObject
	 * */		
	public ArrayList<Object>	read(Class<?> cl, String field_order, String key_where, String value_where);
	

	/**
	 * </h1><b>Update</b></h1>
	 * <p>Update a field in the table. If the table doesn't exist, it will be create</p>
	 * @author Norbert Billa
	 * @param cl  (The class that the table is defined. It has to be an heir of the abstract class AsqlObject)
	 * @param ASqlObject object (only object heir form IObject can be use in Database)
	 * @see ASqlObject
	 * */		
	public void 				update(Class<?> cl, ASqlObject object);
	
	
	
	/**
	 * </h1><b>Delete</b></h1>
	 * <p>Delete a field in the table. If the table doesn't exist, it will be create</p>
	 * @author Norbert Billa
	 * @param cl  (The class that the table is defined. It has to be an heir of the abstract class AsqlObject)
	 * @param ASqlObject object (only object heir form IObject can be use in Database)
	 * @see ASqlObject
	 * */		
	public void		 			delete(Class<?> cl, ASqlObject object);

	
	/**
	 * </h1><b>Drop</b></h1>
	 * <p>Delete a table  if the table exist</p>
	 * @author Norbert Billa
	 * @param cl  (The class that the table is defined. It has to be an heir of the abstract class AsqlObject)
	 * */		
	public void 				drop(Class<?> cl);
	
	public static final String	GET_ALL 		= "SELECT * FROM ";
	public static final String 	SET_ORDER_BY  	= " ORDER BY ";
	public static final String 	SET_WHERE  		= " WHERE "; 
	public static final String 	RECORD  		= "record";
	public static final String 	CREATE_TABLE  	= "CREATE TABLE ";
}
