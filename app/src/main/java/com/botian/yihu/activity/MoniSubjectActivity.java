package com.botian.yihu.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniTest;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoniSubjectActivity extends RxAppCompatActivity {
    @BindView(R.id.tv_sex11)
    TextView tvSex;
    @BindView(R.id.start_test)
    Button startTest;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    //popup窗口里的ListView 下拉列表
    private ListView mTypeLv;
    private ListView mTypeLv1;
    //popup窗口 下拉列表
    private PopupWindow typeSelectPopup;
    private PopupWindow typeSelectPopup1;
    //模拟的假数据 下拉列表
    private List<String> testData;
    private List<String> testData1;
    //数据适配器 下拉列表
    private ArrayAdapter<String> testDataAdapter;
    String subjectNo = 1 + "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moni_subject);
        ButterKnife.bind(this);
        //设置popupWindow右边三角符号大小
        Drawable rightArrow = getResources().getDrawable(R.drawable.ic_arrows_down);
        rightArrow.setBounds(0, 0, 22, 22);
        tvSex.setCompoundDrawables(null, null, rightArrow, null);

    }

    @OnClick({R.id.tv_sex11, R.id.start_test, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sex11:
                //点击控件后显示popup窗口
                initSelectPopup();
                //使用isShowing()检查popup窗口是否在显示状态
                if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
                    typeSelectPopup.showAsDropDown(tvSex, 0, -1);
                }
                break;
            case R.id.start_test:
                Intent intent = new Intent(this, SimulationTestActivity.class);
                intent.putExtra("subjectNo", subjectNo);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
            case R.id.tv_place:
                //点击控件后显示popup窗口
                initSelectPopup1();
                //使用isShowing()检查popup窗口是否在显示状态
                if (typeSelectPopup1 != null && !typeSelectPopup1.isShowing()) {
                    typeSelectPopup1.showAsDropDown(tvPlace, 0, -1);
                }
                break;
        }
    }

    ObserverOnNextListener<MoniTest> listener = new ObserverOnNextListener<MoniTest>() {
        @Override
        public void onNext(MoniTest data) {
            placeData(data.getData());
            //place.setText("所在考场：" + data.getData().get(0).getTypename());
            //typeid = data.getData().get(0).getId() + "";

        }
    };

    /**
     * 初始化popup窗口科目
     */
    private void initSelectPopup() {
        mTypeLv = new ListView(this);
        TestData();
        // 设置适配器
        testDataAdapter = new ArrayAdapter<String>(this, R.layout.popup_text_item, testData);
        mTypeLv.setAdapter(testDataAdapter);
        mTypeLv.setDividerHeight(0);//去掉listview分割线
        // 设置ListView点击事件监听
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = testData.get(position);
                // 把选择的数据展示对应的TextView上
                tvSex.setText(value);
                title.setText(value + "资格考试");
                // 选择完后关闭popup窗口
                subjectNo = position + 1 + "";
                typeSelectPopup.dismiss();
                ApiMethods.getMoniTestPlace(new ProgressObserver<MoniTest>(MoniSubjectActivity.this, listener), "mid,eq," + subjectNo, MoniSubjectActivity.this);

            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, tvSex.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_filter_down);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
    }
    /**
     * 初始化popup窗口考场
     */
    private void initSelectPopup1() {
        mTypeLv1 = new ListView(this);
        // 设置适配器
        testDataAdapter = new ArrayAdapter<String>(this, R.layout.popup_text_item, testData1);
        mTypeLv1.setAdapter(testDataAdapter);
        mTypeLv1.setDividerHeight(0);//去掉listview分割线
        // 设置ListView点击事件监听
        mTypeLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = testData.get(position);
                // 把选择的数据展示对应的TextView上
                //tvSex.setText(value);
                //title.setText(value + "资格考试");
                // 选择完后关闭popup窗口
                //subjectNo = position + 1 + "";
                typeSelectPopup.dismiss();

            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, tvSex.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_filter_down);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
    }

    /**
     * 数据
     */
    private void TestData() {
        testData = new ArrayList<>();
        testData.add("护士执业");
        testData.add("护师执业");
        testData.add("主管护师");
    }
    /**
     * 数据
     */
    private void placeData(List<MoniTest.DataBean> data) {
        testData1 = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            testData1.add(data.get(i).getTypename());
        }
    }

}
