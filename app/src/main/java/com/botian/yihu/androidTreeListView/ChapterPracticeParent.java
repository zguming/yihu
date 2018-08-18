package com.botian.yihu.androidTreeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.database.ShareData;
import com.botian.yihu.util.SubjectUtil;
import com.unnamed.b.atv.model.TreeNode;

import org.litepal.crud.DataSupport;

import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Bogdan Melnychuk on 2/15/15.
 */
//androidTreeListView一级列表适配器
public class ChapterPracticeParent extends TreeNode.BaseNodeViewHolder<ChapterPracticeParent.IconTreeItem> {
    private TextView tvValue;
    private TextView tvValue2;
    private TextView text;
    private ImageView arrowView;
    private ImageView arrowIcon;
    private ImageView tbright;
    private View bottomLine;
    private View bottomLine2;
    private View bottomLine3;

    public ChapterPracticeParent(Context context) {

        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_chapter_practice_parent, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue2 = (TextView) view.findViewById(R.id.node_value2);
        text = (TextView) view.findViewById(R.id.text);
        arrowView = view.findViewById(R.id.arrow_icon);
        bottomLine = view.findViewById(R.id.bot_line);
        bottomLine2 = view.findViewById(R.id.bottom_line);
        bottomLine3 = view.findViewById(R.id.bottom_line3);
        arrowIcon = view.findViewById(R.id.arrow);
        tbright = view.findViewById(R.id.tb_right);
        if (value.share==1) {
            int id=value.noid;
            int columnid= SubjectUtil.getSubjectNo2();
            List<ShareData> shareData= DataSupport.where("chapterId=? and columnid=?" ,id+"",columnid+"").find(ShareData.class);
            if (shareData.size()>0){

            }else {
            tbright.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            arrowIcon.setVisibility(GONE);
            tvValue.setVisibility(GONE);
            tvValue2.setVisibility(View.VISIBLE);
            tvValue2.setText(value.text);}
        }
        tvValue.setText(value.text);
        return view;
    }

    @Override
    //箭头的转换
    public void toggle(boolean active) {
        super.toggle(active);
        arrowView.setImageResource(active ? R.drawable.iconfont_jianhao : R.drawable.iconfont_jiahao);
        arrowIcon.setImageResource(active ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right_small);
        bottomLine.setVisibility(active ? View.VISIBLE : View.INVISIBLE);
        bottomLine2.setVisibility(active ? GONE : View.VISIBLE);
        bottomLine3.setVisibility(active ? View.VISIBLE : GONE);
    }

    public static class IconTreeItem {
        public String text;
        private int noid;
        private int share;

        public IconTreeItem(String text, int noid,int share) {
            this.text = text;
            this.noid = noid;
            this.share = share;
        }

        public int getNoid() {
            return noid;
        }
    }
}
