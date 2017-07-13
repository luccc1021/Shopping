package com.bway.baweishopping.modle.net;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 卢程
 * 2017/7/11.
 */

public class HttpUtils {

    private NetCallback mCallback;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    mCallback.isSuccess(msg.obj);
                    break;
            }
        }
    };

    public <T>void LoadDataServer(String url, NetCallback callback, final Class<T> clazz){
        mCallback = callback;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mCallback.err(500,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage();
                message.what = 0;
                Gson gson = new Gson();
                T t = gson.fromJson(response.body().string(), clazz);
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }
}
