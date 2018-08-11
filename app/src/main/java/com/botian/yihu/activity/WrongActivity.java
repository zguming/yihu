package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.database.WrongData;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WrongActivity extends RxAppCompatActivity {
    @BindView(R.id.linear_chapter_practice)
    LinearLayout linearChapterPractice;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.linear_)
    LinearLayout linear;
    @BindView(R.id.img_wrong_set1)
    ImageView imgWrongSet1;
    @BindView(R.id.text1)
    TextView text1;

    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.wright1)
    ImageView wright1;
    @BindView(R.id.img_wrong_set2)
    ImageView imgWrongSet2;
    @BindView(R.id.wright2)
    ImageView wright2;
    @BindView(R.id.wrong_cycle1)
    ImageView wrongCycle1;
    @BindView(R.id.cycle1)
    TextView cycle1;
    @BindView(R.id.percent1)
    TextView percent1;
    @BindView(R.id.wrong_cycle2)
    ImageView wrongCycle2;
    @BindView(R.id.cycle2)
    TextView cycle2;
    @BindView(R.id.percent2)
    TextView percent2;
    @BindView(R.id.wrong_cycle3)
    ImageView wrongCycle3;
    @BindView(R.id.cycle3)
    TextView cycle3;
    @BindView(R.id.percent3)
    TextView percent3;
    @BindView(R.id.top_text)
    TextView topText;
    private List<WrongData> practiceList1;
    private List<WrongData> practiceList2;
    private List<WrongData> practiceList3;
    private List<WrongData> practiceList4;
    private List<WrongData> practiceList5;
    private int selected = 1;
    private int type = 1;
    private int bet1 = 0;
    private int bet2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                practiceList1 = DataSupport.where("kind=?  and type=?", "2", type + "").find(WrongData.class);
                practiceList2 = DataSupport.where("kind=?  and type=?", "1", type + "").find(WrongData.class);
                emitter.onNext(1);
                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Integer>bindToLifecycle())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {

                        if (practiceList1.size() > 0) {
                            String num1;
                            if (type==1) {
                                num1 = "未掌握 " + practiceList1.size() + "道题";
                            }else if (type==2){
                                num1 = "待强化 " + practiceList1.size() + "道题";
                            }else{
                                num1 = "已消灭 " + practiceList1.size() + "道题";
                            }
                            text1.setText(num1);
                            text1.setTextColor(getResources().getColor(R.color.textColorBlack));
                            wright1.setVisibility(View.VISIBLE);
                            imgWrongSet1.setImageResource(R.drawable.ic_topic_wrong_set_item_mark_normal);
                            bet1 = 1;
                        } else {
                            text1.setText("暂未掌握题目");
                            wright1.setVisibility(View.INVISIBLE);
                            imgWrongSet1.setImageResource(R.drawable.ic_topic_wrong_set_item_mark_unable);
                            bet1 = 0;
                        }
                        if (practiceList2.size() > 0) {
                            String num2;
                            if (type==1) {
                                num2 = "未掌握 " + practiceList2.size() + "道题";
                            }else if (type==2){
                                num2 = "待强化 " + practiceList2.size() + "道题";
                            }else{
                                num2 = "已消灭 " + practiceList2.size() + "道题";
                            }
                            text2.setTextColor(getResources().getColor(R.color.textColorBlack));
                            text2.setText(num2);
                            wright2.setVisibility(View.VISIBLE);
                            imgWrongSet2.setImageResource(R.drawable.ic_topic_wrong_set_item_mark_normal);
                            bet2 = 1;
                        } else {
                            text2.setText("暂未掌握题目");
                            wright2.setVisibility(View.INVISIBLE);
                            imgWrongSet2.setImageResource(R.drawable.ic_topic_wrong_set_item_mark_unable);
                            bet2 = 0;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        //Log.d(TAG, "complete");
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                practiceList3 = DataSupport.where("type=?", 1 + "").find(WrongData.class);
                practiceList4 = DataSupport.where("type=?", 2 + "").find(WrongData.class);
                practiceList5 = DataSupport.where("type=?", 3 + "").find(WrongData.class);
                emitter.onNext(1);
                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Integer>bindToLifecycle())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        String a1 = practiceList3.size() + "";
                        String a2 = practiceList4.size() + "";
                        String a3 = practiceList5.size() + "";
                        cycle1.setText(a1);
                        cycle2.setText(a2);
                        cycle3.setText(a3);

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        //Log.d(TAG, "complete");
                    }
                });
        initView();

    }

    @OnClick({R.id.linear_chapter_practice, R.id.back, R.id.linear_, R.id.cycle1, R.id.cycle2, R.id.cycle3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_chapter_practice:
                if (bet1==1) {
                    Intent intent = new Intent(this, WrongAnswerActivity.class);
                    intent.putExtra("zhenti", "2");
                    intent.putExtra("type", type);
                    startActivity(intent);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.linear_:
                if (bet2==1) {
                    Intent intent23 = new Intent(this, WrongAnswerActivity.class);
                    intent23.putExtra("zhenti", "1");
                    intent23.putExtra("type", type);
                    startActivity(intent23);
                }
                break;
            case R.id.cycle1:
                if (selected != 1) {
                    wrongCycle1.setVisibility(View.VISIBLE);
                    wrongCycle2.setVisibility(View.INVISIBLE);
                    wrongCycle3.setVisibility(View.INVISIBLE);
                    selected = 1;
                    type = 1;
                    topText.setText("未掌握错题分布");
                    practiceList1.clear();
                    practiceList2.clear();
                    initView();
                }
                break;
            case R.id.cycle2:
                if (selected != 2) {
                    wrongCycle2.setVisibility(View.VISIBLE);
                    wrongCycle1.setVisibility(View.INVISIBLE);
                    wrongCycle3.setVisibility(View.INVISIBLE);
                    selected = 2;
                    type = 2;
                    topText.setText("待强化错题分布");
                    practiceList1.clear();
                    practiceList2.clear();
                    initView();
                }
                break;
            case R.id.cycle3:
                if (selected != 3) {
                    wrongCycle3.setVisibility(View.VISIBLE);
                    wrongCycle1.setVisibility(View.INVISIBLE);
                    wrongCycle2.setVisibility(View.INVISIBLE);
                    selected = 3;
                    type = 3;
                    topText.setText("已消灭错题分布");
                    practiceList1.clear();
                    practiceList2.clear();
                    initView();
                }
                break;
        }
    }

}
