package com.example.android_notepad;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import static com.example.android_notepad.Contstants.*;

public class StudentContentProvider extends ContentProvider{
	private static final int MATCHER_CODE = 1;
	static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
		matcher.addURI(AUTHORITY, "Students", MATCHER_CODE);
	}
	
	MySqliteHelper helper;
	private SQLiteDatabase db;
	
	@Override
	public boolean onCreate() {
		helper = new MySqliteHelper(getContext());
		db = helper.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
		Cursor cursor = null;
		
		int code = matcher.match(uri);
		if (code == MATCHER_CODE)
			cursor = db.query(TABLE_STUDENTS, projection, selection, selectionArgs, null, null, orderBy);
		
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		int code = matcher.match(uri);
		if (code == MATCHER_CODE)
			db.insert(TABLE_STUDENTS, null, values);
		
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
