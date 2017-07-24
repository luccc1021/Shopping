package com.bway.baweishopping.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.SpList;
import com.bway.baweishopping.utils.HttpManager;
import com.bway.baweishopping.utils.http.EntityCallBack;
import com.bway.baweishopping.view.adapter.ShoppingListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 卢程
 * 2017/7/21.
 */

public class ShoppingListActivity extends BaseActivity {

    private RecyclerView mListview;
    private TextView paixu;
    private TextView xiaoliang;
    private CheckBox sxuan;
    private CheckBox select;
    private List<SpList.DatasBean.GoodsListBean> spList;
    private ShoppingListAdapter shoppingListAdapter;
    private String url="http://169.254.65.152/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=587";
    private LinearLayout mLinear;

    @Override
    void initView() {
        mListview = (RecyclerView) findViewById(R.id.sp_list_listview);
        paixu = (TextView) findViewById(R.id.sp_list_paixu);
        xiaoliang = (TextView) findViewById(R.id.sp_list_xlyx);
        sxuan = (CheckBox) findViewById(R.id.sp_list_sxuan);
        select = (CheckBox) findViewById(R.id.sp_list_select);
        mLinear = (LinearLayout) findViewById(R.id.Linear_sxuan);
        spList = new ArrayList<>();
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        String xinxi = intent.getStringExtra("xinxi");
        if(xinxi.equals("瑞士品牌")){
            HttpManager.getInstance().get(url, new HashMap<String, Object>(), new EntityCallBack<SpList>() {


                @Override
                public void onSuccess(SpList o) {
                    if(o.getCode() == 200) {
                        List<SpList.DatasBean.GoodsListBean> goods_list = o.getDatas().getGoods_list();
                        spList.addAll(goods_list);
                        shoppingListAdapter = new ShoppingListAdapter(ShoppingListActivity.this, spList);

                        mListview.setLayoutManager(new LinearLayoutManager(ShoppingListActivity.this));
                        mListview.setAdapter(shoppingListAdapter);

                        select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    mListview.setLayoutManager(new GridLayoutManager(ShoppingListActivity.this,2));
                                    mListview.setAdapter(shoppingListAdapter);
                                }else{
                                    mListview.setLayoutManager(new LinearLayoutManager(ShoppingListActivity.this));                    shoppingListAdapter.notifyDataSetChanged();
                                    mListview.setAdapter(shoppingListAdapter);
                                }
                            }
                        });

                        sxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    mLinear.setVisibility(View.VISIBLE);
                                }else{
                                    mLinear.setVisibility(View.GONE);
                                }
                            }
                        });
                    }else{
                        Log.e("-----", "onSuccess: "+ o.getCode());
                    }

                }

                @Override
                public void onFailure(String message, int error) {

                }
            });
        }

    }

    @Override
    int getContentView() {
        return R.layout.activity_shoppinglist;
    }
}
