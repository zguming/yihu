package com.botian.yihu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.activity.ChapterPracticeListActivity;
import com.botian.yihu.activity.HighTestActivity;
import com.botian.yihu.activity.KaoQianListActivity;
import com.botian.yihu.activity.MoniListActivity;
import com.botian.yihu.activity.MoniSubjectActivity;
import com.botian.yihu.activity.MyCollectionActivity;
import com.botian.yihu.activity.MyNoteActivity;
import com.botian.yihu.activity.MymoneyActivity;
import com.botian.yihu.activity.NewsContentActivity;
import com.botian.yihu.activity.WrongActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.Adlist;
import com.botian.yihu.beans.NewsList;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.Version;
import com.botian.yihu.database.CollectData;
import com.botian.yihu.database.NoteData;
import com.botian.yihu.database.WrongData;
import com.botian.yihu.MyVideoPlayer.UPMarqueeView;
import com.botian.yihu.MyVideoPlayer.UpdataDialog;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.GetVersionName;
import com.botian.yihu.util.GlideImageLoader;
import com.botian.yihu.util.SubjectUtil;
import com.botian.yihu.view.SubjectSelectActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//练习
public class PracticeFragment extends RxFragment {

    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_wrong)
    TextView tvWrong;
    @BindView(R.id.tv_notes)
    TextView tvNotes;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    Unbinder unbinder;
    @BindView(R.id.tv_practice_exam_sub)
    TextView tvPracticeExamSub;
    @BindView(R.id.tv_calendar_years_exams_sub)
    TextView tvCalendarYearsExamsSub;
    @BindView(R.id.tv_forecast_exams_sub)
    TextView tvForecastExamsSub;
    @BindView(R.id.rl_chapter_practice)
    RelativeLayout rlChapterPractice;
    @BindView(R.id.title_switch)
    TextView titleSwitch;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.my_subject)
    TextView mySubject;
    @BindView(R.id.tv_chapter_practice)
    TextView tvChapterPractice;
    @BindView(R.id.tv_high_test)
    TextView tvHighTest;
    @BindView(R.id.rl_high_exams)
    RelativeLayout rlHighExams;
    @BindView(R.id.tv_practice_exam)
    TextView tvPracticeExam;
    @BindView(R.id.rl_practice_exam)
    RelativeLayout rlPracticeExam;
    @BindView(R.id.tv_calendar_years_exams)
    TextView tvCalendarYearsExams;
    @BindView(R.id.rl_calendar_year_exams)
    RelativeLayout rlCalendarYearExams;
    @BindView(R.id.tv_forecast_exams)
    TextView tvForecastExams;
    @BindView(R.id.rl_forecast_exams)
    RelativeLayout rlForecastExams;
    @BindView(R.id.ln_collect)
    LinearLayout lnCollect;
    @BindView(R.id.ln_wrong)
    LinearLayout lnWrong;
    @BindView(R.id.ln_notes)
    LinearLayout lnNotes;
    @BindView(R.id.ln_pay)
    LinearLayout lnPay;
    @BindView(R.id.title_switch1)
    ImageView titleSwitch1;
    private View view;
    private SharedPreferences pref;
    private String filter = "mid,eq,1";//首页轮播图mid==1
    private List<String> images = new ArrayList<>();
    int subjectNo;
    int subjectNo2;
    private ACache mCache;
    private UserInfo userInfo;
    private List<CollectData> collectList = new ArrayList<>();
    private List<WrongData> wrongList = new ArrayList<>();
    private List<NoteData> noteList = new ArrayList<>();
    private UPMarqueeView upview1;
    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();
    ObserverOnNextListener<NewsList> listener3;
    private NewsList data2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_practice, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCache = ACache.get(getActivity());
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        ObserverOnNextListener<Adlist> listener = new ObserverOnNextListener<Adlist>() {
            @Override
            public void onNext(Adlist data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    images.add("http://btsc.botian120.com" + data.getData().get(i).getLitpic());
                }
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(images);
                banner.setDelayTime(4000);
                //banner设置方法全部调用完毕时最后调用
                banner.start();

            }
        };
        listener3 = new ObserverOnNextListener<NewsList>() {
            @Override
            public void onNext(NewsList data) {
                initdata(data);
                initView();
            }
        };
        ApiMethods.getAdlist(new MyObserver<Adlist>(listener), filter, (RxAppCompatActivity) getActivity());
        pref = this.getActivity().getSharedPreferences("subjectSelectData", Context.MODE_PRIVATE);
        subjectNo = pref.getInt("subjectNo", 1);
        subjectNo2 = pref.getInt("subjectNo2", 2);
        String filter8 = "mid,eq," + subjectNo;
        String filter9 = "mids,eq," + subjectNo2;
        ApiMethods.getNewsList(new MyObserver<NewsList>(listener3), "flag,eq,h", filter8, filter9,  "1", "6", (RxAppCompatActivity) getActivity());
        initParam();
        upDater();//更新app

    }

    public static PracticeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        PracticeFragment fragment = new PracticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                collectList = DataSupport.findAll(CollectData.class);
                noteList = DataSupport.findAll(NoteData.class);
                wrongList = DataSupport.findAll(WrongData.class);

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
                        //int v=value;
                        String collect = "收藏 " + collectList.size();
                        tvCollect.setText(collect);
                        String note = "笔记 " + noteList.size();
                        tvNotes.setText(note);
                        String wrong = "错题 " + wrongList.size();
                        tvWrong.setText(wrong);

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
        pref = this.getActivity().getSharedPreferences("subjectSelectData", Context.MODE_PRIVATE);
        String subjectName = pref.getString("subjectName", "护士执业");
        subjectNo = pref.getInt("subjectNo", 1);
        subjectNo2 = pref.getInt("subjectNo2", 2);
        SubjectUtil.setSubjectNo(subjectNo);
        SubjectUtil.setSubjectNo2(subjectNo2);
        mySubject.setText(subjectName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
// TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            //从缓存读取用户信息
            userInfo = (UserInfo) mCache.getAsObject("userInfo");

        }
    }

    @OnClick({R.id.ln_collect, R.id.ln_wrong, R.id.ln_notes, R.id.ln_pay, R.id.title_switch, R.id.title_switch1, R.id.my_subject, R.id.rl_chapter_practice, R.id.rl_practice_exam,R.id.rl_calendar_year_exams,R.id.rl_high_exams,R.id.rl_forecast_exams})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ln_collect:
                Intent intent26 = new Intent(getActivity(), MyCollectionActivity.class);
                startActivity(intent26);
                break;
            case R.id.rl_forecast_exams:
                if (userInfo != null) {
                    Intent intent21622 = new Intent(getActivity(), KaoQianListActivity.class);
                    startActivity(intent21622);
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.ln_wrong:
                Intent intent16 = new Intent(getActivity(), WrongActivity.class);
                startActivity(intent16);
                break;
            case R.id.ln_notes:
                Intent intent23 = new Intent(getActivity(), MyNoteActivity.class);
                startActivity(intent23);
                break;
            case R.id.ln_pay:
                if (userInfo != null) {
                    Intent intent22 = new Intent(getActivity(), MymoneyActivity.class);
                    startActivity(intent22);
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.title_switch:
                Intent intent66 = new Intent(getActivity(), SubjectSelectActivity.class);
                startActivity(intent66);
                break;
            case R.id.title_switch1:
                Intent intent666 = new Intent(getActivity(), SubjectSelectActivity.class);
                startActivity(intent666);
                break;
            case R.id.my_subject:
                Intent intent6666 = new Intent(getActivity(), SubjectSelectActivity.class);
                startActivity(intent6666);
                break;
            case R.id.rl_high_exams:
                Intent intent66661 = new Intent(getActivity(), HighTestActivity.class);
                startActivity(intent66661);
                break;
            case R.id.rl_chapter_practice:
                Intent intent9 = new Intent(getActivity(), ChapterPracticeListActivity.class);
                intent9.putExtra("title", "章节练习");
                intent9.putExtra("zhenti", 2);
                startActivity(intent9);
                break;
            case R.id.rl_calendar_year_exams:
                Intent intent98 = new Intent(getActivity(), ChapterPracticeListActivity.class);
                intent98.putExtra("title", "历年真题");
                intent98.putExtra("zhenti", 1);
                startActivity(intent98);
                break;

            case R.id.rl_practice_exam:
                if (userInfo != null) {
                    Intent intent10 = new Intent(getActivity(), MoniListActivity.class);
                    //intent10.putExtra("subjectNo",subjectNo+"");
                    startActivity(intent10);
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    /**
     * 实例化控件 垂直滚动条
     */
    private void initParam() {
        upview1 = view.findViewById(R.id.upview1);
    }

    /**
     * 初始化界面程序
     */
    private void initView() {
        setView();
        upview1.setViews(views);
        /**
         * 设置item_view的监听
         */
        upview1.setOnItemClickListener(new UPMarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                //Toast.makeText(getActivity(), "你点击了第几个items" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getActivity(), position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NewsContentActivity.class);
                    intent.putExtra("id", data2.getData().getData().get(position).getId());
                    intent.putExtra("content", data2.getData().getData().get(position).getcontent());
                    startActivity(intent);
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getActivity(), position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NewsContentActivity.class);
                    intent.putExtra("id", data2.getData().getData().get(position+1).getId());
                    intent.putExtra("content", data2.getData().getData().get(position+1).getcontent());
                    startActivity(intent);
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }

    /**
     * 初始化数据
     */
    private void initdata(NewsList data1) {
        data2=data1;
        data = new ArrayList<>();
        for (int i=0;i<data1.getData().getData().size();i++){
            data.add(data1.getData().getData().get(i).getTitle());
        }

//        data.add("竟不是小米乐视！看水抢了骁龙821首发了！！！");

    }
    public void upDater(){
        ObserverOnNextListener<Version> listener33 = new ObserverOnNextListener<Version>() {
            @Override
            public void onNext(final Version data) {
                if (data.getCode() == 200) {
                    //Toast.makeText(getActivity(), data.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    UpdataDialog updataDialog = new UpdataDialog(getActivity(), R.layout.dialog_version,
                            new int[]{R.id.dialog_sure});
                    updataDialog.show();
                    if (data.getData().get(0).getStatus() == 1) {
                        updataDialog.setCanceledOnTouchOutside(false);
                    }
                    //TextView tvmsg = (TextView) updataDialog.findViewById(R.id.updataversion_msg);
                    TextView tvmsg = (TextView) updataDialog.findViewById(R.id.updataversion_msg);
                    TextView tvcode = (TextView) updataDialog.findViewById(R.id.updataversioncode);
                    String newVersion = data.getData().get(0).getName();
                    String content = data.getData().get(0).getContent();
                    tvcode.setText(newVersion);
                    String ta1 = Html.fromHtml(content).toString();
                    tvmsg.setText(Html.fromHtml(ta1));
                    updataDialog.setOnCenterItemClickListener(new UpdataDialog.OnCenterItemClickListener() {
                        @Override
                        public void OnCenterItemClick(UpdataDialog dialog, View view) {
                            switch (view.getId()) {
                                case R.id.dialog_sure:
                                    Intent intent = new Intent();
                                    intent.setAction("android.intent.action.VIEW");
                                    String cc = "http://btsc.botian120.com" + data.getData().get(0).getUrl();
                                    Uri content_url = Uri.parse(cc);
                                    intent.setData(content_url);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    });
                }

            }

        };
        String versionNama = GetVersionName.getVerName(getActivity());
        ApiMethods.getVersion(new MyObserver<Version>(listener33), versionNama, (RxAppCompatActivity) getActivity());
    }

}
