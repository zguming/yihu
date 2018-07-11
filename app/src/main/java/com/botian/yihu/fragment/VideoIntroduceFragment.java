package com.botian.yihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//资讯
public class VideoIntroduceFragment extends Fragment {
    String title1;
    String price1;
    String content1;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        title1 = bundle.getString("ARGS");
        price1 = bundle.getString("ARGS1");
        content1 = bundle.getString("ARGS2");
        title.setText(title1);
        price.setText(price1);
        content.setText(content1);

    }

    public static VideoIntroduceFragment newInstance(String title, String price, String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", title);
        args.putString("ARGS1", price);
        args.putString("ARGS2", content);
        VideoIntroduceFragment fragment = new VideoIntroduceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
