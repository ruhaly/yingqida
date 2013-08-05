package com.yingqida.richplay.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yingqida.richplay.R;
import com.yingqida.richplay.activity.common.SuperActivity;
import com.yingqida.richplay.entity.Comment;
import com.yingqida.richplay.widget.MyListView;

public class PicInfoActivity extends SuperActivity {

	private MyListView listComm;

	Adapter adapter;

	private List<Comment> list = new ArrayList<Comment>();

	@Override
	public void initData() {
		for (int i = 0; i < 20; i++) {
			Comment c = new Comment();
			c.setUname("user" + i);
			c.setCmsg("usermsg" + i);
			list.add(c);
		}
	}

	@Override
	public void initLayout() {
		setContentView(R.layout.picinfo_layout);
		listComm = (MyListView) findViewById(R.id.listComm);

		if (null == adapter) {
			adapter = new Adapter();
			listComm.setAdapter(adapter);
			// setListViewHeight(listComm);

		} else {
			adapter.notifyDataSetChanged();
		}
	}

	class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Comment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (null == convertView) {
				holder = new Holder();
				convertView = LayoutInflater.from(getBaseContext()).inflate(
						R.layout.comment_item_layout, null);
				holder.text_name = (TextView) convertView
						.findViewById(R.id.text_name);
				holder.text_msg = (TextView) convertView
						.findViewById(R.id.text_msg);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.text_name.setText(getItem(position).getUname());
			holder.text_msg.setText(getItem(position).getCmsg());
			return convertView;
		}

		class Holder {
			TextView text_name;
			TextView text_msg;
		}
	}

	@Override
	public void clearData() {

	}

	/**
	 * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
	 * 
	 * @param listView
	 */
	public void setListViewHeight(ListView listView) {

		// 获取ListView对应的Adapter

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
