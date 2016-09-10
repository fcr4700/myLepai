package qf.com.myprojectlepai.myActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import qf.com.myprojectlepai.Bean.zixunBean.PicShow;
import qf.com.myprojectlepai.Bean.zixunBean.picShow.PicShowContent;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.adapter.PicShowViewpagerAdapter;
import qf.com.myprojectlepai.utils.DownloadUtils;
import qf.com.myprojectlepai.utils.LoadJson;

/**
 * Created by Administrator on 16-9-8.
 */
public class PicShowActivity extends AppCompatActivity{
    ViewPager viewPager;
    ExecutorService executor= Executors.newFixedThreadPool(20);

    ImageView imageView;
    List<PicShowContent> picShowContents;
    //PicshowOut picshowOut;
    List<ImageView> dataList;

    PicShowViewpagerAdapter adapter;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr= (String) msg.obj;
            Gson gson=new Gson();
            final List<PicShow> piclist= gson.fromJson(jsonStr,new TypeToken<List<PicShow>>(){}.getType());
            //此handler为主线程 下载图片需要在子线程中进行
            new Thread(new Runnable() {
                @Override
                public void run() {

                    if (piclist!=null){
                        for (int i = 0; i <piclist.size() ; i++) {
                            imageView=new ImageView(getApplicationContext());
                            //imageView.setImageResource(R.drawable.logo_googleplus);
                            final Bitmap bitmap= DownloadUtils.getImage(piclist.get(i).getPic().getGq());
//                            if (bitmap!=null){
//                                imageView.setImageBitmap(bitmap);
//                            }else{
//                                imageView.setImageResource(R.mipmap.ic_launcher);
//                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    dataList.add(imageView);
                                    if (bitmap!=null){
                                        imageView.setImageBitmap(bitmap);
                                    }else{
                                        imageView.setImageResource(R.mipmap.ic_launcher);
                                    }
                                    Log.d("fcr","==download=image=num：="+dataList.size());
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                }
            }).start();
            adapter=new PicShowViewpagerAdapter(dataList,getApplicationContext());
            viewPager.setAdapter(adapter);
        }
    };

    private void Jsonparse(String jsonStr) {
        JSONObject jsonObject=new JSONObject();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_activity_show);
        viewPager= (ViewPager) findViewById(R.id.pic_show_viewpagerId);
        imageView= (ImageView) findViewById(R.id.pic_imageId);
        dataList=new ArrayList<>();

        initData();


    }

    private void initData() {
        final String url=getIntent().getStringExtra("pic_show_url");
        Log.d("pic","==接收到的网址=="+url);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String jsonData=LoadJson.loadJson(url);
                Message message=Message.obtain();
                message.obj=jsonData;
                handler.sendMessage(message);
            }
        });
    }
}
