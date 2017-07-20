package com.bway.baweishopping.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.baweishopping.R;
import com.bway.baweishopping.view.IView.MyGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 卢程
 * 2017/7/19.
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    /**
     * 每个分组的名字的集合
     */
    private List<String> groupList;

    /**
     * 所有分组的所有子项的 GridView 数据集合
     */
    private List<List<String>> itemList;


    public MyExpandableListViewAdapter(Context context, List<String> groupList,
                                       List<List<String>> itemList) {
        mContext = context;
        this.groupList = groupList;
        this.itemList = itemList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup
            parent) {
        if (null == convertView) {
            convertView = View.inflate(mContext, R.layout.class_parent_item, null);
        }

        TextView tvGroup = (TextView) convertView.findViewById(R.id.parent_list);
        // 如果是展开状态，就显示展开的箭头，否则，显示折叠的箭头
        // 设置分组组名
        tvGroup.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView (final int groupPosition, final int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = View.inflate(mContext, R.layout.class_child_list, null);
        }
        MyGridView gridView = convertView.findViewById(R.id.child_list);
        // 创建 GridView 适配器
        MyGridViewAdapter gridViewAdapter = new MyGridViewAdapter(mContext, itemList.get
                (groupPosition));
        gridView.setAdapter(gridViewAdapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
