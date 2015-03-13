package com.ccppg.appstore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		ShareSDK.initSDK(this);
	}
	
	public void onClick(View view){
		ShareUtil shareUtil = new ShareUtil();
		shareUtil.showShareWindow(this, "百度", findViewById(R.id.root));
	}
}
