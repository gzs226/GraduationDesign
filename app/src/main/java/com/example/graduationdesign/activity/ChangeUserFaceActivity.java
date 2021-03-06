package com.example.graduationdesign.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.SavePhotoUtils;
import com.example.weblibrary.model.UpdateIconResult;
import com.example.weblibrary.user.UserUpLoadImage;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeUserFaceActivity extends BaseActivity {
    private static final String TAG = "ChangeUserFaceActivity";
    private static final String LOG_TAG = "ChangeUserFaceActivity";
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.iv_personal_icon)
    ImageView ivPersonalIcon;
    @BindView(R.id.btn_change)
    Button btnChange;
    private final int ICON_BACK_TO_MESSAGE_CODE = 103;
    private String userIconUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_face);
        ButterKnife.bind(this);

        rename();

        headerCenterText.setText("我的头像");
        ivPersonalIcon.setMaxHeight(ivPersonalIcon.getWidth());
        btnChange.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        Log.e(TAG, "" + tempUri.toString());
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 获取当前时间戳
     *
     * @return 返回时间戳-String
     */
    private String getNewData() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
        str = ConfigUserMessagePrefs.getValue(ChangeUserFaceActivity.this, Contents.USER_ID,"");

        return str;
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            // photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            ivPersonalIcon.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String appEnvironmentPAth = this.getFilesDir().getPath();

        String appPath = this.getExternalFilesDir(null) + "";
        String imagePath = SavePhotoUtils.savePhoto(bitmap, /*Environment.getExternalStorageDirectory().getAbsolutePath()*/appPath, String.valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "--" + appEnvironmentPAth);
        Log.e("imagePath",
                "Environment" + "--" + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.e("imagePath", "getExternalFilesDir" + "--" + this.getExternalFilesDir(null));
                if (imagePath != null) {
                    // 拿着imagePath上传了
                    // ...
                    UserUpLoadImage.updateUserFaceImage(imagePath,
                            getNewData() , new UserUpLoadImage.IUpdateUserFaceImage() {
                                @Override
                                public void Success(String response) {
                                    Gson gson = new Gson();
                                    UpdateIconResult mUpdateIconResult = gson.fromJson(response, UpdateIconResult.class);
                                    if (mUpdateIconResult.getRetdata().equals("1")) {
                                        userIconUrl = mUpdateIconResult.getIconurl();
                                        ResultUserMessage();
                                    }
                                }

                                @Override
                                public void Faild(Request request) {

                                }
                            });

                }
    }

    public void rename() {
        File oleFile = new File("/storage/emulated/0/Android/data/com.example.graduationdesign/files/1486958282244.png"); //要重命名的文件或文件夹
        File newFile = new File("/storage/emulated/0/Android/data/com.example.graduationdesign/files/3333.png");  //重命名为zhidian1
        oleFile.renameTo(newFile);  //执行重命名
    }

    private void ResultUserMessage() {
        Intent intent = new Intent(ChangeUserFaceActivity.this, UserMessageActivity.class);
        intent.putExtra("iconurl", userIconUrl);
        ChangeUserFaceActivity.this.setResult(ICON_BACK_TO_MESSAGE_CODE, intent);
        ChangeUserFaceActivity.this.finish();
    }

    @OnClick(R.id.linear_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
        }
    }
}