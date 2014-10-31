package com.example.android_notepad;

import static com.example.android_notepad.Contstants.COLUMN_ADDRESS;
import static com.example.android_notepad.Contstants.COLUMN_FACULTY;
import static com.example.android_notepad.Contstants.COLUMN_NAME;
import static com.example.android_notepad.Contstants.COLUMN_NUMBER;
import static com.example.android_notepad.Contstants.CONTENT_URI_STUDENTS;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class BoundService extends Service {
	String sName = "Bot";
	String sFaculty = "MMF";
	String sNumber = "255.255.255.0";
	String sAddress = "MTZ";

	class MyBinder extends Binder {
		public BoundService getInstance() {
			return BoundService.this;
		}
	}
	public void startOperation() {
			ContentResolver resolver = getContentResolver();
			ContentValues values = new ContentValues();
			
			values.put(COLUMN_NAME, sName);
			values.put(COLUMN_FACULTY, sFaculty);
			values.put(COLUMN_NUMBER, sNumber);
			values.put(COLUMN_ADDRESS, sAddress);
			resolver.insert(CONTENT_URI_STUDENTS, values);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("Service", "onCreate()");
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		Log.e("Service", "onBind()");
		MyBinder binder = new MyBinder();
		return binder;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("Service", "onDestroy()");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("Service", "onUnbind()");

		return super.onUnbind(intent);
	}
}
