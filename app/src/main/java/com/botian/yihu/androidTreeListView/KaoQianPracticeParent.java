package com.botian.yihu.androidTreeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.KaoQianListActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.SearchKaoQianBuy;
import com.botian.yihu.database.ShareData;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.unnamed.b.atv.model.TreeNode;

import org.litepal.crud.DataSupport;

import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Bogdan Melnychuk on 2/15/15.
 */
//androidTreeListView一级列表适配器
public class KaoQianPracticeParent extends TreeNode.BaseNodeViewHolder<KaoQianPracticeParent.IconTreeItem> {
    private TextView tvValue;
    private TextView tvValue2;
    private TextView text;
    private TextView price;
    private TextView buy;
    private ImageView arrowView;
    private ImageView arrowIcon;
    private ImageView tbright;
    private View bottomLine;
    private View bottomLine2;
    private View bottomLine3;

    public KaoQianPracticeParent(Context context) {

        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_kaoqian_practice_parent, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue2 = (TextView) view.findViewById(R.id.node_value2);
        text = (TextView) view.findViewById(R.id.text);
        buy = (TextView) view.findViewById(R.id.buy);
        price = (TextView) view.findViewById(R.id.price);
        arrowView = view.findViewById(R.id.arrow_icon);
        bottomLine = view.findViewById(R.id.bot_line);
        bottomLine2 = view.findViewById(R.id.bottom_line);
        bottomLine3 = view.findViewById(R.id.bottom_line3);
        arrowIcon = view.findViewById(R.id.arrow);
        tbright = view.findViewById(R.id.tb_right);
        /*if (value.share!=0) {
            int id=value.noid;
            int columnid= SubjectUtil.getSubjectNo2();
            List<ShareData> shareData= DataSupport.where("chapterId=" + id + ";" + "columnid=" + columnid).find(ShareData.class);
            if (shareData.size()>0){

            }else {
            tbright.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            arrowIcon.setVisibility(GONE);
            tvValue.setVisibility(GONE);
            tvValue2.setVisibility(View.VISIBLE);
            tvValue2.setText(value.text);}
        }*/
        tvValue.setText(value.text);
        String price1 = "¥" + value.price;
        price.setText(price1);
        ObserverOnNextListener<SearchKaoQianBuy> listener21 = new ObserverOnNextListener<SearchKaoQianBuy>() {
            @Override
            public void onNext(SearchKaoQianBuy data1) {

                if (data1.getCode() == 200) {
                    buy.setVisibility(GONE);
                } else {

                }

            }
        };
        ApiMethods.SearchKaoQianBuy(new MyObserver<SearchKaoQianBuy>(listener21), value.userid + "", value.noid + "", value.context);
        /*if (value.buy==1){
            buy.setVisibility(GONE);
        }else{
            buy.setVisibility(View.VISIBLE);
        }*/
        return view;
    }

    @Override
    //箭头的转换
    public void toggle(boolean active) {
        super.toggle(active);
        arrowView.setImageResource(active ? R.drawable.iconfont_jianhao : R.drawable.iconfont_jiahao);
        //arrowIcon.setImageResource(active ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right_small);
        bottomLine.setVisibility(active ? View.VISIBLE : View.INVISIBLE);
        bottomLine2.setVisibility(active ? GONE : View.VISIBLE);
        bottomLine3.setVisibility(active ? View.VISIBLE : GONE);
    }

    public static class IconTreeItem {
        public String text;
        private int noid;
        private String price;
        private String userid;
        private RxAppCompatActivity context;

        public IconTreeItem(String text, int noid, String price, String userid, RxAppCompatActivity context) {
            this.text = text;
            this.noid = noid;
            this.price = price;
            this.userid = userid;
            this.context = context;
        }

        public int getNoid() {
            return noid;
        }
    }
}
