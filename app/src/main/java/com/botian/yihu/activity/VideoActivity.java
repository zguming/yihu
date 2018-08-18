package com.botian.yihu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.VideoInfo;
import com.botian.yihu.beans.VodRspData;
import com.botian.yihu.eventbus.VideoEvent;
import com.botian.yihu.fragment.VideoCatalogFragment;
import com.botian.yihu.fragment.VideoDisscussFragment;
import com.botian.yihu.fragment.VideoIntroduceFragment;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.JZMediaIjkplayer;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXPlayerAuthBuilder;
import com.tencent.rtmp.TXVodPlayer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends RxAppCompatActivity {

    @BindView(R.id.introduce)
    TextView introduce;
    @BindView(R.id.directory)
    TextView directory;
    @BindView(R.id.appraise)
    TextView appraise;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    private Fragment currentFragment;
    private VideoIntroduceFragment videoIntroduceFragment;
    private VideoCatalogFragment videoCatalogFragment;
    private VideoDisscussFragment videoDisscussFragment;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    String id;
    String title;
    String price;
    String content;
    private TXVodPlayer mTXPlayerGetInfo;
    JZVideoPlayerStandard jzVideoPlayerStandard;
    String url;
    private int init = 0;
    private String url1;
    private String buy = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        content = intent.getStringExtra("content");
        videoIntroduceFragment = VideoIntroduceFragment.newInstance(title, price, content, id);
        videoCatalogFragment = VideoCatalogFragment.newInstance(id);
        videoDisscussFragment = VideoDisscussFragment.newInstance(id);
        setDefaultFragment();
        ObserverOnNextListener<VideoInfo> listener = new ObserverOnNextListener<VideoInfo>() {
            @Override
            public void onNext(VideoInfo data) {
                String videoid = data.getData().getData().get(0).getVideo_id();
                mTXPlayerGetInfo = new TXVodPlayer(VideoActivity.this);

                TXPlayerAuthBuilder authBuilder = new TXPlayerAuthBuilder();
                authBuilder.setAppId(1254402451);
                authBuilder.setFileId(videoid);
                mTXPlayerGetInfo.startPlay(authBuilder);
                mTXPlayerGetInfo.setVodListener(mGetVodInfoListener);


            }
        };
        //视频目录第一条数据
        ApiMethods.getVideoDirectory1(new ProgressObserver<VideoInfo>(this, listener), "video_id,eq," + id, "pid,gt,0", this);
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
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
                //if (init == 0) {
                    //url1 = data.url;
                    //url = data.url;
                    //init = 1;
                //} else {
                    url = data.url;
                //}

                String cover = data.cover;
                String title = data.title;
                if (data.title == null || data.title.length() == 0) {
                    data.title = param.getString(TXLiveConstants.EVT_PLAY_NAME);
                }
                playVideo(url, cover, title);
            }
        }

        @Override
        public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

        }
    };

    /**
     * 设置默认的
     */

    private void setDefaultFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_layout, videoIntroduceFragment);
        transaction.commit();
        currentFragment = videoIntroduceFragment;
    }

    //用add show hide方式切换fragment
    private void switchContent(Fragment from, Fragment to) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(R.id.fragment_layout, to).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }

    @OnClick({R.id.introduce, R.id.directory, R.id.appraise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.introduce:
                introduce.setTextColor(getResources().getColor(R.color.blue));
                //directory.setTextColor(getResources().getColor(R.color.default_text));
                //appraise.setTextColor(getResources().getColor(R.color.default_text));
                directory.setTextColor(getResources().getColor(R.color.textColorBlack));
                appraise.setTextColor(getResources().getColor(R.color.textColorBlack));

                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                switchContent(currentFragment, videoIntroduceFragment);
                currentFragment = videoIntroduceFragment;
                break;
            case R.id.directory:
                introduce.setTextColor(getResources().getColor(R.color.textColorBlack));
                directory.setTextColor(getResources().getColor(R.color.blue));
                appraise.setTextColor(getResources().getColor(R.color.textColorBlack));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                switchContent(currentFragment, videoCatalogFragment);
                currentFragment = videoCatalogFragment;
                break;
            case R.id.appraise:
                introduce.setTextColor(getResources().getColor(R.color.textColorBlack));
                directory.setTextColor(getResources().getColor(R.color.textColorBlack));
                appraise.setTextColor(getResources().getColor(R.color.blue));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                switchContent(currentFragment, videoDisscussFragment);
                currentFragment = videoDisscussFragment;
                break;
        }
    }

    public void playVideo(String url,
                          String cover,
                          String title) {
        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
        JZVideoPlayer.setMediaInterface(new JZMediaIjkplayer());
        jzVideoPlayerStandard.setUp(url
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, title);
        com.bumptech.glide.Glide.with(this).load(cover).into(jzVideoPlayerStandard.thumbImageView);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        //Change these two variables back
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(VideoEvent videoEvent) {
        jzVideoPlayerStandard.setUp(videoEvent.getUrl()
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoEvent.getTitle());
        if (!url.equals(videoEvent.getUrl())) {
            //Log.d("TAG", "Event: "+url.equals(videoEvent.getUrl()));
            jzVideoPlayerStandard.startButton.performClick();
            url = videoEvent.getUrl();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}