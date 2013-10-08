package com.example.myframe;

import com.example.myframe.Fragment1.CallBack;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment2 extends Fragment {
	public CallBack callBack;

	public Fragment2() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("Fragment1 onCreateView");
		View view = inflater
				.inflate(R.layout.fragment_layout, container, false);
		Button btn = (Button) view.findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.showActionBar(false);
			}
		});
		btn.setText("Fragment2");
		return view;
	}

	@Override
	public void onDestroy() {
		System.out.println("Fragment1 onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		System.out.println("Fragment2 onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			callBack = (CallBack) activity;
		} catch (ClassCastException e) {
		}
	}
}
