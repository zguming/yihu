package com.botian.yihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.HighTestAnswerActivity;
import com.botian.yihu.activity.KaoQianActivity;
import com.botian.yihu.beans.HighTest;
import com.botian.yihu.beans.KaoQianNormal;
import com.botian.yihu.beans.PracticeAnswer;
import com.botian.yihu.database.NoteData;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
//直播
public class HighTestPracticeFragment extends RxFragment {
    Unbinder unbinder;
    @BindView(R.id.btnLookOtherNote)
    TextView btnLookOtherNote;
    @BindView(R.id.ln_note)
    LinearLayout lnNote;
    private PracticeAnswer.DataBean data;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String correct;
    private String analysis;
    private String name;
    //private String material;
    private String image;
    private int topicId;
    //private int typeid;
    private int i;
    private int totalnum;
    View view;
    private String hostUrl = "http://btsc.botian120.com";
    private List<NoteData> noteList9 = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.item_answer, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        lnNote.setVisibility(GONE);
        btnLookOtherNote.setVisibility(View.INVISIBLE);
        a = bundle.getString("A");
        b = bundle.getString("B");
        c = bundle.getString("C");
        d = bundle.getString("D");
        e = bundle.getString("E");
        correct = bundle.getString("Correct");
        analysis = bundle.getString("Analysis");
        name = bundle.getString("Name");
        //material = bundle.getString("Material");
        image = bundle.getString("Image");
        topicId = bundle.getInt("TopicId");
        //typeid = bundle.getInt("Typeid");
        i = bundle.getInt("i");
        totalnum = bundle.getInt("total");
        initView();
    }

    public void initView() {
        final HighTestAnswerActivity activity = (HighTestAnswerActivity) getActivity();
        TextView cailiao = view.findViewById(R.id.cailiao);
        /*if (material != null) {
            cailiao.setText(material);
            cailiao.setVisibility(View.VISIBLE);
        }*/
        TextView timu = view.findViewById(R.id.timu);
        ImageView imageView = view.findViewById(R.id.imageview);
        TextView current = view.findViewById(R.id.current_num);
        String currentNum = i + 1 + "";//当前数目
        current.setText(currentNum);
        TextView total = view.findViewById(R.id.total_num);
        String totalNum = "/" + totalnum;//总数目
        total.setText(totalNum);
        final ImageView answerA = view.findViewById(R.id.answer_a);
        final TextView answerTextA = view.findViewById(R.id.answer_text_a);
        final LinearLayout linearAnswerA = view.findViewById(R.id.linear_answer_a);
        final ImageView answerB = view.findViewById(R.id.answer_b);
        final TextView answerTextB = view.findViewById(R.id.answer_text_b);
        final LinearLayout linearAnswerB = view.findViewById(R.id.linear_answer_b);
        final ImageView answerC = view.findViewById(R.id.answer_c);
        final TextView answerTextC = view.findViewById(R.id.answer_text_c);
        final LinearLayout linearAnswerC = view.findViewById(R.id.linear_answer_c);
        final ImageView answerD = view.findViewById(R.id.answer_d);
        final TextView answerTextD = view.findViewById(R.id.answer_text_d);
        final LinearLayout linearAnswerD = view.findViewById(R.id.linear_answer_d);
        final ImageView answerE = view.findViewById(R.id.answer_e);
        final TextView noteContent = view.findViewById(R.id.noteContent);
        final TextView btnNoteEdit = view.findViewById(R.id.btnNoteEdit);
        final TextView answerTextE = view.findViewById(R.id.answer_text_e);
        final LinearLayout linearAnswerE = view.findViewById(R.id.linear_answer_e);
        final LinearLayout bottomHide = view.findViewById(R.id.bottom_hide);
        final TextView tvTipsYourChoose = view.findViewById(R.id.tv_tips_yourchoose);
        final TextView btnLookOtherNote = view.findViewById(R.id.btnLookOtherNote);
        final TextView btnFeedbackError = view.findViewById(R.id.btn_feedback_error);
        TextView check = view.findViewById(R.id.check);
        TextView analyse = view.findViewById(R.id.analyseinfo);
        bottomHide.setVisibility(View.INVISIBLE);
        String name1=i+1+"."+name;
        timu.setText(name1);
        answerTextA.setText(a);
        answerTextB.setText(b);
        answerTextC.setText(c);
        answerTextD.setText(d);
        answerTextE.setText(e);
        check.setText(correct);
        analyse.setText(analysis);
        String picUrl = hostUrl + image;
        Glide.with(this)
                .load(picUrl)
                .into(imageView);
        final int finalI1 = i;
        linearAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.test2();
                linearAnswerA.setClickable(false);
                linearAnswerB.setClickable(false);
                linearAnswerC.setClickable(false);
                linearAnswerD.setClickable(false);
                linearAnswerE.setClickable(false);
                if ("A".equals(correct)) {
                    activity.test(finalI1, 0);
                    answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                    answerTextA.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1, 1);
                    answerA.setImageDrawable(getResources().getDrawable(R.drawable.w_1));
                    answerTextA.setTextColor(getResources().getColor(R.color.false1));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                    //把错题添加到数据库
                    //addDataBase(finalI1);
                    if (correct.equals("B")) {
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("C")) {
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("D")) {
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                        answerTextD.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("E")) {
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                        answerTextE.setTextColor(getResources().getColor(R.color.correct));
                    }
                }
                tvTipsYourChoose.setText("A");
                bottomHide.setVisibility(View.VISIBLE);
            }
        });
        linearAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.test2();
                linearAnswerA.setClickable(false);
                linearAnswerB.setClickable(false);
                linearAnswerC.setClickable(false);
                linearAnswerD.setClickable(false);
                linearAnswerE.setClickable(false);
                if ("B".equals(correct)) {
                    activity.test(finalI1, 0);
                    answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                    answerTextB.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1, 1);
                    answerB.setImageDrawable(getResources().getDrawable(R.drawable.w_2));
                    answerTextB.setTextColor(getResources().getColor(R.color.false1));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                    //把错题添加到数据库
                    //addDataBase(finalI1);
                    if (correct.equals("A")) {
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("C")) {
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("D")) {
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                        answerTextD.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("E")) {
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                        answerTextE.setTextColor(getResources().getColor(R.color.correct));
                    }
                }
                tvTipsYourChoose.setText("B");
                bottomHide.setVisibility(View.VISIBLE);
            }
        });
        linearAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.test2();
                linearAnswerA.setClickable(false);
                linearAnswerB.setClickable(false);
                linearAnswerC.setClickable(false);
                linearAnswerD.setClickable(false);
                linearAnswerE.setClickable(false);
                if ("C".equals(correct)) {
                    activity.test(finalI1, 0);
                    answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                    answerTextC.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1, 1);
                    answerC.setImageDrawable(getResources().getDrawable(R.drawable.w_3));
                    answerTextC.setTextColor(getResources().getColor(R.color.false1));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                    //把错题添加到数据库
                    //addDataBase(finalI1);
                    if (correct.equals("A")) {
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("B")) {
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("D")) {
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                        answerTextD.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("E")) {
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                        answerTextE.setTextColor(getResources().getColor(R.color.correct));
                    }
                }
                tvTipsYourChoose.setText("C");
                bottomHide.setVisibility(View.VISIBLE);
            }
        });
        linearAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.test2();
                linearAnswerA.setClickable(false);
                linearAnswerB.setClickable(false);
                linearAnswerC.setClickable(false);
                linearAnswerD.setClickable(false);
                linearAnswerE.setClickable(false);
                if ("D".equals(correct)) {
                    activity.test(finalI1, 0);
                    answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                    bottomHide.setVisibility(View.VISIBLE);
                    answerTextD.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1, 1);
                    answerD.setImageDrawable(getResources().getDrawable(R.drawable.w_4));
                    answerTextD.setTextColor(getResources().getColor(R.color.false1));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                    //把错题添加到数据库
                    //addDataBase(finalI1);
                    if (correct.equals("A")) {
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("B")) {
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("C")) {
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("E")) {
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                        answerTextE.setTextColor(getResources().getColor(R.color.correct));
                    }
                }
                tvTipsYourChoose.setText("D");
                bottomHide.setVisibility(View.VISIBLE);
            }
        });
        linearAnswerE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.test2();
                linearAnswerA.setClickable(false);
                linearAnswerB.setClickable(false);
                linearAnswerC.setClickable(false);
                linearAnswerD.setClickable(false);
                linearAnswerE.setClickable(false);
                if ("E".equals(correct)) {
                    activity.test(finalI1, 0);
                    answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                    bottomHide.setVisibility(View.VISIBLE);
                    answerTextE.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1, 1);
                    answerE.setImageDrawable(getResources().getDrawable(R.drawable.w_5));
                    answerTextE.setTextColor(getResources().getColor(R.color.false1));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                    //把错题添加到数据库
                    //addDataBase(finalI1);
                    if (correct.equals("A")) {
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("B")) {
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("C")) {
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.correct));
                    } else if (correct.equals("D")) {
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                        answerTextD.setTextColor(getResources().getColor(R.color.correct));
                    }
                }
                tvTipsYourChoose.setText("E");
                bottomHide.setVisibility(View.VISIBLE);
            }
        });
    }

    public static HighTestPracticeFragment newInstance(HighTest.DataBean content, int i, int total) {
        Bundle args = new Bundle();
        args.putString("A", content.getA());
        args.putString("B", content.getB());
        args.putString("C", content.getC());
        args.putString("D", content.getD());
        args.putString("E", content.getE());
        args.putString("Correct", content.getCorrect());
        args.putString("Analysis", content.getAnalysis());
        args.putString("Name", content.getTitle());
        //args.putString("Material", content.getTitlecl());
        args.putString("Image", content.getLitpic());
        args.putInt("TopicId", content.getId());
        //args.putInt("Typeid", content.getTypeid());
        args.putInt("i", i);
        args.putInt("total", total);
        HighTestPracticeFragment fragment = new HighTestPracticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
