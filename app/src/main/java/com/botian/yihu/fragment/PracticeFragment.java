package com.botian.yihu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.botian.yihu.GlideImageLoader;
import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.activity.ChapterPracticeListActivity;
import com.botian.yihu.activity.MyCollectionActivity;
import com.botian.yihu.activity.WrongActivity;
import com.botian.yihu.adapter.VideoClassAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.Adlist;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.view.SubjectSelectActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//练习
public class PracticeFragment extends Fragment {

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
    private View view;
    private SharedPreferences pref;
    private String filter = "mid,eq,1";//首页轮播图mid==1
    private List<String> images = new ArrayList<>();

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
        initView();
        ObserverOnNextListener<Adlist> listener = new ObserverOnNextListener<Adlist>() {
            @Override
            public void onNext(Adlist data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    images.add("http://btsc.botian120.com"+data.getData().get(i).getLitpic());
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
        ApiMethods.getAdlist(new MyObserver<Adlist>(listener), filter, (RxAppCompatActivity) getActivity());

    }

    public void initView() {
        Drawable draKk = getResources().getDrawable(R.drawable.kk);
        draKk.setBounds(0, 0, 32, 32);
        titleSwitch.setCompoundDrawables(null, null, draKk, null);
        Drawable draCollect = getResources().getDrawable(R.drawable.ic_main_collect);
        draCollect.setBounds(0, 0, 120, 120);
        tvCollect.setCompoundDrawables(null, draCollect, null, null);
        Drawable draWrong = getResources().getDrawable(R.drawable.ic_main_wrong);
        draWrong.setBounds(0, 0, 120, 120);
        tvWrong.setCompoundDrawables(null, draWrong, null, null);
        Drawable draNotes = getResources().getDrawable(R.drawable.ic_main_notes);
        draNotes.setBounds(0, 0, 120, 120);
        tvNotes.setCompoundDrawables(null, draNotes, null, null);
        Drawable draPay = getResources().getDrawable(R.drawable.ic_main_pay);
        draPay.setBounds(0, 0, 120, 120);
        tvPay.setCompoundDrawables(null, draPay, null, null);
        Drawable draPratice = getResources().getDrawable(R.drawable.ic_main_pratice);
        draPratice.setBounds(0, 0, 120, 120);
        tvPracticeExamSub.setCompoundDrawables(null, null, null, draPratice);
        Drawable draYears = getResources().getDrawable(R.drawable.years_icon);
        draYears.setBounds(0, 0, 120, 120);
        tvCalendarYearsExamsSub.setCompoundDrawables(null, null, null, draYears);
        Drawable draExams = getResources().getDrawable(R.drawable.ic_main_exams);
        draExams.setBounds(0, 0, 120, 120);
        tvForecastExamsSub.setCompoundDrawables(null, null, null, draExams);
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
        pref = this.getActivity().getSharedPreferences("subjectSelectData", Context.MODE_PRIVATE);
        String subjectName = pref.getString("subjectName", "护士执业");
        mySubject.setText(subjectName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_collect, R.id.tv_wrong, R.id.tv_notes, R.id.tv_pay, R.id.title_switch, R.id.rl_chapter_practice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                Intent intent2 = new Intent(getActivity(), MyCollectionActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_wrong:
                Intent intent1 = new Intent(getActivity(), WrongActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_notes:
                break;
            case R.id.tv_pay:
                break;
            case R.id.title_switch:
                Intent intent = new Intent(getActivity(), SubjectSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_chapter_practice:
                Intent intent9 = new Intent(getActivity(), ChapterPracticeListActivity.class);
                startActivity(intent9);
                break;
        }
    }

}
