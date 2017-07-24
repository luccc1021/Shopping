package com.bway.baweishopping.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.view.activity.ShoppingListActivity;

import java.util.List;

/**
 * Created by 卢程
 * 2017/7/19.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private Context mContext;

    /**
     * 每个分组下的每个子项的 GridView 数据集合
     */
    private List<String> itemGridList;

    public MyGridViewAdapter(Context mContext, List<String> itemGridList) {
        this.mContext = mContext;
        this.itemGridList = itemGridList;
    }

    @Override
    public int getCount() {
        return itemGridList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemGridList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.gridview_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_gridview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(itemGridList.get(position));
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShoppingListActivity.class);
                intent.putExtra("xinxi",itemGridList.get(position));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }
}
