package com.bway.baweishopping.view.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.bway.baweishopping.R;
import com.bway.baweishopping.view.fragment.CartFragment;
import com.bway.baweishopping.view.fragment.ClassFragment;
import com.bway.baweishopping.view.fragment.HomeFragment;
import com.bway.baweishopping.view.fragment.MineFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private FrameLayout mFrameLayout;
    private RadioButton mHome,mClazz,mCart,mMine;
    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private CartFragment cartFragment;
    private MineFragment mineFragment;
    private FragmentManager manager;
    private RadioButton[] setIdColor;

    @Override
    public void initView() {
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        mHome = (RadioButton) findViewById(R.id.image_home);
        mClazz = (RadioButton) findViewById(R.id.image_class);
        mCart = (RadioButton) findViewById(R.id.image_cart);
        mMine = (RadioButton) findViewById(R.id.image_mine);
        mHome.setOnClickListener(this);
        mClazz.setOnClickListener(this);
        mCart.setOnClickListener(this);
        mMine.setOnClickListener(this);
        homeFragment = new HomeFragment();
        classFragment = new ClassFragment();
        cartFragment = new CartFragment();
        mineFragment = new MineFragment();
        setIdColor = new RadioButton[4];
        setIdColor[0] = mHome;
        setIdColor[1] = mClazz;
        setIdColor[2] = mCart;
        setIdColor[3] = mMine;
    }

    @Override
    public void initData() {
        manager = getSupportFragmentManager();
        FragmentTransaction bt = manager.beginTransaction();
        bt.add(R.id.frame_layout,homeFragment,"home");
        bt.add(R.id.frame_layout,classFragment,"clazz");
        bt.add(R.id.frame_layout,cartFragment,"cart");
        bt.add(R.id.frame_layout,mineFragment,"mine");
        bt.show(homeFragment).hide(classFragment).hide(cartFragment).hide(mineFragment);
        bt.commit();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View view) {
        FragmentTransaction bt = manager.beginTransaction();
        switch (view.getId()){
            case R.id.image_home:
                bt.show(homeFragment).hide(classFragment).hide(cartFragment).hide(mineFragment);
                setTextColor(0);
                bt.commit();
                break;
            case R.id.image_class:
                bt.show(classFragment).hide(homeFragment).hide(cartFragment).hide(mineFragment);
                setTextColor(1);
                bt.commit();
                break;
            case R.id.image_cart:
                bt.show(cartFragment).hide(classFragment).hide(homeFragment).hide(mineFragment);
                setTextColor(2);
                bt.commit();
                break;
            case R.id.image_mine:
                bt.show(mineFragment).hide(classFragment).hide(cartFragment).hide(homeFragment);
                setTextColor(3);
                bt.commit();
                break;
        }
    }

    private void setTextColor(int a) {
        for (int i = 0; i < setIdColor.length; i++) {
            if(i == a){
                setIdColor[a].setTextColor(getResources().getColor(R.color.colorRed));
            }else{
                setIdColor[i].setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }
    }
}
