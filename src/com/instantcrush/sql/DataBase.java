package com.instantcrush.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper implements IConstantsDataBase {

	private static DataBase mDatabase;
	private static SQLiteDatabase db;


	public DataBase(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

	public static DataBase getInstance(Context ctx) {
		if (mDatabase == null) {
			mDatabase = new DataBase(ctx.getApplicationContext());
			db = mDatabase.getWritableDatabase();
		}
		return mDatabase;
	}


	public boolean isTableExists(String tableName) {
		Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
		if(cursor!= null) {
			if(cursor.getCount() > 0) {
				cursor.close();
				return true;
			}
			cursor.close();
		}
		return false;
	}

	@Override
	public void onCreate(SQLiteDatabase db) 									{ DataBase.db = db; 						}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)	{ onCreate(db);								}

	public Cursor read(String selectQuery) 										{ return db.rawQuery(selectQuery, null); 	}
	public void create(String createQuery) 										{ db.execSQL(createQuery); 					}
	public void insert(String table, ContentValues values) 						{ db.insert(table, null, values);			}
	public void	drop(String table)												{ db.execSQL("DROP TABLE IF EXISTS " + table); }
	public void delete(String table, String id) 								{ db.delete(table, "id = ?", new String[] { id }); }
	public void update(String table, ContentValues values) 						{ db.update(table, values, "id = ?", new String[] { values.getAsString("id") });	}



}
