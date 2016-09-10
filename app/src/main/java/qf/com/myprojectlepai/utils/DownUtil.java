package qf.com.myprojectlepai.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 16-9-6.
 */
public class DownUtil {
    LruUtils lruUtils=new LruUtils();
    public static void setImageToImageView(final String url1, final String url2, final Handler handler, final ImageView imageView1, final ImageView imageView2) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("fcr","==进入下载==run==");
                final Bitmap bitmap1 = downLoadImage(url1);
                Log.d("fcr","==bitmap1=="+bitmap1);
                final Bitmap bitmap2 = downLoadImage(url2);
                Log.d("fcr","==bitmap2=="+bitmap2);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView1.setImageBitmap(bitmap1);
                        imageView2.setImageBitmap(bitmap2);
                    }
                });
            }
        });
    }

    public static Bitmap downLoadImage(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request=new Request.Builder().url(url).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            byte[] bs = response.body().bytes();
            return BitmapFactory.decodeByteArray(bs, 0, bs.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String downJsonString(String url) {
        Log.d("fcr","==进入下载json数据的方法=="+url);
        String jsonString = "";
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();

            jsonString = response.body().string();
            Log.d("fcr", "下载的json数据 ===" + jsonString);
            //return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    public static void setImageToImageView(final String url, final Handler handler, final ImageView imageView) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downLoadImage(url);

                Log.d("fcr","重载bitmap====="+bitmap);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
}
