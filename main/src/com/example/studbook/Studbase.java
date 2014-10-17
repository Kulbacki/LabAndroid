package com.example.studbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Studbase extends SQLiteOpenHelper {
	
	public Studbase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static final String DATABASE_NAME = "studbase.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "studbasetable";

	/* поля таблицы */
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_FULLNAME = "fullname";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_FACULTY = "faculty";
	public static final String COLUMN_PHONE = "phone";

	/* запрос на создание базы данных */
	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_FULLNAME
			+ " text not null, " + COLUMN_EMAIL
			+ " text not null, " + COLUMN_FACULTY + " text not null,"
			+ COLUMN_PHONE + " text not null" + ");";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(Studbase.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS studbasetable");
		onCreate(db);
	}
		/* Создаёт новый элемент списка дел. Если создан успешно - возвращается id студента, иначе -1 */
		public long createNewStudbase(String fullname, String email, String faculty, String phone) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues initialValues = createContentValues(fullname, email, faculty, phone);
			
			long row = db.insert(DATABASE_TABLE, null, initialValues);
			db.close();

			return row;
		}
		
		/* Обновляет список */
		public boolean updateStudbasetable(long rowId, String fullname, String email, String faculty, String phone) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues updateValues = createContentValues(fullname, email, faculty, phone);

			return db.update(DATABASE_TABLE, updateValues, COLUMN_ID + "=" + rowId,
					null) > 0;
		}

		public void deleteStudbase(long rowId) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(DATABASE_TABLE, COLUMN_ID + "=" + rowId, null);
			db.close();
		}

		/* Возвращает курсор со всеми элементами списка дел */
		public Cursor getAllStudbasetable() {
			SQLiteDatabase db = this.getWritableDatabase();
			return db.query(DATABASE_TABLE, new String[] { COLUMN_ID ,COLUMN_FULLNAME,
					COLUMN_EMAIL, COLUMN_FACULTY, COLUMN_PHONE }, null,
					null, null, null, null);
		}

		/* Возвращает курсор с указанной записи */
		public Cursor getStudbase(long rowId) throws SQLException {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor mCursor = db.query(true, DATABASE_TABLE,
					new String[] { COLUMN_ID, COLUMN_FULLNAME, COLUMN_EMAIL, COLUMN_FACULTY, COLUMN_PHONE }, COLUMN_ID + "=" + rowId, null,
					null, null, null, null);
			if (mCursor != null) {
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		/* Создаёт пару ключ-значение и записывает в базу */
		private ContentValues createContentValues(String fullname, String email, String faculty, String phone) {
			ContentValues values = new ContentValues();
			values.put(COLUMN_FULLNAME, fullname);
			values.put(COLUMN_EMAIL, email);
			values.put(COLUMN_FACULTY, faculty);
			values.put(COLUMN_PHONE, phone);
			return values;
		}
	}
