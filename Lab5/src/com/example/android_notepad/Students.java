package com.example.android_notepad;

public class Students {
		int personId;
		String name;
		String faculty;
		int number;
		String address;

		@Override
		public String toString() {
			return "Name: " + this.name + " Faculty:" + this.faculty + " Phone:" + this.number + " Address:" + this.address;
		}
}
