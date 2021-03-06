package qf.com.myprojectlepai.utils;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import qf.com.myprojectlepai.Bean.zixunBean.FgridData;
import qf.com.myprojectlepai.Bean.zixunBean.FlistData;
import qf.com.myprojectlepai.Bean.zixunBean.GridData;
import qf.com.myprojectlepai.Bean.zixunBean.ListData;

/**
 * Created by lenovo on 2016/8/22.
 */
public class HandlerJson {

    private static FlistData flistData;
    private static FgridData fgridData;
    private  static List<ListData> dataLists;
    private  static List<GridData> dataGrids;
    private  static List<List> lists;
//    public HandlerJson(Handler handler) {
//        this.handler = handler;
//    }
    public static ExecutorService executor
            = Executors.newFixedThreadPool(10);
    public static  void  getJson(final String url,final Handler handler){
       executor.execute(new Runnable() {
           @Override
           public void run() {
               dataLists=new ArrayList<ListData>();
               dataGrids=new ArrayList<GridData>();
               lists=new ArrayList<List>();
               String JsonString= DownloadUtils.getJsonString(url);
               Gson gson=new Gson();
               //Log.d("fcr","jsonString==="+JsonString);
               String str1=JsonString.replace("160120","listDatas");
               String str=str1.replace("280280","gridDatas");
               flistData=gson.fromJson(str,FlistData.class);
               fgridData=gson.fromJson(str,FgridData.class);
               if (flistData!=null&&fgridData!=null){
                   for (ListData ff:flistData.getListDatas()) {
                       dataLists.add(ff);
                   }
                   for (GridData gg:fgridData.getGridDatas()){
                       dataGrids.add(gg);
                   }
               }

               lists.add(dataLists);
               lists.add(dataGrids);
               Message message=Message.obtain();
               message.obj=lists;
               handler.sendMessage(message);
           }
       });

    }
}
