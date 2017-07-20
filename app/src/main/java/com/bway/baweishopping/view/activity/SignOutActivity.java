package com.bway.baweishopping.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class SignOutActivity extends BaseActivity {

    private Button mButton;
    private SharedPreferences sp;

    @Override
    void initView() {
        mButton = (Button) findViewById(R.id.out_login);
    }

    @Override
    void initData() {
        sp = getSharedPreferences("user", MODE_PRIVATE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setName("点击登陆");
                EventBus.getDefault().post(messageEvent);
                sp.edit().putBoolean("isSuccess",false).commit();
                finish();
            }
        });
    }

    @Override
    int getContentView() {
        return R.layout.activity_sign_out;
    }
}
