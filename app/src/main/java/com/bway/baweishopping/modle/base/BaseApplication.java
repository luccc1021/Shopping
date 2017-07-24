package com.bway.baweishopping.modle.base;

import android.app.Application;

import com.bway.baweishopping.modle.bean.MessageEvent;
import com.bway.baweishopping.utils.HttpManager;
import com.bway.baweishopping.utils.http.okhttp.OkhttpEnginen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
* 类描述：
* 创建人：卢程
* 创建时间：2017/7/21
*/

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.getInstance().init(new OkhttpEnginen());

        //可以随时切换
//        HttpManager.getInstance().init(new VolleyEngine());
    }
}
