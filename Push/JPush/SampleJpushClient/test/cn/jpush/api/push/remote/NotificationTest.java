package cn.jpush.api.push.remote;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

public class NotificationTest extends BaseRemoteTest {
	
    // --------------- Android
	
    @Test
    public void sendNotification_android_title() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setAudience(Audience.all())
                .setPlatform(Platform.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
//                                .setAlert(ALERT)
                        		.setAlert("我是从Client端发出的通知")//通知的内容
                                .setTitle("title")//通知的标题
                                .build()).build())
                .build();
        /*
         * Request Content - {"platform":"all","audience":"all",
         * "notification":{"android":{"alert":"JPush Test - alert","title":"title"}},
         * "options":{"sendno":205915211,"apns_production":false}}
         */
        //本质是发送一个http请求
        PushResult result = _client.sendPush(payload);
        assertTrue(result.isResultOK());
    }
    
    @Test
    public void sendNotification_android_buildId() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setAudience(Audience.all())
                .setPlatform(Platform.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(ALERT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setBuilderId(100)
                                .build()).build())
                .build();
        PushResult result = _client.sendPush(payload);
        assertTrue(result.isResultOK());
    }
    
    @Test
    public void sendNotification_android_extras() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setAudience(Audience.all())
                .setPlatform(Platform.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(ALERT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("key1", "value1")
                                .addExtra("key2", 222)
                                .build()).build())
                .build();
        PushResult result = _client.sendPush(payload);
        assertTrue(result.isResultOK());
    }
    
    
    // ------------------ ios
    
    
    
    
}

