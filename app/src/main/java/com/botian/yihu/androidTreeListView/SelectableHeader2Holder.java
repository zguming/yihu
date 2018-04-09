package com.botian.yihu.androidTreeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Bogdan Melnychuk on 2/15/15.
 */
//androidTreeListView一级列表适配器
public class SelectableHeader2Holder extends TreeNode.BaseNodeViewHolder<SelectableHeader2Holder.IconTreeItem> {
    private TextView tvValue;

    public SelectableHeader2Holder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_selectable_header2, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);


        return view;
    }


    public static class IconTreeItem {
        public String text;

        public IconTreeItem( String text) {
            this.text = text;
        }
    }
}
