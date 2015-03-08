package com.example.sharesdkdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.gldjc.gcsupplier.R;

public class MainActivity extends Activity {
	
	public static String TEST_IMAGE;
	public static String TEST_IMAGE_URL="http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ShareSDK.initSDK(this);
	} 
	
	public Context getContext() {
		return this;
	}
	public void ShareSDK(View v)
	{
		showShare(false, null, false);
	}
	// ʹ�ÿ�ݷ�����ɷ����������ϸ�Ķ�λ��SDK��ѹĿ¼��Docs�ļ�����OnekeyShare���JavaDoc��
	/**ShareSDK���ɷ���������</br>
	 * 1����һ�������÷�ʽ����������onekeyshare��Ŀ��onekeyshare��Ŀ������mainlibs��</br>
	 * 2���ڶ����ǰ�onekeyshare��mainlibs���ɵ���Ŀ�У������Ӿ����õڶ��ַ�ʽ</br>
	 * �뿴��ShareSDK ʹ��˵���ĵ�����SDK����Ŀ¼�� </br>
	 * ���߿����缯���ĵ� http://wiki.sharesdk.cn/Android_%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E6%8C%87%E5%8D%97
	 * 3������ʱ����sample���߱����ӵĻ�������copy��ȥ����proguard-project.txt�ļ���
	 *
	 *
	 * ƽ̨������Ϣ�����ַ�ʽ��
	 * 1�������Ǻ�̨���ø���΢��ƽ̨��key
	 * 2���ڴ��������ø���΢��ƽ̨��key��http://sharesdk.cn/androidDoc/cn/sharesdk/framework/ShareSDK.html
	 * 3���������ļ������ã������������assets/ShareSDK.conf,
	 */
	private void showShare(boolean silent, String platform, boolean captureView) {
		final OnekeyShare oks = new OnekeyShare();
		oks.setNotification(R.drawable.ic_launcher, getContext().getString(R.string.app_name));
		oks.setAddress("12345678901");
		oks.setTitle(getContext().getString(R.string.evenote_title));
		oks.setTitleUrl("http://sharesdk.cn");
		oks.setText(getContext().getString(R.string.share_content));
		if (captureView) {
			//oks.setViewToShare(getPage());
		} else {
			//ͼƬ�ı���·��
			//oks.setImagePath(MainActivity.TEST_IMAGE); TODO
			//ͼƬ������·��
			oks.setImageUrl("http://img.appgo.cn/imgs/sharesdk/content/2013/07/25/1374723172663.jpg");
		}
		oks.setUrl("http://www.sharesdk.cn");
		oks.setFilePath(MainActivity.TEST_IMAGE);
		oks.setComment(getContext().getString(R.string.share));
		oks.setSite(getContext().getString(R.string.app_name));
		oks.setSiteUrl("http://sharesdk.cn");
		oks.setVenueName("ShareSDK");
		oks.setVenueDescription("This is a beautiful place!");
		oks.setLatitude(23.056081f);
		oks.setLongitude(113.385708f);
		oks.setSilent(silent);
		if (platform != null) {
			oks.setPlatform(platform);
		}

		// ��༭ҳ����ʾΪDialogģʽ
		oks.setDialogMode();

		// ���Զ���Ȩʱ���Խ���SSO��ʽ
		oks.disableSSOWhenAuthorize();

		// ȥ��ע�ͣ����ݷ���Ĳ��������ͨ��OneKeyShareCallback�ص�
//		oks.setCallback(new OneKeyShareCallback());
		oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());

		// ȥ��ע�ͣ���ʾ�ھŹ��������Զ����ͼ��
//		Bitmap logo = BitmapFactory.decodeResource(menu.getResources(), R.drawable.ic_launcher);
//		String label = menu.getResources().getString(R.string.app_name);
//		OnClickListener listener = new OnClickListener() {
//			public void onClick(View v) {
//				String text = "Customer Logo -- ShareSDK " + ShareSDK.getSDKVersionName();
//				Toast.makeText(menu.getContext(), text, Toast.LENGTH_SHORT).show();
//				oks.finish();
//			}
//		};
//		oks.setCustomerLogo(logo, label, listener);

		// ȥ��ע�ͣ����ݷ���Ź����н���������΢������Ѷ΢��
//		oks.addHiddenPlatform(SinaWeibo.NAME);
//		oks.addHiddenPlatform(TencentWeibo.NAME);

		// ΪEditPage����һ��������View
		//oks.setEditPageBackground(getPage()); TODO

		oks.show(getContext());
	}
	@Override
	protected void onDestroy() {		
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}
}
