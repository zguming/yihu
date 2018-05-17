package com.botian.yihu.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.beans.SubjectBean;
import com.botian.yihu.contranct.SubjectContranct;
import com.botian.yihu.presenter.SubjectPresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubjectSelectActivity extends RxAppCompatActivity implements SubjectContranct.SubjectView {
    @BindView(R.id.back)
    ImageView back;
    //flowlayout数据
    private List<String> mVals = new ArrayList<>();
    private List<Integer> mNo = new ArrayList<>();
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
        pref=getSharedPreferences("subjectSelectData",MODE_PRIVATE);
        no=pref.getInt("subjectNo",1);
        SubjectPresenter subjectpresenter = new SubjectPresenter(this);
        subjectpresenter.presenter(this,this);
    }

    private void setFlowlayout() {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        final TagFlowLayout mFlowLayout = findViewById(R.id.id_flowlayout);
        TagAdapter tagAdapter = new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_flowlayout,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        if (no != 0){
            tagAdapter.setSelectedList(no-1);
        }else {
            tagAdapter.setSelectedList(0);
        }
        mFlowLayout.setAdapter(tagAdapter);
        //点击事件
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                editor=getSharedPreferences("subjectSelectData",MODE_PRIVATE).edit();
                editor.putInt("subjectNo",mNo.get(position));
                editor.putString("subjectName",mVals.get(position));
                editor.apply();
                finish();
                return true;
            }
        });
    }

    @Override
    public void view(SubjectBean data) {
        for (int i = 0; i < data.getData().size(); i++) {
            mVals.add(data.getData().get(i).getName());
            mNo.add(data.getData().get(i).getId());
        }
        setFlowlayout();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
