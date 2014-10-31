package com.example.studbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddForm extends Activity {
	private EditText FullName;
	private ListView Phone;
	private EditText Faculty;
	private EditText Email;
	private Long mRowId;
	private Studbase mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDbHelper = new Studbase(this);

		setContentView(R.layout.activity_add);

		FullName = (EditText) findViewById(R.id.fullName);
		Phone = (ListView) findViewById(R.id.phone);
		Faculty = (EditText) findViewById(R.id.faculty);
		Email = (EditText) findViewById(R.id.email);

		Button submitBtn = (Button) findViewById(R.id.saveBtn);
		mRowId = null;
		Bundle extras = getIntent().getExtras();
		
		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState
						.getSerializable(Studbase.COLUMN_ID);
		if (extras != null) {
			mRowId = extras.getLong(Studbase.COLUMN_ID);
		}

		populateFields();

		submitBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (TextUtils.isEmpty(FullName.getText().toString())) {
					Toast.makeText(AddForm.this, "Данные не введены",
							Toast.LENGTH_LONG).show();
				} else {
					saveState();
					setResult(RESULT_OK);
					finish();
				}
			}
		});
	}

	private void populateFields() {
		if (mRowId != null) {
			Cursor stud = mDbHelper.getStudbase(mRowId);
			startManagingCursor(stud);
			String category = stud.getString(stud.getColumnIndexOrThrow(Studbase.COLUMN_PHONE));

			for (int i = 0; i < Phone.getCount(); i++) {

				String s = (String) Phone.getItemAtPosition(i);
				Log.e(null, s + " " + category);
				if (s.equalsIgnoreCase(category)) {
					Phone.setSelection(i);
				}
			}

			FullName.setText(stud.getString(stud
					.getColumnIndexOrThrow(Studbase.COLUMN_FULLNAME)));
			Faculty.setText(stud.getString(stud
					.getColumnIndexOrThrow(Studbase.COLUMN_FACULTY)));
			stud.close();
			Email.setText(stud.getString(stud
					.getColumnIndexOrThrow(Studbase.COLUMN_EMAIL)));
			stud.close();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//saveState();
		//outState.putSerializable(ToDoDatabase.COLUMN_ID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		//saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void saveState() {		
		String fullname = FullName.getText().toString();
		String email = Email.getText().toString();
		String faculty = Faculty.getText().toString();
		String phone = (String) Phone.getSelectedItem();	

		if (faculty.length() == 0 && fullname.length() == 0) {
			return;
		}

		if (mRowId == null) {
			long id = mDbHelper.createNewStudbase(fullname, email, faculty, phone);
			if (id > 0) {
				mRowId = id;
			}
		} else {
			mDbHelper.updateStudbasetable(mRowId, fullname, email, faculty, phone);
		}
	}
	}