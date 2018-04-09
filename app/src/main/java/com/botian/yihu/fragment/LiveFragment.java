package com.botian.yihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botian.yihu.R;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
//直播
public class LiveFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static LiveFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

