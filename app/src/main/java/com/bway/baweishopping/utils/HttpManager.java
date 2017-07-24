package com.bway.baweishopping.utils;

import android.os.Handler;


import com.bway.baweishopping.utils.http.CallBack;
import com.bway.baweishopping.utils.http.IHttpEngien;

import java.util.Map;

/**
* 类描述：
* 创建人：卢程
* 创建时间：2017/7/21
*/

public class HttpManager implements IHttpEngien {
    private volatile static HttpManager instance;

    private IHttpEngien engien;

    public static Handler handler = new Handler();

    private HttpManager() {

    }

    public void init(IHttpEngien engien) {
        this.engien = engien;
    }

    /**
     * 单例模式获取Http
     * @return
     */
    public static HttpManager getInstance() {
        if (null == instance) {
            synchronized (HttpManager.class) {
                if (null == instance) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void get(String url, Map<String, Object> params, CallBack callBack) {
        engien.get(url, params, callBack);
    }

    @Override
    public void post(String url, Map<String, Object> params, CallBack callBack) {
        engien.post(url, params, callBack);
    }
}
