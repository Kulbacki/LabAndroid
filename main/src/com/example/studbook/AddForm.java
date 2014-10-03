package com.example.studbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddForm extends Activity {
	EditText name;
	EditText email;
	EditText faculty;
	EditText phone;
	Button saveBtn;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		name = (EditText) findViewById(R.id.fullName);
		email = (EditText) findViewById(R.id.email);
		faculty = (EditText) findViewById(R.id.faculty);
		phone = (EditText) findViewById(R.id.phone);
		
		saveBtn = (Button) findViewById(R.id.saveBtn);
		final Intent intent = new Intent(this, MainActivity.class);
		
	OnClickListener SaveBtn = new OnClickListener() {
		@Override
			public void onClick(View v) {
				intent.putExtra("name", name.getText().toString());
				intent.putExtra("email", email.getText().toString());
				intent.putExtra("faculty", faculty.getText().toString());
				intent.putExtra("phone", phone.getText().toString());
				startActivity(intent);
				}
		};
		saveBtn.setOnClickListener(SaveBtn);
	}
	}