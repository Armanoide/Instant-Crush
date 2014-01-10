package com.instantcrush.sql.object;

import java.util.ArrayList;

import com.instantcrush.sql.record.Record;


public abstract class ASqlObject {

	protected Record record = null;

	public ASqlObject() 						{record = new Record();}

	/**
	 *@author Billa Norbert
	 *<h1><b>Get Record</b></h1> 
	 *<p>In this method, set the structure of the table with the index and his type</p>
	 *<p>{@code Exemple : record.add(new Exemple("id", Record.TYPE_BIGINT_INDEX))}</p>
	 */

	public Record getRecord()					{return record;}
	public abstract String	 					record();
	
	/**
	 *@author Billa Norbert
	 *<h1><b>Default Object</b></h1> 
	 *<p>In this method, set all the default field that you want to have in the table</p>
	 *<p>This method is call only on the creation of the table in the database</p>
	 */
	public abstract ArrayList<ASqlObject> 		defaut_objects();

	public ArrayList<String> getAllKeyValue() 	
	{
		this.record.clear();
		record();
		return record.getAllKey();
	}

}
