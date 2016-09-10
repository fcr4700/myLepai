package qf.com.myprojectlepai.utils;

import android.os.Handler;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2016/9/5.
 */
public class FHeadlerJson {
    public static ExecutorService executor
            = Executors.newFixedThreadPool(10);

    public static void getJson(final String url, final Handler handler) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String JsonString = DownloadUtils.getJsonString(url);
                //Log.d("fcr","JsonString----"+JsonString);
                Message message=Message.obtain();
                message.obj=JsonString;
                handler.sendMessage(message);
            }
        });
    }
}