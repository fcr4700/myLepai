package qf.com.myprojectlepai.fragment.zixunFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.myActivity.BbsRT;

/**
 * Created by Administrator on 16-9-7.
 */
public class BbsContentFragment extends Fragment{


    ListView leftListview;
    ListView rightListView;

    SimpleAdapter adapter;
    SimpleAdapter tempAdapter;


    ArrayAdapter<String> rightAdapter;
    //List<String> rifhtList;
    List<Map<String,Object>> rightList;
    TextView textView;
    //ArrayAdapter<String> rightAdapter;
    List<Map<String,Object>> list;
    public BbsContentFragment getInstace(String name){
        BbsContentFragment fragment=new BbsContentFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }


    int[] leftImg={
            R.drawable.bbs_icon_0,
            R.drawable.bbs_icon_1,
            R.drawable.bbs_icon_2,
            R.drawable.bbs_icon_3,
            R.drawable.bbs_icon_4,
            R.drawable.bbs_icon_5,
            R.drawable.bbs_icon_6,
    };

    String[] names={
            "全部论坛",
            "题材作品区",
            "全部摄影区",
            "二手交易区",
            "全国分站区",
            "器材讨论区",
            "论坛服务区"

    };

    String[] rightname={"热帖","精华帖","最新帖子","最新回复"};
    String[] r2name={"人像","风光","纪实","人体","儿童","人体","建筑","生态","宠物"};
    String[] r3name={"商业","女士视觉","新手","数码","黑白","实验","生活摄影","高校","手机","葡萄酒"};
    String[] r4name={"交易警示","二手交易","器材维修"};
    String[] r5name={"北京","上海","武汉"};
    String[] r6name={"单反和镜头","大中画幅","便携数码"};
    String[] r7name={"活动区","网友服务","蜂鸟茶馆"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bbs_list,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView= (TextView) view.findViewById(R.id.bbs_rightTextId);
        leftListview= (ListView) view.findViewById(R.id.bbs_listviewId);
        rightListView= (ListView) view.findViewById(R.id.bbs_listview2Id);
        list=new ArrayList<>();
        rightList=new ArrayList<Map<String, Object>>();
        initData();
        //initRightData();
        adapter=new SimpleAdapter(getContext(),list,R.layout.bbs_item_list,
                new String[]{"image","name"},new int[]{R.id.bbs_imageId,R.id.bbs_nameId} );

        leftListview.setAdapter(adapter);
        rightListView.setAdapter(rightAdapter);



        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        rightList.clear();
                        initRightData();
                        initTempAdapter();
                        break;
                    case 1:
                        rightList.clear();
                        initRightData2();
                        initTempAdapter();
                        break;
                    case 2:
                        rightList.clear();
                        initRightData3();
                        initTempAdapter();
                        break;
                    case 3:
                        rightList.clear();
                        initRightData4();
                        initTempAdapter();
                        break;
                    case 4:
                        rightList.clear();
                        initRightData5();
                        initTempAdapter();
                        break;
                    case 5:
                        rightList.clear();
                        initRightData6();
                        initTempAdapter();
                        break;
                    case 6:
                        rightList.clear();
                        initRightData7();
                        initTempAdapter();
                        break;
                }
            }
        });
        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(getContext(), BbsRT.class);
                        startActivity(intent);
                        Toast.makeText(getContext(),"kdkd",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        if (getArguments()!=null){
            String name=getArguments().getString("name");
            Log.d("fcr","==Log=="+name);

        }
    }
    public void initTempAdapter(){
        tempAdapter=new SimpleAdapter(getContext(),rightList,R.layout.bbs_item_rightlist,
                new String[]{"msg"},new int[]{R.id.bbs_rightTextId});
        rightListView.setAdapter(tempAdapter);
        tempAdapter.notifyDataSetChanged();
    }


    public void initRightData7(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",r7name[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",r7name[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",r7name[2]);
        rightList.add(rMap2);
    }
    public void initRightData6(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",r6name[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",r6name[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",r6name[2]);
        rightList.add(rMap2);
    }
    public void initRightData5(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",r5name[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",r5name[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",r5name[2]);
        rightList.add(rMap2);
    }
    public void initRightData4(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",r4name[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",r4name[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",r4name[2]);
        rightList.add(rMap2);
    }

    public void initRightData3(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",r3name[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",r3name[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",r3name[2]);
        rightList.add(rMap2);

        HashMap<String,Object> rMap3=new HashMap<>();
        rMap3.put("msg",r3name[3]);
        rightList.add(rMap3);

        HashMap<String,Object> rMap4=new HashMap<>();
        rMap4.put("msg",r3name[4]);
        rightList.add(rMap4);
        HashMap<String,Object> rMap5=new HashMap<>();
        rMap5.put("msg",r3name[5]);
        rightList.add(rMap3);
        HashMap<String,Object> rMap6=new HashMap<>();
        rMap6.put("msg",r3name[6]);
        rightList.add(rMap6);

    }
    public void initRightData2(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",r2name[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",r2name[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",r2name[2]);
        rightList.add(rMap2);

        HashMap<String,Object> rMap3=new HashMap<>();
        rMap3.put("msg",r2name[3]);
        rightList.add(rMap3);

        HashMap<String,Object> rMap4=new HashMap<>();
        rMap4.put("msg",r2name[4]);
        rightList.add(rMap4);
        HashMap<String,Object> rMap5=new HashMap<>();
        rMap5.put("msg",r2name[5]);
        rightList.add(rMap3);
        HashMap<String,Object> rMap6=new HashMap<>();
        rMap6.put("msg",r2name[6]);
        rightList.add(rMap6);

    }
    public void initRightData(){
        HashMap<String,Object> rMap0=new HashMap<>();
        rMap0.put("msg",rightname[0]);
        rightList.add(rMap0);

        HashMap<String,Object> rMap1=new HashMap<>();
        rMap1.put("msg",rightname[1]);
        rightList.add(rMap1);

        HashMap<String,Object> rMap2=new HashMap<>();
        rMap2.put("msg",rightname[2]);
        rightList.add(rMap2);

        HashMap<String,Object> rMap3=new HashMap<>();
        rMap3.put("msg",rightname[3]);
        rightList.add(rMap3);

    }
    private void initData() {
        HashMap<String,Object> map1;
        map1=new HashMap<>();
        map1.put("image",leftImg[0]);
        map1.put("name",names[0]);

        HashMap<String,Object> map2;
        map2=new HashMap<>();
        map2.put("image",leftImg[1]);
        map2.put("name",names[1]);

        HashMap<String,Object> map3;
        map3=new HashMap<>();
        map3.put("image",leftImg[2]);
        map3.put("name",names[2]);

        HashMap<String,Object> map4;
        map4=new HashMap<>();
        map4.put("image",leftImg[3]);
        map4.put("name",names[3]);

        HashMap<String,Object> map5;
        map5=new HashMap<>();
        map5.put("image",leftImg[4]);
        map5.put("name",names[4]);

        HashMap<String,Object> map6;
        map6=new HashMap<>();
        map6.put("image",leftImg[5]);
        map6.put("name",names[5]);

        HashMap<String,Object> map7;
        map7=new HashMap<>();
        map7.put("image",leftImg[6]);
        map7.put("name",names[6]);

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
    }

}
