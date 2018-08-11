package com.botian.yihu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_qq_server)
    TextView tvQqServer;
    private String[] perms = {Manifest.permission.CALL_PHONE};
    private final int PERMS_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.back, R.id.tv_qq_server})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_qq_server:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {//Android 6.0以上版本需要获取权限
                    requestPermissions(perms,PERMS_REQUEST_CODE);//请求权限
                } else {
                    callPhone();
                }
                break;
        }
    }
    /**
     * 获取权限回调方法
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "0371-56871616"));
            startActivity(intent);
        }
    }
}
