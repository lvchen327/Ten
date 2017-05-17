package com.example.administrator.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CriticFragmentPager;
import com.example.administrator.myapplication.api.ApiClient;
import com.example.administrator.myapplication.api.ApiService;
import com.example.administrator.myapplication.bean.CriticBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/17.
 */

public class CriticFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    public static final String TAG = CriticFragment.class.getName();
    @BindView(R.id.critic_pager)
    ViewPager mPager;
    private CallBackValue callBackValue;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_critic;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        setupView();
        /**
         * 滑动监听器OnPageChangeListener
         *  OnPageChangeListener这个接口需要实现三个方法：onPageScrollStateChanged，onPageScrolled ，onPageSelected
         *      1、onPageScrollStateChanged(int arg0) 此方法是在状态改变的时候调用。
         *          其中arg0这个参数有三种状态（0，1，2）
         *              arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做
         *              当页面开始滑动的时候，三种状态的变化顺序为1-->2-->0
         *      2、onPageScrolled(int arg0,float arg1,int arg2) 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直被调用。
         *          其中三个参数的含义分别为：
         *              arg0 :当前页面，及你点击滑动的页面
         *              arg1:当前页面偏移的百分比
         *              arg2:当前页面偏移的像素位置
         *      3、onPageSelected(int arg0) 此方法是页面跳转完后被调用，arg0是你当前选中的页面的Position（位置编号）
         */
        mPager.addOnPageChangeListener(this);

        return rootView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        //传值
        callBackValue.SendMessageValue(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (CallBackValue) getActivity();
    }
    public interface CallBackValue{
        public void SendMessageValue(int intValue);
    }

    public void setupView(){
        ApiService apiService = ApiClient.getApiService();
        Call<CriticBean> criticBean = apiService.getCriticBean();
        criticBean.enqueue(new Callback<CriticBean>() {
            @Override
            public void onResponse(Call<CriticBean> call, Response<CriticBean> response) {
                List<CriticBean.CriticListBean> result = response.body().getResult();
                mPager.setAdapter(new CriticFragmentPager(getChildFragmentManager(),result));
            }
            @Override
            public void onFailure(Call<CriticBean> call, Throwable t) {
            }
        });
    }
}