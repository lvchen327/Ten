package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.api.ApiClient;
import com.example.administrator.myapplication.api.ApiService;
import com.example.administrator.myapplication.bean.CriticContentBean;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/17.
 */

public class CriticContentFragment extends BaseFragment {
    @BindView(R.id.critic_item_i1)
    ImageView criticItemI1;
    @BindView(R.id.critic_item_title)
    TextView criticItemTitle;
    @BindView(R.id.critic_item_author)
    TextView criticItemAuthor;
    @BindView(R.id.critic_item_t1)
    TextView criticItemT1;
    @BindView(R.id.critic_item_i2)
    ImageView criticItemI2;
    @BindView(R.id.critic_item_t2)
    TextView criticItemT2;
    @BindView(R.id.critic_item_realtitle)
    TextView criticItemRealtitle;
    @BindView(R.id.critic_item_i3)
    ImageView criticItemI3;
    @BindView(R.id.critic_item_t3)
    TextView criticItemT3;
    @BindView(R.id.critic_item_i4)
    ImageView criticItemI4;
    @BindView(R.id.critic_item_i5)
    ImageView criticItemI5;
    @BindView(R.id.critic_item_author2)
    TextView criticItemAuthor2;
    @BindView(R.id.critic_item_authorbrief)
    TextView criticItemAuthorbrief;
    @BindView(R.id.critic_t0)
    TextView criticT0;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_critic_item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        setupView();
        return rootView;
    }

    public void setupView() {
        final ApiService apiService = ApiClient.getApiService();
        Call<CriticContentBean> criticContentBean = apiService.getCriticContentBean(id);
        criticContentBean.enqueue(new Callback<CriticContentBean>() {
            @Override
            public void onResponse(Call<CriticContentBean> call, Response<CriticContentBean> response) {
                CriticContentBean bean = response.body();
                criticItemTitle.setText(bean.getTitle());
                criticItemAuthor.setText(String.format("作者:%s | 阅读量:%s", bean.getAuthor(), bean.getTimes()));
                criticItemT1.setText(bean.getText1());
                criticT0.setText("剧情简介");
                criticItemT2.setText(bean.getText2());
                criticItemRealtitle.setText(bean.getRealtitle());
                criticItemT3.setText(bean.getText3()+bean.getText4()+bean.getText5());
                criticItemAuthor2.setText(bean.getAuthor());
                criticItemAuthorbrief.setText(bean.getAuthorbrief());
                setImage(ApiService.BASE_URL+"/"+bean.getImageforplay(),criticItemI1);
                setImage(ApiService.BASE_URL+"/"+bean.getImage1(),criticItemI2);
                setImage(ApiService.BASE_URL+"/"+bean.getImage2(),criticItemI3);
                setImage(ApiService.BASE_URL+"/"+bean.getImage3(),criticItemI4);
                setImage(ApiService.BASE_URL+"/"+bean.getImage4(),criticItemI5);
            }

            @Override
            public void onFailure(Call<CriticContentBean> call, Throwable t) {

            }
        });
    }

    private void setImage(String url,ImageView image) {
        Picasso.with(getContext())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(image);
    }
}