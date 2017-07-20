package com.bway.baweishopping.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.Money;
import com.bway.baweishopping.modle.bean.User;
import com.bway.baweishopping.view.adapter.MyAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bway.baweishopping.R.id.sum;

/**
 * Created by 卢程
 * 2017/7/12.
 */

public class CartFragment extends BaseFragment {

    private PullLoadMoreRecyclerView mRecycleView;
    private List<User> mList;
    private MyAdapter myAdapter;
    private Button mJiesuan;
    private TextView sumText;
    private CheckBox mCheck;
    private List<Money> moneyList;
    private HashMap<Integer,Money> moneyMap;
    private int moneySum;
    private int sMoney;
    private HashMap<Integer, Boolean> getIsSelected = new HashMap<>();
    private User user;
    private Money money;

    @Override
    void initData() {
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setName("商品" + i);
            user.setPrice(i * 10);
            mList.add(user);
        }
        myAdapter = new MyAdapter(getContext(), mList);
        myAdapter.setMap(getIsSelected);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLinearLayout();
        mRecycleView.setAdapter(myAdapter);


//        myAdapter.setOnItemClickListener(new MyItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                ObjectAnimator
//                        .ofFloat(view, "rotationX", 0.0F, 360.0F)//
//                        .setDuration(500)//
//                        .start();
//            }
//        });

        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (int i = 0; i < mList.size(); i++) {
                        getIsSelected.put(i, true);
                    }
                    // 刷新listview和TextView的显示
                    myAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        getIsSelected.put(i, false);
                    }
                    // 刷新listview和TextView的显示
                    myAdapter.notifyDataSetChanged();
                }
            }
        });


        myAdapter.setSumListener(new MyAdapter.getMoneyListener() {
            @Override
            public void getSum(int sum, int position) {
                Boolean b = getIsSelected.get(position);
                int price = mList.get(position).getPrice();
                moneySum = price * sum;
                money.setTrue(b);
                money.setGetAll(moneySum);
                moneyMap.put(position, money);

                for (Integer key : moneyMap.keySet()) {
                    Log.e("+++++", "getSum: "+ key);
                    sMoney = moneyMap.get(key).getGetAll();
                }

                if(moneyMap.size() == mList.size()){
                    mCheck.setChecked(true);
                }else{
                    mCheck.setChecked(false);
                }
                sumText.setText(sMoney+" -"+ position);
            }
        });
    }

    @Override
    void initView(View view) {
        mRecycleView = (PullLoadMoreRecyclerView) view.findViewById(R.id.recycleview);
        mCheck = (CheckBox) view.findViewById(R.id.chec_box);
        sumText = (TextView) view.findViewById(sum);
        mJiesuan = (Button) view.findViewById(R.id.jiesuan);
        money = new Money();
        moneyList = new ArrayList<>();
        moneyMap = new HashMap<>();
        mList = new ArrayList<>();
    }

    @Override
    int getContentView() {
        return R.layout.cart_fragment;
    }
}
