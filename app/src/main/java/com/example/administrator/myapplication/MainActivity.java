package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.myapplication.fragment.CriticFragment;
import com.example.administrator.myapplication.fragment.DiagramFragment;
import com.example.administrator.myapplication.fragment.NovelFragment;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_logo)
    ImageView mLogo;
    @BindView(R.id.main_date_left)
    ImageView mDateLeft;
    @BindView(R.id.main_date_right)
    ImageView mDateRight;
    @BindView(R.id.main_date_week)
    ImageView mDateWeek;
    @BindView(R.id.main_date_month)
    ImageView mDateMonth;
    @BindView(R.id.main_title)
    FrameLayout mTitle;
    @BindView(R.id.main_more)
    ImageView mMore;
    @BindView(R.id.main_contents)
    FrameLayout mContents;
    @BindView(R.id.main_critic)
    RadioButton mCritic;
    @BindView(R.id.activity_main)
    FrameLayout activityMain;
    @BindView(R.id.main_bottom)
    RadioGroup mGroup;

    private Fragment mShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mGroup.setOnCheckedChangeListener(this);
        mCritic.setChecked(true);

    }

//    @OnClick({R.id.main_critic, R.id.main_novel, R.id.main_diagram, R.id.main_personal})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_critic:
//                mLogo.setImageResource(R.mipmap.logo_critic);
//                switchPage(CriticFragment.TAG);
//                break;
//            case R.id.main_novel:
//                mLogo.setImageResource(R.mipmap.logo_novel);
//                switchPage(NovelFragment.TAG);
//                break;
//            case R.id.main_diagram:
//                mLogo.setImageResource(R.mipmap.logo_diagram);
//                switchPage(DiagramFragment.TAG);
//                break;
//            case R.id.main_personal:
//                break;
//        }
//    }

    //页面 切换
    private void switchPage(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        mShowFragment = fm.findFragmentByTag(tag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            try {
                mShowFragment = (Fragment) Class.forName(tag).getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.main_contents, mShowFragment, tag);
        }
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_critic:
                mLogo.setImageResource(R.mipmap.logo_critic);
                switchPage(CriticFragment.TAG);
                break;
            case R.id.main_novel:
                mLogo.setImageResource(R.mipmap.logo_novel);
                switchPage(NovelFragment.TAG);
                break;
            case R.id.main_diagram:
                mLogo.setImageResource(R.mipmap.logo_diagram);
                switchPage(DiagramFragment.TAG);
                break;
            case R.id.main_personal:
                break;
        }
    }
}
