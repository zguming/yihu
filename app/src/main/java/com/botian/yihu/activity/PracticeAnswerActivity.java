package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.MyFragmentPagerAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.PracticeAnswer;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.database.CollectData;
import com.botian.yihu.database.NoteData;
import com.botian.yihu.database.WrongData;
import com.botian.yihu.eventbus.TopicCardEvent;
import com.botian.yihu.fragment.PracticeAnswerFragment;
import com.botian.yihu.fragment.PracticeAnswerFragment2;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
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

public class PracticeAnswerActivity extends RxAppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ib_left)
    ImageButton ibLeft;
    @BindView(R.id.tv_topic_card)
    TextView tvTopicCard;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;
    @BindView(R.id.ib_right)
    ImageButton ibRight;
    //背题viewpager
    @BindView(R.id.view_pager_answer)
    ViewPager viewPagerAnswer;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.img_topic_card)
    ImageView imgTopicCard;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.img_answer)
    ImageView imgAnswer;
    @BindView(R.id.ln_topic_card)
    LinearLayout lnTopicCard;
    @BindView(R.id.ln_collect)
    LinearLayout lnCollect;
    @BindView(R.id.ln_topic_answer)
    LinearLayout lnTopicAnswer;

    //private MyPagerAdapter mAdapter;
    private List<PracticeAnswer.DataBean> practiceList = new ArrayList<>();
    private ArrayList<Integer> topicCard = new ArrayList<>();//答对0，答错1，当前题2
    private int already = 0;//已答总数
    private int correct1 = 0;//答对总数
    private static List<CollectData> practiceList9 = new ArrayList<>();
    private static List<NoteData> noteList9 = new ArrayList<>();

    private int position = 0;
    private int answer666 = 0;//用于判断背题    ，0为非，1为是
    private ACache mCache;
    private UserInfo userInfo;
    private String hostUrl = "http://btsc.botian120.com";
    private int isCollected = 0;//是否已经收藏，0已收藏，1未收藏
    private List<Fragment> fragmentlist2 = new ArrayList<>();//背题
    private List<Fragment> fragmentlist = new ArrayList<>();//无背题
    private MyFragmentPagerAdapter mAdapter;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_answer);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        Intent intent = getIntent();
        String typeid = intent.getIntExtra("typeid", 0) + "";
        ObserverOnNextListener<PracticeAnswer> listener = new ObserverOnNextListener<PracticeAnswer>() {
            @Override
            public void onNext(PracticeAnswer data) {
                practiceList = data.getData();
                initView();
                //mAdapter = new MyPagerAdapter(viewsList);
                //viewPager.setAdapter(mAdapter);
                firstJudge();
                for (int i = 0; i < practiceList.size(); i++) {
                    topicCard.add(6);//6是初始默认数字，无意义
                }
            }
        };
        ApiMethods.getPracticeAnswer(new ProgressObserver<PracticeAnswer>(this, listener), "typeid,eq,"+typeid, this);
        initView1();
    }

    public void firstJudge() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int position001 = viewPager.getCurrentItem();
                int id1 = practiceList.get(position001).getId();
                int cl1 = practiceList.get(position001).getCl();
                practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id1+"",cl1+"","1").find(CollectData.class);
                //noteList9 = DataSupport.where("topicId=" + id1 + ";" + "cl=" + cl1).find(NoteData.class);
                emitter.onNext(1);

                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(PracticeAnswerActivity.this.<Integer>bindToLifecycle())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        if (practiceList9.size() > 0) {


                            imgCollect.setImageResource(R.drawable.ic_collect_press);
                            isCollected = 0;
                        } else {
                            imgCollect.setImageResource(R.drawable.ic_collect_normal);
                            isCollected = 1;
                        }
                        tvTopicCard.setClickable(true);
                        tvCollect.setClickable(true);
                        tvAnswer.setClickable(true);
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

    public void initView1() {
        tvTopicCard.setClickable(false);
        tvCollect.setClickable(false);
        tvAnswer.setClickable(false);
        bottomTab.setVisibility(View.VISIBLE);
    }

    public void initView() {
        for (int i = 0; i < practiceList.size(); i++) {
            Fragment fragment2 = PracticeAnswerFragment2.newInstance(practiceList.get(i), i, practiceList.size());
            Fragment fragment = PracticeAnswerFragment.newInstance(practiceList.get(i), i, practiceList.size());
            fragmentlist2.add(fragment2);
            fragmentlist.add(fragment);
        }
        init();
    }

    public void init() {
        //initViewAnswer();
        //viewPagerAnswer.setOffscreenPageLimit(7);
        //viewPager.setOffscreenPageLimit(7);
        fm = getSupportFragmentManager();
        mAdapter = new MyFragmentPagerAdapter(fm, fragmentlist2);
        viewPagerAnswer.setAdapter(mAdapter);
        mAdapter = new MyFragmentPagerAdapter(fm, fragmentlist);
        viewPager.setAdapter(mAdapter);
        viewPagerAnswer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        //practiceList = DataSupport.order("id desc").find(CollectData.class);
                        //String a=Thread.currentThread().getName();
                        int position00 = viewPagerAnswer.getCurrentItem();
                        int id = practiceList.get(position00).getId();
                        int cl = practiceList.get(position00).getCl();
                        //practiceList9.clear();
                        practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id+"",cl+"","1").find(CollectData.class);
                        emitter.onNext(1);

                        //emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(PracticeAnswerActivity.this.<Integer>bindToLifecycle())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                //Log.d(TAG, "subscribe");
                            }

                            @Override
                            public void onNext(Integer value) {
                                //int v=value;
                                if (practiceList9.size() > 0) {
                                    imgCollect.setImageResource(R.drawable.ic_collect_press);
                                    isCollected = 0;
                                } else {
                                    imgCollect.setImageResource(R.drawable.ic_collect_normal);
                                    isCollected = 1;
                                }
                                //init();
                                //Log.d(TAG, "" + value);
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
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        int position00 = viewPager.getCurrentItem();
                        int id = practiceList.get(position00).getId();
                        int cl = practiceList.get(position00).getCl();
                        practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id+"",cl+"","1").find(CollectData.class);
                        //Log.d("TAG", "subscribe: "+practiceList9.size());
                        //Log.d("TAG", "subscribe1: "+practiceList9.get(0).getJudge());
                        emitter.onNext(1);

                        //emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(PracticeAnswerActivity.this.<Integer>bindToLifecycle())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                //Log.d(TAG, "subscribe");
                            }

                            @Override
                            public void onNext(Integer value) {
                                //int v=value;
                                if (practiceList9.size() > 0) {
                                    //Log.d("TAG", "subscribe1: 是"+practiceList9.size());

                                    imgCollect.setImageResource(R.drawable.ic_collect_press);
                                    isCollected = 0;
                                } else {
                                    imgCollect.setImageResource(R.drawable.ic_collect_normal);
                                    isCollected = 1;
                                }
                                //init();
                                //Log.d(TAG, "" + value);
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
        });

        //practiceList9.clear();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int position00 = viewPagerAnswer.getCurrentItem();
                int id = practiceList.get(position00).getId();
                int cl = practiceList.get(position00).getCl();
                practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id+"",cl+"","1").find(CollectData.class);

                emitter.onNext(1);

                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(PracticeAnswerActivity.this.<Integer>bindToLifecycle())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        //int v=value;
                        if (practiceList9.size() > 0) {
                            imgCollect.setImageResource(R.drawable.ic_collect_press);

                            isCollected = 0;
                        } else {
                            imgCollect.setImageResource(R.drawable.ic_collect_normal);

                            isCollected = 1;
                        }
                        //init();
                        //Log.d(TAG, "" + value);
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
        tvTopicCard.setClickable(true);
        tvAnswer.setClickable(true);

    }


    public void addCollectDataBase(final int finalI1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CollectData collectData = new CollectData();
                collectData.setTopicId(practiceList.get(finalI1).getId());
                collectData.setName(practiceList.get(finalI1).getTitle());
                collectData.setA(practiceList.get(finalI1).getA());
                collectData.setB(practiceList.get(finalI1).getB());
                collectData.setC(practiceList.get(finalI1).getC());
                collectData.setD(practiceList.get(finalI1).getD());
                collectData.setE(practiceList.get(finalI1).getE());
                collectData.setCorrect(practiceList.get(finalI1).getCorrect());
                collectData.setAnalysis(practiceList.get(finalI1).getAnalysis());
                collectData.setTypeid(practiceList.get(finalI1).getTypeid());
                collectData.setCl(practiceList.get(finalI1).getCl());
                collectData.setZhenti(practiceList.get(finalI1).getZhenti());
                collectData.setMaterial(practiceList.get(finalI1).getMaterial());
                collectData.setImage(practiceList.get(finalI1).getLitpic());
                collectData.setJudge(1);
                collectData.save();
            }
        }).start();
    }

    public void delleteCollectDataBase(final int finalI1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int id98 = practiceList.get(finalI1).getId();
                int cl98 = practiceList.get(finalI1).getCl();
                DataSupport.deleteAll(CollectData.class, "topicId=? and cl=? and judge=?",id98+"",cl98+"","1");
            }
        }).start();

    }

    public void addNoteDataBase(final int finalI1, final String note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int id1 = practiceList.get(finalI1).getId();
                int cl1 = practiceList.get(finalI1).getCl();
                noteList9 = DataSupport.where("topicId=? and cl=? and judge=?",id1+"",cl1+"","1").find(NoteData.class);
                if (noteList9.size() > 0) {
                    NoteData noteData = new NoteData();
                    noteData.setNote(note);
                    noteData.updateAll("topicId=? and cl=? and judge=?",id1+"",cl1+"","1");
                } else {
                    NoteData noteData = new NoteData();
                    noteData.setTopicId(practiceList.get(finalI1).getId());
                    noteData.setName(practiceList.get(finalI1).getTitle());
                    noteData.setA(practiceList.get(finalI1).getA());
                    noteData.setB(practiceList.get(finalI1).getB());
                    noteData.setC(practiceList.get(finalI1).getC());
                    noteData.setD(practiceList.get(finalI1).getD());
                    noteData.setE(practiceList.get(finalI1).getE());
                    noteData.setCorrect(practiceList.get(finalI1).getCorrect());
                    noteData.setAnalysis(practiceList.get(finalI1).getAnalysis());
                    noteData.setTypeid(practiceList.get(finalI1).getTypeid());
                    noteData.setCl(practiceList.get(finalI1).getCl());
                    noteData.setZhenti(practiceList.get(finalI1).getZhenti());
                    noteData.setMaterial(practiceList.get(finalI1).getMaterial());
                    noteData.setImage(practiceList.get(finalI1).getLitpic());
                    noteData.setNote(note);
                    noteData.setJudge(1);
                    noteData.save();
                }

            }
        }).start();
    }

    @OnClick({R.id.ib_left, R.id.ln_topic_card, R.id.ln_collect, R.id.ln_topic_answer, R.id.ib_right, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_left:
                if (answer666 == 0) {
                    position = viewPager.getCurrentItem();
                    if (position > 0) {
                        viewPager.setCurrentItem(position - 1);
                        position = position - 1;
                    }
                } else if (answer666 == 1) {
                    position = viewPagerAnswer.getCurrentItem();
                    if (position > 0) {
                        viewPagerAnswer.setCurrentItem(position - 1);
                        position = position - 1;
                    }
                }
                break;
            case R.id.ln_topic_card:
                Intent intent = new Intent(this, TopicCardActivity.class);
                intent.putIntegerArrayListExtra("topicCard", topicCard);
                intent.putExtra("already", already);
                intent.putExtra("correct", correct1);
                intent.putExtra("switch", "0");
                startActivity(intent);
                break;
            case R.id.ln_collect:
                if (isCollected == 1) {
                    if (answer666 == 0) {
                        int a = viewPager.getCurrentItem();
                        addCollectDataBase(a);
                        imgCollect.setImageResource(R.drawable.ic_collect_press);

                    } else if (answer666 == 1) {
                        int a = viewPagerAnswer.getCurrentItem();
                        addCollectDataBase(a);
                        imgCollect.setImageResource(R.drawable.ic_collect_press);

                    }
                    isCollected = 0;
                } else if (isCollected == 0) {
                    if (answer666 == 0) {
                        int a18 = viewPager.getCurrentItem();
                        delleteCollectDataBase(a18);
                        imgCollect.setImageResource(R.drawable.ic_collect_normal);

                    } else if (answer666 == 1) {
                        int a18 = viewPagerAnswer.getCurrentItem();
                        delleteCollectDataBase(a18);
                        imgCollect.setImageResource(R.drawable.ic_collect_normal);
                    }
                    isCollected = 1;

                }

                /*ObserverOnNextListener<CollectionBean> listener = new ObserverOnNextListener<CollectionBean>() {
                    @Override
                    public void onNext(CollectionBean data) {
                        //if (data.getCode() == 400) {
                            Toast.makeText(PracticeAnswerActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                        //}
                        //Drawable collect = getResources().getDrawable(R.drawable.ic_collect_press);
                        //collect.setBounds(0, 0, 100, 100);
                        //tvCollect.setCompoundDrawables(null, collect, null, null);
                    }
                };
                if(userInfo!=null){
                    ApiMethods.getCollection(new MyObserver<CollectionBean>(listener), practiceList.get(a).getId() + "", userInfo.getId() + "", practiceList.get(a).getCl() + "", this);
                }else{
                    Toast.makeText(PracticeAnswerActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }*/
                break;
            case R.id.ln_topic_answer:
                if (answer666 == 0) {
                    position = viewPager.getCurrentItem();
                    int position00 = position;
                    int id = practiceList.get(position00).getId();
                    int cl = practiceList.get(position00).getCl();
                    //practiceList9.clear();
                    practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id+"",cl+"","1").find(CollectData.class);
                    if (practiceList9.size() > 0) {
                        imgCollect.setImageResource(R.drawable.ic_collect_press);

                        isCollected = 0;
                    } else {
                        imgCollect.setImageResource(R.drawable.ic_collect_normal);

                        isCollected = 1;
                    }
                    viewPagerAnswer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            int position00 = viewPagerAnswer.getCurrentItem();
                            int id = practiceList.get(position00).getId();
                            int cl = practiceList.get(position00).getCl();
                            //practiceList9.clear();
                            practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id+"",cl+"","1").find(CollectData.class);
                            if (practiceList9.size() > 0) {
                                imgCollect.setImageResource(R.drawable.ic_collect_press);

                                isCollected = 0;
                            } else {
                                imgCollect.setImageResource(R.drawable.ic_collect_normal);

                                isCollected = 1;
                            }
                        }
                    });
                    viewPagerAnswer.setCurrentItem(position, false);
                    viewPager.setVisibility(View.GONE);
                    viewPagerAnswer.setVisibility(View.VISIBLE);
                    answer666 = 1;
                    imgAnswer.setImageResource(R.drawable.ic_answer_press);

                } else if (answer666 == 1) {
                    position = viewPagerAnswer.getCurrentItem();
                    viewPager.setCurrentItem(position, false);
                    viewPager.setVisibility(View.VISIBLE);
                    viewPagerAnswer.setVisibility(View.GONE);
                    answer666 = 0;
                    imgAnswer.setImageResource(R.drawable.ic_answer_normal);
                    int position00 = position;
                    int id = practiceList.get(position00).getId();
                    int cl = practiceList.get(position00).getCl();
                    //practiceList9.clear();
                    practiceList9 = DataSupport.where("topicId=? and cl=? and judge=?",id+"",cl+"","1").find(CollectData.class);
                    if (practiceList9.size() > 0) {
                        imgCollect.setImageResource(R.drawable.ic_collect_press);
                        isCollected = 0;
                    } else {
                        imgCollect.setImageResource(R.drawable.ic_collect_normal);
                        isCollected = 1;
                    }
                }
                break;
            case R.id.ib_right:
                if (answer666 == 0) {
                    position = viewPager.getCurrentItem();
                    if (position < practiceList.size() - 1) {
                        viewPager.setCurrentItem(position + 1);
                        position = position + 1;
                    }
                } else if (answer666 == 1) {
                    position = viewPagerAnswer.getCurrentItem();
                    if (position < practiceList.size() - 1) {
                        viewPagerAnswer.setCurrentItem(position + 1);
                        position = position + 1;
                    }
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(TopicCardEvent messageEvent) {
        if (answer666 == 0) {
            viewPager.setCurrentItem(messageEvent.getMessage(), false);
        } else if (answer666 == 1) {
            viewPagerAnswer.setCurrentItem(messageEvent.getMessage(), false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void test(int i, int j) {
        //AnswerFragment会调用此方法
        if (j==0){
        correct1 = correct1 + 1;}
        topicCard.set(i, j);

    }

    public void test2() {
        //AnswerFragment会调用此方法
        already = already + 1;


    }

}
