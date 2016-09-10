package qf.com.myprojectlepai.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by lenovo on 2016/8/26.
 */
public class LruUtils {
    private static LruCache<String ,Bitmap> lruCache;
    public static LruCache initLru(){
        int maxSize=4*1024*1024;
        return lruCache=new LruCache<>(maxSize);
    }
    public static Bitmap getImage(String url){
        if (lruCache!=null){
            Log.d("lruD","读取一级缓存");
            return lruCache.get(url);

        }else{
            return null;
        }
    }
    public static void saveImage(String url,Bitmap bitmap){
        if (getImage(url)==null){
            Log.d("lruH","一级缓存成功。。");
            lruCache.put(url,bitmap);
        }
    }
}
