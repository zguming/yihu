package com.botian.yihu.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.adapter.MyFragmentPagerAdapter;
import com.botian.yihu.database.CollectData;
import com.botian.yihu.database.NoteData;
import com.botian.yihu.eventbus.TopicCardEventCollect;
import com.botian.yihu.fragment.CollectAnswerFragment;
import com.botian.yihu.fragment.CollectAnswerFragment2;
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

public class MyCollectionPracticeActivity extends RxAppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ib_left)
    ImageButton ibLeft;
    @BindView(R.id.tv_topic_card)
    TextView tvTopicCard;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;
    @BindView(R.id.ib_right)
    ImageButton ibRight;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.img_topic_card)
    ImageView imgTopicCard;
    @BindView(R.id.ln_topic_card)
    LinearLayout lnTopicCard;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.ln_collect)
    LinearLayout lnCollect;
    @BindView(R.id.img_answer)
    ImageView imgAnswer;
    @BindView(R.id.ln_topic_answer)
    LinearLayout lnTopicAnswer;
    private ArrayList<View> viewsList;
    //private MyPagerAdapter mAdapter;
    private MyFragmentPagerAdapter mAdapter;
    private List<CollectData> practiceList = new ArrayList<>();
    //背题数据
    private ArrayList<View> viewsListAnswer;
    private String hostUrl = "http://btsc.botian120.com";
    //用于判断背题    ，0为非，1为是
    private int answer666 = 1;
    //背题viewpager
    @BindView(R.id.view_pager_answer)
    ViewPager viewPagerAnswer;
    private int position = 0;
    private ArrayList<Integer> topicCard = new ArrayList<>();//答对0，答错1，当前题2
    private int already = 0;//已答总数
    private int correct1 = 0;//答对总数
    private static List<CollectData> practiceList9 = new ArrayList<>();
    private static List<NoteData> noteList9 = new ArrayList<>();
    private int isCollected = 1;//是否已经收藏，0已收藏，1未收藏
    int position665;
    private List<Fragment> fragmentlist2 = new ArrayList<>();//背题
    private List<Fragment> fragmentlist = new ArrayList<>();//无背题
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        initView1();
        viewsListAnswer = new ArrayList<View>();
        //CollectionRecordsParcel practiceParcel = intent.getParcelableExtra("collectionRecordsParcel");
        position665 = intent.getIntExtra("position", 0);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                practiceList = DataSupport.order("id desc").find(CollectData.class);
                //String a=Thread.currentThread().getName();
                for (int i = 0; i < practiceList.size(); i++) {
                    Fragment fragment2 = CollectAnswerFragment2.newInstance(practiceList.get(i), i, practiceList.size());
                    Fragment fragment = CollectAnswerFragment.newInstance(practiceList.get(i), i, practiceList.size());
                    fragmentlist2.add(fragment2);
                    fragmentlist.add(fragment);
                }
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
                        init();
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

        //compositeDisposable.clear();

    }

    public void addNoteDataBase(final int finalI1, final String note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int id1 = practiceList.get(finalI1).getTopicId();
                int cl1 = practiceList.get(finalI1).getCl();
                noteList9 = DataSupport.where("topicId=" + id1 + ";" + "cl=" + cl1).find(NoteData.class);
                if (noteList9.size() > 0) {
                    NoteData noteData = new NoteData();
                    noteData.setNote(note);
                    noteData.updateAll("topicId=" + id1 + ";" + "cl=" + cl1);
                } else {
                    NoteData noteData = new NoteData();
                    noteData.setTopicId(practiceList.get(finalI1).getTopicId());
                    noteData.setName(practiceList.get(finalI1).getName());
                    noteData.setA(practiceList.get(finalI1).getA());
                    noteData.setB(practiceList.get(finalI1).getB());
                    noteData.setC(practiceList.get(finalI1).getC());
                    noteData.setD(practiceList.get(finalI1).getD());
                    noteData.setE(practiceList.get(finalI1).getE());
                    noteData.setCorrect(practiceList.get(finalI1).getCorrect());
                    noteData.setAnalysis(practiceList.get(finalI1).getAnalysis());
                    noteData.setTypeid(practiceList.get(finalI1).getTypeid());
                    noteData.setCl(practiceList.get(finalI1).getCl());
                    noteData.setMaterial(practiceList.get(finalI1).getMaterial());
                    noteData.setImage(practiceList.get(finalI1).getImage());
                    noteData.setNote(note);
                    noteData.save();
                }

            }
        }).start();
    }

    public void init() {
        //initViewAnswer();
        //viewPagerAnswer.setOffscreenPageLimit(2);
        fm = getSupportFragmentManager();
        mAdapter = new MyFragmentPagerAdapter(fm, fragmentlist2);
        viewPagerAnswer.setAdapter(mAdapter);
        viewPagerAnswer.setCurrentItem(position665, false);
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
                        int id = practiceList.get(position00).getTopicId();
                        int cl = practiceList.get(position00).getCl();
                        //practiceList9.clear();
                        practiceList9 = DataSupport.where("topicId=" + id + ";" + "cl=" + cl).find(CollectData.class);
                        emitter.onNext(1);

                        //emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(MyCollectionPracticeActivity.this.<Integer>bindToLifecycle())
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
                        //practiceList = DataSupport.order("id desc").find(CollectData.class);
                        //String a=Thread.currentThread().getName();
                        int position00 = viewPager.getCurrentItem();
                        int id = practiceList.get(position00).getTopicId();
                        int cl = practiceList.get(position00).getCl();
                        //practiceList9.clear();
                        practiceList9 = DataSupport.where("topicId=" + id + ";" + "cl=" + cl).find(CollectData.class);
                        emitter.onNext(1);

                        //emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(MyCollectionPracticeActivity.this.<Integer>bindToLifecycle())
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

        //practiceList9.clear();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int position00 = viewPagerAnswer.getCurrentItem();
                int id = practiceList.get(position00).getTopicId();
                int cl = practiceList.get(position00).getCl();
                practiceList9 = DataSupport.where("topicId=" + id + ";" + "cl=" + cl).find(CollectData.class);

                emitter.onNext(1);

                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(MyCollectionPracticeActivity.this.<Integer>bindToLifecycle())
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

        for (int i = 0; i < practiceList.size(); i++) {
            topicCard.add(6);//6是初始默认数字，无意义
        }
        tvTopicCard.setClickable(true);
        tvAnswer.setClickable(true);

    }


    public void initView1() {
        imgAnswer.setImageResource(R.drawable.ic_answer_press);
        tvTopicCard.setClickable(false);
        tvAnswer.setClickable(false);
        bottomTab.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back, R.id.ib_left, R.id.ln_topic_card, R.id.ln_collect, R.id.ln_topic_answer, R.id.ib_right})
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
                intent.putExtra("switch", "9");
                startActivity(intent);
                break;
            case R.id.ln_topic_answer:
                if (answer666 == 0) {
                    position = viewPager.getCurrentItem();
                    viewPagerAnswer.setCurrentItem(position, false);
                    viewPager.setVisibility(View.GONE);
                    viewPagerAnswer.setVisibility(View.VISIBLE);
                    answer666 = 1;
                    imgAnswer.setImageResource(R.drawable.ic_answer_press);
                    Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                            int position00 = position;
                            int id = practiceList.get(position00).getTopicId();
                            int cl = practiceList.get(position00).getCl();
                            //practiceList9.clear();
                            practiceList9 = DataSupport.where("topicId=" + id + ";" + "cl=" + cl).find(CollectData.class);

                            emitter.onNext(1);

                            //emitter.onComplete();
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(MyCollectionPracticeActivity.this.<Integer>bindToLifecycle())
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
                } else if (answer666 == 1) {
                    position = viewPagerAnswer.getCurrentItem();
                    viewPager.setCurrentItem(position, false);
                    viewPagerAnswer.setVisibility(View.GONE);
                    viewPager.setVisibility(View.VISIBLE);
                    answer666 = 0;
                    imgAnswer.setImageResource(R.drawable.ic_answer_normal);
                    Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                            int position00 = position;
                            int id = practiceList.get(position00).getTopicId();
                            int cl = practiceList.get(position00).getCl();
                            //practiceList9.clear();
                            practiceList9 = DataSupport.where("topicId=" + id + ";" + "cl=" + cl).find(CollectData.class);

                            emitter.onNext(1);

                            //emitter.onComplete();
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(MyCollectionPracticeActivity.this.<Integer>bindToLifecycle())
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
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(TopicCardEventCollect messageEvent) {
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

    public void addCollectDataBase(int finalI1) {
        CollectData collectData = new CollectData();
        collectData.setTopicId(practiceList.get(finalI1).getTopicId());
        collectData.setName(practiceList.get(finalI1).getName());
        collectData.setA(practiceList.get(finalI1).getA());
        collectData.setB(practiceList.get(finalI1).getB());
        collectData.setC(practiceList.get(finalI1).getC());
        collectData.setD(practiceList.get(finalI1).getD());
        collectData.setE(practiceList.get(finalI1).getE());
        collectData.setCorrect(practiceList.get(finalI1).getCorrect());
        collectData.setAnalysis(practiceList.get(finalI1).getAnalysis());
        collectData.setTypeid(practiceList.get(finalI1).getTypeid());
        collectData.setCl(practiceList.get(finalI1).getCl());
        collectData.setMaterial(practiceList.get(finalI1).getMaterial());
        collectData.setImage(practiceList.get(finalI1).getImage());
        collectData.save();
    }

    public void delleteCollectDataBase(int finalI1) {
        int id98 = practiceList.get(finalI1).getTopicId();
        int cl98 = practiceList.get(finalI1).getCl();
        DataSupport.deleteAll(CollectData.class, "topicId=" + id98 + ";" + "cl=" + cl98);

    }

    public CollectData getData(int i) {
        return practiceList.get(i);
    }

    public void test(int i, int j) {
        //AnswerFragment会调用此方法
        correct1 = correct1 + 1;
        topicCard.set(i, j);

    }

    public void test2() {
        //AnswerFragment会调用此方法
        already = already + 1;

    }
}
