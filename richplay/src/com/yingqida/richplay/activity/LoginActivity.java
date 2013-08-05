package com.yingqida.richplay.activity;

import android.content.Intent;
import android.view.View;

import com.yingqida.richplay.HomeActivity;
import com.yingqida.richplay.R;
import com.yingqida.richplay.activity.common.SuperActivity;

public class LoginActivity extends SuperActivity {

	@Override
	public void initData() {

	}

	@Override
	public void initLayout() {
		setContentView(R.layout.login_layout);
	}

	@Override
	public void clearData() {

	}

	public void btnRegisterLis(View view) {
		startActivity(new Intent(getBaseContext(), RegisterActivity.class));
	}

	public void btnLogin(View view) {
		startActivity(new Intent(getBaseContext(), HomeActivity.class));
	}
}
