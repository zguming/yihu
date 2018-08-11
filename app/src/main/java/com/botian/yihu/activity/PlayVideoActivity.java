package com.botian.yihu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.botian.yihu.R;
import com.botian.yihu.util.JZMediaIjkplayer;
import com.tencent.rtmp.TXVodPlayer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class PlayVideoActivity extends RxAppCompatActivity {
    private TXVodPlayer mTXPlayerGetInfo;
    String url;
    String title;
    String cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        cover = intent.getStringExtra("cover");
        int appId = 1254402451;
        String fileId = "7447398155509167622";
        //mTXPlayerGetInfo = new TXVodPlayer(this);
        //mTXPlayerGetInfo.setVodListener(mGetVodInfoListener);

        //TXPlayerAuthBuilder authBuilder = new TXPlayerAuthBuilder();
        //authBuilder.setAppId(appId);
        //authBuilder.setFileId(fileId);
        //mTXPlayerGetInfo.startPlay(authBuilder);
        //mTXPlayerGetInfo.setVodListener(mGetVodInfoListener);
        playVideo();
    }

    /*private ITXVodPlayListener mGetVodInfoListener = new ITXVodPlayListener() {
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
                if (data.title == null || data.title.length() == 0) {
                    data.title = param.getString(TXLiveConstants.EVT_PLAY_NAME);
                }
                playVideo(url, cover, title);
            }
        }

        @Override
        public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

        }
    };*/

    public void playVideo() {
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
        JZVideoPlayer.setMediaInterface(new JZMediaIjkplayer());
        //jzVideoPlayerStandard.startFullscreen(this, JZVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
        jzVideoPlayerStandard.setUp(url
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, title);
        com.bumptech.glide.Glide.with(PlayVideoActivity.this).load(cover).into(jzVideoPlayerStandard.thumbImageView);
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
}