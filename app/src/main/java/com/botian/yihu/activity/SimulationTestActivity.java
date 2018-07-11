package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniTest;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimulationTestActivity extends RxAppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.start_test)
    Button startTest;
    @BindView(R.id.place)
    TextView place;
    private ACache mCache;
    private UserInfo userInfo;
    String subjectNo;
    String typeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_test);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        subjectNo = intent.getStringExtra("subjectNo");
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        ObserverOnNextListener<MoniTest> listener = new ObserverOnNextListener<MoniTest>() {
            @Override
            public void onNext(MoniTest data) {
                place.setText("所在考场：" + data.getData().get(0).getTypename());
                typeid = data.getData().get(0).getId() + "";
                name.setText("姓名：" + userInfo.getUsername());
                if (userInfo.getSex() == 0) {
                    sex.setText("性别：女");
                } else {
                    sex.setText("性别：男");
                }
            }
        };
        ApiMethods.getMoniTestPlace(new ProgressObserver<MoniTest>(SimulationTestActivity.this, listener), "mid,eq," + subjectNo, this);
    }

    @OnClick({R.id.title, R.id.name, R.id.sex, R.id.start_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                break;
            case R.id.name:
                break;
            case R.id.sex:
                break;
            case R.id.start_test:
                Intent intent = new Intent(this, SimulationTestActivity2.class);
                intent.putExtra("mid", subjectNo);
                intent.putExtra("typeid", typeid);
                startActivity(intent);
                break;
        }
    }
}
