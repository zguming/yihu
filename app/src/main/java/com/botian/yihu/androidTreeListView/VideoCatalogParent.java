package com.botian.yihu.androidTreeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.unnamed.b.atv.model.TreeNode;

import butterknife.BindView;

/**
 * Created by Bogdan Melnychuk on 2/15/15.
 */
//androidTreeListView一级列表适配器
public class VideoCatalogParent extends TreeNode.BaseNodeViewHolder<VideoCatalogParent.IconTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;
    private ImageView arrowIcon;
    private View bottomLine;
    private View bottomLine2;
    private View bottomLine3;

    public VideoCatalogParent(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_video_catalog_parent, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        arrowView = view.findViewById(R.id.arrow_icon);
        bottomLine = view.findViewById(R.id.bot_line);
        bottomLine2 = view.findViewById(R.id.bottom_line);
        bottomLine3 = view.findViewById(R.id.bottom_line3);
        arrowIcon = view.findViewById(R.id.arrow);

        return view;
    }

    @Override
    //箭头的转换
    public void toggle(boolean active) {
        super.toggle(active);
        arrowView.setImageResource(active ? R.drawable.iconfont_jianhao : R.drawable.iconfont_jiahao);
        arrowIcon.setImageResource(active ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right_small);
        bottomLine.setVisibility(active ? View.VISIBLE : View.INVISIBLE);
        bottomLine2.setVisibility(active ? View.GONE : View.VISIBLE);
        bottomLine3.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    public static class IconTreeItem {
        public String text;
        private int noid;

        public IconTreeItem(String text, int noid) {
            this.text = text;
            this.noid = noid;
        }

        public int getNoid() {
            return noid;
        }
    }
}
