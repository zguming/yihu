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
public class SelectableItemHolder extends TreeNode.BaseNodeViewHolder<String> {
    private TextView tvValue;

    public SelectableItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, String value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_selectable_item, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value);
        //最后一个item隐藏 下垂直线
        if (node.isLastChild()) {
            view.findViewById(R.id.bot_line).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.bottom_line).setVisibility(View.VISIBLE);
        }

        return view;
    }
}
