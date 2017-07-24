package com.bway.baweishopping.utils.http;

/**
 * Created by WuXirui
 * Create Time: 2017/7/19
 * Description:
 */

public interface CallBack<T> {
    void onSuccess(T t);
    void onFailure(String message, int error);
}
