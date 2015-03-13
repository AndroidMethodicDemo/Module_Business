package com.ccppg.appstore;

import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;


public class ShareUtil implements OnClickListener, PlatformActionListener{

	private String tag=ShareUtil.class.getSimpleName();
	private SharePopupWindow shareWindow;
	private String name;
	
	private ShareParams getShareParams() {
		ShareParams sp = new ShareParams();
		sp.setTitle("setTitle");
		sp.setText("我刚刚在中少应用商城Android版上发现了"+name+"，快来看看吧 。中少应用商城是针对广大少年儿童设计的应用和游戏集合地，里面的内容应有尽有，赶快来下载吧。地址链接：http://www.baidu.com");
		sp.setShareType(Platform.SHARE_TEXT);
		return sp;
	}
	
	public void showShareWindow(Context context,String name,View parent){
		this.name=name;
		if(shareWindow==null){
			shareWindow=new SharePopupWindow(context, new OnClickListener[]{this,this,this,new CancelListener()});
			shareWindow.initAnimation(200);
		}
		shareWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
	}

	@Override
	public void onClick(View v) {
		Platform plat=null;
		switch (v.getId()) {
		case R.id.iv_1:
			plat = ShareSDK.getPlatform("Wechat");
			break;
		case R.id.iv_2:
			plat = ShareSDK.getPlatform("WechatMoments");
			
			break;
		case R.id.iv_3:
			plat = ShareSDK.getPlatform("SinaWeibo");
			
			break;
		}
		ShareParams sp = getShareParams();
		plat.setPlatformActionListener(this);
		plat.share(sp);
		shareWindow.dismiss();
	}
	class CancelListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			shareWindow.dismiss();
		}
		
	}
	@Override
	public void onCancel(Platform arg0, int arg1) {
		
	}

	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		Log.i(tag, arg2.toString());
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		Log.e(tag, arg2.getMessage());
	}
}
