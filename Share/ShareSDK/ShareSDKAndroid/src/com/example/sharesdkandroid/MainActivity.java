package com.example.sharesdkandroid;


import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener,PlatformActionListener{
	
	private Button btnButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		ShareSDK.initSDK(this);
		
		btnButton = (Button)findViewById(R.id.btn1);
		btnButton.setOnClickListener(this);
		
		Platform qzone = ShareSDK.getPlatform(this, SinaWeibo.NAME);
		String openID = qzone.getDb().getUserId();
		String accessTokenString = qzone.getDb().getToken();
		String nickname = (String)qzone.getDb().get("nickname");
		System.out.println(openID);
		System.out.println(nickname);
		System.out.println(accessTokenString);
		String headicon = qzone.getDb().get("icon");
		System.out.println(headicon);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
		
			
			OnekeyShare oksOnekeyShare = new OnekeyShare();
			oksOnekeyShare.setNotification(R.drawable.ic_launcher, "ffff");
			oksOnekeyShare.setText("ffffffffffffffff");
			
			oksOnekeyShare.setPlatform(SinaWeibo.NAME);
			oksOnekeyShare.setSilent(true);
			oksOnekeyShare.show(this);
			
			System.out.println("===================================");
			Platform qzone = ShareSDK.getPlatform(this, SinaWeibo.NAME);
			String openID = qzone.getDb().getUserId();
			String nickname = (String)qzone.getDb().get("nickname");
			System.out.println(openID);
			System.out.println(nickname);
			

//			weiboPlatform.setPlatformActionListener(this);
//			
//			weiboPlatform.authorize();
			
			
//			Platform.ShareParams sParams = new SinaWeibo.ShareParams();
//			sParams.text = "text";
//			Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
//			weibo.share(sParams);
			
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onCancel(Platform arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		System.out.println("===================================");
		Platform qzonePlatform = ShareSDK.getPlatform(this, SinaWeibo.NAME);
		String accessTokenString = qzonePlatform.getDb().getToken();
		System.out.println(accessTokenString);
		
		
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
		
	}
	
	


}
