package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.myapplication.fragment.CriticFragment;
import com.example.administrator.myapplication.fragment.DiagramFragment;
import com.example.administrator.myapplication.fragment.NovelFragment;
import com.example.administrator.myapplication.fragment.PersonalFragment;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CriticFragment.CallBackValue {

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
    private int[] date={R.mipmap.date_0,R.mipmap.date_1,R.mipmap.date_2,R.mipmap.date_3,R.mipmap.date_4,R.mipmap.date_5,R.mipmap.date_6,R.mipmap.date_7,R.mipmap.date_8,R.mipmap.date_9};
    private int[] month={R.mipmap.month_1,R.mipmap.month_2,R.mipmap.month_3,R.mipmap.month_4,R.mipmap.month_5,R.mipmap.month_6,R.mipmap.month_7,R.mipmap.month_8,R.mipmap.month_9,R.mipmap.month_10,R.mipmap.month_11,R.mipmap.month_12};
    private int[] week={R.mipmap.week_1,R.mipmap.week_2,R.mipmap.week_3,R.mipmap.week_4,R.mipmap.week_5,R.mipmap.week_6,R.mipmap.week_7};
    private Calendar mDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mGroup.setOnCheckedChangeListener(this);
        mCritic.setChecked(true);
        mDate = Calendar.getInstance();//获取时间
        setDate(0);

    }

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
                titleShow();
                mLogo.setImageResource(R.mipmap.logo_critic);
                switchPage(CriticFragment.TAG);
                break;
            case R.id.main_novel:
                titleShow();
                mLogo.setImageResource(R.mipmap.logo_novel);
                switchPage(NovelFragment.TAG);
                break;
            case R.id.main_diagram:
                titleShow();
                mLogo.setImageResource(R.mipmap.logo_diagram);
                switchPage(DiagramFragment.TAG);
                break;
            case R.id.main_personal:
                switchPage(PersonalFragment.TAG);
                mTitle.setVisibility(View.GONE);
                break;
        }
    }

    private void titleShow() {
        if (mTitle.getVisibility() == View.GONE) {
            mTitle.setVisibility(View.VISIBLE);
        }

    }

    public void SendMessageValue(int intValue) {
        setDate(intValue);
    }

    private void setDate(int intValu) {
        mDateLeft.setImageResource(date[(mDate.get(Calendar.DATE)-intValu)/10]);
        mDateRight.setImageResource(date[(mDate.get(Calendar.DATE)-intValu)%10]);
        mDateMonth.setImageResource(month[mDate.get(Calendar.MONTH)]);
        int week1 = mDate.get(Calendar.DAY_OF_WEEK) - intValu-1;
        while (week1 <1){
            week1+=7;
        }
        mDateWeek.setImageResource(week[week1-1]);
    }

}
