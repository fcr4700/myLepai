package qf.com.myprojectlepai.myActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import qf.com.myprojectlepai.Bean.zixunBean.bbs_rt.Bbs_Rt;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.WebViewActivity;
import qf.com.myprojectlepai.adapter.Bbs_rtAdapter;
import qf.com.myprojectlepai.utils.LoadJson;

/**
 * Created by Administrator on 16-9-8.
 */
public class BbsRT extends AppCompatActivity{

    ListView listView;
    Intent intent;
    Bbs_rtAdapter adapter;
    private static final String URL_STRING="http://api.fengniao.com/app_ipad/bbs_all_hot.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&page=1";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr= (String) msg.obj;
            Gson gson=new Gson();
            Bbs_Rt rt=gson.fromJson(jsonStr,Bbs_Rt.class);
            if (rt.getList()!=null){
                dataList.addAll(rt.getList());
            }

            adapter=new Bbs_rtAdapter(getApplicationContext(),dataList);
            listView.setAdapter(adapter);


        }
    };

    List<Bbs_Rt.ListBean> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_activity_rt);
        listView= (ListView) findViewById(R.id.bbs_rt_listviewId);
        dataList=new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonStr= LoadJson.loadJson(URL_STRING);
                Message message=Message.obtain();
                message.obj=jsonStr;
                handler.sendMessage(message);



            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonString=LoadJson.loadJson(URL_STRING);
                Gson gson=new Gson();
                Log.d("bbs","==jsonData=="+jsonString);
                Bbs_Rt bbs_rt=gson.fromJson(jsonString,Bbs_Rt.class);
                if (bbs_rt.getList()!=null) {
                    dataList.addAll(bbs_rt.getList());
                }
                //dataList.addAll(bbs_rt.getList());


                Log.d("bbs","dataList大小"+dataList.size());
            }

        }).start();*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //intent=new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                //startActivity(intent);
                intent=new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("bbs_url",webUrl);
                startActivity(intent);

            }
        });
    }
    String webUrl="http://bbs.fengniao.com/forum/8947809.html";
}
