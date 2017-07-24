package com.bway.baweishopping.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.MessageEvent;
import com.bway.baweishopping.utils.HttpManager;
import com.bway.baweishopping.utils.http.EntityCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class SignOutActivity extends BaseActivity {

    private Button mButton;
    private SharedPreferences sp;
    private String url ="http://169.254.65.152/mobile/index.php?act=logout";

    @Override
    void initView() {
        mButton = (Button) findViewById(R.id.out_login);
    }

    @Override
    void initData() {

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpManager.getInstance().post(url,new HashMap<String, Object>(), new EntityCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        MessageEvent messageEvent = new MessageEvent();
                        messageEvent.setName("点击登陆");
                        EventBus.getDefault().post(messageEvent);
                        finish();
                    }

                    @Override
                    public void onFailure(String message, int error) {

                    }
                });

            }
        });
    }

    @Override
    int getContentView() {
        return R.layout.activity_sign_out;
    }
}
