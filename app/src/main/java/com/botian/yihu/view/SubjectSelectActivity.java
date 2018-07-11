package com.botian.yihu.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @BindView(R.id.container)
    LinearLayout container;
    //flowlayout数据
    //private static List<String> mVals = new ArrayList<>();
    //private List<Integer> mNo = new ArrayList<>();
    List<SubjectBean.DataBean> list = new ArrayList<>();//一级列表数据
    List<SubjectBean.DataBean> list2 = new ArrayList<>();//二级列表数据
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
        pref = getSharedPreferences("subjectSelectData", MODE_PRIVATE);
        no = pref.getInt("subjectNo", 1);
        SubjectPresenter subjectpresenter = new SubjectPresenter(this);
        subjectpresenter.presenter(this, this);
    }

    public void intitView() {
        for (int i=0;i<list.size();i++){
            //一级列表
            View view = View.inflate(this, R.layout.item_subject, null);
            TextView text=view.findViewById(R.id.text);
            text.setText(list.get(i).getName());
            container.addView(view);
            intitView2(list.get(i).getId());
        }

    }
    public void intitView2(int id) {
        View view = View.inflate(this, R.layout.item_subject2, null);
        TagFlowLayout mFlowLayout = view.findViewById(R.id.id_flowlayout);
        List<String> data = new ArrayList<>();
        List<Integer> data1 = new ArrayList<>();
        for (int i=0;i<list2.size();i++){
            //二级列表
            if (list2.get(i).getPid()==id){
                data.add(list2.get(i).getName());
                data1.add(list2.get(i).getId());
            }else {
                break;
            }

        }
        setFlowlayout(mFlowLayout,id,data,data1);
        //mVals.clear();
        container.addView(view);

    }

    private void setFlowlayout(final TagFlowLayout layout, final int id, final List<String> data, final List<Integer> data1  ) {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        //final TagFlowLayout mFlowLayout = findViewById(R.id.id_flowlayout);
        TagAdapter tagAdapter = new TagAdapter<String>(data) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_flowlayout,
                        layout, false);
                tv.setText(s);
                return tv;
            }
        };
        //if (no != 0) {
            //tagAdapter.setSelectedList(no - 1);
        //} else {
            //tagAdapter.setSelectedList(0);
        //}
        layout.setAdapter(tagAdapter);
        //点击事件
        layout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                editor = getSharedPreferences("subjectSelectData", MODE_PRIVATE).edit();
                editor.putInt("subjectNo2", data1.get(position));
                editor.putInt("subjectNo", id);
                editor.putString("subjectName", data.get(position));
                editor.apply();
                finish();
                return true;
            }
        });
    }

    @Override
    public void view(SubjectBean data) {
        for (int i = 0; i < data.getData().size(); i++) {
            //mVals.add(data.getData().get(i).getName());
            //mNo.add(data.getData().get(i).getId());
            if (data.getData().get(i).getPid() == 0) {
                list.add(data.getData().get(i));
            } else {
                list2.add(data.getData().get(i));
            }
        }
        intitView();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
