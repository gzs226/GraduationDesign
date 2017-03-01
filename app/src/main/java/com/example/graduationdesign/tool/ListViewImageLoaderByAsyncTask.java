package com.example.graduationdesign.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.graduationdesign.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListViewImageLoaderByAsyncTask {

    private ListView mlistview;
    private Set<newAsyncTask> mTask;
    private LruCache<String, Bitmap> mCache;

    public ListViewImageLoaderByAsyncTask(ListView listview) {
        mlistview = listview;
        mTask = new HashSet<newAsyncTask>();
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };

    }

    //增加到缓存
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCache.put(url, bitmap);
        }
    }

    //从缓存中获取数据
    public Bitmap getBitmapFromCache(String url) {
        return mCache.get(url);
    }

    public void showImageByAsyncTask(ImageView imageview, String url) {
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            imageview.setImageResource(R.drawable.ic_launcher);
        } else {
            imageview.setImageBitmap(bitmap);
        }
    }

    public void loadImages(String IconUrl, ImageView imageView, String UrlTag) {

        Bitmap bitmap = getBitmapFromCache(IconUrl);
        if (bitmap == null) {
            //如果图片不再缓存中，则添加到缓存中。
            newAsyncTask newAsyncTask = new newAsyncTask(IconUrl, UrlTag);
            newAsyncTask.execute(IconUrl);
            mTask.add(newAsyncTask);
        } else {
            imageView.setImageBitmap(bitmap);
        }

    }

    public void cancleAllTasks() {
        if (mTask != null) {
            for (newAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }

    private class newAsyncTask extends AsyncTask<String, Void, Bitmap> {

        // private ImageView mimageview;
        private String murl;
        private String UrlTag;

        public newAsyncTask(String murl, String UrlTag) {
            //    this.mimageview = imageview;
            this.murl = murl;
            this.UrlTag = UrlTag;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mlistview.findViewWithTag(UrlTag);
            Log.e("onPostExecute", "onPostExecute: " + (imageView != null));
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }


        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            Bitmap bitmap = getBitmapFromUrl(url);
            if (bitmap != null) {
                //将不在缓存中的图片增加到缓存
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }
    }

    public Bitmap getBitmapFromUrl(String urlstring) {
        Bitmap bitmap;
        InputStream is;
        try {
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}