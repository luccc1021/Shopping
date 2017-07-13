package com.bway.baweishopping.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class RegisterNow extends BaseActivity implements View.OnClickListener {

    private ImageView goBack;
    private EditText userName;
    private EditText userPass;
    private EditText userPassAgain;
    private EditText userEmail;
    private Button mSignIn;
    private SharedPreferences sp;
    private  int a;
    private  int b;
    private  int c;
    private  int d;

    @Override
    void initView() {
        goBack = (ImageView) findViewById(R.id.register_back);
        userName = (EditText) findViewById(R.id.please_name);
        userPass = (EditText) findViewById(R.id.please_pass);
        userPassAgain = (EditText) findViewById(R.id.please_pass_again);
        userEmail = (EditText) findViewById(R.id.please_email);
        mSignIn = (Button) findViewById(R.id.sign_on);
        goBack.setOnClickListener(this);
        mSignIn.setOnClickListener(this);
    }

    @Override
    void initData() {
        sp = getSharedPreferences("user", MODE_PRIVATE);
    }

    @Override
    int getContentView() {
        return R.layout.activity_register_now;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.sign_on:
                String name = userName.getText().toString();
                String pass = userPass.getText().toString();
                String passagain = userPassAgain.getText().toString();
                String useremail = userEmail.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterNow.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    sp.edit().putString("name",name).commit();
                    a = 1;
                }
                if(!(pass.equals(passagain))){
                    Toast.makeText(RegisterNow.this,"俩次密码必须一致",Toast.LENGTH_SHORT).show();
                }else{
                    sp.edit().putString("pass",pass).commit();
                    b = 1;
                }
                if(!(useremail.contains("@"))){
                    Toast.makeText(RegisterNow.this,"邮箱非法格式",Toast.LENGTH_SHORT).show();
                }else{
                    c = 1;
                }
                if(a == b && b == c){
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setName(name);
                    EventBus.getDefault().post(messageEvent);
                    Toast.makeText(RegisterNow.this,"注册成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
