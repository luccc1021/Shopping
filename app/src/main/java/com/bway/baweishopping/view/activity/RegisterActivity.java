package com.bway.baweishopping.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.baweishopping.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mRegister;
    private TextView mFindPass;
    private Button mLogin;

    @Override
    void initView() {
        mBack = (ImageView) findViewById(R.id.register_back);
        mFindPass = (TextView) findViewById(R.id.mine_findpass);
        mRegister = (TextView) findViewById(R.id.mine_register);
        mLogin = (Button) findViewById(R.id.sign_in);
        mBack.setOnClickListener(this);
        mFindPass.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    void initData() {

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
                break;
            case R.id.mine_findpass:
                break;
        }
    }
}
