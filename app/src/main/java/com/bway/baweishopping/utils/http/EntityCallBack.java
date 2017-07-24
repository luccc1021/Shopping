package com.bway.baweishopping.utils.http;


import com.bway.baweishopping.utils.GenericUtil;

/**
* 类描述：
* 创建人：卢程
* 创建时间：2017/7/21
*/

public abstract class EntityCallBack<T> implements CallBack<T> {
    protected Class<T> clazz;

    public EntityCallBack() {
        this.clazz = GenericUtil.getSuperGenericClass(this.getClass());
    }
}
