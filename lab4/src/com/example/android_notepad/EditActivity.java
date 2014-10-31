package com.example.android_notepad;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static com.example.android_notepad.MySqliteHelper.*;
import static com.example.android_notepad.Contstants.*;

public class EditActivity extends Activity {
	
	 private EditText editName;
	 private EditText editFaculty;
	 private EditText editNumber;
	 private EditText editAddress;
	 private Button submitBtn;
	 private MySqliteHelper helper;
	
	 public void onCreate(Bundle savedInstanceState) {
		 Log.e("EditForm","Create");
	        super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_edit);
			helper = new MySqliteHelper(this);
			
	        editName = (EditText)  findViewById(R.id.editName);
	        editFaculty = (EditText)  findViewById(R.id.editFaculty);
	        editNumber = (EditText)  findViewById(R.id.editNumber);
	        editAddress = (EditText)  findViewById(R.id.editAddress);
	        submitBtn = (Button) findViewById(R.id.submitBtn);
	        
	        submitBtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
						Log.e("onClick","Send data");
						ContentResolver resolver = getContentResolver();
						ContentValues values = new ContentValues();
						
						values.put(COLUMN_NAME, editName.getText().toString());
						values.put(COLUMN_FACULTY, editFaculty.getText().toString());
						values.put(COLUMN_NUMBER, editNumber.getText().toString());
						values.put(COLUMN_ADDRESS, editAddress.getText().toString());
						
						//db.insert(TABLE_STUDENTS, null, values);
						resolver.insert(CONTENT_URI_STUDENTS, values);	
						//finish();
					}		
	        });	
	 }
}