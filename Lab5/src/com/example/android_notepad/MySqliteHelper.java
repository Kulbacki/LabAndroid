package com.example.android_notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.android_notepad.Contstants.*;

public class MySqliteHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "students.db";
	private static final int VERSION = 1;

	public MySqliteHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_STUDENTS + 
				   " (" + COLUMN_PERSON_ID + " integer primary key autoincrement,"
				   		+ COLUMN_NAME + " text,"
				   		+ COLUMN_FACULTY +"  text,"
				   		+ COLUMN_NUMBER + " integer,"
				   		+ COLUMN_ADDRESS + " text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
