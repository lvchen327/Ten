package com.example.administrator.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.AboutusActivity;
import com.example.administrator.myapplication.activity.FavoriteActivity;
import com.example.administrator.myapplication.activity.FeedbackActivity;
import com.example.administrator.myapplication.activity.FontActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/17.
 */

public class PersonalFragment extends BaseFragment {
    public static final String TAG = PersonalFragment.class.getName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.personal_favorite, R.id.personal_font, R.id.personal_aboutus, R.id.personal_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal_favorite:
                startActivity(new Intent(getContext(), FavoriteActivity.class));
                break;
            case R.id.personal_font:
                startActivity(new Intent(getContext(), FontActivity.class));
                break;
            case R.id.personal_aboutus:
                startActivity(new Intent(getContext(), AboutusActivity.class));
                break;
            case R.id.personal_feedback:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
        }
    }
}
