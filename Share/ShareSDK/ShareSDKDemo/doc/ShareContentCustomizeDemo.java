/*
 * 官网地站:http://www.ShareSDK.cn
 * �?��支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第�?��间�?过微信将版本更新内容推�?给您。如果使用过程中有任何问题，也可以�?过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013�?Shacom.example.sharesdkdemoghts reserved.
 */

package com.example.sharesdkdemo;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * 快捷分享项目现在添加为不同的平台添加不同分享内容的方法�?
 *本类用于演示如何区别Twitter的分享内容和其他平台分享内容�? *本类会在{@link DemoPage#showShare(boolean, String)}方法
 *中被调用�? */
public class ShareContentCustomizeDemo implements ShareContentCustomizeCallback {

	public void onShare(Platform platform, ShareParams paramsToShare) {
		// 改写twitter分享内容中的text字段，否则会超长�?		// 因为twitter会将图片地址当作文本的一部分去计算长�?		
		if ("Twitter".equals(platform.getName())) {
//			String text = platform.getContext().getString(R.string.share_content_short);
		String text="ShareSDK不仅集成简单、支持如微信、新浪微博、腾讯微博等社交平台，而且还有强大的统计分析管理后台，实时了解用户、信息流、回流率、传播效应等数据，详情见官网http://sharesdk.cn @ShareSDK";
			paramsToShare.setText(text);
		}
	}

}