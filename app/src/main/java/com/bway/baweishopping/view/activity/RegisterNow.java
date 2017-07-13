package com.bway.baweishopping.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bway.baweishopping.R;

public class RegisterNow extends BaseActivity implements View.OnClickListener {

    private ImageView goBack;
    private EditText userName;
    private EditText userPass;
    private EditText userPassAgain;
    private EditText userEmail;
    private Button mSignIn;

    @Override
    void initView() {
        goBack = (ImageView) findViewById(R.id.register_back);
        userName = (EditText) findViewById(R.id.please_name);
        userPass = (EditText) findViewById(R.id.please_pass);
        userPassAgain = (EditText) findViewById(R.id.please_pass_again);
        userEmail = (EditText) findViewById(R.id.please_email);
        mSignIn = (Button) findViewById(R.id.sign_in);
        goBack.setOnClickListener(this);

    }

    @Override
    void initData() {

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
            case R.id.sign_in:
                if(userName.getText().toString().isEmpty())
                break;
        }
    }
}
