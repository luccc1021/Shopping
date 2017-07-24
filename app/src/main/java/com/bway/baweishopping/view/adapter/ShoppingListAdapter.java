package com.bway.baweishopping.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.SpList;
import com.bway.baweishopping.modle.bean.User;
import com.bway.baweishopping.view.activity.SpDetailsActivity;

import java.util.List;

/**
 * Created by 卢程
 * 2017/7/21.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<SpList.DatasBean.GoodsListBean> mList;
    private Context context;
    private View view;

    public ShoppingListAdapter(Context context, List<SpList.DatasBean.GoodsListBean> list) {
        this.context = context;
         mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.sp_list_listview_item, parent ,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(mList.get(position).getGoods_image_url()).into(holder.image);
        holder.textView1.setText(mList.get(position).getGoods_name());
        holder.textView2.setText("￥"+mList.get(position).getGoods_price());
        holder.textView3.setText("已售 "+mList.get(position).getGoods_salenum());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpDetailsActivity.class);
                intent.putExtra("id", mList.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

 class ViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView textView1,textView2,textView3;
    public ViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.sp_listitem_image);
        textView1 = itemView.findViewById(R.id.sp_listitem_title);
        textView2 = itemView.findViewById(R.id.sp_listitem_money);
        textView3 = itemView.findViewById(R.id.sp_listitem_message);
    }
}
}
