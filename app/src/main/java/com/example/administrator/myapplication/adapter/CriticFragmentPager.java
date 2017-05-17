package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.myapplication.bean.CriticBean;
import com.example.administrator.myapplication.fragment.CriticContentFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class CriticFragmentPager extends FragmentStatePagerAdapter {
    private List<CriticBean.CriticListBean> data;
    private int id;
    private Context content;
    public CriticFragmentPager(FragmentManager fm,List<CriticBean.CriticListBean> data) {
        super(fm);
        this.data=data;
    }


    @Override
    public Fragment getItem(int position) {
        CriticContentFragment fragment = new CriticContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",data.get(position).getId());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}