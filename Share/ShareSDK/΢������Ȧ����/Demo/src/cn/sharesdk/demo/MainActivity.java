package cn.sharesdk.demo;

import java.util.HashMap;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	//定义Handler对象
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		/*这行代码会初始化 Share SDK，此后对 Share SDK 的操作都依
		次为基础。如果不在所有Share SDK 的操作之前调用这行代
		码，会抛出空指针异常*/
		ShareSDK.initSDK(this);
		
		
		/*第一种办法可以获取已经集成到您项目中的所有平台，通过
		这个平台数组，您可以结合循环操作，对所有平台进行统一
		的操作。而第二种方法相对常用，它只是根据您给定的平台
		名称获取一个平台的实例，然后单独对这个平台进行操作*/
		//方法一： 获取已经注册到 SDK 的平台实例列表
	//	Platform[] platformList = ShareSDK.getPlatformList(this);
		//方法二： 获取单个平台
	//	final Platform platform = ShareSDK.getPlatform(this, SinaWeibo.NAME);
		final Platform platform = ShareSDK.getPlatform(this, WechatMoments.NAME);
		
		/*授权是您接触 Share SDK 的第一个操作，不管您选择的
		是“手动授权”还是“自动授权”。
		对于大部分的应用来说，手动授权是没有必要的，但是
		如果您只是想做一个“账号系统”，或者是说您的应用不需
		要注册，只需要是微博的用户，就能登录，那么这个方法还
		是十分有用途的
		调用 authorize 方法，会弹出一个基于 ShareSDKUIShell 的授
		权页面，填写账号和密码以后，会执行授权操作。这个方法
		的操作回调paListener 并不实际带回什么数据，只是通过回
		调告知外部成功或者失败。但是每一个平台都具备一个
		PlatformDb 的成员，这里面存储了此平台的授权信息。可以
		参考章节平台数据库的操作的说明，通过方法 getToken、
		getUserId 等方法，获取授权用户在此平台上的授权信息。并
		由此建立“账户系统”*/
		Button bt_auth=(Button)findViewById(R.id.bt_auth);
		Button bt_data_auth=(Button)findViewById(R.id.bt_data_auth);
		final EditText et=(EditText)findViewById(R.id.et);
		/*
		 * 
		 * 授权事件
		 * 
		 * 
		 * */
		bt_auth.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (platform.isValid ()) 
				{
					platform.removeAccount();
				}
				/*获取平台实例以后还不能立刻执行操作，大部分的操作
				都是以 void 返回值的，因为这些操作内部都将开启线程，不
				能在线程间等到返回值。故 Share SDK 的操作，大量使用了
				回调。下面是设置操作回调的方法：*/
				
				platform.setPlatformActionListener(new PlatformActionListener() 
				{
					/*PlatformActionListener 是 Share SDK 统一的操作回调。它有三
					个方法：onComplete、onError 和 onCancel。分别表示：成功、
					失败和取消。一般来说，“取消”的事件很少出现，但是授
					权的时候会有；错误的时候，可以通过对其 Throwable 参数
					t 执行“t.printStackTrace()”可以得到错误的堆栈。这个堆栈
					十分重要，如果您向 Share SDK 的客服提交 bug，请带上这个
					堆栈，以便他们查询异常。成功的时候，会将操作的结果通
					过其 HashMap<String, Object>参数 res 进行返回，返回时的
					res 已经解析，可以根据不同平台的 api 文档，从res 中得到
					返回体的数据。
					每一个操作的返回，不管成功失败，都会有一个 action，
					表示这个操作的类型，其值可以参考 API 文档的相关说明。
					大部分的回调方法都处于网络线程，因此可以简单默认为回
					调方法都不在主线程，因此如果要在这些方法进行 UI 方面
					的操作，一定要通过Handler 发送一个消息给外部来处理。*/
					
					@Override
					public void onComplete(Platform platform, int action,HashMap<String, Object> res)
					{
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.arg1 = 1;
						msg.arg2 = action;
						msg.obj = platform;
						handler_auth.sendMessage(msg);				
					}
					@Override
					public void onError(Platform platform, int action, Throwable t)
					{
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.arg1 = 2;
						msg.arg2 = action;
						msg.obj = platform;
						handler_auth.sendMessage(msg);
					}
					@Override
					public void onCancel(Platform platform, int action)
					{
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.arg1 = 3;
						msg.arg2 = action;
						msg.obj = platform;
						handler_auth.sendMessage(msg);
					}
				});
			//	weibo.SSOSetting(true); // true 表示不使用 SSO 方式授权
				platform.authorize();
				
			}
		});
		final String accessToken = platform.getDb().getToken(); // 获取授权 token
		final String openId = platform.getDb().getUserId(); // 获取用户在此平台的 ID
		final String nickname = platform.getDb().get("nickname"); // 获取用户昵称
		final String data="token为："+accessToken+"\n用户的ID为："+openId+"\n用户昵称为："+nickname;
		bt_data_auth.setOnClickListener(new OnClickListener()
		{	
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub	
				et.setText(data);
			}
		});
	/*
	 * 
	 * 
	 * 
	 * 	分享到指定平台
	 * */
		Button bt_share=(Button)findViewById(R.id.bt_share);
		bt_share.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
			//	Platform.ShareParams sp = new SinaWeibo.ShareParams();
				WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
				sp.title ="aaa";
				sp.text = "aaa";
				sp.shareType = Platform.SHARE_TEXT;
			//	Platform.ShareParams sp = new WechatMoments.ShareParams();	
			//	sp.text =data;
			//	sp.imagePath = “/mnt/sdcard/测试分享的图片.jpg”;
				// 设置分享事件回调
				platform.setPlatformActionListener(new PlatformActionListener() 
				{
					@Override
					public void onComplete(Platform platform, int action,HashMap<String, Object> res)
					{
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.arg1 = 1;
						msg.arg2 = action;
						msg.obj = platform;
						handler_share.sendMessage(msg);				
					}
					@Override
					public void onError(Platform platform, int action, Throwable t)
					{
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.arg1 = 2;
						msg.arg2 = action;
						msg.obj = platform;
						handler_share.sendMessage(msg);
					}
					@Override
					public void onCancel(Platform platform, int action)
					{
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.arg1 = 3;
						msg.arg2 = action;
						msg.obj = platform;
						handler_share.sendMessage(msg);
					}
				});
				// 执行图文分享
				platform.share(sp);
			}
		});	
	}
	/*
	 * 
	 * 授权事件回调
	 * 
	 * 
	 * */
	final Handler handler_auth=new Handler()
	{
		 public void handleMessage(Message msg)
		{
			 switch (msg.arg1) 
				{
					case 1: 
					{ // 成功					
						Toast.makeText(MainActivity.this,"授权成功", Toast.LENGTH_SHORT).show();	
					}
					break;
					case 2: 
					{ // 失败			
						Toast.makeText(MainActivity.this,"授权失败", Toast.LENGTH_SHORT).show();	
					}
					break;
					case 3: 
					{ // 取消		
						Toast.makeText(MainActivity.this,"授权取消", Toast.LENGTH_SHORT).show();		
					}
					break;
			    }
		}	 
	};
	/*
	 * 
	 *分享事件回调
	 *  
	 *  
	 * */
	final Handler handler_share=new Handler()
	{
		 public void handleMessage(Message msg)
		{
			 switch (msg.arg1) 
				{
					case 1: 
					{ // 成功					
						Toast.makeText(MainActivity.this,"分享成功", Toast.LENGTH_SHORT).show();	
					}
					break;
					case 2: 
					{ // 失败			
						Toast.makeText(MainActivity.this,"分享失败", Toast.LENGTH_SHORT).show();	
					}
					break;
					case 3: 
					{ // 取消		
						Toast.makeText(MainActivity.this,"分享取消", Toast.LENGTH_SHORT).show();		
					}
					break;
			    }
		}	
	};
}
