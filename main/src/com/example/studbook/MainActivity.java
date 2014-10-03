package com.example.studbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{
	ListView list;
	Button addBtn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addBtn = (Button) findViewById(R.id.addBtn);
		addBtn.setOnClickListener(this);
		
		list = (ListView) findViewById(R.id.list);
		
		Intent intent = getIntent();
		String gName = intent.getStringExtra("name");
		String gEmail = intent.getStringExtra("email");
		String gFaculty = intent.getStringExtra("faculty");
		String gPhone = intent.getStringExtra("phone");
		
		final ArrayList<String> contacts = new ArrayList<String>();
		final ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, contacts);
		list.setAdapter(adapter);
		
	}
	@Override
		public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addBtn:
			Intent intent = new Intent(this, AddForm.class);
			startActivity(intent);
		break;
		default:
		break;
		}
	}
	
}
