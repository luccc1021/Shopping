package com.bway.baweishopping.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.DetailsData;
import com.bway.baweishopping.utils.HttpManager;
import com.bway.baweishopping.utils.http.EntityCallBack;

import java.util.HashMap;

public class SpDetailsActivity extends BaseActivity {

    private String url = "http://169.254.65.152/mobile/index.php?act=goods&op=goods_detail&goods_id=";
    private int id;
    private ImageView mCancal;
    private ImageView mImage;
    private TextView mTitle;
    private TextView mMessage;
    private TextView mMoney;

    @Override
    void initView() {
        mCancal = (ImageView) findViewById(R.id.sp_listitem_big_cancal);
        mImage = (ImageView) findViewById(R.id.sp_listitem_big_image);
        mTitle = (TextView) findViewById(R.id.sp_listitem_m_title);
        mMessage = (TextView) findViewById(R.id.sp_listitem_m_message);
        mMoney = (TextView) findViewById(R.id.sp_listitem_m_money);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 100009);
        HttpManager.getInstance().get(url + id, new HashMap<String, Object>(), new EntityCallBack<DetailsData>() {
            @Override
            public void onSuccess(DetailsData o) {

            }

            @Override
            public void onFailure(String message, int error) {

            }
        });
    }

    @Override
    int getContentView() {
        return R.layout.sp_listitem_message;
    }
}
