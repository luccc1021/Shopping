package com.bway.baweishopping.presenter;

/**
 * Created by 卢程
 * 2017/7/11.
 */

public class BasePresenter<V> {
    private V mV;

    public void attachView(V v){
        mV = v;
    }
    public V getmV() {
        return mV;
    }
    public void detach() {
        mV = null;
    }
}
