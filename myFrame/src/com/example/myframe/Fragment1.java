package com.example.myframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Fragment1 extends Fragment {

	public CallBack callBack;

	public interface CallBack {
		public void showActionBar(boolean show);
	}

	public ListView mListView;
	public ArrayAdapter mainAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// v = LayoutInflater.from(getActivity().getBaseContext()).inflate(
		// R.layout.fragment_layout, null);
		super.onCreateContextMenu(menu, v, menuInfo);
		// menu.setHeaderIcon(getResources().getDrawable(R.drawable.ic_launcher));
		// menu.setHeaderTitle("test");
		// menu.add(0, Menu.FIRST, 0, "aaaa");
		// menu.add(0, Menu.FIRST + 1, 0, "bbbb");
		// menu.add(0, Menu.FIRST + 2, 0, "cccc");
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
				callBack.showActionBar(true);
			}
		});
		// registerForContextMenu(btn);
		btn.setText("Fragment1");

		mListView = (ListView) view.findViewById(R.id.mainList);
		String[] list = new String[] { "选项1", "选项2", "选项3", "选项4" };
		mainAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_list_item_1, list);
		mListView.setAdapter(mainAdapter);
		mCallback = new ModeCallback();
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		mListView.setMultiChoiceModeListener(mCallback);

		return view;
	}

	ModeCallback mCallback;

	private class ModeCallback implements ListView.MultiChoiceModeListener {

		//
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			Log.d("GmailActivity", "oncreateactionmode");
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.action_mode_menu, menu);
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			mListView.setSelection(position);
			Log.d("sss", "position" + position);

		}

	}

	@Override
	public void onDestroyView() {
		System.out.println("Fragment1 onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		System.out.println("Fragment1 onDestroy");
		super.onDestroy();
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
