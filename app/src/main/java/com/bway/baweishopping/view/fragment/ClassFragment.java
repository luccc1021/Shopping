package com.bway.baweishopping.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.bway.baweishopping.R;
import com.bway.baweishopping.modle.bean.ClassData;
import com.bway.baweishopping.modle.bean.ClassTwoData;
import com.bway.baweishopping.presenter.MainPresenter;
import com.bway.baweishopping.view.IView.IClassView;
import com.bway.baweishopping.view.adapter.ClassLeftAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 卢程
 * 2017/7/11.
 */

public class ClassFragment extends BaseFragment implements IClassView<ClassData> {

    private String url = "http://169.254.65.152/mobile/index.php?act=goods_class";
    private MainPresenter mainPresenter;
    private List<ClassData.DatasBean.ClassListBean> mList;
    private List<ClassData.DatasBean.ClassListBean> mList2;
    private List<ClassData.DatasBean.ClassListBean> mList3;
    private ListView mListview;
    private ClassLeftAdapter adapter;
    private ClassTwoFragment twoFragment;
    private ArrayList<String> arrayList ;
    private ArrayList<String> arrayList3 ;
    private ArrayList<String> arrayList2 ;
    private FragmentManager fm;
    int sss;
    private String url2 = "http://169.254.65.152/mobile/index.php?act=goods_class&gc_id=";
    private List<Fragment> fragments;
    private Bundle bundle = new Bundle();

    @Override
    void initData() {
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadDataFromServer(url, ClassData.class, 0);
        adapter = new ClassLeftAdapter(mList, getContext());
        mListview.setAdapter(adapter);

        fm = getFragmentManager();

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sss = i;
                mainPresenter.loadDataFromServer(url2 + arrayList3.get(i), ClassData.class, 1);

            }
        });
    }

    @Override
    void initView(View view) {
        mListview = view.findViewById(R.id.list_class);
        mList = new ArrayList<>();
        mList2 = new ArrayList<>();
        mList3 = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    @Override
    int getContentView() {
        return R.layout.class_fragment;
    }

    @Override
    public void isSuccess(ClassData classData, int id) {
        if (id == 0) {
            arrayList = new ArrayList<>();
            arrayList3 = new ArrayList<>();

            List<ClassData.DatasBean.ClassListBean> class_list = classData.getDatas().getClass_list();
            mList.addAll(class_list);
            for (int i = 0; i < mList.size(); i++) {
                arrayList.add(mList.get(i).getText());
                arrayList3.add(mList.get(i).getGc_id());
                twoFragment = new ClassTwoFragment();
                fragments.add(twoFragment);
            }
        }
        if (id == 1) {
            mList2.clear();
            arrayList2 = new ArrayList<>();
//            arrayList3 = new ArrayList<>();
            List<ClassData.DatasBean.ClassListBean> class_list = classData.getDatas().getClass_list();
            mList2.addAll(class_list);
            for (int i = 0; i < mList2.size(); i++) {
                arrayList2.add(mList2.get(i).getGc_id());
                Log.e("---", "isSuccess: "+ mList2.get(i).getGc_id());
            }
            bundle.putStringArrayList("key0", arrayList);
            bundle.putStringArrayList("id2", arrayList3);
            bundle.putInt("key1", sss);
            fragments.get(sss).setArguments(bundle);
            bundle.putStringArrayList("key00", arrayList2);
            fm.beginTransaction().replace(R.id.frame_class, fragments.get(sss), sss + "").commit();
        }
    }


    @Override
    public void err(int code, String err) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.detach();
    }

}

