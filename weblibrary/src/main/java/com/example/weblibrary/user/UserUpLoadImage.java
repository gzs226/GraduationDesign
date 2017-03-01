package com.example.weblibrary.user;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.example.weblibrary.URL;
import com.example.weblibrary.model.UpdateIconResult;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gg on 2017/1/4.
 */
public class UserUpLoadImage {
    private static final String IMAGE_FORMAT = "png";

    private String getNewData() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
        return str;
    }

    /**
     * 判断图片的格式
     *
     * @param image_path 照片的路径
     */
    private static String getImageFormat(String image_path) {
        String path = image_path;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        String imageType = options.outMimeType;
        if (TextUtils.isEmpty(imageType)) {
            imageType = "未能识别的图片";
        } else {
            imageType = imageType.substring(6, imageType.length());
        }
        Log.d("mOkHttpClient", "ImageFormat-->" + imageType);
        return imageType;

    }

    /**
     * 上传用户的头像
     *
     * @param imagePath 照片的路径 XX.png等
     * @param imageName 照片的名字-->上传到服务起的照片名字
     */
    public static void updateUserFaceImage(String imagePath, String imageName, final IUpdateUserFaceImage mIUpdateUserFaceImage) {
        getImageFormat(imagePath);

        OkHttpClient mOkHttpClient = new OkHttpClient();
        File file = new File(imagePath);
        Log.e("imagePath", "updateUserFaceImage: "+ imagePath);
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //1.创建okhttp的对象
        MultipartBuilder mMultipartBuilder = new MultipartBuilder();

        RequestBody requestBody = mMultipartBuilder.type(MultipartBuilder.FORM).addFormDataPart("userid", imageName).addFormDataPart("usericon", imageName+".png", mRequestBody).build();

        Request.Builder mBuider = new Request.Builder();
        Request mRequest = mBuider.url(URL.uploadImage).post(requestBody).build();

        Call mcall = mOkHttpClient.newCall(mRequest);

        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("mOkHttpClient", "--------failure" + request.urlString());
                Log.e("mOkHttpClient", "--------failure" + request.url());
                Log.e("mOkHttpClient", "--------failure" + request.method());
                Log.e("mOkHttpClient", "--------failure" + request.headers());
                Log.e("mOkHttpClient", "--------failureeee" + e);
                mIUpdateUserFaceImage.Faild(request);
            }

            @Override
            public void onResponse(Response response) throws
                    IOException {
                String string = response.body().string();
                Log.e("mOkHttpClient", "--------failure--string--" + string);
                Log.e("mOkHttpClient", "--------failure" + response.message());
                Log.e("mOkHttpClient", "--------failure" + response.isSuccessful());
                Log.e("mOkHttpClient", "--------failure" + response.code());
                Log.e("mOkHttpClient", "--------success");
                mIUpdateUserFaceImage.Success(string);
                Gson gson = new Gson();
                UpdateIconResult person = gson.fromJson(string, UpdateIconResult.class);

                Log.e("gson", "--------" + person.getIconurl());
                Log.e("gson", "--------" + person.getRetdata());


            }
        });
    }

    public interface IUpdateUserFaceImage {
        void Success(String response);

        void Faild(Request request);

    }
}
