package com.bway.baweishopping.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bway.baweishopping.R;
import com.bway.baweishopping.view.activity.RegisterActivity;

/**
 * Created by 卢程
 * 2017/7/12.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mLogin;

    @Override
    void initView(View view) {
        mLogin = view.findViewById(R.id.lnear_login);
        mLogin.setOnClickListener(this);
    }

    @Override
    int getContentView() {
        return R.layout.mine_fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lnear_login:
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
