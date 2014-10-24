package com.example.studbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		Button submit = (Button) findViewById(R.id.submit);
		final Intent intent = new Intent(this, MainActivity.class);
		
	OnClickListener Submit = new OnClickListener() {
		@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		};
		submit.setOnClickListener(Submit);
	}
}