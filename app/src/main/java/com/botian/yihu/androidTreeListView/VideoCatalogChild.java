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
//androidTreeListView二级列表适配器
public class VideoCatalogChild extends TreeNode.BaseNodeViewHolder<VideoCatalogChild.IconTreeItem>
{
    private TextView tvValue;
    private ImageView tb_right1;
    private ImageView tb_right2;

    public VideoCatalogChild(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, VideoCatalogChild.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_video_catalog_child, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tb_right1 =  view.findViewById(R.id.tb_right1);
        tb_right2 =  view.findViewById(R.id.tb_right2);
        tvValue.setText(value.text);
        //最后一个item隐藏 下垂直线
        if (node.isLastChild()) {
            view.findViewById(R.id.bot_line).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.bottom_line).setVisibility(View.VISIBLE);
        }
        if (value.buy.equals("1")){
            tb_right2.setVisibility(View.VISIBLE);
            tb_right1.setVisibility(View.GONE);
        }else {
            tb_right1.setVisibility(View.VISIBLE);
            tb_right2.setVisibility(View.GONE);
        }
        if (value.buy1==0){
            tb_right2.setVisibility(View.VISIBLE);
            tb_right1.setVisibility(View.GONE);
        }
        return view;
    }
    public static class IconTreeItem {
        public String text;
        private int noid;
        private  String buy;
        private  int buy1;
        public IconTreeItem( String text,int noid,String buy,int buy1) {
            this.text = text;
            this.noid = noid;
            this.buy = buy;
            this.buy1 = buy1;
        }

        public int getNoid() {
            return noid;
        }
        public int getBuy1() {
            return buy1;
        }
    }
}
