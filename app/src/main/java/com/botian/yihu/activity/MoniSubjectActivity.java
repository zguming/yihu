package com.botian.yihu.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniTest;
import com.botian.yihu.beans.SearchMoniBuy;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.MoniBuyEvent;
import com.botian.yihu.eventbus.VideoBuyEvent;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.phone)
    Button phone;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.price)
    TextView price;
    //popup窗口里的ListView 下拉列表
    private ListView mTypeLv;
    private ListView mTypeLv1;
    //popup窗口 下拉列表
    private PopupWindow typeSelectPopup;
    private PopupWindow typeSelectPopup1;
    //模拟的假数据 下拉列表
    private List<String> testData;
    private List<String> testData1 = new ArrayList<>();
    private List<Integer> testData2 = new ArrayList<>();
    private List<String> testData3 = new ArrayList<>();
    private int idq;
    //数据适配器 下拉列表
    private ArrayAdapter<String> testDataAdapter;
    private ArrayAdapter<String> testDataAdapter1;
    int subjectNo;
    private ACache mCache;
    private UserInfo userInfo;
    String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moni_subject);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        //设置popupWindow右边三角符号大小
        Drawable rightArrow = getResources().getDrawable(R.drawable.ic_arrows_down);
        rightArrow.setBounds(0, 0, 22, 22);
        tvSex.setCompoundDrawables(null, null, rightArrow, null);
        //设置popupWindow右边三角符号大小
        Drawable rightArrow1 = getResources().getDrawable(R.drawable.ic_arrows_down);
        rightArrow1.setBounds(0, 0, 22, 22);
        tvPlace.setCompoundDrawables(null, null, rightArrow1, null);
    }

    @OnClick({R.id.tv_sex11, R.id.start_test, R.id.back, R.id.tv_place, R.id.phone, R.id.buy})
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
                String tvS = tvSex.getText().toString();
                String tvP = tvPlace.getText().toString();
                if (!tvS.equals("")) {
                    if (!tvP.equals("")) {
                        searchBuy2();

                    } else {
                        Toast.makeText(this, "请选择考场", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this, "请选择科目", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_place:
                String tvS1 = tvSex.getText().toString();
                if (tvS1.equals("")) {
                    Toast.makeText(MoniSubjectActivity.this, "请选择科目", Toast.LENGTH_SHORT).show();

                } else {
                    //点击控件后显示popup窗口
                    initSelectPopup1();
                    //使用isShowing()检查popup窗口是否在显示状态
                    if (typeSelectPopup1 != null && !typeSelectPopup1.isShowing()) {
                        typeSelectPopup1.showAsDropDown(tvPlace, 0, -1);
                    }
                }
                break;
            case R.id.phone:
                final Dialog dialog2 = new Dialog(this, R.style.NormalDialogStyle);

                View view2 = View.inflate(this, R.layout.dialog_buy, null);
                LinearLayout lnPhone = view2.findViewById(R.id.ln_phone);
                LinearLayout lnQq = view2.findViewById(R.id.ln_qq);

                dialog2.setContentView(view2);
                //使得点击对话框外部消失对话框
                dialog2.setCanceledOnTouchOutside(true);
                //设置对话框的大小
                Window dialogWindow2 = dialog2.getWindow();
                dialogWindow2.setWindowAnimations(R.style.bottom_menu_animation);
                WindowManager.LayoutParams lp2 = dialogWindow2.getAttributes();
                //lp2.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.85f);
                lp2.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp2.gravity = Gravity.BOTTOM;
                //dialogWindow2.setAttributes(lp2);
                lnPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog2.dismiss();

                    }
                });
                lnQq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog2.dismiss();
                    }
                });
                dialog2.show();
                break;
            case R.id.buy:
                ObserverOnNextListener<SearchMoniBuy> listenerdd1 = new ObserverOnNextListener<SearchMoniBuy>() {
                    @Override
                    public void onNext(SearchMoniBuy data) {
                        //Toast.makeText(MoniSubjectActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                        if (data.getCode() == 200) {
                            buy.setText("已购买");
                            Toast.makeText(MoniSubjectActivity.this, "已购买过，无需再次购买", Toast.LENGTH_SHORT).show();

                        } else {
                            String tvS2 = tvSex.getText().toString();
                            String tvP2 = tvPlace.getText().toString();
                            if (!tvS2.equals("")) {
                                if (!tvP2.equals("")) {
                                    Intent intent54 = new Intent(MoniSubjectActivity.this, MoniBuyActivity.class);
                                    intent54.putExtra("subject", tvS2);
                                    intent54.putExtra("place", tvP2);
                                    intent54.putExtra("id", idq + "");
                                    intent54.putExtra("subjectNo", subjectNo + "");
                                    intent54.putExtra("money", money);
                                    startActivity(intent54);
                                } else {
                                    Toast.makeText(MoniSubjectActivity.this, "请选择考场", Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                Toast.makeText(MoniSubjectActivity.this, "请选择科目", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                };
                ApiMethods.searchMoniBuy(new MyObserver<SearchMoniBuy>(listenerdd1), userInfo.getId() + "", subjectNo + "", idq + "", MoniSubjectActivity.this);

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
                //title.setText(value + "资格考试");
                // 选择完后关闭popup窗口
                subjectNo = position + 2;
                typeSelectPopup.dismiss();
                ApiMethods.getMoniTestPlace(new ProgressObserver<MoniTest>(MoniSubjectActivity.this, listener), MoniSubjectActivity.this);

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
        testDataAdapter1 = new ArrayAdapter<String>(this, R.layout.popup_text_item, testData1);
        mTypeLv1.setAdapter(testDataAdapter1);
        mTypeLv1.setDividerHeight(0);//去掉listview分割线
        // 设置ListView点击事件监听
        mTypeLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 在这里获取item数据
                String value = testData1.get(position);
                // 把选择的数据展示对应的TextView上
                tvPlace.setText(value);
                String money1=testData3.get(position)+"金币";
                price.setText(money1);
                money=testData3.get(position);
                idq = testData2.get(position);
                searchBuy();
                //title.setText(value + "资格考试");
                // 选择完后关闭popup窗口
                //subjectNo = position + 1 + "";
                typeSelectPopup1.dismiss();

            }
        });
        typeSelectPopup1 = new PopupWindow(mTypeLv1, tvPlace.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_filter_down);
        typeSelectPopup1.setBackgroundDrawable(drawable);
        typeSelectPopup1.setFocusable(true);
        typeSelectPopup1.setOutsideTouchable(true);
        typeSelectPopup1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup1.dismiss();
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
        testData1.clear();
        testData2.clear();
        testData3.clear();
        for (int i = 0; i < data.size(); i++) {
            testData1.add(data.get(i).getTypename());
            testData2.add(data.get(i).getId());
            testData3.add(data.get(i).getMoney());
        }
    }

    public void searchBuy() {
        ObserverOnNextListener<SearchMoniBuy> listenerdd11 = new ObserverOnNextListener<SearchMoniBuy>() {
            @Override
            public void onNext(SearchMoniBuy data) {
                //Toast.makeText(MoniSubjectActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                if (data.getCode() == 200) {
                    buy.setText("已购买");
                }
            }
        };
        ApiMethods.searchMoniBuy(new MyObserver<SearchMoniBuy>(listenerdd11), userInfo.getId() + "", subjectNo + "", idq + "", MoniSubjectActivity.this);

    }
    public void searchBuy2() {
        ObserverOnNextListener<SearchMoniBuy> listenerdd = new ObserverOnNextListener<SearchMoniBuy>() {
            @Override
            public void onNext(SearchMoniBuy data) {
                //Toast.makeText(MoniSubjectActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                if (data.getCode() == 200) {
                    String tvP = tvPlace.getText().toString();
                    Intent intent = new Intent(MoniSubjectActivity.this, SimulationTestActivity.class);
                    intent.putExtra("place", tvP);
                    intent.putExtra("id", idq + "");
                    intent.putExtra("subjectNo", subjectNo + "");
                    startActivity(intent);
                }else{
                    Toast.makeText(MoniSubjectActivity.this, "请先购买", Toast.LENGTH_SHORT).show();

                }
            }
        };
        ApiMethods.searchMoniBuy2(new ProgressObserver<SearchMoniBuy>(MoniSubjectActivity.this,listenerdd), userInfo.getId() + "", subjectNo + "", idq + "", MoniSubjectActivity.this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MoniBuyEvent messageEvent) {
        buy.setText("已购买");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
