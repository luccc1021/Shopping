package com.bway.baweishopping.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.MessageEvent;
import com.bway.baweishopping.modle.bean.Register;
import com.bway.baweishopping.utils.HttpManager;
import com.bway.baweishopping.utils.http.CallBack;
import com.bway.baweishopping.utils.http.EntityCallBack;
import com.bway.baweishopping.utils.http.IHttpEngien;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;


public class RegisterNow extends BaseActivity implements View.OnClickListener {

    private ImageView goBack;
    private EditText userName;
    private EditText userPass;
    private EditText userPassAgain;
    private EditText userEmail;
    private Button mSignIn;
    private SharedPreferences sp;
    private String url = "http://169.254.65.152/mobile/index.php?act=login&op=register";
    private Map<String, Object> map;
    private int a;
    private int b;
    private int c;
    private int d;
    private String name;
    private String pass;
    private String passagain;
    private String useremail;

    @Override
    void initView() {
        goBack = (ImageView) findViewById(R.id.register_back);
        userName = (EditText) findViewById(R.id.please_name);
        userPass = (EditText) findViewById(R.id.please_pass);
        userPassAgain = (EditText) findViewById(R.id.please_pass_again);
        userEmail = (EditText) findViewById(R.id.please_email);
        mSignIn = (Button) findViewById(R.id.sign_on);
        map = new HashMap<>();
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
        switch (view.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.sign_on:
                name = userName.getText().toString();
                pass = userPass.getText().toString();
                passagain = userPassAgain.getText().toString();
                useremail = userEmail.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterNow.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
//                    sp.edit().putString("name", name).commit();
                    a = 1;
                }
                if (!(pass.equals(passagain))) {
                    Toast.makeText(RegisterNow.this, "俩次密码必须一致", Toast.LENGTH_SHORT).show();
                } else {
//                    sp.edit().putString("pass", pass).commit();
                    b = 1;
                }
                if (!(useremail.contains(".com"))) {
                    Toast.makeText(RegisterNow.this, "邮箱非法格式", Toast.LENGTH_SHORT).show();
                } else {
                    c = 1;
                }
                if (a == b && b == c) {
                    map.put("client", "android");
                    map.put("password", pass);
                    map.put("password_confirm",passagain);
                    map.put("email", useremail);
                    map.put("username",name);

                    HttpManager.getInstance().post(url, map, new EntityCallBack<Register>() {
                        @Override
                        public void onSuccess(Register o) {
                            if(o.getCode() == 200) {
                                MessageEvent messageEvent = new MessageEvent();
                                messageEvent.setName(name);
                                EventBus.getDefault().post(messageEvent);
                                Toast.makeText(RegisterNow.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(RegisterNow.this, "sorry"+o.getCode(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(String message, int error) {
                            Toast.makeText(RegisterNow.this, "sorry"+ error, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                break;
        }
    }

}
