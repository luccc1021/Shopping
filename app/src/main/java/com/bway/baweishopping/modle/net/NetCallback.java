package com.bway.baweishopping.modle.net;

/**
 * Created by 卢程
 * 2017/7/11.
 */

public interface NetCallback<T> {
    void isSuccess(T t);
    void err(int code, String err);
}
