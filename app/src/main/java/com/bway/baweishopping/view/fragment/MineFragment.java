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
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.MessageEvent;
import com.bway.baweishopping.view.activity.RegisterActivity;
import com.bway.baweishopping.view.activity.SignOutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.bway.baweishopping.R.id.user_name;

/**
 * Created by 卢程
 * 2017/7/12.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mLogin;
    private TextView meLogin;

    @Override
    void initData() {

    }

    @Override
    void initView(View view) {
        EventBus.getDefault().register(this);
        mLogin = view.findViewById(R.id.lnear_login);
        meLogin = view.findViewById(R.id.me_login);
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
                if(meLogin.getText().equals("点击登陆")) {
                    Intent intent = new Intent(getContext(), RegisterActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), SignOutActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        meLogin.setText(event.getName());
    }
}
