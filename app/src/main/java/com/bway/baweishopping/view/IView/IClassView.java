package com.bway.baweishopping.view.IView;

/**
 * Created by 卢程
 * 2017/7/19.
 */

public interface IClassView<T> {
    void isSuccess(T t, int id);
    void err(int code, String err);

}
