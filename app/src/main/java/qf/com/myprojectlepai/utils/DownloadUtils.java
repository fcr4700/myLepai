package qf.com.myprojectlepai.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2016/8/22.
 */
public class DownloadUtils {
    private static String jsonString;
    private static byte[] image;
    private static  Boolean flag=false;
    public static String getJsonString(String url){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();

        try {
            Response response=client.newCall(request).execute();
            jsonString=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("fcr", "downjson----"+jsonString);
        return jsonString;
    }
    public static Bitmap getImage(String url){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try {
            Response response=client.newCall(request).execute();
            image=response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //二次采样
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(image,0,image.length,options);
        options.inJustDecodeBounds=false;
        options.inSampleSize=3;
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length,options);
        //Log.d("fcr","======"+image.length);
        Log.d("fcr","==下载图片进入二次采样==");
        return bitmap;
    }
    public static boolean fileList(File[] file, String imaString){

        for(File ff:file){
            if(ff.getName().equals(imaString+".png")){
                flag=true;
                break;
            }else{
                flag=false;
            }
        }
        return flag;
    }


}
