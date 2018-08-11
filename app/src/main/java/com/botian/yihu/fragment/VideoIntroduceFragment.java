package com.botian.yihu.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.activity.VideoActivity;
import com.botian.yihu.activity.VideoBuyActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.VodeoBuyList;
import com.botian.yihu.eventbus.VideoBuyEvent;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//资讯
public class VideoIntroduceFragment extends Fragment {
    String title1;
    String price1;
    String content1;
    String id;
    Unbinder unbinder;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.phone)
    Button phone;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.show)
    WebView show;
    private ACache mCache;
    private UserInfo userInfo;
    VideoActivity activity;
    private String[] perms = {Manifest.permission.CALL_PHONE};
    private final int PERMS_REQUEST_CODE = 200;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        mCache = ACache.get(getActivity());
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        activity = (VideoActivity) getActivity();
        Bundle bundle = getArguments();
        title1 = bundle.getString("ARGS");
        price1 = bundle.getString("ARGS1");
        content1 = bundle.getString("ARGS2");
        id = bundle.getString("ARGS3");
        String strPrice =  price1+"金币";
        price.setText(strPrice);
        searchBuy();
        String text = Html.fromHtml(content1).toString();
        //http://btsc.botian120.com
        String cc = text.replaceAll("<img src=\"", "<img src=\"http://btsc.botian120.com");
        show.loadDataWithBaseURL(null, getNewContent(cc), "text/html", "utf-8", null);

    }
    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext) {
        try {
            Document doc = Jsoup.parse(htmltext);
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
            }

            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }
    public static VideoIntroduceFragment newInstance(String title, String price, String content, String id) {
        Bundle args = new Bundle();
        args.putString("ARGS", title);
        args.putString("ARGS1", price);
        args.putString("ARGS2", content);
        args.putString("ARGS3", id);
        VideoIntroduceFragment fragment = new VideoIntroduceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.phone, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone:
                final Dialog dialog2 = new Dialog(getActivity(), R.style.NormalDialogStyle);

                View view2 = View.inflate(getActivity(), R.layout.dialog_buy, null);
                LinearLayout lnPhone = view2.findViewById(R.id.ln_phone);
                LinearLayout lnQq = view2.findViewById(R.id.ln_qq);

                dialog2.setContentView(view2);
                //使得点击对话框外部消失对话框
                dialog2.setCanceledOnTouchOutside(true);
                //设置对话框的大小
                Window dialogWindow2 = dialog2.getWindow();
                dialogWindow2.setWindowAnimations(R.style.bottom_menu_animation);
                WindowManager.LayoutParams lp2 = dialogWindow2.getAttributes();
                //lp2.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.85f);
                lp2.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp2.gravity = Gravity.BOTTOM;
                //dialogWindow2.setAttributes(lp2);
                lnPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog2.dismiss();
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {//Android 6.0以上版本需要获取权限
                            requestPermissions(perms, PERMS_REQUEST_CODE);//请求权限
                        } else {
                            callPhone();
                        }
                        /**
                         * 拨打电话（直接拨打电话）
                         *
                         * @param phoneNum 电话号码
                         */

                    }
                });
                lnQq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog2.dismiss();
                        if (isQQClientAvailable(getActivity())) {
                            final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=408685957&version=1";
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                        } else {
                            Toast.makeText(getActivity(), "请安装QQ客户端", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog2.show();
                break;
            case R.id.buy:
                ObserverOnNextListener<VodeoBuyList> listenerllf = new ObserverOnNextListener<VodeoBuyList>() {
                    @Override
                    public void onNext(VodeoBuyList data) {
                        if (data.getData().size() <= 0) {
                            //buy.setText("已购买");
                            Intent intent = new Intent(getActivity(), VideoBuyActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("money", price1);
                            intent.putExtra("title", title1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "已经购买过，无需再次购买", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                String filter = "userid,eq," + userInfo.getId();
                String filter2 = "column_id,eq," + SubjectUtil.getSubjectNo2();
                String filter3 = "video_id,eq," + id;
                ApiMethods.getVideoBuyList2(new ProgressObserver<VodeoBuyList>(getActivity(), listenerllf), filter, filter2, filter3, (RxAppCompatActivity) getActivity());


                break;
        }
    }


    public void searchBuy() {
        ObserverOnNextListener<VodeoBuyList> listenerll = new ObserverOnNextListener<VodeoBuyList>() {
            @Override
            public void onNext(VodeoBuyList data) {
                if (data.getData().size() > 0) {
                    buy.setText("已购买");
                    activity.setBuy("1");
                }
            }
        };
        String filter = "userid,eq," + userInfo.getId();
        String filter2 = "column_id,eq," + SubjectUtil.getSubjectNo2();
        String filter3 = "video_id,eq," + id;
        ApiMethods.getVideoBuyList(new MyObserver<VodeoBuyList>(listenerll), filter, filter2, filter3, (RxAppCompatActivity) getActivity());

    }

    /**
     * 获取权限回调方法
     *
     * @param permsRequestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case PERMS_REQUEST_CODE:
                boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (storageAccepted) {
                    callPhone();
                } else {
                    //Log.i("MainActivity", "没有权限操作这个请求");
                }
                break;

        }
    }

    //拨打电话
    private void callPhone() {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "0371-56871616"));
            startActivity(intent);
        }
    }

    //判断qq客户端是否安装
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(VideoBuyEvent messageEvent) {
        buy.setText("已购买");
        activity.setBuy("1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
