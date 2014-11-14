package com.example.android_notepad;

import java.util.ArrayList;
import com.example.android_notepad.BoundService.MyBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.os.Bundle;
import android.os.IBinder;
import static com.example.android_notepad.MySqliteHelper.*;
import static com.example.android_notepad.Contstants.*;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button buttonQuery;
	private Button buttonUpdate;
	private ListView listView;

	public ArrayList<Students> array;
	private ArrayAdapter<Students> adapter;
	
	private MySqliteHelper helper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.e("OnCreate","Create");
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new MySqliteHelper(this);
        
        buttonQuery = (Button)findViewById(R.id.buttonQuery);
        buttonQuery.setOnClickListener(this);   
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate); 
        buttonUpdate.setOnClickListener(this);
        
        array = new ArrayList<Students>();
        adapter = new ArrayAdapter<Students>(this, android.R.layout.simple_list_item_1, array);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);    
    }
	
	protected void onStart(){
		super.onStart();
		Log.e("onStart","Update Activity");
		Intent serviceIntent = new Intent(this, BoundService.class);
		bindService(serviceIntent, connection, BIND_AUTO_CREATE);
		fillData();
		Toast.makeText(this, "List Updated", 10).show();
	}
	
	private void fillData() {
		Log.e("FillData","Start fill data is List");
		
		ContentResolver resolver = getContentResolver();
		array.clear();
		String columns[] = new String[] {COLUMN_PERSON_ID, COLUMN_NAME, COLUMN_FACULTY, COLUMN_NUMBER, COLUMN_ADDRESS};
		Cursor cursor = resolver.query(CONTENT_URI_STUDENTS, columns, null, null,null);
		Log.e("FillData","All column iterate");
		if(!cursor.isAfterLast()) {
			cursor.moveToFirst();
			while(!cursor.isAfterLast()) {
				Students p = new Students();
				p.personId = cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_ID));
				p.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
				p.faculty = cursor.getString(cursor.getColumnIndex(COLUMN_FACULTY));
				p.number = cursor.getInt(cursor.getColumnIndex(COLUMN_NUMBER));
				p.address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));

				array.add(p);
				cursor.moveToNext();
			}
		}
		
		cursor.close();
		adapter.notifyDataSetChanged();		
	}
	
	BoundService myservice = null;
	ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d("ServiceConnection", "onServiceDisconnected");
			myservice = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
			Log.d("ServiceConnection", "onServiceConnected");
			
			MyBinder myBinder = (MyBinder) serviceBinder;
			myservice = myBinder.getInstance();
			
		}
	};
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonQuery){
			Log.d("onClick", "Button Query clicked");
			Intent intent = new Intent(MainActivity.this, EditActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.buttonUpdate){
			Log.d("onClick", "Button Update clicked");
				myservice.startOperation();
			}
	
	}
}