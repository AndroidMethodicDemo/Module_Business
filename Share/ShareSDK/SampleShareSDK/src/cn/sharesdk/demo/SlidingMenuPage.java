/*
 * 官网地站:http://www.ShareSDK.cn
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 ShareSDK.cn. All rights reserved.
 */

package cn.sharesdk.demo;

import android.content.Context;
import android.content.res.Resources;
import android.os.Message;
import android.os.Handler.Callback;
import android.view.View;
import m.framework.ui.widget.slidingmenu.SlidingMenu;

/** 所有侧栏页面的父类 */
public abstract class SlidingMenuPage implements Callback {
	private SlidingMenu menu;
	private View pageView;

	/**
	 * 构造方法，初始化SlidingMenu对象，调用initPage方法设置布局
	 * @param menu
	 */
	public SlidingMenuPage(SlidingMenu menu) {
		this.menu = menu;
		pageView = initPage();
	}

	/**
	 * 获取Context对象
	 * @return
	 */
	public Context getContext() {
		return menu.getContext();
	}

	/**
	 * 调用menu.getResources，返回Resources类型的对象
	 * @return
	 */
	public Resources getResources() {
		return menu.getResources();
	}

	/**
	 * 调用menu.isMenuShown，返回boolean类型的对象
	 * @return
	 */
	public boolean isMenuShown() {
		return menu.isMenuShown();
	}

	/**
	 * 调用menu.hideMenu，返回menu.hideMenu
	 */
	public void hideMenu() {
		menu.hideMenu();
	}

	/**
	 * 调用menu.showMenu，返回menu.showMenu
	 */
	public void showMenu() {
		menu.showMenu();
	}

	/**
	 * 调用menu.findViewById，返回View对象
	 */
	public View findViewById(int id) {
		return menu.findViewById(id);
	}

	/**
	 * 子类实现，初始化布局
	 */
	protected abstract View initPage();

	/** 获取页面的View实例 */
	public View getPage() {
		return pageView;
	}

	public boolean handleMessage(Message msg) {
		return false;
	}

}
