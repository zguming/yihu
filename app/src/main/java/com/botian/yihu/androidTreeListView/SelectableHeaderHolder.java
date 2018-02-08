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
public class SelectableHeaderHolder extends TreeNode.BaseNodeViewHolder<SelectableHeaderHolder.IconTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;

    public SelectableHeaderHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_selectable_header, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        arrowView =  view.findViewById(R.id.arrow_icon);

        return view;
    }

    @Override
    public void toggle(boolean active) {
        super.toggle(active);
        arrowView.setImageResource(active ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right_small);
    }

    public static class IconTreeItem {
        public String text;

        public IconTreeItem( String text) {
            this.text = text;
        }
    }
}
