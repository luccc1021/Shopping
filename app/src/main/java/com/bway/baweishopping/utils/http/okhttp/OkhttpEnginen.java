package com.bway.baweishopping.utils.http.okhttp;


import android.util.Log;

import com.bway.baweishopping.utils.GenericUtil;
import com.bway.baweishopping.utils.HttpManager;
import com.bway.baweishopping.utils.http.CallBack;
import com.bway.baweishopping.utils.http.IHttpEngien;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by WuXirui
 * Create Time: 2017/7/19
 * Description:
 */

public class OkhttpEnginen implements IHttpEngien {
    private OkHttpClient client;
    public OkhttpEnginen () {
        client = new OkHttpClient();
    }

    @Override
    public void get(String url, Map<String, Object> params, final CallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getMessage(), 0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                HttpManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Class<?> clazz = GenericUtil.getSuperGenericClass(callBack.getClass());
                        callBack.onSuccess(gson.fromJson(result, clazz));
                    }
                });
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> params, final CallBack callBack) {

        FormBody.Builder body = new FormBody.Builder();

        if (null != params) {
            for (Map.Entry<String, Object> entry:params.entrySet()) {
                body.add(entry.getKey(), (String) entry.getValue());
                Log.e("body", "post: "+entry.getKey()+"/"+(String) entry.getValue() );
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getMessage(), 0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                HttpManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Class<?> clazz = GenericUtil.getSuperGenericClass(callBack.getClass());
                        callBack.onSuccess(gson.fromJson(result, clazz));
                        //
                    }
                });
            }
        });
    }
}
