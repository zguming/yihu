package com.botian.yihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.MyNotePracticeActivity;
import com.botian.yihu.activity.OtherCommentActivity;
import com.botian.yihu.beans.CommentParcel;
import com.botian.yihu.database.NoteData;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
//直播
public class NoteAnswerFragment extends Fragment {
    @BindView(R.id.noteContent)
    TextView noteContent;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String correct;
    private String analysis;
    private String name;
    private String material;
    private String image;
    private String note;
    private int cl;
    private int topicId;
    private int typeid;
    private int i;
    private int totalnum;
    View view;
    private String hostUrl = "http://btsc.botian120.com";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_answer, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        a = bundle.getString("A");
        b = bundle.getString("B");
        c = bundle.getString("C");
        d = bundle.getString("D");
        e = bundle.getString("E");
        correct = bundle.getString("Correct");
        analysis = bundle.getString("Analysis");
        name = bundle.getString("Name");
        material = bundle.getString("Material");
        image = bundle.getString("Image");
        note = bundle.getString("note");
        cl = bundle.getInt("Cl");
        topicId = bundle.getInt("TopicId");
        typeid = bundle.getInt("Typeid");
        i = bundle.getInt("i");
        totalnum = bundle.getInt("total");
        initView();
    }
    public void initView(){
        final MyNotePracticeActivity activity=(MyNotePracticeActivity) getActivity();
        TextView cailiao = view.findViewById(R.id.cailiao);
        if (material != null) {
            cailiao.setText(material);
            cailiao.setVisibility(View.VISIBLE);
        }
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
        timu.setText(name);
        answerTextA.setText(a);
        answerTextB.setText(b);
        answerTextC.setText(c);
        answerTextD.setText(d);
        answerTextE.setText(e);
        check.setText(correct);
        analyse.setText(analysis);
        noteContent.setText(note);
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
                    activity.test(finalI1,0);
                    answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                    answerTextA.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1,1);
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
                    activity.test(finalI1,0);
                    answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                    answerTextB.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1,1);
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
                    activity.test(finalI1,0);
                    answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                    answerTextC.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1,1);
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
                    activity.test(finalI1,0);
                    answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                    bottomHide.setVisibility(View.VISIBLE);
                    answerTextD.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1,1);
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
                    activity.test(finalI1,0);
                    answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                    bottomHide.setVisibility(View.VISIBLE);
                    answerTextE.setTextColor(getResources().getColor(R.color.correct));
                    tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                } else {
                    activity.test(finalI1,1);
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
        btnLookOtherNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentParcel commentParcel = new CommentParcel();
                commentParcel.setId(topicId);
                commentParcel.setTitle(name);
                commentParcel.setCl(cl+"");
                Intent intent = new Intent(getActivity(), OtherCommentActivity.class);
                intent.putExtra("commentParcel", commentParcel);
                startActivity(intent);
            }
        });
    }

    public static NoteAnswerFragment newInstance(NoteData content, int i, int total) {
        Bundle args = new Bundle();
        args.putString("A", content.getA());
        args.putString("B", content.getB());
        args.putString("C", content.getC());
        args.putString("D", content.getD());
        args.putString("E", content.getE());
        args.putString("Correct", content.getCorrect());
        args.putString("Analysis", content.getAnalysis());
        args.putString("Name", content.getName());
        args.putString("Material", content.getMaterial());
        args.putString("Image", content.getImage());
        args.putString("note", content.getNote());
        args.putInt("Cl", content.getCl());
        args.putInt("TopicId", content.getTopicId());
        args.putInt("Typeid", content.getTypeid());
        args.putInt("i", i);
        args.putInt("total", total);
        NoteAnswerFragment fragment = new NoteAnswerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

