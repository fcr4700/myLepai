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

import qf.com.myprojectlepai.Bean.zixunBean.picBean.FTuShangData;
import qf.com.myprojectlepai.Bean.zixunBean.picBean.TuShangdata;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.adapter.PicAdapter;
import qf.com.myprojectlepai.utils.FHeadlerJson;

/**
 * Created by Administrator on 16-9-6.
 */
public class PicPersonFragment extends Fragment{
    PullToRefreshGridView gridView;
    List<TuShangdata> contentList=new ArrayList<>();
    FTuShangData list;
    PicAdapter adapter;
    String URL_PAGE;
    String jsonString;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);jsonString= (String) msg.obj;
            Gson gson=new Gson();
            list=gson.fromJson(jsonString,FTuShangData.class);
            if (list!=null && list.getList()!=null) {
                for (TuShangdata pc : list.getList()) {
                    contentList.add(pc);
                }
            }
            adapter=new PicAdapter(getActivity(),contentList);
            gridView.setAdapter(adapter);
        }
    };
    private int i=1;
    public PicPersonFragment getPicInstance(String name){
        PicPersonFragment fragment=new PicPersonFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pic_gridview,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView= (PullToRefreshGridView) view.findViewById(R.id.picGridView);
        gridView.setMode(PullToRefreshBase.Mode.BOTH);

        if (getArguments().getString("name").equals("RENXIANG")){
            //URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=296&page=";
            URL_PAGE="http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=101&page=";
            String url=URL_PAGE+"1";
            FHeadlerJson.getJson(url,handler);

        }else if (getArguments().getString("name").equals("FENGGUANG")){
            //URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=190&page=";
            URL_PAGE="http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=125&page=";
            String url=URL_PAGE+"1";
            FHeadlerJson.getJson(url,handler);
        }else if (getArguments().getString("name").equals("SHENGTAI")){
            //URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=192&page=";
            //URL_PAGE="http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=190&page=";
            URL_PAGE="http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=16&page=";
            String url=URL_PAGE+"1";
            FHeadlerJson.getJson(url,handler);
        }else if (getArguments().getString("name").equals("SHUMA")) {
            //URL_PAGE="http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=125&page=";
            URL_PAGE = "http://api.fengniao.com//app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=24&page=";
            String url = URL_PAGE + "1";
            FHeadlerJson.getJson(url, handler);


//        if (getArguments().getString("name").equals("RENXIANG")){
//            URL_PAGE="http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=101&page=";
//            String url=URL_PAGE+1;
//            FHeadlerJson.getJson(url,handler);
//
//        }else if(getArguments().getString("name").equals("FENGGUANG")){
//            //URL_PAGE = "http://http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=125&page=";
//            URL_PAGE="http://http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=125&page=";
//            String url=URL_PAGE+1;
//            FHeadlerJson.getJson(url,handler);
//        }else if (getArguments().getString("name").equals("SHENGTAI")){
//            URL_PAGE = "http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&cid=190&page=";
//            String url=URL_PAGE+1;
//            FHeadlerJson.getJson(url,handler);
//        }else if (getArguments().getString("name").equals("SHUMA")){
//            URL_PAGE = "http://api.fengniao.com//app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&osType=Android&osVersion=4.1.1&fid=24&page=";
//            String url=URL_PAGE+1;
//            FHeadlerJson.getJson(url,handler);
//        }
        }
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                i+=1;
                String urlX=URL_PAGE+i;
                FHeadlerJson.getJson(urlX,handler);
                adapter.notifyDataSetChanged();;
                gridView.onRefreshComplete();
            }
        });
    }
}
