package com.botian.yihu.androidTreeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.botian.yihu.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Bogdan Melnychuk on 2/15/15.
 */
//androidTreeListView二级列表适配器
public class ChapterPracticeChild extends TreeNode.BaseNodeViewHolder<ChapterPracticeChild.IconTreeItem>
{
    private TextView tvValue;

    public ChapterPracticeChild(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, ChapterPracticeChild.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_chapter_practice_child, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);
        //最后一个item隐藏 下垂直线
        if (node.isLastChild()) {
            view.findViewById(R.id.bot_line).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.bottom_line).setVisibility(View.VISIBLE);
        }
        return view;
    }
    public static class IconTreeItem {
        public String text;
        private int noid;
        public IconTreeItem( String text,int noid) {
            this.text = text;
            this.noid = noid;
        }

        public int getNoid() {
            return noid;
        }
    }
}
