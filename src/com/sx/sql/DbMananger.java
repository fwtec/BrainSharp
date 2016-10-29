package com.sx.sql;

import java.util.ArrayList;
import java.util.List;

import com.sx.model.Canginfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbMananger extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "Njjzw.db";
	private final static int DATABASE_VERSION = 1;
	// public final static String STATE = "state";
	// private final static String TABLE_POINTLOG = "table_pointLog";
	// public final static String AWARD_DATE= "award_date";
	private final static String CANG_TABLE = "tb_cang";
	private final static String APPTYPE = "apptype";
	private final static String APPCONTENT = "appcontent";
	private final static String APPINDEX = "appindex";

	// private final static String QAINDEX = "qaIndex";

	public DbMananger(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// String sql1 =
		// "CREATE TABLE "+CANG_TABLE+" ("+APPTYPE+" INTEGER, "+APPCONTENT+" INTEGER, "+QAINDEX+" INTEGER);";
		// String sql2 =
		// "CREATE TABLE "+TABLE_POINTLOG+" ( "+AWARD_DATE+" varchar(20));";
		db.execSQL("CREATE TABLE IF NOT EXISTS "+CANG_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, apptype VARCHAR, appcontent VARCHAR,appindex INTEGER)");
		// db.execSQL(sql1);
		// db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// String sql1 = "DROP TABLE IF EXISTS " + TABLE_POINTLOG;
		String sql2 = "DROP TABLE IF EXISTS " + CANG_TABLE;
		db.execSQL(sql2);
		// db.execSQL(sql2);
		onCreate(db);
	}

	// public long insertPointLog(String date){
	// SQLiteDatabase db = this.getWritableDatabase();
	// /* ContentValues */
	// ContentValues cv = new ContentValues();
	// cv.put(AWARD_DATE, date);
	// long row = db.insert(TABLE_POINTLOG, null, cv);
	// return row;
	// }

	// public boolean queryLog(String date){
	// boolean exsits = false;
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor =
	// db.rawQuery("select * from "+TABLE_POINTLOG+" where "+AWARD_DATE+"= ?",
	// new String[]{""+date+""});
	// while(cursor.moveToNext()){
	// exsits = true;
	// break;
	// }
	// cursor.close();
	// return exsits;
	// }

	// public Cursor getUserState(int level){
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor =
	// db.rawQuery("select "+LEVEL+","+POINT+","+QAINDEX+" from "+USER_STATE+" where "+LEVEL+"=?",new
	// String[]{String.valueOf(level)});
	// return cursor;
	// }

	public void insertState(Canginfo cang) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(APPTYPE, cang.getApptype());
		cv.put(APPCONTENT, cang.getAppcontent());
		cv.put(APPINDEX, cang.getAppindex());
		db.insert(CANG_TABLE, null, cv);
	}

	public void Delete() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM students WHERE name='张三'");
		// Log.d("msg","张三的信息已经被删除");
		// Toast.makeText(this, "张三的信息已经被删除", Toast.LENGTH_SHORT).show();
	}

	public List<Canginfo> getScrollData() {
		List<Canginfo> persons = new ArrayList<Canginfo>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from tb_cang",null);
		while (cursor.moveToNext()) {
			Canginfo cang=new Canginfo();
			cang.setId(cursor.getInt(0));
			cang.setApptype(cursor.getString(1));
			cang.setAppcontent(cursor.getString(2));
			cang.setAppindex(cursor.getString(3));
			persons.add(cang);
		}
		return persons;
	}

	public long getCount(String apptype,String appindex) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from tb_cang where apptype=? and appindex=?", new String[] { String.valueOf(apptype),  
				String.valueOf(appindex)});
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
	}

	// public void updateState(QA qa){
	// SQLiteDatabase db = this.getWritableDatabase();
	// String sql =
	// "update "+USER_STATE+" set "+POINT+"="+qa.getPoint()+" , "+QAINDEX+"="+qa.getIndex()+" where "+LEVEL+"="+qa.getLevel();
	// db.execSQL(sql);
	// }

	// public void resetState(){
	// SQLiteDatabase db = this.getWritableDatabase();
	// String sql = "delete from "+USER_STATE;
	// db.execSQL(sql);
	// }

	// public Cursor select() {
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db
	// .query(USER_STATE, null, null, null, null, null,null);
	// return cursor;
	// }
}
