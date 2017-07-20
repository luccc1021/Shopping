package com.bway.baweishopping.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mRegister;
    private TextView mFindPass;
    private Button mLogin;
    private EditText user_name;
    private EditText user_pass;
    private SharedPreferences sp;

    @Override
    void initView() {
        EventBus.getDefault().register(this);
        mBack = (ImageView) findViewById(R.id.register_back);
        mFindPass = (TextView) findViewById(R.id.mine_findpass);
        mRegister = (TextView) findViewById(R.id.mine_register);
        mLogin = (Button) findViewById(R.id.sign_in);
        user_name = (EditText) findViewById(R.id.user_name);
        user_pass = (EditText) findViewById(R.id.user_pass);
        mBack.setOnClickListener(this);
        mFindPass.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    void initData() {
        sp = getSharedPreferences("user", MODE_PRIVATE);
    }

    @Override
    int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.mine_register:
                //点击立即注册
                Intent intent = new Intent(RegisterActivity.this,RegisterNow.class);
                startActivity(intent);
                break;
            case R.id.mine_findpass:
                break;
            case R.id.sign_in:
                String sname = sp.getString("name", null);
                String spass = sp.getString("pass", null);
                String name = user_name.getText().toString();
                String pass = user_pass.getText().toString();
                if(name.equals(sname)&&pass.equals(spass)){
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setName(name);
                    EventBus.getDefault().post(messageEvent);
                    Toast.makeText(RegisterActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    sp.edit().putBoolean("isSuccess",true).commit();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        user_name.setText(event.getName());
    }
}
