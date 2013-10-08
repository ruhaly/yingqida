package com.ruhaly.tipdemo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.EditText;

import com.ruhaly.tipdemo.MainActivity;

public class Test extends ActivityInstrumentationTestCase2<MainActivity> {

	private EditText text_account;

	public Test() {
		super("com.ruhaly.tipdemo.test", MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		text_account = (EditText) getActivity().findViewById(
				com.ruhaly.tipdemo.R.id.text_account);
	}

	@MediumTest
	public void testAdd() {
		assertEquals("µÇÂ¼", text_account.getText().toString());
//		assertEquals(5, getActivity().add());
	}

}
