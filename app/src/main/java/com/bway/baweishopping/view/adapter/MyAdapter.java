package com.bway.baweishopping.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.Money;
import com.bway.baweishopping.modle.bean.User;
import com.bway.baweishopping.view.IView.MyItemClickListener;
import com.bway.baweishopping.view.IView.MyView;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by 卢程
 * 2017/7/17.
 */

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private ViewHolder viewHolder;
    private List<User> list;
    private HashMap<Integer, Boolean> map = new HashMap<>();
    private MyItemClickListener mItemClickListener;
    private View view;
    private int sum = 0;
    private getMoneyListener getMoneyListener;
    private Money money;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Boolean> map) {
        this.map = map;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        money = new Money();
        for (int i = 0; i < list.size(); i++) {
            map.put(i, false);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(list.get(position).getName());
        viewHolder.textView2.setText(list.get(position).getPrice()+"");

        viewHolder.checkBox.setChecked(map.get(position));
//        Log.e(TAG, "map: " + map.get(position) );
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.checkBox.setChecked(!map.get(position));
                map.put(position,viewHolder.checkBox.isChecked());
//                Log.e(TAG, "onClick: " + position + viewHolder.checkBox.isChecked() );
            }
        });

        //获取数量
        viewHolder.myView.setOnNumberChangeListener(new MyView.OnNumberChangeListener() {
            @Override
            public void onNumberChangeListener(View view, int count) {

//                int price = list.get(position).getPrice();
//                int one = price * count;
//                sum += one;

//                Iterator<Map.Entry<Integer, Boolean>> it = map.entrySet().iterator();
//                while (it.hasNext()) {
//                    Map.Entry<Integer, Boolean> entry = it.next();
//                    if (entry.getValue()) {
//                        int key = entry.getKey();
//                        Log.e(TAG, "onNumberChangeListener111: " + key);
//                    }
//                }
                getMoneyListener.getSum(count, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onClick(View view) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        MyView myView;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.title);
            textView2 = itemView.findViewById(R.id.message);
            myView = itemView.findViewById(R.id.countview);
        }
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setSumListener(getMoneyListener getMoneyListener) {
        this.getMoneyListener = getMoneyListener;
    }

    public interface getMoneyListener {
        void getSum(int sum, int position);
    }
}
