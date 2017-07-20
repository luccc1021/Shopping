package com.bway.baweishopping.presenter;

import com.bway.baweishopping.modle.net.HttpUtils;
import com.bway.baweishopping.modle.net.NetCallback;
import com.bway.baweishopping.view.IView.IClassView;

/**
 * Created by 卢程
 * 2017/7/11.
 */

public class MainPresenter extends BasePresenter<IClassView> {
    private final HttpUtils httpUtils;

    public MainPresenter() {
        httpUtils = new HttpUtils();
    }

    public <T>void  loadDataFromServer(String url, final Class<T> t,final int id){
        httpUtils.LoadDataServer(url, new NetCallback() {
            @Override
            public void isSuccess(Object o) {
                getmV().isSuccess(o,id);
            }

            @Override
            public void err(int code, String err) {
                getmV().err(code, err);
            }
        }, t);
    }
}
