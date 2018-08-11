package com.botian.yihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.activity.VideoActivity;
import com.botian.yihu.androidTreeListView.VideoCatalogChild;
import com.botian.yihu.androidTreeListView.VideoCatalogParent;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.VideoCataLog;
import com.botian.yihu.beans.VideoInfo;
import com.botian.yihu.beans.VodRspData;
import com.botian.yihu.eventbus.VideoBuyEvent2;
import com.botian.yihu.eventbus.VideoEvent;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXPlayerAuthBuilder;
import com.tencent.rtmp.TXVodPlayer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//资讯
public class VideoCatalogFragment extends RxFragment {
    private TXVodPlayer mTXPlayerGetInfo;
    @BindView(R.id.container)
    LinearLayout container;
    Unbinder unbinder;
    private List<VideoCataLog.DataBean> videoCataLogList = new ArrayList<>();//视频一集列表数据
    private List<VideoCataLog.DataBean> videoCataLogList2 = new ArrayList<>();//视频二级列表数据
    private int position = 0;
    private String mid;
    private VideoActivity activity;
    private String buy = "0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        activity = (VideoActivity) getActivity();
        buy = activity.getBuy();
        Bundle bundle = getArguments();
        mid = bundle.getString("ARGS");
        ObserverOnNextListener<VideoCataLog> listener = new ObserverOnNextListener<VideoCataLog>() {
            @Override
            public void onNext(VideoCataLog data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    VideoCataLog.DataBean videoCataLog = new VideoCataLog.DataBean();
                    videoCataLog.setId(data.getData().get(i).getId());
                    videoCataLog.setPid(data.getData().get(i).getPid());
                    videoCataLog.setTypename(data.getData().get(i).getTypename());
                    videoCataLog.setBuy(data.getData().get(i).getBuy());
                    if (data.getData().get(i).getPid() == 0) {
                        videoCataLogList.add(videoCataLog);
                    } else {
                        videoCataLogList2.add(videoCataLog);
                    }

                }
                setList();

            }
        };
        ApiMethods.getVideoDirectory(new ProgressObserver<VideoCataLog>(getActivity(), listener), "video_id,eq," + mid, (RxAppCompatActivity) getActivity());

    }

    public void setList() {
        TreeNode root = TreeNode.root();
        for (int i = 0; i < videoCataLogList.size(); i++) {
            TreeNode folder = new TreeNode(new VideoCatalogParent.IconTreeItem(videoCataLogList.get(i).getTypename(), videoCataLogList.get(i).getId())).setViewHolder(new VideoCatalogParent(getActivity()));
            fillFolder(folder, videoCataLogList.get(i).getId());
            root.addChildren(folder);
        }
        AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
        //tView.setDefaultAnimation(true);
        container.addView(tView.getView());
    }

    private void fillFolder(TreeNode folder, int id) {
        for (int i = position; i < videoCataLogList2.size(); i++) {
            if (videoCataLogList2.get(i).getPid() == id) {
                TreeNode file = new TreeNode(new VideoCatalogChild.IconTreeItem(videoCataLogList2.get(i).getTypename(), videoCataLogList2.get(i).getId(), buy, videoCataLogList2.get(i).getBuy())).setViewHolder(new VideoCatalogChild(getActivity()));
                folder.addChildren(file);
                file.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        int id = ((VideoCatalogChild.IconTreeItem) value).getNoid();
                        int buy1 = ((VideoCatalogChild.IconTreeItem) value).getBuy1();
                        ObserverOnNextListener<VideoInfo> listener3 = new ObserverOnNextListener<VideoInfo>() {
                            @Override
                            public void onNext(VideoInfo data) {
                                String videoid = data.getData().getData().get(0).getVideo_id();
                                mTXPlayerGetInfo = new TXVodPlayer(getActivity());

                                TXPlayerAuthBuilder authBuilder = new TXPlayerAuthBuilder();
                                authBuilder.setAppId(1254402451);
                                authBuilder.setFileId(videoid);
                                mTXPlayerGetInfo.startPlay(authBuilder);
                                mTXPlayerGetInfo.setVodListener(mGetVodInfoListener);
                            }

                        };
                        if (buy.equals("1")) {
                            ApiMethods.getVideoInfo(new MyObserver<VideoInfo>(listener3), "typeid,eq," + id, (RxAppCompatActivity) getActivity());
                        } else {
                            if (buy1!=0){
                                Toast.makeText(getActivity(), "请先购买", Toast.LENGTH_SHORT).show();

                            }else {
                                ApiMethods.getVideoInfo(new MyObserver<VideoInfo>(listener3), "typeid,eq," + id, (RxAppCompatActivity) getActivity());

                            }
                        }
                    }
                });
            } else {
                position = i;
                break;
            }

        }
    }

    private ITXVodPlayListener mGetVodInfoListener = new ITXVodPlayListener() {
        @Override
        public void onPlayEvent(TXVodPlayer player, int event, Bundle param) {
            String playEventLog = "receive event: " + event + ", " + param.getString(TXLiveConstants.EVT_DESCRIPTION);

            if (event == TXLiveConstants.PLAY_EVT_GET_PLAYINFO_SUCC) { // 获取点播文件信息成功
                VodRspData data = new VodRspData();
                data.cover = param.getString(TXLiveConstants.EVT_PLAY_COVER_URL);

                data.url = param.getString(TXLiveConstants.EVT_PLAY_URL);
                data.title = param.getString(TXLiveConstants.EVT_PLAY_NAME);

                String url = data.url;
                String cover = data.cover;
                String title = data.title;

                EventBus.getDefault().post(new VideoEvent(cover, url, title));

            }
        }

        @Override
        public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

        }
    };

    public static VideoCatalogFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        VideoCatalogFragment fragment = new VideoCatalogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(VideoBuyEvent2 messageEvent) {
        buy = "1";
        position=0;
        container.removeAllViews();
        setList();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
