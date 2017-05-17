package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.administrator.myapplication.fragment.CriticContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class CriticFragmentPager extends FragmentStatePagerAdapter {
    private List<String> data=new ArrayList<>();
    private int id;
    private Context content;
    public CriticFragmentPager(FragmentManager fm,int id) {
        super(fm);
        for (int i=0;i<9;i++){
            data.add(i+"");
        }
        this.id=id;
    }


    @Override
    public Fragment getItem(int position) {
        String s = data.get(position);
        CriticContentFragment fragment = new CriticContentFragment();
        Log.e("1111", "getItem: "+id );
        return fragment;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}
