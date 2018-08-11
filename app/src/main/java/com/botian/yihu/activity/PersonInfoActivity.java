package com.botian.yihu.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.ChangeUserInfo;
import com.botian.yihu.beans.UploadPhoto;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.UserInfoEvent;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.ScreenSizeUtils;
import com.botian.yihu.view.ForgetPasswordActivity;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import org.greenrobot.eventbus.EventBus;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonInfoActivity extends RxAppCompatActivity {
    @BindView(R.id.rl_user_icon)
    RelativeLayout rlUserIcon;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.rl_update_password)
    RelativeLayout rlUpdatePassword;
    @BindView(R.id.user_icon)
    CircleImageView userIcon;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.back)
    ImageView back;
    private ACache mCache;
    private UserInfo userInfo;
    //头像上传功能
    private Uri imageUri;
    protected static final int IMG_ITEM_CAMERA = 110;
    protected static final int IMG_ITEM_PICTURE = 111;
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        initView();
    }

    private void initView() {
        tvAccount.setText(userInfo.getMoblie());
        tvUsername.setText(userInfo.getUsername());
        String sex;
        if (userInfo.getSex() == 0) {
            sex = "女";
        } else {
            sex = "男";
        }
        tvGender.setText(sex);
        tvUsername.setText(userInfo.getUsername());
        Glide.with(this)
                .load("http://btsc.botian120.com" + userInfo.getAvatar())
                .into(userIcon);
    }

    @OnClick({R.id.rl_user_icon, R.id.rl_nickname, R.id.rl_gender, R.id.rl_update_password, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_user_icon:
                final Dialog dialog = new Dialog(PersonInfoActivity.this, R.style.NormalDialogStyle);
                View view1 = View.inflate(PersonInfoActivity.this, R.layout.dialog_picture, null);
                TextView takePhoto = view1.findViewById(R.id.take_photo);
                TextView picture = view1.findViewById(R.id.picture);

                dialog.setContentView(view1);
                //使得点击对话框外部消失对话框
                dialog.setCanceledOnTouchOutside(true);
                //设置对话框的大小
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = (int) (ScreenSizeUtils.getInstance(PersonInfoActivity.this).getScreenWidth() * 0.85f);
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //创建File对象,用于存储拍照后的图片
                        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                        try {
                            if (outputImage.exists()) {
                                outputImage.delete();
                            }
                            outputImage.createNewFile();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (Build.VERSION.SDK_INT >= 24) {
                            imageUri = FileProvider.getUriForFile(PersonInfoActivity.this, "fileProvider.fileProvider.fileProvider", outputImage);
                        } else {
                            imageUri = Uri.fromFile(outputImage);
                        }
                        // 调用系统的拍照功能
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 指定调用相机拍照后照片的储存路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, IMG_ITEM_CAMERA);
                        dialog.dismiss();
                    }
                });
                picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //从图册选择图片
                        if (ContextCompat.checkSelfPermission(PersonInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(PersonInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            openAlbum();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.rl_nickname:
                final String origin = tvUsername.getText().toString();
                final Dialog dialog3 = new Dialog(PersonInfoActivity.this, R.style.NormalDialogStyle);
                View view3 = View.inflate(PersonInfoActivity.this, R.layout.dialog_nickname, null);
                TextView cancel = view3.findViewById(R.id.cancel);
                TextView confirm = view3.findViewById(R.id.confirm);
                final EditText editText = view3.findViewById(R.id.edit_text);
                editText.setText(origin);
                dialog3.setContentView(view3);
                //使得点击对话框外部消失对话框
                dialog3.setCanceledOnTouchOutside(true);
                //设置对话框的大小
                Window dialogWindow1 = dialog3.getWindow();
                WindowManager.LayoutParams lp1 = dialogWindow1.getAttributes();
                lp1.width = (int) (ScreenSizeUtils.getInstance(PersonInfoActivity.this).getScreenWidth() * 0.85f);
                lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp1.gravity = Gravity.CENTER;
                dialogWindow1.setAttributes(lp1);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = editText.getText().toString();
                        if (str.length() > 1 && str.length() < 10) {
                            tvUsername.setText(str);
                        } else {
                            Toast.makeText(PersonInfoActivity.this, "字数不能小于1或大于10", Toast.LENGTH_SHORT).show();
                        }

                        dialog3.dismiss();
                        if (!str.equals(origin)) {
                            btnSubmit.setVisibility(View.VISIBLE);

                        }
                    }
                });
                dialog3.show();

                break;
            case R.id.rl_gender:
                final String origin1 = tvGender.getText().toString();
                final Dialog dialog2 = new Dialog(PersonInfoActivity.this, R.style.NormalDialogStyle);
                View view2 = View.inflate(PersonInfoActivity.this, R.layout.dialog_sex, null);
                TextView boy = view2.findViewById(R.id.boy);
                TextView girl = view2.findViewById(R.id.girl);

                dialog2.setContentView(view2);
                //使得点击对话框外部消失对话框
                dialog2.setCanceledOnTouchOutside(true);
                //设置对话框的大小
                Window dialogWindow2 = dialog2.getWindow();
                WindowManager.LayoutParams lp2 = dialogWindow2.getAttributes();
                lp2.width = (int) (ScreenSizeUtils.getInstance(PersonInfoActivity.this).getScreenWidth() * 0.85f);
                lp2.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp2.gravity = Gravity.CENTER;
                dialogWindow2.setAttributes(lp2);
                boy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvGender.setText("男");
                        dialog2.dismiss();
                        if (origin1.equals("女")) {
                            btnSubmit.setVisibility(View.VISIBLE);
                        }
                    }
                });
                girl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvGender.setText("女");
                        if (origin1.equals("男")) {
                            btnSubmit.setVisibility(View.VISIBLE);
                        }
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
                break;
            case R.id.rl_update_password:
                Intent intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_submit:
                final String username = tvUsername.getText().toString();
                final int sex;
                if (tvGender.getText().toString().equals("女")) {
                    sex = 0;
                } else {
                    sex = 1;
                }
                ObserverOnNextListener<ChangeUserInfo> listener6 = new ObserverOnNextListener<ChangeUserInfo>() {
                    @Override
                    public void onNext(ChangeUserInfo data) {
                        Toast.makeText(PersonInfoActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                        int id = userInfo.getId();
                        String token = userInfo.getToken();
                        String mobile = userInfo.getMoblie();
                        String avatar = userInfo.getAvatar();
                        UserInfo userInfo5 = new UserInfo();
                        userInfo5.setId(id);
                        userInfo5.setToken(token);
                        userInfo5.setUsername(username);
                        userInfo5.setMoblie(mobile);
                        userInfo5.setAvatar(avatar);
                        userInfo5.setSex(sex);
                        mCache.put("userInfo", userInfo5, 1200 * ACache.TIME_DAY);
                        EventBus.getDefault().post(new UserInfoEvent(username, null));
                        finish();
                    }
                };

                ApiMethods.changeUserInfo(new ProgressObserver<ChangeUserInfo>(PersonInfoActivity.this, listener6), userInfo.getId() + "", username, sex + "", this);
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, IMG_ITEM_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(PersonInfoActivity.this, "你拒绝了该权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IMG_ITEM_CAMERA:
                startPhotoZoom(imageUri, 200);
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null)
                    setPicToView(data);
                break;
            case IMG_ITEM_PICTURE:
                handleImage(data);
                break;
            default:
                break;
        }

    }

    //解析图片真实路径
    private void handleImage(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        Uri imageUri1;
        imageUri1 = Uri.parse(imagePath);
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri1 = FileProvider.getUriForFile(PersonInfoActivity.this, "fileProvider.fileProvider.fileProvider", new File(imageUri1.getPath()));

        }
        startPhotoZoom(imageUri1, 200);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    // 将进行剪裁后的图片显示到UI界面上
    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            userIcon.setImageBitmap(photo);
            final File file = set(photo);
            uploadFile(file);
            EventBus.getDefault().post(new UserInfoEvent("", photo));
            //Log.i("123", "头像地址: " + file.toString());
        }
    }

    public void uploadFile(File file) {
        ObserverOnNextListener<UploadPhoto> listener = new ObserverOnNextListener<UploadPhoto>() {
            @Override
            public void onNext(UploadPhoto data) {
                Toast.makeText(PersonInfoActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                //缓存用户信息
                UserInfo userInfo8 = new UserInfo();
                userInfo8.setId(userInfo.getId());
                userInfo8.setToken(userInfo.getToken());
                userInfo8.setUsername(userInfo.getUsername());
                userInfo8.setMoblie(userInfo.getMoblie());
                userInfo8.setAvatar(data.getData());
                userInfo8.setSex(userInfo.getSex());
                mCache.put("userInfo", userInfo8, 1200 * ACache.TIME_DAY);
            }
        };
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        String a = file.getName();
        ApiMethods.getPhoto(new ProgressObserver<UploadPhoto>(PersonInfoActivity.this, listener), userInfo.getId() + "", filePart, this);
    }

    public File set(Bitmap mBitmap) {
        File imgFile = new File(getExternalCacheDir(), "output_image2.jpg");
        try {
            if (imgFile.exists()) {
                imgFile.delete();
            }
            imgFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream bitmapWtriter = new FileOutputStream(imgFile);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bitmapWtriter);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return imgFile;
    }

    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
