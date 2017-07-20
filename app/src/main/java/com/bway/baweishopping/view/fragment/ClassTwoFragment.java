package com.bway.baweishopping.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.ChildData;
import com.bway.baweishopping.modle.bean.ClassTwoData;
import com.bway.baweishopping.presenter.MainPresenter;
import com.bway.baweishopping.view.IView.IClassView;
import com.bway.baweishopping.view.adapter.MyExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;
import static com.bway.baweishopping.R.layout.item;

/**
 * Created by 卢程
 * 2017/7/19.
 */

public class ClassTwoFragment extends BaseFragment implements IClassView<ClassTwoData>{

    private TextView mText;
    private ExpandableListView expandableListView;
    private ArrayList<String> key0,key00;
    private List<String> mList;
    private List<String> childList;
    private List<ClassTwoData.DatasBean.ClassListBean> mChild;
    private List<String> mChild2;
    private int key1;
    private MainPresenter mainPresenter;
    private List<List<String>> itemList;
    private MyExpandableListViewAdapter adapter;
    private String child;
    private String url;

    @Override
    void initData() {
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);


        Bundle bundle = getArguments();
        key1 = bundle.getInt("key1");
        key0 = bundle.getStringArrayList("key0");
        key00 = bundle.getStringArrayList("key00");
        String[] split = key0.get(key1).split("/");

        for (int i = 0; i < split.length; i++) {
            mList.add(split[i]);
        }
        for (int i = 0; i < mList.size(); i++) {
            url = "http://169.254.65.152/mobile/index.php?act=goods_class&gc_id="+key1+"&gc_id="+key00.get(i);
            childList.add(url);
            Log.e("========", "onGroupClick: "+ url );
            mainPresenter.loadDataFromServer(url,ClassTwoData.class,0);
        }

    }

    @Override
    void initView(View view) {
        expandableListView = view.findViewById(R.id.expand_listview);
        childList = new ArrayList<>();
        mList = new ArrayList<>();
        mChild = new ArrayList<>();
        mChild2 = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    @Override
    int getContentView() {
        return R.layout.class_fragment_two;
    }

    @Override
    public void isSuccess(ClassTwoData classTwoData,int id) {

        mChild.clear();
        mChild2 = new ArrayList<>();
        List<ClassTwoData.DatasBean.ClassListBean> class_list = classTwoData.getDatas().getClass_list();
        mChild.addAll(class_list);
        for (int j = 0; j < mChild.size(); j++) {
            String gc_name = mChild.get(j).getGc_name();
            mChild2.add(gc_name);
        }
//        for (int i = 0; i < childList.size(); i++) {
//
//        }
        itemList.add(mChild2);

        adapter = new MyExpandableListViewAdapter(getContext(), mList, itemList);
        expandableListView.setAdapter(adapter);

//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(Expandabl                                                                                                                                     eListView expandableListView, View view, int i, long l) {
//
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });

    }

    @Override
    public void err(int code, String err) {

    }
}
