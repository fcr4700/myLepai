package qf.com.myprojectlepai.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 16-9-8.
 */
public class LoadJson {
    public static String loadJson(String url){
        String jsonString="";
        OkHttpClient ok=new OkHttpClient();
        if (url!=null){
            Request request=new Request.Builder().url(url).build();
            Response response=null;
            try {
                response=ok.newCall(request).execute();
                jsonString=response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonString;
    }


}
