package com.example.android_notepad;

import android.net.Uri;

public class Contstants {

	// constants for content provider
	// content://com.example.android_notepad
	public static final String AUTHORITY = "com.example.android_notepad";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

	// content://com.example.android_notepad/Students
	public static final String PATH = "Students";
	public static final Uri CONTENT_URI_STUDENTS = Uri.parse("content://" + AUTHORITY + "/" + PATH);
	
	// constants for database student
	public static final String TABLE_STUDENTS = "students";
	public static final String COLUMN_PERSON_ID = "personId";
	public static final String COLUMN_NAME = "personName";
	public static final String COLUMN_FACULTY = "personFaculty";
	public static final String COLUMN_NUMBER = "personNumber";
	public static final String COLUMN_ADDRESS = "personAddress";
}