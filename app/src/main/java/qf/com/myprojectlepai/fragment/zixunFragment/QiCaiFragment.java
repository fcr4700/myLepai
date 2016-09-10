package qf.com.myprojectlepai.fragment.zixunFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import qf.com.myprojectlepai.Bean.zixunBean.qicai.QiCaiBean;
import qf.com.myprojectlepai.Bean.zixunBean.qicai.QiCaiContent;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.adapter.QiCaiAdapter;
import qf.com.myprojectlepai.utils.FHeadlerJson;

/**
 * Created by Administrator on 16-9-6.
 */
public class QiCaiFragment extends Fragment{

    PullToRefreshGridView gridView;
    //List<QiCaiBean.ListBean> list;
    List<QiCaiContent> contentList=new ArrayList<>();

    String jsonString;

    private int i=1;
    QiCaiBean list;
    String URL_PAGE;
    QiCaiAdapter adapter;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            jsonString= (String) msg.obj;
            Gson gson=new Gson();
            list=gson.fromJson(jsonString,QiCaiBean.class);
            if (list.getList()!=null){
                for (QiCaiContent qc:list.getList()){
                    contentList.add(qc);
                }
            }

            adapter=new QiCaiAdapter(contentList,getActivity());
            gridView.setAdapter(adapter);
        }

    };
    public QiCaiFragment getInstance(String name){
        QiCaiFragment fragment=new QiCaiFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pic_gridview,null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView= (PullToRefreshGridView) view.findViewById(R.id.picGridView);
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        if (getArguments().getString("name").equals("QICAI")){
            //URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=296&page=";
            URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=296&page=";
            String url=URL_PAGE+"1";
            FHeadlerJson.getJson(url,handler);

        }else if (getArguments().getString("name").equals("YINGXIANG")){
            URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=190&page=";
            String url=URL_PAGE+"1";
            FHeadlerJson.getJson(url,handler);
        }else if (getArguments().getString("name").equals("XUEYUAN")){
            URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=192&page=";
            String url=URL_PAGE+"1";
            FHeadlerJson.getJson(url,handler);
        }
        //TODO Adapter
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                i+=1;
                String url2=URL_PAGE+i;
                FHeadlerJson.getJson(url2,handler);
                adapter.notifyDataSetChanged();
                gridView.onRefreshComplete();
            }
        });
    }
}
