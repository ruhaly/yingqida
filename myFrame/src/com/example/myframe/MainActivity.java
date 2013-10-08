package com.example.myframe;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.myframe.Fragment1.CallBack;

public class MainActivity extends SherlockActivity implements OnClickListener,
		CallBack {

	private Button tab1;
	private Button tab2;
	private Button tab3;

	public static final String TAGFGM1 = "fragment1";
	public static final String TAGFGM2 = "fragment2";

	public int currentshowtab = -1;
	public ActionBar actionBar;

	public String[] tags;
	public Fragment[] frags;

	public List<Info> list = new ArrayList<Info>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(android.R.drawable.ic_lock_lock);
		// actionBar.setDisplayHomeAsUpEnabled(true);
		tab1 = (Button) findViewById(R.id.tab1);
		tab2 = (Button) findViewById(R.id.tab2);
		tab3 = (Button) findViewById(R.id.tab3);
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
		initTabData();

		Info info = new Info("æôÆï1", "æôÆï1");
		list.add(info);
		info = new Info("æôÆï2", "æôÆï2");
		list.add(info);
		info = new Info("æôÆï3", "æôÆï3");
		list.add(info);
		info = new Info("æôÆï4", "æôÆï4");
		list.add(info);
		info = new Info("æôÆï5", "æôÆï5");
		list.add(info);
		adapter = new MyAdapter();
		actionBar.setListNavigationCallbacks(adapter,
				new OnNavigationListener() {

					@Override
					public boolean onNavigationItemSelected(int itemPosition,
							long itemId) {
						return false;
					}
				});
	}

	public MyAdapter adapter;

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getBaseContext());
			convertView = inflater.inflate(R.layout.dropdown_item, null);
			if (convertView != null) {
				TextView titleView = (TextView) convertView
						.findViewById(R.id.dropdown_title);
				TextView countView = (TextView) convertView
						.findViewById(R.id.dropdown_count);
				titleView.setText(list.get(position).getKey());
				countView.setText(list.get(position).getValue());
			}
			return convertView;

		}

	}

	public void initTabData() {
		tags = new String[] { "tagFragment1", "tagFragment2", "tagFragment3" };
		Fragment frag1 = new Fragment1();
		Fragment frag2 = new Fragment2();
		Fragment frag3 = new Fragment1();
		frags = new Fragment[] { frag1, frag2, frag3 };
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.optionsmenu, menu);
		// int group1 = 1;
		// int gourp2 = 2;
		// menu.add(group1, 1, 1, "item 1");
		// menu.add(group1, 2, 2, "item 2");
		// menu.add(gourp2, 3, 3, "item 3");
		// menu.add(gourp2, 4, 4, "item 4");
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderIcon(getResources().getDrawable(R.drawable.ic_launcher));
		menu.setHeaderTitle("test");
		menu.add(0, Menu.FIRST, 0, "aaaa");
		menu.add(0, Menu.FIRST + 1, 0, "bbbb");
		menu.add(0, Menu.FIRST + 2, 0, "cccc");
	}

	private Intent createShareIntent() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("image/*");
		Uri uri = Uri.fromFile(getFileStreamPath("shared.png"));
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		return shareIntent;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab1: {
			switchTab(0);
			break;
		}
		case R.id.tab2: {
			switchTab(1);
			break;
		}
		case R.id.tab3: {
			switchTab(2);
			break;
		}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		switchTab(0);
	}

	public void switchTab(int tab) {
		if (tab == currentshowtab) {
			return;
		}
		FragmentTransaction beginTransaction = getSupportFragmentManager()
				.beginTransaction();
		Fragment oldFrag = null;
		if (-1 != currentshowtab) {
			oldFrag = getSupportFragmentManager().findFragmentByTag(
					tags[currentshowtab]);
		}
		Fragment frag = getSupportFragmentManager()
				.findFragmentByTag(tags[tab]);
		if (null != frag) {
			if (!frag.isAdded()) {
				beginTransaction.add(R.id.frameContent, frag, tags[tab]);
			}
		} else {
			frag = frags[tab];
			beginTransaction.add(R.id.frameContent, frag, tags[tab]);
		}
		if (null != oldFrag) {
			beginTransaction.hide(oldFrag).show(frag);
		} else {
			beginTransaction.show(frag);
		}
		beginTransaction.commit();
		currentshowtab = tab;
	}

	@Override
	public void showActionBar(boolean show) {

		if (null != actionBar) {
			if (show) {
				actionBar.show();
			} else {
				actionBar.hide();
			}
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			Toast.makeText(getBaseContext(), "sad", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
