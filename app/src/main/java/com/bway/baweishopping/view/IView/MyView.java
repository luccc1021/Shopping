package com.bway.baweishopping.view.IView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bway.baweishopping.R;


/**
 * Created by 卢程
 * 2017/7/17.
 */

public class MyView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private Button mBtn_decrease;
    private Button mBtn_increase;
    private TextView mTv_number;
    private OnNumberChangeListener onNumberChangeListener;
    private int number = 0;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.my_view, this);

        mBtn_decrease = (Button) findViewById(R.id.btn_decrease);
        mBtn_increase = (Button) findViewById(R.id.btn_increase);
        mTv_number = (TextView) findViewById(R.id.tv_number);

        mBtn_decrease.setOnClickListener(this);
        mBtn_increase.setOnClickListener(this);
        mTv_number.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_decrease:
                if (number > 0) {
                    number--;
                    mTv_number.setText(String.valueOf(number));
                }
                break;
            case R.id.btn_increase:
                    number++;
                    mTv_number.setText(String.valueOf(number));
                break;
        }
        if (onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChangeListener(this, number);
        }
        mTv_number.clearFocus();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

    public interface OnNumberChangeListener {
        void onNumberChangeListener(View view, int count);
    }
}
