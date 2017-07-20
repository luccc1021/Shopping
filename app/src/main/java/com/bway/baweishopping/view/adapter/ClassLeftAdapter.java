package com.bway.baweishopping.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.ClassData;

import java.util.List;

/**
 * Created by 卢程
 * 2017/7/19.
 */

public class ClassLeftAdapter extends BaseAdapter{
    private List<ClassData.DatasBean.ClassListBean> mList;
    private Context context;

    public ClassLeftAdapter(List<ClassData.DatasBean.ClassListBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder Viewholder;
        if (convertView == null) {
            Viewholder = new ViewHolder();
            convertView = View.inflate(context, R.layout.class_item, null);
            Viewholder.imageView = convertView.findViewById(R.id.image_class_left);
            Viewholder.textView = convertView.findViewById(R.id.text_class_left);
            convertView.setTag(Viewholder);
        } else {
            Viewholder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(mList.get(position).getImage()).into(Viewholder.imageView);
        Viewholder.textView.setText(mList.get(position).getGc_name());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
